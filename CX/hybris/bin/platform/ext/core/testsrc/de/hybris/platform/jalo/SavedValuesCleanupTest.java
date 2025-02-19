/*
 * Copyright (c) 2024 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.jalo;

import static org.fest.assertions.Assertions.assertThat;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.constants.CoreConstants;
import de.hybris.platform.core.enums.SavedValueEntryType;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.hmc.jalo.SavedValues;
import de.hybris.platform.hmc.model.SavedValueEntryModel;
import de.hybris.platform.hmc.model.SavedValuesModel;
import de.hybris.platform.servicelayer.ServicelayerBaseTest;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.type.TypeService;
import de.hybris.platform.servicelayer.user.UserService;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;

@IntegrationTest
public class SavedValuesCleanupTest extends ServicelayerBaseTest
{
	@Resource
	ModelService modelService;
	@Resource
	TypeService typeService;
	@Resource
	UserService userService;
	@Resource
	FlexibleSearchService flexibleSearchService;

	private UserModel testUser;

	@Before
	public void setUp() throws Exception
	{
		testUser = modelService.create(UserModel.class);
		testUser.setUid("testUser");
		testUser.setDescription("oldValue");
		modelService.save(testUser);
	}

	@Test
	public void shouldCleanupSavedValuesWithEntriesViaJaloConnectionWhenThresholdReached()
	{
		//given
		final int threshold = SavedValues.getMaxSize();
		for (int i = 0; i < threshold; i++)
		{
			createSavedValues();
		}

		assertThat(countSavedValues()).isEqualTo(threshold);
		assertThat(countSavedValueEntries()).isEqualTo(threshold);

		final SavedValuesModel additionalSavedValues = createSavedValues();
		final Set<SavedValueEntryModel> additionalSavedValuesEntries = additionalSavedValues.getSavedValuesEntries();
		assertThat(additionalSavedValuesEntries).hasSize(1);

		assertThat(countSavedValueEntries()).isEqualTo(threshold + 1);
		assertThat(countSavedValues()).isEqualTo(threshold + 1);

		//when
		final Map<String, Object> originalValues = Map.of(UserModel.DESCRIPTION, "oldValue");
		final Map<String, Object> modifiedValues = Map.of(UserModel.DESCRIPTION, "newValue");
		JaloConnection.getInstance()
		              .logItemModification(testUser.getPk(), originalValues, modifiedValues, false);

		//then
		assertThat(countSavedValues()).isEqualTo(threshold);
		assertThat(countSavedValueEntries()).isEqualTo(threshold);
	}

	private SavedValuesModel createSavedValues()
	{
		final SavedValuesModel savedValues = modelService.create(SavedValuesModel.class);

		savedValues.setTimestamp(new Date());
		savedValues.setModifiedItem(testUser);
		savedValues.setModifiedItemDisplayString("User[PK123456789]");
		savedValues.setModifiedItemType(typeService.getComposedTypeForClass(UserModel.class));
		savedValues.setModificationType(SavedValueEntryType.CHANGED);
		savedValues.setUser(userService.getCurrentUser());

		final SavedValueEntryModel savedValueEntry = modelService.create(SavedValueEntryModel.class);
		savedValueEntry.setParent(savedValues);
		savedValueEntry.setModifiedAttribute(UserModel.DESCRIPTION);
		savedValueEntry.setOldValueAttributeDescriptor(
				typeService.getAttributeDescriptor(typeService.getComposedTypeForClass(UserModel.class), UserModel.DESCRIPTION));

		modelService.saveAll(savedValues, savedValueEntry);
		return savedValues;
	}

	private int countSavedValues()
	{
		final String query = "SELECT {" + Item.PK + "} FROM {" + CoreConstants.TC.SAVEDVALUES + "}";
		final FlexibleSearchQuery fsq = new FlexibleSearchQuery(query);
		return flexibleSearchService.search(fsq).getTotalCount();
	}

	private int countSavedValueEntries()
	{
		final String query = "SELECT {" + Item.PK + "} FROM {" + CoreConstants.TC.SAVEDVALUEENTRY + "}";
		final FlexibleSearchQuery fsq = new FlexibleSearchQuery(query);
		return flexibleSearchService.search(fsq).getTotalCount();
	}

}
