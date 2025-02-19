/*
 * Copyright (c) 2024 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.hybris.backoffice.cockpitng.util;

import de.hybris.platform.cache.AbstractCacheUnit;
import de.hybris.platform.regioncache.CacheController;
import de.hybris.platform.regioncache.CacheLifecycleCallback;
import de.hybris.platform.regioncache.key.CacheKey;
import de.hybris.platform.regioncache.region.CacheRegion;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.web.session.PersistedSession;
import org.apache.commons.configuration.Configuration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static com.hybris.backoffice.cockpitng.util.DefaultBackofficeSessionCleaner.HANDLE_CACHE_REGION_NAME;
import static com.hybris.backoffice.cockpitng.util.DefaultBackofficeSessionCleaner.HANDLE_EXTENSION_NAME;
import static com.hybris.backoffice.cockpitng.util.DefaultBackofficeSessionCleaner.SPRING_SESSION_ENABLED_FLAG;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class DefaultBackofficeSessionCleanerTest {

	@Mock
	private CacheController cacheController;
	@Mock
	private ConfigurationService configurationService;
	@InjectMocks
	private DefaultBackofficeSessionCleaner cleaner;

	@Test
	public void shouldNotRegisterCacheLifeCycleCallbackIfSessionFailOverDisabled() {
		var mockConf = mock(Configuration.class);
		when(mockConf.getBoolean(SPRING_SESSION_ENABLED_FLAG, false)).thenReturn(false);
		when(configurationService.getConfiguration()).thenReturn(mockConf);

		cleaner.init();
		verify(cacheController, never()).addLifecycleCallback(isA(CacheLifecycleCallback.class));
	}

	@Test
	public void shouldRegisterCacheLifeCycleCallbackIfSessionFailOverEnabled() {
		var mockConf = mock(Configuration.class);
		when(mockConf.getBoolean(SPRING_SESSION_ENABLED_FLAG, false)).thenReturn(true);
		when(configurationService.getConfiguration()).thenReturn(mockConf);

		cleaner.init();
		verify(cacheController, times(1)).addLifecycleCallback(isA(CacheLifecycleCallback.class));
	}

	@Test
	public void shouldCleanupExpiredSession() throws Exception {
		var mockKey1 = mock(CacheKey.class);
		var mockKey2 = mock(CacheKey.class);
		var keys = List.of(mockKey1, mockKey2);
		var mockRegion = mock(CacheRegion.class);
		when(mockRegion.getName()).thenReturn(HANDLE_CACHE_REGION_NAME);
		when(mockRegion.getSize()).thenReturn((long) keys.size());
		when(mockRegion.getAllKeys()).thenReturn(keys);

		var mockPersistedSession1 = mock(PersistedSession.class);
		when(mockPersistedSession1.isExpired()).thenReturn(true);
		when(mockPersistedSession1.getExtension()).thenReturn(HANDLE_EXTENSION_NAME);
		var mockPersistedSession2 = mock(PersistedSession.class);
		lenient().when(mockPersistedSession2.isExpired()).thenReturn(false);
		lenient().when(mockPersistedSession2.getExtension()).thenReturn(HANDLE_EXTENSION_NAME);

		var mockUnit1 = mock(AbstractCacheUnit.class);
		when(mockUnit1.get()).thenReturn(mockPersistedSession1);
		var mockUnit2 = mock(AbstractCacheUnit.class);
		when(mockUnit2.get()).thenReturn(mockPersistedSession2);

		when(mockRegion.get(mockKey1)).thenReturn(mockUnit1);
		when(mockRegion.get(mockKey2)).thenReturn(mockUnit2);

		cleaner.cleanupExpiredSession(mockRegion);
		verify(mockRegion, times(1)).remove(mockKey1, false);
		verify(mockRegion, never()).remove(mockKey2, false);
	}
}
