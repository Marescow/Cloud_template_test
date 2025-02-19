/*
 * Copyright (c) 2022 SAP SE or an SAP affiliate company. All rights reserved.
 */

package de.hybris.platform.spring.security;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.core.enums.SAPUserVerificationPurpose;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.EmployeeModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.jalo.user.UserManager;
import de.hybris.platform.persistence.security.PasswordEncoderFactory;
import de.hybris.platform.servicelayer.ServicelayerBaseTest;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserIdDecorationService;
import de.hybris.platform.servicelayer.user.UserIdDecorationStrategy;
import de.hybris.platform.servicelayer.user.impl.DefaultSapUserVerificationTokenService;
import de.hybris.platform.servicelayer.user.impl.DefaultUserIdDecorationService;
import de.hybris.platform.util.Config;

import javax.annotation.Resource;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@IntegrationTest
public class CoreAuthenticationProviderTest extends ServicelayerBaseTest
{
	@Resource
	private ModelService modelService;

	@Resource
	private DefaultUserIdDecorationService defaultUserIdDecorationService;

	@Resource
	private DefaultSapUserVerificationTokenService userVerificationTokenService;

	@Resource(name = "core.passwordEncoderFactory")
	private PasswordEncoderFactory encoderFactory;

	@Resource(name = "wsUserDetailsService")
	private CoreUserDetailsService coreUserDetailsService;

	private UserManager userManager;
	private final CoreAuthenticationProvider coreAuthenticationProvider = spy(CoreAuthenticationProvider.class);
	private final RejectUserPreAuthenticationChecks rejectUserPreAuthenticationChecks = spy(
			RejectUserPreAuthenticationChecks.class);

	private static final String USER_ID = "test@sap.com";
	private static final String USER_PASSWORD = "1234";
	private static final String FOO_DECORATOR = "|foo";
	private static final String BAR_DECORATOR = "|bar";

	@Before
	public void setUp()
	{
		userManager = UserManager.getInstance();
		coreAuthenticationProvider.setUserDetailsService(coreUserDetailsService);
		coreAuthenticationProvider.setPreAuthenticationChecks(rejectUserPreAuthenticationChecks);
		coreAuthenticationProvider.setUserVerificationTokenService(userVerificationTokenService);
	}

	@After
	public void tearDown()
	{
		userManager.setUserIdDecorationService(defaultUserIdDecorationService);
	}

	@Test
	public void shouldAuthenticateExistingUser()
	{
		//given
		enableOTPLoginConfiguration(false);
		createUser(USER_ID);
		final Authentication testAuthentication = createTestAuthentication(USER_ID, USER_PASSWORD);

		//when
		final Authentication authentication = coreAuthenticationProvider.authenticate(testAuthentication);

		//then
		assertThat(authentication).isNotNull();
	}

	@Test
	public void shouldSucceedToAuthenticateExistingCustomerIfValidUsernameAndPasswordProvidedAndOTPIsDisabled()
	{
		//given
		enableOTPLoginConfiguration(false);
		createCustomer(USER_ID);
		final Authentication testAuthentication = createTestAuthentication(USER_ID, USER_PASSWORD);

		//when
		final Authentication authentication = coreAuthenticationProvider.authenticate(testAuthentication);

		//then
		assertThat(authentication).isNotNull();
	}

	@Test
	public void shouldSucceedToAuthenticateExistingEmployeeIfValidUsernameAndPasswordProvidedAndOTPIsDisabled()
	{
		//given
		enableOTPLoginConfiguration(false);
		createEmployee(USER_ID);
		final Authentication testAuthentication = createTestAuthentication(USER_ID, USER_PASSWORD);

		//when
		final Authentication authentication = coreAuthenticationProvider.authenticate(testAuthentication);

		//then
		assertThat(authentication).isNotNull();
	}

	@Test
	public void shouldSucceedToAuthenticateExistingEmployeeIfValidUsernameAndPasswordProvidedAndOTPIsEnabled()
	{
		//given
		enableOTPLoginConfiguration(true);
		createEmployee(USER_ID);
		final Authentication testAuthentication = createTestAuthentication(USER_ID, USER_PASSWORD);

		//when
		final Authentication authentication = coreAuthenticationProvider.authenticate(testAuthentication);

		//then
		assertThat(authentication).isNotNull();
	}

