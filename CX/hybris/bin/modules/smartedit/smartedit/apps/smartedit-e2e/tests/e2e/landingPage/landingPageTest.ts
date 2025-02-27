/*
 * Copyright (c) 2022 SAP SE or an SAP affiliate company. All rights reserved.
 */
import { InflectionPoint } from '../../utils/components/InflectionPoint';
import { Storefront } from '../../utils/components/Storefront';
import { LandingPagePageObject } from '../../utils/pageObjects/LandingPagePageObject';

describe('Landing Page - ', () => {
    beforeEach(async () => {
        await LandingPagePageObject.Actions.openAndBeReady();
    });

    describe('GIVEN I am on the landing page ', () => {
        it(
            'WHEN the page is fully loaded ' + 'THEN I expect to see the first site selected',
            async () => {
                // THEN
                await LandingPagePageObject.Assertions.expectedSiteIsSelected(
                    LandingPagePageObject.Constants.ELECTRONICS_SITE
                );
                await LandingPagePageObject.Assertions.selectedSiteHasRightNumberOfCatalogs(1);
            }
        );

        it(
            'WHEN I have a site with multiple catalogs ' +
                'THEN only the catalog for the current site is expanded',
            async () => {
                // GIVEN
                await LandingPagePageObject.Assertions.selectedSiteHasRightNumberOfCatalogs(1);

                // WHEN
                await LandingPagePageObject.Actions.selectSite(
                    LandingPagePageObject.Constants.ACTION_FIGURES_SITE
                );

                // THEN
                await LandingPagePageObject.Assertions.selectedSiteHasRightNumberOfCatalogs(2);
            }
        );

        it(
            'WHEN I click on the thumbnail ' +
                'THEN I expect to be redirected to the homepage of the active catalog version',
            async () => {
                // WHEN
                await LandingPagePageObject.Actions.selectSite(
                    LandingPagePageObject.Constants.ELECTRONICS_SITE
                );
                await LandingPagePageObject.Actions.navigateToStorefrontViaThumbnail(
                    LandingPagePageObject.Constants.ELECTRONICS_CATALOG
                );

                // THEN
                await Storefront.Assertions.assertStoreFrontIsDisplayed();
            }
        );

        it(
            'WHEN I click on the homepage link of the active catalog version ' +
                'THEN I expect to be redirected to the homepage of the active catalog version',
            async () => {
                // WHEN
                await LandingPagePageObject.Actions.selectSite(
                    LandingPagePageObject.Constants.ELECTRONICS_SITE
                );
                await LandingPagePageObject.Actions.navigateToStorefrontViaHomePageLink(
                    LandingPagePageObject.Constants.ELECTRONICS_CATALOG,
                    LandingPagePageObject.Constants.ACTIVE_CATALOG_VERSION
                );

                // THEN
                await Storefront.Assertions.assertStoreFrontIsDisplayed();
            }
        );

        it(
            'WHEN I click on the homepage link of a staged catalog version ' +
                'THEN I expect to be redirected to the homepage of that staged catalog version',
            async () => {
                // WHEN
                await LandingPagePageObject.Actions.navigateToStorefrontViaHomePageLink(
                    LandingPagePageObject.Constants.ELECTRONICS_CATALOG,
                    LandingPagePageObject.Constants.ACTIVE_CATALOG_VERSION
                );

                // THEN
                await Storefront.Assertions.assertStoreFrontIsDisplayed();
            }
        );

        it('THEN inflection point icon should not be visible on this page', async () => {
            // THEN
            await InflectionPoint.Assertions.inflectionPointSelectorIsNotPresent();
        });

        it(
            'WHEN I click on a multicountry site AND I click on the local catalog ' +
                'THEN I am redirected to the right page',
            async () => {
                // GIVEN
                const CONSTANTS = LandingPagePageObject.Constants;
                await LandingPagePageObject.Actions.selectSite(CONSTANTS.ACTION_FIGURES_SITE);

                // WHEN
                await LandingPagePageObject.Actions.clickOnHomePageLink(
                    CONSTANTS.ACTION_FIGURES_CATALOG,
                    CONSTANTS.ACTIVE_CATALOG_VERSION
                );

                // THEN
                await LandingPagePageObject.Assertions.assertStorefrontIsLoaded();
            }
        );

        it(
            'WHEN I click on a multicountry site AND I click on a parent catalog ' +
                'THEN I am redirected to the right page',
            async () => {
                // GIVEN
                const CONSTANTS = LandingPagePageObject.Constants;
                await LandingPagePageObject.Actions.selectSite(CONSTANTS.ACTION_FIGURES_SITE);

                // WHEN
                await LandingPagePageObject.Actions.clickOnParentCatalogHomePageLink(
                    CONSTANTS.TOYS_CATALOG,
                    CONSTANTS.ACTIVE_CATALOG_VERSION
                );

                // THEN
                await LandingPagePageObject.Assertions.assertStorefrontIsLoaded();
            }
        );

        it(
            'WHEN I open the dropdown for sites and search by key ' +
                'THEN I expect the data to be filtered by label',
            async () => {
                // WHEN - THEN
                await LandingPagePageObject.Actions.openSiteSelector();
                await LandingPagePageObject.Assertions.searchAndAssertInDropdown('toy', ['Toys']);
            }
        );
    });

    it(
        'GIVEN I am on a store front ' +
            'WHEN I click on the burger menu and the SITES link ' +
            'THEN I will be redirected to the landing page',
        async () => {
            // GIVEN
            await LandingPagePageObject.Actions.selectSite(
                LandingPagePageObject.Constants.APPAREL_SITE
            );
            await LandingPagePageObject.Actions.navigateToStorefrontViaThumbnail(
                LandingPagePageObject.Constants.APPAREL_UK_CATALOG
            );

            // WHEN
            await LandingPagePageObject.Actions.navigateToLandingPage();

            // THEN
            await LandingPagePageObject.Assertions.assertLandingPageIsDisplayed();
        }
    );

    it(
        'GIVEN I am on the landing page with action figures site id in url ' +
            'WHEN the page is fully loaded ' +
            'THEN I expect action figures site is selected in sites dropdown',
        async () => {
            // GIVEN
            await LandingPagePageObject.Actions.goToLandingPageWithSiteId(
                LandingPagePageObject.Constants.ACTION_FIGURES_SITE_ID
            );

            // WHEN - THEN
            await LandingPagePageObject.Assertions.expectedSiteIsSelected(
                LandingPagePageObject.Constants.ACTION_FIGURES_SITE
            );
            await LandingPagePageObject.Assertions.selectedSiteHasRightNumberOfCatalogs(2);

            await LandingPagePageObject.Assertions.assertLandingPageDoesNotHaveSiteInUrl(
                LandingPagePageObject.Constants.ACTION_FIGURES_CATALOG
            );
        }
    );

    describe('GIVEN I am on the landing page with action figure site id in url ', () => {
        beforeEach(async () => {
            // GIVEN
            await LandingPagePageObject.Actions.goToLandingPageWithSiteId(
                LandingPagePageObject.Constants.ACTION_FIGURES_SITE_ID
            );
        });

        it(
            'WHEN I select electronics site from sites dropdown ' +
                'THEN I expect to change to electronics site and view electronics content catalogs',
            async () => {
                // WHEN
                await LandingPagePageObject.Actions.selectSite(
                    LandingPagePageObject.Constants.ELECTRONICS_SITE
                );

                // THEN
                await LandingPagePageObject.Assertions.expectedSiteIsSelected(
                    LandingPagePageObject.Constants.ELECTRONICS_SITE
                );
                await LandingPagePageObject.Assertions.selectedSiteHasRightNumberOfCatalogs(1);
            }
        );

        it(
            'WHEN I do reload ' +
                'THEN I expect to not have sites in url and action figures site is still selected in dropdown',
            async () => {
                // WHEN
                await LandingPagePageObject.Actions.refreshOnLandingPage();

                // THEN
                await LandingPagePageObject.Assertions.expectedSiteIsSelected(
                    LandingPagePageObject.Constants.ACTION_FIGURES_SITE
                );

                await LandingPagePageObject.Assertions.assertLandingPageDoesNotHaveSiteInUrl(
                    LandingPagePageObject.Constants.ACTION_FIGURES_SITE
                );
            }
        );

        it(
            'WHEN I navigate back in the browser ' + 'THEN I expect to not have sites in url',
            async () => {
                // WHEN
                await LandingPagePageObject.Actions.navigateBack();

                // THEN
                await LandingPagePageObject.Assertions.expectedSiteIsSelected(
                    LandingPagePageObject.Constants.ELECTRONICS_SITE
                );

                await LandingPagePageObject.Assertions.assertLandingPageDoesNotHaveSiteInUrl(
                    LandingPagePageObject.Constants.ACTION_FIGURES_SITE
                );
            }
        );
    });
});
