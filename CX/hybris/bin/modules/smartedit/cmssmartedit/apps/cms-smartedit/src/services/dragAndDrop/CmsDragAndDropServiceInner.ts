/*
 * Copyright (c) 2022 SAP SE or an SAP affiliate company. All rights reserved.
 */
import { Inject } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import {
    AssetsService,
    COMPONENT_REMOVED_EVENT,
    DRAG_AND_DROP_EVENTS,
    ICMSComponent,
    IPageContentSlotsComponentsRestService
} from 'cmscommons';
import {
    CONTENT_SLOT_TYPE,
    CONTRACT_CHANGE_LISTENER_COMPONENT_PROCESS_STATUS,
    CONTRACT_CHANGE_LISTENER_PROCESS_EVENTS,
    DragAndDropService,
    GatewayFactory,
    IAlertService,
    IBrowserService,
    IWaitDialogService,
    MessageGateway,
    OVERLAY_RERENDERED_EVENT,
    Payload,
    SeDowngradeService,
    SMARTEDIT_COMPONENT_PROCESS_STATUS,
    SystemEventService,
    TypedMap,
    windowUtils,
    YJQUERY_TOKEN,
    DOMAIN_TOKEN,
    ISlotRestrictionsService,
    COMPONENT_IN_SLOT_STATUS,
    ComponentHandlerService
} from 'smarteditcommons';
import { ComponentEditingFacade } from './ComponentEditingFacade';

export interface CmsDragAndDropCachedComponentHint {
    original: JQuery<HTMLElement>;
    position: number;
    rect: TypedMap<number>;
}

export interface CmsDragAndDropCachedComponent {
    id: string;
    type: string;
    original: JQuery<HTMLElement>;
    position: number;
    rect: TypedMap<number>;
    hints: CmsDragAndDropCachedComponentHint[];
}

export interface CmsDragAndDropCachedSlot {
    id: string;
    uuid: string;
    original: JQuery<HTMLElement>;
    /**
     * The list of components contained in the slot, they must contain an "id" property
     */
    components: CmsDragAndDropCachedComponent[];
    hint: {
        original: JQuery<HTMLElement>;
        rect: TypedMap<number>;
    };
    rect: TypedMap<number>;
    isAllowed?: boolean;
    mayBeAllowed?: boolean;
}

export interface CmsDragAndDropDragInfo extends Payload {
    /**
     * The smartedit id of the component.
     */
    componentId: string;
    componentUuid: string;
    componentType: string;
    slotUuid: string;
    /**
     * The smartedit id of the slot from which the component originates.
     */
    slotId: string;
    slotOperationRelatedId: string;
    slotOperationRelatedType: string;
    /**
     * The boolean that determines if the component should be cloned or not.
     */
    cloneOnDrop?: boolean;
}

/**
 * This service provides a rich drag and drop experience tailored for CMS operations.
 */
@SeDowngradeService()
export class CmsDragAndDropService {
    private static readonly CMS_DRAG_AND_DROP_ID = 'se.cms.dragAndDrop';

    private static readonly TARGET_SELECTOR =
        "#smarteditoverlay .smartEditComponentX[data-smartedit-component-type='ContentSlot']";
    private static readonly SOURCE_SELECTOR =
        "#smarteditoverlay .smartEditComponentX[data-smartedit-component-type!='ContentSlot'] .movebutton";
    private static readonly MORE_MENU_SOURCE_SELECTOR = '.movebutton';

    private static readonly SLOT_SELECTOR =
        ".smartEditComponentX[data-smartedit-component-type='ContentSlot']";
    private static readonly COMPONENT_SELECTOR =
        ".smartEditComponentX[data-smartedit-component-type!='ContentSlot']";
    private static readonly HINT_SELECTOR = '.overlayDropzone';

