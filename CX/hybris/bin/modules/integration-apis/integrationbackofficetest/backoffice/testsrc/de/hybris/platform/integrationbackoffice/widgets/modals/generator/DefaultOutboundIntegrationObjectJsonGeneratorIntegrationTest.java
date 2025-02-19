/*
 * Copyright (c) 2023 SAP SE or an SAP affiliate company. All rights reserved.
 */

package de.hybris.platform.integrationbackoffice.widgets.modals.generator;

import static de.hybris.platform.integrationservices.util.IntegrationTestUtil.importImpEx;

import static org.junit.Assert.assertEquals;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.impex.jalo.ImpExException;
import de.hybris.platform.integrationbackoffice.BeanRegisteringRule;
import de.hybris.platform.integrationservices.model.IntegrationObjectModel;
import de.hybris.platform.integrationservices.util.IntegrationObjectTestUtil;
import de.hybris.platform.integrationservices.util.IntegrationTestUtil;
import de.hybris.platform.servicelayer.ServicelayerTest;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;

@IntegrationTest
public class DefaultOutboundIntegrationObjectJsonGeneratorIntegrationTest extends ServicelayerTest
{
	private static final String JSON_GENERATOR_BEAN = "jsonGenerator";
	private static final String TEST_NAME = "IntegrationObjectOutboundJsonGenerator";
	private static final String READ_ONLY_JSON_IO = TEST_NAME + "_ProductIO_1";
	private static final String VA_JSON_IO = TEST_NAME + "_ProductIO_VA";
	private static final List<String> USED_IOs = List.of(READ_ONLY_JSON_IO, VA_JSON_IO);

	@Rule
	public BeanRegisteringRule rule = new BeanRegisteringRule()
			.register(BeanDefinitionBuilder.rootBeanDefinition(DefaultOutboundIntegrationObjectJsonGenerator.class)
			                               .addConstructorArgReference("integrationServicesDescriptorFactory"),
					JSON_GENERATOR_BEAN);

	private DefaultOutboundIntegrationObjectJsonGenerator jsonGenerator;

	@Before
	public void setUp()
	{
		jsonGenerator = (DefaultOutboundIntegrationObjectJsonGenerator) rule.getBean(
				DefaultOutboundIntegrationObjectJsonGenerator.class, JSON_GENERATOR_BEAN);
	}

	@After
	public void tearDown()
	{
		USED_IOs.forEach(objectCode -> IntegrationTestUtil.removeSafely(
				IntegrationObjectModel.class, it -> it.getCode().equals(objectCode)));
	}

	private void setReadOnlyJSONTest() throws ImpExException
	{
		importImpEx(
				"$ioCode=" + READ_ONLY_JSON_IO,
				"INSERT_UPDATE IntegrationObject; code[unique = true];",
				"                               ; $ioCode",
				"$io = integrationObject(code)[unique = true]",
				"INSERT_UPDATE IntegrationObjectItem; $io    ; code[unique = true]; type(code); root[default = false]",
				"                                   ; $ioCode; Catalog       ; Catalog       ; ;",
				"                                   ; $ioCode; Product       ; Product       ; true;",
				"                                   ; $ioCode; CatalogVersion; CatalogVersion; ;",

				"INSERT_UPDATE IntegrationObjectItemAttribute; integrationObjectItem(integrationObject(code), code)[unique = true]; attributeName[unique = true]; attributeDescriptor(enclosingType(code), qualifier); returnIntegrationObjectItem(integrationObject(code), code); unique[default = false]; autoCreate[default = false]",
				"                                            ; $ioCode:Catalog       ; id            ; Catalog:id            ;                     ; true;",
				"                                            ; $ioCode:Product       ; creationtime  ; Product:creationtime  ;                     ; ;",
				"                                            ; $ioCode:Product       ; code          ; Product:code          ;                     ; true;",
				"                                            ; $ioCode:Product       ; catalogVersion; Product:catalogVersion; $ioCode:CatalogVersion; true;",
				"                                            ; $ioCode:Product       ; modifiedtime  ; Product:modifiedtime  ;                     ; ;",
				"                                            ; $ioCode:CatalogVersion; version       ; CatalogVersion:version;                     ; true;",
				"                                            ; $ioCode:CatalogVersion; catalog       ; CatalogVersion:catalog; $ioCode:Catalog       ; true;"
		);
	}

