package org.atxsm.tictactoe

import org.specs.Specification
import org.atxsm.tictactoe.Position.{Vert, Horz}

class TicTacToeGameSpec extends Specification {

  val game = TicTacToeGame.newGame

  "a fresh tic tac toe game" should {

    "have X up next" in {
      game.getGameState mustEq GameState.X_NEXT
    }

    "not allow O to play next" in {
      (game play (Player.O, Horz.CENTER, Vert.CENTER)
              must throwA[NotYourTurnException])
    }
  }

  "a tic tac toe game with one play (X)" should {
    game play (Player.X, Horz.CENTER, Vert.CENTER)

    "not allow X to play again consecutively" in {
      (game play (Player.X, Horz.LEFT, Vert.TOP)
              must throwA[NotYourTurnException])
    }

    "not allow O to play in the same spot as X" in {
      (game play (Player.O, Horz.CENTER, Vert.CENTER)
              must throwA[UnavailableMoveException])
    }

    "should have O up next" in {
      game.getGameState mustEq GameState.O_NEXT
    }
  }

  "a tic tac toe game with two plays (X, then O)" should {
    game play (Player.X, Horz.CENTER, Vert.CENTER)
    game play (Player.O, Horz.LEFT, Vert.BOTTOM)

    "not allow O to play again consecutively" in {
      (game play (Player.O, Horz.LEFT, Vert.TOP)
              must throwA[NotYourTurnException])
    }

    "not allow X to play in the same spot as O" in {
      (game play (Player.X, Horz.LEFT, Vert.BOTTOM)
              must throwA[UnavailableMoveException])
    }

    "should have X up next" in {
      game.getGameState mustEq GameState.X_NEXT
    }
  }

  "a tic tac toe game in which X is about to win" should {
    game play (Player.X, Horz.CENTER, Vert.BOTTOM)
    game play (Player.O, Horz.LEFT, Vert.BOTTOM)
    game play (Player.X, Horz.CENTER, Vert.CENTER)
    game play (Player.O, Horz.LEFT, Vert.CENTER)

    "have X up next" in {
      game.getGameState mustEq GameState.X_NEXT
    }

    "allow X to win" in {
      game play (Player.X, Horz.CENTER, Vert.TOP)
      game.getGameState mustEq GameState.X_HAS_WON
    }
  }

  "a tic tac toe game in which a stalemate is next" should {
    game play (Player.X, Horz.LEFT, Vert.BOTTOM)
    game play (Player.O, Horz.CENTER, Vert.CENTER)
    game play (Player.X, Horz.LEFT, Vert.CENTER)
    game play (Player.O, Horz.LEFT, Vert.TOP)
    game play (Player.X, Horz.RIGHT, Vert.BOTTOM)
    game play (Player.O, Horz.CENTER, Vert.BOTTOM)
    game play (Player.X, Horz.CENTER, Vert.TOP)
    game play (Player.O, Horz.RIGHT, Vert.CENTER)

    "have X up next" in {
      game.getGameState mustEq GameState.X_NEXT
    }

    "get to a stalemate when X plays the last spot" in {
      game play (Player.X, Horz.RIGHT, Vert.TOP)
      game.getGameState mustEq GameState.STALEMATE
    }
  }

}
