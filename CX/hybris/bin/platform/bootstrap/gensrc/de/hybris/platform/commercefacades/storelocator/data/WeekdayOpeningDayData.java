/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 13 févr. 2025, 16:27:35
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.commercefacades.storelocator.data;

import de.hybris.platform.commercefacades.storelocator.data.OpeningDayData;


import java.util.Objects;
public  class WeekdayOpeningDayData extends OpeningDayData 

{



	/** <i>Generated property</i> for <code>WeekdayOpeningDayData.weekDay</code> property defined at extension <code>commercefacades</code>. */
	
	private String weekDay;

	/** <i>Generated property</i> for <code>WeekdayOpeningDayData.closed</code> property defined at extension <code>commercefacades</code>. */
	
	private boolean closed;
	
	public WeekdayOpeningDayData()
	{
		// default constructor
	}
	
	public void setWeekDay(final String weekDay)
	{
		this.weekDay = weekDay;
	}

	public String getWeekDay() 
	{
		return weekDay;
	}
	
	public void setClosed(final boolean closed)
	{
		this.closed = closed;
	}

	public boolean isClosed() 
	{
		return closed;
	}
	

}