    private static readonly CSS_CLASSES = {
        UI_HELPER_OVERLAY: 'overlayDnd',
        DROPZONE: 'overlayDropzone',
        DROPZONE_FULL: 'overlayDropzone--full',
        DROPZONE_TOP: 'overlayDropzone--top',
        DROPZONE_BOTTOM: 'overlayDropzone--bottom',
        DROPZONE_LEFT: 'overlayDropzone--left',
        DROPZONE_RIGHT: 'overlayDropzone--right',
        DROPZONE_HOVERED: 'overlayDropzone--hovered',
        DROPZONE_MAY_BE_ALLOWED: 'overlayDropzone--mayBeAllowed',
        OVERLAY_IN_DRAG_DROP: 'smarteditoverlay_dndRendering',
        COMPONENT_DRAGGED: 'component_dragged',
        COMPONENT_DRAGGED_HOVERED: 'component_dragged_hovered',
        SLOTS_MARKED: 'slot-marked',
        SLOT_ALLOWED: 'over-slot-enabled',
        SLOT_NOT_ALLOWED: 'over-slot-disabled',
        SLOT_MAY_BE_ALLOWED: 'over-slot-maybeenabled'
    };

    private static readonly DEFAULT_DRAG_IMG = '/images/contextualmenu_move_on.png';

    private cachedSlots: TypedMap<CmsDragAndDropCachedSlot> = {};
    private highlightedSlot: CmsDragAndDropCachedSlot = null;
    private highlightedComponent: CmsDragAndDropCachedComponent = null;
    private highlightedHint: CmsDragAndDropCachedComponentHint = null;
    private dragInfo: CmsDragAndDropDragInfo = null;
    private overlayRenderedUnSubscribeFn: () => void = null;
    private componentRemovedUnSubscribeFn: () => void = null;
    private gateway: MessageGateway;
    private _window: Window;

    constructor(
        private alertService: IAlertService,
        private assetsService: AssetsService,
        private browserService: IBrowserService,
        private componentEditingFacade: ComponentEditingFacade,
        private componentHandlerService: ComponentHandlerService,
        private dragAndDropService: DragAndDropService,
        private gatewayFactory: GatewayFactory,
        private translateService: TranslateService,
        private pageContentSlotsComponentsRestService: IPageContentSlotsComponentsRestService,
        private slotRestrictionsService: ISlotRestrictionsService,
        private systemEventService: SystemEventService,
        private waitDialogService: IWaitDialogService,
        @Inject(YJQUERY_TOKEN) private yjQuery: JQueryStatic,
        @Inject(DOMAIN_TOKEN) private domain: string
    ) {
        this._window = windowUtils.getWindow();
        this.gateway = this.gatewayFactory.createGateway('cmsDragAndDrop');
    }

    /**
     * This method registers this drag and drop instance in SmartEdit.
     */
    public register(): void {
        this.dragAndDropService.register({
            id: CmsDragAndDropService.CMS_DRAG_AND_DROP_ID,
            sourceSelector: [
                CmsDragAndDropService.SOURCE_SELECTOR,
                CmsDragAndDropService.MORE_MENU_SOURCE_SELECTOR
            ], // the source selectors are DnD menus located both inside and outside the more options of the overlay
            targetSelector: CmsDragAndDropService.TARGET_SELECTOR,
            startCallback: (event: DragEvent) => this.onStart(event),
            dragEnterCallback: (event: DragEvent) => this.onDragEnter(event),
            dragOverCallback: (event: DragEvent) => this.onDragOver(event),
            dropCallback: (event: DragEvent) => this.onDrop(event),
            outCallback: (event: DragEvent) => this.onDragLeave(event),
            stopCallback: (event: DragEvent) => this.onStop(event),
            enableScrolling: true
        });
    }

    /**
     * This method unregisters this drag and drop instance from SmartEdit.
     */
    public unregister(): void {
        this.dragAndDropService.unregister([CmsDragAndDropService.CMS_DRAG_AND_DROP_ID]);

        if (this.overlayRenderedUnSubscribeFn) {
            this.overlayRenderedUnSubscribeFn();
        }
        if (this.componentRemovedUnSubscribeFn) {
            this.componentRemovedUnSubscribeFn();
        }
    }

    /**
     * This method applies this drag and drop instance in the current page. After this method is executed,
     * the user can start a drag and drop operation.
     */
    public apply(): void {
        this.dragAndDropService.apply(CmsDragAndDropService.CMS_DRAG_AND_DROP_ID);
        this.addUIHelpers();

        // Register a listener for every time the overlay is updated.
        this.overlayRenderedUnSubscribeFn = this.systemEventService.subscribe(
            OVERLAY_RERENDERED_EVENT,
            () => this.onOverlayUpdate()
        );
        this.componentRemovedUnSubscribeFn = this.systemEventService.subscribe(
            COMPONENT_REMOVED_EVENT,
            () => this.onOverlayUpdate()
        );

        this.gateway.subscribe<CmsDragAndDropDragInfo>(
            DRAG_AND_DROP_EVENTS.DRAG_STARTED,
            (eventId: string, data: CmsDragAndDropDragInfo) => {
                this.dragAndDropService.markDragStarted();
                this.initializeDragOperation(data);
            }
        );

        this.gateway.subscribe(DRAG_AND_DROP_EVENTS.DRAG_STOPPED, () => {
            this.dragAndDropService.markDragStopped();
            this.cleanDragOperation();
        });
    }

