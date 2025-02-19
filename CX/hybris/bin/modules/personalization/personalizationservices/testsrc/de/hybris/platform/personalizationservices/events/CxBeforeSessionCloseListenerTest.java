package de.hybris.platform.personalizationservices.events;
import static de.hybris.platform.personalizationservices.constants.PersonalizationservicesConstants.USER_TO_SEGMENTS_SESSION_KEY;
import static de.hybris.platform.personalizationservices.constants.PersonalizationservicesConstants.ACTIVE_PERSONALIZATION;
import static de.hybris.platform.personalizationservices.constants.PersonalizationservicesConstants.CONTEXT_BASESITE_KEY;
import static de.hybris.platform.personalizationservices.constants.PersonalizationservicesConstants.CONTEXT_SEGMENTS_KEY;
import static de.hybris.platform.personalizationservices.constants.PersonalizationservicesConstants.CONTEXT_USER_KEY;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.personalizationservices.action.dao.CxActionResultDao;
import de.hybris.platform.personalizationservices.data.UserToSegmentData;
import de.hybris.platform.personalizationservices.model.CxResultsModel;
import de.hybris.platform.personalizationservices.model.CxSegmentModel;
import de.hybris.platform.personalizationservices.model.CxUserToSegmentModel;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.event.events.BeforeSessionCloseEvent;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.session.impl.DefaultSessionTokenService;
import de.hybris.platform.jalo.user.UserManager;
import de.hybris.platform.site.BaseSiteService;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import de.hybris.bootstrap.annotations.IntegrationTest;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


@IntegrationTest
public class CxBeforeSessionCloseListenerTest extends ServicelayerTransactionalTest
{
	private static final String RESULT_KEY_1 = "resultKey1";
	private static final String SESSION_KEY_1 = "sessionKey1";
	private static final String RESULT_KEY_2 = "resultKey2";
	private static final String SESSION_KEY_2 = "sessionKey2";

	private static final String USER_ID = "userId";
	private static final String SEGMENT1 = "segment1";
	private static final String SEGMENT2 = "segment2";
	private static final String NOT_EXISTING_SEGMENT = "notExisitng";
	private static final String BASE_SITE_ID = "testBaseSite";
	private static final String BASE_SITE1_ID = "testBaseSite1";

	@Resource
	private CxBeforeSessionCloseListener cxBeforeSessionCloseListener;
	@Resource
	private ModelService modelService;
	@Resource
	private BaseSiteService baseSiteService;
	@Resource
	private DefaultSessionTokenService defaultSessionTokenService;
	@Resource
	private CxActionResultDao cxActionResultDao;

	@Resource
	private CatalogVersionService catalogVersionService;

	@Resource
	private SessionService sessionService;

	private BeforeSessionCloseEvent event;

	private JaloSession session;

	private BaseSiteModel baseSite;


	private UserModel user;

	@Before
	public void setup() throws Exception
	{
		createCoreData();
		createDefaultCatalog();
		createUser();

		session = JaloSession.getCurrentSession();
		session.getSessionContext().setAttribute(ACTIVE_PERSONALIZATION, true);
		session.setHttpSessionId("http-session-id");
		event = new BeforeSessionCloseEvent(session);

		baseSite = createBaseSite(BASE_SITE_ID);
		createSegment(SEGMENT1);
		createSegment(SEGMENT2);
	}

	@After
	public void cleanUp() throws Exception
	{
		session = JaloSession.getCurrentSession();
		session.getSessionContext().setAttribute(ACTIVE_PERSONALIZATION, false);
		session.setHttpSessionId(null);
	}

