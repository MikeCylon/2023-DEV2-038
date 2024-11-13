package random.game.tictactoe

import random.game.tictactoe.TicTacToeConfig.COLUMNS
import random.game.tictactoe.TicTacToeConfig.ROWS

class TicTacToeGameState(
    override val board: List<List<Tile>> = List(ROWS) { List(COLUMNS) { Tile.EMPTY } },
    override val currentTurn: Player = Player.X,
    override val status: GameStatus = GameStatus.ONGOING
) : GameState