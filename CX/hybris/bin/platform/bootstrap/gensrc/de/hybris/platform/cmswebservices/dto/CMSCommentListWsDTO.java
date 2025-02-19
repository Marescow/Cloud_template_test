/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 13 févr. 2025, 16:27:36
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
 * Specifies a list of available CMS comments.
 */
@ApiModel(value="CMSCommentListWsDTO", description="Specifies a list of available CMS comments.")
public  class CMSCommentListWsDTO  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** <i>Generated property</i> for <code>CMSCommentListWsDTO.comments</code> property defined at extension <code>cmswebservices</code>. */
@ApiModelProperty(name="comments") 	
	private List<CMSCommentWsDTO> comments;

	/** <i>Generated property</i> for <code>CMSCommentListWsDTO.pagination</code> property defined at extension <code>cmswebservices</code>. */
@ApiModelProperty(name="pagination", example="{\"count\" : \"0\", \"page\" : \"0\", \"totalCount\" : \"0\", \"totalPages\" : \"0\"}") 	
	private PaginationWsDTO pagination;
	
	public CMSCommentListWsDTO()
	{
		// default constructor
	}
	
	public void setComments(final List<CMSCommentWsDTO> comments)
	{
		this.comments = comments;
	}

	public List<CMSCommentWsDTO> getComments() 
	{
		return comments;
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