    /**
     * This method updates this drag and drop instance in the current page. It is important to execute
     * this method every time a draggable or droppable element is added or removed from the page DOM.
     */
    public update(): void {
        this.dragAndDropService.update(CmsDragAndDropService.CMS_DRAG_AND_DROP_ID);

        // Add UI helpers -> They identify the places where you can drop components.
        this.addUIHelpers();

        // Update cache elements AFTER adding UI Helpers
        this.cacheElements();
    }

    // Other Event Handlers
    private onOverlayUpdate(): Promise<void> {
        this.update();
        return Promise.resolve();
    }

    // Drag and Drop Event Handlers
    private onStart(event: DragEvent): void {
        // Find element
        let targetElm = this.getSelector(event.target);
        // when the DnD icon is in the more option dropdown, the targetElm is a span and has no data-component-id. Here we get the closest element (i.e. <contextual-menu-item>)
        if (!targetElm.attr('data-component-id')) {
            targetElm = this.yjQuery(targetElm).closest('[data-component-id]');
        }
        const component = targetElm.closest(CmsDragAndDropService.COMPONENT_SELECTOR) as JQuery<
            HTMLElement
        >;
        const slot = component.closest(CmsDragAndDropService.SLOT_SELECTOR);

        // Here if the component evaluated above exits that means the component has been located and we can fetch its attributes
        // else it is not located as the DnD option is hidden inside the more option of the contextual menu in which case
        // we find the component/slot info by accessing attributes of the DnD icon.
        const componentId =
            component.length > 0
                ? this.componentHandlerService.getId(component)
                : targetElm.attr('data-component-id');
        const componentUuid =
            component.length > 0
                ? this.componentHandlerService.getSlotOperationRelatedUuid(component)
                : targetElm.attr('data-component-uuid');
        const componentType =
            component.length > 0
                ? this.componentHandlerService.getType(component)
                : targetElm.attr('data-component-type');
        const slotOperationRelatedId =
            component.length > 0
                ? this.componentHandlerService.getSlotOperationRelatedId(component)
                : targetElm.attr('data-component-id');
        const slotOperationRelatedType =
            component.length > 0
                ? this.componentHandlerService.getSlotOperationRelatedType(component)
                : targetElm.attr('data-component-type');

        const slotId =
            component.length > 0
                ? this.componentHandlerService.getId(slot)
                : targetElm.attr('data-slot-id');
        const slotUuid =
            component.length > 0
                ? this.componentHandlerService.getId(slot)
                : targetElm.attr('data-slot-uuid');

        const dragInfo = {
            componentId,
            componentUuid,
            componentType,
            slotUuid,
            slotId,
            slotOperationRelatedId,
            slotOperationRelatedType
        };
        component.addClass(CmsDragAndDropService.CSS_CLASSES.COMPONENT_DRAGGED);
        this.initializeDragOperation(dragInfo);
        this.toggleKeepVisibleComponentAndSlot(true);
    }

    private onDragEnter(event: DragEvent): Promise<void> {
        return this.highlightSlot(event);
    }

