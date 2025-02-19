/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 13 févr. 2025, 16:27:39
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.core.servicelayer.user;

import java.io.Serializable;
import de.hybris.platform.core.model.user.SAPUserVerificationTokenModel;


import java.util.Objects;
/**
 * Representation of VerificationToken with not encoded tokenId and code.
 */
public  class UserVerificationTokenData  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** Not hashed value of the token id. Value is available only on user verification token creation.<br/><br/><i>Generated property</i> for <code>UserVerificationTokenData.tokenId</code> property defined at extension <code>core</code>. */
	
	private String tokenId;

	/** Not encoded value of the token code. Value is available only on user verification token creation.<br/><br/><i>Generated property</i> for <code>UserVerificationTokenData.tokenCode</code> property defined at extension <code>core</code>. */
	
	private String tokenCode;

	/** The verification token data.<br/><br/><i>Generated property</i> for <code>UserVerificationTokenData.token</code> property defined at extension <code>core</code>. */
	
	private SAPUserVerificationTokenModel token;
	
	public UserVerificationTokenData()
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
	
	public void setTokenCode(final String tokenCode)
	{
		this.tokenCode = tokenCode;
	}

	public String getTokenCode() 
	{
		return tokenCode;
	}
	
	public void setToken(final SAPUserVerificationTokenModel token)
	{
		this.token = token;
	}

	public SAPUserVerificationTokenModel getToken() 
	{
		return token;
	}
	

}