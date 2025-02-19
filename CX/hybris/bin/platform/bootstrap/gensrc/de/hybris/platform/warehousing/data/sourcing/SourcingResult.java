/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 13 févr. 2025, 16:27:40
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.warehousing.data.sourcing;

import java.io.Serializable;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import java.util.Map;


import java.util.Objects;
public  class SourcingResult  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** <i>Generated property</i> for <code>SourcingResult.allocation</code> property defined at extension <code>warehousing</code>. */
	
	private Map<AbstractOrderEntryModel, Long> allocation;

	/** <i>Generated property</i> for <code>SourcingResult.warehouse</code> property defined at extension <code>warehousing</code>. */
	
	private WarehouseModel warehouse;
	
	public SourcingResult()
	{
		// default constructor
	}
	
	public void setAllocation(final Map<AbstractOrderEntryModel, Long> allocation)
	{
		this.allocation = allocation;
	}

	public Map<AbstractOrderEntryModel, Long> getAllocation() 
	{
		return allocation;
	}
	
	public void setWarehouse(final WarehouseModel warehouse)
	{
		this.warehouse = warehouse;
	}

	public WarehouseModel getWarehouse() 
	{
		return warehouse;
	}
	

}