    private async onDragOver(event: DragEvent): Promise<void> {
        await this.highlightSlot(event);
        if (!this.highlightedSlot || !this.highlightedSlot.isAllowed) {
            return;
        }

        const slotId = this.componentHandlerService.getId(this.highlightedSlot.original);

        // Check which component is highlighted
        if (this.highlightedHint && this.isMouseInRegion(event, this.highlightedHint)) {
            // If right hint is already highlighted don't do anything.
            return;
        } else if (this.highlightedHint) {
            // Hint is not longer hovered.
            this.clearHighlightedHint();
        }

        const cachedSlot = this.cachedSlots[slotId];
        if (cachedSlot.components.length > 0) {
            // Find the hovered component.
            if (
                !this.highlightedComponent ||
                !this.isMouseInRegion(event, this.highlightedComponent)
            ) {
                this.clearHighlightedComponent();
                // Find the component, if any, to higlight.
                const componentToHighlight = this.selectMouseOverElement(
                    event,
                    cachedSlot.components
                );
                if (componentToHighlight) {
                    this.highlightedComponent = componentToHighlight;
                }
            }

            // Find the hint, if any, to highlight.
            if (this.highlightedComponent?.hints) {
                const hintToHighlight = this.selectMouseOverElement(
                    event,
                    this.highlightedComponent.hints
                );
                if (hintToHighlight) {
                    this.highlightedHint = hintToHighlight;
                }
            }
        }

        if (
            this.highlightedComponent &&
            this.highlightedComponent.id === this.dragInfo.slotOperationRelatedId
        ) {
            this.highlightedComponent.original.addClass(
                CmsDragAndDropService.CSS_CLASSES.COMPONENT_DRAGGED_HOVERED
            );
        } else if (this.highlightedHint) {
            if (this.highlightedSlot.isAllowed) {
                this.highlightedHint.original.addClass(
                    CmsDragAndDropService.CSS_CLASSES.DROPZONE_HOVERED
                );
                if (this.highlightedSlot.mayBeAllowed) {
                    this.highlightedHint.original.addClass(
                        CmsDragAndDropService.CSS_CLASSES.DROPZONE_MAY_BE_ALLOWED
                    );
                }
            }
        }
    }

    private selectMouseOverElement(
        event: DragEvent,
        elements: CmsDragAndDropCachedComponent[]
    ): CmsDragAndDropCachedComponent;
    private selectMouseOverElement(
        event: DragEvent,
        elements: CmsDragAndDropCachedComponentHint[]
    ): CmsDragAndDropCachedComponentHint;
    private selectMouseOverElement(
        event: DragEvent,
        elements: CmsDragAndDropCachedComponent[] | CmsDragAndDropCachedComponentHint[]
    ): CmsDragAndDropCachedComponent | CmsDragAndDropCachedComponentHint {
        return elements.find((element) => this.isMouseInRegion(event, element));
    }

    private async onDrop(event: DragEvent): Promise<void> {
        if (this.highlightedSlot) {
            const sourceSlotId = this.dragInfo.slotId;
            const targetSlotId = this.componentHandlerService.getId(this.highlightedSlot.original);
            const targetSlotUUId = this.componentHandlerService.getUuid(
                this.highlightedSlot.original
            );
            const sourceComponentId = this.dragInfo.componentId;
            // if component is dragged from component-menu, there is no slotOperationRelated(Id/Type) available.
            const sourceSlotOperationRelatedId =
                this.dragInfo.slotOperationRelatedId || this.dragInfo.componentId;
            const componentType =
                this.dragInfo.slotOperationRelatedType || this.dragInfo.componentType;

            if (!this.highlightedSlot.isAllowed) {
                const translation = this.translateService.instant(
                    'se.drag.and.drop.not.valid.component.type',
                    {
                        slotUID: targetSlotId,
                        componentUID: sourceSlotOperationRelatedId
                    }
                );
                this.alertService.showDanger({
                    message: translation
                });
                return;
            }
            if (this.highlightedHint || this.highlightedSlot.components.length === 0) {
                let position = this.highlightedHint ? this.highlightedHint.position : 0;
                let performAction: Promise<void>;

                this.waitDialogService.showWaitModal();

                if (!sourceSlotId) {
                    if (!sourceComponentId) {
                        const slotInfo = {
                            targetSlotId,
                            targetSlotUUId
                        };
                        const catalogVersionUuid = this.componentHandlerService.getCatalogVersionUuid(
                            this.highlightedSlot.original
                        );
                        performAction = this.componentEditingFacade.addNewComponentToSlot(
                            slotInfo,
                            catalogVersionUuid,
                            componentType,
                            position
                        );
                    } else {
                        const dragInfo = {
                            componentId: sourceComponentId,
                            componentUuid: this.dragInfo.componentUuid,
                            componentType
                        };
                        const componentProperties = {
                            targetSlotId,
                            dragInfo,
                            position
                        };

                        performAction = this.dragInfo.cloneOnDrop
                            ? this.componentEditingFacade.cloneExistingComponentToSlot(
                                  componentProperties
                              )
                            : this.componentEditingFacade.addExistingComponentToSlot(
                                  targetSlotId,
                                  dragInfo,
                                  position
                              );
                    }
                } else {
                    if (sourceSlotId === targetSlotId) {
                        const currentComponentPos = this.getComponentPositionFromCachedSlot(
                            sourceSlotId,
                            sourceComponentId
                        );
                        if (currentComponentPos < position) {
                            // The current component will be removed from its current position, thus the target
                            // position needs to take this into account.
                            position--;
                        } else if (currentComponentPos === position) {
                            // Do not perform update if position and slot has not changed.

                            this.waitDialogService.hideWaitModal();
                            return;
                        }
                    }
                    performAction = this.componentEditingFacade.moveComponent(
                        sourceSlotId,
                        targetSlotId,
                        sourceSlotOperationRelatedId,
                        position
                    );
                }

                try {
                    await performAction;
                    this.scrollToModifiedSlot(targetSlotId);
                } catch {
                    this.onStop(event);
                } finally {
                    this.waitDialogService.hideWaitModal();
                }
            }
        }
    }

