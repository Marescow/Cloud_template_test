/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 13 févr. 2025, 16:27:36
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.commercewebservicescommons.dto.verificationtoken;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


import java.util.Objects;
/**
 * Representation of information for obtained verification token.
 */
@ApiModel(value="VerificationToken", description="Representation of information for obtained verification token.")
public  class VerificationTokenWsDTO  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** Unique token ID generated for verification request, which is used for authentication along with the token code.<br/><br/><i>Generated property</i> for <code>VerificationTokenWsDTO.tokenId</code> property defined at extension <code>commercewebservicescommons</code>. */
@ApiModelProperty(name="tokenId", value="Unique token ID generated for verification request, which is used for authentication along with the token code.", required=true, example="<LGN[nZbnrnhMWy2uBbzKWU/SQRVBZ7mJaiXX9/87PegvovM=]>") 	
	private String tokenId;

	/** Verification token expiration time in seconds.<br/><br/><i>Generated property</i> for <code>VerificationTokenWsDTO.expiresIn</code> property defined at extension <code>commercewebservicescommons</code>. */
@ApiModelProperty(name="expiresIn", value="Verification token expiration time in seconds.", required=true, example="300") 	
	private Integer expiresIn;
	
	public VerificationTokenWsDTO()
	{
		// default constructor
	}
	
	public void setTokenId(final String tokenId)
	{
		this.tokenId = tokenId;
	}

	public String getTokenId() 
	{
		return tokenId;
	}
	
	public void setExpiresIn(final Integer expiresIn)
	{
		this.expiresIn = expiresIn;
	}

	public Integer getExpiresIn() 
	{
		return expiresIn;
	}
	

}