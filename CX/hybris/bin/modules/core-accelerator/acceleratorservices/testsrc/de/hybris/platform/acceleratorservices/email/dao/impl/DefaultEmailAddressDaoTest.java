/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.acceleratorservices.email.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.acceleratorservices.email.dao.EmailAddressDao;
import de.hybris.platform.acceleratorservices.model.email.EmailAddressModel;
import de.hybris.platform.servicelayer.ServicelayerTransactionalBaseTest;
import de.hybris.platform.servicelayer.model.ModelService;

import javax.annotation.Resource;

import java.util.List;
import org.junit.Before;
import org.junit.Test;


/**
 */
@IntegrationTest
public class DefaultEmailAddressDaoTest extends ServicelayerTransactionalBaseTest
{
	@Resource
	private ModelService modelService;

	@Resource
	private EmailAddressDao emailAddressDao;

	private EmailAddressModel emailId1;
	private EmailAddressModel emailId2;

	@Before
	public void setUp()
	{
		emailId1 = modelService.create(EmailAddressModel.class);
		emailId1.setEmailAddress("1@1.com");
		emailId1.setDisplayName(" ");
		modelService.save(emailId1);
		emailId2 = modelService.create(EmailAddressModel.class);
		emailId2.setEmailAddress("2@2.com");
		emailId2.setDisplayName("2 2");
		modelService.save(emailId2);
	}

	@Test
	public void testFindEmailAddressByEmailIdWithEmptyDisplay()
	{
		final EmailAddressModel email = emailAddressDao.findEmailAddressByEmailAndDisplayName("1@1.com", " ");
		assertNotNull(email);
		assertEquals(email, emailId1);
	}

	@Test
	public void testFindEmailAddressByEmailIdWithNullDisplay()
	{
		final EmailAddressModel email = emailAddressDao.findEmailAddressByEmailAndDisplayName("1@1.com", null);
		assertNull(email);
	}

	@Test
	public void testFindEmailAddressByEmailIdName()
	{
		final EmailAddressModel email = emailAddressDao.findEmailAddressByEmailAndDisplayName("2@2.com", "2 2");
		assertNotNull(email);
		assertEquals(email, emailId2);
	}

	@Test
	public void testFindEmailAddressByEmailNull()
	{
		final EmailAddressModel email = emailAddressDao.findEmailAddressByEmailAndDisplayName("3@3.com", null);
		assertNull(email);
	}

	@Test
	public void getAllEmailAddress()
	{
		final List<EmailAddressModel> emailAddress = emailAddressDao.getAllEmailAddress();
		assertNotNull(emailAddress);
		assertEquals(2, emailAddress.size());
		assertEquals("1@1.com", emailAddress.get(0).getEmailAddress());
	}

}
