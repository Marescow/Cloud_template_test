/*
 * Copyright (c) 2022 SAP SE or an SAP affiliate company. All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package de.hybris.platform.sap.productconfig.rules.rule.evaluation.impl;

import static de.hybris.platform.ruleengine.constants.RuleEngineConstants.RULEMETADATA_RULECODE;
import static org.mockito.Matchers.notNull;
import static org.mockito.Mockito.when;

import de.hybris.platform.ruleengineservices.calculation.AbstractRuleEngineTest;
import de.hybris.platform.ruleengineservices.rao.RuleEngineResultRAO;
import de.hybris.platform.ruleengineservices.rule.evaluation.RuleActionContext;
import de.hybris.platform.ruleengineservices.rule.evaluation.actions.RAOLookupService;
import de.hybris.platform.servicelayer.config.ConfigurationService;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Optional;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class AbstractProductConfigRAOActionTCBase extends AbstractRuleEngineTest
{
	private RuleEngineResultRAO result;

	@Mock
	private ConfigurationService configurationService;
	@Mock
	private RuleActionContext context;
	
	@Mock
	private Map<String, Object> metaData;
	
	@Mock
	private RAOLookupService raoLookupService;

	@Before
	public void abstractSetUp()
	{
		result = new RuleEngineResultRAO();
		result.setActions(new LinkedHashSet<>());

		when(context.getRuleMetadata()).thenReturn(metaData);
		when(context.getRuleEngineResultRao()).thenReturn(result);
		when(metaData.get(RULEMETADATA_RULECODE)).thenReturn("notNullValue");
	}

	protected RuleEngineResultRAO getResult()
	{
		return result;
	}

	protected RuleActionContext getContext()
	{
		return context;
	}

	protected ConfigurationService getConfigurationService()
	{
		return configurationService;
	}

	protected RAOLookupService getRaoLookupService()
	{
		return raoLookupService;
	}
}

