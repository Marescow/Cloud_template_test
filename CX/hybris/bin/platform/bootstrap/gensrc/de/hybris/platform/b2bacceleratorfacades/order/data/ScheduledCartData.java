/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 13 févr. 2025, 16:27:34
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.b2bacceleratorfacades.order.data;

import de.hybris.platform.b2bacceleratorfacades.order.data.TriggerData;
import de.hybris.platform.commercefacades.order.data.CartData;
import java.util.Date;


import java.util.Objects;
public  class ScheduledCartData extends CartData 

{



	/** <i>Generated property</i> for <code>ScheduledCartData.active</code> property defined at extension <code>b2bacceleratorfacades</code>. */
	
	private boolean active;

	/** <i>Generated property</i> for <code>ScheduledCartData.triggerData</code> property defined at extension <code>b2bacceleratorfacades</code>. */
	
	private TriggerData triggerData;

	/** <i>Generated property</i> for <code>ScheduledCartData.firstOrderDate</code> property defined at extension <code>b2bacceleratorfacades</code>. */
	
	private Date firstOrderDate;

	/** <i>Generated property</i> for <code>ScheduledCartData.jobCode</code> property defined at extension <code>b2bacceleratorfacades</code>. */
	
	private String jobCode;
	
	public ScheduledCartData()
	{
		// default constructor
	}
	
	public void setActive(final boolean active)
	{
		this.active = active;
	}

	public boolean isActive() 
	{
		return active;
	}
	
	public void setTriggerData(final TriggerData triggerData)
	{
		this.triggerData = triggerData;
	}

	public TriggerData getTriggerData() 
	{
		return triggerData;
	}
	
	public void setFirstOrderDate(final Date firstOrderDate)
	{
		this.firstOrderDate = firstOrderDate;
	}

	public Date getFirstOrderDate() 
	{
		return firstOrderDate;
	}
	
	public void setJobCode(final String jobCode)
	{
		this.jobCode = jobCode;
	}

	public String getJobCode() 
	{
		return jobCode;
	}
	

}