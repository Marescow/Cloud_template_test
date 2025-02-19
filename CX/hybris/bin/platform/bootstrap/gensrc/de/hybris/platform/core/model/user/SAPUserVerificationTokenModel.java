/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 13 févr. 2025, 16:27:28                     ---
 * ----------------------------------------------------------------
 *  
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.core.model.user;

import de.hybris.bootstrap.annotations.Accessor;
import de.hybris.platform.core.enums.SAPUserVerificationPurpose;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.servicelayer.model.ItemModelContext;
import java.util.Date;

/**
 * Generated model class for type SAPUserVerificationToken first defined at extension core.
 */
@SuppressWarnings("all")
public class SAPUserVerificationTokenModel extends ItemModel
{
	/**<i>Generated model type code constant.</i>*/
	public static final String _TYPECODE = "SAPUserVerificationToken";
	
	/** <i>Generated constant</i> - Attribute key of <code>SAPUserVerificationToken.hashedTokenId</code> attribute defined at extension <code>core</code>. */
	public static final String HASHEDTOKENID = "hashedTokenId";
	
	/** <i>Generated constant</i> - Attribute key of <code>SAPUserVerificationToken.encodedTokenCode</code> attribute defined at extension <code>core</code>. */
	public static final String ENCODEDTOKENCODE = "encodedTokenCode";
	
	/** <i>Generated constant</i> - Attribute key of <code>SAPUserVerificationToken.tokenEncoding</code> attribute defined at extension <code>core</code>. */
	public static final String TOKENENCODING = "tokenEncoding";
	
	/** <i>Generated constant</i> - Attribute key of <code>SAPUserVerificationToken.uid</code> attribute defined at extension <code>core</code>. */
	public static final String UID = "uid";
	
	/** <i>Generated constant</i> - Attribute key of <code>SAPUserVerificationToken.verificationPurpose</code> attribute defined at extension <code>core</code>. */
	public static final String VERIFICATIONPURPOSE = "verificationPurpose";
	
	/** <i>Generated constant</i> - Attribute key of <code>SAPUserVerificationToken.expirationTime</code> attribute defined at extension <code>core</code>. */
	public static final String EXPIRATIONTIME = "expirationTime";
	
	/** <i>Generated constant</i> - Attribute key of <code>SAPUserVerificationToken.failedVerificationAttempts</code> attribute defined at extension <code>core</code>. */
	public static final String FAILEDVERIFICATIONATTEMPTS = "failedVerificationAttempts";
	
	
	/**
	 * <i>Generated constructor</i> - Default constructor for generic creation.
	 */
	public SAPUserVerificationTokenModel()
	{
		super();
	}
	
	/**
	 * <i>Generated constructor</i> - Default constructor for creation with existing context
	 * @param ctx the model context to be injected, must not be null
	 */
	public SAPUserVerificationTokenModel(final ItemModelContext ctx)
	{
		super(ctx);
	}
	
	/**
	 * <i>Generated constructor</i> - Constructor with all mandatory attributes.
	 * @deprecated since 4.1.1 Please use the default constructor without parameters
	 * @param _encodedTokenCode initial attribute declared by type <code>SAPUserVerificationToken</code> at extension <code>core</code>
	 * @param _expirationTime initial attribute declared by type <code>SAPUserVerificationToken</code> at extension <code>core</code>
	 * @param _hashedTokenId initial attribute declared by type <code>SAPUserVerificationToken</code> at extension <code>core</code>
	 * @param _uid initial attribute declared by type <code>SAPUserVerificationToken</code> at extension <code>core</code>
	 * @param _verificationPurpose initial attribute declared by type <code>SAPUserVerificationToken</code> at extension <code>core</code>
	 */
	@Deprecated(since = "4.1.1", forRemoval = true)
	public SAPUserVerificationTokenModel(final String _encodedTokenCode, final Date _expirationTime, final String _hashedTokenId, final String _uid, final SAPUserVerificationPurpose _verificationPurpose)
	{
		super();
		setEncodedTokenCode(_encodedTokenCode);
		setExpirationTime(_expirationTime);
		setHashedTokenId(_hashedTokenId);
		setUid(_uid);
		setVerificationPurpose(_verificationPurpose);
	}
	
