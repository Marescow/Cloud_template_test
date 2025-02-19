/*
 * Copyright (c) 2023 SAP SE or an SAP affiliate company. All rights reserved
 */
package com.hybris.backoffice.search.resolvers.impl;

import static org.assertj.core.api.Assertions.assertThat;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.catalog.enums.SyncItemStatus;
import de.hybris.platform.catalog.model.CatalogModel;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.core.Registry;
import de.hybris.platform.core.Tenant;
import de.hybris.platform.core.TenantAwareThreadFactory;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.product.UnitModel;
import de.hybris.platform.core.model.type.ComposedTypeModel;
import de.hybris.platform.impex.jalo.ImpExException;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.user.UserManager;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.impex.ImpExResource;
import de.hybris.platform.servicelayer.impex.impl.ClasspathImpExResource;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.util.Config;
import de.hybris.platform.variants.model.VariantTypeModel;

import javax.annotation.Resource;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.hybris.backoffice.constants.BackofficeConstants;
import com.hybris.backoffice.search.resolvers.LabelServiceForSearch;


/**
 * Integration test for LabelServiceForSearch service,
 * all created data by onSetup method will be roll-backed after once test case is over.
 */
@IntegrationTest
public class DefaultLabelServiceForSearchIntegrationTest extends ServicelayerTransactionalTest
{
	private static final Logger LOG = Logger.getLogger(DefaultLabelServiceForSearchIntegrationTest.class);

	@Resource(name = "labelServiceForSearch")
	private LabelServiceForSearch labelServiceForSearch;

	@Resource(name = "flexibleSearchService")
	private FlexibleSearchService flexibleSearchService;

	@Resource(name = "modelService")
	private ModelService modelService;

	/**
	 * Prepared test data for Catalog domain
	 */
	private final Map<String, ItemModel> preparedItemModels = new HashMap<>();

	@Before
	public void onSetup() throws Exception
	{
		preparedItemModels.clear();

		//Firstly, create core data. Such as: Language, Currency etc
		createCoreData();

		//Second, create all catalog data. Such as: Catalog, CatalogVersion etc
		createTestedCatalogData();
	}

	@Test
	public void isMatchingForGetDefinedLabelRules()
	{
		final Map<String, String> labelRule4Source = Config.getParametersByPattern(
				BackofficeConstants.BACKOFFICE_SEARCH_INDEX_LABEL_RULE_PATTERN);

		final Map<String, String> labelRules4Test = labelServiceForSearch.getDefinedLabelRules();

		assertThat(labelRules4Test).hasSameSizeAs(labelRule4Source);
		assertThat(labelRules4Test.values()).allMatch(labelRule4Source::containsValue);
	}

	@Test
	public void testGetObjectLabelForCatalog()
	{
		final Map<String, String> expectedLabelValuesByLocale = new HashMap<>();
		expectedLabelValuesByLocale.put(Locale.ENGLISH.getLanguage(), "testCatalog_en");
		expectedLabelValuesByLocale.put(Locale.GERMAN.getLanguage(), "testCatalog_de");
		expectedLabelValuesByLocale.put(Locale.FRENCH.getLanguage(), "testCatalog");

		testAndAssertForAllUseCases(CatalogModel._TYPECODE, expectedLabelValuesByLocale);
	}

	@Test
	public void testGetObjectLabelForCatalogVersion()
	{
		final Map<String, String> expectedLabelValuesByLocale = new HashMap<>();
		expectedLabelValuesByLocale.put(Locale.ENGLISH.getLanguage(), "testCatalog_en : Online");
		expectedLabelValuesByLocale.put(Locale.GERMAN.getLanguage(), "testCatalog_de : Online");
		expectedLabelValuesByLocale.put(Locale.FRENCH.getLanguage(), "testCatalog : Online");

		testAndAssertForAllUseCases(CatalogVersionModel._TYPECODE, expectedLabelValuesByLocale);
	}

	@Test
	public void testGetObjectLabelForCategory()
	{
		final Map<String, String> expectedLabelValuesByLocale = new HashMap<>();
		expectedLabelValuesByLocale.put(Locale.ENGLISH.getLanguage(), "testCategory0_en [testCategory0] - testCatalog_en : Online");
		expectedLabelValuesByLocale.put(Locale.GERMAN.getLanguage(), "testCategory0_de [testCategory0] - testCatalog_de : Online");
		expectedLabelValuesByLocale.put(Locale.FRENCH.getLanguage(), " [testCategory0] - testCatalog : Online");

		testAndAssertForAllUseCases(CategoryModel._TYPECODE, expectedLabelValuesByLocale);
	}

