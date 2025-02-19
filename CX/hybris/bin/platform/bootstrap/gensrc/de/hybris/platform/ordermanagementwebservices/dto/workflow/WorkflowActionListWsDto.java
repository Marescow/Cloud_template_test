/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 13 févr. 2025, 16:27:39
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.ordermanagementwebservices.dto.workflow;

import java.io.Serializable;
import de.hybris.platform.ordermanagementwebservices.dto.workflow.WorkflowActionWsDto;
import java.util.List;


import java.util.Objects;
public  class WorkflowActionListWsDto  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** <i>Generated property</i> for <code>WorkflowActionListWsDto.workflowActions</code> property defined at extension <code>ordermanagementwebservices</code>. */
	
	private List<WorkflowActionWsDto> workflowActions;
	
	public WorkflowActionListWsDto()
	{
		// default constructor
	}
	
	public void setWorkflowActions(final List<WorkflowActionWsDto> workflowActions)
	{
		this.workflowActions = workflowActions;
	}

	public List<WorkflowActionWsDto> getWorkflowActions() 
	{
		return workflowActions;
	}
	

}