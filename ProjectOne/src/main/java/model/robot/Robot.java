package model.robot;

import model.world.Direction;
import model.world.World;

import java.util.Objects;

public abstract class Robot implements Runnable{
    protected World world;
    private String id;

    public Robot() {
        id = IDGenerator.generateID() + "";
    }

    abstract public void move(Direction direction);

    abstract public boolean interact();

    public void setWorld(World world) {
        this.world = world;
    }

    public World getWorld() {
        return world;
    }

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
