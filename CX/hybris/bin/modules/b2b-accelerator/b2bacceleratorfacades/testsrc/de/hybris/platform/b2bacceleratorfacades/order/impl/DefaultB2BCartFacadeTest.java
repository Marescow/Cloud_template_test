package de.hybris.platform.b2bacceleratorfacades.order.impl;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.b2bacceleratorfacades.exception.DomainException;
import de.hybris.platform.commercefacades.order.CartFacade;
import de.hybris.platform.commercefacades.order.data.CartData;
import de.hybris.platform.commercefacades.order.data.CartModificationData;
import de.hybris.platform.commercefacades.order.data.OrderEntryData;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.quote.data.QuoteData;
import de.hybris.platform.commerceservices.order.CommerceCartModificationException;
import de.hybris.platform.core.enums.QuoteState;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import de.hybris.platform.util.localization.Localization;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static de.hybris.platform.testframework.Assert.assertEquals;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;

@UnitTest
public class DefaultB2BCartFacadeTest
{
    @InjectMocks
    private DefaultB2BCartFacade b2bCartFacade;

    @Mock
    private CartFacade cartFacade;

    @Mock
    private Converter<AbstractOrderModel, List<CartModificationData>> groupCartModificationListConverter;

    private static Long ADD_5_ITEMS = 5L;
    private static final Long ADD_EXCEEDED_ITEMS = 10000000L;
    private static String PRODUCT_CODE = "HW1210-3422";
    private static int ENTRY_NUMBER_1 = 1;
    private static Integer ENTRY_NUMBER_UNKNOWN = null;
    private static int CALLED_ONCE = 1;
    private static final CartData CART_DATA = new CartData();

    private CartModificationData mockedCartModificationData;

    @Before
    public void setUp()
    {
        MockitoAnnotations.openMocks(this);

        mockedCartModificationData = mockCartModificationData();
        b2bCartFacade.setBuyerUneditableStatusList(List.of(QuoteState.BUYER_SUBMITTED,QuoteState.BUYER_OFFER,QuoteState.SELLER_DRAFT,QuoteState.SELLERAPPROVER_PENDING));
        when(cartFacade.getSessionCart()).thenReturn(CART_DATA);
    }

    @Test
    public void addCartEntryWithoutQuote() throws CommerceCartModificationException
    {
        final OrderEntryData cartEntry = buildEntryDataWithEntryNumber(ENTRY_NUMBER_UNKNOWN);

        when(cartFacade.getSessionCart()).thenReturn(new CartData());

        when(cartFacade.addToCart(anyString(), anyLong())).thenReturn(mockedCartModificationData);

        b2bCartFacade.addOrderEntry(cartEntry);

        verify(cartFacade, times(CALLED_ONCE)).addToCart(anyString(), anyLong());
    }

    @Test
    public void addCartEntryWithQuoteInDraft() throws CommerceCartModificationException
    {
        final OrderEntryData cartEntry = buildEntryDataWithEntryNumber(ENTRY_NUMBER_UNKNOWN);

        mockSessionCartWithQuoteInStatus(QuoteState.BUYER_DRAFT);

        when(cartFacade.addToCart(anyString(), anyLong())).thenReturn(mockedCartModificationData);

        b2bCartFacade.addOrderEntry(cartEntry);

        verify(cartFacade, times(CALLED_ONCE)).addToCart(anyString(), anyLong());
    }

    @Test(expected = DomainException.class)
    public void addCartEntryWithQuoteApproved() throws CommerceCartModificationException
    {
        final OrderEntryData cartEntry = buildEntryDataWithEntryNumber(ENTRY_NUMBER_UNKNOWN);

        mockSessionCartWithQuoteInStatus(QuoteState.BUYER_OFFER);

        b2bCartFacade.addOrderEntry(cartEntry);
    }

