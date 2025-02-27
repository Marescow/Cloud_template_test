/*
 * Copyright (c) 2020 SAP SE or an SAP affiliate company. All rights reserved
 */
package com.hybris.backoffice.excel.exporting;

import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import de.hybris.platform.core.model.type.AttributeDescriptorModel;

import java.util.Collections;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.hybris.backoffice.excel.data.ExcelAttribute;
import com.hybris.backoffice.excel.data.ExcelAttributeDescriptorAttribute;
import com.hybris.backoffice.excel.data.ExcelExportResult;
import com.hybris.backoffice.excel.template.ClassificationIncludedHeaderPromptPopulator;
import com.hybris.backoffice.excel.template.mapper.ExcelMapper;


@RunWith(MockitoJUnitRunner.class)
public class HeaderPromptWorkbookDecoratorTest
{

	@Mock
	private ExcelMapper<ExcelExportResult, ExcelAttributeDescriptorAttribute> mapper;
	@Spy
	private ClassificationIncludedHeaderPromptPopulator populator;

	private HeaderPromptWorkbookDecorator decorator = new HeaderPromptWorkbookDecorator();

	@Before
	public void setUp()
	{
		decorator.setMapper(mapper);
		decorator.setHeaderPromptPopulator(populator);
	}

	@Test
	public void shouldCurrentAvailableAttributesBeMergedWithItemsAttributes()
	{
		// given
		final ExcelAttribute currentAttribute = new ExcelAttributeDescriptorAttribute(mock(AttributeDescriptorModel.class), null);
		final Workbook workbook = new XSSFWorkbook();

		final ExcelExportResult result = new ExcelExportResult(workbook, Collections.emptyList(),
				Collections.emptyList(), Collections.emptyList(), Lists.newArrayList(currentAttribute));
		given(mapper.apply(result)).willReturn(Lists.newArrayList(mock(ExcelAttributeDescriptorAttribute.class)));

		// when
		doNothing().when(populator).populate(any());
		decorator.decorate(result);

		// then
		final ArgumentMatcher<ExcelExportResult> matcher = new ArgumentMatcher<ExcelExportResult>()
		{
			@Override
			public boolean matches(ExcelExportResult excelExportResult) {
				return excelExportResult.getAvailableAdditionalAttributes().size() == 2;
			}
		};
		verify(populator).populate(Mockito.argThat(matcher));
		assertTrue(workbook.getForceFormulaRecalculation());
	}

}
