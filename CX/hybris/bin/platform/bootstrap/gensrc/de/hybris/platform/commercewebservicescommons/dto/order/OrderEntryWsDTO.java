/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 13 févr. 2025, 16:27:40
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.commercewebservicescommons.dto.order;

import java.io.Serializable;
import de.hybris.platform.commercewebservicescommons.dto.comments.CommentWsDTO;
import de.hybris.platform.commercewebservicescommons.dto.order.ConfigurationInfoWsDTO;
import de.hybris.platform.commercewebservicescommons.dto.order.DeliveryModeWsDTO;
import de.hybris.platform.commercewebservicescommons.dto.order.StatusSummaryWsDTO;
import de.hybris.platform.commercewebservicescommons.dto.product.PriceWsDTO;
import de.hybris.platform.commercewebservicescommons.dto.product.ProductWsDTO;
import de.hybris.platform.commercewebservicescommons.dto.store.PointOfServiceWsDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;


import java.util.Objects;
/**
 * Representation of an Order entry
 */
@ApiModel(value="OrderEntry", description="Representation of an Order entry")
public  class OrderEntryWsDTO  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** Entry number of the order entry<br/><br/><i>Generated property</i> for <code>OrderEntryWsDTO.entryNumber</code> property defined at extension <code>commercewebservicescommons</code>. */
@ApiModelProperty(name="entryNumber", value="Entry number of the order entry") 	
	private Integer entryNumber;

	/** Quantity number of items in order entry<br/><br/><i>Generated property</i> for <code>OrderEntryWsDTO.quantity</code> property defined at extension <code>commercewebservicescommons</code>. */
@ApiModelProperty(name="quantity", value="Quantity number of items in order entry") 	
	private Long quantity;

	/** Base price of order entry item<br/><br/><i>Generated property</i> for <code>OrderEntryWsDTO.basePrice</code> property defined at extension <code>commercewebservicescommons</code>. */
@ApiModelProperty(name="basePrice", value="Base price of order entry item") 	
	private PriceWsDTO basePrice;

	/** Total price of order entry item<br/><br/><i>Generated property</i> for <code>OrderEntryWsDTO.totalPrice</code> property defined at extension <code>commercewebservicescommons</code>. */
@ApiModelProperty(name="totalPrice", value="Total price of order entry item") 	
	private PriceWsDTO totalPrice;

	/** Product details of order entry<br/><br/><i>Generated property</i> for <code>OrderEntryWsDTO.product</code> property defined at extension <code>commercewebservicescommons</code>. */
@ApiModelProperty(name="product", value="Product details of order entry") 	
	private ProductWsDTO product;

	/** Flag defining if order entry item is updateable<br/><br/><i>Generated property</i> for <code>OrderEntryWsDTO.updateable</code> property defined at extension <code>commercewebservicescommons</code>. */
@ApiModelProperty(name="updateable", value="Flag defining if order entry item is updateable") 	
	private Boolean updateable;

	/** Delivery mode<br/><br/><i>Generated property</i> for <code>OrderEntryWsDTO.deliveryMode</code> property defined at extension <code>commercewebservicescommons</code>. */
@ApiModelProperty(name="deliveryMode", value="Delivery mode") 	
	private DeliveryModeWsDTO deliveryMode;

	/** Configuration info of order entry<br/><br/><i>Generated property</i> for <code>OrderEntryWsDTO.configurationInfos</code> property defined at extension <code>commercewebservicescommons</code>. */
@ApiModelProperty(name="configurationInfos", value="Configuration info of order entry") 	
	private List<ConfigurationInfoWsDTO> configurationInfos;

	/** List of aggregated status information per entry, relevant if the entry is configurable and its configuration contains one or many issues in different severities. Note that configurators typically raise such issues only in case the parent document is changeable. 
            In this case the issues (depending on their severity) need to be fixed before a checkout can be done. This means this segment can be present for a cart entry, for order entries it will always be empty<br/><br/><i>Generated property</i> for <code>OrderEntryWsDTO.statusSummaryList</code> property defined at extension <code>commercewebservicescommons</code>. */