    @Test
    public void addOrderEntryFailWithExceededQuantity() throws CommerceCartModificationException
    {
        final OrderEntryData cartEntry = buildEntryDataWithEntryNumberAndExceededQuantity(ENTRY_NUMBER_UNKNOWN);
        CartModificationData resultCartNodificationData = null;
        when(cartFacade.getSessionCart()).thenReturn(new CartData());
        when(cartFacade.addToCart(anyString(), anyLong())).thenReturn(newCartModificationData(1));
        try (MockedStatic<Localization> localizationClass = mockStatic(Localization.class))
        {
            localizationClass.when(() -> Localization.getLocalizedString("basket.information.quantity.reducedNumberOfItemsAdded.lowStock", new Object[]
                    { "testProduct" })).thenReturn("Correct StatusMessage");
            resultCartNodificationData = b2bCartFacade.addOrderEntry(cartEntry);
        }

        verify(cartFacade,times(1)).addToCart(anyString(), anyLong());
        assertEquals("Correct StatusMessage",resultCartNodificationData.getStatusMessage());
    }

    @Test
    public void addOrderEntryFailWithNoItemAdded() throws CommerceCartModificationException
    {
        final OrderEntryData cartEntry = buildEntryDataWithEntryNumberAndExceededQuantity(ENTRY_NUMBER_UNKNOWN);
        CartModificationData resultCartNodificationData = null;
        when(cartFacade.getSessionCart()).thenReturn(new CartData());
        when(cartFacade.addToCart(anyString(), anyLong())).thenReturn(newCartModificationData(0));
        try (MockedStatic<Localization> localizationClass = mockStatic(Localization.class))
        {
            localizationClass.when(() -> Localization.getLocalizedString("basket.information.quantity.noItemsAdded.lowStock", new Object[]
                    { "testProduct" })).thenReturn("Correct StatusMessage");
            resultCartNodificationData = b2bCartFacade.addOrderEntry(cartEntry);
        }

        verify(cartFacade,times(1)).addToCart(anyString(), anyLong());
        assertEquals("Correct StatusMessage",resultCartNodificationData.getStatusMessage());
    }

    @Test
    public void updateCartEntryWithoutQuote() throws CommerceCartModificationException
    {
        final OrderEntryData cartEntry = buildEntryDataWithEntryNumber(ENTRY_NUMBER_1);

        when(cartFacade.getSessionCart()).thenReturn(new CartData());

        when(cartFacade.updateCartEntry(cartEntry.getEntryNumber(), cartEntry.getQuantity())).thenReturn(mockedCartModificationData);

        b2bCartFacade.updateOrderEntry(cartEntry);

        verify(cartFacade, times(CALLED_ONCE)).updateCartEntry(cartEntry.getEntryNumber(), cartEntry.getQuantity());
    }

    @Test
    public void updateCartEntryWithQuoteInDraft() throws CommerceCartModificationException
    {
        final OrderEntryData cartEntry = buildEntryDataWithEntryNumber(ENTRY_NUMBER_1);

        mockSessionCartWithQuoteInStatus(QuoteState.BUYER_DRAFT);

        when(cartFacade.updateCartEntry(cartEntry.getEntryNumber(), cartEntry.getQuantity())).thenReturn(mockedCartModificationData);

        b2bCartFacade.updateOrderEntry(cartEntry);

        verify(cartFacade, times(CALLED_ONCE)).updateCartEntry(cartEntry.getEntryNumber(), cartEntry.getQuantity());
    }

    @Test(expected = DomainException.class)
    public void updateCartEntryWithQuoteApproved() throws CommerceCartModificationException
    {
        final OrderEntryData cartEntry = buildEntryDataWithEntryNumber(ENTRY_NUMBER_1);

        mockSessionCartWithQuoteInStatus(QuoteState.BUYER_OFFER);

        b2bCartFacade.updateOrderEntry(cartEntry);
    }

    @Test
    public void deleteCartEntryWithoutQuote() throws CommerceCartModificationException
    {
        final OrderEntryData groupCartEntries = new OrderEntryData();
        groupCartEntries.setEntries(Collections.EMPTY_LIST);

        when(cartFacade.getSessionCart()).thenReturn(new CartData());

        when(groupCartModificationListConverter.convert(any(), anyList())).thenReturn(null);

        b2bCartFacade.deleteGroupedOrderEntries(groupCartEntries);

        verify(groupCartModificationListConverter, times(CALLED_ONCE)).convert(any(), anyList());
    }

