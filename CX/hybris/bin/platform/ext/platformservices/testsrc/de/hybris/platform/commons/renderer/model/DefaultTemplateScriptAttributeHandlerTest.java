/*
 * Copyright (c) 2024 SAP SE or an SAP affiliate company. All rights reserved.
 */

package de.hybris.platform.commons.renderer.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.commons.enums.RendererTypeEnum;
import de.hybris.platform.commons.model.renderer.RendererTemplateModel;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.servicelayer.ServicelayerBaseTest;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

@IntegrationTest
public class DefaultTemplateScriptAttributeHandlerTest extends ServicelayerBaseTest
{
	@Resource
	ModelService modelService;

	@Test
	public void shouldRemoveMediaObjectWhileRemovingTheTemplate()
	{
		//given
		final String content = "testContent-" + RandomStringUtils.randomAlphanumeric(6);
		final String code = "testCode-" + UUID.randomUUID();

		//and
		final RendererTemplateModel template = createRendererTemplateModel(content, code);

		modelService.save(template);

		final MediaModel media = template.getDefaultContent();

		//when
		modelService.remove(template);

		//then
		assertTrue(modelService.isRemoved(template));
		assertTrue(modelService.isRemoved(media));
	}

	@Test
	public void shouldCorrectlyReadValueOfDynamicAttributeBeforeSave()
	{
		//given
		final String content = "testContent-" + RandomStringUtils.randomAlphanumeric(6);
		final String code = "testCode-" + UUID.randomUUID();

		//and
		final RendererTemplateModel model = createRendererTemplateModel(content, code);

		//then
		assertTrue(modelService.isNew(model));
		assertEquals(content, model.getDefaultTemplateScript());
		assertEquals(code, model.getCode());
	}

	@Test
	public void shouldSaveRendererTemplateWithDynamicAttributeValue()
	{
		//given
		final String content = "testContent-" + RandomStringUtils.randomAlphanumeric(6);
		final String code = "testCode-" + UUID.randomUUID();

		//and
		final RendererTemplateModel model = createRendererTemplateModel(content, code);

		//when
		modelService.save(model);

		//then
		assertFalse(modelService.isNew(model));
		modelService.refresh(model);
		assertEquals(content, model.getDefaultTemplateScript());
		assertEquals(code, model.getCode());
	}

	@Test
	public void shouldAllowToSaveRendererTemplateWithTheSameCodeAfterAbandoningModel()
	{
		//given
		final String content = "testContent-" + RandomStringUtils.randomAlphanumeric(6);
		final String code = "testCode-" + UUID.randomUUID();

		//and
		final RendererTemplateModel modelToAbandon = createRendererTemplateModel(content, code);
		modelToAbandon.setProperty("abandoned", true);

		//detach model
		modelService.detach(modelToAbandon);

		//and
		final RendererTemplateModel modelToSave = createRendererTemplateModel(content, code);

		//when
		modelService.save(modelToSave);

		//then
		assertFalse(modelService.isNew(modelToSave));
	}

	@Test
	public void shouldAllowToCreateTwoRendererTemplateModelsWithDifferentCodesOnceTemplateScriptSetFirst()
	{
		//given
		final String content1 = "testContent-" + RandomStringUtils.randomAlphanumeric(6);
		final String content2 = "testContent-" + RandomStringUtils.randomAlphanumeric(6);

		final String code1 = "code1-" + UUID.randomUUID();
		final String code2 = "code2-" + UUID.randomUUID();

		//and
		final RendererTemplateModel firstTemplate = createRendererTemplateModel(content1, code1);
		final RendererTemplateModel secondTemplate = createRendererTemplateModel(content2, code2);

		//when
		modelService.saveAll(firstTemplate, secondTemplate);

		//then
		modelService.refresh(firstTemplate);
		modelService.refresh(secondTemplate);

		assertEquals(content1, firstTemplate.getDefaultTemplateScript());
		assertEquals(code1, firstTemplate.getCode());
		assertEquals(content2, secondTemplate.getDefaultTemplateScript());
		assertEquals(code2, secondTemplate.getCode());
	}

	@Test
	public void shouldReSaveDefaultTemplateScript()
	{
		//given
		final String content = "testContent-" + RandomStringUtils.randomAlphanumeric(6);
		final String reSavedContent = "reSavedTestContent-" + RandomStringUtils.randomAlphanumeric(6);

		final String code = "testCode-" + UUID.randomUUID();

		//and
		final RendererTemplateModel rendererTemplateModel = createRendererTemplateModel(content, code);

		//and
		modelService.save(rendererTemplateModel);

		//when
		rendererTemplateModel.setDefaultTemplateScript(reSavedContent);

		//then
		modelService.save(rendererTemplateModel);

		assertEquals(rendererTemplateModel.getDefaultTemplateScript(), reSavedContent);
	}

	@Test
	public void shouldRemoveDefaultTemplateScriptWhenCleared()
	{
		//given
		final String content = "testContent-" + RandomStringUtils.randomAlphanumeric(6);

		final String code = "testCode-" + UUID.randomUUID();

		//and
		final RendererTemplateModel rendererTemplateModel = createRendererTemplateModel(content, code);

		//and
		modelService.save(rendererTemplateModel);

		//when
		rendererTemplateModel.setDefaultTemplateScript(null);

		//then
		modelService.save(rendererTemplateModel);

		assertNull(rendererTemplateModel.getDefaultTemplateScript());

	}

	private RendererTemplateModel createRendererTemplateModel(final String content, final String code){
		final RendererTemplateModel rendererTemplateModel = modelService.create(RendererTemplateModel.class);

		rendererTemplateModel.setDefaultTemplateScript(content);
		rendererTemplateModel.setCode(code);
		rendererTemplateModel.setRendererType(RendererTypeEnum.VELOCITY);

		return rendererTemplateModel;
	}
}
