/*
 * Copyright (c) 2022 SAP SE or an SAP affiliate company. All rights reserved.
 */
/**
 * @ngdoc overview
 * @name cmsitemsRestService
 *
 * @description
 * The cmsitemsRestService provides a service to CRUD operations on CMS Items API.
 */
import {
    EvictionTag,
    Cached,
    IRestService,
    OperationContextRegistered,
    InvalidateCache,
    IRestOptions
} from '@smart/utils';
import * as lodash from 'lodash';
import { SeDowngradeService } from '../../di';
import { CONTEXT_SITE_ID, CMSITEMS_UPDATE_EVENT } from '../../utils/smarteditconstants';
import { rarelyChangingContent, userEvictionTag } from '../cache';
import { IPage, ICatalogService, IRestServiceFactory, CMSItemSearch, CMSItem } from '../interfaces';
import { LanguageService } from '../language/LanguageService';

export const cmsitemsUri = `/cmswebservices/v1/sites/${CONTEXT_SITE_ID}/cmsitems`;
export const cmsitemsEvictionTag = new EvictionTag({ event: CMSITEMS_UPDATE_EVENT });

/**
 * @ngdoc service
 * @name cmsitemsRestService.cmsitemsRestService
 *
 * @description
 * Service to deal with CMS Items related CRUD operations.
 */
@OperationContextRegistered(cmsitemsUri.replace(/CONTEXT_SITE_ID/, ':CONTEXT_SITE_ID'), 'CMS')
@SeDowngradeService()
export class CmsitemsRestService {
    private readonly cmsitemsUuidsUri = `/cmswebservices/v1/sites/${CONTEXT_SITE_ID}/cmsitems/uuids`;

    private readonly resource: IRestService<CMSItem>;
    private readonly versionedResource: IRestService<CMSItem>;
    private readonly uuidsResource: IRestService<CMSItem[] | IPage<CMSItem>>;

    constructor(
        private readonly restServiceFactory: IRestServiceFactory,
        private readonly catalogService: ICatalogService,
        private readonly languageService: LanguageService
    ) {
        this.resource = restServiceFactory.get(cmsitemsUri);

        this.versionedResource = restServiceFactory.get(cmsitemsUri + '/:itemUuid');

        this.uuidsResource = restServiceFactory.get(this.cmsitemsUuidsUri);
    }

    /**
     * @ngdoc method
     * @name cmsitemsRestService.service:cmsitemsRestService#getByIdAndVersion
     * @methodOf cmsitemsRestService.cmsitemsRestService
     *
     * @description
     * Get the CMS Item that matches the given item uuid (Universally Unique Identifier) for a given version.
     *
     * @param {String} cmsitemUuid The CMS Item uuid
     * @param {String} versionId The uid of the version to be retrieved.
     *
     * @returns {Promise<CMSItem>} If request is successful, it returns a promise that resolves with the CMS Item object. If the
     * request fails, it resolves with errors from the backend.
     */
    @Cached({ actions: [rarelyChangingContent], tags: [userEvictionTag, cmsitemsEvictionTag] })
    public async getByIdAndVersion<T extends CMSItem = CMSItem>(
        itemUuid: string,
        versionId: string,
        options?: IRestOptions
    ): Promise<T> {
        options = await this.setHeaderInfo(options);
        return this.versionedResource.get(
            {
                itemUuid,
                versionId
            },
            options
        );
    }

    /**
     * @ngdoc method
     * @name cmsitemsRestService.service:cmsitemsRestService#get
     * @methodOf cmsitemsRestService.cmsitemsRestService
     *
     * @description
     * Fetch CMS Items search result by making a REST call to the CMS Items API.
     * A search can be performed by a typeCode (optionnaly in combination of a mask parameter), or by providing a list of cms items uuid.
     *
     * @param {Object} queryParams The object representing the query params
     * @param {String} queryParams.pageSize number of items in the page
     * @param {String} queryParams.currentPage current page number
     * @param {String =} queryParams.typeCode for filtering on the cms item typeCode
     * @param {String =} queryParams.mask for filtering the search
     * @param {String =} queryParams.itemSearchParams search on additional fields using a comma separated list of field name and value
     * pairs which are separated by a colon. Exact matches only.
     * @param {String =} queryParams.catalogId the catalog to search items in. If empty, the current context catalog will be used.
     * @param {String =} queryParams.catalogVersion the catalog version to search items in. If empty, the current context catalog version will be used.
     *
     * @returns {Promise<CMSItem>} If request is successful, it returns a promise that resolves with the paged search result. If the
     * request fails, it resolves with errors from the backend.
     */
    @Cached({ actions: [rarelyChangingContent], tags: [userEvictionTag, cmsitemsEvictionTag] })
    public async get<T extends CMSItem = CMSItem>(
        queryParams: CMSItemSearch,
        options?: IRestOptions
    ): Promise<IPage<T>> {
        options = await this.setHeaderInfo(options);
        return this.catalogService.retrieveUriContext().then((uriContext) => {
            const catalogDetailsParams = {
                catalogId: queryParams.catalogId || uriContext.CURRENT_CONTEXT_CATALOG,
                catalogVersion:
                    queryParams.catalogVersion || uriContext.CURRENT_CONTEXT_CATALOG_VERSION
            };

            queryParams = lodash.merge(catalogDetailsParams, queryParams);

            return this.restServiceFactory.get<IPage<T>>(cmsitemsUri).get(queryParams, options);
        });
    }

