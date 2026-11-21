package code.candyGenerationStrategy;

import code.Candy;
import code.Grid;
import code.Position;
import code.Snake;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AvailableCellCandyGenerator implements CandyGenerationStrategy {
    private final Random random = new Random();

    @Override
    public Candy generateCandy(Grid grid, Snake snake) {
        if (grid == null || snake == null) {
            return null;
        }

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
