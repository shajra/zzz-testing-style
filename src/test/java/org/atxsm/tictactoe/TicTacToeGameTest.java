package org.atxsm.tictactoe;


import org.atxsm.tictactoe.Position.Horz;
import org.atxsm.tictactoe.Position.Vert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class TicTacToeGameTest {

    private TicTacToeGame game;

    @Before
    public void setUp() {
        game = TicTacToeGame.newGame();
    }

    @Test
    public void x_up_next_when_game_is_new() {
        assertThat(game.getGameState(), is(GameState.X_NEXT));
    }

    @Test(expected = NotYourTurnException.class)
    public void o_cant_play_when_game_is_new() {
        game.play(Player.O, Horz.CENTER, Vert.CENTER);
    }

    @Test
    public void plays_should_alternate_between_x_and_o() {
        assertThat(game.getGameState(), is(GameState.X_NEXT));
        game.play(Player.X, Horz.CENTER, Vert.CENTER);
        assertThat(game.getGameState(), is(GameState.O_NEXT));
        game.play(Player.O, Horz.LEFT, Vert.BOTTOM);
        assertThat(game.getGameState(), is(GameState.X_NEXT));
    }

    @Test(expected = NotYourTurnException.class)
    public void x_cant_play_again_consecutively() {
        game.play(Player.X, Horz.CENTER, Vert.CENTER);
        game.play(Player.X, Horz.LEFT, Vert.TOP);
    }

    @Test(expected = NotYourTurnException.class)
    public void o_cant_play_again_consecutively() {
        game.play(Player.X, Horz.CENTER, Vert.CENTER);
        game.play(Player.O, Horz.LEFT, Vert.BOTTOM);
        game.play(Player.O, Horz.LEFT, Vert.TOP);
    }

    @Test(expected = UnavailableMoveException.class)
    public void cant_play_same_spot_twice() {
        game.play(Player.X, Horz.CENTER, Vert.CENTER);
        game.play(Player.O, Horz.CENTER, Vert.CENTER);
    }

    @Test
    public void should_be_playable_to_a_stalemate() {
        game.play(Player.X, Horz.LEFT, Vert.BOTTOM);
        game.play(Player.O, Horz.CENTER, Vert.CENTER);
        game.play(Player.X, Horz.LEFT, Vert.CENTER);
        game.play(Player.O, Horz.LEFT, Vert.TOP);
        game.play(Player.X, Horz.RIGHT, Vert.BOTTOM);
        game.play(Player.O, Horz.CENTER, Vert.BOTTOM);
        game.play(Player.X, Horz.CENTER, Vert.TOP);
        game.play(Player.O, Horz.RIGHT, Vert.CENTER);
        game.play(Player.X, Horz.RIGHT, Vert.TOP);
        assertThat(game.getGameState(), is(GameState.STALEMATE));
    }

    @Test
    public void should_be_possible_for_x_to_win() {
        game.play(Player.X, Horz.CENTER, Vert.BOTTOM);
        game.play(Player.O, Horz.LEFT, Vert.BOTTOM);
        game.play(Player.X, Horz.CENTER, Vert.CENTER);
        game.play(Player.O, Horz.LEFT, Vert.CENTER);
        game.play(Player.X, Horz.CENTER, Vert.TOP);
        assertThat(game.getGameState(), is(GameState.X_HAS_WON));
    }

}
