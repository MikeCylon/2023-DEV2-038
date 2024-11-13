package random.game.tictactoe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GameViewModel : ViewModel() {

    private val game = TicTacToeGame()

    private val _uiState = MutableStateFlow<GameState>(game.boardState)
    val uiState: StateFlow<GameState> = _uiState

    fun makeMove(player: Player, position: Move) = viewModelScope.launch {
        runCatching {
            game.makeMove(player, position)
            _uiState.emit(game.boardState)
        }.onFailure {
            // Handle unexpected failures
            // Reset game on failure as a simple solution
            game.reset()
            _uiState.emit(game.boardState)
        }
    }

    fun reset() = viewModelScope.launch {
        game.reset()
        _uiState.emit(game.boardState)
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