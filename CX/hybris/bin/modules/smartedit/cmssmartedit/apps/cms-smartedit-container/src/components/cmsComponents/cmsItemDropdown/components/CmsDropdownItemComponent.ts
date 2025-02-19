/*
 * Copyright (c) 2022 SAP SE or an SAP affiliate company. All rights reserved.
 */
import {
    Component,
    ViewEncapsulation,
    ChangeDetectionStrategy,
    Inject,
    OnDestroy
} from '@angular/core';
import {
    SeDowngradeComponent,
    SystemEventService,
    ItemComponentData,
    ITEM_COMPONENT_DATA_TOKEN
} from 'smarteditcommons';
import { CMSLinkItem } from '../../../genericEditor/singleActiveCatalogAwareSelector';
import {
    ON_EDIT_NESTED_COMPONENT_EVENT,
    UPDATE_PUBLISHED_STATUS_TO_FALSE
} from '../CmsComponentConstants';

export interface CMSLinkDropdownItem extends CMSLinkItem {
    id: string;
}

@SeDowngradeComponent()
@Component({
    selector: 'se-cms-dropdown-item',
    templateUrl: './CmsDropdownItemComponent.html',
    styleUrls: ['./CmsDropdownItemComponent.scss'],
    changeDetection: ChangeDetectionStrategy.OnPush,
    encapsulation: ViewEncapsulation.None
})
export class CmsDropdownItemComponent implements OnDestroy {
    public item: CMSLinkDropdownItem;
    public isSelected: boolean;
    public qualifier: string;
    public isPublished = false;
    public updatePublishedStatusToFalseUnRegFn: () => void;

    constructor(
        @Inject(ITEM_COMPONENT_DATA_TOKEN) data: ItemComponentData<CMSLinkDropdownItem>,
        private systemEventService: SystemEventService
    ) {
        ({
            item: this.item,
            select: { id: this.qualifier },
            selected: this.isSelected
        } = data);
        this.updatePublishedStatusToFalseUnRegFn = this.systemEventService.subscribe(
            UPDATE_PUBLISHED_STATUS_TO_FALSE,
            () => {
                this.isPublished = false;
            }
        );
    }
    ngOnDestroy(): void {
        this.updatePublishedStatusToFalseUnRegFn();
    }

    public onClick(event: Event): void {
        event.stopPropagation();

        if (this.isSelected && !this.isPublished) {
            this.systemEventService.publishAsync(ON_EDIT_NESTED_COMPONENT_EVENT, {
                qualifier: this.qualifier,
                item: this.item
            });
            this.isPublished = true;
        }
    }
}
