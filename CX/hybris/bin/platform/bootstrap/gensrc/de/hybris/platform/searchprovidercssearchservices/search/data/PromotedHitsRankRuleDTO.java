/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 13 févr. 2025, 16:27:36
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.searchprovidercssearchservices.search.data;

import de.hybris.platform.searchprovidercssearchservices.search.data.AbstractRankRuleDTO;
import de.hybris.platform.searchprovidercssearchservices.search.data.PromotedHitDTO;
import java.util.List;


import java.util.Objects;
public  class PromotedHitsRankRuleDTO extends AbstractRankRuleDTO 

{



	/** <i>Generated property</i> for <code>PromotedHitsRankRuleDTO.hits</code> property defined at extension <code>searchprovidercssearchservices</code>. */
	
	private List<PromotedHitDTO> hits;
	
	public PromotedHitsRankRuleDTO()
	{
		// default constructor
	}
	
	public void setHits(final List<PromotedHitDTO> hits)
	{
		this.hits = hits;
	}

	public List<PromotedHitDTO> getHits() 
	{
		return hits;
	}
	

}