	@Test
	public void shouldAuthenticateExistingCustomerIfValidVerificationTokenIsProvidedAndOTPIsEnabled()
	{

		//given
		enableOTPLoginConfiguration(true);
		createCustomer(USER_ID);


		final var verificationTokenData = userVerificationTokenService.create(SAPUserVerificationPurpose.LOGIN, USER_ID);
		final var tokenId = verificationTokenData.getTokenId();

		final var anotherVerificationTokenData = userVerificationTokenService.create(SAPUserVerificationPurpose.LOGIN, USER_ID);

		final Authentication testAuthentication = createTestAuthentication(verificationTokenData.getTokenId(), verificationTokenData.getTokenCode());

		//when
		final Authentication authentication = coreAuthenticationProvider.authenticate(testAuthentication);

		//then
		assertThat(authentication).isNotNull();
		//verification token is consumed (does not exist any more)
		assertThatThrownBy(() -> userVerificationTokenService.lookupToken(SAPUserVerificationPurpose.LOGIN, tokenId)).isInstanceOf(
				UnknownIdentifierException.class);
		//user is not able to authenticate with the same verification token again
		assertThatThrownBy(() -> coreAuthenticationProvider.authenticate(testAuthentication)).isInstanceOf(
				BadCredentialsException.class);
		//another verification token still exists as it was not consumed
		assertThat(userVerificationTokenService.lookupToken(SAPUserVerificationPurpose.LOGIN,
				anotherVerificationTokenData.getTokenId())).isNotNull();
	}

	@Test
	public void shouldFailToAuthenticateExistingCustomerAndRemoveTokenIfVerificationTokenIsValidAndVerificationCodeDoesNotMatchForXTimesAndOTPIsEnabled()
	{

		//given
		enableOTPLoginConfiguration(true);
		createCustomer(USER_ID);

		final var verificationTokenData = userVerificationTokenService.create(SAPUserVerificationPurpose.LOGIN, USER_ID);
		final var tokenId = verificationTokenData.getTokenId();


		final Authentication testAuthentication = createTestAuthentication(verificationTokenData.getTokenId(), "invalid");

		//when, then
		final var maxNumberOfFailedAttempts = Config.getInt("otp.customer.login.max.failed.attempts", 3);

		for (int i = 0; i < maxNumberOfFailedAttempts; i++)
		{
			assertThatThrownBy(() -> coreAuthenticationProvider.authenticate(testAuthentication)).isInstanceOf(
					BadCredentialsException.class);

			if (i < maxNumberOfFailedAttempts - 1)
			{
				//verification token is still present the failure attempts was increased
				assertThat(userVerificationTokenService.lookupToken(SAPUserVerificationPurpose.LOGIN,
						verificationTokenData.getTokenId()).getFailedVerificationAttempts()).isEqualTo(i + 1);
			}
		}

		//verification token is removed after the last failed attempt
		assertThatThrownBy(() -> userVerificationTokenService.lookupToken(SAPUserVerificationPurpose.LOGIN, tokenId)).isInstanceOf(
				UnknownIdentifierException.class);

	}

	@Test
	public void shouldFailToAuthenticateExistingCustomerIfProvidedVerificationTokenIsOutdatedAndOTPIsEnabled()
	{
		//given
		enableOTPLoginConfiguration(true);
		createCustomer(USER_ID);
		final int outdatedTtl = -1;

		final var verificationTokenData = userVerificationTokenService.createVerificationToken(SAPUserVerificationPurpose.LOGIN,
				USER_ID, outdatedTtl);
		final var tokenId = verificationTokenData.getTokenId();

		final Authentication testAuthentication = createTestAuthentication(verificationTokenData.getTokenId(), verificationTokenData.getTokenCode());

		//when, then
		assertThatThrownBy(() -> coreAuthenticationProvider.authenticate(testAuthentication)).isInstanceOf(
				BadCredentialsException.class);

		//outdated verification token is not retrievable as it got deleted
		assertThatThrownBy(() -> userVerificationTokenService.lookupToken(SAPUserVerificationPurpose.LOGIN, tokenId)).isInstanceOf(
				UnknownIdentifierException.class);
	}

