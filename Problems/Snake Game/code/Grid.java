package code;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Grid {
    private final Integer rows;
    private final Integer cols;
    private final Set<Position> walls;

    public Grid(Integer rows, Integer cols, Set<Position> walls) {
        this.rows = rows;
        this.cols = cols;
        this.walls = (walls != null) ? new HashSet<>(walls) : new HashSet<>();
    }
    
    public Position wrap(Position position) {
        if (position == null || rows == null || cols == null || rows <= 0 || cols <= 0) {
            return position;
        }
        int wrappedRow = (position.getRow() % rows + rows) % rows;
        int wrappedCol = (position.getCol() % cols + cols) % cols;
        return new Position(wrappedRow, wrappedCol);
    }

    public boolean isWall(Position position) {
        return position != null && walls.contains(position);
    }

    public Integer getWalkAbleCellCount() {
        if (rows == null || cols == null) {
            return 0;
        }
        int totalCells = rows * cols;
        int validWallsCount = 0;
        for (Position wall : walls) {
            if (wall.getRow() >= 0 && wall.getRow() < rows && wall.getCol() >= 0 && wall.getCol() < cols) {
                validWallsCount++;
            }
        }
        return Math.max(0, totalCells - validWallsCount);
    }

    public Integer getRows() {
        return rows;
    }

    public Integer getCols() {
        return cols;
    }

    public Set<Position> getWalls() {
        return Collections.unmodifiableSet(walls);
    }
}