    private getComponentPositionFromCachedSlot(slotId: string, componentId: string): number {
        const cachedSlot = this.cachedSlots[slotId];
        const componentsInCachedSlot = cachedSlot?.components ? cachedSlot.components : [];
        const cachedComponent = componentsInCachedSlot.find(
            (component) => this.componentHandlerService.getId(component.original) === componentId
        );
        const currentComponentPos = cachedComponent
            ? cachedComponent.position
            : this.componentHandlerService.getComponentPositionInSlot(slotId, componentId);
        return currentComponentPos;
    }

    private onDragLeave(event: DragEvent): void {
        if (this.highlightedSlot) {
            const slotId = this.componentHandlerService.getId(this.highlightedSlot.original);
            const cachedSlot = this.cachedSlots[slotId];

            if (!this.isMouseInRegion(event, cachedSlot)) {
                this.clearHighlightedSlot();
            }
        }
    }

    private onStop(event: DragEvent): void {
        const component = this.getSelector(event.target).closest(
            CmsDragAndDropService.COMPONENT_SELECTOR
        ) as JQuery<HTMLElement>;
        this.toggleKeepVisibleComponentAndSlot(false);
        this.cleanDragOperation(component);
        this.systemEventService.publish(CONTRACT_CHANGE_LISTENER_PROCESS_EVENTS.RESTART_PROCESS);
    }

    private initializeDragOperation(dragInfo: CmsDragAndDropDragInfo): void {
        this.dragInfo = dragInfo;
        this.cacheElements();

        // Prepare UI
        const overlay = this.componentHandlerService.getOverlay();
        overlay.addClass(CmsDragAndDropService.CSS_CLASSES.OVERLAY_IN_DRAG_DROP);

        // Send an event to signal that the drag operation is started. Other pieces of SE, like contextual menus
        // need to be aware.
        this.systemEventService.publishAsync(DRAG_AND_DROP_EVENTS.DRAG_STARTED);
    }

    private cleanDragOperation(draggedComponent?: JQuery<HTMLElement>): void {
        this.clearHighlightedSlot();
        if (draggedComponent) {
            draggedComponent.removeClass(CmsDragAndDropService.CSS_CLASSES.COMPONENT_DRAGGED);
        }

        const overlay = this.componentHandlerService.getOverlay();
        overlay.removeClass(CmsDragAndDropService.CSS_CLASSES.OVERLAY_IN_DRAG_DROP);
        this.systemEventService.publishAsync(DRAG_AND_DROP_EVENTS.DRAG_STOPPED);

        this.dragInfo = null;
        this.cachedSlots = {};
        this.highlightedSlot = null;
    }

