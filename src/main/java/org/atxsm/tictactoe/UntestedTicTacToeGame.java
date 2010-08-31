package org.atxsm.tictactoe;


import org.atxsm.tictactoe.Position.Horz;
import org.atxsm.tictactoe.Position.Vert;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


public class UntestedTicTacToeGame {

    private Map<Position, Player> board;

    private GameState gameState = GameState.X_NEXT;

    public static UntestedTicTacToeGame newGame() {
        return new UntestedTicTacToeGame();
    }

    public void play(Player player, Horz horz, Vert vert) {
        if (!gameState.allowsNext(player)) {
            throw new NotYourTurnException();
        }
        if (!isMoveAllowed(horz, vert)) {
            throw new UnavailableMoveException();
        }
        board.put(new Position(horz, vert), player);
        gameState = getNextState(horz, vert);
    }

    public GameState getGameState() {
        return gameState;
    }

    private UntestedTicTacToeGame() {
        board = new HashMap<Position, Player>();
    }

    private boolean isMoveAllowed(Horz horz, Vert vert) {
        return !board.containsKey(new Position(horz, vert));
    }

    private GameState getNextState(Horz horz, Vert vert) {
        return gameState.getNextState(hasWon(horz, vert), isBoardFull());
    }

    private boolean hasWon(Horz horz, Vert vert) {
        Collection<Row> rows = Row.getRows(new Position(horz, vert));
        for (Row row : rows) {
            if (hasWonRow(row)) {
                return true;
            }
        }
        return false;
    }

    private boolean isBoardFull() {
        return board.size() >= 9;
    }

    private boolean hasWonRow(Row row) {
        Player player = null;
        for (Position position : row.getPositions()) {
            if (!board.containsKey(position)) {
                return false;
            }
            Player piece = board.get(position);
            if (player == null) {
                player = piece;
            }
            if (piece != player) {
                return false;
            }
        }
        return true;
    }

}