	@Test
	public void testCleanResultsForSession() throws Exception
	{
		//given
		createCxResults(RESULT_KEY_1, SESSION_KEY_1);
		createCxResults(RESULT_KEY_2, SESSION_KEY_1);
		assertThereAreResults(SESSION_KEY_1);
		session.setUser(UserManager.getInstance().getAnonymousCustomer());
		defaultSessionTokenService.setSessionToken(SESSION_KEY_1);

		// when
		cxBeforeSessionCloseListener.onEvent(event);

		//then
		assertThereIsNoResults(SESSION_KEY_1);
	}

	@Test
	public void testThatResulAreRemovedOnlyForSelectedSession() throws Exception
	{
		//given
		createCxResults(RESULT_KEY_1, SESSION_KEY_1);
		createCxResults(RESULT_KEY_2, SESSION_KEY_2);
		assertThereAreResults(SESSION_KEY_1);
		assertThereAreResults(SESSION_KEY_2);
		session.setUser(UserManager.getInstance().getAnonymousCustomer());
		defaultSessionTokenService.setSessionToken(SESSION_KEY_1);

		//when
		cxBeforeSessionCloseListener.onEvent(event);

		//then
		assertThereIsNoResults(SESSION_KEY_1);
		assertThereAreResults(SESSION_KEY_2);
	}

	@Test
	public void testCleanWhenThereIsNoResults() throws Exception
	{
		//given
		session.setUser(UserManager.getInstance().getAnonymousCustomer());
		defaultSessionTokenService.setSessionToken(SESSION_KEY_1);

		//when
		cxBeforeSessionCloseListener.onEvent(event);

		//then
		assertThereIsNoResults(SESSION_KEY_1);
	}

	@Test
	public void testSaveUserSegments() throws Exception
	{
		session.setUser(UserManager.getInstance().createCustomer("cust"));
		final UserModel user = modelService.get(session.getUser());

		//given
		final Collection<UserToSegmentData> expected = createUserSegments(user, BigDecimal.ONE, SEGMENT1, SEGMENT2);
		sessionService.setAttribute(USER_TO_SEGMENTS_SESSION_KEY + user.getUid(), expected);
		baseSiteService.setCurrentBaseSite(baseSite, false);

		//when
		cxBeforeSessionCloseListener.onEvent(event);

		//then
		verifySegments(expected, user.getUserToSegments(), BASE_SITE_ID);
	}

	@Test
	public void testSaveUserSegmentsForTwoBaseSites() throws Exception
	{
		//given
		session.setUser(UserManager.getInstance().createCustomer("cust"));
		final UserModel user = modelService.get(session.getUser());
		final Collection<UserToSegmentData> expected = createUserSegments(user, BigDecimal.ONE, SEGMENT1, SEGMENT2);
		sessionService.setAttribute(USER_TO_SEGMENTS_SESSION_KEY + user.getUid(), expected);
		baseSiteService.setCurrentBaseSite(baseSite, false);

		//when
		cxBeforeSessionCloseListener.onEvent(event);

		//given
		final BaseSiteModel baseSite1 = createBaseSite(BASE_SITE1_ID);
		baseSiteService.setCurrentBaseSite(baseSite1, false);

		//when
		cxBeforeSessionCloseListener.onEvent(event);

		//then
		final Collection<CxUserToSegmentModel> userSegments = user.getUserToSegments();
		Assert.assertEquals(4, userSegments.size());

		Collection<CxUserToSegmentModel> userSegmentsForBaseSite = userSegments.stream()//
				.filter(us -> BASE_SITE_ID.equals(us.getBaseSite().getUid()))//
				.collect(Collectors.toSet());
		verifySegments(expected, userSegmentsForBaseSite, BASE_SITE_ID);

		userSegmentsForBaseSite = userSegments.stream()//
				.filter(us -> BASE_SITE1_ID.equals(us.getBaseSite().getUid()))//
				.collect(Collectors.toSet());
		verifySegments(expected, userSegmentsForBaseSite, BASE_SITE1_ID);
	}