@ApiModelProperty(name="statusSummaryList", value="List of aggregated status information per entry, relevant if the entry is configurable and its configuration contains one or many issues in different severities. Note that configurators typically raise such issues only in case the parent document is changeable. In this case the issues (depending on their severity) need to be fixed before a checkout can be done. This means this segment can be present for a cart entry, for order entries it will always be empty") 	
	private List<StatusSummaryWsDTO> statusSummaryList;

	/** Point of service associated with order entry<br/><br/><i>Generated property</i> for <code>OrderEntryWsDTO.deliveryPointOfService</code> property defined at extension <code>commercewebservicescommons</code>. */
@ApiModelProperty(name="deliveryPointOfService", value="Point of service associated with order entry") 	
	private PointOfServiceWsDTO deliveryPointOfService;

	/** Total price of cancelled items which belong to the order entry item<br/><br/><i>Generated property</i> for <code>OrderEntryWsDTO.cancelledItemsPrice</code> property defined at extension <code>commercewebservicescommons</code>. */
@ApiModelProperty(name="cancelledItemsPrice", value="Total price of cancelled items which belong to the order entry item") 	
	private PriceWsDTO cancelledItemsPrice;

	/** Quantity number of cancellable items in order entry<br/><br/><i>Generated property</i> for <code>OrderEntryWsDTO.cancellableQuantity</code> property defined at extension <code>commercewebservicescommons</code>. */
@ApiModelProperty(name="cancellableQuantity", value="Quantity number of cancellable items in order entry", example="5") 	
	private Long cancellableQuantity;

	/** Total price of returned items which belong to the order entry item<br/><br/><i>Generated property</i> for <code>OrderEntryWsDTO.returnedItemsPrice</code> property defined at extension <code>commercewebservicescommons</code>. */
@ApiModelProperty(name="returnedItemsPrice", value="Total price of returned items which belong to the order entry item") 	
	private PriceWsDTO returnedItemsPrice;

	/** Quantity number of returnable items in order entry<br/><br/><i>Generated property</i> for <code>OrderEntryWsDTO.returnableQuantity</code> property defined at extension <code>commercewebservicescommons</code>. */
@ApiModelProperty(name="returnableQuantity", value="Quantity number of returnable items in order entry", example="5") 	
	private Long returnableQuantity;

	/** List of order entry comments.<br/><br/><i>Generated property</i> for <code>OrderEntryWsDTO.comments</code> property defined at extension <code>b2bwebservicescommons</code>. */
@ApiModelProperty(name="comments", value="List of order entry comments.") 	
	private List<CommentWsDTO> comments;

	/** <i>Generated property</i> for <code>OrderEntryWsDTO.url</code> property defined at extension <code>ordermanagementwebservices</code>. */
@ApiModelProperty(name="url") 	
	private String url;

	/** <i>Generated property</i> for <code>OrderEntryWsDTO.quantityAllocated</code> property defined at extension <code>warehousingwebservices</code>. */
@ApiModelProperty(name="quantityAllocated") 	
	private Long quantityAllocated;

	/** <i>Generated property</i> for <code>OrderEntryWsDTO.quantityUnallocated</code> property defined at extension <code>warehousingwebservices</code>. */
@ApiModelProperty(name="quantityUnallocated") 	
	private Long quantityUnallocated;

	/** <i>Generated property</i> for <code>OrderEntryWsDTO.quantityCancelled</code> property defined at extension <code>warehousingwebservices</code>. */
@ApiModelProperty(name="quantityCancelled") 	
	private Long quantityCancelled;

	/** <i>Generated property</i> for <code>OrderEntryWsDTO.quantityPending</code> property defined at extension <code>warehousingwebservices</code>. */
@ApiModelProperty(name="quantityPending") 	
	private Long quantityPending;

	/** <i>Generated property</i> for <code>OrderEntryWsDTO.quantityShipped</code> property defined at extension <code>warehousingwebservices</code>. */
@ApiModelProperty(name="quantityShipped") 	
	private Long quantityShipped;

	/** <i>Generated property</i> for <code>OrderEntryWsDTO.quantityReturned</code> property defined at extension <code>warehousingwebservices</code>. */
