/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 13 févr. 2025, 16:27:41
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
 * Representation of information of a guest user
 */
@ApiModel(value="SAPGuestUserRequest", description="Representation of information of a guest user")
public  class SAPGuestUserRequestWsDTO  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** Email of the guest user. It will be used during the checkout process.<br/><br/><i>Generated property</i> for <code>SAPGuestUserRequestWsDTO.email</code> property defined at extension <code>commercewebservicescommons</code>. */
@ApiModelProperty(name="email", value="Email of the guest user. It will be used during the checkout process.", required=true, example="xxx.yy@zz.com") 	
	private String email;
	
	public SAPGuestUserRequestWsDTO()
	{
		// default constructor
	}
	
	public void setEmail(final String email)
	{
		this.email = email;
	}

	public String getEmail() 
	{
		return email;
	}
	

}