/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.xyformsservices.form.impl;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import de.hybris.platform.servicelayer.exceptions.ModelSavingException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.xyformsservices.daos.YFormDao;
import de.hybris.platform.xyformsservices.enums.YFormDataTypeEnum;
import de.hybris.platform.xyformsservices.enums.YFormDefinitionStatusEnum;
import de.hybris.platform.xyformsservices.exception.YFormServiceException;
import de.hybris.platform.xyformsservices.form.YFormService;
import de.hybris.platform.xyformsservices.model.YFormDataModel;
import de.hybris.platform.xyformsservices.model.YFormDefinitionModel;
import de.hybris.platform.xyformsservices.strategy.GetVersionNumberStrategy;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;



/**
 * Implementation of the YFormService for managing yForms.
 */
public class DefaultYFormService implements YFormService
{
	private static final Logger LOG = Logger.getLogger(DefaultYFormService.class);

	private YFormDao yformDao;
	private ModelService modelService;
	private GetVersionNumberStrategy getVersionNumberStrategy;

	private static final String APPLICATION_ID_NOTNULL = "Parameter applicationId must not be null";
	private static final String FORM_ID_NOTNULL = "Parameter formId must not be null";
	private static final String EXCEPTION_WHILE_SAVE = "Exception while saving yform definition";
	private static final String FAILED = "] failed.";
	private static final String FORM_ID = "] and formId[";
	private static final String YFORM_WITH_ID = "YFormDefinition with id[";
	private static final String NOT_EXIST = "] does not exist";

	/**
	 * For a given applicationId and formId a form definition is returned.
	 *
	 * @param applicationId
	 * @param formId
	 * @return YFormDefinitionModel
	 */
	@Override
	public YFormDefinitionModel getYFormDefinition(final String applicationId, final String formId) throws YFormServiceException
	{
		validateParameterNotNull(applicationId, "Parameter applicationId must not be null");
		validateParameterNotNull(formId, "Parameter formId must not be null");
		try
		{
			return yformDao.findYFormDefinition(applicationId, formId);
		}
		catch (final ModelNotFoundException e)
		{
			throw new YFormServiceException("YFormDefinition with id[" + applicationId + ":" + formId + "] does not exist");
		}
		catch (final AmbiguousIdentifierException e)
		{
			throw new YFormServiceException("Multiple YFormDefinition found with id[" + applicationId + ":" + formId + "]");
		}
		catch (final UnknownIdentifierException e)
		{
			throw new YFormServiceException("YFormDefinition with id[" + applicationId + ":" + formId + "] does not exist");
		}
	}

	/**
	 * For a given applicationId and formId a form definition is returned.
	 *
	 * @param applicationId
	 * @param formId
	 * @param version
	 * @return YFormDefinitionModel
	 */
	@Override
	public YFormDefinitionModel getYFormDefinition(final String applicationId, final String formId, final int version)
			throws YFormServiceException
	{
		validateParameterNotNull(applicationId, "Parameter applicationId must not be null");
		validateParameterNotNull(formId, "Parameter formId must not be null");
		try
		{
			return yformDao.findYFormDefinition(applicationId, formId, version);
		}
		catch (final ModelNotFoundException e)
		{
			throw new YFormServiceException("YFormDefinition with id[" + applicationId + ":" + formId + "] does not exist");
		}
		catch (final AmbiguousIdentifierException e)
		{
			throw new YFormServiceException("Multiple YFormDefinition found with id[" + applicationId + ":" + formId + "]");
		}
		catch (final UnknownIdentifierException e)
		{
			throw new YFormServiceException("YFormDefinition with id[" + applicationId + ":" + formId + "] does not exist");
		}
	}

	/**
	 * Update YFormDefinition if it exists in the database. <br/>
	 * Catch AmbiguousIdentifierException and throws YFormServiceException with corresponding error messages on retrieval
	 * from the DAO.
	 *
	 * @param applicationId
	 * @param formId
	 * @param content
	 * @param documentId
	 * @return YFormDefinitionModel
	 * @throws ModelNotFoundException
	 * @throws YFormServiceException
	 */
	@Override
	public YFormDefinitionModel updateYFormDefinition(final String applicationId, final String formId, final String content,
			final String documentId) throws YFormServiceException
	{
		final YFormDefinitionModel yformDefinition = yformDao.findYFormDefinition(applicationId, formId);

		try
		{
			yformDefinition.setContent(content);
			yformDefinition.setDocumentId(documentId);
			modelService.save(yformDefinition);
			return yformDefinition;
		}
		catch (final ModelSavingException e)
		{
			LOG.debug("Exception while saving yform definition", e);
			throw new YFormServiceException(
					"Update YFormDefinition with applicationId[" + applicationId + "] and formId[" + formId + "] failed.");
		}
	}

