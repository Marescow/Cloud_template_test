/*
 * Copyright (c) 2024 SAP SE or an SAP affiliate company. All rights reserved.
 */

package de.hybris.platform.integrationservices;

import de.hybris.platform.catalog.model.CatalogModel;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.impex.jalo.ImpExException;
import de.hybris.platform.integrationservices.util.IntegrationTestUtil;

import java.util.HashSet;
import java.util.Set;

import org.junit.rules.ExternalResource;


/**
 * A helper class for fluent creation of catalog version items in integration tests.
 * The created items are persisted in the database and cleaned automatically, if this builder is used as
 * a {@link org.junit.Rule} or {@link spock.lang.AutoCleanup} in the test.
 * <p>
 * This builder is implemented to satisfy only existing usages. If needed, more attribute specifications can be added.
 * </p>
 */
public class CatalogVersionBuilder extends ExternalResource
{
	private static final String DEFAULT_VERSION = "Test";

	private final Set<CatalogVersionModel> createdVersions;
	private final Set<CatalogBuilder> usedCatalogBuilders;
	private CatalogBuilder catalogBuilder;
	private String catalogVersion;

	/**
	 * Creates a new catalog version builder initialized to default values.
	 *
	 * @return a new catalog version builder to be used for further specifications and creation of a catalog version.
	 */
	public static CatalogVersionBuilder catalogVersion()
	{
		return new CatalogVersionBuilder();
	}

	private CatalogVersionBuilder()
	{
		createdVersions = new HashSet<>();
		usedCatalogBuilders = new HashSet<>();
	}

	/**
	 * Specifies version for the catalog version to create.
	 *
	 * @param v a version value for the catalog version being created. If not specified, a default value of {@code "Test"} will be used.
	 * @return a builder with the catalog version specified
	 */
	public CatalogVersionBuilder withVersion(final String v)
	{
		catalogVersion = v;
		return this;
	}

	/**
	 * Specifies catalog for the catalog version to create.
	 *
	 * @param id ID of the catalog to be associated with the catalog version being created.
	 * @return a builder with the catalog specified
	 */
	public CatalogVersionBuilder withCatalog(final String id)
	{
		getCatalogBuilder().withId(id);
		return this;
	}

	/**
	 * Specifies catalog for the catalog version to create.
	 *
	 * @param builder catalog specification to be associated with the catalog version being created.
	 * @return a builder with the catalog specified
	 */
	public CatalogVersionBuilder withCatalog(final CatalogBuilder builder)
	{
		catalogBuilder = builder;
		return this;
	}

	/**
	 * Creates a catalog version with the specified attribute values and persists it in the database.
	 *
	 * @return the created catalog version model
	 * @throws ImpExException if an error occurs during the import of the catalog version
	 */
	public CatalogVersionModel build() throws ImpExException
	{
		final String version = deriveVersion();
		final CatalogModel catalog = deriveCatalog();
		IntegrationTestUtil.importImpEx(
				"INSERT_UPDATE CatalogVersion; version[unique=true]; catalog(id)[unique=true]",
				"                            ; " + version + "     ; " + catalog.getId());
		final CatalogVersionModel created = find();
		if (created != null)
		{
			createdVersions.add(created);
		}
		return created;
	}

	/**
	 * Searches for a persisted catalog version that matches the specified catalog and version in this builder.
	 *
	 * @return a matching catalog version, or {@code null} if no such catalog version is found.
	 */
	public CatalogVersionModel find()
	{
		final String version = deriveVersion();
		final CatalogModel catalog = getCatalogBuilder().find();
		return IntegrationTestUtil.findAny(CatalogVersionModel.class,
						cv -> cv.getVersion().equals(version) && cv.getCatalog().equals(catalog))
				.orElse(null);
	}

	/**
	 * Deletes all catalogs created by this builder from the database.
	 */
	public void cleanup()
	{
		IntegrationTestUtil.removeAll(createdVersions);
		createdVersions.clear();
		usedCatalogBuilders.forEach(CatalogBuilder::cleanup);
		usedCatalogBuilders.clear();
	}

	@Override
	protected void before() throws ImpExException
	{
		build();
	}

	@Override
	protected void after()
	{
		cleanup();
	}

	private CatalogBuilder getCatalogBuilder()
	{
		if (catalogBuilder == null)
		{
			catalogBuilder = CatalogBuilder.catalog();
		}
		return catalogBuilder;
	}

	private String deriveVersion()
	{
		return catalogVersion == null ? DEFAULT_VERSION : catalogVersion;
	}

	private CatalogModel deriveCatalog() throws ImpExException
	{
		final CatalogBuilder builder = getCatalogBuilder();
		CatalogModel catalog = builder.find();
		if (catalog == null)
		{
			catalog = builder.build();
			usedCatalogBuilders.add(builder);
		}
		return catalog;
	}
}
