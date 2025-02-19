/*
 * [y] hybris Platform
 *
 * Copyright (c) 2018 SAP SE or an SAP affiliate company.  All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package de.hybris.platform.couponfacades.converters.populator;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.commercefacades.voucher.data.VoucherData;
import de.hybris.platform.couponservices.model.AbstractCouponModel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


/**
 * Junit Test Suite for {@link CouponModelToVoucherDataPopulator }
 */
@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class CouponModelToVoucherDataPopulatorTest
{
	private final static String COUPON_CODE = "couponCode";
	private final static String COUPON_NAME = "couponName";

	@Mock
	private AbstractCouponModel couponModel;

	@InjectMocks
	private CouponModelToVoucherDataPopulator couponModelToVoucherDataPopulator;

	@Test(expected = IllegalArgumentException.class)
	public void testPopulateSourceNull()
	{
		final VoucherData voucherData = new VoucherData();
		couponModelToVoucherDataPopulator.populate(null, voucherData);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPopulateTargetNull()
	{
		final AbstractCouponModel couponModel = new AbstractCouponModel();
		couponModelToVoucherDataPopulator.populate(couponModel, null);
	}

	@Test
	public void testPopulate()
	{

		final VoucherData voucherData = new VoucherData();
		given(couponModel.getCouponId()).willReturn(COUPON_CODE);
		given(couponModel.getName()).willReturn(COUPON_NAME);

		couponModelToVoucherDataPopulator.populate(couponModel, voucherData);

		assertEquals(COUPON_CODE, voucherData.getCode());
		assertEquals(COUPON_NAME, voucherData.getName());
		assertEquals(COUPON_NAME, voucherData.getDescription());
	}
}