	@Test
	public void testGetObjectLabelForProduct()
	{
		final Map<String, String> expectedLabelValuesByLocale = new HashMap<>();
		expectedLabelValuesByLocale.put(Locale.ENGLISH.getLanguage(), "testProduct0_en [testProduct0] - testCatalog_en : Online");
		expectedLabelValuesByLocale.put(Locale.GERMAN.getLanguage(), "testProduct0_de [testProduct0] - testCatalog_de : Online");
		expectedLabelValuesByLocale.put(Locale.FRENCH.getLanguage(), " [testProduct0] - testCatalog : Online");

		testAndAssertForAllUseCases(ProductModel._TYPECODE, expectedLabelValuesByLocale);
	}

	@Test
	public void testGetObjectLabelForComposedType()
	{
		// Prepare data for ComposedType
		createTestedComposedTypeData();

		// Test it
		final Map<String, String> expectedLabelValuesByLocale = new HashMap<>();
		expectedLabelValuesByLocale.put(Locale.ENGLISH.getLanguage(), "testComposedType0_en [testComposedType0]");
		expectedLabelValuesByLocale.put(Locale.GERMAN.getLanguage(), "testComposedType0_de [testComposedType0]");
		expectedLabelValuesByLocale.put(Locale.FRENCH.getLanguage(), " [testComposedType0]");

		testAndAssertForAllUseCases(ComposedTypeModel._TYPECODE, expectedLabelValuesByLocale);
	}

	@Test
	public void testGetObjectLabelForSubType()
	{
		// Prepare data for VariantType
		createTestedVariantTypeData();

		// Test it. Note: VariantType parent is ComposedType, so label rule is same with ComposedType model
		final Map<String, String> expectedLabelValuesByLocale = new HashMap<>();
		expectedLabelValuesByLocale.put(Locale.ENGLISH.getLanguage(), "testVariantType_en [testVariantType0]");
		expectedLabelValuesByLocale.put(Locale.GERMAN.getLanguage(), "testVariantType_de [testVariantType0]");
		expectedLabelValuesByLocale.put(Locale.FRENCH.getLanguage(), " [testVariantType0]");

		testAndAssertForAllUseCases(VariantTypeModel._TYPECODE, expectedLabelValuesByLocale);
	}

	@Test
	public void testInputObjectIsNull()
	{
		assertThat(labelServiceForSearch.getObjectLabel(null)).isEqualTo(BackofficeConstants.BACKOFFICE_LABEL_VALUE_IS_NULL);
	}

	@Test
	public void testHybrisEnumValueType()
	{
		String labelValue = labelServiceForSearch.getObjectLabel(SyncItemStatus.IN_SYNC, Locale.ENGLISH);
		assertThat(labelValue).isNull();

		labelValue = labelServiceForSearch.getObjectLabel(SyncItemStatus.IN_SYNC);
		assertThat(labelValue).isNull();
	}

	@Test
	public void testOtherType()
	{
		final String testObject = String.valueOf(10);
		assertThat(labelServiceForSearch.getObjectLabel(testObject, Locale.FRENCH)).isEqualTo(testObject);
	}

	@Test
	public void testIllegalLabelRule() {
		final String keyOfNewLabelRule =
				BackofficeConstants.BACKOFFICE_SEARCH_INDEX_LABEL_RULE_PATTERN + UnitModel._TYPECODE;
		final String labelRule = "(invalidQualifier?:'')+' ['+code+']'";
		Config.setParameter(keyOfNewLabelRule, labelRule);
		labelServiceForSearch.getDefinedLabelRules();

		final UnitModel unitModel = modelService.create(UnitModel._TYPECODE);
		unitModel.setCode("testCode");
		final String labelValue = labelServiceForSearch.getObjectLabel(unitModel);
		assertThat(labelValue).isNull();
	}

	/**
	 * Optional Test Case and disable this case by default.
	 * Note: in general, should using ServicelayerTest to replace ServicelayerTransactionalTest when enable this case,
	 * because class ServicelayerTransactionalTest defined Transactional annotation and all data be roll backed after one test case is ended.
	 * But due to testLabelValuesOnMultiThreadEnv method used multi-threads to ran test case,
	 * then it led to multi-transactions for different tested thread. So prepared data must be committed firstly.
	 *
	 * @throws InterruptedException
	 */
	//@Test
	public void testLabelValuesOnMultiThreadEnv() throws InterruptedException
	{
		final int nCountOfThread = 5;
		final CountDownLatch terminatedSemaphore = new CountDownLatch(nCountOfThread);
		final Tenant currentTenant = Registry.getCurrentTenant();
		final TenantAwareThreadFactory threadFactory = new TenantAwareThreadFactory(currentTenant);

		int nIndex = 0;
		while (nIndex < nCountOfThread)
		{
			Thread newThread = currentTenant.createAndRegisterBackgroundThread((() -> {
				testInputObjectIsNull();
				testHybrisEnumValueType();
				testOtherType();
				isMatchingForGetDefinedLabelRules();
				testGetObjectLabelForCatalog();
				testGetObjectLabelForCatalogVersion();
				testGetObjectLabelForCategory();
				testGetObjectLabelForProduct();
				testGetObjectLabelForComposedType();
				testGetObjectLabelForSubType();
				testIllegalLabelRule();
				terminatedSemaphore.countDown();
			}), threadFactory);
			newThread.setName(String.format("TestLabelValuesOnMultiThreadEnv - %d", nIndex));
			newThread.start();
			nIndex++;
		}

		terminatedSemaphore.await();
	}

