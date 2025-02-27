/*
 * Copyright (c) 2022 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.samlsinglesignon.utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.servicelayer.ServicelayerTest;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.testframework.PropertyConfigSwitcher;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.security.saml2.provider.service.authentication.Saml2AuthenticatedPrincipal;

import com.google.common.collect.Lists;


@UnitTest
public class DefaultSAMLUtilTest extends ServicelayerTest
{
	private static final String SSO_USERID_KEY = "userIdKey";
	private static final String SSO_FIRSTNAME_KEY = "firstName";
	private static final String SSO_LASTNAME_KEY = "lastName";
	private static final String SSO_CUSTOM_KEY = "custom";
	private static final String SSO_LANGUAGE_KEY = "language";
	private static final String LANGUAGE_ISO_CODE = "zh_TW";

	private Saml2AuthenticatedPrincipal principal;
	private HttpServletRequest httpServletRequest;
	private CommonI18NService commonI18NService;
	private LanguageModel plLanguageModel;
	private LanguageModel deLanguageModel;
	private LanguageModel frLanguageModel;
	final Locale plLocale = new Locale("pl");
	final Locale deLocale = new Locale("de");
	final Locale frLocale = new Locale("fr");

	private final PropertyConfigSwitcher ssoUserIdSwitcher = new PropertyConfigSwitcher(SAMLUtil.SSO_USERID_KEY);
	private final PropertyConfigSwitcher ssoUserFirstNameSwitcher = new PropertyConfigSwitcher(SAMLUtil.SSO_FIRSTNAME_KEY);
	private final PropertyConfigSwitcher ssoUserLastNameSwitcher = new PropertyConfigSwitcher(SAMLUtil.SSO_LASTNAME_KEY);
	private final PropertyConfigSwitcher ssoUserLanguageSwitcher = new PropertyConfigSwitcher(SAMLUtil.SSO_LANGUAGE_KEY);

	@Before
	public void setup()
	{
		principal = Mockito.mock(Saml2AuthenticatedPrincipal.class);

		httpServletRequest = Mockito.mock(HttpServletRequest.class);
		commonI18NService = Mockito.mock(CommonI18NService.class);

		plLanguageModel = Mockito.mock(LanguageModel.class);
		Mockito.when(plLanguageModel.getIsocode()).thenReturn("pl");
		deLanguageModel = Mockito.mock(LanguageModel.class);
		Mockito.when(deLanguageModel.getIsocode()).thenReturn("de");
		frLanguageModel = Mockito.mock(LanguageModel.class);
		Mockito.when(frLanguageModel.getIsocode()).thenReturn("fr");

		Mockito.when(commonI18NService.getAllLanguages())
		       .thenReturn(Lists.newArrayList(plLanguageModel, deLanguageModel, frLanguageModel));
		Mockito.when(commonI18NService.getLocaleForIsoCode("pl")).thenReturn(plLocale);
		Mockito.when(commonI18NService.getLocaleForIsoCode("de")).thenReturn(deLocale);
		Mockito.when(commonI18NService.getLocaleForIsoCode("fr")).thenReturn(frLocale);
		Mockito.when(commonI18NService.getLanguage(any())).thenThrow(UnknownIdentifierException.class);
		Mockito.when(httpServletRequest.getHeader("accept-language")).thenReturn("nonempty string");

		ssoUserIdSwitcher.switchToValue(SSO_USERID_KEY);
		ssoUserFirstNameSwitcher.switchToValue(SSO_FIRSTNAME_KEY);
		ssoUserLastNameSwitcher.switchToValue(SSO_LASTNAME_KEY);
		ssoUserLanguageSwitcher.switchToValue(SSO_LANGUAGE_KEY);
	}

	@After
	public void tearDown()
	{
		ssoUserIdSwitcher.switchBackToDefault();
		ssoUserFirstNameSwitcher.switchBackToDefault();
		ssoUserLastNameSwitcher.switchBackToDefault();
		ssoUserLanguageSwitcher.switchBackToDefault();
	}

	@Test
	public void shouldGetUserId()
	{
		// given
		Mockito.when(principal.getFirstAttribute(SSO_USERID_KEY)).thenReturn("id");

		// when
		final String userId = SAMLUtil.getUserId(principal);

		// then
		assertThat(userId).isEqualTo("id");
	}

	@Test
	public void shouldReturnEmptyForGetUserIdWhenNoSSOKey()
	{
		// given
		Mockito.when(principal.getFirstAttribute(SSO_USERID_KEY)).thenReturn(null);

		// when
		final String userId = SAMLUtil.getUserId(principal);

		// then
		assertThat(userId).isEmpty();
	}

	@Test
	public void shouldGetUserName()
	{
		// given
		Mockito.when(principal.getFirstAttribute(SSO_FIRSTNAME_KEY)).thenReturn("first");
		Mockito.when(principal.getFirstAttribute(SSO_LASTNAME_KEY)).thenReturn("last");

		// when
		final String userName = SAMLUtil.getUserName(principal);

		// then
		assertThat(userName).isEqualTo("first last");
	}

	@Test
	public void shouldGetCustomAttribute()
	{
		// given
		Mockito.when(principal.getFirstAttribute(SSO_CUSTOM_KEY)).thenReturn("custom_value");

		// when
		final String customAttribute = SAMLUtil.getCustomAttribute(principal, SSO_CUSTOM_KEY);

		// then
		assertThat(customAttribute).isEqualTo("custom_value");
	}

	@Test
	public void shouldGetCustomAttributesList()
	{
		// given
		Mockito.when(principal.getAttribute(SSO_CUSTOM_KEY)).thenReturn(Arrays.asList("custom_value1", "custom_value2"));

		// when
		final List<String> customAttributes = SAMLUtil.getCustomAttributes(principal, SSO_CUSTOM_KEY);

		// then
		assertThat(customAttributes).containsOnly("custom_value1", "custom_value2");
	}

	@Test
	public void shouldGetGermanLanguageFromCredential()
	{
		//given
		Mockito.when(principal.getFirstAttribute(SSO_LANGUAGE_KEY)).thenReturn("de");

		//when
		final String language = SAMLUtil.getLanguage(principal, httpServletRequest, commonI18NService);

		//then
		assertThat(language).isEqualTo("de");
	}

	@Test
	public void shouldGetFrenchLanguageFromHttpRequest()
	{
		//given
		Mockito.when(principal.getFirstAttribute(SSO_LANGUAGE_KEY)).thenReturn("ar");
		Mockito.when(httpServletRequest.getLocale()).thenReturn(frLocale);

		//when
		final String language = SAMLUtil.getLanguage(principal, httpServletRequest, commonI18NService);

		//then
		assertThat(language).isEqualTo("fr");
	}

	@Test
	public void shouldGetPolishLanguageAsDefaultLanguage()
	{
		//given
		Mockito.when(principal.getFirstAttribute(SSO_LANGUAGE_KEY)).thenReturn("");
		final Locale czLocale = new Locale("cz");
		Mockito.when(httpServletRequest.getLocale()).thenReturn(czLocale);
		Mockito.when(commonI18NService.getCurrentLanguage()).thenReturn(plLanguageModel);

		//when
		final String language = SAMLUtil.getLanguage(principal, httpServletRequest, commonI18NService);

		//then
		assertThat(language).isEqualTo("pl");
	}

	@Test
	public void shouldGetPolishLanguageAsDefaultLanguageWhenLocaleIsNotAvailable()
	{
		//given
		Mockito.when(principal.getFirstAttribute(SSO_LANGUAGE_KEY)).thenReturn("");
		final Locale czLocale = new Locale("cz");
		Mockito.when(httpServletRequest.getLocale()).thenReturn(czLocale);
		Mockito.when(commonI18NService.getLocaleForIsoCode("cz")).thenReturn(czLocale);
		Mockito.when(commonI18NService.getCurrentLanguage()).thenReturn(plLanguageModel);

		//when
		final String language = SAMLUtil.getLanguage(principal, httpServletRequest, commonI18NService);

		//then
		assertThat(language).isEqualTo("pl");
	}

	@Test
	public void shouldGetPolishLanguageAsDefaultLanguageWhenRequestHeaderLocaleIsNull()
	{
		//given
		Mockito.when(principal.getFirstAttribute(SSO_LANGUAGE_KEY)).thenReturn("");
		Mockito.when(httpServletRequest.getHeader("accept-language")).thenReturn(null);
		Mockito.when(httpServletRequest.getLocale()).thenReturn(deLocale);
		Mockito.when(commonI18NService.getCurrentLanguage()).thenReturn(plLanguageModel);

		//when
		final String language = SAMLUtil.getLanguage(principal, httpServletRequest, commonI18NService);

		//then
		assertThat(language).isEqualTo("pl");
	}

	@Test
	public void shouldGetLanguageFromCredentialIsoCode()
	{
		//given
		Mockito.when(principal.getFirstAttribute(SSO_LANGUAGE_KEY)).thenReturn(LANGUAGE_ISO_CODE);
		final LanguageModel languageModel = Mockito.mock(LanguageModel.class);
		Mockito.when(languageModel.getIsocode()).thenReturn(LANGUAGE_ISO_CODE);
		Mockito.reset(commonI18NService);
		Mockito.when(commonI18NService.getLanguage(LANGUAGE_ISO_CODE)).thenReturn(languageModel);

		//when
		final String language = SAMLUtil.getLanguage(principal, httpServletRequest, commonI18NService);

		//then
		assertThat(language).isEqualTo(LANGUAGE_ISO_CODE);
	}

}