	/**
	 * <i>Generated constructor</i> - for all mandatory and initial attributes.
	 * @deprecated since 4.1.1 Please use the default constructor without parameters
	 * @param _encodedTokenCode initial attribute declared by type <code>SAPUserVerificationToken</code> at extension <code>core</code>
	 * @param _expirationTime initial attribute declared by type <code>SAPUserVerificationToken</code> at extension <code>core</code>
	 * @param _hashedTokenId initial attribute declared by type <code>SAPUserVerificationToken</code> at extension <code>core</code>
	 * @param _owner initial attribute declared by type <code>Item</code> at extension <code>core</code>
	 * @param _uid initial attribute declared by type <code>SAPUserVerificationToken</code> at extension <code>core</code>
	 * @param _verificationPurpose initial attribute declared by type <code>SAPUserVerificationToken</code> at extension <code>core</code>
	 */
	@Deprecated(since = "4.1.1", forRemoval = true)
	public SAPUserVerificationTokenModel(final String _encodedTokenCode, final Date _expirationTime, final String _hashedTokenId, final ItemModel _owner, final String _uid, final SAPUserVerificationPurpose _verificationPurpose)
	{
		super();
		setEncodedTokenCode(_encodedTokenCode);
		setExpirationTime(_expirationTime);
		setHashedTokenId(_hashedTokenId);
		setOwner(_owner);
		setUid(_uid);
		setVerificationPurpose(_verificationPurpose);
	}
	
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SAPUserVerificationToken.encodedTokenCode</code> attribute defined at extension <code>core</code>. 
	 * @return the encodedTokenCode
	 */
	@Accessor(qualifier = "encodedTokenCode", type = Accessor.Type.GETTER)
	public String getEncodedTokenCode()
	{
		return getPersistenceContext().getPropertyValue(ENCODEDTOKENCODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SAPUserVerificationToken.expirationTime</code> attribute defined at extension <code>core</code>. 
	 * @return the expirationTime - The date/time when the one time token will expire
	 */
	@Accessor(qualifier = "expirationTime", type = Accessor.Type.GETTER)
	public Date getExpirationTime()
	{
		return getPersistenceContext().getPropertyValue(EXPIRATIONTIME);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SAPUserVerificationToken.failedVerificationAttempts</code> attribute defined at extension <code>core</code>. 
	 * @return the failedVerificationAttempts
	 */
	@Accessor(qualifier = "failedVerificationAttempts", type = Accessor.Type.GETTER)
	public Integer getFailedVerificationAttempts()
	{
		return getPersistenceContext().getPropertyValue(FAILEDVERIFICATIONATTEMPTS);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SAPUserVerificationToken.hashedTokenId</code> attribute defined at extension <code>core</code>. 
	 * @return the hashedTokenId
	 */
	@Accessor(qualifier = "hashedTokenId", type = Accessor.Type.GETTER)
	public String getHashedTokenId()
	{
		return getPersistenceContext().getPropertyValue(HASHEDTOKENID);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SAPUserVerificationToken.tokenEncoding</code> attribute defined at extension <code>core</code>. 
	 * @return the tokenEncoding
	 */
	@Accessor(qualifier = "tokenEncoding", type = Accessor.Type.GETTER)
	public String getTokenEncoding()
	{
		return getPersistenceContext().getPropertyValue(TOKENENCODING);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SAPUserVerificationToken.uid</code> attribute defined at extension <code>core</code>. 
	 * @return the uid
	 */
	@Accessor(qualifier = "uid", type = Accessor.Type.GETTER)
	public String getUid()
	{
		return getPersistenceContext().getPropertyValue(UID);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SAPUserVerificationToken.verificationPurpose</code> attribute defined at extension <code>core</code>. 
	 * @return the verificationPurpose - Verification purpose of the token.
	 */
	@Accessor(qualifier = "verificationPurpose", type = Accessor.Type.GETTER)
	public SAPUserVerificationPurpose getVerificationPurpose()
	{
		return getPersistenceContext().getPropertyValue(VERIFICATIONPURPOSE);
	}
	
	/**
	 * <i>Generated method</i> - Initial setter of <code>SAPUserVerificationToken.encodedTokenCode</code> attribute defined at extension <code>core</code>. Can only be used at creation of model - before first save.  
	 *  
	 * @param value the encodedTokenCode
	 */
	@Accessor(qualifier = "encodedTokenCode", type = Accessor.Type.SETTER)
	public void setEncodedTokenCode(final String value)
	{
		getPersistenceContext().setPropertyValue(ENCODEDTOKENCODE, value);
	}
	
	/**
	 * <i>Generated method</i> - Initial setter of <code>SAPUserVerificationToken.expirationTime</code> attribute defined at extension <code>core</code>. Can only be used at creation of model - before first save.  
	 *  
	 * @param value the expirationTime - The date/time when the one time token will expire
	 */
	@Accessor(qualifier = "expirationTime", type = Accessor.Type.SETTER)
	public void setExpirationTime(final Date value)
	{
		getPersistenceContext().setPropertyValue(EXPIRATIONTIME, value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>SAPUserVerificationToken.failedVerificationAttempts</code> attribute defined at extension <code>core</code>. 
	 *  
	 * @param value the failedVerificationAttempts
	 */
	@Accessor(qualifier = "failedVerificationAttempts", type = Accessor.Type.SETTER)
	public void setFailedVerificationAttempts(final Integer value)
	{
		getPersistenceContext().setPropertyValue(FAILEDVERIFICATIONATTEMPTS, value);
	}
	
	/**
	 * <i>Generated method</i> - Initial setter of <code>SAPUserVerificationToken.hashedTokenId</code> attribute defined at extension <code>core</code>. Can only be used at creation of model - before first save.  
	 *  
	 * @param value the hashedTokenId
	 */
	@Accessor(qualifier = "hashedTokenId", type = Accessor.Type.SETTER)
	public void setHashedTokenId(final String value)
	{
		getPersistenceContext().setPropertyValue(HASHEDTOKENID, value);
	}
	
	/**
	 * <i>Generated method</i> - Initial setter of <code>SAPUserVerificationToken.tokenEncoding</code> attribute defined at extension <code>core</code>. Can only be used at creation of model - before first save.  
	 *  
	 * @param value the tokenEncoding
	 */
	@Accessor(qualifier = "tokenEncoding", type = Accessor.Type.SETTER)
	public void setTokenEncoding(final String value)
	{
		getPersistenceContext().setPropertyValue(TOKENENCODING, value);
	}
	
	/**
	 * <i>Generated method</i> - Initial setter of <code>SAPUserVerificationToken.uid</code> attribute defined at extension <code>core</code>. Can only be used at creation of model - before first save.  
	 *  
	 * @param value the uid
	 */
	@Accessor(qualifier = "uid", type = Accessor.Type.SETTER)
	public void setUid(final String value)
	{
		getPersistenceContext().setPropertyValue(UID, value);
	}
	
	/**
	 * <i>Generated method</i> - Initial setter of <code>SAPUserVerificationToken.verificationPurpose</code> attribute defined at extension <code>core</code>. Can only be used at creation of model - before first save.  
	 *  
	 * @param value the verificationPurpose - Verification purpose of the token.
	 */
	@Accessor(qualifier = "verificationPurpose", type = Accessor.Type.SETTER)
	public void setVerificationPurpose(final SAPUserVerificationPurpose value)
	{
		getPersistenceContext().setPropertyValue(VERIFICATIONPURPOSE, value);
	}
	
}
