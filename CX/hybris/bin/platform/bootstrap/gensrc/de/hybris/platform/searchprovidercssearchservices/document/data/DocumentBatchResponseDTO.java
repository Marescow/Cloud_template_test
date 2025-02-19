/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 13 févr. 2025, 16:27:39
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.searchprovidercssearchservices.document.data;

import java.io.Serializable;
import de.hybris.platform.searchprovidercssearchservices.document.data.DocumentBatchOperationResponseDTO;
import java.util.List;


import java.util.Objects;
public  class DocumentBatchResponseDTO  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** <i>Generated property</i> for <code>DocumentBatchResponseDTO.responses</code> property defined at extension <code>searchprovidercssearchservices</code>. */
	
	private List<DocumentBatchOperationResponseDTO> responses;
	
	public DocumentBatchResponseDTO()
	{
		// default constructor
	}
	
	public void setResponses(final List<DocumentBatchOperationResponseDTO> responses)
	{
		this.responses = responses;
	}

	public List<DocumentBatchOperationResponseDTO> getResponses() 
	{
		return responses;
	}
	

}