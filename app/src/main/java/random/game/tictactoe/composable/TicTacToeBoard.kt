package random.game.tictactoe.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import random.game.tictactoe.GameState
import random.game.tictactoe.Move
import random.game.tictactoe.Player
import random.game.tictactoe.TicTacToeMove
import random.game.tictactoe.ui.theme.TicTacToeTheme

@Composable
fun TicTacToeBoard(
    gameState: GameState,
    onMove: (Player, Move) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(modifier) {
        gameState.board.forEachIndexed { r, rows ->
            Column {
                rows.forEachIndexed { c, tile ->
                    TicTacToeTile(
                        modifier = Modifier.size(80.dp),
                        tile = tile,
                        onClick = {
                            onMove(gameState.currentTurn, TicTacToeMove(r, c))
                        },
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