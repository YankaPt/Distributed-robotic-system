package model;

import java.util.HashSet;
import java.util.Set;

public class CoordinateCenter extends Robot {
    private Set<Robot> robots;
    private SurfaceModel surfaceModel;
    private static final int INITIAL_SURFACE_MODEL_SIZE = 10;

    public CoordinateCenter() {
        robots = new HashSet<>();
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
        robots.add(robot);
    }

    public void removeRobot(Robot robot) {
        robots.remove(robot);
    }

    public Set<Robot> getRobots() {
        return robots;
    }

    public String getId() {
        return id;
    }

    @Override
    void move(Direction direction) {

    }

    @Override
    void interact() {

    }
}
