/*
 * Copyright (c) 2023 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.b2b.dao;

import de.hybris.platform.b2b.model.B2BCostCenterModel;
import de.hybris.platform.b2b.model.B2BUnitModel;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.user.UserGroupModel;
import de.hybris.platform.servicelayer.internal.dao.GenericDao;

import java.util.List;
import java.util.Set;

import org.apache.commons.lang.NotImplementedException;

public interface B2BCostCenterDao extends GenericDao<B2BCostCenterModel>
{

	/**
	 * Returns list of active {@link B2BCostCenterModel}s associated with any B2BUnit in the set passed and having the
	 * matching currency.
	 * 
	 * @param branch
	 * @param currency
	 * @return List {@link B2BCostCenterModel}
	 */
	List<B2BCostCenterModel> findActiveCostCentersByBranchAndCurrency(final Set<B2BUnitModel> branch, final CurrencyModel currency);

	/**
	 * Finds B2BCostCenter by code, If none is found null is returned.
	 * 
	 * @param code
	 *           , the code of the desired Cost Center
	 */
	B2BCostCenterModel findByCode(final String code);

	/**
	 * Returns list of available {@link B2BCostCenterModel}s associated with any B2BUnit in the set passed and
	 * the currency is not null.
	 * @param parents
	 * @return
	 */
	default List<B2BCostCenterModel> findAvailableCostCentersByB2BUnit(final Set<UserGroupModel> parents) {
		throw new NotImplementedException(
				"Not implemented. Use de.hybris.platform.b2b.dao.impl.DefaultB2BCostCenterDao.findAvailableCostCentersByB2BUnit(Set).");
	}
}
