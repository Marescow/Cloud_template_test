/*
 * Copyright (c) 2022 SAP SE or an SAP affiliate company. All rights reserved.
 */

package de.hybris.platform.spring.security;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.core.Registry;
import de.hybris.platform.core.enums.SAPUserVerificationPurpose;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserGroupModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.jalo.user.Customer;
import de.hybris.platform.servicelayer.ServicelayerTransactionalBaseTest;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserConstants;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.servicelayer.user.impl.DefaultSapUserVerificationTokenService;
import de.hybris.platform.testframework.PropertyConfigSwitcher;

import javax.annotation.Resource;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static java.time.Instant.now;
import static java.util.Collections.singleton;
import static java.util.Date.from;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.fail;


@IntegrationTest
public class CoreUserDetailsServiceTest extends ServicelayerTransactionalBaseTest
{

	public static final String TEST_USER_UID = "testUser@sap.com";
	public static final String TEST_USER_GROUP_UID = "testUserGroup";
	public static final String TEST_ROLE_PREFIX = "TEST_ROLE";

	private final PropertyConfigSwitcher loginAnonymousDisabled = new PropertyConfigSwitcher(
			Customer.LOGIN_ANONYMOUS_ALWAYS_DISABLED);

	@Resource
	private ModelService modelService;
	@Resource
	private CommonI18NService commonI18NService;
	@Resource
	private UserService userService;

	@Resource
	private DefaultSapUserVerificationTokenService userVerificationTokenService;

	private UserModel user;

	private UserGroupModel userGroup;

	@Before
	public void setUp() throws Exception
	{
		userGroup = createAndSaveUserGroup();
		user = createAndSaveUser();
	}

	@After
	public void tearDown()
	{
		loginAnonymousDisabled.switchBackToDefault();
	}

	@Test
	public void testLoadUserByUsernameWithNullUserName()
	{
		//given
		final CoreUserDetailsService coreUserDetailsService = createCoreUserDetailsService(false);

		//when
		final CoreUserDetails coreUserDetails = coreUserDetailsService.loadUserByUsername(null);

		//then
		assertThat(coreUserDetails).isNull();
	}

	@Test
	public void testLoadUserByUsernameWithExistingUser()
	{
		//when
		final CoreUserDetailsService coreUserDetailsService = createCoreUserDetailsService(false);
		final CoreUserDetails coreUserDetails = coreUserDetailsService.loadUserByUsername(TEST_USER_UID);

		//then
		assertThat(coreUserDetails).isNotNull();
		assertThat(user.getUid()).isEqualTo(coreUserDetails.getUsername());
		assertThat(user.getEncodedPassword()).isEqualTo(coreUserDetails.getPassword());
		assertThat(user.getSessionLanguage().getIsocode()).isEqualTo(coreUserDetails.getLanguageISO());
		assertThat(coreUserDetails.isEnabled()).isTrue();
		assertThat(coreUserDetails.isAccountNonExpired()).isTrue();
		assertThat(coreUserDetails.isAccountNonLocked()).isTrue();
		assertThat(coreUserDetails.isCredentialsNonExpired()).isTrue();
		final Collection<GrantedAuthority> authorities = coreUserDetails.getAuthorities();
		assertThat(authorities).isNotNull().hasSize(1);
		final GrantedAuthority authority = authorities.iterator().next();
		assertThat(TEST_ROLE_PREFIX + userGroup.getUid().toUpperCase()).isEqualTo(authority.getAuthority());
		assertThat(user.getPk()).isEqualTo(coreUserDetails.getPk());
	}

