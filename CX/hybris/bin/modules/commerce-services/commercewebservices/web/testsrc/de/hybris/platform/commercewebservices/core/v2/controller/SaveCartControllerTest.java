package de.hybris.platform.commercewebservices.core.v2.controller;


import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.commercefacades.order.SaveCartFacade;
import de.hybris.platform.commerceservices.order.CommerceSaveCartException;
import de.hybris.platform.commercewebservicescommons.dto.order.SAPSavedCartRequestWsDTO;
import de.hybris.platform.webservicescommons.mapping.DataMapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;


@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class SaveCartControllerTest
{
	@InjectMocks
	private SaveCartController saveCartController;
	@Mock
	private SaveCartFacade saveCartFacade;
	@Mock
	private DataMapper dataMapper;

	@Test
	public void testSaveCart() throws CommerceSaveCartException
	{
		String cartId = "cartId";
		SAPSavedCartRequestWsDTO savedCart = new SAPSavedCartRequestWsDTO();
		savedCart.setName("mycart");
		savedCart.setDescription("mycart description");
		saveCartController.doCartSave(cartId, savedCart, "cartId");
		verify(saveCartFacade).saveCart(any());
	}

	@Test
	public void testCopySavedCart() throws CommerceSaveCartException
	{
		String cartId = "cartId";
		SAPSavedCartRequestWsDTO savedCart = new SAPSavedCartRequestWsDTO();
		savedCart.setName("mycart");
		savedCart.setDescription("mycart description");
		saveCartController.copySavedCart(cartId, savedCart, "cartId");
		verify(saveCartFacade).cloneSavedCart(any());
	}
}
