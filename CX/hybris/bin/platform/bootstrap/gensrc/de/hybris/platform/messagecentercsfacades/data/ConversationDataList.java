/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 13 févr. 2025, 16:27:40
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.messagecentercsfacades.data;

import java.io.Serializable;
import de.hybris.platform.messagecentercsfacades.data.ConversationData;
import java.util.List;


import java.util.Objects;
public  class ConversationDataList  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** <i>Generated property</i> for <code>ConversationDataList.conversations</code> property defined at extension <code>messagecentercsfacades</code>. */
	
	private List<ConversationData> conversations;
	
	public ConversationDataList()
	{
		// default constructor
	}
	
	public void setConversations(final List<ConversationData> conversations)
	{
		this.conversations = conversations;
	}

	public List<ConversationData> getConversations() 
	{
		return conversations;
	}
	

}