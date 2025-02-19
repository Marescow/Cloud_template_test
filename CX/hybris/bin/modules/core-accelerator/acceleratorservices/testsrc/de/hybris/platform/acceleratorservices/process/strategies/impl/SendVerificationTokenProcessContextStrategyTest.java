/*
 * Copyright (c) 2024 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.acceleratorservices.process.strategies.impl;

import static org.mockito.Mockito.when;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.model.SendVerificationTokenProcessModel;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.processengine.model.BusinessProcessModel;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class SendVerificationTokenProcessContextStrategyTest {
	@InjectMocks
	private SendVerificationTokenProcessContextStrategy strategy;

	@Mock
	private SendVerificationTokenProcessModel businessProcessModel;

	@Mock
	private BaseSiteModel site;

	@Mock
	private LanguageModel language;



	@Test
	public void testGetCmsSite() {
		when(businessProcessModel.getSite()).thenReturn(site);
		Assert.assertEquals(site, strategy.getCmsSite(businessProcessModel));

		BusinessProcessModel invalidModel = new BusinessProcessModel();
		Assert.assertNull(strategy.getCmsSite(invalidModel));
	}

	@Test
	public void testComputeLanguage() {
		when(businessProcessModel.getLanguage()).thenReturn(language);
		Assert.assertEquals(language, strategy.computeLanguage(businessProcessModel));

		BusinessProcessModel invalidModel = new BusinessProcessModel();
		Assert.assertNull(strategy.computeLanguage(invalidModel));
	}

	@Test
	public void testGetCustomer() {
		Assert.assertNull(strategy.getCustomer(businessProcessModel));
	}
}
