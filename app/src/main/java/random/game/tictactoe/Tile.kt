package random.game.tictactoe

enum class Tile {
    EMPTY, X, O;

    companion object {

        fun from(player: Player): Tile {
            return when (player) {
                Player.X -> X
                Player.O -> O
            }
        }

    }
}