	@Test
	public void testLoadUserByValidVerificationTokenAndOTPLoginEnabled()
	{

		//given
		final CoreUserDetailsService coreUserDetailsService = createCoreUserDetailsService(true);
		final var userVerificationTokenData = userVerificationTokenService.create(SAPUserVerificationPurpose.LOGIN,
				user.getUid());

		//when
		final CoreUserDetails coreUserDetails = coreUserDetailsService.loadUserByUsername(userVerificationTokenData.getTokenId());

		//then
		assertThat(coreUserDetails).isNotNull();
		assertThat(coreUserDetails.getVerificationToken()).isNotNull();
		assertThat(coreUserDetails.getVerificationToken().getHashedTokenId()).isEqualTo(
				userVerificationTokenData.getToken().getHashedTokenId());
		assertThat(coreUserDetails.getVerificationToken().getEncodedTokenCode()).isEqualTo(
				userVerificationTokenData.getToken().getEncodedTokenCode());
		assertThat(user.getUid()).isEqualTo(coreUserDetails.getUsername());
		assertThat(user.getEncodedPassword()).isEqualTo(coreUserDetails.getPassword());
		assertThat(user.getSessionLanguage().getIsocode()).isEqualTo(coreUserDetails.getLanguageISO());
		assertThat(coreUserDetails.isEnabled()).isTrue();
		assertThat(coreUserDetails.isAccountNonExpired()).isTrue();
		assertThat(coreUserDetails.isAccountNonLocked()).isTrue();
		assertThat(coreUserDetails.isCredentialsNonExpired()).isTrue();
		final Collection<GrantedAuthority> authorities = coreUserDetails.getAuthorities();
		assertThat(authorities).isNotNull().hasSize(1);
		final GrantedAuthority authority = authorities.iterator().next();
		assertThat(TEST_ROLE_PREFIX + userGroup.getUid().toUpperCase()).isEqualTo(authority.getAuthority());
		assertThat(user.getPk()).isEqualTo(coreUserDetails.getPk());
	}

	@Test
	public void testShouldFailToLoadUserByValidVerificationTokenWhenOTPLoginIsDisabled()
	{
		//given
		final CoreUserDetailsService coreUserDetailsService = createCoreUserDetailsService(false);
		final var userVerificationTokenData = userVerificationTokenService.create(SAPUserVerificationPurpose.LOGIN,
				user.getUid());
		final var tokenId = userVerificationTokenData.getTokenId();

		//when
		final UsernameNotFoundException expected = assertThrows(UsernameNotFoundException.class,
				() -> coreUserDetailsService.loadUserByUsername(tokenId));

		//then
		assertThat(expected.getMessage()).isEqualTo("Token id was provided for login, but OTP login is disabled!");
	}

	@Test
	public void testLoadUserEvenIfVerificationTokenIsOutdatedAndOTPLoginEnabled()
	{

		//given
		final CoreUserDetailsService coreUserDetailsService = createCoreUserDetailsService(true);
		final int outdatedTtl = -1;
		final var userVerificationTokenData = userVerificationTokenService.createVerificationToken(
				SAPUserVerificationPurpose.LOGIN, TEST_USER_UID, outdatedTtl);

		//when
		final CoreUserDetails coreUserDetails = coreUserDetailsService.loadUserByUsername(userVerificationTokenData.getTokenId());

		//then
		assertThat(coreUserDetails).isNotNull();
		assertThat(user.getUid()).isEqualTo(coreUserDetails.getUsername());
		assertThat(user.getEncodedPassword()).isEqualTo(coreUserDetails.getPassword());
		assertThat(user.getSessionLanguage().getIsocode()).isEqualTo(coreUserDetails.getLanguageISO());
		assertThat(coreUserDetails.isEnabled()).isTrue();
		assertThat(coreUserDetails.isAccountNonExpired()).isTrue();
		assertThat(coreUserDetails.isAccountNonLocked()).isTrue();
		assertThat(coreUserDetails.isCredentialsNonExpired()).isTrue();
		final Collection<GrantedAuthority> authorities = coreUserDetails.getAuthorities();
		assertThat(authorities).isNotNull().hasSize(1);
		final GrantedAuthority authority = authorities.iterator().next();
		assertThat(TEST_ROLE_PREFIX + userGroup.getUid().toUpperCase()).isEqualTo(authority.getAuthority());
		assertThat(user.getPk()).isEqualTo(coreUserDetails.getPk());
	}

	@Test
	public void testLoadUserByUsernameWithExistingUserAndLoginDisabled()
	{

		//given
		final CoreUserDetailsService coreUserDetailsService = createCoreUserDetailsService(false);
		coreUserDetailsService.setOneTimeLoginPasswordEnabled(false);
		user.setLoginDisabled(true);
		modelService.save(user);

		//when
		final CoreUserDetails coreUserDetails = coreUserDetailsService.loadUserByUsername(TEST_USER_UID);

		//then
		assertThat(coreUserDetails).isNotNull();
		assertThat(coreUserDetails.isEnabled()).isFalse();
	}

	@Test
	public void testLoadUserByUsernameWithDeactivatedUserInThePast()
	{

		//given
		final CoreUserDetailsService coreUserDetailsService = createCoreUserDetailsService(false);

		final Instant yesterday = now().minus(1, ChronoUnit.DAYS);
		user.setDeactivationDate(from(yesterday));
		modelService.save(user);

		//when
		final CoreUserDetails coreUserDetails = coreUserDetailsService.loadUserByUsername(TEST_USER_UID);

		//then
		assertThat(coreUserDetails).isNotNull();
		assertThat(coreUserDetails.isEnabled()).isFalse();
	}

