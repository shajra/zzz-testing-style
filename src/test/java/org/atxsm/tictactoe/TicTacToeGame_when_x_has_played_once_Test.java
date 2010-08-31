package org.atxsm.tictactoe;


import org.atxsm.tictactoe.Position.Horz;
import org.atxsm.tictactoe.Position.Vert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class TicTacToeGame_when_x_has_played_once_Test {

    private TicTacToeGame game;

    @Before
    public void setUp() {
        game = TicTacToeGame.newGame();
        game.play(Player.X, Horz.CENTER, Vert.CENTER);
    }

    @Test(expected = NotYourTurnException.class)
    public void should_not_allow_x_to_play_again_consecutively() {
        game.play(Player.X, Horz.LEFT, Vert.TOP);
    }

    @Test(expected = UnavailableMoveException.class)
    public void should_not_allow_o_to_play_in_the_same_spot_as_x() {
        game.play(Player.O, Horz.CENTER, Vert.CENTER);
    }

    @Test
    public void should_have_o_up_next() {
        assertThat(game.getGameState(), is(GameState.O_NEXT));
    }

}
