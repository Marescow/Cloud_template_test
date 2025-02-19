/*
 * Copyright (c) 2023 SAP SE or an SAP affiliate company. All rights reserved.
 */

package de.hybris.platform.integrationbackoffice.widgets.modals.generator;

import static de.hybris.platform.integrationservices.util.IntegrationTestUtil.importImpEx;

import static org.junit.Assert.assertEquals;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.catalog.model.classification.ClassAttributeAssignmentModel;
import de.hybris.platform.catalog.model.classification.ClassificationAttributeModel;
import de.hybris.platform.catalog.model.classification.ClassificationAttributeUnitModel;
import de.hybris.platform.catalog.model.classification.ClassificationClassModel;
import de.hybris.platform.catalog.model.classification.ClassificationSystemModel;
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
public class DefaultPersistenceIntegrationObjectJsonGeneratorIntegrationTest extends ServicelayerTest
{
	private static final String JSON_GENERATOR = "jsonGenerator";
	private static final String TEST_NAME = "DefaultIntegrationObjectJsonGenerator";
	private static final String BASIC_JSON_IO = TEST_NAME + "_ProductIO_1";
	private static final String MAP_JSON_IO = TEST_NAME + "_ProductIO_2";
	private static final String COLLECTION_JSON_IO = TEST_NAME + "_ProductIO_3";
	private static final String MAP_NOT_SUPPORTED_JSON_IO = TEST_NAME + "_ProductIO_4";
	private static final String CIRCULAR_DEPENDENCY_JSON_IO = TEST_NAME + "_OrderIO_1";
	private static final String CIRCULAR_DEPENDENCY_COMPLEX_JSON_IO = TEST_NAME + "_OrderIO_2";
	private static final String PRODUCT_CLASSIFICATION_IO = TEST_NAME + "_ProductIO_5";
	private static final String PRODUCT_CLASSIFICATION_ENUM_REF_AND_MULTIVALUE_IO = TEST_NAME + "_ProductIO_6";
	private static final String READ_ONLY_JSON_IO = TEST_NAME + "_ProductIO_7";
	private static final String VA_JSON_IO = TEST_NAME + "_ProductIO_8";
	private static final String MULTI_TYPE_DEFINITION_IO = TEST_NAME + "_CategoryIO_1";
	private static final List<String> USED_IOs = List.of(BASIC_JSON_IO, MAP_JSON_IO, COLLECTION_JSON_IO,
			MAP_NOT_SUPPORTED_JSON_IO, CIRCULAR_DEPENDENCY_JSON_IO, CIRCULAR_DEPENDENCY_COMPLEX_JSON_IO,
			PRODUCT_CLASSIFICATION_IO, PRODUCT_CLASSIFICATION_ENUM_REF_AND_MULTIVALUE_IO, READ_ONLY_JSON_IO,
			VA_JSON_IO, MULTI_TYPE_DEFINITION_IO);
	private static final String CLASSIFICATION_SYSTEM = TEST_NAME + "_Electronics";

	@Rule
	public BeanRegisteringRule rule = new BeanRegisteringRule()
			.register(BeanDefinitionBuilder.rootBeanDefinition(DefaultPersistenceIntegrationObjectJsonGenerator.class)
			                               .addConstructorArgReference("integrationServicesDescriptorFactory")
			                               .addConstructorArgReference("modelService")
			                               .addConstructorArgReference("defaultIntegrationServicesConfiguration"),
					JSON_GENERATOR);

	private DefaultPersistenceIntegrationObjectJsonGenerator jsonGenerator;

	@Before
	public void setUp()
	{
		jsonGenerator = (DefaultPersistenceIntegrationObjectJsonGenerator) rule.getBean(
				DefaultPersistenceIntegrationObjectJsonGenerator.class, JSON_GENERATOR);
	}

	@After
	public void tearDown()
	{
		USED_IOs.forEach(objectCode -> IntegrationTestUtil.removeSafely(IntegrationObjectModel.class,
				it -> it.getCode().equals(objectCode)));

		IntegrationTestUtil.remove(ClassAttributeAssignmentModel.class,
				it -> it.getClassificationClass().getCatalogVersion().getCatalog().getId().equals(CLASSIFICATION_SYSTEM));
		IntegrationTestUtil.remove(ClassificationAttributeModel.class,
				it -> it.getSystemVersion().getCatalog().getId().equals(CLASSIFICATION_SYSTEM));
		IntegrationTestUtil.remove(ClassificationAttributeUnitModel.class,
				it -> it.getSystemVersion().getCatalog().getId().equals(CLASSIFICATION_SYSTEM));
		IntegrationTestUtil.remove(ClassificationClassModel.class,
				it -> it.getCatalogVersion().getCatalog().getId().equals(CLASSIFICATION_SYSTEM));
		IntegrationTestUtil.remove(ClassificationSystemModel.class, it -> it.getId().equals(CLASSIFICATION_SYSTEM));
	}

	private IntegrationObjectModel setBasicJSONTest() throws ImpExException
	{
		importImpEx(
				"$ioCode=" + BASIC_JSON_IO,
				"INSERT_UPDATE IntegrationObject; code[unique = true];",
				"                               ; $ioCode",
				"$io = integrationObject(code)[unique = true]",
				"INSERT_UPDATE IntegrationObjectItem; $io    ; code[unique = true]; type(code)    ; root[default = false]",
				"                                   ; $ioCode; CatalogVersion; CatalogVersion; ;",
				"                                   ; $ioCode; Product       ; Product       ; true;",
				"                                   ; $ioCode; Catalog       ; Catalog       ; ;",
				"$integrationItem = integrationObjectItem(integrationObject(code), code)[unique = true]",
				"$attrName = attributeName[unique = true]",
				"$attrDescriptor = attributeDescriptor(enclosingType(code), qualifier)",
				"$attributeType=returnIntegrationObjectItem(integrationObject(code), code)",
				"INSERT_UPDATE IntegrationObjectItemAttribute; $integrationItem      ; $attrName         ; $attrDescriptor           ; $attributeType        ; unique[default = false]",
				"                                            ; $ioCode:CatalogVersion; active            ; CatalogVersion:active     ;                       ;     ",
				"                                            ; $ioCode:CatalogVersion; catalog           ; CatalogVersion:catalog    ; $ioCode:Catalog       ; true",
				"                                            ; $ioCode:CatalogVersion; version           ; CatalogVersion:version    ;                       ; true",
				"                                            ; $ioCode:Product       ; manufacturerAID   ; Product:manufacturerAID   ;                       ;     ",
				"                                            ; $ioCode:Product       ; numberContentUnits; Product:numberContentUnits;                       ;     ",
				"                                            ; $ioCode:Product       ; catalogVersion    ; Product:catalogVersion    ; $ioCode:CatalogVersion; true",
				"                                            ; $ioCode:Product       ; offlineDate       ; Product:offlineDate       ;                       ;     ",
				"                                            ; $ioCode:Product       ; code              ; Product:code              ;                       ; true",
				"                                            ; $ioCode:Catalog       ; id                ; Catalog:id                ;                       ; true"
		);
		return IntegrationObjectTestUtil.findIntegrationObjectByCode(BASIC_JSON_IO);
	}

