package code.candyGenerationStrategy;

import code.Candy;
import code.Grid;
import code.Position;
import code.Snake;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomCandyGenerator implements CandyGenerationStrategy {
    private static final int MAX_ATTEMPTS = 100;
    private final Random random = new Random();

    @Override
    public Candy generateCandy(Grid grid, Snake snake) {
        if (grid == null || snake == null) {
            return null;
        }

        int totalWalkable = grid.getWalkAbleCellCount();
        if (snake.getSize() >= totalWalkable) {
            return null;
        }

        // Try fast random placement first in O(1) expected time
        for (int i = 0; i < MAX_ATTEMPTS; i++) {
            int r = random.nextInt(grid.getRows());
            int c = random.nextInt(grid.getCols());
            Position candidate = new Position(r, c);

            if (!grid.isWall(candidate) && !snake.isOccupying(candidate)) {
                return new Candy(candidate, 1);
            }
        }

        // Fallback if random attempts collided on crowded board
        List<Position> available = new ArrayList<>();
        for (int r = 0; r < grid.getRows(); r++) {
            for (int c = 0; c < grid.getCols(); c++) {
                Position pos = new Position(r, c);
                if (!grid.isWall(pos) && !snake.isOccupying(pos)) {
                    available.add(pos);
                }
            }
        }

        if (available.isEmpty()) {
            return null;
        }

        Position chosen = available.get(random.nextInt(available.size()));
        return new Candy(chosen, 1);
    }
}
