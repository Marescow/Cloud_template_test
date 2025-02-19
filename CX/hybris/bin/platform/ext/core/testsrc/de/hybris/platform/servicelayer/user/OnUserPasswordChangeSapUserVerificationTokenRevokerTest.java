package de.hybris.platform.servicelayer.user;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.core.enums.SAPUserVerificationPurpose;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.ServicelayerBaseTest;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.model.ModelService;

import javax.annotation.Resource;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;


@IntegrationTest
public class OnUserPasswordChangeSapUserVerificationTokenRevokerTest extends ServicelayerBaseTest
{

	final String customerWhoChangesPasswordUid = uniqueString();
	final String otherCustomerUid = uniqueString();
	@Resource
	UserService userService;
	@Resource
	ModelService modelService;
	@Resource
	UserVerificationTokenService userVerificationTokenService;

	@Before
	public void prepareTestData()
	{

		final CustomerModel customerWhoChangesPassword = modelService.create(CustomerModel.class);
		customerWhoChangesPassword.setUid(customerWhoChangesPasswordUid);
		customerWhoChangesPassword.setPassword("TEST");

		final CustomerModel otherCustomer = modelService.create(CustomerModel.class);
		otherCustomer.setUid(otherCustomerUid);
		otherCustomer.setPassword("TEST");

		modelService.saveAll();

	}


	private String uniqueString()
	{
		return UUID.randomUUID().toString();
	}

	@Test
	public void shouldRevokeSapUserVerificationTokensForUserWhoPasswordWasChanged()
	{
		//given
		final var tokenForRemoval1 = userVerificationTokenService.create(SAPUserVerificationPurpose.LOGIN,
				customerWhoChangesPasswordUid).getTokenId();
		final var tokenForRemoval2 = userVerificationTokenService.create(SAPUserVerificationPurpose.LOGIN,
				customerWhoChangesPasswordUid).getTokenId();
		final var tokenForRemoval3 = userVerificationTokenService.create(SAPUserVerificationPurpose.LOGIN,
				customerWhoChangesPasswordUid).getTokenId();
		final var tokenOfOtherUser1 = userVerificationTokenService.create(SAPUserVerificationPurpose.LOGIN, otherCustomerUid)
				.getTokenId();
		final var tokenOfOtherUser2 = userVerificationTokenService.create(SAPUserVerificationPurpose.LOGIN, otherCustomerUid)
				.getTokenId();
		//confirm tokens exists before password change
		assertNotNull(userVerificationTokenService.lookupToken(SAPUserVerificationPurpose.LOGIN, tokenForRemoval1));
		assertNotNull(userVerificationTokenService.lookupToken(SAPUserVerificationPurpose.LOGIN, tokenForRemoval2));
		assertNotNull(userVerificationTokenService.lookupToken(SAPUserVerificationPurpose.LOGIN, tokenForRemoval3));

		//when
		userService.setPassword(customerWhoChangesPasswordUid, "NEW");

		//then user tokens of user who changed password should be removed
		assertThrows(UnknownIdentifierException.class,
				() -> userVerificationTokenService.lookupToken(SAPUserVerificationPurpose.LOGIN, tokenForRemoval1));
		assertThrows(UnknownIdentifierException.class,
				() -> userVerificationTokenService.lookupToken(SAPUserVerificationPurpose.LOGIN, tokenForRemoval2));
		assertThrows(UnknownIdentifierException.class,
				() -> userVerificationTokenService.lookupToken(SAPUserVerificationPurpose.LOGIN, tokenForRemoval3));
		// and tokens of other users should remain
		assertNotNull(userVerificationTokenService.lookupToken(SAPUserVerificationPurpose.LOGIN, tokenOfOtherUser1));
		assertNotNull(userVerificationTokenService.lookupToken(SAPUserVerificationPurpose.LOGIN, tokenOfOtherUser2));
	}
}
