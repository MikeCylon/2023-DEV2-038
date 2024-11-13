package random.game.tictactoe

enum class GameStatus {
    ONGOING, DRAW, WINNER_X, WINNER_O
}

interface GameState {
    val board: List<List<Tile>>
    val currentTurn: Player
    val status: GameStatus
}

interface GameBoard : GameState, PlayerMove {

    fun isPlayable(): Boolean

    fun isTurn(player: Player)

    fun isWinner(player: Player): Boolean

    fun isDraw(): Boolean

    fun isEnded(): Boolean
}

interface PlayerMove {
    fun makeMove(player: Player, position: Move): Boolean
}

interface Move {
    val x: Int
    val y: Int
}