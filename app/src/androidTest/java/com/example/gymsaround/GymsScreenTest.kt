package com.example.gymsaround

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.gymsaround.DummyGymsList.getDummyGymsList
import com.example.gymsaround.Gyms.presentation.SemanticsDescription
import com.example.gymsaround.Gyms.presentation.gymslist.GymsScreen
import com.example.gymsaround.Gyms.presentation.gymslist.GymsScreenState
import com.example.gymsaround.ui.theme.GymsAroundTheme
import org.junit.Rule
import org.junit.Test

class GymsScreenTest {

    @get:Rule
    val testRule: ComposeContentTestRule = createComposeRule()


    // junit test framework
    @Test
    fun loadingState_isActive() {
        testRule.setContent {
            GymsAroundTheme {
                GymsScreen(
                    state = GymsScreenState(
                        isLoading = true,
                        gyms = emptyList(),
                    ),
                    onItemClick = {},
                    onFavouriteIconClick = { _: Int, _: Boolean -> }
                )
            }
        }
        testRule.onNodeWithContentDescription(SemanticsDescription.GYMS_LIST_LOADING)
            .assertIsDisplayed()
    }

    @Test
    fun loadingContentState_iaActive() {
        val gymsList = getDummyGymsList()
        testRule.setContent {
            GymsAroundTheme {
                GymsScreen(
                    onItemClick = {},
                    state = GymsScreenState(
                        isLoading = false,
                        gyms = gymsList,
                    ),
                    onFavouriteIconClick = { _: Int, _: Boolean -> }
                )
            }
            testRule.onNodeWithText(gymsList[0].name).assertIsDisplayed()
            testRule.onNodeWithContentDescription(SemanticsDescription.GYMS_LIST_LOADING)
                .assertDoesNotExist()
        }
    }

    @Test
    fun errorState_isActive() {
        val errorText = "failed to load data"
        testRule.setContent {
            GymsAroundTheme {
                GymsScreen(
                    onItemClick = {},
                    state = GymsScreenState(
                        isLoading = true,
                        gyms = emptyList(),
                        error = errorText
                    ),
                    onFavouriteIconClick = { _: Int, _: Boolean -> }
                )
            }
            testRule.onNodeWithText(errorText).assertIsDisplayed()
            testRule.onNodeWithContentDescription(SemanticsDescription.GYMS_LIST_LOADING)
                .assertDoesNotExist()
        }
    }

    @Test
    fun onItemClick_isActive() {
        val gymsList = getDummyGymsList()
        val itemList = gymsList[0]
        testRule.setContent {
            GymsAroundTheme {
                GymsScreen(
                    onItemClick = { id ->
                        assert(id == itemList.id)
                    },
                    state = GymsScreenState(
                        isLoading = false,
                        gyms = gymsList,
                    ),
                    onFavouriteIconClick = { _: Int, _: Boolean -> }
                )
            }
        }
        testRule.onNodeWithText(itemList.name).performClick()

    }
}