package random.game.tictactoe

enum class GameStatus {
    ONGOING, DRAW, WINNER_X, WINNER_O;

    companion object {

        fun from(tile: Tile): GameStatus {
            return when (tile) {
                Tile.X -> WINNER_X
                Tile.O -> WINNER_O
                else -> ONGOING
            }
        }

    }
}

interface GameState {
    val board: List<List<Tile>>
    val currentTurn: Player
    val status: GameStatus
}

interface GameBoard : PlayerMove {

    fun isPlayable(): Boolean

    fun isTurn(player: Player): Boolean

    fun isWinner(player: Player): Boolean

    fun isDraw(): Boolean

    fun isEnded(): Boolean

    fun reset()
}

interface PlayerMove {
    fun makeMove(player: Player, position: Move): Boolean
}

interface Move {
    val x: Int
    val y: Int
}