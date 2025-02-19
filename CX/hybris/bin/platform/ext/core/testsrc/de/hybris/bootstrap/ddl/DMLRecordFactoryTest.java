/*
 * Copyright (c) 2024 SAP SE or an SAP affiliate company. All rights reserved.
 */

package de.hybris.bootstrap.ddl;

import static org.mockito.AdditionalMatchers.not;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.bootstrap.codegenerator.CodeGenerator;
import de.hybris.bootstrap.ddl.model.YColumn;
import de.hybris.bootstrap.ddl.model.YDbModel;
import de.hybris.bootstrap.ddl.pk.PkFactory;
import de.hybris.bootstrap.typesystem.YAtomicType;
import de.hybris.bootstrap.typesystem.YAttributeDescriptor;
import de.hybris.bootstrap.typesystem.YComposedType;
import de.hybris.bootstrap.typesystem.YDeployment;
import de.hybris.bootstrap.typesystem.YExtension;
import de.hybris.bootstrap.typesystem.YTypeSystem;
import de.hybris.platform.core.PK;

import org.apache.commons.beanutils.DynaBean;
import org.apache.ddlutils.model.Database;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class DMLRecordFactoryTest
{
	private static final String TABLE_PREFIX = "";
	private static final String TYPE_SYSTEM_NAME = "DEFAULT";
	private static final int PROPERTY_MAX_LENGTH = 30;
	private static final String ATTRIBUTE_DESCRIPTORS_TABLE = "attributedescriptors";
	private static final String PERSISTENCE_TYPE_ATTRIBUTE = "persistenceType";
	private static final String PERSISTENCE_TYPE_COLUMN = "persistencetypepk";

	private DMLRecordFactory dmlRecordFactory;
	@Mock
	private YDbModel dbModel;
	@Mock
	private Database database;
	@Mock
	private PkFactory pkFactory;
	@Mock
	private YTypeSystem typeSystem;
	@Mock
	private HybrisPlatform platform;
	@Mock
	private CodeGenerator codeGenerator;
	@Mock
	private PropertiesLoader properties;
	@Mock
	private YComposedType enclosingType;
	@Mock
	private YComposedType metaType;
	@Mock
	private YComposedType declaringType;
	@Mock
	private YAttributeDescriptor attributeDescriptor;
	@Mock
	private YAttributeDescriptor declaringAttributeDescriptor;
	@Mock
	private YAtomicType attributeColumnType;
	@Mock
	private YAtomicType declaredAttributeColumnType;
	@Mock
	private YDeployment metaTypeDeployment;
	@Mock
	private DynaBean attrDynaBean;
	@Mock
	private YAttributeDescriptor persistenceTypeAttDescriptor;
	@Mock
	private YAttributeDescriptor otherAttrDescriptor;
	@Mock
	private YColumn persistenceTypeColumn;
	@Mock
	private YColumn otherColumn;
	@Mock
	private YExtension extension;


	@Test
	public void shouldReturnDeclaredAttributeTypeAsPersistenceTypeWhenLegacyModeIsDisabled()
	{
		//given
		mockSystemTypeInformation();
		when(attributeDescriptor.getPersistenceType()).thenReturn(YAttributeDescriptor.PersistenceType.PROPERTY);
		when(attributeDescriptor.isRedeclared()).thenReturn(Boolean.TRUE);
		when(attributeDescriptor.getSuperAttribute()).thenReturn(declaringAttributeDescriptor);
		when(declaringAttributeDescriptor.getColumnType()).thenReturn(declaredAttributeColumnType);
		final PK declaredAttributeColumnTypePK = PK.fromLong(3L);
		when(pkFactory.getOrCreatePK(declaredAttributeColumnType)).thenReturn(declaredAttributeColumnTypePK);

		when(properties.getProperty(DMLRecordFactory.REDECLARED_ATTRIBUTE_LEGACY_MODE_PROPERTY, "false")).thenReturn("false");
		dmlRecordFactory = new DMLRecordFactory(dbModel, database, typeSystem, platform, codeGenerator, pkFactory,
				PROPERTY_MAX_LENGTH, TYPE_SYSTEM_NAME, TABLE_PREFIX, properties);

		//when
		dmlRecordFactory.addAttributeDescriptor(enclosingType, attributeDescriptor);

		//then
		verify(attrDynaBean).set(PERSISTENCE_TYPE_COLUMN, declaredAttributeColumnTypePK);
	}

	@Test
	public void shouldReturnAttributeTypeAsPersistenceTypeWhenLegacyModeIsEnabled()
	{
		//given
		mockSystemTypeInformation();
		when(attributeDescriptor.getPersistenceType()).thenReturn(YAttributeDescriptor.PersistenceType.PROPERTY);
		when(attributeDescriptor.isRedeclared()).thenReturn(Boolean.TRUE);
		final PK attributeColumnTypePK = PK.fromLong(4L);
		when(pkFactory.getOrCreatePK(attributeColumnType)).thenReturn(attributeColumnTypePK);

		when(properties.getProperty(DMLRecordFactory.REDECLARED_ATTRIBUTE_LEGACY_MODE_PROPERTY, "false")).thenReturn("true");
		dmlRecordFactory = new DMLRecordFactory(dbModel, database, typeSystem, platform, codeGenerator, pkFactory,
				PROPERTY_MAX_LENGTH, TYPE_SYSTEM_NAME, TABLE_PREFIX, properties);

		//when
		dmlRecordFactory.addAttributeDescriptor(enclosingType, attributeDescriptor);

		//then
		verify(attrDynaBean).set(PERSISTENCE_TYPE_COLUMN, attributeColumnTypePK);
		verify(attributeDescriptor, never()).getSuperAttribute();
	}

	@Test
	public void shouldReturnAttributeTypeAsPersistenceTypeWhenAttributeIsNotRedeclared()
	{
		//given
		mockSystemTypeInformation();
		when(attributeDescriptor.getPersistenceType()).thenReturn(YAttributeDescriptor.PersistenceType.PROPERTY);
		when(attributeDescriptor.isRedeclared()).thenReturn(Boolean.FALSE);
		final PK attributeColumnTypePK = PK.fromLong(4L);
		when(pkFactory.getOrCreatePK(attributeColumnType)).thenReturn(attributeColumnTypePK);

		when(properties.getProperty(DMLRecordFactory.REDECLARED_ATTRIBUTE_LEGACY_MODE_PROPERTY, "false")).thenReturn("false");
		dmlRecordFactory = new DMLRecordFactory(dbModel, database, typeSystem, platform, codeGenerator, pkFactory,
				PROPERTY_MAX_LENGTH, TYPE_SYSTEM_NAME, TABLE_PREFIX, properties);

		//when
		dmlRecordFactory.addAttributeDescriptor(enclosingType, attributeDescriptor);

		//then
		verify(attrDynaBean).set(PERSISTENCE_TYPE_COLUMN, attributeColumnTypePK);
		verify(attributeDescriptor, never()).getSuperAttribute();
	}

	@Test
	public void shouldReturnNullAsPersistenceTypeWhenAttributeIsNotProperty()
	{
		//given
		mockSystemTypeInformation();
		when(attributeDescriptor.getPersistenceType()).thenReturn(YAttributeDescriptor.PersistenceType.JALO);

		when(properties.getProperty(DMLRecordFactory.REDECLARED_ATTRIBUTE_LEGACY_MODE_PROPERTY, "false")).thenReturn("false");
		dmlRecordFactory = new DMLRecordFactory(dbModel, database, typeSystem, platform, codeGenerator, pkFactory,
				PROPERTY_MAX_LENGTH, TYPE_SYSTEM_NAME, TABLE_PREFIX, properties);

		//when
		dmlRecordFactory.addAttributeDescriptor(enclosingType, attributeDescriptor);

		//then
		verify(attrDynaBean).set(PERSISTENCE_TYPE_COLUMN, null);
	}

	private void mockSystemTypeInformation()
	{
		when(attributeDescriptor.getMetaType()).thenReturn(metaType);
		when(attributeDescriptor.getQualifier()).thenReturn("attributeName");
		when(attributeDescriptor.getNamespace()).thenReturn(extension);
		when(attributeDescriptor.getDeclaringType()).thenReturn(declaringType);
		when(attributeDescriptor.getColumnType()).thenReturn(attributeColumnType);
		when(attributeDescriptor.getDeclaringAttribute()).thenReturn(declaringAttributeDescriptor);
		when(metaType.getAttributeIncludingSuperType(PERSISTENCE_TYPE_ATTRIBUTE)).thenReturn(persistenceTypeAttDescriptor);
		when(metaType.getAttributeIncludingSuperType(not(eq(PERSISTENCE_TYPE_ATTRIBUTE)))).thenReturn(otherAttrDescriptor);
		when(metaType.getDeployment()).thenReturn(metaTypeDeployment);
		when(metaTypeDeployment.getTableName()).thenReturn(ATTRIBUTE_DESCRIPTORS_TABLE);
		when(database.createDynaBeanFor(ATTRIBUTE_DESCRIPTORS_TABLE, false)).thenReturn(attrDynaBean);
		when(pkFactory.getOrCreatePK(enclosingType, attributeDescriptor)).thenReturn(PK.fromLong(1L));
		when(pkFactory.getOrCreatePK(metaType)).thenReturn(PK.fromLong(2L));
		when(dbModel.findMappedColumn(metaType, persistenceTypeAttDescriptor)).thenReturn(persistenceTypeColumn);
		when(persistenceTypeColumn.getName()).thenReturn(PERSISTENCE_TYPE_COLUMN);
		when(dbModel.findMappedColumn(metaType, otherAttrDescriptor)).thenReturn(otherColumn);
		when(otherColumn.getName()).thenReturn("otherColumn");
		when(enclosingType.getSuperType()).thenReturn(declaringType);
	}
}
