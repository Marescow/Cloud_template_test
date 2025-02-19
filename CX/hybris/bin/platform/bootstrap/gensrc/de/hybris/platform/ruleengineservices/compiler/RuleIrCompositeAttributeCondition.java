/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 13 févr. 2025, 16:27:41
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.ruleengineservices.compiler;

import de.hybris.platform.ruleengineservices.compiler.AbstractRuleIrPatternCondition;
import de.hybris.platform.ruleengineservices.compiler.RuleIrCondition;
import de.hybris.platform.ruleengineservices.compiler.RuleIrGroupOperator;
import java.util.List;


import java.util.Objects;
public  class RuleIrCompositeAttributeCondition extends AbstractRuleIrPatternCondition 

{



	/** <i>Generated property</i> for <code>RuleIrCompositeAttributeCondition.operator</code> property defined at extension <code>ruleengineservices</code>. */
	
	private RuleIrGroupOperator operator;

	/** <i>Generated property</i> for <code>RuleIrCompositeAttributeCondition.children</code> property defined at extension <code>ruleengineservices</code>. */
	
	private List<RuleIrCondition> children;
	
	public RuleIrCompositeAttributeCondition()
	{
		// default constructor
	}
	
	public void setOperator(final RuleIrGroupOperator operator)
	{
		this.operator = operator;
	}

	public RuleIrGroupOperator getOperator() 
	{
		return operator;
	}
	
	public void setChildren(final List<RuleIrCondition> children)
	{
		this.children = children;
	}

	public List<RuleIrCondition> getChildren() 
	{
		return children;
	}
	

}