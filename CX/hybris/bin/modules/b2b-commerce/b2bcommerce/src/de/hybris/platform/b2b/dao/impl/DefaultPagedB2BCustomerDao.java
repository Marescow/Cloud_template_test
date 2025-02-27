/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.b2b.dao.impl;

import de.hybris.platform.b2b.constants.B2BConstants;
import de.hybris.platform.b2b.dao.PagedB2BCustomerDao;
import de.hybris.platform.b2b.model.B2BCustomerModel;
import de.hybris.platform.commerceservices.search.dao.impl.DefaultPagedGenericDao;
import de.hybris.platform.commerceservices.search.flexiblesearch.data.SortQueryData;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.internal.dao.SortParameters;
import de.hybris.platform.util.Config;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;


public class DefaultPagedB2BCustomerDao extends DefaultPagedGenericDao<B2BCustomerModel>
		implements PagedB2BCustomerDao<B2BCustomerModel>
{
	public static final Logger LOG = Logger.getLogger(DefaultPagedB2BBudgetDao.class);

	private static final String FIND_B2BCUSTOMERS_QUERY = "SELECT {b2bcustomer:pk}, {b2bcustomer:name} as CustomerName,"
			+ " {b2bunit:name} AS UnitName FROM { B2BCustomer AS b2bcustomer "
			+ " JOIN PrincipalGroupRelation AS b2bunitrelation ON {b2bunitrelation:source} = {b2bcustomer:pk} "
			+ " JOIN B2BUnit  AS b2bunit ON {b2bunit:pk} = {b2bunitrelation:target} } ";

	private static final String FIND_B2BCUSTOMERS_IN_DESIRED_GROUPS = "SELECT DISTINCT {b2bcustomer:pk},"
			+ " {b2bcustomer:name} as CustomerName FROM { B2BCustomer AS b2bcustomer"
			+ " JOIN PrincipalGroupRelation AS b2bunitrelation ON {b2bunitrelation:source} = {b2bcustomer:pk}"
			+ " JOIN B2BUnit AS b2bunit ON {b2bunit:pk} = {b2bunitrelation:target}"
			+ " JOIN PrincipalGroupRelation 	AS desiredgrouprelations ON {desiredgrouprelations:source} = {b2bcustomer:pk}"
			+ " JOIN UserGroup AS desiredgroups ON {desiredgroups:pk} = {desiredgrouprelations:target}}"
			+ " WHERE {desiredgroups:uid} IN (?usergroups)";

	private static final String WHERE_UNIT_NAME = " WHERE {b2bunit:uid} = ?unit ";
	private static final String FILTER_BY_UNIT = " AND {b2bunit:uid} = ?unit ";
	private static final String SEARCH_BY_NAME_AND_UNIT = " AND ( LOWER ({b2bunit:name}) like LOWER (?searchterm)"
			+ " OR LOWER ({b2bcustomer:name}) like LOWER (?searchterm) ) ";
	private static final String OR_B2BUNIT_MEMBERS = " OR {b2bunit:uid} = ?unit ";
	private static final String ORDERBY_UNIT_NAME = " ORDER BY UnitName ";
	private static final String ORDERBY_CUSTOMER_NAME = " ORDER BY CustomerName ";
	private static final String ORDERBY_UNIT_NAME_ASC = " ORDER BY UnitName ASC ";
	private static final String ORDERBY_CUSTOMER_NAME_ASC = " ORDER BY CustomerName ASC ";
	private static final String ORDERBY_UNIT_NAME_DESC = " ORDER BY UnitName DESC ";
	private static final String ORDERBY_CUSTOMER_NAME_DESC = " ORDER BY CustomerName DESC ";
	private static final String DEFAULT_SORT_CODE = Config.getString(B2BConstants.DEFAULT_SORT_CODE_PROP, "byName");

	public DefaultPagedB2BCustomerDao(final String typeCode)
	{
		super(typeCode);
	}

	@Override
	public SearchPageData<B2BCustomerModel> find(final PageableData pageableData)
	{
		final List<SortQueryData> sortQueries = Arrays.asList(
				createSortQueryData("byName", new HashMap<String, Object>(), SortParameters.singletonAscending(UserModel.NAME)),
				createSortQueryData("byUnit", FIND_B2BCUSTOMERS_QUERY + ORDERBY_UNIT_NAME));

		return getPagedFlexibleSearchService().search(sortQueries, DEFAULT_SORT_CODE, new HashMap<String, Object>(), pageableData);
	}

	@Override
	public SearchPageData<B2BCustomerModel> findPagedCustomersByGroupMembership(final PageableData pageableData,
			final String... userGroupid)
	{

		final List<SortQueryData> sortQueries = Arrays.asList(
				createSortQueryData("byName", FIND_B2BCUSTOMERS_IN_DESIRED_GROUPS + ORDERBY_CUSTOMER_NAME_ASC),
				createSortQueryData("byNameDesc", FIND_B2BCUSTOMERS_IN_DESIRED_GROUPS + ORDERBY_CUSTOMER_NAME_DESC),
				createSortQueryData("byUnit", FIND_B2BCUSTOMERS_IN_DESIRED_GROUPS + ORDERBY_UNIT_NAME_ASC),
				createSortQueryData("byUnitDesc", FIND_B2BCUSTOMERS_IN_DESIRED_GROUPS + ORDERBY_UNIT_NAME_DESC));

		return getPagedFlexibleSearchService().search(sortQueries, DEFAULT_SORT_CODE,
				Collections.singletonMap("usergroups", Arrays.asList(userGroupid)), pageableData);
	}

	@Override
	public SearchPageData<B2BCustomerModel> findPagedApproversForUnitByGroupMembership(final PageableData pageableData,
			final String unit, final String... userGroupid)
	{
		final Map<String, Object> queryParams = new HashMap<String, Object>(2);
		queryParams.put("unit", unit);
		queryParams.put("usergroups", Arrays.asList(userGroupid));

		final List<SortQueryData> sortQueries = Arrays.asList(
				createSortQueryData("byName", FIND_B2BCUSTOMERS_IN_DESIRED_GROUPS + OR_B2BUNIT_MEMBERS + ORDERBY_CUSTOMER_NAME),
				createSortQueryData("byUnit", FIND_B2BCUSTOMERS_IN_DESIRED_GROUPS + OR_B2BUNIT_MEMBERS + ORDERBY_UNIT_NAME));

		return getPagedFlexibleSearchService().search(sortQueries, DEFAULT_SORT_CODE, queryParams, pageableData);
	}

	@Override
	public SearchPageData<B2BCustomerModel> findPagedCustomersForUnitByGroupMembership(final PageableData pageableData,
			final String unit, final String... userGroupid)
	{
		final Map<String, Object> queryParams = new HashMap<String, Object>(2);
		queryParams.put("unit", unit);
		queryParams.put("usergroups", Arrays.asList(userGroupid));

		final List<SortQueryData> sortQueries = Arrays
				.asList(createSortQueryData("byName", FIND_B2BCUSTOMERS_IN_DESIRED_GROUPS + FILTER_BY_UNIT + ORDERBY_CUSTOMER_NAME));

		return getPagedFlexibleSearchService().search(sortQueries, DEFAULT_SORT_CODE, queryParams, pageableData);
	}

	@Override
	public SearchPageData<B2BCustomerModel> findPagedCustomersBySearchTermAndGroupMembership(final PageableData pageableData,
			final String searchTerm, final String... userGroupid)
	{

		final Map<String, Object> queryParams = new HashMap<String, Object>(2);
		queryParams.put("usergroups", Arrays.asList(userGroupid));
		queryParams.put("searchterm", "%" + searchTerm + "%");

		final List<SortQueryData> sortQueries = Arrays.asList(
				createSortQueryData("byName",
						FIND_B2BCUSTOMERS_IN_DESIRED_GROUPS + SEARCH_BY_NAME_AND_UNIT + ORDERBY_CUSTOMER_NAME_ASC),
				createSortQueryData("byNameDesc",
						FIND_B2BCUSTOMERS_IN_DESIRED_GROUPS + SEARCH_BY_NAME_AND_UNIT + ORDERBY_CUSTOMER_NAME_DESC),
				createSortQueryData("byUnit", FIND_B2BCUSTOMERS_IN_DESIRED_GROUPS + SEARCH_BY_NAME_AND_UNIT + ORDERBY_UNIT_NAME_ASC),
				createSortQueryData("byUnitDesc",
						FIND_B2BCUSTOMERS_IN_DESIRED_GROUPS + SEARCH_BY_NAME_AND_UNIT + ORDERBY_UNIT_NAME_DESC));

		return getPagedFlexibleSearchService().search(sortQueries, DEFAULT_SORT_CODE, queryParams, pageableData);
	}

	@Override
	public SearchPageData<B2BCustomerModel> findPagedCustomersForUnit(final PageableData pageableData, final String unit)
	{
		final Map<String, Object> queryParams = new HashMap<String, Object>(2);
		queryParams.put("unit", unit);

		final List<SortQueryData> sortQueries = Arrays
				.asList(createSortQueryData("byName", FIND_B2BCUSTOMERS_QUERY + WHERE_UNIT_NAME + ORDERBY_CUSTOMER_NAME));

		return getPagedFlexibleSearchService().search(sortQueries, DEFAULT_SORT_CODE, queryParams, pageableData);
	}
}
