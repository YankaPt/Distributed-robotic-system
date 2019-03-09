package model.robot;

import model.world.Direction;
import model.world.LandscapeType;
import model.world.SurfaceModel;
import strategies.CustomStrategy;
import strategies.RunIntoAndTurnStrategy;
import strategies.Strategy;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Hub extends Robot {
    private Map<Robot, Point> robotsAndRelativeLocations;
    private SurfaceModel surfaceModel;
    private static final int INITIAL_SURFACE_MODEL_SIZE = 11;

    public Hub() {
        robotsAndRelativeLocations = new HashMap<>();
        surfaceModel = new SurfaceModel(INITIAL_SURFACE_MODEL_SIZE);
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

    public boolean runStrategies() throws InterruptedException{
        Strategy strategy = new RunIntoAndTurnStrategy(this);
    /*    if (world.getFlatness() >= 0.5) {
            strategy = new RunIntoAndTurnStrategy(this);
        }*/
        return strategy.runStrategy();
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
                surfaceModel.getSurfaceModel()[center.y + robotRelativeLocation.y][center.x + robotRelativeLocation.x - 1] = landscapeType;
                break;
            }
            case RIGHT: {
                surfaceModel.getSurfaceModel()[center.y + robotRelativeLocation.y][center.x + robotRelativeLocation.x + 1] = landscapeType;
                break;
            }
            case FORWARD: {
                surfaceModel.getSurfaceModel()[center.y + robotRelativeLocation.y - 1][center.x + robotRelativeLocation.x] = landscapeType;
                break;
            }
            case BACKWARD: {
                surfaceModel.getSurfaceModel()[center.y + robotRelativeLocation.y + 1][center.x + robotRelativeLocation.x] = landscapeType;
                break;
            }
        }
        world.showHubMap(this);
    }

    public boolean verifyDirectionAndCommitMove(Robot robot, Direction direction) {
        Point robotRelativeLocation = robotsAndRelativeLocations.get(robot);
        Point center = surfaceModel.getCenter();
        switch (direction) {
            case LEFT: {
                if (surfaceModel.getSurfaceModel()[center.y + robotRelativeLocation.y][center.x + robotRelativeLocation.x - 1].equals(LandscapeType.FREE)) {
                    robotRelativeLocation.translate(-1,0);
                    world.updateRobotLocation(robot, this);
                    return true;
                }
                break;
            }
            case RIGHT: {
                if (surfaceModel.getSurfaceModel()[center.y + robotRelativeLocation.y][center.x + robotRelativeLocation.x + 1].equals(LandscapeType.FREE)) {
                    robotRelativeLocation.translate(1, 0);
                    world.updateRobotLocation(robot, this);
                    return true;
                }
                break;
            }
            case FORWARD: {
                if (surfaceModel.getSurfaceModel()[center.y + robotRelativeLocation.y - 1][center.x + robotRelativeLocation.x].equals(LandscapeType.FREE)) {
                    robotRelativeLocation.translate(0, -1);
                    world.updateRobotLocation(robot, this);
                    return true;
                }
                break;
            }
            case BACKWARD: {
                if (surfaceModel.getSurfaceModel()[center.y + robotRelativeLocation.y + 1][center.x + robotRelativeLocation.x].equals(LandscapeType.FREE)) {
                    robotRelativeLocation.translate(0, 1);
                    world.updateRobotLocation(robot, this);
                    return true;
                }
                break;
            }
        }
        return false;
    }

    @Override
    public void move(Direction direction) {
        throw new UnsupportedOperationException("Hub can't move yet");
    }

    @Override
    public boolean interact() {
        throw new UnsupportedOperationException("Hub can't interact with anything yet");
    }

    @Override
    public void run() {

    }
}
