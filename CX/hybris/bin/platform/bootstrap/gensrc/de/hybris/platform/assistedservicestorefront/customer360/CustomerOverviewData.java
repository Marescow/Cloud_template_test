/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 13 févr. 2025, 16:27:38
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.assistedservicestorefront.customer360;

import java.io.Serializable;
import de.hybris.platform.commercefacades.product.data.ImageData;
import de.hybris.platform.commercefacades.user.data.AddressData;
import java.util.Date;


import java.util.Objects;
public  class CustomerOverviewData  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** <i>Generated property</i> for <code>CustomerOverviewData.fullName</code> property defined at extension <code>assistedservicestorefront</code>. */
	
	private String fullName;

	/** <i>Generated property</i> for <code>CustomerOverviewData.email</code> property defined at extension <code>assistedservicestorefront</code>. */
	
	private String email;

	/** <i>Generated property</i> for <code>CustomerOverviewData.profilePicture</code> property defined at extension <code>assistedservicestorefront</code>. */
	
	private ImageData profilePicture;

	/** <i>Generated property</i> for <code>CustomerOverviewData.address</code> property defined at extension <code>assistedservicestorefront</code>. */
	
	private AddressData address;

	/** <i>Generated property</i> for <code>CustomerOverviewData.signedUp</code> property defined at extension <code>assistedservicestorefront</code>. */
	
	private Date signedUp;
	
	public CustomerOverviewData()
	{
		// default constructor
	}
	
	public void setFullName(final String fullName)
	{
		this.fullName = fullName;
	}

	public String getFullName() 
	{
		return fullName;
	}
	
	public void setEmail(final String email)
	{
		this.email = email;
	}

	public String getEmail() 
	{
		return email;
	}
	
	public void setProfilePicture(final ImageData profilePicture)
	{
		this.profilePicture = profilePicture;
	}

	public ImageData getProfilePicture() 
	{
		return profilePicture;
	}
	
	public void setAddress(final AddressData address)
	{
		this.address = address;
	}

	public AddressData getAddress() 
	{
		return address;
	}
	
	public void setSignedUp(final Date signedUp)
	{
		this.signedUp = signedUp;
	}

	public Date getSignedUp() 
	{
		return signedUp;
	}
	

}