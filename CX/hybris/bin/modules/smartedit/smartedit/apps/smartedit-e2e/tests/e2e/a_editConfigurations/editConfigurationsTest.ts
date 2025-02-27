/*
 * Copyright (c) 2022 SAP SE or an SAP affiliate company. All rights reserved.
 */
import { browser } from 'protractor';
import { Configurations } from '../../utils/components/Configurations';
import { Login } from '../../utils/components/Login';
import { Page } from '../../utils/components/Page';

const defaultConfigData = require('./defaultConfigurations.json');

describe('Configuration Editor', () => {
    const HTTPS_SOME_URI = '"https://someuri"';
    async function logOutAndRefresh() {
        await Login.Actions.logoutUser();
        await browser.clearLocalStorage();
        await browser.refresh();
    }

    beforeEach(async () => {
        await Page.Actions.getAndWaitForLogin(
            'smartedit-e2e/generated/e2e/a_editConfigurations/#!/ng'
        );
    });
    afterEach(async () => {
        await browser.clearLocalStorage();
    });

    describe('Permissions', () => {
        afterEach(async () => {
            await browser.waitUntilNoModal();
            await browser.waitForAbsence(Configurations.Elements.getConfigurationCenterButton());
            await Login.Actions.logoutUser();
        });

        it('GIVEN I am logged in as a user with permission to view the configuration editor THEN configuration center will be visible', async () => {
            await Login.Actions.loginAsAdmin();
            await browser.waitUntilNoModal();
            await browser.waitForPresence(Configurations.Elements.getConfigurationCenterButton());
        });

        it('GIVEN I am logged in as a user without permission to view the configuration editor THEN configuration center will be hidden', async () => {
            await Login.Actions.loginAsCmsManager();
            await browser.waitUntilNoModal();
            await browser.waitForAbsence(Configurations.Elements.getConfigurationCenterButton());
        });

        it('GIVEN I am logged in as a user with permission to view editor AND then switched to user without permission THEN configuration center must be hidden', async () => {
            // GIVEN
            await Login.Actions.loginAsAdmin();
            await browser.waitUntilNoModal();

            await logOutAndRefresh();

            // WHEN
            await Login.Actions.loginAsCmsManager();
            await browser.waitUntilNoModal();

            // // THEN
            await browser.waitForAbsence(Configurations.Elements.getConfigurationCenterButton());
        });

        it('GIVEN I am logged in as a user without permission to view editor AND then switched to user with permission THEN configuration center must be visible', async () => {
            // GIVEN
            await Login.Actions.loginAsCmsManager();
            await browser.waitUntilNoModal();

            await logOutAndRefresh();

            // WHEN
            await Login.Actions.loginAsAdmin();
            await browser.waitUntilNoModal();

            // THEN
            const isPresent = await Configurations.Elements.getConfigurationCenterButton().isPresent();
            expect(isPresent).toBe(true);
        });
    });

    describe('Modified Configurations', () => {
        beforeEach(async () => {
            await Login.Actions.loginAsAdmin();

            await browser.waitUntilNoModal();
            await Configurations.Actions.openConfigurationEditor();
            await Configurations.Actions.waitForConfigurationModal(2);
        });

        afterEach(async () => {
            await Configurations.Actions.clickCancel();
        });

        it("GIVEN I'm in the Configuration Editor WHEN I attempt to add a duplicate key THEN I expect to see an error", async () => {
            await Configurations.Actions.clickAdd();
            await Configurations.Actions.waitForConfigurationsToPopulate(3);
            await Configurations.Actions.setConfigurationKeyAndValue(
                0,
                'previewTicketURI',
                `"previewTicketURI"`
            );
            await Configurations.Actions.waitForErrorForKey('previewTicketURI');

            const text = await Configurations.Elements.getErrorForKey('previewTicketURI').getText();

            expect(text).toEqual('This is a duplicate key');
        });

        it("GIVEN I'm in the Configuration Editor WHEN user types an absolute URL THEN the editor shall display a checkbox", async () => {
            await Configurations.Actions.setConfigurationValue(0, HTTPS_SOME_URI); // add key and value
            await browser.waitForPresence(
                Configurations.Elements.getAbsoluteUrlCheckbox(),
                'Checkbox not present'
            );
        });

        it("GIVEN I'm in the Configuration Editor WHEN user types does not type an absolute URL THEN the editor shall not display a checkbox", async () => {
            await Configurations.Actions.setConfigurationValue(0, '"/someuri/"'); // add key and value
            await browser.waitForAbsence(Configurations.Elements.getAbsoluteUrlCheckbox());
        });
    });

    describe('Configurations', () => {
        const PREVIEW_TICKET_URI = '"thepreviewTicketURI"';
        const i18N_API_ROOT_VALUE_MALFORMED = '{malformed}';
        const CLASS_NOT_CHECKED = ' not-checked';
        beforeEach(async () => {
            await Login.Actions.loginAsAdmin();
            await browser.waitForContainerToBeReady();
            await browser.waitUntilNoModal();
            await Configurations.Actions.openConfigurationEditor();
            await Configurations.Actions.waitForConfigurationModal(2);
        });

        afterEach(async () => {
            await Configurations.Actions.clickCancel();
            await Login.Actions.logoutUser();
        });

        it('GIVEN I am in the Configuration Editor THEN I expect to see a title, a save and cancel button, and configurations as defined in the backend', async () => {
            expect(await Configurations.Elements.getConfigurationTitle().getText()).toContain(
                'edit configuration'
            );
            expect(await Configurations.Elements.getCancelButton().isPresent()).toBe(true);
            expect(await Configurations.Elements.getSaveButton().isPresent()).toBe(true);
            expect(await Configurations.Actions.getConfigurations()).toEqual(defaultConfigData);
        });

        it("GIVEN I'm in the Configuration Editor WHEN I delete a configuration entry AND I reopen the configuration editor THEN I expect to see the remaining configurations", async () => {
            await Configurations.Actions.deleteConfiguration(1); // delete the 2nd configuration
            await Configurations.Actions.clickSave();
            await Configurations.Actions.openConfigurationEditor();

            expect(await Configurations.Actions.getConfigurations()).toEqual([
                {
                    key: 'previewTicketURI',
                    value: PREVIEW_TICKET_URI
                }
            ]);
        });

        it("GIVEN I'm in the Configuration Editor WHEN I attempt to add a malformed configuration THEN an error is displayed", async () => {
            await Configurations.Actions.clickAdd();
            await Configurations.Actions.waitForConfigurationsToPopulate(3);
            await Configurations.Actions.setConfigurationKeyAndValue(
                0,
                'newkey',
                '{othermalformed}'
            );
            await Configurations.Actions.clickSave();
            await Configurations.Actions.waitForErrorForKey('newkey');

            expect(await Configurations.Elements.getErrorForKey('newkey').getText()).toEqual(
                'this value should be a valid JSON format'
            );
        });

        it("GIVEN I'm in the Configuration Editor WHEN I attempt to add a malformed configuration AND re-open the configuration editor THEN I expect to see the original configurations", async () => {
            await Configurations.Actions.clickAdd();
            await Configurations.Actions.waitForConfigurationsToPopulate(3);
            await Configurations.Actions.setConfigurationKeyAndValue(
                0,
                'newkey',
                '{othermalformed}'
            );
            await Configurations.Actions.clickSave();
            await Configurations.Actions.waitForErrorForKey('newkey');
            await Configurations.Actions.clickCancel();
            await Configurations.Actions.openConfigurationEditor();

            expect(await Configurations.Actions.getConfigurations()).toEqual([
                {
                    key: 'previewTicketURI',
                    value: PREVIEW_TICKET_URI
                },
                {
                    key: 'i18nAPIRoot',
                    value: i18N_API_ROOT_VALUE_MALFORMED
                }
            ]);
        });

        it("GIVEN I'm in the Configuration Editor WHEN I attempt to add a new well-formed configuration THEN the configuration will be added", async () => {
            await Configurations.Actions.clickAdd();
            await Configurations.Actions.waitForConfigurationsToPopulate(3);
            await Configurations.Actions.setConfigurationKeyAndValue(0, 'newkey', '"new value"');
            await Configurations.Actions.clickSave();
            await Configurations.Actions.clickCancel();
            await Configurations.Actions.openConfigurationEditor();

            expect(await Configurations.Actions.getConfigurations()).toEqual([
                {
                    key: 'previewTicketURI',
                    value: PREVIEW_TICKET_URI
                },
                {
                    key: 'i18nAPIRoot',
                    value: i18N_API_ROOT_VALUE_MALFORMED
                },
                {
                    key: 'newkey',
                    value: '"new value"'
                }
            ]);
        });

        it("GIVEN I'm in the Configuration Editor WHEN I attempt to modify an configuration with a well-formed configuration THEN I expect to see the configuration modified", async () => {
            await Configurations.Actions.setConfigurationValue(1, '"new"');
            await Configurations.Actions.clickSave();
            await Configurations.Actions.openConfigurationEditor();

            expect(await Configurations.Actions.getConfigurations()).toEqual([
                {
                    key: 'previewTicketURI',
                    value: PREVIEW_TICKET_URI
                },
                {
                    key: 'i18nAPIRoot',
                    value: '"new"'
                }
            ]);
        });

        it("GIVEN I'm in the Configuration Editor WHEN I attempt to add a duplicate key AND click cancel THEN I expect to see the original configuration in tact", async () => {
            await Configurations.Actions.clickAdd();
            await Configurations.Actions.waitForConfigurationsToPopulate(3);
            await Configurations.Actions.setConfigurationKeyAndValue(
                0,
                'previewTicketURI',
                'previewTicketURI'
            );
            await Configurations.Actions.waitForErrorForKey('previewTicketURI');
            await Configurations.Actions.clickCancel();
            await Configurations.Actions.clickConfirmOk();
            await Configurations.Actions.openConfigurationEditor();
            expect(await Configurations.Actions.getConfigurations()).toEqual([
                {
                    key: 'previewTicketURI',
                    value: PREVIEW_TICKET_URI
                },
                {
                    key: 'i18nAPIRoot',
                    value: i18N_API_ROOT_VALUE_MALFORMED
                }
            ]);
        });

        it("GIVEN I'm in the Configuration Editor WHEN user types an absolute URL and does not tick the checkbox THEN the editor shall highlight the message and not save", async () => {
            await Configurations.Actions.setConfigurationValue(0, HTTPS_SOME_URI); // add key and value

            expect(
                await Configurations.Elements.getAbsoluteUrlCheckbox().isDisplayed()
            ).toBeTruthy();

            expect(
                await Configurations.Elements.getAbsoluteUrlText().getAttribute('class')
            ).not.toMatch(CLASS_NOT_CHECKED);
            await Configurations.Actions.clickSave();

            expect(
                await Configurations.Elements.getAbsoluteUrlText().getAttribute('class')
            ).toMatch(CLASS_NOT_CHECKED);

            await Configurations.Actions.clickCancel();
            await Configurations.Actions.openConfigurationEditor();

            expect(await Configurations.Actions.getConfigurations()).toEqual([
                {
                    key: 'previewTicketURI',
                    value: PREVIEW_TICKET_URI
                },
                {
                    key: 'i18nAPIRoot',
                    value: i18N_API_ROOT_VALUE_MALFORMED
                }
            ]);
        });

        it("GIVEN I'm in the Configuration Editor WHEN user types an absolute URL and ticks the checkbox THEN the editor shall not highlight the message and save the content", async () => {
            await Configurations.Actions.setConfigurationValue(0, HTTPS_SOME_URI); // add key and value

            await browser.waitForPresence(Configurations.Elements.getAbsoluteUrlCheckbox());
            await browser.click(Configurations.Elements.getAbsoluteUrlCheckbox());

            expect(
                await Configurations.Elements.getAbsoluteUrlText().getAttribute('class')
            ).not.toMatch(CLASS_NOT_CHECKED);
            await Configurations.Actions.clickSave();
            expect(
                await Configurations.Elements.getAbsoluteUrlText().getAttribute('class')
            ).not.toMatch(CLASS_NOT_CHECKED);

            await Configurations.Actions.clickCancel();
            await Configurations.Actions.openConfigurationEditor();

            expect(await Configurations.Actions.getConfigurations()).toEqual([
                {
                    key: 'previewTicketURI',
                    value: HTTPS_SOME_URI
                },
                {
                    key: 'i18nAPIRoot',
                    value: i18N_API_ROOT_VALUE_MALFORMED
                }
            ]);
        });
    });
});
