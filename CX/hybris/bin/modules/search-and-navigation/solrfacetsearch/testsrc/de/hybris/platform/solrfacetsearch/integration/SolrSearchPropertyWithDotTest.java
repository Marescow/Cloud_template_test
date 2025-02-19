/*
 * Copyright (c) 2023 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.solrfacetsearch.integration;

import de.hybris.platform.solrfacetsearch.search.Facet;
import de.hybris.platform.solrfacetsearch.search.FacetSearchService;
import de.hybris.platform.solrfacetsearch.search.SearchQuery;
import de.hybris.platform.solrfacetsearch.search.SearchResult;

import de.hybris.platform.solrfacetsearch.config.FacetSearchConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.IndexedType;
import de.hybris.platform.solrfacetsearch.config.SearchConfig;

import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogVersionModel;

import de.hybris.platform.solrfacetsearch.indexer.impl.DefaultIndexerService;
import de.hybris.platform.solrfacetsearch.integration.AbstractIntegrationTest;

import de.hybris.bootstrap.annotations.IntegrationTest;

import java.util.Arrays;

import javax.annotation.Resource;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

@IntegrationTest
public class SolrSearchPropertyWithDotTest extends AbstractIntegrationTest {
    private static final String INDEXED_PROPERTY= "categ.ory";

    @Resource
    private CatalogVersionService catalogVersionService;

    @Resource
    private DefaultIndexerService indexerService;

    @Resource
    private FacetSearchService facetSearchService;

    @Override
    protected void loadData() throws Exception
    {
        importConfig("/test/integration/SolrSearchPropertyWithDotTest.csv");
    }

    @Test
    public void testPropertyWithDot() throws Exception {
        // given
        final FacetSearchConfig facetSearchConfig = getFacetSearchConfig();
        final IndexedType indexedType = facetSearchConfig.getIndexConfig().getIndexedTypes().values().iterator().next();
        final CatalogVersionModel hwOnline = catalogVersionService.getCatalogVersion(HW_CATALOG, ONLINE_CATALOG_VERSION);

        // when
        indexerService.performFullIndex(facetSearchConfig);

        final SearchQuery query = facetSearchService.createSearchQuery(facetSearchConfig, indexedType);
        query.setCatalogVersions(Arrays.asList(hwOnline));
        query.addFacet(INDEXED_PROPERTY);

        final SearchResult result = facetSearchService.search(query);
        final Facet facet = result.getFacet(INDEXED_PROPERTY);

        // then
        assertNotNull(facet);
        assertNotNull(facet.getFacetValues());
        assertEquals(INDEXED_PROPERTY, facet.getName());
        assertEquals(INDEXED_PROPERTY, facet.getDisplayName());
    }
}