	@Test
	public void shouldFailToAuthenticateExistingCustomerIfVerificationTokenIsMissingAndOTPIsEnabled()
	{

		//given
		enableOTPLoginConfiguration(true);
		createCustomer(USER_ID);


		final var verificationTokenData = userVerificationTokenService.create(SAPUserVerificationPurpose.LOGIN, USER_ID);
		final String noneExistingTokenId= "LNG-missing-verification-token-length-40";

		final Authentication testAuthentication = createTestAuthentication(noneExistingTokenId, verificationTokenData.getTokenCode());

		//when, then
		assertThatThrownBy(() -> coreAuthenticationProvider.authenticate(testAuthentication)).isInstanceOf(
				BadCredentialsException.class);

		//verification token is not consumed
		assertThat(userVerificationTokenService.lookupToken(SAPUserVerificationPurpose.LOGIN,
				verificationTokenData.getTokenId())).isNotNull();
	}

	@Test
	public void shouldFailToAuthenticateExistingCustomerIfValidVerificationTokenAndOTPIsDisabled()
	{

		//given
		enableOTPLoginConfiguration(false);
		createCustomer(USER_ID);


		final var verificationTokenData = userVerificationTokenService.create(SAPUserVerificationPurpose.LOGIN, USER_ID);

		final Authentication testAuthentication = createTestAuthentication(verificationTokenData.getTokenId(), verificationTokenData.getTokenCode());

		//when, then
		assertThatThrownBy(() -> coreAuthenticationProvider.authenticate(testAuthentication)).isInstanceOf(
				BadCredentialsException.class);

		//verification token is not consumed
		assertThat(userVerificationTokenService.lookupToken(SAPUserVerificationPurpose.LOGIN,
				verificationTokenData.getTokenId())).isNotNull();
	}

	@Test
	public void shouldFailToAuthenticateExistingCustomerIfValidUsernameAndPasswordProvidedAndOTPIsEnabled()
	{

		//given
		enableOTPLoginConfiguration(true);
		createCustomer(USER_ID);


		final var verificationTokenData = userVerificationTokenService.create(SAPUserVerificationPurpose.LOGIN,
				USER_ID);

		final Authentication testAuthentication = createTestAuthentication(USER_ID, USER_PASSWORD);

		//when, then
		assertThatThrownBy(() -> coreAuthenticationProvider.authenticate(testAuthentication)).isInstanceOf(
				BadCredentialsException.class);

		//verification token is not consumed
		assertThat(userVerificationTokenService.lookupToken(SAPUserVerificationPurpose.LOGIN,
				verificationTokenData.getTokenId())).isNotNull();
	}

	@Test
	public void shouldThrowExceptionWhenAuthenticatingNotExistingUser()
	{
		//given
		enableOTPLoginConfiguration(false);
		createUser(USER_ID);
		final Authentication testAuthentication = createTestAuthentication(UUID.randomUUID().toString(), USER_PASSWORD);

		//when, then
		assertThatThrownBy(() -> coreAuthenticationProvider.authenticate(testAuthentication)).isInstanceOf(
				BadCredentialsException.class);
	}

	@Test
	public void shouldAuthenticateUserWithDecoratedUserId()
	{
		//given
		enableOTPLoginConfiguration(false);
		final UserIdDecorationStrategy fooDecorationStrategy = mock(UserIdDecorationStrategy.class);
		when(fooDecorationStrategy.decorateUserId(USER_ID)).thenReturn(Optional.of(USER_ID + FOO_DECORATOR));
		configureUserIdDecorationService(List.of(fooDecorationStrategy));
		createCustomerWithDecoratedUserId(USER_ID, FOO_DECORATOR);
		final Authentication testAuthentication = createTestAuthentication(USER_ID, USER_PASSWORD);

		//when
		final Authentication authentication = coreAuthenticationProvider.authenticate(testAuthentication);

		//then
		assertThat(authentication).isNotNull();
	}