    @Test
    public void deleteCartEntryWithQuoteInDraft() throws CommerceCartModificationException
    {
        final OrderEntryData groupCartEntries = new OrderEntryData();
        groupCartEntries.setEntries(Collections.EMPTY_LIST);

        mockSessionCartWithQuoteInStatus(QuoteState.BUYER_DRAFT);

        when(groupCartModificationListConverter.convert(any(), anyList())).thenReturn(null);

        b2bCartFacade.deleteGroupedOrderEntries(groupCartEntries);

        verify(groupCartModificationListConverter, times(CALLED_ONCE)).convert(any(), anyList());
    }

    @Test(expected = DomainException.class)
    public void deleteCartEntryWithQuoteApproved() throws CommerceCartModificationException
    {
        final OrderEntryData groupCartEntries = new OrderEntryData();
        groupCartEntries.setEntries(Collections.EMPTY_LIST);

        mockSessionCartWithQuoteInStatus(QuoteState.BUYER_OFFER);

        b2bCartFacade.deleteGroupedOrderEntries(groupCartEntries);
    }

    protected OrderEntryData buildEntryDataWithEntryNumber(final Integer entryNumber)
    {
        final OrderEntryData orderEntry = new OrderEntryData();
        orderEntry.setQuantity(ADD_5_ITEMS);
        orderEntry.setProduct(new ProductData());
        orderEntry.getProduct().setCode(PRODUCT_CODE);
        orderEntry.setEntryNumber(entryNumber);

        return orderEntry;
    }

    protected OrderEntryData buildEntryDataWithEntryNumberAndExceededQuantity(final Integer entryNumber)
    {
        final OrderEntryData orderEntry = new OrderEntryData();
        orderEntry.setQuantity(ADD_EXCEEDED_ITEMS);
        orderEntry.setProduct(new ProductData());
        orderEntry.getProduct().setCode(PRODUCT_CODE);
        orderEntry.setEntryNumber(entryNumber);
        return orderEntry;
    }

    protected void mockSessionCartWithQuoteInStatus(final QuoteState quoteState)
    {
        final CartData cartData = mock(CartData.class);
        final QuoteData quoteData = mock(QuoteData.class);

        when(quoteData.getState()).thenReturn(quoteState);
        when(cartData.getQuoteData()).thenReturn(quoteData);
        when(cartFacade.getSessionCart()).thenReturn(cartData);
    }

    protected CartModificationData mockCartModificationData()
    {
        final CartModificationData cartModificationData = mock(CartModificationData.class);
        when(cartModificationData.getQuantityAdded()).thenReturn(ADD_5_ITEMS);
        when(cartModificationData.getQuantity()).thenReturn(ADD_5_ITEMS);
        return cartModificationData;
    }

    protected CartModificationData newCartModificationData(int AddedQuantity)
    {
        final CartModificationData cartModificationData = new CartModificationData();
        final OrderEntryData orderEntryData = new OrderEntryData();
        final ProductData productData = new ProductData();
        productData.setName("testProduct");
        productData.setCode(PRODUCT_CODE);
        orderEntryData.setProduct(productData);
        orderEntryData.setQuantity(ADD_EXCEEDED_ITEMS);
        orderEntryData.setEntryNumber(ENTRY_NUMBER_UNKNOWN);
        cartModificationData.setEntry(orderEntryData);
        cartModificationData.setQuantityAdded(AddedQuantity);
        cartModificationData.setStatusCode("lowStock");
        return cartModificationData;
    }

    @Test(expected = DomainException.class)
    public void addOrderEntryWithQuoteInDraft() throws CommerceCartModificationException
    {
        final OrderEntryData cartEntry = buildEntryDataWithEntryNumber(ENTRY_NUMBER_1);

        mockSessionCartWithQuoteInStatus(QuoteState.SELLER_DRAFT);

        b2bCartFacade.addOrderEntry(cartEntry);
    }
}
