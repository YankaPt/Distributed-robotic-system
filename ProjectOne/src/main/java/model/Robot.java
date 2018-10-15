package model;

import java.util.Objects;

public abstract class Robot {
    String id;

    public Robot() {
        id = IDGenerator.generateID() + "";
    }

    abstract void move(Direction direction);

    abstract void interact();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Robot robot = (Robot) o;
        return Objects.equals(id, robot.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return id;
    }
}
class IDGenerator {
    private static int counter = 0;

    public static synchronized int generateID() {
        return counter++;
    }
}
