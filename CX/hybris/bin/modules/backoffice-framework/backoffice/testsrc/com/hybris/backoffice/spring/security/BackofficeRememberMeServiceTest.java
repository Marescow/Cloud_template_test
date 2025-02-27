/*
 * Copyright (c) 2020 SAP SE or an SAP affiliate company. All rights reserved
 */
package com.hybris.backoffice.spring.security;

import static com.hybris.backoffice.spring.security.BackofficeRememberMeService.ORG_ZKOSS_WEB_PREFERRED_LOCALE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.jalo.user.LoginToken;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class BackofficeRememberMeServiceTest
{

	private static final String ISO_CODE = "fr";

	@Mock
	private LanguageModel languageModel;
	@Mock
	private LoginToken loginToken;
	@Mock
	private HttpServletRequest request;
	@Mock
	private HttpServletResponse response;
	@Mock
	private HttpSession session;

	@Mock
	private CommonI18NService commonI18NService;

	@InjectMocks
	private BackofficeRememberMeService backofficeRememberMeService;


	@Test
	public void shouldSetLocaleFromCookie()
	{
		// given
		when(request.getSession()).thenReturn(session);
		when(commonI18NService.getCurrentLanguage()).thenReturn(languageModel);
		when(commonI18NService.getLocaleForIsoCode(ISO_CODE)).thenReturn(Locale.FRENCH);
		when(languageModel.getIsocode()).thenReturn(ISO_CODE);

		// when
		backofficeRememberMeService.processAutoLoginCookie(loginToken, request, response);

		// then
		assertZkLocaleSet();
	}

	protected void assertZkLocaleSet()
	{
		final ArgumentCaptor<String> attributeNameCaptor = ArgumentCaptor.forClass(String.class);
		final ArgumentCaptor<Locale> localeCaptor = ArgumentCaptor.forClass(Locale.class);
		verify(session).setAttribute(attributeNameCaptor.capture(), localeCaptor.capture());

		assertThat(attributeNameCaptor.getValue()).isEqualTo(ORG_ZKOSS_WEB_PREFERRED_LOCALE);
		assertThat(localeCaptor.getValue().toLanguageTag()).isEqualTo(ISO_CODE);
	}


}
