/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 13 févr. 2025, 16:27:35
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.cmswebservices.data;

import java.io.Serializable;
import de.hybris.platform.cmsfacades.data.OptionData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import java.util.Map;
import java.util.Set;


import java.util.Objects;
/**
 * Specifies properties of the component type attribute.
 */
@ApiModel(value="ComponentTypeAttributeData", description="Specifies properties of the component type attribute.")
public  class ComponentTypeAttributeData  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** <i>Generated property</i> for <code>ComponentTypeAttributeData.qualifier</code> property defined at extension <code>cmswebservices</code>. */
@ApiModelProperty(name="qualifier", example="cmsComponents") 	
	private String qualifier;

	/** <i>Generated property</i> for <code>ComponentTypeAttributeData.required</code> property defined at extension <code>cmswebservices</code>. */
@ApiModelProperty(name="required", example="false") 	
	private boolean required;

	/** <i>Generated property</i> for <code>ComponentTypeAttributeData.localized</code> property defined at extension <code>cmswebservices</code>. */
@ApiModelProperty(name="localized", example="false") 	
	private Boolean localized;

	/** <i>Generated property</i> for <code>ComponentTypeAttributeData.cmsStructureType</code> property defined at extension <code>cmswebservices</code>. */
@ApiModelProperty(name="cmsStructureType", example="ShortString") 	
	private String cmsStructureType;

	/** <i>Generated property</i> for <code>ComponentTypeAttributeData.cmsStructureEnumType</code> property defined at extension <code>cmswebservices</code>. */
@ApiModelProperty(name="cmsStructureEnumType") 	
	private String cmsStructureEnumType;

	/** <i>Generated property</i> for <code>ComponentTypeAttributeData.i18nKey</code> property defined at extension <code>cmswebservices</code>. */
@ApiModelProperty(name="i18nKey", example="type.searchboxcomponent.name") 	
	private String i18nKey;

	/** <i>Generated property</i> for <code>ComponentTypeAttributeData.dependsOn</code> property defined at extension <code>cmswebservices</code>. */
@ApiModelProperty(name="dependsOn") 	
	private String dependsOn;

	/** <i>Generated property</i> for <code>ComponentTypeAttributeData.options</code> property defined at extension <code>cmswebservices</code>. */
@ApiModelProperty(name="options") 	
	private List<OptionData> options;

	/** <i>Generated property</i> for <code>ComponentTypeAttributeData.idAttribute</code> property defined at extension <code>cmswebservices</code>. */
@ApiModelProperty(name="idAttribute", example="value") 	
	private String idAttribute;

	/** <i>Generated property</i> for <code>ComponentTypeAttributeData.labelAttributes</code> property defined at extension <code>cmswebservices</code>. */
@ApiModelProperty(name="labelAttributes", example="[\"label\"]") 	
	private List<String> labelAttributes;

	/** <i>Generated property</i> for <code>ComponentTypeAttributeData.paged</code> property defined at extension <code>cmswebservices</code>. */
@ApiModelProperty(name="paged", example="false") 	
	private boolean paged;

	/** <i>Generated property</i> for <code>ComponentTypeAttributeData.editable</code> property defined at extension <code>cmswebservices</code>. */
@ApiModelProperty(name="editable", example="false") 	
	private boolean editable;

	/** <i>Generated property</i> for <code>ComponentTypeAttributeData.collection</code> property defined at extension <code>cmswebservices</code>. */
@ApiModelProperty(name="collection", example="false") 	
	private boolean collection;

	/** <i>Generated property</i> for <code>ComponentTypeAttributeData.uri</code> property defined at extension <code>cmswebservices</code>. */
@ApiModelProperty(name="uri") 	
	private String uri;

	/** <i>Generated property</i> for <code>ComponentTypeAttributeData.params</code> property defined at extension <code>cmswebservices</code>. */
@ApiModelProperty(name="params", example="{\"typeCode\" : \"AbstractRestriction\"}") 	
	private Map<String,String> params;

	/** <i>Generated property</i> for <code>ComponentTypeAttributeData.subTypes</code> property defined at extension <code>cmswebservices</code>. */
