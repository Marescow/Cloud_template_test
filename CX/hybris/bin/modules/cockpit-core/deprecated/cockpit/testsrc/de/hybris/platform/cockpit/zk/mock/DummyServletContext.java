/*
 * Copyright (c) 2024 SAP SE or an SAP affiliate company. All rights reserved
 */
package de.hybris.platform.cockpit.zk.mock;

import javax.servlet.*;
import javax.servlet.FilterRegistration.Dynamic;
import javax.servlet.descriptor.JspConfigDescriptor;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;


/**
 * @author Jacek
 */
public class DummyServletContext implements ServletContext
{
	Map<String, Object> map = new HashMap<String, Object>();

	@Override
	public Object getAttribute(final String name)
	{
		return map.get(name);
	}

	@Override
	public Enumeration getAttributeNames()
	{
		return null;
	}

	@Override
	public ServletContext getContext(final String uripath)
	{
		return null;
	}

	public String getContextPath()
	{
		return null;
	}

	@Override
	public String getInitParameter(final String name)
	{
		return null;
	}

	@Override
	public Enumeration getInitParameterNames()
	{
		return null;
	}

	@Override
	public int getMajorVersion()
	{
		return 0;
	}

	@Override
	public String getMimeType(final String file)
	{
		return null;
	}

	@Override
	public int getMinorVersion()
	{
		return 0;
	}

	@Override
	public RequestDispatcher getNamedDispatcher(final String name)
	{
		return null;
	}

	@Override
	public String getRealPath(final String path)
	{
		return null;
	}

	@Override
	public RequestDispatcher getRequestDispatcher(final String path)
	{
		return null;
	}

	@Override
	public URL getResource(final String path) throws MalformedURLException
	{
		return null;
	}

	@Override
	public InputStream getResourceAsStream(final String path)
	{
		return null;
	}

	@Override
	public Set getResourcePaths(final String path)
	{
		return null;
	}

	@Override
	public String getServerInfo()
	{
		return null;
	}

	@Override
	public Servlet getServlet(final String name) throws ServletException
	{
		return null;
	}

	@Override
	public String getServletContextName()
	{
		return null;
	}

	@Override
	public Enumeration getServletNames()
	{
		return null;
	}

	@Override
	public Enumeration getServlets()
	{
		return null;
	}

	@Override
	public void log(final String msg)
	{//
	}

	@Override
	public void log(final Exception exception, final String msg)
	{//
	}

	@Override
	public void log(final String message, final Throwable throwable)
	{//
	}

	@Override
	public void removeAttribute(final String name)
	{//
	}

