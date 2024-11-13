package random.game.tictactoe

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class BoardUnitTest {

    private lateinit var gameBoard: GameBoard

    @Before
    fun before() {
        gameBoard = TicTacToeGame()
    }

    @Test
    fun isGamePlayable() {
        assertEquals(true, gameBoard.isPlayable())
    }

    @Test
    fun isGameFinished() {
        assertEquals(false, gameBoard.isEnded())
    }

    @Test
    fun `x always starts the game`() {
        assertEquals(true, gameBoard.isTurn(Player.X))
    }

    @Test
    fun `O never starts the game`() {
        assertEquals(false, gameBoard.isTurn(Player.O))
    }

    @Test
    fun `O is the second player of the game`() {
        gameBoard.makeMove(Player.X, TicTacToeMove(x = 0, y = 0))
        assertEquals(true, gameBoard.isTurn(Player.O))
    }

    @Test
    fun `a move must be done on the 3x3 board`() {
        val invalidMove = gameBoard.makeMove(Player.X, TicTacToeMove(x = 42, y = 42))
        assertEquals(false, invalidMove)
    }

    @Test
    fun `player x cannot play twice in a row`() {
        val moves: List<Pair<Player, Move>> = listOf<Pair<Player, Move>>(
            Player.X to TicTacToeMove(x = 0, y = 0),
            Player.X to TicTacToeMove(x = 0, y = 1),
        )
        val lastMoveInvalid = moves.map { (player, move) ->
            gameBoard.makeMove(player, move)
        }.last()
        assertEquals(false, lastMoveInvalid)
    }

    @Test
    fun `player o cannot play on a filled tile`() {
        val moves: List<Pair<Player, Move>> = listOf<Pair<Player, Move>>(
            Player.X to TicTacToeMove(x = 0, y = 0),
            Player.O to TicTacToeMove(x = 0, y = 0),
        )
        val lastMoveInvalid = moves.map { (player, move) ->
            gameBoard.makeMove(player, move)
        }.last()
        assertEquals(false, lastMoveInvalid)
    }

    @Test
    fun `player x wins the game vertical`() {
        val moves: List<Pair<Move, Player>> = listOf<Pair<Move, Player>>(
            TicTacToeMove(x = 1, y = 0) to Player.X,
            TicTacToeMove(x = 2, y = 0) to Player.O,
            TicTacToeMove(x = 1, y = 1) to Player.X,
            TicTacToeMove(x = 2, y = 1) to Player.O,
            TicTacToeMove(x = 1, y = 2) to Player.X,
        )
        moves.forEach { (move, player) ->
            gameBoard.makeMove(player, move)
        }
        assertEquals(true, gameBoard.isEnded())
        assertEquals(false, gameBoard.isDraw())
        assertEquals(false, gameBoard.isWinner(Player.O))
        assertEquals(true, gameBoard.isWinner(Player.X))
    }

    @Test
    fun `player o wins the game horizontal`() {
        val moves: List<Pair<Move, Player>> = listOf<Pair<Move, Player>>(
            TicTacToeMove(x = 0, y = 0) to Player.X,
            TicTacToeMove(x = 1, y = 0) to Player.O,
            TicTacToeMove(x = 2, y = 2) to Player.X,
            TicTacToeMove(x = 1, y = 1) to Player.O,
            TicTacToeMove(x = 0, y = 2) to Player.X,
            TicTacToeMove(x = 1, y = 2) to Player.O,
        )
        moves.forEach { (move, player) ->
            gameBoard.makeMove(player, move)
        }
        assertEquals(true, gameBoard.isEnded())
        assertEquals(false, gameBoard.isDraw())
        assertEquals(false, gameBoard.isWinner(Player.X))
        assertEquals(true, gameBoard.isWinner(Player.O))
    }

    @Test
    fun `player x wins the game diagonal`() {
        val moves: List<Pair<Move, Player>> = listOf<Pair<Move, Player>>(
            TicTacToeMove(x = 0, y = 0) to Player.X,
            TicTacToeMove(x = 1, y = 0) to Player.O,
            TicTacToeMove(x = 1, y = 1) to Player.X,
            TicTacToeMove(x = 1, y = 2) to Player.O,
            TicTacToeMove(x = 2, y = 2) to Player.X,
        )
        moves.forEach { (move, player) ->
            gameBoard.makeMove(player, move)
        }
        assertEquals(true, gameBoard.isEnded())
        assertEquals(false, gameBoard.isDraw())
        assertEquals(false, gameBoard.isWinner(Player.O))
        assertEquals(true, gameBoard.isWinner(Player.X))
    }

    @Test
    fun `game ended in a draw`() {
        val moves: List<Pair<Player, Move>> = listOf<Pair<Player, Move>>(
            Player.X to TicTacToeMove(x = 0, y = 0),
            Player.O to TicTacToeMove(x = 0, y = 1),
            Player.X to TicTacToeMove(x = 0, y = 2),
            Player.O to TicTacToeMove(x = 1, y = 0),
            Player.X to TicTacToeMove(x = 1, y = 1),
            Player.O to TicTacToeMove(x = 1, y = 2),
            Player.X to TicTacToeMove(x = 2, y = 0),
            Player.O to TicTacToeMove(x = 2, y = 1),
            Player.X to TicTacToeMove(x = 2, y = 2),
        )
        moves.forEach { (player, move) ->
            gameBoard.makeMove(player, move)
        }
        assertEquals(true, gameBoard.isEnded())
        assertEquals(false, gameBoard.isDraw())
        assertEquals(false, gameBoard.isWinner(Player.O))
        assertEquals(true, gameBoard.isWinner(Player.X))
    }
}