	private void testAndAssertForAllUseCases(final String itemType, final Map<String, String> expectedLabelValuesByLocale)
	{
		final ItemModel itemModel = preparedItemModels.get(itemType);
		assertThat(itemModel).isNotNull();

		String labelValue = labelServiceForSearch.getObjectLabel(itemModel, Locale.ENGLISH);
		assertThat(labelValue).isNotNull().isEqualTo(expectedLabelValuesByLocale.get(Locale.ENGLISH.getLanguage()));

		labelValue = labelServiceForSearch.getObjectLabel(itemModel, Locale.GERMAN);
		assertThat(labelValue).isNotNull().isEqualTo(expectedLabelValuesByLocale.get(Locale.GERMAN.getLanguage()));

		labelValue = labelServiceForSearch.getObjectLabel(itemModel, Locale.FRENCH);
		assertThat(labelValue).isNotNull().isEqualTo(expectedLabelValuesByLocale.get(Locale.FRENCH.getLanguage()));

		labelValue = labelServiceForSearch.getObjectLabel(itemModel);
		assertThat(labelValue).isNotNull().isEqualTo(expectedLabelValuesByLocale.get(Locale.ENGLISH.getLanguage()));
	}

	private void createTestedCatalogData() throws ImpExException
	{
		LOG.info("Creating test catalog...");

		JaloSession.getCurrentSession().setUser(UserManager.getInstance().getAdminEmployee());
		final long startTime = System.currentTimeMillis();

		final ImpExResource testCatalogData = new ClasspathImpExResource("/test/catalog/catalogs.impex",
				StandardCharsets.UTF_8.name());
		importData(testCatalogData);

		// checking imported stuff
		final CatalogModel catalog = flexibleSearchService.<CatalogModel>search(
				"SELECT {PK} FROM {Catalog} WHERE {id}='testCatalog'").getResult().get(0);
		assertThat(catalog).isNotNull();
		preparedItemModels.put(catalog.getItemtype(), catalog);

		final CatalogVersionModel version = flexibleSearchService.<CatalogVersionModel>search(
				"SELECT {PK} FROM {CatalogVersion} WHERE {version}='Online' AND {catalog}=?catalog",
				Collections.singletonMap("catalog", catalog)).getResult().get(0);
		assertThat(version).isNotNull();
		preparedItemModels.put(version.getItemtype(), version);

		// setting catalog to session and admin user
		JaloSession.getCurrentSession().getSessionContext()
				.setAttribute("catalogversions", modelService.toPersistenceLayer(Collections.singletonList(version)));

		final CategoryModel category = flexibleSearchService.<CategoryModel>search(
				"SELECT {PK} FROM {Category} WHERE {code}='testCategory0'").getResult().get(0);
		assertThat(category).isNotNull();
		preparedItemModels.put(category.getItemtype(), category);

		final ProductModel product = flexibleSearchService.<ProductModel>search(
				"SELECT {PK} FROM {Product} WHERE {code}='testProduct0'").getResult().get(0);
		assertThat(product).isNotNull();
		assertThat(product.getSupercategories()).contains(category);
		preparedItemModels.put(product.getItemtype(), product);

		LOG.info("Finished creating test catalog in " + (System.currentTimeMillis() - startTime) + "ms");
	}

	private void createTestedComposedTypeData()
	{
		final ComposedTypeModel composedTypeModel = modelService.create(ComposedTypeModel._TYPECODE);
		composedTypeModel.setCode("testComposedType0");
		composedTypeModel.setName("testComposedType0_en", Locale.ENGLISH);
		composedTypeModel.setName("testComposedType0_de", Locale.GERMAN);
		composedTypeModel.setName(null, Locale.FRENCH);
		preparedItemModels.put(composedTypeModel.getItemtype(), composedTypeModel);
	}

	private void createTestedVariantTypeData()
	{
		final VariantTypeModel variantTypeModel = modelService.create(VariantTypeModel._TYPECODE);
		variantTypeModel.setCode("testVariantType0");
		variantTypeModel.setName("testVariantType_en", Locale.ENGLISH);
		variantTypeModel.setName("testVariantType_de", Locale.GERMAN);
		variantTypeModel.setName(null, Locale.FRENCH);
		preparedItemModels.put(variantTypeModel.getItemtype(), variantTypeModel);
	}
}
