/*
 * Copyright (c) 2023 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.servicelayer.search.impl;

import static org.assertj.core.api.Assertions.assertThat;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.core.LazyLoadItemSet;
import de.hybris.platform.core.Registry;
import de.hybris.platform.core.model.c2l.CountryModel;
import de.hybris.platform.jalo.ConsistencyCheckException;
import de.hybris.platform.jalo.c2l.C2LManager;
import de.hybris.platform.jalo.c2l.Country;
import de.hybris.platform.servicelayer.ServicelayerBaseTest;
import de.hybris.platform.servicelayer.search.internal.resolver.ItemObjectResolver;

import java.util.Collections;
import java.util.List;

import org.junit.Test;


@IntegrationTest
public class LazyLoadModelSetTest extends ServicelayerBaseTest
{

	@Test
	public void testModelBasedSet() throws ConsistencyCheckException
	{
		// given
		final Country myCountry = C2LManager.getInstance().createCountry("thalerland");

		final LazyLoadItemSet<Country> baseSet = new LazyLoadItemSet<>(null, Collections.singletonList(myCountry.getPK()),
				1);
		assertThat(baseSet).containsExactly(myCountry);

		// when
		final LazyLoadModelSet<CountryModel> modelSet = LazyLoadModelSet.create(baseSet, List.of(CountryModel.class),
				Registry.getApplicationContext().getBean("modelResolver", ItemObjectResolver.class));

		// then
		assertThat(modelSet.iterator().next()).isInstanceOf(CountryModel.class);
		assertThat(modelSet.iterator().next().getPk()).isEqualTo(baseSet.iterator().next().getPK());
		assertThat(modelSet.getPosition(modelSet.iterator().next())).isNotNegative();
	}
}
