/*
 * Copyright (c) 2022 SAP SE or an SAP affiliate company. All rights reserved.
 */
/* jshint unused:false, undef:false */
describe('Restrictions Editor - ', function () {
    var page = e2e.pageObjects.PageRestrictionsEditor;
    var restrictionsEditor = e2e.componentObjects.restrictionsEditor;
    var workflow = e2e.componentObjects.workflow;
    var backendClient = e2e.mockBackendClient;
    const COMMON_PATH =
        'cmssmarteditwebservices\\/v1\\/catalogs\\/apparel-ukContentCatalog\\/versions\\/Staged\\/workfloweditableitems';

    describe('Restrictions list ', function () {
        describe('GIVEN a restriction already exists', function () {
            let fixtureID0;

            beforeAll(async function () {
                fixtureID0 = await backendClient.modifyFixture([COMMON_PATH], {
                    'editableItems.0.editableByUser': true
                });
            });

            beforeEach(async function () {
                await page.actions
                    .navigateToPage(page.constants.CONTENT_PAGE_TYPE, true, __dirname);
            });

            it(
                'WHEN clicking save without doing anything' +
                    'THEN the dialog is not considered dirty and cannot be saved',
                function () {
                    // WHEN
                    page.actions.clickSave();

                    // THEN
                    page.assertions.saveNotExecuted();
                }
            );

            it(
                'WHEN a restriction is edited ' +
                    'THEN the main dialog is considered dirty and can be saved',
                function () {
                    // GIVEN
                    var restrictionName =
                        restrictionsEditor.constants.EXISTING_TIME_RESTRICTION_NAME;
                    var restrictionData = {
                        activeFrom: '10/12/22 11:05 AM',
                        activeUntil: '11/12/22 11:05 AM'
                    };

                    // WHEN
                    restrictionsEditor.actions.updateTimeRestrictionData(
                        restrictionName,
                        restrictionData
                    );
                    restrictionsEditor.actions.clickUpdateRestrictionButton();
                    page.actions.clickSave();

                    // THEN
                    page.assertions.saveExecuted();
                }
            );

            it(
                'WHEN a restriction is removed ' +
                    'THEN the dialog is considered dirty and can be saved',
                function () {
                    // GIVEN
                    var restrictionName =
                        restrictionsEditor.constants.EXISTING_TIME_RESTRICTION_NAME;

                    // WHEN
                    restrictionsEditor.actions.removeRestriction(restrictionName);
                    page.actions.clickSave();

                    // THEN
                    page.assertions.saveExecuted();
                }
            );

            it(
                'WHEN the application condition is changed ' +
                    'THEN the dialog is considered dirty and can be saved',
                function () {
                    // GIVEN

                    // WHEN
                    restrictionsEditor.actions.applyAnyRestriction();
                    page.actions.clickSave();

                    // THEN
                    page.assertions.saveExecuted();
                }
            );

            afterAll(function () {
                backendClient.removeFixture(fixtureID0);
            });
        });

        describe('GIVEN there is no restriction', function () {
            beforeEach(async function () {
                page.actions
                    .navigateToPage(page.constants.CONTENT_PAGE_TYPE, false, __dirname);
            });

            it(
                'WHEN clicking save without doing anything' +
                    'THEN the dialog is not considered dirty and cannot be saved',
                function () {
                    // WHEN
                    page.actions.clickSave();

                    // THEN
                    page.assertions.saveNotExecuted();
                }
            );

            it(
                'WHEN a restriction is added ' +
                    'THEN the dialog is considered dirty and can be saved',
                function () {
                    // GIVEN
                    var existingTimeRestriction =
                        restrictionsEditor.constants.EXISTING_TIME_RESTRICTION_NAME;

                    // WHEN
                    restrictionsEditor.actions.addExistingTimeRestriction(existingTimeRestriction);
                    restrictionsEditor.actions.clickAddRestrictionButton();
                    page.actions.clickSave();

                    // THEN
                    page.assertions.saveExecuted();
                }
            );
        });
    });

    describe('Restriction errors', function () {
        var existingTimeRestrictionName =
            restrictionsEditor.constants.EXISTING_TIME_RESTRICTION_NAME;
        var newTimeRestrictionName = restrictionsEditor.constants.NEW_TIME_RESTRICTION_NAME;
        let fixtureID0;

        beforeAll(async function () {
            fixtureID0 = await backendClient.modifyFixture([COMMON_PATH], {
                'editableItems.0.editableByUser': true
            });
        });

        beforeEach(async function () {
            page.actions
                .navigateToPage(page.constants.CONTENT_PAGE_TYPE, true, __dirname);
        });

        it('WHEN a restriction is added AND save event returns 2 errors (for existing restriction and for a new restriction) THEN restriction list should highlight both of them', function () {
            // GIVEN
            var newRestrictionData = {
                activeFrom: '10/12/22 11:05 AM',
                activeUntil: '11/12/22 11:05 AM'
            };

            // WHEN
            restrictionsEditor.actions.createNewTimeRestriction(
                newTimeRestrictionName,
                newRestrictionData
            );
            restrictionsEditor.actions.clickAddRestrictionButton();
            page.actions.clickSaveAndPropagateErrors();

            // THEN
            expect(restrictionsEditor.elements.getRestrictionsWithErrorsCount()).toBe(2);
        });

        it('GIVEN a list of restrictions AND save event returns 2 errors (for existing restriction and for a new restriction) WHEN any broken restriction is opened for editing THEN a proper error message is displayed under the field', function () {
            // GIVEN
            var newRestrictionData = {
                activeFrom: '10/12/22 11:05 AM',
                activeUntil: '11/12/22 11:05 AM'
            };

            restrictionsEditor.actions.createNewTimeRestriction(
                newTimeRestrictionName,
                newRestrictionData
            );
            restrictionsEditor.actions.clickAddRestrictionButton();
            page.actions.clickSaveAndPropagateErrors();

            // WHEN
            restrictionsEditor.actions.openRestrictionForEditing(existingTimeRestrictionName);

            // THEN

            expect(restrictionsEditor.elements.getErrorMessageByFieldName('activeFrom')).toBe(
                'Error message activeFrom'
            );
            restrictionsEditor.actions.clickSliderPanelCloseButton();

            // WHEN
            restrictionsEditor.actions.openRestrictionForEditing(newTimeRestrictionName);

            // THEN
            expect(restrictionsEditor.elements.getErrorMessageByFieldName('name')).toBe(
                'Error message name'
            );
        });

        it('GIVEN a slider panel WHEN Time restriction to be added AND activeFrom is less then activeUntil THEN error is displayed', function () {
            // GIVEN
            var newRestrictionData = {
                activeFrom: '10/12/23 11:05 AM',
                activeUntil: '11/12/22 11:05 AM'
            };

            // WHEN
            restrictionsEditor.actions.createNewTimeRestriction(
                newTimeRestrictionName,
                newRestrictionData
            );
            restrictionsEditor.actions.clickAddRestrictionButton();

            // THEN
            expect(restrictionsEditor.elements.getErrorMessageByFieldName('activeUntil')).toBe(
                'The dates and times provided are not valid. The Active until date/time must be after/later than the Active from date/time.'
            );
        });

        it('GIVEN a slider panel WHEN Time restriction to be edited AND activeFrom is less then activeUntil THEN error is displayed', function () {
            // GIVEN
            var newRestrictionData = {
                activeFrom: '10/12/23 11:05 AM',
                activeUntil: '11/12/22 11:05 AM'
            };

            // WHEN
            restrictionsEditor.actions.openRestrictionForEditing(existingTimeRestrictionName);
            restrictionsEditor.actions.setTimeRestrictionData(newRestrictionData);
            restrictionsEditor.actions.clickUpdateRestrictionButton();

            // THEN
            expect(restrictionsEditor.elements.getErrorMessageByFieldName('activeUntil')).toBe(
                'The dates and times provided are not valid. The Active until date/time must be after/later than the Active from date/time.'
            );
        });

        afterAll(function () {
            backendClient.removeFixture(fixtureID0);
        });
    });

    describe('Editable restriction', function () {
        var existingTimeRestrictionName =
            restrictionsEditor.constants.EXISTING_TIME_RESTRICTION_NAME;
        var newTimeRestrictionName = restrictionsEditor.constants.NEW_TIME_RESTRICTION_NAME;
        var newRestrictionData = {
            activeFrom: '10/12/22 11:05 AM',
            activeUntil: '11/12/22 11:05 AM'
        };

        var updatedRestrictionData = {
            activeFrom: '10/12/23 11:05 AM',
            activeUntil: '11/12/23 11:05 AM'
        };

        let fixtureID0;

        beforeAll(async function () {
            fixtureID0 = await backendClient.modifyFixture([COMMON_PATH], {
                'editableItems.0.editableByUser': true
            });
        });

        beforeEach(async function () {
            page.actions
                .navigateToPage(page.constants.CONTENT_PAGE_TYPE, false, __dirname);
        });

        it(
            'GIVEN the restriction editor is open ' +
                'WHEN I add an existing restriction that is supported for editing ' +
                'THEN it is added to the list',
            function () {
                // GIVEN
                restrictionsEditor.assertions.listHasExpectedNumberOfRestrictions(0);
                restrictionsEditor.assertions.restrictionIsNotInList(existingTimeRestrictionName);

                // WHEN
                restrictionsEditor.actions.addExistingTimeRestriction(existingTimeRestrictionName);
                restrictionsEditor.actions.clickAddRestrictionButton();

                // THEN
                restrictionsEditor.assertions.listHasExpectedNumberOfRestrictions(1);
                restrictionsEditor.assertions.restrictionIsInList(existingTimeRestrictionName);
            }
        );

        // TODO: Test is flaky. Will fix it in a separate ticket.
        xit(
            'GIVEN the restriction editor is open ' +
                'WHEN I select an existing restriction that is supported for editing ' +
                'THEN it cannot be edited',
            function () {
                // GIVEN
                restrictionsEditor.assertions.restrictionIsNotInList(existingTimeRestrictionName);

                // WHEN
                restrictionsEditor.actions.addExistingTimeRestriction(existingTimeRestrictionName);

                // THEN
                restrictionsEditor.assertions.timeRestrictionPanelIsNotEditable();
            }
        );

        it(
            'GIVEN the restriction editor for a supported restriction is open ' +
                'WHEN I type a new restriction name ' +
                'THEN it can be created and added to the list',
            function () {
                // GIVEN
                restrictionsEditor.assertions.restrictionIsNotInList(newTimeRestrictionName);

                // WHEN
                restrictionsEditor.actions.createNewTimeRestriction(
                    newTimeRestrictionName,
                    newRestrictionData
                );
                restrictionsEditor.actions.clickAddRestrictionButton();

                // THEN
                restrictionsEditor.assertions.restrictionIsInList(newTimeRestrictionName);
                restrictionsEditor.assertions.timeRestrictionHasRightData(
                    newTimeRestrictionName,
                    newRestrictionData
                );
            }
        );

        it(
            'GIVEN a restriction is in the restrictions list ' +
                'WHEN I click the edit restriction button ' +
                'THEN the panel is opened in edit mode',
            function () {
                // GIVEN
                restrictionsEditor.actions.createNewTimeRestriction(
                    newTimeRestrictionName,
                    newRestrictionData
                );
                restrictionsEditor.actions.clickAddRestrictionButton();

                // WHEN
                restrictionsEditor.actions.openRestrictionForEditing(newTimeRestrictionName);

                // THEN
                restrictionsEditor.assertions.timeRestrictionIsInEditMode();
            }
        );

        it(
            'GIVEN a time restriction is in the restrictions list ' +
                'WHEN I click the edit restriction button ' +
                'THEN I can edit and save changes to the restriction',
            function () {
                // GIVEN
                restrictionsEditor.actions.createNewTimeRestriction(
                    newTimeRestrictionName,
                    newRestrictionData
                );
                restrictionsEditor.actions.clickAddRestrictionButton();

                // WHEN
                restrictionsEditor.actions.updateTimeRestrictionData(
                    newTimeRestrictionName,
                    updatedRestrictionData
                );
                restrictionsEditor.actions.clickUpdateRestrictionButton();

                // THEN
                restrictionsEditor.assertions.restrictionIsInList(newTimeRestrictionName);
                restrictionsEditor.assertions.timeRestrictionHasRightData(
                    newTimeRestrictionName,
                    updatedRestrictionData
                );
            }
        );

        afterAll(function () {
            backendClient.removeFixture(fixtureID0);
        });
    });

    describe('Non-editable restrictions', function () {
        var existingRestrictionName = restrictionsEditor.constants.EXISTING_USER_RESTRICTION_NAME;
        var newRestrictionName = 'some invalid restriction name';
        let fixtureID0;

        beforeAll(async function () {
            fixtureID0 = await backendClient.modifyFixture([COMMON_PATH], {
                'editableItems.0.editableByUser': true
            });
        });

        beforeEach(async function () {
            page.actions
                .navigateToPage(page.constants.CATEGORY_PAGE_TYPE, false, __dirname);
        });

        it(
            'GIVEN the restriction editor is open ' +
                'WHEN I select a restriction not supported for editing in SmartEdit ' +
                'THEN I can select an existing restriction and the panel will be read only',
            function () {
                // GIVEN
                restrictionsEditor.assertions.restrictionIsNotInList(existingRestrictionName);

                // WHEN
                restrictionsEditor.actions.addExistingNonSupportedRestriction(
                    existingRestrictionName
                );

                // THEN
                restrictionsEditor.assertions.restrictionPanelIsEmptyAndNotEditable();
            }
        );

        it(
            'GIVEN the restriction editor is open ' +
                'WHEN I select a restriction not supported for editing in SmartEdit ' +
                'THEN I can select an existing restriction and add it to the list',
            function () {
                // GIVEN
                restrictionsEditor.assertions.restrictionIsNotInList(existingRestrictionName);

                // WHEN
                restrictionsEditor.actions.addExistingNonSupportedRestriction(
                    existingRestrictionName
                );
                restrictionsEditor.actions.clickAddRestrictionButton();

                // THEN
                restrictionsEditor.assertions.restrictionIsInList(existingRestrictionName);
            }
        );

        it(
            'GIVEN the restriction editor is open ' +
                'WHEN I select a restriction not supported for editing in SmartEdit ' +
                'THEN I cannot create a new restriction',
            function () {
                // GIVEN
                restrictionsEditor.assertions.restrictionIsNotInList(newRestrictionName);

                // WHEN
                restrictionsEditor.actions.tryToCreateNewNonSupportedRestriction(
                    newRestrictionName
                );

                // THEN
                browser.waitForAbsence(
                    restrictionsEditor.elements.getRestrictionSearchBoxNewButton(),
                    'Expected Create button not to be available'
                );
            }
        );

        it(
            'GIVEN the list contains a restriction not supported for editing in SmartEdit ' +
                'WHEN I edit it ' +
                'THEN SmartEdit will show that the restriction is not editable',
            function () {
                // GIVEN
                restrictionsEditor.actions.addExistingNonSupportedRestriction(
                    existingRestrictionName
                );
                restrictionsEditor.actions.clickAddRestrictionButton();

                // WHEN
                restrictionsEditor.actions.openRestrictionForEditing(existingRestrictionName);

                // THEN
                restrictionsEditor.assertions.restrictionCannotBeEdited();
            }
        );

        afterAll(function () {
            backendClient.removeFixture(fixtureID0);
        });
    });

    describe('clear all button', function () {
        var existingCategoryRestrictionName =
            restrictionsEditor.constants.EXISTING_CATEGORY_RESTRICTION_NAME;

        beforeEach(async function () {
            page.actions
                .navigateToPage(page.constants.CATEGORY_PAGE_TYPE, false, __dirname);
        });

        it(
            'GIVEN a list of existing restrictions for a page, ' +
                'WHEN the "clear all" button is clicked, ' +
                'THEN the restrictions list should be empty',
            function () {
                //GIVEN
                restrictionsEditor.actions.addExistingCategoryRestriction(
                    existingCategoryRestrictionName
                );
                restrictionsEditor.actions.clickAddRestrictionButton();
                restrictionsEditor.assertions.listHasExpectedNumberOfRestrictions(1);

                //WHEN
                restrictionsEditor.actions.clickClearAllButton();

                //THEN
                restrictionsEditor.assertions.listHasExpectedNumberOfRestrictions(0);
            }
        );
    });

    describe('Non-editable in context (because of workflow) restrictions', function () {
        beforeEach(async function () {
            workflow.utils.makeItemsEditable(['timeRestrictionIdA']);
            await page.actions
                .navigateToPage(page.constants.CONTENT_PAGE_TYPE, true, __dirname);
        });

        it('GIVEN list of restrictions AND second restriction is not edited in current context THEN there is no edit button', function () {
            // GIVEN
            restrictionsEditor.assertions.listHasExpectedNumberOfRestrictions(2);

            // WHEN
            restrictionsEditor.actions.openRestrictionDropdownMenu(
                restrictionsEditor.constants.EXISTING_CATEGORY_RESTRICTION_NAME
            );

            // THEN
            restrictionsEditor.assertions.assertEditButtonIsHidden(
                restrictionsEditor.constants.EXISTING_CATEGORY_RESTRICTION_NAME
            );
            restrictionsEditor.assertions.assertRestrictionPartOfWorkflowMessageDisplayed(
                restrictionsEditor.constants.EXISTING_CATEGORY_RESTRICTION_NAME
            );
        });
    });

    describe('Editable in context (because of workflow) restrictions', function () {
        let fixtureID0;
        let fixtureID1;

        beforeAll(async function () {
            fixtureID0 = await backendClient.replaceFixture(
                [
                    'cmssmarteditwebservices\\/v1\\/catalogs\\/apparel-ukContentCatalog\\/versions\\/Staged\\/workfloweditableitems\\?itemUids=timeRestrictionIdA'
                ],
                {
                    editableItems: [
                        {
                            uid: 'timeRestrictionIdA',
                            uuid: 'timeRestrictionIdA',
                            editableByUser: true
                        }
                    ]
                }
            );

            fixtureID1 = await backendClient.replaceFixture(
                [
                    'cmssmarteditwebservices\\/v1\\/catalogs\\/apparel-ukContentCatalog\\/versions\\/Staged\\/workfloweditableitems\\?itemUids=catalogRestrictionIdD'
                ],
                {
                    editableItems: [
                        {
                            uid: 'catalogRestrictionIdD',
                            uuid: 'catalogRestrictionIdD',
                            editableByUser: true
                        }
                    ]
                }
            );
        });
        beforeEach(async function () {
            workflow.utils.makeItemsEditable(['timeRestrictionIdA', 'catalogRestrictionIdD']);
            await page.actions
                .navigateToPage(page.constants.CONTENT_PAGE_TYPE, true, __dirname);
        });

        it('GIVEN list of restrictions AND second restriction is edited in current context THEN there is no edit button', function () {
            // GIVEN
            restrictionsEditor.assertions.listHasExpectedNumberOfRestrictions(2);

            // WHEN
            restrictionsEditor.actions.openRestrictionDropdownMenu(
                restrictionsEditor.constants.EXISTING_CATEGORY_RESTRICTION_NAME
            );

            // THEN
            restrictionsEditor.assertions.assertEditButtonIsVisible(
                restrictionsEditor.constants.EXISTING_CATEGORY_RESTRICTION_NAME
            );
        });

        afterAll(function () {
            backendClient.removeFixture(fixtureID0);
            backendClient.removeFixture(fixtureID1);
        });
    });
});
