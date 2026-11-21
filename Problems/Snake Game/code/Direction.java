package code;

public enum Direction {
    UP(-1, 0),
    DOWN(1, 0),
    LEFT(0, -1),
    RIGHT(0, 1);

    private final Integer dRow;
    private final Integer dCol;

    Direction(Integer dRow, Integer dCol) {
        this.dRow = dRow;
        this.dCol = dCol;
    }

    public Integer getDRow() {
        return dRow;
    }

    public Integer getDCol() {
        return dCol;
    }

    public boolean isOpposite(Direction other) {
        if (other == null) {
            return false;
        }
        return this.dRow + other.dRow == 0 && this.dCol + other.dCol == 0;
    }

    public Position nextFrom(Position current) {
        if (current == null) {
            return null;
        }
        return new Position(current.getRow() + this.dRow, current.getCol() + this.dCol);
    }
}