@ApiModelProperty(name="quantityReturned") 	
	private Long quantityReturned;
	
	public OrderEntryWsDTO()
	{
		// default constructor
	}
	
	public void setEntryNumber(final Integer entryNumber)
	{
		this.entryNumber = entryNumber;
	}

	public Integer getEntryNumber() 
	{
		return entryNumber;
	}
	
	public void setQuantity(final Long quantity)
	{
		this.quantity = quantity;
	}

	public Long getQuantity() 
	{
		return quantity;
	}
	
	public void setBasePrice(final PriceWsDTO basePrice)
	{
		this.basePrice = basePrice;
	}

	public PriceWsDTO getBasePrice() 
	{
		return basePrice;
	}
	
	public void setTotalPrice(final PriceWsDTO totalPrice)
	{
		this.totalPrice = totalPrice;
	}

	public PriceWsDTO getTotalPrice() 
	{
		return totalPrice;
	}
	
	public void setProduct(final ProductWsDTO product)
	{
		this.product = product;
	}

	public ProductWsDTO getProduct() 
	{
		return product;
	}
	
	public void setUpdateable(final Boolean updateable)
	{
		this.updateable = updateable;
	}

	public Boolean getUpdateable() 
	{
		return updateable;
	}
	
	public void setDeliveryMode(final DeliveryModeWsDTO deliveryMode)
	{
		this.deliveryMode = deliveryMode;
	}

	public DeliveryModeWsDTO getDeliveryMode() 
	{
		return deliveryMode;
	}
	
	public void setConfigurationInfos(final List<ConfigurationInfoWsDTO> configurationInfos)
	{
		this.configurationInfos = configurationInfos;
	}

	public List<ConfigurationInfoWsDTO> getConfigurationInfos() 
	{
		return configurationInfos;
	}
	
	public void setStatusSummaryList(final List<StatusSummaryWsDTO> statusSummaryList)
	{
		this.statusSummaryList = statusSummaryList;
	}

	public List<StatusSummaryWsDTO> getStatusSummaryList() 
	{
		return statusSummaryList;
	}
	
	public void setDeliveryPointOfService(final PointOfServiceWsDTO deliveryPointOfService)
	{
		this.deliveryPointOfService = deliveryPointOfService;
	}

	public PointOfServiceWsDTO getDeliveryPointOfService() 
	{
		return deliveryPointOfService;
	}
	
	public void setCancelledItemsPrice(final PriceWsDTO cancelledItemsPrice)
	{
		this.cancelledItemsPrice = cancelledItemsPrice;
	}

	public PriceWsDTO getCancelledItemsPrice() 
	{
		return cancelledItemsPrice;
	}
	
	public void setCancellableQuantity(final Long cancellableQuantity)
	{
		this.cancellableQuantity = cancellableQuantity;
	}

	public Long getCancellableQuantity() 
	{
		return cancellableQuantity;
	}
	
	public void setReturnedItemsPrice(final PriceWsDTO returnedItemsPrice)
	{
		this.returnedItemsPrice = returnedItemsPrice;
	}

	public PriceWsDTO getReturnedItemsPrice() 
	{
		return returnedItemsPrice;
	}
	
	public void setReturnableQuantity(final Long returnableQuantity)
	{
		this.returnableQuantity = returnableQuantity;
	}

	public Long getReturnableQuantity() 
	{
		return returnableQuantity;
	}
	
	public void setComments(final List<CommentWsDTO> comments)
	{
		this.comments = comments;
	}

	public List<CommentWsDTO> getComments() 
	{
		return comments;
	}
	
	public void setUrl(final String url)
	{
		this.url = url;
	}

	public String getUrl() 
	{
		return url;
	}
	
	public void setQuantityAllocated(final Long quantityAllocated)
	{
		this.quantityAllocated = quantityAllocated;
	}

	public Long getQuantityAllocated() 
	{
		return quantityAllocated;
	}
	
	public void setQuantityUnallocated(final Long quantityUnallocated)
	{
		this.quantityUnallocated = quantityUnallocated;
	}

	public Long getQuantityUnallocated() 
	{
		return quantityUnallocated;
	}
	
	public void setQuantityCancelled(final Long quantityCancelled)
	{
		this.quantityCancelled = quantityCancelled;
	}

	public Long getQuantityCancelled() 
	{
		return quantityCancelled;
	}
	
	public void setQuantityPending(final Long quantityPending)
	{
		this.quantityPending = quantityPending;
	}

	public Long getQuantityPending() 
	{
		return quantityPending;
	}
	
	public void setQuantityShipped(final Long quantityShipped)
	{
		this.quantityShipped = quantityShipped;
	}

	public Long getQuantityShipped() 
	{
		return quantityShipped;
	}
	
	public void setQuantityReturned(final Long quantityReturned)
	{
		this.quantityReturned = quantityReturned;
	}

	public Long getQuantityReturned() 
	{
		return quantityReturned;
	}
	

}