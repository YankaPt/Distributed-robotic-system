package boidrequests;

import boids.Boid;

import java.awt.*;

public class BoidCommunicationRequest extends BoidRequest {
    private String type;
    private int priority;
    private Point target;

    public BoidCommunicationRequest(Boid source) {
        super(source);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Point getTarget() {
        return target;
    }

    public void setTarget(Point target) {
        this.target = target;
    }
}
