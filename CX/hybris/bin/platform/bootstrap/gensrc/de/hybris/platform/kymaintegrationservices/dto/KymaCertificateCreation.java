/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 13 févr. 2025, 16:27:36
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.kymaintegrationservices.dto;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonProperty;


import java.util.Objects;
public  class KymaCertificateCreation  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** <i>Generated property</i> for <code>KymaCertificateCreation.subject</code> property defined at extension <code>kymaintegrationservices</code>. */
@JsonProperty("subject") 	
	private String subject;

	/** <i>Generated property</i> for <code>KymaCertificateCreation.extensions</code> property defined at extension <code>kymaintegrationservices</code>. */
@JsonProperty("extensions") 	
	private String extensions;

	/** <i>Generated property</i> for <code>KymaCertificateCreation.keyAlgorithm</code> property defined at extension <code>kymaintegrationservices</code>. */
@JsonProperty("key-algorithm") 	
	private String keyAlgorithm;
	
	public KymaCertificateCreation()
	{
		// default constructor
	}
	
@JsonProperty("subject") 	public void setSubject(final String subject)
	{
		this.subject = subject;
	}

@JsonProperty("subject") 	public String getSubject() 
	{
		return subject;
	}
	
@JsonProperty("extensions") 	public void setExtensions(final String extensions)
	{
		this.extensions = extensions;
	}

@JsonProperty("extensions") 	public String getExtensions() 
	{
		return extensions;
	}
	
@JsonProperty("key-algorithm") 	public void setKeyAlgorithm(final String keyAlgorithm)
	{
		this.keyAlgorithm = keyAlgorithm;
	}

@JsonProperty("key-algorithm") 	public String getKeyAlgorithm() 
	{
		return keyAlgorithm;
	}
	

}