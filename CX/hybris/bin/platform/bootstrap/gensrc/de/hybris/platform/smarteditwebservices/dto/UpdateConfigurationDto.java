/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 13 févr. 2025, 16:27:40
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.smarteditwebservices.dto;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


import java.util.Objects;
/**
 * Configuration data used for update
 */
@ApiModel(value="configurationData", description="Configuration data used for update")
public  class UpdateConfigurationDto  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** The uid of the configuration data<br/><br/><i>Generated property</i> for <code>UpdateConfigurationDto.uid</code> property defined at extension <code>smarteditwebservices</code>. */
@ApiModelProperty(name="uid", value="The uid of the configuration data", required=true) 	
	private String uid;

	/** The configuration data key<br/><br/><i>Generated property</i> for <code>UpdateConfigurationDto.key</code> property defined at extension <code>smarteditwebservices</code>. */
@ApiModelProperty(name="key", value="The configuration data key", required=true) 	
	private String key;

	/** The configuration data value<br/><br/><i>Generated property</i> for <code>UpdateConfigurationDto.value</code> property defined at extension <code>smarteditwebservices</code>. */
@ApiModelProperty(name="value", value="The configuration data value", required=true) 	
	private String value;
	
	public UpdateConfigurationDto()
	{
		// default constructor
	}
	
	public void setUid(final String uid)
	{
		this.uid = uid;
	}

	public String getUid() 
	{
		return uid;
	}
	
	public void setKey(final String key)
	{
		this.key = key;
	}

	public String getKey() 
	{
		return key;
	}
	
	public void setValue(final String value)
	{
		this.value = value;
	}

	public String getValue() 
	{
		return value;
	}
	

}