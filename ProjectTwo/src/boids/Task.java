package boids;

import java.awt.*;

public class Task {
    private String type;
    private Point target;
    private int priority;

    public Task(String type, Point target) {
        this(type, target, 1);
    }

    public Task(String type, Point target, int priority) {
        this.type = type;
        this.target = target;
        this.priority = priority;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Point getTarget() {
        return target;
    }

    public void setTarget(Point target) {
        this.target = target;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
