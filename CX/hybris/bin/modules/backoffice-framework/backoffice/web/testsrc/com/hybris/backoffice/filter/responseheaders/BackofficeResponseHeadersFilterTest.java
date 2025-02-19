/*
 * Copyright (c) 2020 SAP SE or an SAP affiliate company. All rights reserved
 */
package com.hybris.backoffice.filter.responseheaders;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import de.hybris.bootstrap.annotations.UnitTest;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
@UnitTest
public class BackofficeResponseHeadersFilterTest
{
	@Mock
	HttpServletRequest servletRequest;
	@Mock
	HttpServletResponse servletResponse;
	@Mock
	FilterChain filterChain;
	@Mock
	private BackofficeResponseHeadersHandler backofficeResponseHeadersHandler;

	@InjectMocks
	private final BackofficeResponseHeadersFilter backofficeResponseHeadersFilter = new BackofficeResponseHeadersFilter();

	@Test
	public void testDoFilter() throws IOException, ServletException
	{
		// when
		when((servletRequest.getContextPath())).thenReturn("contextPath");
		when((servletRequest.getRequestURI())).thenReturn("contextPath");
		backofficeResponseHeadersFilter.doFilter(servletRequest, servletResponse, filterChain);

		// then
		verify(backofficeResponseHeadersHandler).handleResponseHeaders(servletRequest, servletResponse);
		verify(filterChain).doFilter(servletRequest, servletResponse);
	}

	@Test
	public void testDoFilterWhenZKReview() throws IOException, ServletException
	{
		// when
		when((servletRequest.getContextPath())).thenReturn("contextPath");
		when((servletRequest.getRequestURI())).thenReturn("contextPath/zkau/view/");
		backofficeResponseHeadersFilter.doFilter(servletRequest, servletResponse, filterChain);

		// then
		verify(servletResponse, times(1)).setHeader("content-security-policy", "sandbox");
	}

	@Test(expected = ServletException.class)
	public void testDoFilterWithServletException() throws IOException, ServletException
	{
		// given
		when((servletRequest.getContextPath())).thenReturn("contextPath");
		when((servletRequest.getRequestURI())).thenReturn("contextPath");
		doThrow(ServletException.class).when(filterChain).doFilter(servletRequest, servletResponse);

		// when
		backofficeResponseHeadersFilter.doFilter(servletRequest, servletResponse, filterChain);

		// then ServletException is thrown
	}

	@Test(expected = IOException.class)
	public void testDoFilterWithIOException() throws IOException, ServletException
	{
		// given
		when((servletRequest.getContextPath())).thenReturn("contextPath");
		when((servletRequest.getRequestURI())).thenReturn("contextPath");
		doThrow(IOException.class).when(filterChain).doFilter(servletRequest, servletResponse);

		// when
		backofficeResponseHeadersFilter.doFilter(servletRequest, servletResponse, filterChain);

		// then IOException is thrown
	}

}