    private async highlightSlot(event: DragEvent): Promise<void> {
        const slot = this.yjQuery(event.target).closest(
            CmsDragAndDropService.SLOT_SELECTOR
        ) as JQuery<HTMLElement>;
        const slotId = this.componentHandlerService.getId(slot);

        let oldSlotId: string;
        if (this.highlightedSlot) {
            oldSlotId = this.componentHandlerService.getId(this.highlightedSlot.original);

            if (oldSlotId !== slotId) {
                this.clearHighlightedSlot();
            }
        }

        if (!this.highlightedSlot || this.highlightedSlot.isAllowed === undefined) {
            this.highlightedSlot = this.cachedSlots[slotId];

            const dragInfo = { ...this.dragInfo };
            // if component is dragged from component-menu, there is no slotOperationRelated(Id/Type) available.
            dragInfo.componentId =
                this.dragInfo.slotOperationRelatedId || this.dragInfo.componentId;
            dragInfo.componentType =
                this.dragInfo.slotOperationRelatedType || this.dragInfo.componentType;
            if (dragInfo.cloneOnDrop) {
                delete dragInfo.componentId;
            }

            const isComponentAllowed = await this.slotRestrictionsService.isComponentAllowedInSlot(
                this.highlightedSlot,
                dragInfo
            );
            const isSlotEditable = await this.slotRestrictionsService.isSlotEditable(slotId);
            // The highlighted slot might have changed while waiting for the promise to be resolved.
            if (this.highlightedSlot && this.highlightedSlot.id === slotId) {
                const isAllowed =
                    isComponentAllowed === COMPONENT_IN_SLOT_STATUS.ALLOWED && isSlotEditable;
                const mayBeAllowed =
                    isComponentAllowed === COMPONENT_IN_SLOT_STATUS.MAYBEALLOWED && isSlotEditable;

                /* Basically the component could be allowed to drop in the slot if the isComponentAllowed status is either ALLOWED or MAYBEALLOWED.
                 * But in order to differentiate between ALLOWED and MAYBEALLOWED, we store it in highlightedSlot.isAllowed and highlightedSlot.mayBeAllowed respectively.
                 */
                this.highlightedSlot.isAllowed = isAllowed || mayBeAllowed;
                this.highlightedSlot.mayBeAllowed = mayBeAllowed;

                if (this.highlightedSlot.isAllowed) {
                    slot.addClass(CmsDragAndDropService.CSS_CLASSES.SLOT_ALLOWED);
                    if (mayBeAllowed) {
                        slot.addClass(CmsDragAndDropService.CSS_CLASSES.SLOT_MAY_BE_ALLOWED);
                    }
                } else {
                    slot.addClass(CmsDragAndDropService.CSS_CLASSES.SLOT_NOT_ALLOWED);
                }

                if (event.type === 'dragenter' && (!oldSlotId || oldSlotId !== slotId)) {
                    if (this.highlightedSlot && this.highlightedSlot.id === slotId) {
                        this.systemEventService.publish(slotId + '_SHOW_SLOT_MENU');
                        this.systemEventService.publish(DRAG_AND_DROP_EVENTS.DRAG_OVER, slotId); // can be used to perform any actions on encountering a drag over event.
                    }
                }
            }
        }
    }

    private addUIHelpers(): void {
        const overlay = this.componentHandlerService.getOverlay();

        // First remove all dropzones.
        overlay.find('.' + CmsDragAndDropService.CSS_CLASSES.UI_HELPER_OVERLAY).remove();

        const vm = this;
        overlay.find(CmsDragAndDropService.SLOT_SELECTOR).each((i, overlayElement) => {
            const slot = vm.yjQuery(overlayElement);
            const slotHeight = slot[0].offsetHeight;
            const slotWidth = slot[0].offsetWidth;

            const components = slot.find(CmsDragAndDropService.COMPONENT_SELECTOR);

            if (components.length === 0) {
                const uiHelperOverlay = vm.yjQuery('<div></div>');
                uiHelperOverlay.addClass(CmsDragAndDropService.CSS_CLASSES.UI_HELPER_OVERLAY);

                const uiHelper = vm.yjQuery('<div></div>');
                uiHelper.addClass(CmsDragAndDropService.CSS_CLASSES.DROPZONE);
                uiHelper.addClass(CmsDragAndDropService.CSS_CLASSES.DROPZONE_FULL);

                uiHelperOverlay.height(slotHeight);
                uiHelperOverlay.width(slotWidth);

                uiHelperOverlay.append(uiHelper);
                slot.append(uiHelperOverlay);
            } else {
                components.each((j, componentElement) => {
                    const component = vm.yjQuery(componentElement);
                    const componentHeight = component[0].offsetHeight;
                    const componentWidth = component[0].offsetWidth;

                    const uiHelperOverlay = vm.yjQuery('<div></div>');
                    uiHelperOverlay.addClass(CmsDragAndDropService.CSS_CLASSES.UI_HELPER_OVERLAY);

                    uiHelperOverlay.height(componentHeight);
                    uiHelperOverlay.width(componentWidth);

                    const firstHelper = vm.yjQuery('<div></div>');
                    const secondHelper = vm.yjQuery('<div></div>');

                    firstHelper.addClass(CmsDragAndDropService.CSS_CLASSES.DROPZONE);
                    secondHelper.addClass(CmsDragAndDropService.CSS_CLASSES.DROPZONE);

                    if (componentWidth === slotWidth) {
                        firstHelper.addClass(CmsDragAndDropService.CSS_CLASSES.DROPZONE_TOP);
                        secondHelper.addClass(CmsDragAndDropService.CSS_CLASSES.DROPZONE_BOTTOM);
                    } else {
                        firstHelper.addClass(CmsDragAndDropService.CSS_CLASSES.DROPZONE_LEFT);
                        secondHelper.addClass(CmsDragAndDropService.CSS_CLASSES.DROPZONE_RIGHT);
                    }

                    uiHelperOverlay.append(firstHelper);
                    uiHelperOverlay.append(secondHelper);

                    component.append(uiHelperOverlay);
                });
            }
        });
    }

