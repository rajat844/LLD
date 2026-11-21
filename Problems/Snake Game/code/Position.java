package code;

import java.util.Objects;

public class Position {
    private Integer row;
    private Integer col;

    public Position(Integer row, Integer col) {
        this.row = row;
        this.col = col;
    }

    public Integer getRow() {
        return row;
    }

    public Integer getCol() {
        return col;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return Objects.equals(row, position.row) && Objects.equals(col, position.col);
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}
