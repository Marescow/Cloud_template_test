/*
* ----------------------------------------------------------------
* --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
* --- Generated at 13 févr. 2025, 16:27:35
* ----------------------------------------------------------------
*
* Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
*/
package de.hybris.platform.ruleengineservices.rao;

import java.io.Serializable;
import java.util.Objects;
import de.hybris.platform.ruleengineservices.rao.AbstractRuleActionRAO;
import java.util.LinkedHashSet;

public  class AbstractActionedRAO  implements Serializable 

{

/** <i>Generated property</i> for <code>AbstractActionedRAO.actions</code> property defined at extension <code>ruleengineservices</code>. */
	private LinkedHashSet<AbstractRuleActionRAO> actions;
	
	public AbstractActionedRAO()
	{
		// default constructor
	}
	
	public void setActions(final LinkedHashSet<AbstractRuleActionRAO> actions)
	{
		this.actions = actions;
	}
	public LinkedHashSet<AbstractRuleActionRAO> getActions() 
	{
		return actions;
	}
	

}