	/**
	 * Create a new YFormDefinition by the given parameters. <br/>
	 *
	 * @param applicationId
	 * @param formId
	 * @param title
	 * @param description
	 * @param content
	 * @param documentId
	 * @return YFormDefinitionModel
	 * @throws de.hybris.platform.xyformsservices.exception.YFormServiceException
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public YFormDefinitionModel createYFormDefinition(final String applicationId, final String formId, final String title,
			final String description, final String content, final String documentId) throws YFormServiceException
	{
		try
		{
			final int version = getVersionNumberStrategy.execute(applicationId, formId);
			if (version > 1)
			{
				final YFormDefinitionModel yform = this.getYFormDefinition(applicationId, formId);
				yform.setLatest(false);
				modelService.save(yform);
			}

			final YFormDefinitionModel yformDefinition = modelService.create(YFormDefinitionModel.class);
			yformDefinition.setContent(content);
			yformDefinition.setApplicationId(applicationId);
			yformDefinition.setTitle(title);
			yformDefinition.setDescription(description);
			yformDefinition.setFormId(formId);
			yformDefinition.setVersion(version);
			yformDefinition.setDocumentId(documentId);
			modelService.save(yformDefinition);

			return yformDefinition;
		}
		catch (final ModelSavingException e)
		{
			LOG.debug("Exception while saving yform definition", e);
			throw new YFormServiceException(
					"Create YFormDefinition with applicationId[" + applicationId + "] and formId[" + formId + "] failed.");
		}
	}

	/**
	 * For a given id and type a form data is returned.
	 *
	 * @param formDataId
	 * @param type
	 * @return YFormDataModel
	 */
	@Override
	public YFormDataModel getYFormData(final String formDataId, final YFormDataTypeEnum type) throws YFormServiceException
	{
		validateParameterNotNull(formDataId, "Parameter id must not be null");
		try
		{
			return yformDao.findYFormData(formDataId, type);
		}
		catch (final ModelNotFoundException e)
		{
			throw new YFormServiceException("YFormData with formDataId[" + formDataId + "] does not exist", e);
		}
	}

	/**
	 * For the given parameters a YFormDataModel is returned.
	 *
	 * @param applicationId
	 * @param formId
	 * @param refId
	 * @param type
	 * @return YFormDataModel
	 * @throws YFormServiceException
	 */
	@Override
	public YFormDataModel getYFormData(final String applicationId, final String formId, final String refId,
			final YFormDataTypeEnum type) throws YFormServiceException
	{
		validateParameterNotNull(applicationId, "Parameter applicationId must not be null");
		validateParameterNotNull(formId, "Parameter formId must not be null");
		validateParameterNotNull(refId, "Parameter refId must not be null");
		validateParameterNotNull(type, "Parameter type must not be null");
		try
		{
			return yformDao.findYFormData(applicationId, formId, refId, type);
		}
		catch (final ModelNotFoundException e)
		{
			throw new YFormServiceException("YFormData with applicationId[" + applicationId + "], formId[" + formId + "] , refId["
					+ refId + "], type[" + type.toString() + "]  does not exist", e);
		}
	}

