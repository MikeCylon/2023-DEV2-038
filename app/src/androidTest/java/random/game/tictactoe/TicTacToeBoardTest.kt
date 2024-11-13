package random.game.tictactoe

import androidx.activity.ComponentActivity
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import org.junit.Rule
import org.junit.Test
import random.game.tictactoe.composable.TicTacToeBoard
import random.game.tictactoe.ui.theme.TicTacToeTheme

class TicTacToeBoardTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    companion object {
        const val BOARD_TAG = "boardtag"
    }

    @Test
    fun allEmptyTiles() {
        composeTestRule.setContent {
            TicTacToeTheme {
                TicTacToeBoard(
                    modifier = Modifier.testTag(BOARD_TAG),
                    gameState = TicTacToeGameState(),
                    onMove = { _, _ -> },
                )
            }
        }

        composeTestRule.onNodeWithTag(BOARD_TAG)
            .assertExists()
            .assertIsDisplayed()

        composeTestRule.onNode(hasContentDescription("X"))
            .assertDoesNotExist()

        composeTestRule.onNode(hasContentDescription("O"))
            .assertDoesNotExist()

        composeTestRule.onAllNodesWithContentDescription("EMPTY")
            .assertCountEquals(9)

    }

}