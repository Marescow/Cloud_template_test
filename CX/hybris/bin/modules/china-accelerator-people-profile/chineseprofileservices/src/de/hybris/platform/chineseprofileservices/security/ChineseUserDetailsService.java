/*
 * Copyright (c) 2024 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.chineseprofileservices.security;

import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.security.PrincipalGroup;
import de.hybris.platform.jalo.user.User;
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.daos.UserDao;
import de.hybris.platform.spring.security.CoreUserDetails;
import de.hybris.platform.spring.security.CoreUserDetailsService;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


/**
 * Implementation for {@link CoreUserDetailsService}. Delivers main functionality for chinese user details.
 */
public class ChineseUserDetailsService extends CoreUserDetailsService
{

	private String rolePrefix = "ROLE_";

	private UserDao userDao;

	private CommonI18NService commonI18NService;

	private ModelService modelService;

	@Override
	public CoreUserDetails loadUserByUsername(final String username)
	{
		CoreUserDetails userDetails;
		try
		{
			userDetails = super.loadUserByUsername(username);
		}
		catch (UsernameNotFoundException e)
		{
			userDetails = findUserByUID(username);
		}
		return userDetails;
	}

	protected boolean isUserDeactivated(final User user)
	{
		return user.getDeactivationDate() != null && user.getDeactivationDate().toInstant().isBefore(Instant.now());
	}

	protected Collection<GrantedAuthority> getAuthoritiesByUser(final User user)
	{
		final Set<PrincipalGroup> groups = user.getGroups();
		final Collection<GrantedAuthority> authorities = new ArrayList<>(groups.size());
		final Iterator<PrincipalGroup> itr = groups.iterator();
		while (itr.hasNext())
		{
			final PrincipalGroup group = itr.next();
			authorities.add(new SimpleGrantedAuthority(rolePrefix + group.getUid().toUpperCase(Locale.getDefault())));
			for (final PrincipalGroup gr : group.getAllGroups())
			{
				authorities.add(new SimpleGrantedAuthority(rolePrefix + gr.getUid().toUpperCase(Locale.getDefault())));
			}
		}
		return authorities;
	}

	protected CoreUserDetails findUserByUID(final String userUid)
	{
		UserModel user;
		try
		{
			user = userDao.findUserByUID(userUid);
		}
		catch (final ModelNotFoundException e)
		{
			throw new UsernameNotFoundException("User not found!");
		}
		if (Objects.isNull(user))
		{
			throw new UsernameNotFoundException("User not found!");
		}

		final User sourceUser = modelService.getSource(user);
		final boolean enabled = isNotAnonymousOrAnonymousLoginIsAllowed(sourceUser) && !sourceUser.isLoginDisabledAsPrimitive()
				&& !this.isUserDeactivated(sourceUser);
		final boolean accountNonExpired = true;
		final boolean credentialsNonExpired = true;
		final boolean accountNonLocked = true;
		String password = sourceUser.getEncodedPassword(JaloSession.getCurrentSession().getSessionContext());
		// a null value has to be transformed to empty string, otherwise the constructor
		// of org.springframework.security.userdetails.User will fail
		if (password == null)
		{
			password = StringUtils.EMPTY;
		}

		return new CoreUserDetails(user.getUid(), password, enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked,
				getAuthoritiesByUser(sourceUser), getCommonI18NService().getCurrentLanguage().getIsocode(), null);
	}

	@Required
	public void setUserDao(final UserDao userDao)
	{
		this.userDao = userDao;
	}

	@Required
	public void setCommonI18NService(final CommonI18NService commonI18NService)
	{
		this.commonI18NService = commonI18NService;
	}

	@Override
	public void setRolePrefix(final String rolePrefix)
	{
		this.rolePrefix = rolePrefix;
	}

	public UserDao getUserDao()
	{
		return userDao;
	}

	public CommonI18NService getCommonI18NService()
	{
		return commonI18NService;
	}

	protected ModelService getModelService()
	{
		return modelService;
	}

	@Override
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

}