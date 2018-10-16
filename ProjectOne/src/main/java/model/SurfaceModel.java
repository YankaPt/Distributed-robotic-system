package model;

import java.awt.*;
import java.util.Arrays;

public class SurfaceModel {
    private LandscapeType[][] surface;
    private Point center;

    public SurfaceModel(int size) {
        surface = new LandscapeType[size][size];
        Arrays.stream(surface)
                .forEach((c) -> Arrays.fill(c, LandscapeType.UNKNOWN));
        center = new Point(size/2, size/2);
        surface[center.y][center.x] = LandscapeType.FREE;
    }

    public LandscapeType[][] getSurface() {
        return surface;
    }

    public Point getCenter() {
        return center;
    }
}