@ApiModelProperty(name="subTypes", example="{\"CMSUserGroupRestriction\" : \"type.cmsusergrouprestriction.name\", \"CMSCampaignRestriction\" : \"type.cmscampaignrestriction.name\"}") 	
	private Map<String,String> subTypes;

	/** <i>Generated property</i> for <code>ComponentTypeAttributeData.containedTypes</code> property defined at extension <code>cmswebservices</code>. */
@ApiModelProperty(name="containedTypes", example="[\"MediaFormat\", \"MediaContainer\"]") 	
	private Set<String> containedTypes;

	/** <i>Generated property</i> for <code>ComponentTypeAttributeData.placeholder</code> property defined at extension <code>cmswebservices</code>. */
@ApiModelProperty(name="placeholder", example="Select or create an entry") 	
	private String placeholder;
	
	public ComponentTypeAttributeData()
	{
		// default constructor
	}
	
	public void setQualifier(final String qualifier)
	{
		this.qualifier = qualifier;
	}

	public String getQualifier() 
	{
		return qualifier;
	}
	
	public void setRequired(final boolean required)
	{
		this.required = required;
	}

	public boolean isRequired() 
	{
		return required;
	}
	
	public void setLocalized(final Boolean localized)
	{
		this.localized = localized;
	}

	public Boolean getLocalized() 
	{
		return localized;
	}
	
	public void setCmsStructureType(final String cmsStructureType)
	{
		this.cmsStructureType = cmsStructureType;
	}

	public String getCmsStructureType() 
	{
		return cmsStructureType;
	}
	
	public void setCmsStructureEnumType(final String cmsStructureEnumType)
	{
		this.cmsStructureEnumType = cmsStructureEnumType;
	}

	public String getCmsStructureEnumType() 
	{
		return cmsStructureEnumType;
	}
	
	public void setI18nKey(final String i18nKey)
	{
		this.i18nKey = i18nKey;
	}

	public String getI18nKey() 
	{
		return i18nKey;
	}
	
	public void setDependsOn(final String dependsOn)
	{
		this.dependsOn = dependsOn;
	}

	public String getDependsOn() 
	{
		return dependsOn;
	}
	
	public void setOptions(final List<OptionData> options)
	{
		this.options = options;
	}

	public List<OptionData> getOptions() 
	{
		return options;
	}
	
	public void setIdAttribute(final String idAttribute)
	{
		this.idAttribute = idAttribute;
	}

	public String getIdAttribute() 
	{
		return idAttribute;
	}
	
	public void setLabelAttributes(final List<String> labelAttributes)
	{
		this.labelAttributes = labelAttributes;
	}

	public List<String> getLabelAttributes() 
	{
		return labelAttributes;
	}
	
	public void setPaged(final boolean paged)
	{
		this.paged = paged;
	}

	public boolean isPaged() 
	{
		return paged;
	}
	
	public void setEditable(final boolean editable)
	{
		this.editable = editable;
	}

	public boolean isEditable() 
	{
		return editable;
	}
	
	public void setCollection(final boolean collection)
	{
		this.collection = collection;
	}

	public boolean isCollection() 
	{
		return collection;
	}
	
	public void setUri(final String uri)
	{
		this.uri = uri;
	}

	public String getUri() 
	{
		return uri;
	}
	
	public void setParams(final Map<String,String> params)
	{
		this.params = params;
	}

	public Map<String,String> getParams() 
	{
		return params;
	}
	
	public void setSubTypes(final Map<String,String> subTypes)
	{
		this.subTypes = subTypes;
	}

	public Map<String,String> getSubTypes() 
	{
		return subTypes;
	}
	
	public void setContainedTypes(final Set<String> containedTypes)
	{
		this.containedTypes = containedTypes;
	}

	public Set<String> getContainedTypes() 
	{
		return containedTypes;
	}
	
	public void setPlaceholder(final String placeholder)
	{
		this.placeholder = placeholder;
	}

	public String getPlaceholder() 
	{
		return placeholder;
	}
	

}