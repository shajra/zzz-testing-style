package org.atxsm.tictactoe;


import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.atxsm.tictactoe.GameState.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.everyItem;


public class GameStateTest {

    @Test
    public void x_winning_is_permanent() {
        assertNeverChanges(X_HAS_WON);
    }

    @Test
    public void o_winning_is_permanent() {
        assertNeverChanges(O_HAS_WON);
    }

    @Test
    public void a_stalemate_is_permanent() {
        assertNeverChanges(STALEMATE);
    }

    @Test
    public void x_not_winning_but_filling_the_board_is_a_stalemate() {
        boolean winMade = false;
        boolean boardFull = true;
        assertThat(X_NEXT.getNextState(winMade, boardFull), is(STALEMATE));
    }

    @Test
    public void o_not_winning_but_filling_the_board_is_a_stalemate() {
        boolean winMade = false;
        boolean boardFull = true;
        assertThat(O_NEXT.getNextState(winMade, boardFull), is(STALEMATE));
    }

    private void assertNeverChanges(GameState state) {
        assertThat(allNextStatesOf(state), everyItem(is(state)));
    }

    private List<GameState> allNextStatesOf(GameState state) {
        List<GameState> allNextStates = new ArrayList<GameState>();
        for (Boolean hasWon : Arrays.asList(true, false)) {
            for (Boolean boardFull : Arrays.asList(true, false)) {
                allNextStates.add(state.getNextState(hasWon, boardFull));
            }
        }
        return allNextStates;
    }

}
