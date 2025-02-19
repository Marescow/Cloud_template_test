/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 13 févr. 2025, 16:27:41
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.ordermanagementfacades.returns.data;

import java.io.Serializable;
import de.hybris.platform.ordermanagementfacades.returns.data.ReturnEntryModificationWsDTO;
import java.util.List;


import java.util.Objects;
public  class ReturnRequestModificationWsDTO  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** <i>Generated property</i> for <code>ReturnRequestModificationWsDTO.returnEntries</code> property defined at extension <code>ordermanagementwebservices</code>. */
	
	private List<ReturnEntryModificationWsDTO> returnEntries;

	/** <i>Generated property</i> for <code>ReturnRequestModificationWsDTO.refundDeliveryCost</code> property defined at extension <code>ordermanagementwebservices</code>. */
	
	private Boolean refundDeliveryCost;
	
	public ReturnRequestModificationWsDTO()
	{
		// default constructor
	}
	
	public void setReturnEntries(final List<ReturnEntryModificationWsDTO> returnEntries)
	{
		this.returnEntries = returnEntries;
	}

	public List<ReturnEntryModificationWsDTO> getReturnEntries() 
	{
		return returnEntries;
	}
	
	public void setRefundDeliveryCost(final Boolean refundDeliveryCost)
	{
		this.refundDeliveryCost = refundDeliveryCost;
	}

	public Boolean getRefundDeliveryCost() 
	{
		return refundDeliveryCost;
	}
	

}