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

    public void move(Position newHead) {
        if (newHead == null || body.isEmpty()) {
            return;
        }
        Position tail = body.removeLast();
        occupied.remove(tail);

        body.addFirst(newHead);
        occupied.add(newHead);
    }

    public void increaseLength(Position newHead) {
        if (newHead == null) {
            return;
        }
        body.addFirst(newHead);
        occupied.add(newHead);
    }
    
    public Direction getDirection() {
        return headDirection;
    }

    public void setDirection(Direction direction) {
        if (direction == null) {
            return;
        }
        if (this.headDirection == null || !this.headDirection.isOpposite(direction)) {
            this.headDirection = direction;
        }
    }

    public int getSize() {
        return body.size();
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
