package org.atxsm.tictactoe

import org.specs.Specification
import GameState._

class GameStateSpec extends Specification {

  "X winning is permanent" in {
    allNextStates(X_HAS_WON) forall { _ mustBe X_HAS_WON }
  }

  "O winning is permanent" in {
    allNextStates(O_HAS_WON) forall { _ mustBe O_HAS_WON }
  }

  "a stalemate is permanent" in {
    allNextStates(STALEMATE) forall { _ mustBe STALEMATE }
  }

  "not winning but filling the board is a stalemate" in {
    val (winMade, boardFull) = (false, true)
    List(O_NEXT, X_NEXT) foreach {
      _.getNextState(winMade, boardFull) mustBe STALEMATE
    }
  }

  "on X's turn" in {
    val state = X_NEXT

    "if X wins then X wins whether the board is full or not" in {
      val winMade = true
      List(true, false) foreach { boardFull =>
        state.getNextState(winMade, boardFull) mustBe X_HAS_WON
      }
    }

    "if X doesn't win then O is next if the board isn't full" in {
      val (winMade, boardFull) = (false, false)
        state.getNextState(winMade, boardFull) mustBe O_NEXT
    }
  }

  "on O's turn" in {
    val state = O_NEXT

    "if O wins then O wins whether the board is full or not" in {
      val winMade = true
      List(true, false) foreach { boardFull =>
        state.getNextState(winMade, boardFull) mustBe O_HAS_WON
      }
    }

    "if O doesn't win then X is next if the board isn't full" in {
      val (winMade, boardFull) = (false, false)
      state.getNextState(winMade, boardFull) mustBe X_NEXT
    }
  }

  private def allNextStates(state: GameState) = {
    for (hasWon <- List(true, false);
         boardFull <- List(true, false))
    yield state.getNextState(hasWon, boardFull)
  }

  private val volatileStates = List(X_NEXT, O_NEXT)

}
