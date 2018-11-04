package model;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Hub extends Robot {
    Map<Robot, Point> robotsAndRelativeLocations;
    private SurfaceModel surfaceModel;
    private static final int INITIAL_SURFACE_MODEL_SIZE = 10;

    public Hub() {
        robotsAndRelativeLocations = new HashMap<>();
        surfaceModel = new SurfaceModel(INITIAL_SURFACE_MODEL_SIZE);
        System.out.print(id);
    }

    public void createNewSurfaceModel(int size) {
        surfaceModel = new SurfaceModel(size);
    }

    public SurfaceModel getSurfaceModel() {
        return surfaceModel;
    }

    public void setSurfaceModel(SurfaceModel surfaceModel) {
        this.surfaceModel = surfaceModel;
    }

    public void addRobot(Robot robot) {
        addRobot(robot, new Point(0,0));
    }
    public void addRobot(Robot robot, Point point) {
        robotsAndRelativeLocations.put(robot, point);
        if (robot instanceof RobotPrototype) {
            ((RobotPrototype) robot).setHub(this);
        } else {
            throw new UnsupportedOperationException("Hub can't control other types of robots yet");
        }
    }

    public void removeRobot(Robot robot) {
        robotsAndRelativeLocations.remove(robot);
    }

    public Point getRelativeLocationFor(Robot robot) {
        return robotsAndRelativeLocations.get(robot);
    }

    public Map<Robot, Point> getRobots() {
        return robotsAndRelativeLocations;
    }

    public void improveSurfaceModel(Robot robot, Direction direction, LandscapeType landscapeType) {
        Point robotRelativeLocation = robotsAndRelativeLocations.get(robot);
        Point center = surfaceModel.getCenter();
        switch (direction) {
            case LEFT: {
                surfaceModel.getSurfaceModel()[center.x + robotRelativeLocation.x - 1][center.y + robotRelativeLocation.y] = landscapeType;
            }
            case RIGHT: {
                surfaceModel.getSurfaceModel()[center.x + robotRelativeLocation.x + 1][center.y + robotRelativeLocation.y] = landscapeType;
            }
            case FORWARD: {
                surfaceModel.getSurfaceModel()[center.x + robotRelativeLocation.x][center.y + robotRelativeLocation.y - 1] = landscapeType;
            }
            case BACKWARD: {
                surfaceModel.getSurfaceModel()[center.x + robotRelativeLocation.x][center.y + robotRelativeLocation.y + 1] = landscapeType;
            }
        }
    }

    public boolean verifyDirectionAndMove(Robot robot, Direction direction) {
        Point robotRelativeLocation = robotsAndRelativeLocations.get(robot);
        Point center = surfaceModel.getCenter();
        switch (direction) {
            case LEFT: {
                if (surfaceModel.getSurfaceModel()[center.x + robotRelativeLocation.x - 1][center.y + robotRelativeLocation.y].equals(LandscapeType.FREE)) {
                    robotRelativeLocation.translate(-1,0);
                    return true;
                }
            }
            case RIGHT: {
                if (surfaceModel.getSurfaceModel()[center.x + robotRelativeLocation.x + 1][center.y + robotRelativeLocation.y].equals(LandscapeType.FREE)) {
                    robotRelativeLocation.translate(1, 0);
                    return true;
                }
            }
            case FORWARD: {
                if (surfaceModel.getSurfaceModel()[center.x + robotRelativeLocation.x][center.y + robotRelativeLocation.y - 1].equals(LandscapeType.FREE)) {
                    robotRelativeLocation.translate(0, -1);
                    return true;
                }
            }
            case BACKWARD: {
                if (surfaceModel.getSurfaceModel()[center.x + robotRelativeLocation.x][center.y + robotRelativeLocation.y + 1].equals(LandscapeType.FREE)) {
                    robotRelativeLocation.translate(0, 1);
                    return true;
                }
            }
        }
        return false;
    }

    public String getId() {
        return id;
    }

    @Override
    void move(Direction direction) {
        throw new UnsupportedOperationException("Hub can't move yet");
    }

    @Override
    void interact() {
        throw new UnsupportedOperationException("Hub can't interact with anything yet");
    }
}
