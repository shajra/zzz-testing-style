package org.atxsm.tictactoe;


import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Set;


public enum GameState {

    X_HAS_WON, O_HAS_WON, X_NEXT(Player.X), O_NEXT(Player.O), STALEMATE;

    private final Collection<Player> playersAllowedNext;

    private static Set<GameState> endStates = EnumSet.of(X_HAS_WON,
            O_HAS_WON, STALEMATE);

    public boolean allowsNext(Player player) {
        return playersAllowedNext.contains(player);
    }

    public GameState getNextState(boolean winMade, boolean boardFull) {
        if (endStates.contains(this)) {
            return this;
        }
        if (boardFull && !winMade) {
            return STALEMATE;
        }
        if (winMade) {
            switch (this) {
                case O_NEXT:
                    return O_HAS_WON;
                case X_NEXT:
                    return X_HAS_WON;
            }
        }
        switch (this) {
            case O_NEXT:
                return X_NEXT;
            case X_NEXT:
                return O_NEXT;
        }
        throw new TicTacToeException("game state should be impossible: " + this);
    }

    private GameState(Player... playersAllowedNext) {
        this.playersAllowedNext = Arrays.asList(playersAllowedNext);
    }

}