	private IntegrationObjectModel setMapJSONTest() throws ImpExException
	{
		importImpEx(
				"$ioCode=" + MAP_JSON_IO,
				"INSERT_UPDATE IntegrationObject; code[unique = true];",
				"                               ; $ioCode",
				"$io = integrationObject(code)[unique = true]",
				"INSERT_UPDATE IntegrationObjectItem; $io    ; code[unique = true]; type(code)    ; root[default = false]",
				"                                   ; $ioCode; Catalog            ; Catalog       ;     ",
				"                                   ; $ioCode; CatalogVersion     ; CatalogVersion;     ",
				"                                   ; $ioCode; Product            ; Product       ; true",
				"$integrationItem = integrationObjectItem(integrationObject(code), code)[unique = true]",
				"$attrName = attributeName[unique = true]",
				"$attrDescriptor = attributeDescriptor(enclosingType(code), qualifier)",
				"$attributeType=returnIntegrationObjectItem(integrationObject(code), code)",
				"INSERT_UPDATE IntegrationObjectItemAttribute; $integrationItem      ; $attrName     ; $attrDescriptor       ; $attributeType        ; unique[default = false]",
				"                                            ; $ioCode:Catalog       ; id            ; Catalog:id            ;                       ; true",
				"                                            ; $ioCode:CatalogVersion; catalog       ; CatalogVersion:catalog; $ioCode:Catalog       ; true",
				"                                            ; $ioCode:CatalogVersion; version       ; CatalogVersion:version;                       ; true",
				"                                            ; $ioCode:Product       ; catalogVersion; Product:catalogVersion; $ioCode:CatalogVersion; true",
				"                                            ; $ioCode:Product       ; code          ; Product:code          ;                       ; true",
				"                                            ; $ioCode:Product       ; description   ; Product:description   ;                       ;     ",
				"                                            ; $ioCode:Product       ; deliveryTime  ; Product:deliveryTime  ;                       ;     "
		);
		return IntegrationObjectTestUtil.findIntegrationObjectByCode(MAP_JSON_IO);
	}

	private IntegrationObjectModel setCollectionJSONTest() throws ImpExException
	{
		importImpEx(
				"$ioCode=" + COLLECTION_JSON_IO,
				"INSERT_UPDATE IntegrationObject; code[unique = true];",
				"                               ; $ioCode",
				"$io = integrationObject(code)[unique = true]",
				"INSERT_UPDATE IntegrationObjectItem; $io    ; code[unique = true]; type(code)    ; root[default = false]",
				"                                   ; $ioCode; DeliveryMode       ; DeliveryMode  ;     ",
				"                                   ; $ioCode; CatalogVersion     ; CatalogVersion;     ",
				"                                   ; $ioCode; Media              ; Media         ;     ",
				"                                   ; $ioCode; Catalog            ; Catalog       ;     ",
				"                                   ; $ioCode; Product            ; Product       ; true",
				"$integrationItem = integrationObjectItem(integrationObject(code), code)[unique = true]",
				"$attrName = attributeName[unique = true]",
				"$attrDescriptor = attributeDescriptor(enclosingType(code), qualifier)",
				"$attributeType=returnIntegrationObjectItem(integrationObject(code), code)",
				"INSERT_UPDATE IntegrationObjectItemAttribute; $integrationItem      ; $attrName     ; $attrDescriptor       ; $attributeType        ; unique[default = false]",
				"                                            ; $ioCode:DeliveryMode  ; code          ; DeliveryMode:code     ;                       ; true",
				"                                            ; $ioCode:CatalogVersion; version       ; CatalogVersion:version;                       ; true",
				"                                            ; $ioCode:CatalogVersion; catalog       ; CatalogVersion:catalog; $ioCode:Catalog       ; true",
				"                                            ; $ioCode:Media         ; catalogVersion; Media:catalogVersion  ; $ioCode:CatalogVersion; true",
				"                                            ; $ioCode:Media         ; code          ; Media:code            ;                       ; true",
				"                                            ; $ioCode:Catalog       ; id            ; Catalog:id            ;                       ; true",
				"                                            ; $ioCode:Product       ; code          ; Product:code          ;                       ; true",
				"                                            ; $ioCode:Product       ; catalogVersion; Product:catalogVersion; $ioCode:CatalogVersion; true",
				"                                            ; $ioCode:Product       ; data_sheet    ; Product:data_sheet    ; $ioCode:Media         ;     ",
				"                                            ; $ioCode:Product       ; deliveryTime  ; Product:deliveryTime  ;                       ;     ",
				"                                            ; $ioCode:Product       ; deliveryModes ; Product:deliveryModes ; $ioCode:DeliveryMode  ;     "
		);
		return IntegrationObjectTestUtil.findIntegrationObjectByCode(COLLECTION_JSON_IO);
	}

