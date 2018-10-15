package model;

import java.util.HashSet;
import java.util.Set;

public class CoordinateCenter {
    private Set<Robot> robots;
    private String id;
    private SurfaceModel surfaceModel;

    public CoordinateCenter() {
        robots = new HashSet<>();
        surfaceModel = new SurfaceModel(10);
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
}
