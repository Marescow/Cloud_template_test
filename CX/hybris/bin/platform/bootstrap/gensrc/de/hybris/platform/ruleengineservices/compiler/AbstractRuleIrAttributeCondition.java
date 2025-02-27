/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 13 févr. 2025, 16:27:37
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.ruleengineservices.compiler;

import de.hybris.platform.ruleengineservices.compiler.AbstractRuleIrPatternCondition;
import de.hybris.platform.ruleengineservices.compiler.RuleIrAttributeOperator;


import java.util.Objects;
public abstract  class AbstractRuleIrAttributeCondition extends AbstractRuleIrPatternCondition 

{



	/** <i>Generated property</i> for <code>AbstractRuleIrAttributeCondition.attribute</code> property defined at extension <code>ruleengineservices</code>. */
	
	private String attribute;

	/** <i>Generated property</i> for <code>AbstractRuleIrAttributeCondition.operator</code> property defined at extension <code>ruleengineservices</code>. */
	
	private RuleIrAttributeOperator operator;
	
	public AbstractRuleIrAttributeCondition()
	{
		// default constructor
	}
	
	public void setAttribute(final String attribute)
	{
		this.attribute = attribute;
	}

	public String getAttribute() 
	{
		return attribute;
	}
	
	public void setOperator(final RuleIrAttributeOperator operator)
	{
		this.operator = operator;
	}

	public RuleIrAttributeOperator getOperator() 
	{
		return operator;
	}
	

}