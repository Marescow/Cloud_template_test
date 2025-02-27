/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 13 févr. 2025, 16:27:38
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.commercewebservicescommons.dto.voucher;

import java.io.Serializable;
import de.hybris.platform.commercewebservicescommons.dto.voucher.VoucherWsDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;


import java.util.Objects;
/**
 * Representation of a Voucher List
 */
@ApiModel(value="VoucherList", description="Representation of a Voucher List")
public  class VoucherListWsDTO  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** List of vouchers<br/><br/><i>Generated property</i> for <code>VoucherListWsDTO.vouchers</code> property defined at extension <code>commercewebservicescommons</code>. */
@ApiModelProperty(name="vouchers", value="List of vouchers") 	
	private List<VoucherWsDTO> vouchers;
	
	public VoucherListWsDTO()
	{
		// default constructor
	}
	
	public void setVouchers(final List<VoucherWsDTO> vouchers)
	{
		this.vouchers = vouchers;
	}

	public List<VoucherWsDTO> getVouchers() 
	{
		return vouchers;
	}
	

}