	/**
	 * Create or Update YFormDataModel.
	 *
	 * @param applicationId
	 * @param formId
	 * @param formDataId
	 * @param type
	 * @param refId
	 * @param content
	 * @return YFormDataModel
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public YFormDataModel createOrUpdateYFormData(final String applicationId, final String formId, final String formDataId,
			final YFormDataTypeEnum type, final String refId, final String content) throws YFormServiceException
	{
		validateParameterNotNull(applicationId, "Parameter applicationId must not be null");
		validateParameterNotNull(formId, "Parameter formId must not be null");
		validateParameterNotNull(type, "Parameter type must not be null");

		try
		{
			// If trying to create/update a DATA item, any DRAFT record must be removed
			if (YFormDataTypeEnum.DATA.equals(type))
			{
				final YFormDataModel data = yformDao.findYFormData(formDataId, YFormDataTypeEnum.DRAFT);
				modelService.remove(data);
			}
		}
		catch (final ModelNotFoundException e)
		{
			// Nothing, there is no DRAFT record
			LOG.debug("No DRAFT record found. Ignored.");
		}

		YFormDataModel yformData;
		try
		{
			// We update any existing record
			yformData = updateYFormData(formDataId, type, content, refId);
		}
		catch (final ModelNotFoundException e)
		{
			yformData = createYFormData(applicationId, formId, formDataId, type, refId, content);
		}
		return yformData;
	}

	/**
	 * Update YFormData by the given form data id.
	 *
	 * @param formDataId
	 * @param type
	 * @param content
	 * @return YFormDataModel
	 * @throws ModelNotFoundException
	 * @throws YFormServiceException
	 */
	@Override
	public YFormDataModel updateYFormData(final String formDataId, final YFormDataTypeEnum type, final String content)
			throws YFormServiceException
	{
		return updateYFormData(formDataId, type, content, null);
	}

	/**
	 * Update YFormData by the given form data id.
	 *
	 * @param formDataId form data identifier
	 * @param type type of the form (DATA, DRAFT)
	 * @param content form content
	 * @param refId reference id of the form
	 * @return YFormDataModel
	 * @throws ModelNotFoundException throws exception if not found
	 * @throws YFormServiceException throws exception if error during saving
	 */
	public YFormDataModel updateYFormData(final String formDataId, final YFormDataTypeEnum type, final String content, final String refId)
			throws YFormServiceException
	{
		try
		{
			final YFormDataModel yformData = yformDao.findYFormData(formDataId, type);

			// Form data is updated
			yformData.setContent(content);
			if (refId != null)
			{
				yformData.setRefId(refId);
			}
			modelService.save(yformData);

			return yformData;
		}
		catch (final ModelSavingException e)
		{
			throw new YFormServiceException(
					"Update YFormData with formDataId[" + formDataId + "] and content[" + content + "] failed.", e);
		}
	}

	/**
	 * Create YFormData if no previous exists. <br/>
	 * It also assigns it to the corresponding YFormDefinition.
	 *
	 * @param applicationId
	 * @param formId
	 * @param formDataId
	 * @param type
	 * @param refId
	 * @param content
	 * @return YFormDataModel
	 */
	@Override
	public YFormDataModel createYFormData(final String applicationId, final String formId, final String formDataId,
			final YFormDataTypeEnum type, final String refId, final String content) throws YFormServiceException
	{
		return this.createYFormData(applicationId, formId, formDataId, type, refId, content, applicationId, formId);
	}

	/**
	 * Create YFormData if no previous exists. <br/>
	 * It also assigns it to the corresponding YFormDefinition.
	 *
	 * @param applicationId
	 * @param formId
	 * @param formDataId
	 * @param type
	 * @param refId
	 * @param content
	 * @param ownerApplicationId
	 * @param ownerFormId
	 * @return YFormDataModel
	 */
	@Override
	public YFormDataModel createYFormData(final String applicationId, final String formId, final String formDataId,
			final YFormDataTypeEnum type, final String refId, final String content, final String ownerApplicationId,
			final String ownerFormId) throws YFormServiceException
	{
		return this.createYFormData(applicationId, formId, formDataId, type, refId, content, ownerApplicationId, ownerFormId,
				false);
	}

	/**
	 * Create YFormData if no previous exists. <br/>
	 * It also assigns it to the corresponding YFormDefinition.
	 *
	 * @param applicationId
	 * @param formId
	 * @param formDataId
	 * @param type
	 * @param refId
	 * @param content
	 * @param ownerApplicationId
	 * @param ownerFormId
	 * @param system
	 * @return YFormDataModel
	 */
	@Override
	public YFormDataModel createYFormData(final String applicationId, final String formId, final String formDataId,
			final YFormDataTypeEnum type, final String refId, final String content, final String ownerApplicationId,
			final String ownerFormId, final boolean system) throws YFormServiceException
	{
		final YFormDataModel yformDataModel = modelService.create(YFormDataModel.class);
		yformDataModel.setContent(content);
		yformDataModel.setId(formDataId);
		yformDataModel.setRefId(refId);
		yformDataModel.setType(type);
		yformDataModel.setApplicationId(ownerApplicationId);
		yformDataModel.setFormId(ownerFormId);
		yformDataModel.setSystem(system);
		final YFormDefinitionModel yformDefinition = getYFormDefinition(applicationId, formId);
		yformDataModel.setFormDefinition(yformDefinition);
		try
		{
			modelService.saveAll();
		}
		catch (final ModelSavingException e)
		{
			LOG.debug("Exception while saving yform definition", e);
			throw new YFormServiceException(
					"Update YFormData with formDataId[" + formDataId + "] and content[" + content + "] failed.");
		}
		return yformDataModel;
	}

