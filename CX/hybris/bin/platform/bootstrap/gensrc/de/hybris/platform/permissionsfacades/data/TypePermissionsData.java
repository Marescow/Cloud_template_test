/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 13 févr. 2025, 16:27:37
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.permissionsfacades.data;

import java.io.Serializable;


import java.util.Objects;
/**
 * Type with CRUD permission assignment values.
 */
public  class TypePermissionsData  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** A Type specifying which type the permission assignment values are for.<br/><br/><i>Generated property</i> for <code>TypePermissionsData.type</code> property defined at extension <code>permissionsfacades</code>. */
	
	private String type;

	/** Type with CRUD permission assignment values.<br/><br/><i>Generated property</i> for <code>TypePermissionsData.permissions</code> property defined at extension <code>permissionsfacades</code>. */
	
	private PermissionValuesData permissions;
	
	public TypePermissionsData()
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
	
	public void setPermissions(final PermissionValuesData permissions)
	{
		this.permissions = permissions;
	}

	public PermissionValuesData getPermissions() 
	{
		return permissions;
	}
	

}