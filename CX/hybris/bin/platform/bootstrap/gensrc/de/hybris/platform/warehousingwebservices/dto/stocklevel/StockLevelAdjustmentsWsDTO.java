/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 13 févr. 2025, 16:27:39
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.warehousingwebservices.dto.stocklevel;

import java.io.Serializable;
import de.hybris.platform.warehousingwebservices.dto.stocklevel.StockLevelAdjustmentWsDTO;
import java.util.List;


import java.util.Objects;
public  class StockLevelAdjustmentsWsDTO  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** <i>Generated property</i> for <code>StockLevelAdjustmentsWsDTO.stockLevelAdjustments</code> property defined at extension <code>warehousingwebservices</code>. */
	
	private List<StockLevelAdjustmentWsDTO> stockLevelAdjustments;
	
	public StockLevelAdjustmentsWsDTO()
	{
		// default constructor
	}
	
	public void setStockLevelAdjustments(final List<StockLevelAdjustmentWsDTO> stockLevelAdjustments)
	{
		this.stockLevelAdjustments = stockLevelAdjustments;
	}

	public List<StockLevelAdjustmentWsDTO> getStockLevelAdjustments() 
	{
		return stockLevelAdjustments;
	}
	

}