package org.atxsm.tictactoe;


import org.atxsm.tictactoe.Position.Horz;
import org.atxsm.tictactoe.Position.Vert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class TicTacToeGame_when_x_is_about_to_win_Test {

    private TicTacToeGame game;

    @Before
    public void setUp() {
        game = TicTacToeGame.newGame();
        game.play(Player.X, Horz.CENTER, Vert.BOTTOM);
        game.play(Player.O, Horz.LEFT, Vert.BOTTOM);
        game.play(Player.X, Horz.CENTER, Vert.CENTER);
        game.play(Player.O, Horz.LEFT, Vert.CENTER);
    }

    @Test
    public void should_have_x_up_next() {
        assertThat(game.getGameState(), is(GameState.X_NEXT));
    }

    @Test
    public void should_allow_x_to_win() {
        game.play(Player.X, Horz.CENTER, Vert.TOP);
        assertThat(game.getGameState(), is(GameState.X_HAS_WON));
    }

}
