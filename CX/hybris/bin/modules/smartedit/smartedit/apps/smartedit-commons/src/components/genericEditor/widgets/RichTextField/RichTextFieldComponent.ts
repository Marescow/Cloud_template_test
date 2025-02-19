/*
 * Copyright (c) 2022 SAP SE or an SAP affiliate company. All rights reserved.
 */
import { AfterViewInit, Component, ElementRef, Inject, OnDestroy, ViewChild } from '@angular/core';
import { TypedMap } from '@smart/utils';

import { SeDowngradeComponent } from 'smarteditcommons/di';
import { SettingsService } from 'smarteditcommons/services';
import { GenericEditorWidgetData } from '../../../genericEditor/types';
import { GENERIC_EDITOR_WIDGET_DATA } from '../../components/tokens';
import { GenericEditorSanitizationService } from './services/GenericEditorSanitizationService';
import { RichTextFieldLocalizationService } from './services/RichTextFieldLocalizationService';
import { RichTextLoaderService } from './services/RichTextLoaderService';
import { RICH_TEXT_CONFIGURATION } from './tokens';
let HAS_BIND = false;
/* @internal */
@SeDowngradeComponent()
@Component({
    selector: 'se-rich-text-field',
    templateUrl: './RichTextFieldComponent.html'
})
export class RichTextFieldComponent implements AfterViewInit, OnDestroy {
    @ViewChild('textarea', { static: false }) private textarea: ElementRef;
    readonly LICENSE_KEY =
        'TVdvME0wNUZWMVF6YkVsT01uaDVhM2MzYzNSeU9YSnUtTnpVNU9UTXpOVGM0T1RNMk5qYzI=';
    private editorInstance: CKEDITOR.editor;
    private readonly saveBtnId = 'save';
    private readonly events = ['focus', 'mousedown'];
    private saveBtn: HTMLElement;

    constructor(
        @Inject(GENERIC_EDITOR_WIDGET_DATA) public widget: GenericEditorWidgetData<any>,
        private seRichTextLoaderService: RichTextLoaderService,
        @Inject(RICH_TEXT_CONFIGURATION) private seRichTextConfiguration: TypedMap<any>,
        private genericEditorSanitizationService: GenericEditorSanitizationService,
        private seRichTextFieldLocalizationService: RichTextFieldLocalizationService,
        private settingsService: SettingsService
    ) {
        this.saveBtn = document.getElementById(this.saveBtnId);
        if (!HAS_BIND && this.saveBtn) {
            this.addMultipleEventListeners(this.saveBtn);
            HAS_BIND = true;
        }
    }

    ngAfterViewInit(): Promise<void> {
        return this.settingsService
            .get('cms.components.allowUnsafeJavaScript')
            .then((allowUnsafeJavaScript: string) =>
                this.seRichTextLoaderService.load().then(() => {
                    this.seRichTextConfiguration.allowedContent = allowUnsafeJavaScript === 'true';
                    this.seRichTextConfiguration.licenseKey = this.LICENSE_KEY;
                    this.editorInstance = window.CKEDITOR.replace(
                        this.textarea.nativeElement,
                        this.seRichTextConfiguration
                    );

                    this.seRichTextFieldLocalizationService.localizeCKEditor();

                    if (this.editorInstance) {
                        this.editorInstance.on('change', () => this.onChange());
                        this.editorInstance.on('mode', () => this.onMode());
                        window.CKEDITOR.on('instanceReady', (ev: any) => this.onInstanceReady(ev));
                    }
                })
            );
    }

    ngOnDestroy(): void {
        if (this.editorInstance && window.CKEDITOR.instances[this.editorInstance.name]) {
            window.CKEDITOR.instances[this.editorInstance.name].destroy();
            HAS_BIND = false;
        }
    }

    addMultipleEventListeners(element: HTMLElement): void {
        this.events.forEach((event) => {
            element.addEventListener(event, this.bindSaveEvent.bind(this));
        });
    }
    bindSaveEvent(): void {
        if (this.editorInstance.mode === 'source') {
            this.editorInstance.setMode('wysiwyg', this.onChange.bind(this));
        } else {
            this.onChange();
        }
    }

    onChange(): void {
        setTimeout(() => {
            this.widget.model[this.widget.qualifier] = this.editorInstance.getData();
            this.reassignUserCheck();
            this.toggleSubmitButton(this.requiresUserCheck() && !this.widget.field.isUserChecked);
        });
    }

    onMode(): void {
        if (this.editorInstance.mode === 'source') {
            const editable = this.editorInstance.editable();
            editable.attachListener(editable, 'input', () => {
                this.editorInstance.fire('change');
            });
        }
    }

    onInstanceReady(ev: any): void {
        const tags = window.CKEDITOR.dtd;
        const elements = {
            ...tags.$nonBodyContent,
            ...tags.$block,
            ...tags.$listItem,
            ...tags.$tableContent,
            br: 1
        };

        for (const element of Object.keys(elements)) {
            ev.editor.dataProcessor.writer.setRules(element, {
                indent: false,
                breakBeforeOpen: false,
                breakAfterOpen: false,
                breakBeforeClose: false,
                breakAfterClose: false
            });
        }

        ev.editor.dataProcessor.writer.lineBreakChars = '';
        ev.editor.dataProcessor.writer.indentationChars = '';
    }

    requiresUserCheck(): boolean {
        let requiresUserCheck = false;
        for (const qualifier in this.widget.field.requiresUserCheck) {
            if (this.widget.field.requiresUserCheck.hasOwnProperty(qualifier)) {
                requiresUserCheck =
                    requiresUserCheck || this.widget.field.requiresUserCheck[qualifier];
            }
        }
        return requiresUserCheck;
    }

    reassignUserCheck(): void {
        if (
            this.widget.model &&
            this.widget.qualifier &&
            this.widget.model[this.widget.qualifier]
        ) {
            const sanitizedContentMatchesContent = this.genericEditorSanitizationService.isSanitized(
                this.widget.model[this.widget.qualifier]
            );
            this.widget.field.requiresUserCheck = this.widget.field.requiresUserCheck || {};
            this.widget.field.requiresUserCheck[
                this.widget.qualifier
            ] = !sanitizedContentMatchesContent;
        } else {
            this.widget.field.requiresUserCheck = this.widget.field.requiresUserCheck || {};
            this.widget.field.requiresUserCheck[this.widget.qualifier] = false;
        }
    }

    checkboxOnClick(event: HTMLInputElement): void {
        this.toggleSubmitButton(!event.checked);
    }

    private toggleSubmitButton(state: boolean): void {
        this.widget.editor.api.setInProgress(state);
    }
}
