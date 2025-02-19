/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 13 févr. 2025, 16:27:39
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.xyformsfacades.data;

import java.io.Serializable;
import de.hybris.platform.xyformsfacades.data.YFormDefinitionData;
import de.hybris.platform.xyformsservices.enums.YFormDataTypeEnum;
import java.util.Date;


import java.util.Objects;
public  class YFormDataData  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** <i>Generated property</i> for <code>YFormDataData.id</code> property defined at extension <code>xyformsfacades</code>. */
	
	private String id;

	/** <i>Generated property</i> for <code>YFormDataData.content</code> property defined at extension <code>xyformsfacades</code>. */
	
	private String content;

	/** <i>Generated property</i> for <code>YFormDataData.type</code> property defined at extension <code>xyformsfacades</code>. */
	
	private YFormDataTypeEnum type;

	/** <i>Generated property</i> for <code>YFormDataData.refId</code> property defined at extension <code>xyformsfacades</code>. */
	
	private String refId;

	/** <i>Generated property</i> for <code>YFormDataData.createdDate</code> property defined at extension <code>xyformsfacades</code>. */
	
	private Date createdDate;

	/** <i>Generated property</i> for <code>YFormDataData.lastModifiedDate</code> property defined at extension <code>xyformsfacades</code>. */
	
	private Date lastModifiedDate;

	/** <i>Generated property</i> for <code>YFormDataData.formDefinition</code> property defined at extension <code>xyformsfacades</code>. */
	
	private YFormDefinitionData formDefinition;
	
	public YFormDataData()
	{
		// default constructor
	}
	
	public void setId(final String id)
	{
		this.id = id;
	}

	public String getId() 
	{
		return id;
	}
	
	public void setContent(final String content)
	{
		this.content = content;
	}

	public String getContent() 
	{
		return content;
	}
	
	public void setType(final YFormDataTypeEnum type)
	{
		this.type = type;
	}

	public YFormDataTypeEnum getType() 
	{
		return type;
	}
	
	public void setRefId(final String refId)
	{
		this.refId = refId;
	}

	public String getRefId() 
	{
		return refId;
	}
	
	public void setCreatedDate(final Date createdDate)
	{
		this.createdDate = createdDate;
	}

	public Date getCreatedDate() 
	{
		return createdDate;
	}
	
	public void setLastModifiedDate(final Date lastModifiedDate)
	{
		this.lastModifiedDate = lastModifiedDate;
	}

	public Date getLastModifiedDate() 
	{
		return lastModifiedDate;
	}
	
	public void setFormDefinition(final YFormDefinitionData formDefinition)
	{
		this.formDefinition = formDefinition;
	}

	public YFormDefinitionData getFormDefinition() 
	{
		return formDefinition;
	}
	

}