	private IntegrationObjectModel setMapNotSupportedJSONTest() throws ImpExException
	{
		importImpEx(
				"$ioCode=" + MAP_NOT_SUPPORTED_JSON_IO,
				"INSERT_UPDATE IntegrationObject; code[unique = true];",
				"                               ; $ioCode",
				"$io = integrationObject(code)[unique = true]",
				"INSERT_UPDATE IntegrationObjectItem; $io    ; code[unique = true]; type(code); root[default = false]",
				"                                   ; $ioCode; Product       ; Product        ; true",
				"                                   ; $ioCode; Catalog       ; Catalog        ;     ",
				"                                   ; $ioCode; CatalogVersion; CatalogVersion ;     ",
				"                                   ; $ioCode; ArticleStatus ; ArticleStatus  ;     ",
				"$integrationItem = integrationObjectItem(integrationObject(code), code)[unique = true]",
				"$attrName = attributeName[unique = true]",
				"$attrDescriptor = attributeDescriptor(enclosingType(code), qualifier)",
				"$attributeType=returnIntegrationObjectItem(integrationObject(code), code)",
				"INSERT_UPDATE IntegrationObjectItemAttribute[disable.interceptor.types=validate]; $integrationItem; $attrName    ; $attrDescriptor      ; $attributeType       ; unique",
				"                                                                                ; $ioCode:Product ; articleStatus; Product:articleStatus; $ioCode:ArticleStatus; false",
				"INSERT_UPDATE IntegrationObjectItemAttribute; $integrationItem      ; $attrName     ; $attrDescriptor              ; $attributeType         ; unique[default = false]",
				"                                            ; $ioCode:Product       ; code          ; Product:code                 ;                        ; true",
				"                                            ; $ioCode:Product       ; catalogVersion; Product:catalogVersion       ; $ioCode:CatalogVersion ; true",
				"                                            ; $ioCode:Catalog       ; id            ; Catalog:id                   ;                        ; true",
				"                                            ; $ioCode:CatalogVersion; generationDate; CatalogVersion:generationDate;                        ;     ",
				"                                            ; $ioCode:CatalogVersion; version       ; CatalogVersion:version       ;                        ; true",
				"                                            ; $ioCode:CatalogVersion; generatorInfo ; CatalogVersion:generatorInfo ;                        ;     ",
				"                                            ; $ioCode:CatalogVersion; inclAssurance ; CatalogVersion:inclAssurance ;                        ;     ",
				"                                            ; $ioCode:CatalogVersion; catalog       ; CatalogVersion:catalog       ; $ioCode:Catalog        ; true",
				"                                            ; $ioCode:ArticleStatus ; code          ; ArticleStatus:code           ;                        ; true"
		);
		return IntegrationObjectTestUtil.findIntegrationObjectByCode(MAP_NOT_SUPPORTED_JSON_IO);
	}

	private IntegrationObjectModel setCircularDependencyJSONTest() throws ImpExException
	{
		importImpEx(
				"$ioCode=" + CIRCULAR_DEPENDENCY_JSON_IO,
				"INSERT_UPDATE IntegrationObject; code[unique = true];",
				"                               ; $ioCode",
				"$io = integrationObject(code)[unique = true]",
				"INSERT_UPDATE IntegrationObjectItem; $io    ; code[unique = true]; type(code); root[default = false]",
				"                                   ; $ioCode; OrderEntry         ; OrderEntry;     ",
				"                                   ; $ioCode; Order              ; Order     ; true ",
				"$integrationItem = integrationObjectItem(integrationObject(code), code)[unique = true]",
				"$attrName = attributeName[unique = true]",
				"$attrDescriptor = attributeDescriptor(enclosingType(code), qualifier)",
				"$attributeType=returnIntegrationObjectItem(integrationObject(code), code)",
				"INSERT_UPDATE IntegrationObjectItemAttribute; $integrationItem  ; $attrName; $attrDescriptor ; $attributeType    ; unique[default = false]",
				"                                            ; $ioCode:OrderEntry; order    ; OrderEntry:order; $ioCode:Order     ; true",
				"                                            ; $ioCode:Order     ; entries  ; Order:entries   ; $ioCode:OrderEntry;     ",
				"                                            ; $ioCode:Order     ; code     ; Order:code      ;                   ; true"
		);
		return IntegrationObjectTestUtil.findIntegrationObjectByCode(CIRCULAR_DEPENDENCY_JSON_IO);
	}

	private IntegrationObjectModel setCircularDependencyComplexJSONTest() throws ImpExException
	{
		importImpEx(
				"$ioCode=" + CIRCULAR_DEPENDENCY_COMPLEX_JSON_IO,
				"INSERT_UPDATE IntegrationObject; code[unique = true];",
				"                               ; $ioCode",
				"$io = integrationObject(code)[unique = true]",
				"INSERT_UPDATE IntegrationObjectItem; $io    ; code[unique = true]; type(code); root[default = false]",
				"                                   ; $ioCode; Order              ; Order     ; true",
				"                                   ; $ioCode; User               ; User      ;     ",
				"                                   ; $ioCode; OrderEntry         ; OrderEntry;     ",
				"$integrationItem = integrationObjectItem(integrationObject(code), code)[unique = true]",
				"$attrName = attributeName[unique = true]",
				"$attrDescriptor = attributeDescriptor(enclosingType(code), qualifier)",
				"$attributeType=returnIntegrationObjectItem(integrationObject(code), code)",
				"INSERT_UPDATE IntegrationObjectItemAttribute; $integrationItem  ; $attrName      ; $attrDescriptor      ; $attributeType    ; unique[default = false]",
				"                                            ; $ioCode:Order     ; entries        ; Order:entries        ; $ioCode:OrderEntry;     ",
				"                                            ; $ioCode:Order     ; user           ; Order:user           ; $ioCode:User      ; true",
				"                                            ; $ioCode:Order     ; originalVersion; Order:originalVersion; $ioCode:Order     ;     ",
				"                                            ; $ioCode:Order     ; placedBy       ; Order:placedBy       ; $ioCode:User      ;     ",
				"                                            ; $ioCode:User      ; uid            ; User:uid             ;                   ; true",
				"                                            ; $ioCode:OrderEntry; order          ; OrderEntry:order     ; $ioCode:Order     ; true"
		);
		return IntegrationObjectTestUtil.findIntegrationObjectByCode(CIRCULAR_DEPENDENCY_COMPLEX_JSON_IO);
	}

