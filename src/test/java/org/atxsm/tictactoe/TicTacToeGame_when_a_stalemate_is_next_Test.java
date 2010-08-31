package org.atxsm.tictactoe;


import org.atxsm.tictactoe.Position.Horz;
import org.atxsm.tictactoe.Position.Vert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class TicTacToeGame_when_a_stalemate_is_next_Test {

    private TicTacToeGame game;

    @Before
    public void setUp() {
        game = TicTacToeGame.newGame();
        game.play(Player.X, Horz.LEFT, Vert.BOTTOM);
        game.play(Player.O, Horz.CENTER, Vert.CENTER);
        game.play(Player.X, Horz.LEFT, Vert.CENTER);
        game.play(Player.O, Horz.LEFT, Vert.TOP);
        game.play(Player.X, Horz.RIGHT, Vert.BOTTOM);
        game.play(Player.O, Horz.CENTER, Vert.BOTTOM);
        game.play(Player.X, Horz.CENTER, Vert.TOP);
        game.play(Player.O, Horz.RIGHT, Vert.CENTER);
    }

    @Test
    public void should_have_x_up_next() {
        assertThat(game.getGameState(), is(GameState.X_NEXT));
    }

    @Test
    public void should_get_to_a_stalemate_when_x_plays_the_last_spot() {
        game.play(Player.X, Horz.RIGHT, Vert.TOP);
        assertThat(game.getGameState(), is(GameState.STALEMATE));
    }

}
