/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 13 févr. 2025, 16:27:38
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.chinesecommercewebservicescommons.dto;

import java.io.Serializable;
import de.hybris.platform.chinesecommercewebservicescommons.dto.CityWsDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;


import java.util.Objects;
/**
 * list of city
 */
@ApiModel(value="CityList", description="list of city")
public  class CityListWsDTO  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** list of city<br/><br/><i>Generated property</i> for <code>CityListWsDTO.cities</code> property defined at extension <code>chinesecommercewebservicescommons</code>. */
@ApiModelProperty(name="cities", value="list of city") 	
	private List<CityWsDTO> cities;
	
	public CityListWsDTO()
	{
		// default constructor
	}
	
	public void setCities(final List<CityWsDTO> cities)
	{
		this.cities = cities;
	}

	public List<CityWsDTO> getCities() 
	{
		return cities;
	}
	

}