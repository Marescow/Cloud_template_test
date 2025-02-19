/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 13 févr. 2025, 16:27:36
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.commercewebservicescommons.dto.user;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


import java.util.Objects;
/**
 * Representation of information for sending a token to restore a forgotten password.
 */
@ApiModel(value="PasswordRestoreTokenInput", description="Representation of information for sending a token to restore a forgotten password.")
public  class PasswordRestoreTokenInputWsDTO  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** User login identifier. Target to receive the token.<br/><br/><i>Generated property</i> for <code>PasswordRestoreTokenInputWsDTO.loginId</code> property defined at extension <code>commercewebservicescommons</code>. */
@ApiModelProperty(name="loginId", value="User login identifier. Target to receive the token.", required=true, example="keenreviewer@sap.com") 	
	private String loginId;
	
	public PasswordRestoreTokenInputWsDTO()
	{
		// default constructor
	}
	
	public void setLoginId(final String loginId)
	{
		this.loginId = loginId;
	}

	public String getLoginId() 
	{
		return loginId;
	}
	

}