	@Test
	public void testSaveUserSegmentsWithNotExistingSegment() throws Exception
	{
		//given
		Assert.assertTrue(CollectionUtils.isEmpty(user.getUserToSegments()));
		final Collection<UserToSegmentData> userSegments = createUserSegments(user, BigDecimal.ONE, NOT_EXISTING_SEGMENT);
		session.setUser(UserManager.getInstance().createCustomer("cust"));
		final UserModel user = modelService.get(session.getUser());
		sessionService.setAttribute(USER_TO_SEGMENTS_SESSION_KEY + user.getUid(), userSegments);
		baseSiteService.setCurrentBaseSite(baseSite, false);

		//when
		cxBeforeSessionCloseListener.onEvent(event);

		//then
		Assert.assertTrue(CollectionUtils.isEmpty(user.getUserToSegments()));
	}

	private void createCxResults(final String key, final String sessionKey)
	{
		final CxResultsModel results = new CxResultsModel();
		results.setCatalogVersion(catalogVersionService.getCatalogVersion("testCatalog", "Online"));
		results.setKey(key);
		results.setSessionKey(sessionKey);
		results.setCalculationTime(new Date());
		results.setAnonymous(true);
		results.setResults("testResults");
		modelService.save(results);
	}

	private void assertThereIsNoResults(final String sessionId)
	{
		final List<CxResultsModel> resultList = cxActionResultDao.findResultsBySessionKey(sessionId);
		assertEquals(0, resultList.size());
	}

	private void assertThereAreResults(final String sessionId)
	{
		final List<CxResultsModel> resultList = cxActionResultDao.findResultsBySessionKey(sessionId);
		assertTrue(!resultList.isEmpty());
	}

	protected Map<String, Object> createContext(final String userId, final Collection<UserToSegmentData> userSegments,
			final String baseSiteUid)
	{
		final Map<String, Object> contextMap = new HashMap<>();
		contextMap.put(CONTEXT_USER_KEY, user.getUid());
		contextMap.put(CONTEXT_SEGMENTS_KEY, userSegments);
		contextMap.put(CONTEXT_BASESITE_KEY, baseSiteUid);

		return contextMap;
	}

	protected BaseSiteModel createBaseSite(final String baseSiteId)
	{
		final BaseSiteModel baseSite = modelService.create(BaseSiteModel.class);
		baseSite.setUid(baseSiteId);
		modelService.save(baseSite);
		return baseSite;
	}

	protected void createUser()
	{
		user = modelService.create(UserModel.class);
		user.setUid(USER_ID);
		modelService.save(user);
	}

	protected void createSegment(final String code)
	{
		final CxSegmentModel segment = modelService.create(CxSegmentModel.class);
		segment.setCode(code);
		modelService.save(segment);
	}

	protected Collection<UserToSegmentData> createUserSegments(final UserModel user, final BigDecimal affinity,
			final String... segments)
	{
		return Arrays.stream(segments).map(s -> createUserSegment(affinity, s)).collect(Collectors.toList());
	}

	protected UserToSegmentData createUserSegment(final BigDecimal affinity, final String segmentCode)
	{
		final UserToSegmentData data = new UserToSegmentData();
		data.setAffinity(affinity);
		data.setCode(segmentCode);
		return data;
	}

	protected void verifySegments(final Collection<UserToSegmentData> expected, final Collection<CxUserToSegmentModel> current,
			final String baseSiteId)
	{
		Assert.assertEquals(expected.size(), current.size());

		final Map<String, BigDecimal> expectedDataMap = expected.stream().collect(//
				Collectors.toMap(//
						UserToSegmentData::getCode, //
						UserToSegmentData::getAffinity));

		Assert.assertTrue(current.stream().allMatch(us -> baseSiteId.equals(us.getBaseSite().getUid())//
				&& expectedDataMap.containsKey(us.getSegment().getCode())//
				&& expectedDataMap.get(us.getSegment().getCode()).compareTo(us.getAffinity()) == 0));
	}
}
