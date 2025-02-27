/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 13 févr. 2025, 16:27:40
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.webservicescommons.dto;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


import java.util.Objects;
/**
 * Pagination info
 */
@ApiModel(value="pagination", description="Pagination info")
public  class PaginationWsDTO  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** Number of elements on this page<br/><br/><i>Generated property</i> for <code>PaginationWsDTO.count</code> property defined at extension <code>webservicescommons</code>. */
@ApiModelProperty(name="count", value="Number of elements on this page") 	
	private Integer count;

	/** Total number of elements<br/><br/><i>Generated property</i> for <code>PaginationWsDTO.totalCount</code> property defined at extension <code>webservicescommons</code>. */
@ApiModelProperty(name="totalCount", value="Total number of elements") 	
	private Long totalCount;

	/** Current page number<br/><br/><i>Generated property</i> for <code>PaginationWsDTO.page</code> property defined at extension <code>webservicescommons</code>. */
@ApiModelProperty(name="page", value="Current page number") 	
	private Integer page;

	/** Total number of pages<br/><br/><i>Generated property</i> for <code>PaginationWsDTO.totalPages</code> property defined at extension <code>webservicescommons</code>. */
@ApiModelProperty(name="totalPages", value="Total number of pages") 	
	private Integer totalPages;

	/** Indicates if there is next page<br/><br/><i>Generated property</i> for <code>PaginationWsDTO.hasNext</code> property defined at extension <code>webservicescommons</code>. */
@ApiModelProperty(name="hasNext", value="Indicates if there is next page") 	
	private Boolean hasNext;

	/** Indicates if there is previous page<br/><br/><i>Generated property</i> for <code>PaginationWsDTO.hasPrevious</code> property defined at extension <code>webservicescommons</code>. */
@ApiModelProperty(name="hasPrevious", value="Indicates if there is previous page") 	
	private Boolean hasPrevious;
	
	public PaginationWsDTO()
	{
		// default constructor
	}
	
	public void setCount(final Integer count)
	{
		this.count = count;
	}

	public Integer getCount() 
	{
		return count;
	}
	
	public void setTotalCount(final Long totalCount)
	{
		this.totalCount = totalCount;
	}

	public Long getTotalCount() 
	{
		return totalCount;
	}
	
	public void setPage(final Integer page)
	{
		this.page = page;
	}

	public Integer getPage() 
	{
		return page;
	}
	
	public void setTotalPages(final Integer totalPages)
	{
		this.totalPages = totalPages;
	}

	public Integer getTotalPages() 
	{
		return totalPages;
	}
	
	public void setHasNext(final Boolean hasNext)
	{
		this.hasNext = hasNext;
	}

	public Boolean getHasNext() 
	{
		return hasNext;
	}
	
	public void setHasPrevious(final Boolean hasPrevious)
	{
		this.hasPrevious = hasPrevious;
	}

	public Boolean getHasPrevious() 
	{
		return hasPrevious;
	}
	

}