	@Test
	public void shouldAuthenticateUserWithDecoratedUserIdByManyStrategies()
	{
		//given
		enableOTPLoginConfiguration(false);
		final UserIdDecorationStrategy fooDecorationStrategy = mock(UserIdDecorationStrategy.class);
		final UserIdDecorationStrategy barDecorationStrategy = mock(UserIdDecorationStrategy.class);
		when(fooDecorationStrategy.decorateUserId(anyString())).thenAnswer(i -> Optional.of(returnFirstArg(i) + FOO_DECORATOR));
		when(barDecorationStrategy.decorateUserId(anyString())).thenAnswer(i -> Optional.of(returnFirstArg(i) + BAR_DECORATOR));
		configureUserIdDecorationService(List.of(fooDecorationStrategy, barDecorationStrategy));
		createCustomerWithDecoratedUserId(USER_ID, FOO_DECORATOR, BAR_DECORATOR);
		final Authentication testAuthentication = createTestAuthentication(USER_ID, USER_PASSWORD);

		//when
		final Authentication authentication = coreAuthenticationProvider.authenticate(testAuthentication);

		//then
		assertThat(authentication).isNotNull();
	}

	@Test
	public void shouldLookUpUserByPKWhenAuthenticatingAndPKIsNotNull()
	{
		//given
		enableOTPLoginConfiguration(false);
		createUser(USER_ID);
		final Authentication testAuthentication = createTestAuthentication(USER_ID, USER_PASSWORD);

		//when
		coreAuthenticationProvider.authenticate(testAuthentication);

		//then
		verify(coreAuthenticationProvider, times(1)).getUserByPK(any());
		verify(coreAuthenticationProvider, times(0)).getUserByLogin(any());
		verify(rejectUserPreAuthenticationChecks, times(1)).getUserByPK(any());
		verify(rejectUserPreAuthenticationChecks, times(0)).getUserByLogin(any());
	}

	@Test
	public void shouldLookUpUserByLoginWhenAuthenticatingAndPKIsNull()
	{
		//given
		enableOTPLoginConfiguration(false);
		createUser(USER_ID);
		final Authentication testAuthentication = createTestAuthentication(USER_ID, USER_PASSWORD);
		when(coreAuthenticationProvider.retrieveUser(USER_ID)).thenReturn(getCoreUserDetailsWithNullPK(USER_ID));

		//when
		coreAuthenticationProvider.authenticate(testAuthentication);

		//then
		verify(coreAuthenticationProvider, times(1)).getUserByLogin(any());
		verify(coreAuthenticationProvider, times(0)).getUserByPK(any());
		verify(rejectUserPreAuthenticationChecks, times(1)).getUserByLogin(any());
		verify(rejectUserPreAuthenticationChecks, times(0)).getUserByPK(any());
	}

	@Test
	public void shouldLookUpUserByLoginWhenAuthenticatingAndUserDetailsIsNotInstanceOfCoreUserDetails()
	{
		//given
		enableOTPLoginConfiguration(false);
		createUser(USER_ID);
		final Authentication testAuthentication = createTestAuthentication(USER_ID, USER_PASSWORD);
		when(coreAuthenticationProvider.retrieveUser(USER_ID)).thenReturn(getUserDetails(USER_ID));

		//when
		coreAuthenticationProvider.authenticate(testAuthentication);

		//then
		verify(coreAuthenticationProvider, times(1)).getUserByLogin(any());
		verify(coreAuthenticationProvider, times(0)).getUserByPK(any());
	}

	@Test
	public void shouldThrowExceptionIfStrategyIsNotAwareOfAlreadyDecoratedUserID()
	{
		/* the exception will be thrown in case that configured UserIdDecorationStrategy is not resistance for situations where
		the such strategy can decorate the same user identifier many times */

		//given
		enableOTPLoginConfiguration(false);
		final UserIdDecorationStrategy fooDecorationStrategy = mock(UserIdDecorationStrategy.class);
		when(fooDecorationStrategy.decorateUserId(anyString())).thenAnswer(i -> Optional.of(returnFirstArg(i) + FOO_DECORATOR));
		configureUserIdDecorationService(List.of(fooDecorationStrategy));
		createUserWithDecoratedUserId(USER_ID, FOO_DECORATOR);
		final Authentication testAuthentication = createTestAuthentication(USER_ID, USER_PASSWORD);
		when(coreAuthenticationProvider.getPKFromUserDetails(any())).thenReturn(null);

		//when, then
		assertThatThrownBy(() -> coreAuthenticationProvider.authenticate(testAuthentication)).isInstanceOf(
				BadCredentialsException.class);
	}

