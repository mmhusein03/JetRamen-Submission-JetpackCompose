package com.md29.husein.jetramen

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performScrollToIndex
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.md29.husein.jetramen.data.model.FakeMenuData
import com.md29.husein.jetramen.ui.navigation.Screen
import com.md29.husein.jetramen.ui.screen.detail.onNodeWithStringId
import com.md29.husein.jetramen.ui.theme.JetRamenTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class JetRamenAppTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
    private lateinit var navController: TestNavHostController

    @Before
    fun setUp() {
        composeTestRule.setContent {
            JetRamenTheme {
                navController = TestNavHostController(LocalContext.current)
                navController.navigatorProvider.addNavigator(ComposeNavigator())
                JetRamenApp(navController = navController)
            }
        }
    }

    @Test
    fun navHost_verifyStartDestination() {
        navController.assertCurrentRouteName(Screen.Home.route)
    }

    @Test
    fun navHost_clickItem_navigatesToDetailWithData() {
        composeTestRule.onNodeWithTag("MenuList").performScrollToIndex(5)
        composeTestRule.onNodeWithText(FakeMenuData.dummyMenu[5].title).performClick()
        navController.assertCurrentRouteName(Screen.DetailMenu.route)
        composeTestRule.onNodeWithText(FakeMenuData.dummyMenu[5].title).assertIsDisplayed()
    }

    @Test
    fun navHost_bottomNavigation_working() {
        composeTestRule.onNodeWithStringId(R.string.menu_cart).performClick()
        navController.assertCurrentRouteName(Screen.Cart.route)
        composeTestRule.onNodeWithStringId(R.string.menu_profile).performClick()
        navController.assertCurrentRouteName(Screen.Profile.route)
        composeTestRule.onNodeWithStringId(R.string.menu_home).performClick()
        navController.assertCurrentRouteName(Screen.Home.route)
    }

    @Test
    fun navHost_clickItem_navigatesBack() {
        composeTestRule.onNodeWithTag("MenuList").performScrollToIndex(5)
        composeTestRule.onNodeWithText(FakeMenuData.dummyMenu[5].title).performClick()
        navController.assertCurrentRouteName(Screen.DetailMenu.route)
        composeTestRule.onNodeWithContentDescription(composeTestRule.activity.getString(R.string.back)).performClick()
        navController.assertCurrentRouteName(Screen.Home.route)
    }

    @Test
    fun navHost_checkout_rightBackStack() {
        composeTestRule.onNodeWithTag("MenuList").performScrollToIndex(3)
        composeTestRule.onNodeWithText(FakeMenuData.dummyMenu[3].title).performClick()
        navController.assertCurrentRouteName(Screen.DetailMenu.route)
        composeTestRule.onNodeWithStringId(R.string.plus_symbol).performScrollTo().performClick()
        composeTestRule.onNodeWithContentDescription("Order Button").performClick()
        navController.assertCurrentRouteName(Screen.Cart.route)
        composeTestRule.onNodeWithStringId(R.string.minus_symbol).performScrollTo().performClick()
        composeTestRule.onNodeWithStringId(R.string.menu_home).performClick()
        navController.assertCurrentRouteName(Screen.Home.route)
    }

    @Test
    fun searchBar_working() {
        composeTestRule.onNodeWithTag("searchBar").performClick()
        composeTestRule.onNodeWithContentDescription("Search").performTextInput("sakura")
        composeTestRule.onNodeWithText(FakeMenuData.dummyMenu[13].title).assertIsDisplayed()
    }

    @Test
    fun searchBar_noResultsFound() {
        composeTestRule.onNodeWithTag("searchBar").performClick()
        composeTestRule.onNodeWithContentDescription("Search").performTextInput("mie goreng")
        composeTestRule.onNodeWithStringId(R.string.empty_list).assertIsDisplayed()
    }

    @Test
    fun checkout_orderButton_disabledAfterRemovingItem() {
        composeTestRule.onNodeWithText(FakeMenuData.dummyMenu[0].title).performClick()
        composeTestRule.onNodeWithStringId(R.string.plus_symbol).performScrollTo().performClick()
        composeTestRule.onNodeWithContentDescription("Order Button").performClick()
        composeTestRule.onNodeWithStringId(R.string.minus_symbol).performScrollTo().performClick()
        composeTestRule.onNodeWithContentDescription("Order Button").assertIsNotEnabled()
    }
}