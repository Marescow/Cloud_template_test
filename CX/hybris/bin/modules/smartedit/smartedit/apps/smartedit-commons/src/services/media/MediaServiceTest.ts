/*
 * Copyright (c) 2022 SAP SE or an SAP affiliate company. All rights reserved.
 */
import { IRestService, RestServiceFactory } from 'smarteditcommons';
import { MediaDTO } from '../interfaces/IMediaService';
import { MediaService } from './MediaService';

describe('MediaService', () => {
    const mockMedia = {
        catalogId: 'electronicsContentCatalog',
        catalogVersion: 'Staged',
        code: 'addressBookPagePreview',
        downloadUrl:
            '/medias/AddressBook.png?context=bWFzdGVyfGltYWdlc3wyNDU0NnxpbWFnZS9wbmd8aW1hZ2VzL2hmNi9oMzYvODc5Njg4MDUwMjgxNC5wbmd8OTIyY2U3NjUzYmY1NjNlYmRmMzg5NTBjNGQ5ZDY5NGJiMmI5OTc0NDM0ZjE1NTRjODc3NmMxOWFlZjJjYTMyNA&attachment=true',
        mime: 'image/png',
        url: '/medias/AddressBook.png?context=bWFzdGVyfGltYWdlc3wyNDU0NnxpbWFnZS9wbmd8aW1hZ2VzL2hmNi9oMzYvODc5Njg4MDUwMjgxNC5wbmd8OTIyY2U3NjUzYmY1NjNlYmRmMzg5NTBjNGQ5ZDY5NGJiMmI5OTc0NDM0ZjE1NTRjODc3NmMxOWFlZjJjYTMyNA',
        uuid: 'eyJpdGVtSWQiOiJhZGRyZXNzQm9va1BhZ2VQcmV2aWV3IiwiY2F0YWxvZ0lkIjoiZWxlY3Ryb25pY3NDb250ZW50Q2F0YWxvZyIsImNhdGFsb2dWZXJzaW9uIjoiU3RhZ2VkIn0='
    };

    let restServiceFactory: jasmine.SpyObj<RestServiceFactory>;
    let mockRestService: jasmine.SpyObj<IRestService<MediaDTO>>;

    let service: MediaService;
    beforeEach(() => {
        restServiceFactory = jasmine.createSpyObj<RestServiceFactory>('restServiceFactory', [
            'get'
        ]);
        mockRestService = jasmine.createSpyObj<IRestService<MediaDTO>>('restService', ['get']);

        service = new MediaService(restServiceFactory);
    });

    it('getPage fetches page of Media properly', async () => {
        const mockMediaResponse = {
            media: [mockMedia]
        };
        mockRestService.get.and.returnValue(Promise.resolve<any>(mockMediaResponse));
        restServiceFactory.get.and.returnValue(mockRestService);

        const page = await service.getPage('', 0, 0, '');
        expect(page.results[0]).toEqual(jasmine.objectContaining({ id: mockMedia.uuid }));
    });

    it('getMedia fetches Media properly by given uuid', async () => {
        const mockMediaResponse = mockMedia;
        mockRestService.get.and.returnValue(Promise.resolve<any>(mockMediaResponse));
        restServiceFactory.get.and.returnValue(mockRestService);

        const media = await service.getMedia('123');

        expect(restServiceFactory.get).toHaveBeenCalledWith(jasmine.stringMatching(/\/123$/));
        expect(media).toEqual(jasmine.objectContaining({ id: mockMedia.uuid }));
    });

    it('mediaDTOtoMedia correctly', () => {
        const aMediaDTO: MediaDTO = {
            id: '1',
            code: 'contextualmenu_delete_off',
            uuid: 'contextualmenu_delete_off',
            description: 'contextualmenu_delete_off',
            altText: 'contextualmenu_delete_off alttext',
            realFileName: 'contextualmenu_delete_off.png',
            url: '/apps/cms-smartedit-e2e/generated/images/contextualmenu_delete_off.png',
            catalogId: 'fake catalog',
            catalogVersion: 'fake version',
            downloadUrl: '/apps/cms-smartedit-e2e/generated/images/contextualmenu_delete_off.png',
            mime: 'fake mime'
        };
        expect((service as any).mediaDTOtoMedia(aMediaDTO)).toEqualData({
            id: 'contextualmenu_delete_off',
            code: 'contextualmenu_delete_off',
            description: 'contextualmenu_delete_off',
            altText: 'contextualmenu_delete_off alttext',
            realFileName: 'contextualmenu_delete_off.png',
            url: '/apps/cms-smartedit-e2e/generated/images/contextualmenu_delete_off.png',
            downloadUrl: '/apps/cms-smartedit-e2e/generated/images/contextualmenu_delete_off.png',
            mime: 'fake mime'
        });
    });
});
