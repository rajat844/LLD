package code;

import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Snake {
    private final Deque<Position> body;
    private final Set<Position> occupied;
    private Direction headDirection;

    public Snake(Position initialPosition, Direction initialDirection) {
        this.body = new LinkedList<>();
        this.occupied = new HashSet<>();
        this.headDirection = initialDirection;

        if (initialPosition != null) {
            this.body.addFirst(initialPosition);
            this.occupied.add(initialPosition);
        }
    }

    /**
     * Moves the snake to a new head position in O(1) time.
     * Removes the tail and adds the new head.
     *
     * @param newHead the next position for the snake's head
     */
    public void move(Position newHead) {
        if (newHead == null || body.isEmpty()) {
            return;
        }
        // Remove tail first to safely allow moving into the cell just vacated by the tail
        Position tail = body.removeLast();
        occupied.remove(tail);

        body.addFirst(newHead);
        occupied.add(newHead);
    }

    /**
     * Moves the snake forward by one step in its current direction.
     */
    public void move() {
        if (headDirection != null && !body.isEmpty()) {
            Position next = headDirection.nextFrom(getHead());
            if (next != null) {
                move(next);
            }
        }
    }

    /**
     * Grows the snake by adding a new head position without removing the tail in O(1) time.
     *
     * @param newHead the new head position after eating candy
     */
    public void increaseLength(Position newHead) {
        if (newHead == null) {
            return;
        }
        body.addFirst(newHead);
        occupied.add(newHead);
    }

    /**
     * Grows the snake forward by one step in its current direction.
     */
    public void increaseLength() {
        if (headDirection != null && !body.isEmpty()) {
            Position next = headDirection.nextFrom(getHead());
            if (next != null) {
                increaseLength(next);
            }
        }
    }

    public Direction getDirection() {
        return headDirection;
    }

    /**
     * Updates the snake's direction.
     * 180-degree turns are ignored as per requirement.
     *
     * @param direction new direction to face
     */
    public void setDirection(Direction direction) {
        if (direction == null) {
            return;
        }
        // Ignore 180-degree turns
        if (this.headDirection == null || !this.headDirection.isOpposite(direction)) {
            this.headDirection = direction;
        }
    }

    public int getSize() {
        return body.size();
    }

    // Matching diagram naming convention
    public int getSIze() {
        return getSize();
    }

    public Position getHead() {
        return body.peekFirst();
    }

    public Position getTail() {
        return body.peekLast();
    }

    public boolean isOccupying(Position position) {
        return position != null && occupied.contains(position);
    }

    public Deque<Position> getBody() {
        return body;
    }

    public Set<Position> getOccupied() {
        return Collections.unmodifiableSet(occupied);
    }
}
