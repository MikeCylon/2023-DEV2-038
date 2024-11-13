package random.game.tictactoe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class GameViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<GameState>(TicTacToeGameState())
    val uiState: StateFlow<GameState> = _uiState


    fun makeMove(player: Player, position: Move) {
        TODO("Not yet implemented")
    }

    companion object {

        class Factory() : ViewModelProvider.Factory {

            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(GameViewModel::class.java)) {
                    return GameViewModel() as T
                }
                throw IllegalArgumentException("Unknown ViewModel ${modelClass.name}")
            }
        }
    }
}