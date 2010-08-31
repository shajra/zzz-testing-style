package org.atxsm.tictactoe;


import org.atxsm.tictactoe.Position.Horz;
import org.atxsm.tictactoe.Position.Vert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class TicTacToeGame_with_two_plays_Test {

    private TicTacToeGame game;

    @Before
    public void setUp() {
        game = TicTacToeGame.newGame();
        game.play(Player.X, Horz.CENTER, Vert.CENTER);
        game.play(Player.O, Horz.LEFT, Vert.BOTTOM);
    }

    @Test(expected = NotYourTurnException.class)
    public void should_not_allow_o_to_play_again_consecutively() {
        game.play(Player.O, Horz.LEFT, Vert.TOP);
    }

    @Test(expected = UnavailableMoveException.class)
    public void should_not_allow_x_to_play_in_the_same_spot_as_o() {
        game.play(Player.X, Horz.LEFT, Vert.BOTTOM);
    }

    @Test
    public void should_have_x_up_next() {
        assertThat(game.getGameState(), is(GameState.X_NEXT));
    }

}
