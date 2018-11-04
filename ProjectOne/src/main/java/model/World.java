package model;

import java.awt.*;
import java.util.*;

public class World {
    private Surface surface;
    private Map<Robot, Point> robotsAndLocations;
    private Set<Hub> hubs;

    public World(String file) {
        this.surface = readSurface(file);
        robotsAndLocations = new HashMap<>();
        hubs = new HashSet<>();
    }
    private Surface readSurface(String file) {
        Surface surface = new Surface();
        return surface;
    }

    public void registerHub(Hub hub, Point point) {
        robotsAndLocations.put(hub, point);
        hubs.add(hub);
        hub.getRobots().forEach((r, p) -> {
            p.translate(point.x, point.y);
            robotsAndLocations.put(r, p);
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
        //TODO
        return null;
    }
}