	@Test
	public void testLoadUserByUsernameWithDeactivatedUserInTheFuture()
	{
		//given
		final CoreUserDetailsService coreUserDetailsService = createCoreUserDetailsService(false);

		final Instant tomorrow = now().plus(1, ChronoUnit.DAYS);
		user.setDeactivationDate(from(tomorrow));
		modelService.save(user);

		//when
		final CoreUserDetails coreUserDetails = coreUserDetailsService.loadUserByUsername(TEST_USER_UID);

		//then
		assertThat(coreUserDetails).isNotNull();
		assertThat(coreUserDetails.isEnabled()).isTrue();
	}

	@Test(expected = UsernameNotFoundException.class)
	public void testLoadUserByUsernameWithNonExistingUser()
	{
		//given
		final CoreUserDetailsService coreUserDetailsService = createCoreUserDetailsService(false);

		// when then
		coreUserDetailsService.loadUserByUsername("foo");

		fail("should throw UsernameNotFoundException");
	}

	@Test
	public void testLoadByUsernameWithAnonymousUser()
	{
		final CustomerModel anonymousUser = userService.getAnonymousUser();
		final boolean isAnonymousUserLoginDisabled = anonymousUser.isLoginDisabled();
		try {
			//given
			final CoreUserDetailsService coreUserDetailsService = createCoreUserDetailsService(false);

			loginAnonymousDisabled.switchToValue(Boolean.FALSE.toString());
		anonymousUser.setLoginDisabled(false);
		modelService.save(anonymousUser);

			//when
			final CoreUserDetails coreUserDetails = coreUserDetailsService.loadUserByUsername(
					UserConstants.ANONYMOUS_CUSTOMER_UID);

			//then
			assertThat(coreUserDetails).isNotNull();
			assertThat(UserConstants.ANONYMOUS_CUSTOMER_UID).isEqualTo(coreUserDetails.getUsername());
			assertThat(coreUserDetails.isEnabled()).isTrue();
		}
		finally
		{
			anonymousUser.setLoginDisabled(isAnonymousUserLoginDisabled);
			modelService.save(anonymousUser);
		}
	}

	@Test
	public void testLoadByUsernameWithAnonymousUserAndLoginDisabled()
	{
		//given
		final CoreUserDetailsService coreUserDetailsService = createCoreUserDetailsService(false);
		loginAnonymousDisabled.switchToValue(Boolean.TRUE.toString());

		//when
		final CoreUserDetails coreUserDetails = coreUserDetailsService.loadUserByUsername(UserConstants.ANONYMOUS_CUSTOMER_UID);

		//then
		assertThat(coreUserDetails).isNotNull();
		assertThat(UserConstants.ANONYMOUS_CUSTOMER_UID).isEqualTo(coreUserDetails.getUsername());
		assertThat(coreUserDetails.isEnabled()).isFalse();
	}

	private CoreUserDetailsService createCoreUserDetailsService(final boolean isOneTimeLoginPasswordEnabled)

	{
		final CoreUserDetailsService coreUserDetailsService = new CoreUserDetailsService();
		coreUserDetailsService.setApplicationContext(Registry.getApplicationContext());
		coreUserDetailsService.afterPropertiesSet();
		coreUserDetailsService.setRolePrefix(TEST_ROLE_PREFIX);
		coreUserDetailsService.setOneTimeLoginPasswordEnabled(isOneTimeLoginPasswordEnabled);
		return coreUserDetailsService;
	}

	private UserGroupModel createAndSaveUserGroup()
	{
		final UserGroupModel userGroup = modelService.create(UserGroupModel.class);
		userGroup.setUid(TEST_USER_GROUP_UID);
		userGroup.setName("Test user group");

		modelService.save(userGroup);

		return userGroup;
	}

	private UserModel createAndSaveUser()
	{
		final UserModel user = modelService.create(UserModel.class);
		user.setUid(TEST_USER_UID);
		user.setName("Test user");
		user.setEncodedPassword("1234");
		user.setLoginDisabled(false);
		user.setDeactivationDate(null);
		user.setSessionLanguage(commonI18NService.getLanguage("en"));
		user.setGroups(singleton(userGroup));

		modelService.save(user);

		return user;
	}

}
