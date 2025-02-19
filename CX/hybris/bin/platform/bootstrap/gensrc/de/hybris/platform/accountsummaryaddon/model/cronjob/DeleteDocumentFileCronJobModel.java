/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 13 févr. 2025, 16:27:28                     ---
 * ----------------------------------------------------------------
 *  
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.accountsummaryaddon.model.cronjob;

import de.hybris.bootstrap.annotations.Accessor;
import de.hybris.platform.accountsummaryaddon.enums.DocumentStatus;
import de.hybris.platform.accountsummaryaddon.model.B2BDocumentTypeModel;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.cronjob.model.JobModel;
import de.hybris.platform.servicelayer.model.ItemModelContext;
import java.util.List;

/**
 * Generated model class for type DeleteDocumentFileCronJob first defined at extension accountsummaryaddon.
 */
@SuppressWarnings("all")
public class DeleteDocumentFileCronJobModel extends CronJobModel
{
	/**<i>Generated model type code constant.</i>*/
	public static final String _TYPECODE = "DeleteDocumentFileCronJob";
	
	/** <i>Generated constant</i> - Attribute key of <code>DeleteDocumentFileCronJob.numberOfDay</code> attribute defined at extension <code>accountsummaryaddon</code>. */
	public static final String NUMBEROFDAY = "numberOfDay";
	
	/** <i>Generated constant</i> - Attribute key of <code>DeleteDocumentFileCronJob.documentStatusList</code> attribute defined at extension <code>accountsummaryaddon</code>. */
	public static final String DOCUMENTSTATUSLIST = "documentStatusList";
	
	/** <i>Generated constant</i> - Attribute key of <code>DeleteDocumentFileCronJob.documentTypeList</code> attribute defined at extension <code>accountsummaryaddon</code>. */
	public static final String DOCUMENTTYPELIST = "documentTypeList";
	
	
	/**
	 * <i>Generated constructor</i> - Default constructor for generic creation.
	 */
	public DeleteDocumentFileCronJobModel()
	{
		super();
	}
	
	/**
	 * <i>Generated constructor</i> - Default constructor for creation with existing context
	 * @param ctx the model context to be injected, must not be null
	 */
	public DeleteDocumentFileCronJobModel(final ItemModelContext ctx)
	{
		super(ctx);
	}
	
	/**
	 * <i>Generated constructor</i> - Constructor with all mandatory attributes.
	 * @deprecated since 4.1.1 Please use the default constructor without parameters
	 * @param _job initial attribute declared by type <code>CronJob</code> at extension <code>processing</code>
	 */
	@Deprecated(since = "4.1.1", forRemoval = true)
	public DeleteDocumentFileCronJobModel(final JobModel _job)
	{
		super();
		setJob(_job);
	}
	
	/**
	 * <i>Generated constructor</i> - for all mandatory and initial attributes.
	 * @deprecated since 4.1.1 Please use the default constructor without parameters
	 * @param _job initial attribute declared by type <code>CronJob</code> at extension <code>processing</code>
	 * @param _owner initial attribute declared by type <code>Item</code> at extension <code>core</code>
	 */
	@Deprecated(since = "4.1.1", forRemoval = true)
	public DeleteDocumentFileCronJobModel(final JobModel _job, final ItemModel _owner)
	{
		super();
		setJob(_job);
		setOwner(_owner);
	}
	
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DeleteDocumentFileCronJob.documentStatusList</code> attribute defined at extension <code>accountsummaryaddon</code>. 
	 * Consider using FlexibleSearchService::searchRelation for pagination support of large result sets.
	 * @return the documentStatusList
	 */
	@Accessor(qualifier = "documentStatusList", type = Accessor.Type.GETTER)
	public List<DocumentStatus> getDocumentStatusList()
	{
		return getPersistenceContext().getPropertyValue(DOCUMENTSTATUSLIST);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DeleteDocumentFileCronJob.documentTypeList</code> attribute defined at extension <code>accountsummaryaddon</code>. 
	 * Consider using FlexibleSearchService::searchRelation for pagination support of large result sets.
	 * @return the documentTypeList
	 */
	@Accessor(qualifier = "documentTypeList", type = Accessor.Type.GETTER)
	public List<B2BDocumentTypeModel> getDocumentTypeList()
	{
		return getPersistenceContext().getPropertyValue(DOCUMENTTYPELIST);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DeleteDocumentFileCronJob.numberOfDay</code> attribute defined at extension <code>accountsummaryaddon</code>. 
	 * @return the numberOfDay - number of day to keep files
	 */
	@Accessor(qualifier = "numberOfDay", type = Accessor.Type.GETTER)
	public Integer getNumberOfDay()
	{
		return getPersistenceContext().getPropertyValue(NUMBEROFDAY);
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>DeleteDocumentFileCronJob.documentStatusList</code> attribute defined at extension <code>accountsummaryaddon</code>. 
	 *  
	 * @param value the documentStatusList
	 */
	@Accessor(qualifier = "documentStatusList", type = Accessor.Type.SETTER)
	public void setDocumentStatusList(final List<DocumentStatus> value)
	{
		getPersistenceContext().setPropertyValue(DOCUMENTSTATUSLIST, value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>DeleteDocumentFileCronJob.documentTypeList</code> attribute defined at extension <code>accountsummaryaddon</code>. 
	 *  
	 * @param value the documentTypeList
	 */
	@Accessor(qualifier = "documentTypeList", type = Accessor.Type.SETTER)
	public void setDocumentTypeList(final List<B2BDocumentTypeModel> value)
	{
		getPersistenceContext().setPropertyValue(DOCUMENTTYPELIST, value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>DeleteDocumentFileCronJob.numberOfDay</code> attribute defined at extension <code>accountsummaryaddon</code>. 
	 *  
	 * @param value the numberOfDay - number of day to keep files
	 */
	@Accessor(qualifier = "numberOfDay", type = Accessor.Type.SETTER)
	public void setNumberOfDay(final Integer value)
	{
		getPersistenceContext().setPropertyValue(NUMBEROFDAY, value);
	}
	
}
