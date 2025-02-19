/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 13 févr. 2025, 16:27:41
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.permissionswebservices.dto;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


import java.util.Objects;
/**
 * Type with CRUD permission assignment values.
 */
@ApiModel(value="TypePermissions", description="Type with CRUD permission assignment values.")
public  class TypePermissionsWsDTO  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** A Type specifying which type the permission assignment values are for.<br/><br/><i>Generated property</i> for <code>TypePermissionsWsDTO.type</code> property defined at extension <code>permissionswebservices</code>. */
@ApiModelProperty(name="type", value="A Type specifying which type the permission assignment values are for.", required=true) 	
	private String type;

	/** Type with CRUD permission assignment values.<br/><br/><i>Generated property</i> for <code>TypePermissionsWsDTO.permissions</code> property defined at extension <code>permissionswebservices</code>. */
@ApiModelProperty(name="permissions", value="Type with CRUD permission assignment values.", required=true) 	
	private PermissionValuesWsDTO permissions;
	
	public TypePermissionsWsDTO()
	{
		// default constructor
	}
	
	public void setType(final String type)
	{
		this.type = type;
	}

	public String getType() 
	{
		return type;
	}
	
	public void setPermissions(final PermissionValuesWsDTO permissions)
	{
		this.permissions = permissions;
	}

	public PermissionValuesWsDTO getPermissions() 
	{
		return permissions;
	}
	

}