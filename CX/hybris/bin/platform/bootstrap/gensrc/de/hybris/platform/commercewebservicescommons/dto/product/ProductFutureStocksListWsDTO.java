/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 13 févr. 2025, 16:27:32
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.commercewebservicescommons.dto.product;

import java.io.Serializable;
import de.hybris.platform.commercewebservicescommons.dto.product.ProductFutureStocksWsDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;


import java.util.Objects;
/**
 * Representation of a Product Future Stocks List
 */
@ApiModel(value="ProductFutureStocksList", description="Representation of a Product Future Stocks List")
public  class ProductFutureStocksListWsDTO  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** List of product future stocks<br/><br/><i>Generated property</i> for <code>ProductFutureStocksListWsDTO.productFutureStocks</code> property defined at extension <code>commercewebservicescommons</code>. */
@ApiModelProperty(name="productFutureStocks", value="List of product future stocks") 	
	private List<ProductFutureStocksWsDTO> productFutureStocks;
	
	public ProductFutureStocksListWsDTO()
	{
		// default constructor
	}
	
	public void setProductFutureStocks(final List<ProductFutureStocksWsDTO> productFutureStocks)
	{
		this.productFutureStocks = productFutureStocks;
	}

	public List<ProductFutureStocksWsDTO> getProductFutureStocks() 
	{
		return productFutureStocks;
	}
	

}