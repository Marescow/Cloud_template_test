/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 13 févr. 2025, 16:27:35
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.commercefacades.verificationtoken.data;

import java.io.Serializable;


import java.util.Objects;
public  class CreateVerificationTokenInputData  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** <i>Generated property</i> for <code>CreateVerificationTokenInputData.username</code> property defined at extension <code>commercefacades</code>. */
	
	private String username;

	/** <i>Generated property</i> for <code>CreateVerificationTokenInputData.loginId</code> property defined at extension <code>commercefacades</code>. */
	
	private String loginId;

	/** <i>Generated property</i> for <code>CreateVerificationTokenInputData.purpose</code> property defined at extension <code>commercefacades</code>. */
	
	private String purpose;

	/** <i>Generated property</i> for <code>CreateVerificationTokenInputData.password</code> property defined at extension <code>commercefacades</code>. */
	
	private String password;
	
	public CreateVerificationTokenInputData()
	{
		// default constructor
	}
	
	public void setUsername(final String username)
	{
		this.username = username;
	}

	public String getUsername() 
	{
		return username;
	}
	
	public void setLoginId(final String loginId)
	{
		this.loginId = loginId;
	}

	public String getLoginId() 
	{
		return loginId;
	}
	
	public void setPurpose(final String purpose)
	{
		this.purpose = purpose;
	}

	public String getPurpose() 
	{
		return purpose;
	}
	
	public void setPassword(final String password)
	{
		this.password = password;
	}

	public String getPassword() 
	{
		return password;
	}
	

}