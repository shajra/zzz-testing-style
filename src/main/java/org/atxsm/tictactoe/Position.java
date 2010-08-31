package org.atxsm.tictactoe;


public class Position {

    private final Vert vertical;
    private final Horz horizontal;

    public Position(Horz horizontal, Vert vertical) {
        this.vertical = vertical;
        this.horizontal = horizontal;
    }

    public Horz getHorizontal() {
        return horizontal;
    }

    public Vert getVertical() {
        return vertical;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        if (horizontal != position.horizontal) return false;
        if (vertical != position.vertical) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = vertical != null ? vertical.hashCode() : 0;
        result = 31 * result
                + (horizontal != null ? horizontal.hashCode() : 0);
        return result;
    }

    public static enum Vert {
        BOTTOM, CENTER, TOP;
    }

    public static enum Horz {
        LEFT, CENTER, RIGHT;
    }

}
