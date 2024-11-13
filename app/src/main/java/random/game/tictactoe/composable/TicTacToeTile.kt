package random.game.tictactoe.composable

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import random.game.tictactoe.Tile
import random.game.tictactoe.ui.theme.TicTacToeTheme

@Composable
fun TicTacToeTile(
    tile: Tile,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    when (tile) {
        Tile.X -> {
            Icon(
                modifier = modifier,
                imageVector = Icons.Filled.Clear,
                contentDescription = "X",
            )
        }

        Tile.O -> {
            Icon(
                modifier = modifier,
                imageVector = Icons.Filled.Info,
                contentDescription = "O",
            )
        }

        Tile.EMPTY -> {
            IconButton(
                modifier = modifier,
                onClick = onClick,
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "EMPTY",
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TicTacToeBoardPreview(
) {
    TicTacToeTheme {
        TicTacToeTile(
            tile = Tile.O,
            onClick = { },
        )
    }
}