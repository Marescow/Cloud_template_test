/*
 * Copyright (c) 2023 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.product.interceptors;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.RemoveInterceptor;
import de.hybris.platform.variants.model.VariantProductModel;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class VariantProductRemoveInterceptor implements RemoveInterceptor
{
	@Override
	public void onRemove(final Object model, final InterceptorContext ctx) throws InterceptorException
	{
		if (!(model instanceof VariantProductModel variantProductModel))
		{
			return;
		}

		onRemovedVariant(variantProductModel, ctx);
	}

	public void onRemovedVariant(final VariantProductModel variantProductModel, final InterceptorContext ctx)
	{
		final ProductModel currentBaseProduct = variantProductModel.getBaseProduct();
		updateProductModificationTime(currentBaseProduct, ctx);
	}

	private void updateProductModificationTime(final ProductModel product, final InterceptorContext ctx)
	{
		if (product != null && !ctx.isRemoved(product))
		{
			product.setModifiedtime(getCurrentDateTime());
			ctx.getModelService().save(product);
		}
	}

	private Date getCurrentDateTime()
	{
		return Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
	}
}
