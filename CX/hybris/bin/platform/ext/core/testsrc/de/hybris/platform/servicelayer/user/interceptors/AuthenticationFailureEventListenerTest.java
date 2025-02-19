/*
 * Copyright (c) 2024 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.servicelayer.user.interceptors;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.core.model.user.BruteForceLoginAttemptsModel;
import de.hybris.platform.core.model.user.BruteForceLoginDisabledAuditModel;
import de.hybris.platform.core.model.user.UserGroupModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.spring.security.exceptions.OtpBadCredentialsException;

import javax.annotation.Resource;

import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import org.junit.Test;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

import static com.google.common.collect.ImmutableMap.of;
import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;


@IntegrationTest
public class AuthenticationFailureEventListenerTest extends AbstractAuthenticationEventListenerTest
{
	@Resource(name = "authenticationFailureEventListener")
	private ApplicationListener listener;

	@Test
	public void testMultiGroups() throws Exception
	{
		UserModel user = userService.getUserForUID(testUid);
		final UserGroupModel group = modelService.create(UserGroupModel.class);
		group.setUid("testGroup2");
		group.setMaxBruteForceLoginAttempts(Integer.valueOf(3));
		user.setGroups((Set) ImmutableSet.builder().addAll(user.getGroups()).add(group).build());
		modelService.saveAll(user, group);
		user = userService.getUserForUID(testUid);
		assertThat(user.getGroups().size()).isEqualTo(2);
		IntStream.range(0, 2).forEach(i -> listener.onApplicationEvent(new AuthenticationFailureBadCredentialsEvent(
				new UsernamePasswordAuthenticationToken(testUid, "pwd"), new BadCredentialsException(""))));
		assertThat(userService.getUserForUID(testUid).isLoginDisabled()).isTrue();
		final BruteForceLoginAttemptsModel attempts = findAttempts();
		assertThat(attempts.getAttempts()).isEqualTo(0);
		final List<BruteForceLoginDisabledAuditModel> result = auditRecords().getResult();
		assertThat(result.size()).isEqualTo(1);
		assertThat(result.get(0).getFailedLogins()).isEqualTo(2);
	}

	@Test
	public void testAttemptCreation() throws Exception
	{
		listener.onApplicationEvent(new AuthenticationFailureBadCredentialsEvent(
				new UsernamePasswordAuthenticationToken(testUid, "pwd"), new BadCredentialsException("")));
		final BruteForceLoginAttemptsModel attempts = findAttempts();
		assertThat(attempts.getAttempts()).isEqualTo(1);
	}

	@Test
	public void testAttemptCreationForOtpCausedException() throws Exception
	{
		//given
		final String ignoredTestUid = "otpTokenId";
		final OtpBadCredentialsException otpBadCredentialsException = new OtpBadCredentialsException(testUid);
		//when
		listener.onApplicationEvent(
				new AuthenticationFailureBadCredentialsEvent(new UsernamePasswordAuthenticationToken(ignoredTestUid, "pwd"),
						new BadCredentialsException("", otpBadCredentialsException)));

		//then
		// attempt for testUid should be recorded
		final BruteForceLoginAttemptsModel attempts = findAttempts();
		assertThat(attempts.getAttempts()).isEqualTo(1);
		// no attempts for ignoredTestUid should be recorded
		final var searchResult = findAttempts(ignoredTestUid);
		assertThat(searchResult.getCount()).isZero();
	}

	@Test
	public void testDisable() throws Exception
	{
		testAttemptCreation();
		listener.onApplicationEvent(new AuthenticationFailureBadCredentialsEvent(
				new UsernamePasswordAuthenticationToken(testUid, "pwd"), new BadCredentialsException("")));
		assertThat(userService.getUserForUID(testUid).isLoginDisabled()).isTrue();
		final BruteForceLoginAttemptsModel attempts = findAttempts();
		assertThat(attempts.getAttempts()).isEqualTo(0);
		assertThat(auditRecords().getResult().size()).isEqualTo(1);
	}

	private SearchResult<BruteForceLoginDisabledAuditModel> auditRecords()
	{
		return searchService.search(format("select {a.pk} from {%s as a}  where {a.%s}=?uid",
				BruteForceLoginDisabledAuditModel._TYPECODE, BruteForceLoginDisabledAuditModel.UID),
				ImmutableMap.of("uid", testUid));
	}

	private SearchResult<BruteForceLoginAttemptsModel> findAttempts(final String userId)
	{
		return searchService.search(new FlexibleSearchQuery(
				format("select {pk} from {%s} where {%s}= ?uid", BruteForceLoginAttemptsModel._TYPECODE,
						BruteForceLoginAttemptsModel.UID), of("uid", userId)));
	}
}
