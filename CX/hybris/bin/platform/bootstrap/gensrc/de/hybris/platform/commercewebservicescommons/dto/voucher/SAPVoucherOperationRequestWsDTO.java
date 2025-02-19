/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 13 févr. 2025, 16:27:36
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.commercewebservicescommons.dto.voucher;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


import java.util.Objects;
/**
 * Voucher operation request.
 */
@ApiModel(value="SAPVoucherOperationRequest", description="Voucher operation request.")
public  class SAPVoucherOperationRequestWsDTO  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** Voucher code. A valid voucher code can be used to get a discount.<br/><br/><i>Generated property</i> for <code>SAPVoucherOperationRequestWsDTO.voucherCode</code> property defined at extension <code>commercewebservicescommons</code>. */
@ApiModelProperty(name="voucherCode", value="Voucher code. A valid voucher code can be used to get a discount.", required=true, example="WINTER16") 	
	private String voucherCode;
	
	public SAPVoucherOperationRequestWsDTO()
	{
		// default constructor
	}
	
	public void setVoucherCode(final String voucherCode)
	{
		this.voucherCode = voucherCode;
	}

	public String getVoucherCode() 
	{
		return voucherCode;
	}
	

}