package random.game.tictactoe

class TicTacToeGame(): GameBoard {

    // TODO migrate to flow
    var boardState: TicTacToeGameState = TicTacToeGameState()

    override fun makeMove(player: Player, position: Move): Boolean {
        if (isEnded()) {
            return false
        }
        if (position.x !in boardState.board.indices || position.y !in boardState.board[0].indices) {
            return false
        }
        if (boardState.board[position.x][position.y] != Tile.EMPTY) {
            return false
        }
        if (!isTurn(player)) {
            return false
        }
        val newBoard = boardState.board.mapIndexed { row, rows ->
            rows.mapIndexed { col, tile ->
                if (row == position.x && col == position.y) {
                    Tile.from(player)
                } else {
                    tile
                }
            }
        }
        val nextPlayer = if (boardState.currentTurn == Player.X) Player.O else Player.X
        boardState = boardState.copy(
            board = newBoard,
            currentTurn = nextPlayer,
            status = newBoard.toGameStatus()
        )
        return true
    }

    private fun List<List<Tile>>.toGameStatus(): GameStatus {
        this.forEach { row ->
            row.allMatching()?.let { return GameStatus.from(it) }
        }
        this[0].indices.forEach { col ->
            this.map { it[col] }.allMatching()?.let { return GameStatus.from(it) }
        }

        // diagonal
        this.indices
            .map { this[it][it] }
            .allMatching()
            ?.let { return GameStatus.from(it) }

        // Other diagonal
        this.indices
            .map { this[it][this.size - 1 - it] }
            .allMatching()
            ?.let { return GameStatus.from(it) }

        val isDraw = this.flatten().all { it == Tile.X || it == Tile.O }
        return if (isDraw) GameStatus.DRAW else GameStatus.ONGOING
    }

    private fun List<Tile>.allMatching(): Tile? {
        return if (this.isNotEmpty() && this.first() != Tile.EMPTY && this.all { it == this.first() }) {
            this.first()
        } else {
            null
        }
    }

    override fun isTurn(player: Player) = boardState.currentTurn == player

    override fun reset() {
        boardState = TicTacToeGameState()
    }

    override fun isPlayable(): Boolean = boardState.status == GameStatus.ONGOING

    override fun isWinner(player: Player): Boolean {
        return when (player) {
            Player.X -> boardState.status == GameStatus.WINNER_X
            Player.O -> boardState.status == GameStatus.WINNER_O
        }
    }

    override fun isDraw(): Boolean = boardState.status == GameStatus.DRAW

    override fun isEnded(): Boolean = boardState.status != GameStatus.ONGOING

}