package org.atxsm.tictactoe;


import org.atxsm.tictactoe.Position.Horz;
import org.atxsm.tictactoe.Position.Vert;

import java.util.*;


public enum Row {

    TOP_ACROSS(Vert.TOP),
    CENTER_ACROSS(Vert.CENTER),
    BOTTOM_ACROSS(Vert.BOTTOM),
    RIGHT_DOWN(Horz.RIGHT),
    CENTER_DOWN(Horz.CENTER),
    LEFT_DOWN(Horz.LEFT),
    DIAG_DOWN(DiagDirection.DOWN),
    DIAG_UP(DiagDirection.UP);

    private static final Map<Position, Set<Row>> rowsByPosition;

    private List<Position> positions = new ArrayList<Position>();

    public static Collection<Row> getRows(Position position) {
        return Collections.unmodifiableSet(rowsByPosition.get(position));
    }

    public Collection<Position> getPositions() {
        return Collections.unmodifiableList(positions);
    }

    static {
        rowsByPosition = new HashMap<Position, Set<Row>>();
        for (Row row : values()) {
            for (Position position : row.getPositions()) {
                mapRowByPosition(row, position);
            }
        }
    }

    private static void mapRowByPosition(Row row, Position position) {
        Set<Row> rows = rowsByPosition.get(position);
        if (rows == null) {
            rows = new HashSet<Row>();
            rowsByPosition.put(position, rows);
        }
        rows.add(row);
    }

    private Row(Vert vert) {
        for (Horz horz : Horz.values()) {
            positions.add(new Position(horz, vert));
        }
    }

    private Row(Horz horz) {
        for (Vert vert : Vert.values()) {
            positions.add(new Position(horz, vert));
        }
    }

    private Row(DiagDirection diagDirection) {
        switch (diagDirection) {
            case UP:
                positions.add(new Position(Horz.LEFT, Vert.BOTTOM));
                positions.add(new Position(Horz.CENTER, Vert.CENTER));
                positions.add(new Position(Horz.RIGHT, Vert.TOP));
                break;
            case DOWN:
                positions.add(new Position(Horz.LEFT, Vert.TOP));
                positions.add(new Position(Horz.CENTER, Vert.CENTER));
                positions.add(new Position(Horz.RIGHT, Vert.BOTTOM));
                break;
            default:
                throw new TicTacToeException(diagDirection + " shouldn't exist");
        }
    }

    private enum DiagDirection {
        UP, DOWN
    }

}