	/**
	 * For the given refId a list of YFormDataModel is returned.
	 *
	 * @param refId
	 * @return List<YFormDataModel>
	 */
	@Override
	public List<YFormDataModel> getYFormDataByRefId(final String refId)
	{
		return getYFormDao().findYFormDataByRefId(refId);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void setFormDefinitionStatus(final String applicationId, final String formId, final YFormDefinitionStatusEnum status)
	{
		final List<YFormDefinitionModel> definitions = yformDao.findYFormDefinitions(applicationId, formId);
		for (final YFormDefinitionModel form : definitions)
		{
			form.setStatus(status);
			modelService.save(form);
		}
	}

	@Override
	public YFormDefinitionModel createOrUpdateYFormDefinition(final String applicationId, final String formId, final String title,
			final String description, final String content, final String documentId, final String version)
			throws YFormServiceException
	{
		validateParameterNotNull(applicationId, APPLICATION_ID_NOTNULL);
		validateParameterNotNull(formId, FORM_ID_NOTNULL);

		YFormDefinitionModel yformDefinition = null;
		boolean needToCreateNewForm = false;

		try
		{
			// Find form definition by applicationId, formId and version
			yformDefinition = yformDao.findYFormDefinition(applicationId, formId, Integer.parseInt(version));
		}
		catch (final UnknownIdentifierException | ModelNotFoundException e)
		{
			needToCreateNewForm = true;
		}

		if (needToCreateNewForm) {
			// set old forms to not latest
			try {
				final List<YFormDefinitionModel> oldForms = yformDao.findYFormDefinitions(applicationId, formId);
				oldForms.stream().filter(YFormDefinitionModel::getLatest).forEach(oldForm -> {
					oldForm.setLatest(false);
					modelService.save(oldForm);
				});
			}
			catch (final UnknownIdentifierException | ModelNotFoundException e)
			{
				LOG.debug(YFORM_WITH_ID + URLEncoder.encode(applicationId, StandardCharsets.UTF_8) + ":" + URLEncoder.encode(formId, StandardCharsets.UTF_8) + NOT_EXIST, e);
			}

			// create new form definition with version
			yformDefinition = modelService.create(YFormDefinitionModel.class);
			yformDefinition.setContent(content);
			yformDefinition.setApplicationId(applicationId);
			yformDefinition.setTitle(title);
			yformDefinition.setDescription(description);
			yformDefinition.setFormId(formId);
			yformDefinition.setVersion(Integer.parseInt(version));
			yformDefinition.setDocumentId(documentId);
		} else {
			// update existing form definition
			yformDefinition.setContent(content);
			yformDefinition.setDocumentId(documentId);
			yformDefinition.setTitle(title);
			yformDefinition.setDescription(description);
		}

		try {
			modelService.save(yformDefinition);
		}
		catch (final ModelSavingException e)
		{
			LOG.debug(EXCEPTION_WHILE_SAVE, e);
			throw new YFormServiceException(
					"createOrUpdateYFormDefinition with applicationId[" + applicationId + FORM_ID + formId + FAILED);
		}

		return yformDefinition;
	}

	protected ModelService getModelService()
	{
		return modelService;
	}

	@Required
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	protected YFormDao getYFormDao()
	{
		return yformDao;
	}

	@Required
	public void setYFormDao(final YFormDao yformDao)
	{
		this.yformDao = yformDao;
	}

	protected GetVersionNumberStrategy getGetVersionNumberStrategy()
	{
		return getVersionNumberStrategy;
	}

	@Required
	public void setGetVersionNumberStrategy(final GetVersionNumberStrategy getVersionNumberStrategy)
	{
		this.getVersionNumberStrategy = getVersionNumberStrategy;
	}
}
