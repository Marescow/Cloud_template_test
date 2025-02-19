/*
 * Copyright (c) 2022 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.cms2lib.components;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.cms2lib.model.components.ProductCarouselComponentModel;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.model.ModelService;

import javax.annotation.Resource;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@IntegrationTest
public class ProductCarouselComponentIntegrationTest extends ServicelayerTransactionalTest
{

	@Resource
	private FlexibleSearchService flexibleSearchService;

	@Resource
	private ModelService modelService;


	@Before
	public void setUp() throws Exception
	{
		importCsv("/test/productCarouseComponentTestData.impex", "UTF-8");
	}

	@Test
	public void shouldBeConsistentAboutOrderOfProductCodesAndOrderOfProducts()
	{
		// given
		final String componentUid = "productCarouselAA";
		final Map<String, String> queryParams = new HashMap<>();
		queryParams.put(ProductCarouselComponentModel.UID, componentUid);

		// when
		final SearchResult<ProductCarouselComponentModel> results = flexibleSearchService.search(
						"SELECT {" + ProductCarouselComponentModel.PK + "} " + "FROM {" + ProductCarouselComponentModel._TYPECODE + "} "
								+ "WHERE {" + ProductCarouselComponentModel.UID + "} = ?" + ProductCarouselComponentModel.UID, queryParams);
		final ProductCarouselComponentModel productCarouselComponentModel = results.getResult().get(0);

		// then
		assertEquals("productAA", productCarouselComponentModel.getProductCodes().get(0));
		assertEquals("productBB", productCarouselComponentModel.getProductCodes().get(1));
		assertEquals("productCC", productCarouselComponentModel.getProductCodes().get(2));

		// when
		final List<ProductModel> productList = productCarouselComponentModel.getProducts();
		final List<ProductModel> newProductList = new ArrayList<ProductModel>(productList.size());
		newProductList.add(productList.get(1));
		newProductList.add(productList.get(2));
		newProductList.add(productList.get(0));
		productCarouselComponentModel.setProducts(newProductList);
		modelService.save(productCarouselComponentModel);
		modelService.refresh(productCarouselComponentModel);

		// then
		assertEquals("productBB", productCarouselComponentModel.getProductCodes().get(0));
		assertEquals("productCC", productCarouselComponentModel.getProductCodes().get(1));
		assertEquals("productAA", productCarouselComponentModel.getProductCodes().get(2));
	}

}