    private cacheElements(): void {
        const overlay = this.componentHandlerService.getOverlay();
        if (!overlay) {
            return;
        }
        const scrollY = this.getWindowScrolling();

        overlay.find(CmsDragAndDropService.SLOT_SELECTOR).each((si, slotElement) => {
            const slot = this.yjQuery(slotElement);
            const slotId = this.componentHandlerService.getId(slot);
            const slotUuid = this.componentHandlerService.getUuid(slot);

            // Fetch all components (visible or not) in each slot to get proper position values.
            // The componentHandlerService.getComponentPositionInSlot method is not used here, because it's only based on visible components in the DOM.
            this.pageContentSlotsComponentsRestService
                .getComponentsForSlot(slotId)
                .then((componentsForSlot: ICMSComponent[]) => {
                    const cachedSlot: CmsDragAndDropCachedSlot = {
                        id: slotId,
                        uuid: slotUuid,
                        original: slot,
                        components: [],
                        rect: this.getElementRects(slot, scrollY),
                        hint: null
                    };

                    const components = slot.children(CmsDragAndDropService.COMPONENT_SELECTOR);
                    if (components.length === 0) {
                        const hint = slot.find(CmsDragAndDropService.HINT_SELECTOR);
                        cachedSlot.hint =
                            hint.length > 0
                                ? {
                                      original: hint,
                                      rect: this.getElementRects(hint, scrollY)
                                  }
                                : null;
                    } else {
                        components.each((ci: number, componentElement) => {
                            const component = this.yjQuery(componentElement);
                            let positionInSlot = componentsForSlot.findIndex(
                                (componentInSlot: ICMSComponent) =>
                                    componentInSlot.uuid ===
                                    this.componentHandlerService.getUuid(component)
                            );
                            if (positionInSlot === -1) {
                                positionInSlot = ci;
                            }
                            const cachedComponent: CmsDragAndDropCachedComponent = {
                                id: this.componentHandlerService.getSlotOperationRelatedId(
                                    component
                                ),
                                type: this.componentHandlerService.getSlotOperationRelatedType(
                                    component
                                ),
                                original: component,
                                position: positionInSlot,
                                hints: [],
                                rect: this.getElementRects(component, scrollY)
                            };

                            let positionInComponent = positionInSlot++;
                            component
                                .find(CmsDragAndDropService.HINT_SELECTOR)
                                .each((hi, hintElement) => {
                                    const hint = this.yjQuery(hintElement);
                                    const cachedHint = {
                                        original: hint,
                                        position: positionInComponent++,
                                        rect: this.getElementRects(hint, scrollY)
                                    };

                                    cachedComponent.hints.push(cachedHint);
                                });

                            cachedSlot.components.push(cachedComponent);
                        });
                    }

                    this.cachedSlots[cachedSlot.id] = cachedSlot;
                });
        });
    }

