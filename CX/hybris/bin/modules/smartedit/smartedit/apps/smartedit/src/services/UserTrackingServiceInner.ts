/*
 * Copyright (c) 2022 SAP SE or an SAP affiliate company. All rights reserved.
 */
import {GatewayProxied, IUserTrackingService, SeDowngradeService} from 'smarteditcommons';

/** @internal */
@SeDowngradeService(IUserTrackingService)
@GatewayProxied('initConfiguration', 'trackingUserAction')
export class UserTrackingService extends IUserTrackingService {}