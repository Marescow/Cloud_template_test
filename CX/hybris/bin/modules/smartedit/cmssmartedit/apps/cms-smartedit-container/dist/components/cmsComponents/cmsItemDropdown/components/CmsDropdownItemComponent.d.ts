import { OnDestroy } from '@angular/core';
import { SystemEventService, ItemComponentData } from 'smarteditcommons';
import { CMSLinkItem } from '../../../genericEditor/singleActiveCatalogAwareSelector';
export interface CMSLinkDropdownItem extends CMSLinkItem {
    id: string;
}
export declare class CmsDropdownItemComponent implements OnDestroy {
    private systemEventService;
    item: CMSLinkDropdownItem;
    isSelected: boolean;
    qualifier: string;
    isPublished: boolean;
    updatePublishedStatusToFalseUnRegFn: () => void;
    constructor(data: ItemComponentData<CMSLinkDropdownItem>, systemEventService: SystemEventService);
    ngOnDestroy(): void;
    onClick(event: Event): void;
}
