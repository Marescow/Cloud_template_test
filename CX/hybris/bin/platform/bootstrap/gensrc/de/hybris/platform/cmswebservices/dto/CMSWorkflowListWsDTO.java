/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 13 févr. 2025, 16:27:42
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.cmswebservices.dto;

import java.io.Serializable;
import de.hybris.platform.webservicescommons.dto.PaginationWsDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;


import java.util.Objects;
/**
 * Specifies a list of available CMS workflows.
 */
@ApiModel(value="CMSWorkflowListWsDTO", description="Specifies a list of available CMS workflows.")
public  class CMSWorkflowListWsDTO  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** <i>Generated property</i> for <code>CMSWorkflowListWsDTO.workflows</code> property defined at extension <code>cmswebservices</code>. */
@ApiModelProperty(name="workflows") 	
	private List<CMSWorkflowWsDTO> workflows;

	/** <i>Generated property</i> for <code>CMSWorkflowListWsDTO.pagination</code> property defined at extension <code>cmswebservices</code>. */
@ApiModelProperty(name="pagination", example="{\"count\" : \"0\", \"page\" : \"0\", \"totalCount\" : \"0\", \"totalPages\" : \"0\"}") 	
	private PaginationWsDTO pagination;
	
	public CMSWorkflowListWsDTO()
	{
		// default constructor
	}
	
	public void setWorkflows(final List<CMSWorkflowWsDTO> workflows)
	{
		this.workflows = workflows;
	}

	public List<CMSWorkflowWsDTO> getWorkflows() 
	{
		return workflows;
	}
	
	public void setPagination(final PaginationWsDTO pagination)
	{
		this.pagination = pagination;
	}

	public PaginationWsDTO getPagination() 
	{
		return pagination;
	}
	

}