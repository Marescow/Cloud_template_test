/*
 * Copyright (c) 2024 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.acceleratorservices.process.email.context.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.acceleratorservices.email.CMSEmailPageService;
import de.hybris.platform.acceleratorservices.email.EmailGenerationService;
import de.hybris.platform.acceleratorservices.email.EmailService;
import de.hybris.platform.acceleratorservices.model.cms2.pages.EmailPageModel;
import de.hybris.platform.acceleratorservices.model.email.EmailAddressModel;
import de.hybris.platform.acceleratorservices.model.email.EmailMessageModel;
import de.hybris.platform.acceleratorservices.process.email.actions.GenerateEmailAction;
import de.hybris.platform.acceleratorservices.process.email.actions.GenerateVerificationTokenEmailAction;
import de.hybris.platform.acceleratorservices.process.strategies.ProcessContextResolutionStrategy;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.commerceservices.model.SendVerificationTokenProcessModel;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class GenerateVerificationTokenEmailActionTest {
	@Mock
	private ProcessContextResolutionStrategy contextResolutionStrategy;
	@Mock
	private CMSEmailPageService cmsEmailPageService;
	@Mock
	private CatalogVersionModel contentCatalogVersion;
	@Mock
	private EmailPageModel emailPageModel;
	@Mock
	private EmailGenerationService emailGenerationService;
	@Mock
	private EmailService emailService;

	@Mock
	private ModelService modelService;

	@InjectMocks
	private GenerateVerificationTokenEmailAction action;

	private SendVerificationTokenProcessModel processModel;
	private EmailMessageModel emailMessageModel;
	private EmailAddressModel emailAddressModel;
	private List<EmailMessageModel> emails;

	@Before
	public void setUp() {
		emailAddressModel = Mockito.mock(EmailAddressModel.class);
		emailMessageModel = Mockito.mock(EmailMessageModel.class);
		emails = new ArrayList<>();
		emails.add(emailMessageModel);

		processModel = new SendVerificationTokenProcessModel();
		processModel.setEmails(emails);

		Mockito.when(contextResolutionStrategy.getContentCatalogVersion(processModel)).thenReturn(contentCatalogVersion);

		Mockito.when(cmsEmailPageService.getEmailPageForFrontendTemplate(nullable(String.class), nullable(contentCatalogVersion.getClass())))
				.thenReturn(emailPageModel);


		emailMessageModel = Mockito.mock(EmailMessageModel.class);
		Mockito.when(emailGenerationService.generate(processModel, emailPageModel)).thenReturn(emailMessageModel);
	}

	@Test
	public void executeActionOk() throws Exception {
		processModel.setTarget("test@test.com");
		when(emailService.getOrCreateEmailAddressForEmail(anyString(), anyString())).thenReturn(emailAddressModel);

		Assert.assertEquals(GenerateEmailAction.Transition.OK, action.executeAction(processModel));
		verify(emailService, times(1)).getOrCreateEmailAddressForEmail(anyString(), anyString());
		verify(modelService, times(1)).save(any(EmailMessageModel.class));
	}

	@Test
	public void executeActionNoTarget() throws Exception {
		Assert.assertEquals(GenerateEmailAction.Transition.NOK, action.executeAction(processModel));
		verify(emailService, never()).getOrCreateEmailAddressForEmail(anyString(), anyString());
		verify(modelService, never()).save(any(EmailMessageModel.class));
	}
}
