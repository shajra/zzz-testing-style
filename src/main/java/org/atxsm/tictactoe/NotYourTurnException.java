package org.atxsm.tictactoe;


public class NotYourTurnException extends TicTacToeException {

    public NotYourTurnException() {
        super();
    }

    public NotYourTurnException(String message) {
        super(message);
    }
}
