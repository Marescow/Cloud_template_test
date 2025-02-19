/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 13 févr. 2025, 16:27:37
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.commercewebservicescommons.dto.order;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


import java.util.Objects;
/**
 * Representation of information of a voucher
 */
@ApiModel(value="SAPVoucherRequest", description="Representation of information of a voucher")
public  class SAPVoucherRequestWsDTO  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** Voucher identifier (code).<br/><br/><i>Generated property</i> for <code>SAPVoucherRequestWsDTO.voucherId</code> property defined at extension <code>commercewebservicescommons</code>. */
@ApiModelProperty(name="voucherId", value="Voucher identifier (code).", required=true, example="VCHR-H8BC-Y3D5-34AL") 	
	private String voucherId;
	
	public SAPVoucherRequestWsDTO()
	{
		// default constructor
	}
	
	public void setVoucherId(final String voucherId)
	{
		this.voucherId = voucherId;
	}

	public String getVoucherId() 
	{
		return voucherId;
	}
	

}