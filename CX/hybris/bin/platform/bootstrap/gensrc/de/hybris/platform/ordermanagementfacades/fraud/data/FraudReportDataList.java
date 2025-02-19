/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 13 févr. 2025, 16:27:37
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.ordermanagementfacades.fraud.data;

import java.io.Serializable;
import de.hybris.platform.ordermanagementfacades.fraud.data.FraudReportData;
import java.util.List;


import java.util.Objects;
public  class FraudReportDataList  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** <i>Generated property</i> for <code>FraudReportDataList.reports</code> property defined at extension <code>ordermanagementfacades</code>. */
	
	private List<FraudReportData> reports;
	
	public FraudReportDataList()
	{
		// default constructor
	}
	
	public void setReports(final List<FraudReportData> reports)
	{
		this.reports = reports;
	}

	public List<FraudReportData> getReports() 
	{
		return reports;
	}
	

}