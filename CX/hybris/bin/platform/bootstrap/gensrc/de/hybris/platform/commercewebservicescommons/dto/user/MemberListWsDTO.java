/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 13 févr. 2025, 16:27:35
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.commercewebservicescommons.dto.user;

import java.io.Serializable;
import de.hybris.platform.commercewebservicescommons.dto.user.PrincipalWsDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;


import java.util.Objects;
/**
 * Representation of a Member List
 */
@ApiModel(value="MemberList", description="Representation of a Member List")
public  class MemberListWsDTO  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** List of member<br/><br/><i>Generated property</i> for <code>MemberListWsDTO.members</code> property defined at extension <code>commercewebservicescommons</code>. */
@ApiModelProperty(name="members", value="List of member") 	
	private List<PrincipalWsDTO> members;
	
	public MemberListWsDTO()
	{
		// default constructor
	}
	
	public void setMembers(final List<PrincipalWsDTO> members)
	{
		this.members = members;
	}

	public List<PrincipalWsDTO> getMembers() 
	{
		return members;
	}
	

}