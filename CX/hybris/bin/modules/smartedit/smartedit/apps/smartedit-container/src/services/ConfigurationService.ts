/*
 * Copyright (c) 2022 SAP SE or an SAP affiliate company. All rights reserved.
 */
import { NgForm } from '@angular/forms';
import * as lo from 'lodash';

import {
    objectUtils,
    stringUtils,
    CONFIGURATION_URI,
    Errors,
    IRestService,
    LogService,
    RestServiceFactory,
    SeDowngradeService
} from 'smarteditcommons';
import {
    Configuration,
    ConfigurationItem
} from 'smarteditcontainer/services/bootstrap/Configuration';
import { LoadConfigManagerService } from './bootstrap/LoadConfigManagerService';

interface ConfigurationHolder {
    keys: string[];
    errors: boolean;
}

/** @internal */
@SeDowngradeService()
export class ConfigurationService {
    // Constants
    private readonly ABSOLUTE_URI_NOT_APPROVED = 'URI_EXCEPTION';
    private readonly ABSOLUTE_URI_REGEX = /(\"[A-Za-z]+:\/|\/\/)/;

    private editorCRUDService: IRestService<Configuration>;
    private configuration: Configuration = [];
    private pristine: Configuration;
    private loadCallback: () => void;

    constructor(
        private logService: LogService,
        private loadConfigManagerService: LoadConfigManagerService,
        private restServiceFactory: RestServiceFactory
    ) {
        this.editorCRUDService = this.restServiceFactory.get<Configuration>(
            CONFIGURATION_URI,
            'key'
        );
    }

    /*
     * The Add Entry method adds an entry to the list of configurations.
     *
     */
    public addEntry(): void {
        const item: ConfigurationItem = { key: '', value: '', isNew: true, uuid: lo.uniqueId() };

        this.configuration = [item, ...(this.configuration || [])];
    }
    /*
     * The Remove Entry method deletes the specified entry from the list of configurations. The method does not delete the actual configuration, but just removes it from the array of configurations.
     * The entry will be deleted when a user clicks the Submit button but if the entry is new we can are removing it from the configuration
     *
     * @param {Object} entry The object to be deleted
     * @param {Object} configurationForm The form object which is an instance of {@link https://docs.angularjs.org/api/ng/type/form.FormController FormController}
     * that provides methods to monitor and control the state of the form.
     */
    public removeEntry(entry: ConfigurationItem, configurationForm: NgForm): void {
        if (entry.isNew) {
            this.configuration = this.configuration.filter(
                (confEntry: ConfigurationItem) => confEntry.uuid !== entry.uuid
            );
        } else {
            configurationForm.form.markAsDirty();
            const NewSameKeyEntry = this.configuration.find(
                (confEntry: ConfigurationItem) => confEntry.isNew && confEntry.key === entry.key
            );
            if (!!NewSameKeyEntry) {
                NewSameKeyEntry.isNew = false;
                this.configuration = this.configuration.filter(
                    (confEntry: ConfigurationItem) => confEntry.uuid !== entry.uuid
                );
            } else {
                entry.toDelete = true;
            }
        }
        this.isValid(configurationForm);
    }
    /*
     * Method that returns a list of configurations by filtering out only those configurations whose 'toDelete' parameter is set to false.
     *
     * @returns {Object} A list of filtered configurations.
     */
    public filterConfiguration(): ConfigurationItem[] {
        return (this.configuration || []).filter(
            (instance: ConfigurationItem) => !instance.toDelete
        );
    }

    public filterErrorConfiguration(): ConfigurationItem[] {
        return (this.configuration || []).filter(
            (instance: ConfigurationItem) => instance.hasErrors && !instance.toDelete
        );
    }

    public validateUserInput(entry: ConfigurationItem): void {
        if (!entry.value) {
            return;
        }

        entry.requiresUserCheck = !!entry.value.match(this.ABSOLUTE_URI_REGEX);
    }
    /*
     * The Submit method saves the list of available configurations by making a REST call to a web service.
     * The method is called when a user clicks the Submit button in the configuration editor.
     *
     * @param {Object} configurationForm The form object that is an instance of {@link https://docs.angularjs.org/api/ng/type/form.FormController FormController}.
     * It provides methods to monitor and control the state of the form.
     */
    public submit(configurationForm: NgForm): Promise<any[]> {
        if (!configurationForm.dirty || !this.isValid(configurationForm)) {
            return Promise.reject<any[]>([]);
        }

        configurationForm.form.markAsPristine();

        return Promise.all(
            this.configuration.map((entry: ConfigurationItem, i: number) =>
                this.reconstructConfigurationEntry(entry, i)
            )
        );
    }

    /*
     * The init method initializes the configuration editor and loads all the configurations so they can be edited.
     *
     * @param {Function} loadCallback The callback to be executed after loading the configurations.
     */
    public init(_loadCallback?: () => void): Promise<any> {
        this.loadCallback = _loadCallback || lo.noop;
        return this.loadAndPresent();
    }

    public isValid(configurationForm: NgForm): boolean {
        let hasError = false;
        this.configuration.forEach((entry: ConfigurationItem) => {
            delete entry.errors;
        });
        this.configuration.forEach((entry: ConfigurationItem) => {
            if (stringUtils.isBlank(entry.key)) {
                this.addKeyError(entry, 'se.configurationform.required.entry.error');
                entry.hasErrors = true;
                hasError = true;
            }
            if (stringUtils.isBlank(entry.value)) {
                this.addValueError(entry, 'se.configurationform.required.entry.error');
                entry.hasErrors = true;
                hasError = true;
            }
        });

        return !hasError && configurationForm.valid && !this.findDuplicateKeyConfiguration();
    }

    private reconstructConfigurationEntry(entry: ConfigurationItem, i: number): Promise<any> {
        const payload = objectUtils.copy(entry);
        let method: string;

        payload.secured = false; // needed for yaas configuration service
        delete payload.toDelete;
        delete payload.errors;
        delete payload.uuid;
        try {
            if (entry.toDelete) {
                method = 'remove';
            } else if (payload.isNew) {
                method = 'save';
                payload.value = this.validate(payload);
            } else {
                method = 'update';
                payload.value = this.validate(payload);
            }
        } catch (error) {
            entry.hasErrors = true;
            if (error instanceof Errors.ParseError) {
                this.addValueError(entry, 'se.configurationform.json.parse.error');
            }
            return Promise.reject<void>({});
        }

        delete payload.isNew;
        entry.hasErrors = false;

        // eslint-disable-next-line @typescript-eslint/no-unsafe-return
        return this.editorCRUDService[method](payload).then(
            () => {
                switch (method) {
                    case 'save':
                        delete entry.isNew;
                        break;
                    case 'remove':
                        this.configuration.splice(i, 1);
                        break;
                    default:
                }
                return Promise.resolve<any>({});
            },
            () => {
                this.addValueError(entry, 'se.configurationform.save.error');
                return Promise.reject<any>({});
            }
        );
    }

    private reset(configurationForm?: NgForm): void {
        this.configuration = objectUtils.copy(this.pristine);

        if (configurationForm) {
            configurationForm.form.markAsPristine();
        }
        if (this.loadCallback) {
            this.loadCallback();
        }
    }
    private addError(entry: ConfigurationItem, type: string, message: string): void {
        entry.hasErrors = true;
        entry.errors = entry.errors || {};
        entry.errors[type] = entry.errors[type] || [];
        entry.errors[type].push({
            message
        });
    }
    private addKeyError(entry: ConfigurationItem, message: string): void {
        this.addError(entry, 'keys', message);
    }
    private addValueError(entry: ConfigurationItem, message: string): void {
        this.addError(entry, 'values', message);
    }
    private prettify(array: ConfigurationItem[]): ConfigurationItem[] {
        const configuration = objectUtils.copy(array);
        configuration.forEach((entry: ConfigurationItem) => {
            try {
                entry.value = JSON.stringify(JSON.parse(entry.value), null, 2);
            } catch (parseError) {
                this.addValueError(entry, 'se.configurationform.json.parse.error');
            }
        });
        return configuration;
    }

    /**
     * for editing purposes
     */
    private loadAndPresent(): Promise<void> {
        return new Promise((resolve, reject) =>
            this.loadConfigManagerService.loadAsArray().then(
                (response: ConfigurationItem[]) => {
                    this.pristine = this.prettify(
                        response.map((item) => ({ ...item, uuid: lo.uniqueId() }))
                    );
                    this.reset();
                    resolve();
                },
                () => {
                    this.logService.log('load failed');
                    reject();
                }
            )
        );
    }

    private findDuplicateKeyConfiguration(): boolean {
        return this.configuration.reduce(
            (confHolder: ConfigurationHolder, nextConfiguration: ConfigurationItem) => {
                if (nextConfiguration.toDelete) {
                    return confHolder;
                }
                if (confHolder.keys.indexOf(nextConfiguration.key) > -1) {
                    this.addKeyError(
                        nextConfiguration,
                        'se.configurationform.duplicate.entry.error'
                    );
                    confHolder.errors = true;
                } else {
                    confHolder.keys.push(nextConfiguration.key);
                }
                if (!nextConfiguration.errors && nextConfiguration.hasErrors) {
                    nextConfiguration.hasErrors = false;
                }
                return confHolder;
            },
            {
                keys: [],
                errors: false
            }
        ).errors;
    }

    private validate(entry: ConfigurationItem): string {
        if (entry.requiresUserCheck && !entry.isCheckedByUser) {
            throw new Error(this.ABSOLUTE_URI_NOT_APPROVED);
        } else {
            try {
                return JSON.stringify(JSON.parse(entry.value));
            } catch (parseError) {
                throw new Errors.ParseError(entry.value);
            }
        }
    }
}