	private IntegrationObjectModel setProductIOClassificationTest() throws ImpExException
	{
		importImpEx(
				"# The following ImpEx provides a full example of",
				"# 1) Creating classifications",
				"# 2) Creating Integration Object with classification attributes",
				"# ***************************************************************************************",
				"#     Set up the classifications",
				"# ***************************************************************************************",
				"$SYSTEM=" + CLASSIFICATION_SYSTEM,
				"$VERSION=Staged",
				"$SYSTEM_VERSION=$SYSTEM:$VERSION",
				"$catalogVersionHeader=catalogVersion(catalog(id), version)",
				"$systemVersionHeader=systemVersion(catalog(id), version)",
				"INSERT_UPDATE ClassificationSystem; id[unique = true]",
				"                                  ; $SYSTEM",
				"INSERT_UPDATE ClassificationSystemVersion; catalog(id)[unique = true]; version[unique = true]",
				"                                         ; $SYSTEM                   ; $VERSION",
				"INSERT_UPDATE ClassificationClass; code[unique = true]; $catalogVersionHeader[unique = true]",
				"                                 ; dimensions         ; $SYSTEM_VERSION",
				"INSERT_UPDATE ClassificationAttributeUnit; $systemVersionHeader[unique = true]; code[unique = true]; symbol; unitType",
				"                                         ; $SYSTEM_VERSION                    ; centimeters        ; cm    ; measurement",
				"INSERT_UPDATE ClassificationAttribute; code[unique = true]; $systemVersionHeader[unique = true]",
				"                                     ; height             ; $SYSTEM_VERSION",
				"                                     ; width              ; $SYSTEM_VERSION",
				"                                     ; depth              ; $SYSTEM_VERSION",
				"$class=classificationClass($catalogVersionHeader, code)",
				"$attribute=classificationAttribute($systemVersionHeader, code)",
				"INSERT_UPDATE ClassAttributeAssignment; $class[unique = true]     ; $attribute[unique = true]; unit($systemVersionHeader, code); attributeType(code)",
				"                                      ; $SYSTEM_VERSION:dimensions; $SYSTEM_VERSION:height   ; $SYSTEM_VERSION:centimeters     ; number",
				"                                      ; $SYSTEM_VERSION:dimensions; $SYSTEM_VERSION:width    ; $SYSTEM_VERSION:centimeters     ; number",
				"                                      ; $SYSTEM_VERSION:dimensions; $SYSTEM_VERSION:depth    ; $SYSTEM_VERSION:centimeters     ; number",
				"# ***************************************************************************************",
				"#     Set up integration objects with regular attributes and classification attributes",
				"# ***************************************************************************************",
				"$ioCode=" + PRODUCT_CLASSIFICATION_IO,
				"INSERT_UPDATE IntegrationObject; code[unique = true];",
				"                               ; $ioCode",
				"$io = integrationObject(code)[unique = true]",
				"INSERT_UPDATE IntegrationObjectItem; $io    ; code[unique = true]; type(code); root[default = false]",
				"                                   ; $ioCode                                   ; Product            ; Product   ; true",
				"                                   ; $ioCode                                   ; Catalog            ; Catalog",
				"                                   ; $ioCode                                   ; CatalogVersion     ; CatalogVersion",
				"$item=integrationObjectItem(integrationObject(code), code)",
				"$descriptor=attributeDescriptor(enclosingType(code), qualifier)",
				"$attributeType=returnIntegrationObjectItem(integrationObject(code), code)",
				"INSERT_UPDATE IntegrationObjectItemAttribute; $item[unique = true]  ; attributeName[unique = true]; $descriptor           ; $attributeType;",
				"                                            ; $ioCode:Product       ; code                        ; Product:code",
				"                                            ; $ioCode:Product       ; catalogVersion              ; Product:catalogVersion; $ioCode:CatalogVersion",
				"                                            ; $ioCode:CatalogVersion; version                     ; CatalogVersion:version",
				"                                            ; $ioCode:CatalogVersion; catalog                     ; CatalogVersion:catalog; $ioCode:Catalog",
				"                                            ; $ioCode:Catalog       ; id                          ; Catalog:id",
				"$SYSTEM_VERSION=" + CLASSIFICATION_SYSTEM + ":Staged",
				"$item=integrationObjectItem(integrationObject(code), code)",
				"$systemVersionHeader=systemVersion(catalog(id), version)",
				"$classificationClassHeader=classificationClass(catalogVersion(catalog(id), version), code)",
				"$classificationAttributeHeader=classificationAttribute($systemVersionHeader, code)",
				"$classificationAssignment=classAttributeAssignment($classificationClassHeader, $classificationAttributeHeader)",
				"INSERT_UPDATE IntegrationObjectItemClassificationAttribute; $item[unique = true]; attributeName[unique = true]; $classificationAssignment",
				"                                                          ; $ioCode:Product     ; height                      ; $SYSTEM_VERSION:dimensions:$SYSTEM_VERSION:height",
				"                                                          ; $ioCode:Product     ; depth                       ; $SYSTEM_VERSION:dimensions:$SYSTEM_VERSION:depth",
				"                                                          ; $ioCode:Product     ; width                       ; $SYSTEM_VERSION:dimensions:$SYSTEM_VERSION:width"
		);
		return IntegrationObjectTestUtil.findIntegrationObjectByCode(PRODUCT_CLASSIFICATION_IO);
	}

