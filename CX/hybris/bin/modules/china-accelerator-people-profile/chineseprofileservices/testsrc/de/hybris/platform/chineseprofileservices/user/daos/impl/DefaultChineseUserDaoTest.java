/*
 * Copyright (c) 2024 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.chineseprofileservices.user.daos.impl;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.EmployeeModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.internal.dao.SortParameters;
import de.hybris.platform.servicelayer.model.ModelService;

import javax.annotation.Resource;

import java.util.Collection;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


@IntegrationTest
public class DefaultChineseUserDaoTest extends ServicelayerTransactionalTest
{
	@Resource
	private DefaultChineseUserDao chineseUserDao;

	@Resource(name = "modelService")
	private ModelService modelService;

	@Before
	public void prepare()
	{
		final CustomerModel customer = new CustomerModel();
		customer.setLoginDisabled(false);
		customer.setUid("test@gmail.com");
		customer.setMobileNumber("12345678910");
		customer.setEncodedPassword("123456123456");
		modelService.save(customer);
	}

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void test_Find_User_By_Email()
	{
		final UserModel user = chineseUserDao.findUserByUID("test@gmail.com");
		assertEquals("123456123456", user.getEncodedPassword());
		assertEquals("test@gmail.com", user.getUid());
	}

	@Test
	public void test_Find_User_By_Mobile()
	{
		final UserModel user = chineseUserDao.findUserByUID("12345678910");
		assertEquals("123456123456", user.getEncodedPassword());
		assertEquals("test@gmail.com", user.getUid());
	}

	@Test
	public void test_Find_Unknown_User()
	{
		final UserModel user = chineseUserDao.findUserByUID("aaa@gmail.com");
		assertNull(user);
	}

	@Test
	public void testFindCustomersByUids()
	{
		final Collection<UserModel> users = chineseUserDao.findUsersByUids(SortParameters.SortOrder.DESCENDING, "test@gmail.com");
		assertNotNull(users);
		assertEquals(1, users.size());
		assertEquals("123456123456", users.iterator().next().getEncodedPassword());
		assertEquals("test@gmail.com", users.iterator().next().getUid());
	}

	@Test
	public void testFindCustomersByMobiles()
	{
		final Collection<UserModel> users = chineseUserDao.findUsersByUids(SortParameters.SortOrder.ASCENDING, "12345678910");
		assertNotNull(users);
		assertEquals(1, users.size());
		assertEquals("123456123456", users.iterator().next().getEncodedPassword());
		assertEquals("test@gmail.com", users.iterator().next().getUid());
		assertEquals("12345678910", ((CustomerModel)users.iterator().next()).getMobileNumber());
	}

	@Test
	public void testFindEmployeesByUids()
	{
		createEmployee();
		final Collection<UserModel> users = chineseUserDao.findUsersByUids(SortParameters.SortOrder.DESCENDING, "testagent");
		assertNotNull(users);
		assertEquals(1, users.size());
		assertEquals("123456123456", users.iterator().next().getEncodedPassword());
		assertEquals("testagent", users.iterator().next().getUid());
	}

	@Test
	public void testFindUsersByNullUids()
	{
		assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> chineseUserDao.findUsersByUids(null, null));
	}

	@Test
	public void testFindUsersByEmptyUids()
	{
		final Collection<UserModel> users = chineseUserDao.findUsersByUids(null);
		assertTrue(users == null || users.isEmpty());
	}

	@Test
	public void testFindUsersByUnknownUids()
	{
		final Collection<UserModel> users = chineseUserDao.findUsersByUids(null, "aaa@gmail.com");
		assertTrue(users == null || users.isEmpty());
	}

	private void createEmployee()
	{
		final EmployeeModel employee = new EmployeeModel();
		employee.setLoginDisabled(false);
		employee.setUid("testagent");
		employee.setEncodedPassword("123456123456");
		modelService.save(employee);
	}
}
