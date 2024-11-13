package random.game.tictactoe

import androidx.activity.ComponentActivity
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import random.game.tictactoe.composable.TicTacToeTile
import random.game.tictactoe.ui.theme.TicTacToeTheme


class TicTacToeTileTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    companion object {
        const val TILE_TAG = "tiletag"
    }

    @Test
    fun clickEmptyTile() {
        var onClicked = false

        composeTestRule.setContent {
            TicTacToeTheme {
                TicTacToeTile(
                    modifier = Modifier.testTag(TILE_TAG),
                    tile = Tile.EMPTY,
                    onClick = { onClicked = true },
                )
            }
        }

        composeTestRule.onNodeWithTag(TILE_TAG)
            .assertExists()
            .assertIsDisplayed()
            .performClick()

        assert(onClicked)
    }

    @Test
    fun clickOTile() {
        var onClicked = false

        composeTestRule.setContent {
            TicTacToeTheme {
                TicTacToeTile(
                    modifier = Modifier.testTag(TILE_TAG),
                    tile = Tile.O,
                    onClick = { onClicked = true },
                )
            }
        }

        composeTestRule.onNodeWithTag(TILE_TAG)
            .assertExists()
            .assertIsDisplayed()
            .performClick()

        Assert.assertFalse(onClicked)
    }
}