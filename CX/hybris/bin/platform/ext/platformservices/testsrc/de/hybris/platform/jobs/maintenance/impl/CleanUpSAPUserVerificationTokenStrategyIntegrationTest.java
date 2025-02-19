/*
 * Copyright (c) 2024 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.jobs.maintenance.impl;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.core.enums.SAPUserVerificationPurpose;
import de.hybris.platform.core.model.user.SAPUserVerificationTokenModel;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.ServicelayerBaseTest;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.user.impl.DefaultSapUserVerificationTokenService;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests the logic for service {@link CleanUpSAPUserVerificationTokenStrategy} which is responsible for removing expired verification token.
 */
@IntegrationTest
public class CleanUpSAPUserVerificationTokenStrategyIntegrationTest extends ServicelayerBaseTest
{

    @Resource
    private ModelService modelService;

    @Resource
    private FlexibleSearchService flexibleSearchService;

    @Resource
    private DefaultSapUserVerificationTokenService userVerificationTokenService;

    private CleanUpSAPUserVerificationTokenStrategy cleanupStrategy;


    @Before
    public void setUp()
    {
        cleanupStrategy = new CleanUpSAPUserVerificationTokenStrategy(modelService);

    }


    @Test
    public void shouldRemoveExpiredToken()
    {
        //given
        final var tokenValid1 = userVerificationTokenService.createVerificationToken(SAPUserVerificationPurpose.LOGIN, randomUserId(),30);
        final var tokenValid2 = userVerificationTokenService.createVerificationToken(SAPUserVerificationPurpose.LOGIN, randomUserId(),20);
        final var tokenValid3 = userVerificationTokenService.createVerificationToken(SAPUserVerificationPurpose.LOGIN, randomUserId(),60);
        final int outdatedTtl = -1;
        final var tokenOutdated1 = userVerificationTokenService.createVerificationToken(SAPUserVerificationPurpose.LOGIN, randomUserId(),outdatedTtl);
        final var tokenOutdated2 = userVerificationTokenService.createVerificationToken(SAPUserVerificationPurpose.LOGIN, randomUserId(),outdatedTtl);
        final var tokenOutdated3 = userVerificationTokenService.createVerificationToken(SAPUserVerificationPurpose.LOGIN, randomUserId(),outdatedTtl);

        final var expiredTokenHashesIds = Set.of(tokenOutdated1.getToken().getHashedTokenId(),
                tokenOutdated2.getToken().getHashedTokenId(), tokenOutdated3.getToken().getHashedTokenId());


        //when
        List<SAPUserVerificationTokenModel> tokens = useCleanUpSAPUserVerificationTokenStrategy();

        //then

        //only expired tokens are identified for the removal
        final int nrOfIdentifiedOutdatedTokens = 3;
        assertThat(tokens).hasSize(nrOfIdentifiedOutdatedTokens);
        assertThat(tokens.stream().map(SAPUserVerificationTokenModel::getHashedTokenId).collect(Collectors.toSet())).isEqualTo(expiredTokenHashesIds);

        //expired tokens are removed
        cleanupStrategy.process(tokens);
        tokens = useCleanUpSAPUserVerificationTokenStrategy();
        assertThat(tokens).isEmpty();
    }


    private List<SAPUserVerificationTokenModel> useCleanUpSAPUserVerificationTokenStrategy()
    {
        final CronJobModel cronJob = modelService
                .create(CronJobModel.class);
        final FlexibleSearchQuery query = cleanupStrategy.createFetchQuery(cronJob);
        return flexibleSearchService.<SAPUserVerificationTokenModel>search(query).getResult();
    }

    private String randomUserId()
    {
        return RandomStringUtils.random(8, true, true) + "@sap.com";
    }

}
