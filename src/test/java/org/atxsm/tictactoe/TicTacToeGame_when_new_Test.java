package org.atxsm.tictactoe;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.atxsm.tictactoe.Position.Horz;
import org.atxsm.tictactoe.Position.Vert;


public class TicTacToeGame_when_new_Test {

    private TicTacToeGame game;

    @Before
    public void setUp() {
        game = TicTacToeGame.newGame();
    }

    @Test
    public void should_have_x_up_next() {
        assertThat(game.getGameState(), is(GameState.X_NEXT));
    }

    @Test(expected = NotYourTurnException.class)
    public void should_not_all_o_to_play_next() {
        game.play(Player.O, Horz.CENTER, Vert.CENTER);
    }

}
