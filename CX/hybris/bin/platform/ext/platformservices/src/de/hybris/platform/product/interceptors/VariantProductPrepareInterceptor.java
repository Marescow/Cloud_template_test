/*
 * Copyright (c) 2023 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.product.interceptors;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.PrepareInterceptor;
import de.hybris.platform.servicelayer.model.ItemModelContext;
import de.hybris.platform.variants.model.VariantProductModel;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;


public class VariantProductPrepareInterceptor implements PrepareInterceptor
{
	@Override
	public void onPrepare(final Object model, final InterceptorContext ctx) throws InterceptorException
	{
		if (!(model instanceof VariantProductModel variantProductModel))
		{
			return;
		}

		if (ctx.isNew(variantProductModel))
		{
			onNewVariant(variantProductModel, ctx);
		}
		else
		{
			onChangedVariant(variantProductModel, ctx);
		}
	}

	private void onNewVariant(final VariantProductModel variantProductModel, final InterceptorContext ctx)
	{
		final ProductModel currentBaseProduct = variantProductModel.getBaseProduct();
		updateProductModificationTime(currentBaseProduct, ctx);
	}

	private void onChangedVariant(final VariantProductModel variantProductModel, final InterceptorContext ctx)
	{
		if (!ctx.isModified(variantProductModel, VariantProductModel.BASEPRODUCT))
		{
			return;
		}

		final ProductModel currentBaseProduct = variantProductModel.getBaseProduct();
		final ProductModel previousBaseProduct = getPreviousBaseProduct(variantProductModel);

		if (areTheSameByPk(currentBaseProduct, previousBaseProduct))
		{
			return;
		}

		updateProductModificationTime(currentBaseProduct, ctx);
		updateProductModificationTime(previousBaseProduct, ctx);
	}

	private boolean areTheSameByPk(final ProductModel currentProduct, final ProductModel previousProduct)
	{
		if (currentProduct == null && previousProduct == null)
		{
			return true;
		}
		else if (currentProduct == null || previousProduct == null)
		{
			return false;
		}
		return Objects.equals(currentProduct.getPk(), previousProduct.getPk());
	}

	private ProductModel getPreviousBaseProduct(final VariantProductModel variantProductModel)
	{
		final ItemModelContext variantProductContext = variantProductModel.getItemModelContext();
		return variantProductContext.getOriginalValue(VariantProductModel.BASEPRODUCT);
	}

	private void updateProductModificationTime(final ProductModel product, final InterceptorContext ctx)
	{
		if (product != null && !ctx.isRemoved(product))
		{
			product.setModifiedtime(getCurrentDateTime());
			ctx.registerElement(product);
		}
	}

	private Date getCurrentDateTime()
	{
		return Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
	}
}
