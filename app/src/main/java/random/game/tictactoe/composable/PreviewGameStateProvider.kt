package random.game.tictactoe.composable

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import random.game.tictactoe.GameState
import random.game.tictactoe.TicTacToeGameState

internal class PreviewGameStateProvider : PreviewParameterProvider<GameState> {
    override val values: Sequence<GameState> =
        sequenceOf(
            TicTacToeGameState(

            ),
        )

    override val count: Int = values.count()
}