	private void setVirtualAttributeJSONTest() throws ImpExException
	{
		importImpEx(
				"$ioCode=" + VA_JSON_IO,
				"INSERT_UPDATE IntegrationObject; code[unique = true];",
				"                               ; $ioCode",
				"$io = integrationObject(code)[unique = true]",
				"INSERT_UPDATE IntegrationObjectItem; $io    ; code[unique = true]; type(code); root[default = false]",
				"                                   ; $ioCode; Catalog       ; Catalog       ; ;",
				"                                   ; $ioCode; Product       ; Product       ; true;",
				"                                   ; $ioCode; CatalogVersion; CatalogVersion; ;",

				"INSERT_UPDATE IntegrationObjectItemAttribute; integrationObjectItem(integrationObject(code), code)[unique = true]; attributeName[unique = true]; attributeDescriptor(enclosingType(code), qualifier); returnIntegrationObjectItem(integrationObject(code), code); unique[default = false]; autoCreate[default = false]",
				"                                            ; $ioCode:Catalog       ; id            ; Catalog:id            ;                     ; true;",
				"                                            ; $ioCode:Product       ; creationtime  ; Product:creationtime  ;                     ; ;",
				"                                            ; $ioCode:Product       ; code          ; Product:code          ;                     ; true;",
				"                                            ; $ioCode:Product       ; catalogVersion; Product:catalogVersion; $ioCode:CatalogVersion; true;",
				"                                            ; $ioCode:Product       ; modifiedtime  ; Product:modifiedtime  ;                     ; ;",
				"                                            ; $ioCode:CatalogVersion; version       ; CatalogVersion:version;                     ; true;",
				"                                            ; $ioCode:CatalogVersion; catalog       ; CatalogVersion:catalog; $ioCode:Catalog       ; true;",

				"INSERT_UPDATE IntegrationObjectItemVirtualAttribute; integrationObjectItem(integrationObject(code), code)[unique = true]; attributeName[unique = true]; retrievalDescriptor(code)",
				"; $ioCode:Product       ; testVa; testVa",

				"INSERT_UPDATE IntegrationObjectVirtualAttributeDescriptor; code[unique = true]; logicLocation; type(code)",
				"; testVa; model://modelScriptForTest; java.lang.String "
		);
	}

	@Test
	public void readOnlyJsonTest() throws ImpExException
	{
		final var expectedJsonString = """
				{
				  "integrationKey": "TEST_KEY",
				  "creationtime": "/Date(1577836800000)/",
				  "modifiedtime": "/Date(1577836800000)/",
				  "code": "Test_code",
				  "catalogVersion": {
				    "integrationKey": "TEST_KEY",
				    "version": "Test_version",
				    "catalog": {
				      "integrationKey": "TEST_KEY",
				      "id": "Test_id"
				    }
				  }
				}
				""".trim();
		setReadOnlyJSONTest();
		final IntegrationObjectModel integrationObjectModel =
				IntegrationObjectTestUtil.findIntegrationObjectByCode(READ_ONLY_JSON_IO);

		final String actualJsonString = jsonGenerator.generateJson(integrationObjectModel);

		assertEquals(expectedJsonString, actualJsonString);
	}

	@Test
	public void virtualAttributeJsonTest() throws ImpExException
	{
		final var expectedJsonString = """
				{
				  "integrationKey": "TEST_KEY",
				  "testVa": "Test_testVa",
				  "creationtime": "/Date(1577836800000)/",
				  "modifiedtime": "/Date(1577836800000)/",
				  "code": "Test_code",
				  "catalogVersion": {
				    "integrationKey": "TEST_KEY",
				    "version": "Test_version",
				    "catalog": {
				      "integrationKey": "TEST_KEY",
				      "id": "Test_id"
				    }
				  }
				}
				""".trim();
		setVirtualAttributeJSONTest();
		final IntegrationObjectModel integrationObjectModel =
				IntegrationObjectTestUtil.findIntegrationObjectByCode(VA_JSON_IO);

		final String actualJsonString = jsonGenerator.generateJson(integrationObjectModel);

		assertEquals(expectedJsonString, actualJsonString);
	}
}
