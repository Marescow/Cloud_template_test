/*
 * Copyright (c) 2022 SAP SE or an SAP affiliate company. All rights reserved.
 */
import { ChangeDetectorRef, ComponentFactoryResolver, SimpleChange } from '@angular/core';
import { MediaUploadFormComponent } from 'cmssmarteditcontainer/components/genericEditor/media/components';
import {
    MediaBackendValidationHandler,
    MediaFolderService,
    MediaUtilService
} from 'cmssmarteditcontainer/components/genericEditor/media/services';
import {
    FileValidatorFactory,
    IMediaToUpload,
    ICMSMedia,
    MediaUploadFieldsCustom
} from 'smarteditcommons';
import { createSimulateNgOnChanges } from 'testhelpers';

const fakeMediaUploadFieldsCustomDirective = {
    viewContainerRef: {
        clear: () => {},
        createComponent: () => ({
            instance: {
                image: '',
                customFieldsChange: {
                    subscribe: () => ({
                        unsubscribe: () => {}
                    })
                }
            }
        })
    }
};

describe('MediaUploadFormComponent', () => {
    const fileValidatorFactory = new FileValidatorFactory();
    const cdr = jasmine.createSpyObj<ChangeDetectorRef>('changeDetectorRef', [
        'detectChanges',
        'markForCheck'
    ]);
    let mediaBackendValidationHandler: jasmine.SpyObj<MediaBackendValidationHandler>;
    let mediaUploaderService: jasmine.SpyObj<IMediaToUpload>;
    let mediaFolderService: jasmine.SpyObj<MediaFolderService>;
    let mediaUtilService: jasmine.SpyObj<MediaUtilService>;
    let resolver: jasmine.SpyObj<ComponentFactoryResolver>;
    let mediaUploadFieldsCustom: MediaUploadFieldsCustom;

    let component: MediaUploadFormComponent;

    type Input = Partial<Pick<typeof component, 'image'>>;
    let simulateNgOnChanges: ReturnType<typeof createSimulateNgOnChanges>;
    beforeEach(() => {
        mediaBackendValidationHandler = jasmine.createSpyObj<MediaBackendValidationHandler>(
            'mediaBackendValidationHandler',
            ['handleResponse']
        );
        mediaUploaderService = jasmine.createSpyObj<IMediaToUpload>('mediaUploaderService', [
            'uploadMedia'
        ]);
        mediaUtilService = jasmine.createSpyObj<MediaUtilService>('mediaUtilService', [
            'getAcceptedFileTypes'
        ]);
        resolver = jasmine.createSpyObj<ComponentFactoryResolver>('resolver', [
            'resolveComponentFactory'
        ]);
        mediaUploadFieldsCustom = null;

        component = new MediaUploadFormComponent(
            cdr,
            fileValidatorFactory,
            mediaBackendValidationHandler,
            mediaUploaderService,
            mediaFolderService,
            mediaUtilService,
            resolver,
            mediaUploadFieldsCustom
        );
        simulateNgOnChanges = createSimulateNgOnChanges<Input>(component);
    });

    describe('load MediaUploadFieldsCustom', () => {
        it('GIVEN no mediaUploadFieldsCustom WHEN initialize a new MediaUploadFormComponent THEN it does not loadMediaUploadFieldsCustom', () => {
            component = new MediaUploadFormComponent(
                cdr,
                fileValidatorFactory,
                mediaBackendValidationHandler,
                mediaUploaderService,
                mediaFolderService,
                mediaUtilService,
                resolver,
                mediaUploadFieldsCustom
            );
            expect(resolver.resolveComponentFactory).not.toHaveBeenCalled();
        });

        it('GIVEN with mediaUploadFieldsCustom WHEN load MediaUploadFieldsCustom THEN it does loadMediaUploadFieldsCustom', () => {
            (component as any).mediaUploadFieldsCustomDirective = fakeMediaUploadFieldsCustomDirective;
            (component as any).mediaUploadFieldsCustom = { component: null };
            (component as any).loadMediaUploadFieldsCustom();
            expect(resolver.resolveComponentFactory).toHaveBeenCalled();
        });

        it('GIVEN with MediaUploadFieldsCustom WHEN set customFields it uploads the image with corresponding customFields', async () => {
            component.acceptedFileTypes = ['jpg', 'png'];
            const image = { type: 'jpg' } as File;
            component.image = image;
            const name = 'someName';

            component.imageParams = {
                code: name,
                description: name,
                altText: name
            };

            (component as any).customFields = {
                purpose: name,
                someFields: name
            };

            await component.uploadMedia();

            expect(mediaUploaderService.uploadMedia).toHaveBeenCalledWith({
                file: image,
                code: name,
                description: name,
                altText: name,
                folder: '',
                purpose: name,
                someFields: name
            });
        });

        it('GIVEN with MediaUploadFieldsCustom WHEN set customFields it uploads the image path string with corresponding customFields', async () => {
            component.acceptedFileTypes = ['jpg', 'png'];

            component.image = 'fileAddress';
            const name = 'someName';

            component.imageParams = {
                code: name,
                description: name,
                altText: name
            };

            (component as any).customFields = {
                purpose: name,
                someFields: name
            };

            await component.uploadMedia();

            expect(mediaUploaderService.uploadMedia).toHaveBeenCalledWith({
                url: component.image,
                code: name,
                description: name,
                altText: name,
                folder: '',
                purpose: name,
                someFields: name
            });
        });
    });

    describe('GIVEN image has changed', () => {
        it('AND it has value THEN it sets imageParams properly', () => {
            const name = 'someName';

            const image = {
                name
            };
            simulateNgOnChanges({
                image: new SimpleChange(undefined, image, false)
            });

            expect(component.imageParams).toEqual({
                code: name,
                description: name,
                altText: name
            });
        });
    });

    describe('onCancel', () => {
        it('should reset params AND emit', () => {
            const emitSpy = spyOn(component.onCancel, 'emit');
            component.cancel();

            expect(component.imageParams).toEqual(null);
            expect(component.fieldErrors).toEqual([]);
            expect(component.isUploading).toBe(false);

            expect(emitSpy).toHaveBeenCalled();
        });
    });

    describe('uploadMedia', () => {
        beforeEach(() => {
            component.acceptedFileTypes = ['jpg', 'png'];
            const image = { type: 'jpg' } as File;
            component.image = image;
        });

        it('GIVEN no validation errors THEN it uploads the image object', async () => {
            const uploadedMedia = { uuid: '1' } as ICMSMedia;
            mediaUploaderService.uploadMedia.and.returnValue(Promise.resolve(uploadedMedia));

            const onUploadSuccessEmitSpy = spyOn(component.onUploadSuccess, 'emit');
            const name = 'someName';

            component.imageParams = {
                code: name,
                description: name,
                altText: name
            };

            await component.uploadMedia();

            expect(mediaUploaderService.uploadMedia).toHaveBeenCalledWith({
                file: component.image,
                code: name,
                description: name,
                altText: name,
                folder: ''
            });

            // assert that params has been reset
            expect(component.imageParams).toBeNull();
            expect(component.fieldErrors).toEqual([]);
            expect(component.isUploading).toBe(false);

            expect(onUploadSuccessEmitSpy).toHaveBeenCalledWith('1');
            expect(component.isUploading).toBe(false);
        });

        it('GIVEN no validation errors THEN it uploads the image string', async () => {
            component.image = 'fileAddress';
            const name = 'someName';

            component.imageParams = {
                code: name,
                description: name,
                altText: name
            };

            await component.uploadMedia();

            expect(mediaUploaderService.uploadMedia).toHaveBeenCalledWith({
                url: component.image,
                code: name,
                description: name,
                altText: name,
                folder: ''
            });
        });

        it('GIVEN uploadMedia fails THEN it delegates error handling to mediaBackendValidationHandler', async () => {
            component.imageParams = {
                code: 'code'
            } as any;

            mediaUploaderService.uploadMedia.and.returnValue(Promise.reject());

            await component.uploadMedia();

            expect(mediaBackendValidationHandler.handleResponse).toHaveBeenCalled();
        });

        it('GIVEN code is not provided THEN it populates errors AND does not upload', async () => {
            component.imageParams = {
                code: '',
                description: 'someName',
                altText: 'someName'
            };

            await component.uploadMedia();

            expect(mediaUploaderService.uploadMedia).not.toHaveBeenCalled();
            expect(component.fieldErrors.length).toBe(1);
        });
    });

    describe('getErrorsForFieldByCode', () => {
        it('should filter errors on subject and get messages', () => {
            component.fieldErrors = [
                {
                    subject: 'code',
                    message: 'some code message'
                }
            ];
            expect(component.getErrorsForFieldByCode('code')).toEqual(['some code message']);
        });

        it('should not populate messages for unmatched subjects in errors', () => {
            component.fieldErrors = [
                {
                    subject: 'code',
                    message: 'some code message'
                }
            ];
            expect(component.getErrorsForFieldByCode('altText')).toEqual([]);
        });
    });
});
