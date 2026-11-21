package code;

import code.candyGenerationStrategy.CandyGenerationStrategy;

public class Game {
    private final Grid grid;
    private final Snake snake;
    private Candy candy;
    private Integer score;
    private GameState state;
    private final CandyGenerationStrategy strategy;

    public Game(Grid grid, Snake snake, CandyGenerationStrategy strategy) {
        this.grid = grid;
        this.snake = snake;
        this.strategy = strategy;
        this.state = GameState.RUNNING;
        this.score = 0;

        // Generate initial candy
        if (this.strategy != null && this.grid != null && this.snake != null) {
            this.candy = this.strategy.generateCandy(this.grid, this.snake);
            if (this.candy == null && this.snake.getSize() >= this.grid.getWalkAbleCellCount()) {
                this.state = GameState.WON;
            }
        }
    }

    /**
     * Executes one tick of the game (O(1) time complexity).
     *
     * @return current GameState after this tick
     */
    public GameState step() {
        if (state != GameState.RUNNING) {
            return state;
        }

        if (snake == null || grid == null) {
            return state;
        }

        Position currentHead = snake.getHead();
        Direction currentDir = snake.getDirection();
        if (currentHead == null || currentDir == null) {
            return state;
        }

        Position nextRaw = currentDir.nextFrom(currentHead);
        Position nextPos = grid.wrap(nextRaw);

        // 1. Check wall collision
        if (grid.isWall(nextPos)) {
            this.state = GameState.GAME_OVER;
            return this.state;
        }

        // 2. Check eating candy vs regular move
        boolean isEating = (candy != null && nextPos.equals(candy.getPosition()));

        // 3. Check self collision
        // If not eating candy, current tail moves forward and is vacated.
        // If eating candy, tail stays, so moving onto any occupied cell is a self-collision.
        boolean selfCollision = isEating
                ? snake.isOccupying(nextPos)
                : (snake.isOccupying(nextPos) && !nextPos.equals(snake.getTail()));

        if (selfCollision) {
            this.state = GameState.GAME_OVER;
            return this.state;
        }

        // 4. Perform move or growth
        if (isEating) {
            snake.increaseLength(nextPos);
            score += (candy.getValue() != null ? candy.getValue() : 1);

            // Check win condition
            if (snake.getSize() >= grid.getWalkAbleCellCount()) {
                this.candy = null;
                this.state = GameState.WON;
                return this.state;
            }

            // Generate next candy
            if (strategy != null) {
                this.candy = strategy.generateCandy(grid, snake);
                if (this.candy == null) {
                    this.state = GameState.WON;
                    return this.state;
                }
            }
        } else {
            snake.move(nextPos);
        }

        return this.state;
    }

    public void pause() {
        if (this.state == GameState.RUNNING) {
            this.state = GameState.PAUSED;
        }
    }

    public void resume() {
        if (this.state == GameState.PAUSED) {
            this.state = GameState.RUNNING;
        }
    }

    public void continueGame() {
        resume();
    }

    public void changeDirection(Direction direction) {
        if (this.state == GameState.RUNNING && direction != null && snake != null) {
            snake.setDirection(direction);
        }
    }

    public int getScore() {
        return score != null ? score : 0;
    }

    public Grid getGrid() {
        return grid;
    }

    public Snake getSnake() {
        return snake;
    }

    public Candy getCandy() {
        return candy;
    }

    public GameState getState() {
        return state;
    }

    public CandyGenerationStrategy getStrategy() {
        return strategy;
    }
}
