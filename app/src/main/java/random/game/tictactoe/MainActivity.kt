package random.game.tictactoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import random.game.tictactoe.composable.TicTacToeBoard
import random.game.tictactoe.ui.theme.TicTacToeTheme

class MainActivity : ComponentActivity() {

    private val viewModel: GameViewModel by viewModels<GameViewModel> {
        GameViewModel.Companion.Factory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TicTacToeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    val gameState = viewModel.uiState.collectAsStateWithLifecycle()
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center,
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            TicTacToeBoard(
                                modifier = Modifier.padding(innerPadding),
                                gameState = gameState.value,
                                onMove = { player, position ->
                                    viewModel.makeMove(player, position)
                                }
                            )
                            // ideally we'd make more Composable but this is a simple exercise
                            when (gameState.value.status) {
                                GameStatus.WINNER_X -> {
                                    Text(
                                        text = "Congrats X",
                                    )
                                }
                                GameStatus.WINNER_O -> {
                                    Text(
                                        text = "Congrats O",
                                    )
                                }
                                GameStatus.DRAW -> {
                                    Text(
                                        text = "Too bad this is a draw",
                                    )
                                }
                                GameStatus.ONGOING -> {
                                    Text(
                                        text = "Your turn player ${gameState.value.currentTurn}",
                                    )
                                }
                            }
                            IconButton(
                                onClick = { viewModel.reset() },
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Refresh,
                                    contentDescription = "Reset",
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

