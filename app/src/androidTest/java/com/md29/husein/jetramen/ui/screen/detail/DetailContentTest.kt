package com.md29.husein.jetramen.ui.screen.detail

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.printToLog
import com.md29.husein.jetramen.R
import com.md29.husein.jetramen.data.model.Menu
import com.md29.husein.jetramen.data.model.OrderMenu
import com.md29.husein.jetramen.ui.theme.JetRamenTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailContentTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private val fakeOrderMenu = OrderMenu(
        menu = Menu(
            3,
            R.drawable.shio_ramen,
            "Shio Ramen",
            "Ramen dengan kuah clear (jernih) berbumbu garam laut, memberikan rasa ringan dan segar. Tersedia dengan mi ramen, daun bawang, serta pilihan topping seafood seperti udang atau kerang.",
            25000
        ),
        count = 0
    )

    @Before
    fun setUp() {
        composeTestRule.setContent {
            JetRamenTheme {
                DetailContent(
                    image = fakeOrderMenu.menu.image,
                    title = fakeOrderMenu.menu.title,
                    description = fakeOrderMenu.menu.description,
                    price = fakeOrderMenu.menu.price,
                    count = fakeOrderMenu.count,
                    onBackClick = {},
                    onAddToCart = {}
                )
            }
        }
        composeTestRule.onRoot().printToLog("currentLabelExists")
    }

    @Test
    fun detailContent_isDisplayed() {
        composeTestRule.onNodeWithText(fakeOrderMenu.menu.title)
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(
                R.string.price,
                fakeOrderMenu.menu.price
            )
        ).assertIsDisplayed()
    }

    @Test
    fun increaseProduct_buttonEnabled() {
        composeTestRule.onNodeWithContentDescription("Order Button").assertIsNotEnabled()
        composeTestRule.onNodeWithStringId(R.string.plus_symbol).performScrollTo().performClick()
        composeTestRule.onNodeWithContentDescription("Order Button").assertIsEnabled()
    }

    @Test
    fun increaseProduct_correctCounter() {
        composeTestRule.onNodeWithStringId(R.string.plus_symbol).performScrollTo().performClick().performClick()
        composeTestRule.onNodeWithTag("count").assert(hasText("2"))

    }

    @Test
    fun decreaseProduct_stillZero() {
        composeTestRule.onNodeWithStringId(R.string.minus_symbol).performScrollTo().performClick()
        composeTestRule.onNodeWithTag("count").assert(hasText("0"))

    }
}