package code;

import code.candyGenerationStrategy.CandyGenerationStrategy;
import code.candyGenerationStrategy.RandomCandyGenerator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

public class GameManager {
    private static final int MAX_TOP_SCORES = 10;
    private final PriorityQueue<Integer> highscores;
    private Game currentGame;

    public GameManager() {
        this.highscores = new PriorityQueue<>(MAX_TOP_SCORES);
    }

    public Game playGame() {
        Grid defaultGrid = new Grid(10, 10, new HashSet<>());
        Snake defaultSnake = new Snake(new Position(0, 0), Direction.RIGHT);
        CandyGenerationStrategy defaultStrategy = new RandomCandyGenerator();
        return playGame(defaultGrid, defaultSnake, defaultStrategy);
    }

    public Game playGame(Grid grid, Snake snake, CandyGenerationStrategy strategy) {
        this.currentGame = new Game(grid, snake, strategy);
        return this.currentGame;
    }

    public void recordScore(int score) {
        if (highscores.size() < MAX_TOP_SCORES) {
            highscores.offer(score);
        } else if (highscores.peek() != null && score > highscores.peek()) {
            highscores.poll();
            highscores.offer(score);
        }
    }

    public void recordScore() {
        if (currentGame != null) {
            recordScore(currentGame.getScore());
        }
    }

    public List<Integer> getTopScores() {
        List<Integer> list = new ArrayList<>(highscores);
        list.sort(Collections.reverseOrder());
        return list;
    }

    public PriorityQueue<Integer> getHighscores() {
        return highscores;
    }

    public Game getCurrentGame() {
        return currentGame;
    }
}
