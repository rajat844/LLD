# Snake Game

## Functional Requirements

1. The game runs on a configurable R x C grid that may contain internal wall cells. Crossing any outer boundary wraps the snake seamlessly to the opposite side of the grid.
2. Snake can move in all four directions, player can only take 90 degree turns only. 180 degree turns will be ignored.
3. When snake eats the cady, the candy will disappear, snake size grows and score will increase.
4. The candy can appear on any cell within the grid if that cell is not wall or occupied by snake.
5. When there is no space left in the grid, user will won the game and no new candy will appear.
6. If snake collides with itself or wall the game is over.
7. User can pause an active game, and can resume the game from where it left.
8. Upon reaching game over or won, system will monitor top 10 highscores in descending order.

## Non Functional Requirements

1. Every game tick will be O(1) complexity.
2. Game will follow OCP.
