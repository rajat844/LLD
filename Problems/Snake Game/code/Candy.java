package code;

public class Candy {
    private final Position position;
    private final Integer value;

    public Candy(Position position, Integer value) {
        this.position = position;
        this.value = value;
    }

    public Candy(Position position) {
        this(position, 1);
    }

    public Position getPosition() {
        return position;
    }

    public Integer getValue() {
        return value;
    }
}
