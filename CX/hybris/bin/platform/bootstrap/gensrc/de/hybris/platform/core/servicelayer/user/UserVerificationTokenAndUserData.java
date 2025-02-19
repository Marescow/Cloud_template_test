/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 13 févr. 2025, 16:27:36
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.core.servicelayer.user;

import java.io.Serializable;
import de.hybris.platform.core.model.user.SAPUserVerificationTokenModel;
import de.hybris.platform.core.model.user.UserModel;


import java.util.Objects;
/**
 * Representation of VerificationToken with the matching User model.
 */
public  class UserVerificationTokenAndUserData  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** The matching verification token for the user.<br/><br/><i>Generated property</i> for <code>UserVerificationTokenAndUserData.token</code> property defined at extension <code>core</code>. */
	
	private SAPUserVerificationTokenModel token;

	/** The user.<br/><br/><i>Generated property</i> for <code>UserVerificationTokenAndUserData.user</code> property defined at extension <code>core</code>. */
	
	private UserModel user;
	
	public UserVerificationTokenAndUserData()
	{
		// default constructor
	}
	
	public void setToken(final SAPUserVerificationTokenModel token)
	{
		this.token = token;
	}

	public SAPUserVerificationTokenModel getToken() 
	{
		return token;
	}
	
	public void setUser(final UserModel user)
	{
		this.user = user;
	}

	public UserModel getUser() 
	{
		return user;
	}
	

}