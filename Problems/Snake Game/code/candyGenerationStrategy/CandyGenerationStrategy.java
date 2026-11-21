package code.candyGenerationStrategy;

import code.Candy;
import code.Grid;
import code.Snake;

public interface CandyGenerationStrategy {
    Candy generateCandy(Grid grid, Snake snake);
}
