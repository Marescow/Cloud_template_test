/*
 * Copyright (c) 2024 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.chineseprofileservices.security.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.chineseprofileservices.security.ChineseUserDetailsService;
import de.hybris.platform.core.Registry;
import de.hybris.platform.core.enums.SAPUserVerificationPurpose;
import de.hybris.platform.impex.jalo.ImpExException;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.JaloInvalidParameterException;
import de.hybris.platform.jalo.security.JaloSecurityException;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.daos.UserDao;
import de.hybris.platform.servicelayer.user.impl.DefaultSapUserVerificationTokenService;
import de.hybris.platform.spring.security.CoreUserDetails;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


@IntegrationTest
public class ChineseUserDetailsServiceTest extends ServicelayerTransactionalTest
{
	private final String USER_1 = "a@sap.com";
	private final String USER_1_MOBILE = "18108050323";
	private final String USER_2 = "b@sap.com";
	private static final String TEST_ROLE_PREFIX = "TEST_ROLE_";

	@Resource
	private ChineseUserDetailsService chineseUserDetailsService;

	@Resource
	private UserDao userDao;

	@Resource
	private CommonI18NService commonI18NService;

	@Resource
	private ModelService modelService;
	
	@Resource
	private DefaultSapUserVerificationTokenService userVerificationTokenService;

	@Before
	public void prepare() throws ImpExException
	{
		importCsv("/chineseprofileservices/test/impex/chineseprofileservices-test-data.impex", "utf-8");
		final ChineseUserDetailsService chineseUserDetailsServiceObj = new ChineseUserDetailsService();
		chineseUserDetailsServiceObj.setUserDao(userDao);
		chineseUserDetailsServiceObj.setCommonI18NService(commonI18NService);
		chineseUserDetailsServiceObj.setModelService(modelService);
		chineseUserDetailsService = Mockito.spy(chineseUserDetailsServiceObj);
		chineseUserDetailsService.setApplicationContext(Registry.getApplicationContext());
		chineseUserDetailsService.afterPropertiesSet();
		chineseUserDetailsService.setRolePrefix(TEST_ROLE_PREFIX);
	}

	@Test
	public void test_Load_Exists_User() throws JaloInvalidParameterException, JaloSecurityException, JaloBusinessException
	{
		chineseUserDetailsService.setOneTimeLoginPasswordEnabled(false);
		final CoreUserDetails coreUesrDetail = chineseUserDetailsService.loadUserByUsername(USER_1);

		assertEquals("12341234", coreUesrDetail.getPassword());
		assertEquals(new SimpleGrantedAuthority("ROLE_CUSTOMERGROUP"), coreUesrDetail.getAuthorities().toArray()[0]);
	}

	@Test
	public void test_Load_Exists_User_Mobile() throws JaloInvalidParameterException, JaloSecurityException, JaloBusinessException
	{
		chineseUserDetailsService.setOneTimeLoginPasswordEnabled(false);
		final CoreUserDetails coreUesrDetail = chineseUserDetailsService.loadUserByUsername(USER_1_MOBILE);

		assertEquals("12341234", coreUesrDetail.getPassword());
		assertEquals(new SimpleGrantedAuthority("TEST_ROLE_CUSTOMERGROUP"), coreUesrDetail.getAuthorities().toArray()[0]);
	}

	@Test(expected = UsernameNotFoundException.class)
	public void test_Load_Unkown_User()
	{
		chineseUserDetailsService.setOneTimeLoginPasswordEnabled(false);
		chineseUserDetailsService.loadUserByUsername(USER_2);
	}

	@Test
	public void testLoadUserByNullName()
	{
		final CoreUserDetails result;
		result = chineseUserDetailsService.loadUserByUsername(null);
		assertNull(result);
	}

	@Test
	public void testLoadUserByValidVerificationTokenAndOTPLoginEnabled()
	{
		chineseUserDetailsService.setOneTimeLoginPasswordEnabled(true);
		final var userVerificationTokenData = userVerificationTokenService.create(SAPUserVerificationPurpose.LOGIN,
				USER_1);
		final CoreUserDetails coreUserDetail = chineseUserDetailsService.loadUserByUsername(userVerificationTokenData.getTokenId());

		assertThat(coreUserDetail).isNotNull();
		assertThat(coreUserDetail.getVerificationToken()).isNotNull();
		assertThat(USER_1).isEqualTo(coreUserDetail.getUsername());
		assertEquals("12341234", coreUserDetail.getPassword());
	}
}