	private IntegrationObjectModel setProductIOClassificationEnumReferenceAndMultivalued() throws ImpExException
	{
		importImpEx(
				"# The following ImpEx provides a full example of",
				"# 1) Creating classifications",
				"# 2) Creating Integration Object with classification attributes",
				"# ***************************************************************************************",
				"#     Set up the classifications",
				"# ***************************************************************************************",
				"$SYSTEM=" + CLASSIFICATION_SYSTEM,
				"$VERSION=Staged",
				"$SYSTEM_VERSION=$SYSTEM:$VERSION",
				"$catalogVersionHeader=catalogVersion(catalog(id), version)",
				"$systemVersionHeader=systemVersion(catalog(id), version)",
				"INSERT_UPDATE ClassificationSystem; id[unique = true]",
				"                                  ; $SYSTEM",
				"                                  ; Alternative",
				"INSERT_UPDATE ClassificationSystemVersion; catalog(id)[unique = true]; version[unique = true]",
				"                                         ; $SYSTEM                   ; $VERSION",
				"                                         ; Alternative               ; Products",
				"INSERT_UPDATE ClassificationClass; code[unique = true]; $catalogVersionHeader[unique = true]",
				"                                 ; dimensions         ; $SYSTEM_VERSION",
				"                                 ; QA                 ; $SYSTEM_VERSION",
				"                                 ; alternativeProduct ; Alternative:Products",


				"INSERT_UPDATE ClassificationAttributeUnit; $systemVersionHeader[unique = true]; code[unique = true]; symbol; unitType",
				"                                         ; $SYSTEM_VERSION                    ; centimeters        ; cm    ; measurement",
				"INSERT_UPDATE ClassificationAttribute; code[unique = true]; $systemVersionHeader[unique = true]",
				"                                     ;depth               ; $SYSTEM_VERSION",
				"                                     ;bool                ; $SYSTEM_VERSION",
				"                                     ;valueList           ; $SYSTEM_VERSION",
				"                                     ;ReferenceTypeM      ; $SYSTEM_VERSION",
				"                                     ;tester              ; $SYSTEM_VERSION",
				"                                     ;dateM               ; $SYSTEM_VERSION",
				"                                     ;valueListM          ; $SYSTEM_VERSION",
				"                                     ;stringType          ; $SYSTEM_VERSION",
				"                                     ;numberM             ; $SYSTEM_VERSION",
				"                                     ;stringTypeM         ; $SYSTEM_VERSION",
				"                                     ;date                ; $SYSTEM_VERSION",
				"                                     ;classificationName  ; Alternative:Products",
				"                                     ;boolM               ; $SYSTEM_VERSION",

				"$class=classificationClass($catalogVersionHeader, code)",
				"$attribute=classificationAttribute($systemVersionHeader, code)",
				"INSERT_UPDATE ClassAttributeAssignment; $class[unique = true]                  ; $attribute[unique = true]             ; unit($systemVersionHeader, code); attributeType(code);multiValued; referenceType(code)",
				"                                      ; $SYSTEM_VERSION:dimensions             ; $SYSTEM_VERSION:dateM                 ;                                 ; date               ;true",
				"                                      ; $SYSTEM_VERSION:dimensions             ; $SYSTEM_VERSION:stringTypeM           ;                                 ; string             ;true",
				"                                      ; $SYSTEM_VERSION:dimensions             ; $SYSTEM_VERSION:numberM               ;                                 ; number             ;true",
				"                                      ; $SYSTEM_VERSION:dimensions             ; $SYSTEM_VERSION:boolM                 ;                                 ; boolean            ;true",
				"                                      ; $SYSTEM_VERSION:QA                     ; $SYSTEM_VERSION:tester                ;                                 ; reference          ;false      ; Employee",
				"                                      ; $SYSTEM_VERSION:dimensions             ; $SYSTEM_VERSION:valueListM            ;                                 ; enum               ;true",
				"                                      ; $SYSTEM_VERSION:QA                     ; $SYSTEM_VERSION:ReferenceTypeM        ;                                 ; reference          ;true       ;Employee",
				"                                      ; $SYSTEM_VERSION:dimensions             ; $SYSTEM_VERSION:depth                 ; $SYSTEM_VERSION:centimeters     ; number             ;false",
				"                                      ; $SYSTEM_VERSION:dimensions             ; $SYSTEM_VERSION:bool                  ;                                 ; boolean            ;false",
				"                                      ; $SYSTEM_VERSION:dimensions             ; $SYSTEM_VERSION:valueList             ;                                 ; enum               ;false",
				"                                      ; $SYSTEM_VERSION:dimensions             ; $SYSTEM_VERSION:stringType            ;                                 ; string             ;false",
				"                                      ; $SYSTEM_VERSION:dimensions             ; $SYSTEM_VERSION:date                  ;                                 ; date               ;false",
				"                                      ; Alternative:Products:alternativeProduct;Alternative:Products:classificationName;                                 ;string              ;false",
				"$ioCode=" + PRODUCT_CLASSIFICATION_ENUM_REF_AND_MULTIVALUE_IO,
				"INSERT_UPDATE IntegrationObject; code[unique = true];",
				"                               ; $ioCode",
				"$io = integrationObject(code)[unique = true]",
				"INSERT_UPDATE IntegrationObjectItem; $io    ; code[unique = true]; type(code)    ; root[default = false]",
				"                                   ; $ioCode; Catalog            ; Catalog       ;     ",
				"                                   ; $ioCode; Product            ; Product       ; true",
				"                                   ; $ioCode; Employee           ; Employee      ;     ",
				"                                   ; $ioCode; CatalogVersion     ; CatalogVersion;     ",

				"$item = integrationObjectItem(integrationObject(code), code)",
				"$descriptor = attributeDescriptor(enclosingType(code), qualifier)",
				"$refType = returnIntegrationObjectItem(integrationObject(code), code)",
				"INSERT_UPDATE IntegrationObjectItemAttribute; $item[unique = true]  ; attributeName[unique = true]; $descriptor           ; $refType              ; unique[default = false]",
				"                                            ; $ioCode:Catalog       ; id                          ; Catalog:id            ;                       ; true",
				"                                            ; $ioCode:Product       ; code                        ; Product:code          ;                       ; true",
				"                                            ; $ioCode:Product       ; catalogVersion              ; Product:catalogVersion; $ioCode:CatalogVersion; true",
				"                                            ; $ioCode:Employee      ; uid                         ; Employee:uid          ;                       ; true",
				"                                            ; $ioCode:CatalogVersion; catalog                     ; CatalogVersion:catalog; $ioCode:Catalog       ; true",
				"                                            ; $ioCode:CatalogVersion; version                     ; CatalogVersion:version;                       ; true",

				"$SYSTEM_VERSION=" + CLASSIFICATION_SYSTEM + ":Staged",
				"$systemVersionHeader=systemVersion(catalog(id), version)",
				"$classificationClassHeader=classificationClass(catalogVersion(catalog(id), version), code)",
				"$classificationAttributeHeader=classificationAttribute($systemVersionHeader, code)",
				"$classificationAssignment=classAttributeAssignment($classificationClassHeader, $classificationAttributeHeader)",
				"INSERT_UPDATE IntegrationObjectItemClassificationAttribute; $item[unique = true]; attributeName[unique = true]; $classificationAssignment                           ; returnIntegrationObjectItem(integrationObject(code), code)",
				"                                                          ; $ioCode:Product     ; bool                        ; $SYSTEM_VERSION:dimensions:$SYSTEM_VERSION:bool",
				"                                                          ; $ioCode:Product     ; valueList                   ; $SYSTEM_VERSION:dimensions:$SYSTEM_VERSION:valueList",
				"                                                          ; $ioCode:Product     ; ReferenceTypeM              ; $SYSTEM_VERSION:QA:$SYSTEM_VERSION:ReferenceTypeM   ;$ioCode:Employee",
				"                                                          ; $ioCode:Product     ; depth                       ; $SYSTEM_VERSION:dimensions:$SYSTEM_VERSION:depth",
				"                                                          ; $ioCode:Product     ; tester                      ; $SYSTEM_VERSION:QA:$SYSTEM_VERSION:tester           ; $ioCode:Employee",
				"                                                          ; $ioCode:Product     ; dateM                       ; $SYSTEM_VERSION:dimensions:$SYSTEM_VERSION:dateM",
				"                                                          ; $ioCode:Product     ; valueListM                  ; $SYSTEM_VERSION:dimensions:$SYSTEM_VERSION:valueListM",
				"                                                          ; $ioCode:Product     ; stringType                  ; $SYSTEM_VERSION:dimensions:$SYSTEM_VERSION:stringType",
				"                                                          ; $ioCode:Product     ; numberM                     ; $SYSTEM_VERSION:dimensions:$SYSTEM_VERSION:numberM",
				"                                                          ; $ioCode:Product     ; stringTypeM                 ; $SYSTEM_VERSION:dimensions:$SYSTEM_VERSION:stringTypeM",
				"                                                          ; $ioCode:Product     ; date                        ; $SYSTEM_VERSION:dimensions:$SYSTEM_VERSION:date",
				"                                                          ; $ioCode:Product     ; boolM                       ; $SYSTEM_VERSION:dimensions:$SYSTEM_VERSION:boolM",
				"                                                          ; $ioCode:Product     ; classificationName          ; Alternative:Products:alternativeProduct:Alternative:Products:classificationName"
		);
		return IntegrationObjectTestUtil.findIntegrationObjectByCode(PRODUCT_CLASSIFICATION_ENUM_REF_AND_MULTIVALUE_IO);
	}

