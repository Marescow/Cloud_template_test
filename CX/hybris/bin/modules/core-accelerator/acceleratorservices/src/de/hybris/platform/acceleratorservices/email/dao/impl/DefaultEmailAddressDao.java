/*
 * Copyright (c) 2022 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.acceleratorservices.email.dao.impl;

import de.hybris.platform.acceleratorservices.email.dao.EmailAddressDao;
import de.hybris.platform.acceleratorservices.model.email.EmailAddressModel;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.servicelayer.util.ServicesUtil;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;


/**
 * Default Data Access for looking up email addresses.
 */
public class DefaultEmailAddressDao extends DefaultGenericDao<EmailAddressModel> implements EmailAddressDao
{
	private static final Logger LOG = Logger.getLogger(DefaultEmailAddressDao.class);


	public DefaultEmailAddressDao()
	{
		super(EmailAddressModel._TYPECODE);
	}

	@Override
	public EmailAddressModel findEmailAddressByEmailAndDisplayName(final String emailAddress, final String displayName)
	{
		ServicesUtil.validateParameterNotNull(emailAddress, "emailAddress must not be null");

		final Map<String, Object> params = new HashMap<String, Object>();

		params.put(EmailAddressModel.EMAILADDRESS, emailAddress);
		if (displayName != null)
		{
			params.put(EmailAddressModel.DISPLAYNAME, displayName);
		}

		if (LOG.isDebugEnabled())
		{
			LOG.info("Searching for emailAddress [" + emailAddress + "] displayName [" + displayName + "]");
		}

		final StringBuilder query = new StringBuilder("SELECT {").append(ItemModel.PK).append("} FROM  {")
				.append(EmailAddressModel._TYPECODE).append("} WHERE {").append(EmailAddressModel.EMAILADDRESS)
				.append("} = ?").append(EmailAddressModel.EMAILADDRESS);
		if (displayName == null)
		{
			query.append(" AND {").append(EmailAddressModel.DISPLAYNAME).append("} is null ");
		}
		else
		{
			query.append(" AND {").append(EmailAddressModel.DISPLAYNAME).append("} = ?").append(EmailAddressModel.DISPLAYNAME);
		}


		final SearchResult<EmailAddressModel> results = getFlexibleSearchService().<EmailAddressModel> search(query.toString(),
				params);

		if (LOG.isDebugEnabled())
		{
			LOG.info("Results: " + (results == null ? "null" : String.valueOf(results.getCount())));
		}

		if (results == null || CollectionUtils.isEmpty(results.getResult()))
		{
			return null;
		}

		return results.getResult().iterator().next();
	}

	@Override
	public List<EmailAddressModel> getAllEmailAddress(){
		if (LOG.isDebugEnabled())
		{
			LOG.info("Searching all emailAddress");
		}

		final StringBuilder query = new StringBuilder("SELECT {").append(EmailAddressModel.PK).append("} FROM {")
					.append(EmailAddressModel._TYPECODE).append("}");
		final SearchResult<EmailAddressModel> results = getFlexibleSearchService().<EmailAddressModel> search(query.toString());

		if (LOG.isDebugEnabled())
		{
			LOG.info("Results: " + (results == null ? "null" : String.valueOf(results.getCount())));
		}

		if (results == null || CollectionUtils.isEmpty(results.getResult()))
		{
			return Collections.emptyList();
		}
		return results.getResult();
	}
}
