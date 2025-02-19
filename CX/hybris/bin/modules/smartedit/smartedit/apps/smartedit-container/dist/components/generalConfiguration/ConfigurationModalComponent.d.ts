import './ConfigurationModalComponent.scss';
import { OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ModalManagerService, IConfirmationModalService } from 'smarteditcommons';
import { ConfigurationItem } from '../../services/bootstrap/Configuration';
import { ConfigurationService } from '../../services/ConfigurationService';
export declare class ConfigurationModalComponent implements OnInit {
    editor: ConfigurationService;
    modalManager: ModalManagerService;
    private confirmationModalService;
    form: NgForm;
    isConfigurationsAvailable: boolean;
    constructor(editor: ConfigurationService, modalManager: ModalManagerService, confirmationModalService: IConfirmationModalService);
    ngOnInit(): void;
    formIsInvalid(): boolean;
    trackByFn(_: number, item: ConfigurationItem): string;
    private onCancel;
    private onSave;
}