	@Override
	public void setAttribute(final String name, final Object object)
	{
		map.put(name, object);
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.ServletContext#addFilter(java.lang.String, java.lang.String)
	 */
	@Override
	public Dynamic addFilter(final String arg0, final String arg1)
	{
		// YTODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.ServletContext#addFilter(java.lang.String, javax.servlet.Filter)
	 */
	@Override
	public Dynamic addFilter(final String arg0, final Filter arg1)
	{
		// YTODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.ServletContext#addFilter(java.lang.String, java.lang.Class)
	 */
	@Override
	public Dynamic addFilter(final String arg0, final Class<? extends Filter> arg1)
	{
		// YTODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.ServletContext#addListener(java.lang.Class)
	 */
	@Override
	public void addListener(final Class<? extends EventListener> arg0)
	{
		// YTODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.ServletContext#addListener(java.lang.String)
	 */
	@Override
	public void addListener(final String arg0)
	{
		// YTODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.ServletContext#addListener(java.util.EventListener)
	 */
	@Override
	public <T extends EventListener> void addListener(final T arg0)
	{
		// YTODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.ServletContext#addServlet(java.lang.String, java.lang.String)
	 */
	@Override
	public javax.servlet.ServletRegistration.Dynamic addServlet(final String arg0, final String arg1)
	{
		// YTODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.ServletContext#addServlet(java.lang.String, javax.servlet.Servlet)
	 */
	@Override
	public javax.servlet.ServletRegistration.Dynamic addServlet(final String arg0, final Servlet arg1)
	{
		// YTODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.ServletContext#addServlet(java.lang.String, java.lang.Class)
	 */
	@Override
	public javax.servlet.ServletRegistration.Dynamic addServlet(final String arg0, final Class<? extends Servlet> arg1)
	{
		// YTODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.ServletContext#createFilter(java.lang.Class)
	 */
	@Override
	public <T extends Filter> T createFilter(final Class<T> arg0) throws ServletException
	{
		// YTODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.ServletContext#createListener(java.lang.Class)
	 */
	@Override
	public <T extends EventListener> T createListener(final Class<T> arg0) throws ServletException
	{
		// YTODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.ServletContext#createServlet(java.lang.Class)
	 */
	@Override
	public <T extends Servlet> T createServlet(final Class<T> arg0) throws ServletException
	{
		// YTODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.ServletContext#declareRoles(java.lang.String[])
	 */
	@Override
	public void declareRoles(final String... arg0)
	{
		// YTODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.ServletContext#getClassLoader()
	 */
	@Override
	public ClassLoader getClassLoader()
	{
		// YTODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.ServletContext#getDefaultSessionTrackingModes()
	 */
	@Override
	public Set<SessionTrackingMode> getDefaultSessionTrackingModes()
	{
		// YTODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.ServletContext#getEffectiveMajorVersion()
	 */
	@Override
	public int getEffectiveMajorVersion()
	{
		// YTODO Auto-generated method stub
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.ServletContext#getEffectiveMinorVersion()
	 */
	@Override
	public int getEffectiveMinorVersion()
	{
		// YTODO Auto-generated method stub
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.ServletContext#getEffectiveSessionTrackingModes()
	 */
	@Override
	public Set<SessionTrackingMode> getEffectiveSessionTrackingModes()
	{
		// YTODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.ServletContext#getFilterRegistration(java.lang.String)
	 */
	@Override
	public FilterRegistration getFilterRegistration(final String arg0)
	{
		// YTODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.ServletContext#getFilterRegistrations()
	 */
	@Override
	public Map<String, ? extends FilterRegistration> getFilterRegistrations()
	{
		// YTODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.ServletContext#getJspConfigDescriptor()
	 */
	@Override
	public JspConfigDescriptor getJspConfigDescriptor()
	{
		// YTODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.ServletContext#getServletRegistration(java.lang.String)
	 */
	@Override
	public ServletRegistration getServletRegistration(final String arg0)
	{
		// YTODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.ServletContext#getServletRegistrations()
	 */
	@Override
	public Map<String, ? extends ServletRegistration> getServletRegistrations()
	{
		// YTODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.ServletContext#getSessionCookieConfig()
	 */
	@Override
	public SessionCookieConfig getSessionCookieConfig()
	{
		// YTODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.ServletContext#setInitParameter(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean setInitParameter(final String arg0, final String arg1)
	{
		// YTODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.ServletContext#setSessionTrackingModes(java.util.Set)
	 */
	@Override
	public void setSessionTrackingModes(final Set<SessionTrackingMode> arg0) throws IllegalStateException, IllegalArgumentException
	{
		// YTODO Auto-generated method stub

	}

	@Override
	public String getVirtualServerName()
	{
		return null;
	}

	@Override
	public int getSessionTimeout()
	{
		return 0;
	}

	@Override
	public void setSessionTimeout(final int i)
	{
		//
	}

	@Override
	public String getRequestCharacterEncoding()
	{
		return null;
	}

	@Override
	public void setRequestCharacterEncoding(final String s)
	{
		//
	}

	@Override
	public String getResponseCharacterEncoding()
	{
		return null;
	}

	@Override
	public void setResponseCharacterEncoding(final String s)
	{
		//
	}

	@Override
	public ServletRegistration.Dynamic addJspFile(final String s, final String s1)
	{
		return null;
	}
}