    /**
     * @ngdoc method
     * @name cmsitemsRestService.service:cmsitemsRestService#getByIds
     * @methodOf cmsitemsRestService.cmsitemsRestService
     *
     * @description
     * Fetch CMS Items by uuids, making a POST call to the CMS Items API.
     * A search can be performed by providing a list of cms items uuid.
     *
     * @param {string[] =} uuids list of cms item uuids
     *
     * @returns {Promise<CMSItem[]>} If request is successful, it returns a promise that resolves to the result. If the
     * request fails, it resolves with errors from the backend. Be mindful that the response payload size could
     * increase dramatically depending on the number of uuids that you send on the request.
     */
    @Cached({ actions: [rarelyChangingContent], tags: [userEvictionTag, cmsitemsEvictionTag] })
    public getByIds<T extends CMSItem = CMSItem>(
        uuids: string[],
        fields?: string
    ): Promise<IPage<T>> {
        return this.getByIdsNoCache(uuids, fields);
    }

    /**
     * @ngdoc method
     * @name cmsitemsRestService.service:cmsitemsRestService#update
     * @methodOf cmsitemsRestService.cmsitemsRestService
     *
     * @description
     * Update a CMS Item.
     *
     * @param {Object} cmsitem The object representing the CMS Item to update
     * @param {String} cmsitem.identifier The cms item identifier (uuid)
     *
     * @returns {Promise<CMSItem>} If request is successful, it returns a promise that resolves with the updated CMS Item object. If the
     * request fails, it resolves with errors from the backend.
     */
    @InvalidateCache(cmsitemsEvictionTag)
    public async update<T extends CMSItem = CMSItem>(
        cmsitem: T,
        options?: IRestOptions
    ): Promise<T> {
        options = await this.setHeaderInfo(options);
        return this.resource.update(cmsitem, options);
    }

    /**
     * @ngdoc method
     * @name cmsitemsRestService.service:cmsitemsRestService#delete
     * @methodOf cmsitemsRestService.cmsitemsRestService
     *
     * @description
     * Remove a CMS Item.
     *
     * @param {Number} cmsitemUuid The CMS Item uuid
     */
    @InvalidateCache(cmsitemsEvictionTag)
    public async delete(identifier: string, options?: IRestOptions): Promise<void> {
        options = await this.setHeaderInfo(options);
        return this.resource.remove(
            {
                identifier
            },
            options
        );
    }

    /**
     * @ngdoc method
     * @name cmsitemsRestService.service:cmsitemsRestService#create
     * @methodOf cmsitemsRestService.cmsitemsRestService
     *
     * @description
     * Create a new CMS Item.
     *
     * @param {Object} cmsitem The object representing the CMS Item to create
     *
     * @returns {Promise<CMSItem>} If request is successful, it returns a promise that resolves with the CMS Item object. If the
     * request fails, it resolves with errors from the backend.
     */
    public async create<T extends CMSItem = CMSItem>(
        cmsitem: T,
        options?: IRestOptions
    ): Promise<T> {
        options = await this.setHeaderInfo(options);
        return this.catalogService.getCatalogVersionUUid().then((catalogVersionUUid) => {
            cmsitem.catalogVersion = cmsitem.catalogVersion || catalogVersionUUid;
            if (cmsitem.onlyOneRestrictionMustApply === undefined) {
                cmsitem.onlyOneRestrictionMustApply = false;
            }
            return this.resource.save(cmsitem, options);
        });
    }

    /**
     * The function is same as getByIds but it doesn't use caching, it will request data from backend every time.
     *
     * If request is successful, it returns a promise that resolves to the result. If the
     * request fails, it resolves with errors from the backend. Be mindful that the response payload size could
     * increase dramatically depending on the number of uuids that you send on the request.
     */
    public async getByIdsNoCache<T extends CMSItem = CMSItem>(
        uuids: string[],
        fields?: string,
        options?: IRestOptions
    ): Promise<IPage<T>> {
        uuids = Array.from(new Set(uuids)); // removing duplicates
        options = await this.setHeaderInfo(options);
        return this.catalogService.getCatalogVersionUUid().then((catalogVersion) => {
            const payload: any = {
                catalogVersion,
                uuids,
                fields
            };
            return this.uuidsResource.save(payload, options);
        });
    }

    /**
     * @ngdoc method
     * @name cmsitemsRestService.service:cmsitemsRestService#getById
     * @methodOf cmsitemsRestService.cmsitemsRestService
     *
     * @description
     * Get the CMS Item that matches the given item uuid (Universally Unique Identifier).
     *
     * @param {Number} cmsitemUuid The CMS Item uuid
     *
     * @returns {Promise<CMSItem>} If request is successful, it returns a promise that resolves with the CMS Item object. If the
     * request fails, it resolves with errors from the backend.
     */
    public async getById<T extends CMSItem = CMSItem>(
        cmsitemUuid: string,
        options?: IRestOptions
    ): Promise<T> {
        options = await this.setHeaderInfo(options);
        return this.resource.getById<T>(cmsitemUuid, options);
    }

    private async setDefaultHeader(): Promise<IRestOptions> {
        const localeIsoCode = await this.languageService.getResolveLocaleIsoCode();
        return {
            headers: { 'Accept-Language': localeIsoCode }
        };
    }

    private async setHeaderInfo(options?: IRestOptions): Promise<IRestOptions> {
        const defaultHeader = await this.setDefaultHeader();
        if (options == null) {
            options = defaultHeader;
        } else {
            options = {
                headers: {
                    ...options.headers,
                    ...defaultHeader.headers
                }
            };
        }
        return options;
    }
}