	private IntegrationObjectModel setReadOnlyJSONTest() throws ImpExException
	{
		importImpEx(
				"$ioCode=" + READ_ONLY_JSON_IO,
				"INSERT_UPDATE IntegrationObject; code[unique = true];",
				"                               ; $ioCode",
				"$io = integrationObject(code)[unique = true]",
				"INSERT_UPDATE IntegrationObjectItem; $io    ; code[unique = true]; type(code)    ; root[default = false]",
				"                                   ; $ioCode; Catalog            ; Catalog       ;     ",
				"                                   ; $ioCode; Product            ; Product       ; true",
				"                                   ; $ioCode; CatalogVersion     ; CatalogVersion;     ",

				"$item = integrationObjectItem(integrationObject(code), code)",
				"$descriptor = attributeDescriptor(enclosingType(code), qualifier)",
				"$refType = returnIntegrationObjectItem(integrationObject(code), code)",
				"INSERT_UPDATE IntegrationObjectItemAttribute; $item[unique = true]  ; attributeName[unique = true]; $descriptor           ; $refType              ; unique[default = false]",
				"                                            ; $ioCode:Catalog       ; id                          ; Catalog:id            ;                       ; true",
				"                                            ; $ioCode:Product       ; creationtime                ; Product:creationtime  ;                       ;     ",
				"                                            ; $ioCode:Product       ; code                        ; Product:code          ;                       ; true",
				"                                            ; $ioCode:Product       ; catalogVersion              ; Product:catalogVersion; $ioCode:CatalogVersion; true",
				"                                            ; $ioCode:Product       ; modifiedtime                ; Product:modifiedtime  ;                       ;     ",
				"                                            ; $ioCode:CatalogVersion; version                     ; CatalogVersion:version;                       ; true",
				"                                            ; $ioCode:CatalogVersion; catalog                     ; CatalogVersion:catalog; $ioCode:Catalog       ; true"
		);
		return IntegrationObjectTestUtil.findIntegrationObjectByCode(READ_ONLY_JSON_IO);
	}

	private IntegrationObjectModel setVirtualAttributeJSONTest() throws ImpExException
	{
		importImpEx(
				"$ioCode=" + VA_JSON_IO,
				"INSERT_UPDATE IntegrationObject; code[unique = true];",
				"                               ; $ioCode",
				"$io = integrationObject(code)[unique = true]",
				"INSERT_UPDATE IntegrationObjectItem; $io    ; code[unique = true]; type(code); root[default = false]",
				"                                   ; $ioCode; Catalog       ; Catalog        ;     ",
				"                                   ; $ioCode; Product       ; Product        ; true",
				"                                   ; $ioCode; CatalogVersion; CatalogVersion ;     ",

				"$item = integrationObjectItem(integrationObject(code), code)",
				"$descriptor = attributeDescriptor(enclosingType(code), qualifier)",
				"$refItem = returnIntegrationObjectItem(integrationObject(code), code)",
				"INSERT_UPDATE IntegrationObjectItemAttribute; $item[unique = true]  ; attributeName[unique = true]; $descriptor           ; $refItem              ; unique[default = false]",
				"                                            ; $ioCode:Catalog       ; id                          ; Catalog:id            ;                       ; true",
				"                                            ; $ioCode:Product       ; creationtime                ; Product:creationtime  ;                       ;     ",
				"                                            ; $ioCode:Product       ; code                        ; Product:code          ;                       ; true",
				"                                            ; $ioCode:Product       ; catalogVersion              ; Product:catalogVersion; $ioCode:CatalogVersion; true",
				"                                            ; $ioCode:Product       ; modifiedtime                ; Product:modifiedtime  ;                       ;     ",
				"                                            ; $ioCode:CatalogVersion; version                     ; CatalogVersion:version;                       ; true",
				"                                            ; $ioCode:CatalogVersion; catalog                     ; CatalogVersion:catalog; $ioCode:Catalog       ; true",

				"INSERT_UPDATE IntegrationObjectItemVirtualAttribute; $item[unique = true]; attributeName[unique = true]; retrievalDescriptor(code)",
				"                                                   ; $ioCode:Product     ; testVa                      ; testVa",

				"INSERT_UPDATE IntegrationObjectVirtualAttributeDescriptor; code[unique = true]; logicLocation             ; type(code)",
				"                                                         ; testVa             ; model://modelScriptForTest; java.lang.String "
		);
		return IntegrationObjectTestUtil.findIntegrationObjectByCode(VA_JSON_IO);
	}

