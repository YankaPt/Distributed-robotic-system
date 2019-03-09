package model.world;

import model.robot.Hub;
import model.robot.Robot;
import model.robot.RobotPrototype;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class World {
    private Surface surface;
    private Point targetLocation;
    private Map<model.robot.Robot, Point> robotsAndLocations;
    private Set<Hub> hubs;
    private Double flatness;

    public World(String file) throws IOException {
        this.surface = readSurface(file);
        calculateFlatness();
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

    public void calculateFlatness() {
        double totalFlatness = 0;
        for (LandscapeType[] landscapeTypes : surface.surface) {
            for (LandscapeType landscapeType : landscapeTypes) {
                if (landscapeType.equals(LandscapeType.FREE)) {
                    totalFlatness++;
                }
            }
        }
        flatness = totalFlatness / (surface.getSize() * surface.getSize());
    }

    public void setTarget(Point target) {
        this.targetLocation = target;
    }

    public Point getTarget() {
        return targetLocation;
    }

    public Point getRelativeTargetLocationFor(Hub hub) {
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

    public void deleteRobot(model.robot.Robot robot) {
        if (robot instanceof RobotPrototype) {
            ((RobotPrototype) robot).getHub().removeRobot(robot);
        } else {
            throw new UnsupportedOperationException("Unexpecting type of robots for remove");
        }
        robotsAndLocations.remove(robot);
    }

    public void updateRobotLocation(model.robot.Robot robot, Hub hub) {
        Point hubLocation = robotsAndLocations.get(hub);
        Point updatedLocation = new Point(hub.getRelativeLocationFor(robot));
        updatedLocation.translate(hubLocation.x, hubLocation.y);
        robotsAndLocations.get(robot).setLocation(updatedLocation);
    }

    public LandscapeType checkCell(model.robot.Robot robot, Direction direction) {
        Point location = robotsAndLocations.get(robot);
        switch (direction) {
            case LEFT: {
                return surface.surface[location.y][location.x - 1];
            }
            case RIGHT: {
                return surface.surface[location.y][location.x + 1];
            }
            case FORWARD: {
                return surface.surface[location.y - 1][location.x];
            }
            case BACKWARD: {
                return surface.surface[location.y + 1][location.x];
            } default: {
                return null;
            }
        }
    }

    public Map<Robot, Point> getRobotsAndLocations() {
        return robotsAndLocations;
    }

    public void showHubMap(Hub hub) {
        System.out.println("______________________________________________________");
        LandscapeType[][] hubMap = hub.getSurfaceModel().getSurfaceModel();
        Point hubLocation = robotsAndLocations.get(hub);
        Robot robot = hub.getRobots().keySet().stream().findFirst().get();
        int i = 0;
        for (LandscapeType[] landscapeTypes : hubMap) {
            int j = 0;
            for (LandscapeType landscapeType: landscapeTypes) {
                if (hub.getSurfaceModel().getCenter().x + hub.getRelativeLocationFor(robot).x == j && hub.getSurfaceModel().getCenter().y + hub.getRelativeLocationFor(robot).y == i) {
                    System.out.print("R ");
                    j++;
                    continue;
                }
                if (hub.getSurfaceModel().getCenter().x == j && hub.getSurfaceModel().getCenter().y == i) {
                    System.out.print("H ");
                    j++;
                    continue;
                }
                if (hub.getSurfaceModel().getCenter().x + getRelativeTargetLocationFor(hub).x == j && hub.getSurfaceModel().getCenter().y + getRelativeTargetLocationFor(hub).y == i) {
                    System.out.print("T ");
                    j++;
                    continue;
                }
                switch (landscapeType) {
                    case OBST: {
                        System.out.print("X ");
                        break;
                    }
                    case UNKN: {
                        System.out.print("? ");
                        break;
                    }
                    case FREE: {
                        System.out.print("+ ");
                        break;
                    }
                }
                j++;
            }
            System.out.print("\n");
            i++;
        }
    }
    public Surface getSurface() {
        return surface;
    }

    public void setSurface(Surface surface) {
        this.surface = surface;
    }

    public Double getFlatness() {
        return flatness;
    }

    public void clear() {
        robotsAndLocations.clear();
        hubs.clear();
    }
}
