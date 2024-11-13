package random.game.tictactoe.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import random.game.tictactoe.GameState
import random.game.tictactoe.Move
import random.game.tictactoe.Player
import random.game.tictactoe.ui.theme.TicTacToeTheme

@Composable
fun TicTacToeBoard(
    gameState: GameState,
    onMove: (Player, Move) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(modifier) {
        gameState.board.forEach { rows ->
            Column {
                rows.forEach { tile ->
                    TicTacToeTile(
                        tile = tile,
                        onClick = {}, //TODO events
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TicTacToeBoardPreview(
    @PreviewParameter(PreviewGameStateProvider::class) gameState: GameState,
) {
    TicTacToeTheme {
        TicTacToeBoard(
            gameState = gameState,
            onMove = { _, _ -> },
        )
    }
}