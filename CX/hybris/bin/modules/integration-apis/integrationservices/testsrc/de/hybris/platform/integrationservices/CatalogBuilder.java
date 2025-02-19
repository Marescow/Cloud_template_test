/*
 * Copyright (c) 2024 SAP SE or an SAP affiliate company. All rights reserved.
 */

package de.hybris.platform.integrationservices;

import de.hybris.platform.catalog.model.CatalogModel;
import de.hybris.platform.impex.jalo.ImpExException;
import de.hybris.platform.integrationservices.util.IntegrationTestUtil;

import java.util.HashSet;
import java.util.Set;

import org.junit.rules.ExternalResource;


/**
 * A helper class for fluent creation of catalog items in integration tests.
 * The created items are persisted in the database and cleaned automatically, if this builder is used as
 * a {@link org.junit.Rule} or {@link spock.lang.AutoCleanup} in the test.
 * <p>
 * This builder is implemented to satisfy only existing usages. If needed, more attribute specifications can be added.
 * </p>
 */
public class CatalogBuilder extends ExternalResource
{
	private static final String DEFAULT_ID = "Catalog";

	private final Set<CatalogModel> createdCatalogs;
	private String catalogId;
	private String name;

	/**
	 * Creates a new catalog builder initialized to default values. The catalog type will be set to "Catalog".
	 *
	 * @return a new catalog builder to be used for further specifications and creation of a catalog.
	 */
	public static CatalogBuilder catalog()
	{
		return new CatalogBuilder();
	}

	private CatalogBuilder()
	{
		createdCatalogs = new HashSet<>();
	}

	/**
	 * Specifies ID of the catalog to create. This value will be transformed to be specific to the test by querying the current naming context.
	 *
	 * @param id an ID of the catalog. If not specified, a default ID of {@code "<test name>_Catalog"} value is used.
	 * @return a builder with the ID specified
	 * @see NamingContext#testSpecific(String)
	 */
	public CatalogBuilder withId(final String id)
	{
		catalogId = id;
		return this;
	}

	/**
	 * Specifies name of the catalog to create.
	 *
	 * @param n a name of the catalog. If not specified, an empty value is used.
	 * @return a builder with the name specified
	 */
	public CatalogBuilder withName(final String n)
	{
		name = n;
		return this;
	}

	/**
	 * Creates a catalog with the specified attributes and persists it in the database.
	 *
	 * @return the created catalog model
	 * @throws ImpExException if an error occurs during the catalog creation
	 */
	public CatalogModel build() throws ImpExException
	{
		IntegrationTestUtil.importImpEx(
				"INSERT_UPDATE Catalog; id[unique=true]  ; name",
				"                     ;" + deriveId() + ";" + deriveName());
		final CatalogModel catalog = find();
		if (catalog != null)
		{
			createdCatalogs.add(catalog);
		}
		return catalog;
	}

	/**
	 * Searches for a persisted catalog that matches the specified catalog ID in this builder.
	 *
	 * @return a catalog that matches the specified catalog ID, or {@code null} if no such catalog is found.
	 */
	public CatalogModel find()
	{
		return IntegrationTestUtil.findAny(CatalogModel.class, this::match).orElse(null);
	}

	/**
	 * Deletes all catalogs created by this builder from the database.
	 */
	public void cleanup()
	{
		IntegrationTestUtil.removeAll(createdCatalogs);
		createdCatalogs.clear();
	}

	@Override
	protected void after()
	{
		cleanup();
	}

	private boolean match(final CatalogModel catalog)
	{
		return deriveId().equals(catalog.getId());
	}

	private String deriveId()
	{
		final String id = catalogId == null
				? DEFAULT_ID
				: catalogId;
		return NamingContext.testSpecific(id);
	}

	private String deriveName()
	{
		return name == null
				? ""
				: name;
	}
}
