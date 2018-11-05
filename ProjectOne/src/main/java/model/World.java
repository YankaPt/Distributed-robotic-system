package model;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class World {
    private  Surface surface;
    private Point targetLocation;
    private Map<Robot, Point> robotsAndLocations;
    private Set<Hub> hubs;

    public World(String file) throws IOException {
        this.surface = readSurface(file);
        robotsAndLocations = new HashMap<>();
        hubs = new HashSet<>();
    }
    private Surface readSurface(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String size = reader.readLine();
        String[] elements;
        Surface surface = new Surface(Integer.parseInt(size));
        for (int i = 0; i < surface.getSize(); i++) {
            elements = reader.readLine().split(" ");
            for (int j = 0; j < surface.getSize(); j++) {
                surface.surface[i][j] = LandscapeType.valueOf(elements[j]);
            }
        }

        return surface;
    }

    public void setTarget(Point target) {
        this.targetLocation = target;
    }

    public Point getTarget() {
        return targetLocation;
    }

    public Point getRelativeTargetLocation(Hub hub) {
        Point hubLocation = robotsAndLocations.get(hub);
        return new Point(targetLocation.x - hubLocation.x, targetLocation.y - hubLocation.y);
    }

    public void registerHub(Hub hub, Point point) {
        robotsAndLocations.put(hub, point);
        hubs.add(hub);
        hub.getRobots().forEach((r, p) -> {
            Point point1 = new Point(p);
            point1.translate(point.x, point.y);
            robotsAndLocations.put(r, point1);
            r.setWorld(this);
        });
    }

    public void deleteRobot(Robot robot) {
        if (robot instanceof RobotPrototype) {
            ((RobotPrototype) robot).getHub().removeRobot(robot);
        } else {
            throw new UnsupportedOperationException("Unexpecting type of robots for remove");
        }
        robotsAndLocations.remove(robot);
    }

    public LandscapeType checkCell(Robot robot, Direction direction) {
        Point location = robotsAndLocations.get(robot);
        switch (direction) {
            case LEFT: {
                return surface.surface[location.x - 1][location.y];
            }
            case RIGHT: {
                return surface.surface[location.x + 1][location.y];
            }
            case FORWARD: {
                return surface.surface[location.x][location.y - 1];
            }
            case BACKWARD: {
                return surface.surface[location.x][location.y + 1];
            } default: {
                return null;
            }
        }
    }
}
