/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 13 févr. 2025, 16:27:35
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.ordermanagementwebservices.dto.fraud;

import java.io.Serializable;
import de.hybris.platform.ordermanagementwebservices.dto.fraud.FraudReportWsDTO;
import java.util.List;


import java.util.Objects;
public  class FraudReportListWsDTO  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** <i>Generated property</i> for <code>FraudReportListWsDTO.reports</code> property defined at extension <code>ordermanagementwebservices</code>. */
	
	private List<FraudReportWsDTO> reports;
	
	public FraudReportListWsDTO()
	{
		// default constructor
	}
	
	public void setReports(final List<FraudReportWsDTO> reports)
	{
		this.reports = reports;
	}

	public List<FraudReportWsDTO> getReports() 
	{
		return reports;
	}
	

}