	private IntegrationObjectModel setMultiTypeDefinitionJSONTest() throws ImpExException
	{
		importImpEx(
				"$ioCode=" + MULTI_TYPE_DEFINITION_IO,
				"INSERT_UPDATE IntegrationObject; code[unique = true]",
				"                               ; $ioCode",

				"$io = integrationObject(code)",
				"INSERT_UPDATE IntegrationObjectItem; $io[unique = true]; code[unique = true]; type(code)    ; root[default = false]",
				"                                   ; $ioCode           ; CatalogVersion     ; CatalogVersion; 	    ",
				"                                   ; $ioCode           ; Category           ; Category      ; true	",
				"                                   ; $ioCode           ; Media2             ; Media         ; 	    ",
				"                                   ; $ioCode           ; Catalog            ; Catalog       ; 	    ",
				"                                   ; $ioCode           ; Media              ; Media         ; 	    ",

				"$item = integrationObjectItem(integrationObject(code), code)",
				"$descriptor = attributeDescriptor(enclosingType(code), qualifier)",
				"$refItem = returnIntegrationObjectItem(integrationObject(code), code)",
				"INSERT_UPDATE IntegrationObjectItemAttribute; $item[unique = true] ; attributeName[unique = true]; $descriptor            ; $refItem              ; unique[default = false]",
				"                                            ; $ioCode:CatalogVersion; version                    ; CatalogVersion:version ;                       ; true",
				"                                            ; $ioCode:CatalogVersion; catalog                    ; CatalogVersion:catalog ; $ioCode:Catalog       ; true",
				"                                            ; $ioCode:Category      ; thumbnail                  ; Category:thumbnail     ; $ioCode:Media2        ; 	",
				"                                            ; $ioCode:Category      ; catalogVersion             ; Category:catalogVersion; $ioCode:CatalogVersion; true",
				"                                            ; $ioCode:Category      ; picture                    ; Category:picture       ; $ioCode:Media         ; 	",
				"                                            ; $ioCode:Category      ; detail                     ; Category:detail        ; $ioCode:Media         ; 	",
				"                                            ; $ioCode:Category      ; code                       ; Category:code          ;                       ; true",
				"                                            ; $ioCode:Media2        ; code                       ; Media:code             ;                       ; true",
				"                                            ; $ioCode:Media2        ; altText                    ; Media:altText          ;                       ; 	",
				"                                            ; $ioCode:Media2        ; catalogVersion             ; Media:catalogVersion   ; $ioCode:CatalogVersion; true",
				"                                            ; $ioCode:Catalog       ; id                         ; Catalog:id             ;                       ; true",
				"                                            ; $ioCode:Media         ; code                       ; Media:code             ;                       ; true",
				"                                            ; $ioCode:Media         ; catalogVersion             ; Media:catalogVersion   ; $ioCode:CatalogVersion; true",
				"                                            ; $ioCode:Media         ; dataPK                     ; Media:dataPK           ;                       ; 	"
		);
		return IntegrationObjectTestUtil.findIntegrationObjectByCode(MULTI_TYPE_DEFINITION_IO);
	}

	@Test
	public void basicJsonTest() throws ImpExException
	{
		final IntegrationObjectModel io = setBasicJSONTest();
		final String expectedJsonString = """
				{
				  "integrationKey": "TEST_KEY",
				  "numberContentUnits": "123.0",
				  "offlineDate": "/Date(1577836800000)/",
				  "code": "Test_code",
				  "manufacturerAID": "Test_manufacturerAID",
				  "catalogVersion": {
				    "integrationKey": "TEST_KEY",
				    "active": true,
				    "version": "Test_version",
				    "catalog": {
				      "integrationKey": "TEST_KEY",
				      "id": "Test_id"
				    }
				  }
				}
				""".trim();

		final String actualJsonString = jsonGenerator.generateJson(io);


		assertEquals(expectedJsonString, actualJsonString);
	}

	@Test
	public void mapJsonTest() throws ImpExException
	{
		final IntegrationObjectModel integrationObjectModel = setMapJSONTest();
		final String expectedJsonString = """
				{
				  "integrationKey": "TEST_KEY",
				  "description": "Test_description",
				  "code": "Test_code",
				  "deliveryTime": "123.0",
				  "catalogVersion": {
				    "integrationKey": "TEST_KEY",
				    "version": "Test_version",
				    "catalog": {
				      "integrationKey": "TEST_KEY",
				      "id": "Test_id"
				    }
				  },
				  "localizedAttributes": [
				    {
				      "language": "en",
				      "description": "Test_description"
				    }
				  ]
				}
				""".trim();

		final String actualJsonString = jsonGenerator.generateJson(integrationObjectModel);

		assertEquals(expectedJsonString, actualJsonString);
	}

	@Test
	public void collectionJsonTest() throws ImpExException
	{
		final IntegrationObjectModel io = setCollectionJSONTest();
		final String expectedJsonString = """
				{
				  "integrationKey": "TEST_KEY",
				  "code": "Test_code",
				  "deliveryTime": "123.0",
				  "catalogVersion": {
				    "integrationKey": "TEST_KEY",
				    "version": "Test_version",
				    "catalog": {
				      "integrationKey": "TEST_KEY",
				      "id": "Test_id"
				    }
				  },
				  "data_sheet": [
				    {
				      "integrationKey": "TEST_KEY",
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
				  ],
				  "deliveryModes": [
				    {
				      "integrationKey": "TEST_KEY",
				      "code": "Test_code"
				    }
				  ]
				}
				""".trim();

		final String actualJsonString = jsonGenerator.generateJson(io);

		assertEquals(expectedJsonString, actualJsonString);
	}

	@Test
	public void mapOfMapsNotSupportedJsonTest() throws ImpExException
	{
		final IntegrationObjectModel io = setMapNotSupportedJSONTest();
		final String expectedJsonString = """
				{
				  "integrationKey": "TEST_KEY",
				  "code": "Test_code",
				  "catalogVersion": {
				    "integrationKey": "TEST_KEY",
				    "generationDate": "/Date(1577836800000)/",
				    "generatorInfo": "Test_generatorInfo",
				    "version": "Test_version",
				    "inclAssurance": true,
				    "catalog": {
				      "integrationKey": "TEST_KEY",
				      "id": "Test_id"
				    }
				  },
				  "articleStatus": "Invalid map attribute: MapDescriptor is not available",
				  "localizedAttributes": [
				    {
				      "language": "en",
				      "articleStatus": "Unsupported primitive type"
				    }
				  ]
				}
				""".trim();

		final String actualJsonString = jsonGenerator.generateJson(io);

		assertEquals(expectedJsonString, actualJsonString);
	}