    private clearHighlightedHint(): void {
        if (this.highlightedHint) {
            this.highlightedHint.original.removeClass(
                CmsDragAndDropService.CSS_CLASSES.DROPZONE_HOVERED
            );
            this.highlightedHint.original.removeClass(
                CmsDragAndDropService.CSS_CLASSES.DROPZONE_MAY_BE_ALLOWED
            );
            this.highlightedHint = null;
        }
    }

    private clearHighlightedComponent(): void {
        this.clearHighlightedHint();
        if (this.highlightedComponent) {
            this.highlightedComponent.original.removeClass(
                CmsDragAndDropService.CSS_CLASSES.COMPONENT_DRAGGED_HOVERED
            );
            this.highlightedComponent = null;
        }
    }

    private clearHighlightedSlot(): void {
        this.clearHighlightedComponent();

        if (this.highlightedSlot) {
            this.highlightedSlot.original.removeClass(
                CmsDragAndDropService.CSS_CLASSES.SLOT_ALLOWED
            );
            this.highlightedSlot.original.removeClass(
                CmsDragAndDropService.CSS_CLASSES.SLOT_NOT_ALLOWED
            );
            this.highlightedSlot.original.removeClass(
                CmsDragAndDropService.CSS_CLASSES.SLOT_MAY_BE_ALLOWED
            );

            this.systemEventService.publish('HIDE_SLOT_MENU');
            this.systemEventService.publish(DRAG_AND_DROP_EVENTS.DRAG_LEAVE); // can be used to perform any actions on encountering a drag leave event.
        }

        this.highlightedSlot = null;
    }

    private isMouseInRegion(
        event: DragEvent,
        element:
            | CmsDragAndDropCachedComponent
            | CmsDragAndDropCachedSlot
            | CmsDragAndDropCachedComponentHint
    ): boolean {
        const boundingRect = element.rect;

        return (
            event.pageX >= boundingRect.left &&
            event.pageX <= boundingRect.right &&
            event.pageY >= boundingRect.top &&
            event.pageY <= boundingRect.bottom
        );
    }

    private getElementRects(element: JQuery<HTMLElement>, scrollY: number): TypedMap<number> {
        const baseRect = element[0].getBoundingClientRect();
        return {
            left: baseRect.left,
            right: baseRect.right,
            bottom: baseRect.bottom + scrollY,
            top: baseRect.top + scrollY
        };
    }

    private getWindowScrolling(): number {
        return this._window.pageYOffset;
    }

    private scrollToModifiedSlot(componentId: string): void {
        const component = this.componentHandlerService.getComponentInOverlay(
            componentId,
            CONTENT_SLOT_TYPE
        );
        if (component && component.length > 0) {
            component[0].scrollIntoView();
        }
    }

    private getSelector(selector: HTMLElement | EventTarget): JQuery<HTMLElement | EventTarget> {
        return this.yjQuery(selector);
    }

    /**
     * When a PROCESS_COMPONENTS is occuring, it could remove the currently dragged component if this one is not in the viewport.
     * To avoid having the dragged component and it's slot removed we mark then as "KEEP_VISIBLE" when the drag and drop start.
     * On drag end, an event is sent to call a RESTART_PROCESS to add or remove the components according to their viewport visibility and the component and slot are marked as "PROCESS".
     * Using yjQuery.each() here because of MiniCart component (among other slots/compoents) that have multiple occurences in DOM.
     */
    private toggleKeepVisibleComponentAndSlot(keepVisible: boolean): void {
        if (this.dragInfo) {
            const status = keepVisible
                ? CONTRACT_CHANGE_LISTENER_COMPONENT_PROCESS_STATUS.KEEP_VISIBLE
                : CONTRACT_CHANGE_LISTENER_COMPONENT_PROCESS_STATUS.PROCESS;
            this.yjQuery.each(
                this.componentHandlerService.getComponentUnderSlot(
                    this.dragInfo.componentId,
                    this.dragInfo.componentType,
                    this.dragInfo.slotId
                ),
                (i: number, element: HTMLElement) => {
                    element.dataset[SMARTEDIT_COMPONENT_PROCESS_STATUS] = status;
                }
            );
            this.yjQuery.each(
                this.componentHandlerService.getComponent(this.dragInfo.slotId, CONTENT_SLOT_TYPE),
                (i: number, element: HTMLElement) => {
                    element.dataset[SMARTEDIT_COMPONENT_PROCESS_STATUS] = status;
                }
            );
        }
    }
}