	@Test
	public void shouldAuthenticateUserIfStrategyIsAwareOfAlreadyDecoratedUserID()
	{
		//given
		enableOTPLoginConfiguration(false);
		final UserIdDecorationStrategy awareAlreadyDecoratedUserIdDecorationStrategy = mock(UserIdDecorationStrategy.class);
		when(awareAlreadyDecoratedUserIdDecorationStrategy.decorateUserId(USER_ID)).thenReturn(
				Optional.of(USER_ID + FOO_DECORATOR));
		when(awareAlreadyDecoratedUserIdDecorationStrategy.decorateUserId(USER_ID + FOO_DECORATOR)).thenReturn(Optional.empty());
		configureUserIdDecorationService(List.of(awareAlreadyDecoratedUserIdDecorationStrategy));
		createCustomerWithDecoratedUserId(USER_ID, FOO_DECORATOR);
		final Authentication testAuthentication = createTestAuthentication(USER_ID, USER_PASSWORD);
		when(coreAuthenticationProvider.getPKFromUserDetails(any())).thenReturn(null);

		//when
		final Authentication authentication = coreAuthenticationProvider.authenticate(testAuthentication);

		//then
		assertThat(authentication).isNotNull();
	}

	private void enableOTPLoginConfiguration(final boolean isEnabled)
	{
		coreAuthenticationProvider.setOneTimeLoginPasswordEnabled(isEnabled);
		coreUserDetailsService.setOneTimeLoginPasswordEnabled(isEnabled);
	}

	private CoreUserDetails getCoreUserDetailsWithNullPK(final String userId)
	{
		final CoreUserDetails coreUserDetails = coreUserDetailsService.loadUserByUsername(userId);

		return new CoreUserDetails(coreUserDetails.getUsername(), coreUserDetails.getPassword(), coreUserDetails.isEnabled(),
				coreUserDetails.isAccountNonExpired(), coreUserDetails.isCredentialsNonExpired(),
				coreUserDetails.isAccountNonLocked(), coreUserDetails.getAuthorities(), coreUserDetails.getLanguageISO(), null);
	}

	private org.springframework.security.core.userdetails.User getUserDetails(final String userId)
	{
		final CoreUserDetails coreUserDetails = coreUserDetailsService.loadUserByUsername(userId);

		return new org.springframework.security.core.userdetails.User(coreUserDetails.getUsername(),
				coreUserDetails.getPassword(), coreUserDetails.isEnabled(),
				coreUserDetails.isAccountNonExpired(), coreUserDetails.isCredentialsNonExpired(),
				coreUserDetails.isAccountNonLocked(), coreUserDetails.getAuthorities());
	}

	private void createUser(final String userId)
	{
		final UserModel user = modelService.create(UserModel.class);
		user.setUid(userId);
		user.setEncodedPassword(USER_PASSWORD);

		modelService.save(user);
	}

	private void createUserWithDecoratedUserId(final String userId, final String... decorators)
	{
		createUser(decorateUserIdWithDecorators(userId, decorators));
	}

	private void createCustomer(final String userId)
	{
		final CustomerModel user = modelService.create(CustomerModel.class);
		user.setUid(userId);
		user.setEncodedPassword(USER_PASSWORD);

		modelService.save(user);
	}

	private void createEmployee(final String userId)
	{
		final EmployeeModel user = modelService.create(EmployeeModel.class);
		user.setUid(userId);
		user.setEncodedPassword(USER_PASSWORD);

		modelService.save(user);
	}

	private void createCustomerWithDecoratedUserId(final String userId, final String... decorators)
	{
		createCustomer(decorateUserIdWithDecorators(userId, decorators));
	}

	private String decorateUserIdWithDecorators(final String userId, final String... decorators)
	{
		final StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(userId);

		for (final String decorator : decorators)
		{
			stringBuilder.append(decorator);
		}

		return stringBuilder.toString();
	}

	private Authentication createTestAuthentication(final String principal, final String credentials)
	{

		return new UsernamePasswordAuthenticationToken(principal, credentials);
	}

	private static String returnFirstArg(final InvocationOnMock invocation)
	{
		return (String) invocation.getArguments()[0];
	}

	private void configureUserIdDecorationService(final List<UserIdDecorationStrategy> userIdDecorationStrategies)
	{
		final UserIdDecorationService userIdDecorationService = new DefaultUserIdDecorationService(userIdDecorationStrategies);

		userManager.setUserIdDecorationService(userIdDecorationService);
	}
}