	@Test
	public void circularDependencyJsonTest() throws ImpExException
	{
		final IntegrationObjectModel integrationObjectModel = setCircularDependencyJSONTest();
		final String expectedJsonString = """
				{
				  "integrationKey": "TEST_KEY",
				  "code": "Test_code",
				  "entries": [
				    {
				      "integrationKey": "TEST_KEY",
				      "order": {
				        "integrationKey": "TEST_KEY",
				        "code": "Test_code"
				      }
				    }
				  ]
				}
				""".trim();

		final String actualJsonString = jsonGenerator.generateJson(integrationObjectModel);

		assertEquals(expectedJsonString, actualJsonString);
	}

	@Test
	public void circularDependencyComplexJsonTest() throws ImpExException
	{
		final IntegrationObjectModel integrationObjectModel = setCircularDependencyComplexJSONTest();
		final String expectedJsonString = """
				{
				  "integrationKey": "TEST_KEY",
				  "originalVersion": {
				    "integrationKey": "TEST_KEY",
				    "user": {
				      "integrationKey": "TEST_KEY",
				      "uid": "Test_uid"
				    }
				  },
				  "entries": [
				    {
				      "integrationKey": "TEST_KEY",
				      "order": {
				        "integrationKey": "TEST_KEY",
				        "user": {
				          "integrationKey": "TEST_KEY",
				          "uid": "Test_uid"
				        }
				      }
				    }
				  ],
				  "user": {
				    "integrationKey": "TEST_KEY",
				    "uid": "Test_uid"
				  },
				  "placedBy": {
				    "integrationKey": "TEST_KEY",
				    "uid": "Test_uid"
				  }
				}
				""".trim();

		final String actualJsonString = jsonGenerator.generateJson(integrationObjectModel);

		assertEquals(expectedJsonString, actualJsonString);
	}

	@Test
	public void classificationJsonTest() throws ImpExException
	{
		final IntegrationObjectModel integrationObjectModel = setProductIOClassificationTest();
		final String expectedJsonString = """
				{
				  "integrationKey": "TEST_KEY",
				  "depth": "123.0",
				  "height": "123.0",
				  "code": "Test_code",
				  "width": "123.0",
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

		final String actualJsonString = jsonGenerator.generateJson(integrationObjectModel);

		assertEquals(expectedJsonString, actualJsonString);
	}

	@Test
	public void classificationCollectionEnumReferenceJsonTest() throws ImpExException
	{
		final IntegrationObjectModel integrationObjectModel = setProductIOClassificationEnumReferenceAndMultivalued();
		final String expectedJsonString = """
				{
				  "integrationKey": "TEST_KEY",
				  "depth": "123.0",
				  "dateM": [
				    {
				      "value": "/Date(1577836800000)/"
				    },
				    {
				      "value": "/Date(1577836800000)/"
				    }
				  ],
				  "bool": true,
				  "boolM": [
				    {
				      "value": true
				    },
				    {
				      "value": false
				    }
				  ],
				  "code": "Test_code",
				  "classificationName": "Test_classificationName",
				  "valueListM": [
				    {
				      "value": "Test_valueListM_1"
				    },
				    {
				      "value": "Test_valueListM_2"
				    }
				  ],
				  "stringTypeM": [
				    {
				      "value": "Test_stringTypeM_1"
				    },
				    {
				      "value": "Test_stringTypeM_2"
				    }
				  ],
				  "date": "/Date(1577836800000)/",
				  "numberM": [
				    {
				      "value": "123.0"
				    },
				    {
				      "value": "34"
				    }
				  ],
				  "stringType": "Test_stringType",
				  "valueList": "Test_valueList",
				  "tester": {
				    "integrationKey": "TEST_KEY",
				    "uid": "Test_uid"
				  },
				  "ReferenceTypeM": [
				    {
				      "integrationKey": "TEST_KEY",
				      "uid": "Test_uid"
				    }
				  ],
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

		final String actualJsonString = jsonGenerator.generateJson(integrationObjectModel);

		assertEquals(expectedJsonString, actualJsonString);
	}

	@Test
	public void readOnlyJsonTest() throws ImpExException
	{
		final IntegrationObjectModel integrationObjectModel = setReadOnlyJSONTest();
		final String expectedJsonString = """
				{
				  "integrationKey": "TEST_KEY",
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

		final String actualJsonString = jsonGenerator.generateJson(integrationObjectModel);

		assertEquals(expectedJsonString, actualJsonString);
	}

	@Test
	public void virtualAttributeJsonTest() throws ImpExException
	{
		final IntegrationObjectModel integrationObjectModel = setVirtualAttributeJSONTest();
		// VA wouldn't be shown because they are not settable, so the expected json is the same as readonly
		final String expectedJsonString = """
				{
				  "integrationKey": "TEST_KEY",
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

		final String actualJsonString = jsonGenerator.generateJson(integrationObjectModel);

		assertEquals(expectedJsonString, actualJsonString);
	}

	@Test
	public void multiTypeDefinitionJsonTest() throws ImpExException
	{
		final IntegrationObjectModel integrationObjectModel = setMultiTypeDefinitionJSONTest();
		final var expectedJsonString = """
				{
				  "integrationKey": "TEST_KEY",
				  "code": "Test_code",
				  "catalogVersion": {
				    "integrationKey": "TEST_KEY",
				    "version": "Test_version",
				    "catalog": {
				      "integrationKey": "TEST_KEY",
				      "id": "Test_id"
				    }
				  },
				  "detail": [
				    {
				      "integrationKey": "TEST_KEY",
				      "dataPK": "12345",
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
				  ],
				  "picture": {
				    "integrationKey": "TEST_KEY",
				    "dataPK": "12345",
				    "code": "Test_code",
				    "catalogVersion": {
				      "integrationKey": "TEST_KEY",
				      "version": "Test_version",
				      "catalog": {
				        "integrationKey": "TEST_KEY",
				        "id": "Test_id"
				      }
				    }
				  },
				  "thumbnail": {
				    "integrationKey": "TEST_KEY",
				    "altText": "Test_altText",
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
				}
				""".trim();

		final String actualJsonString = jsonGenerator.generateJson(integrationObjectModel);

		assertEquals(expectedJsonString, actualJsonString);
	}
}
