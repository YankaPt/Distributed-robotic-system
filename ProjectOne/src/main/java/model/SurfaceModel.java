package model;

import java.awt.*;
import java.util.Arrays;

public class SurfaceModel {
    LandscapeType[][] surface;
    Point center;

    public SurfaceModel(int size) {
        surface = new LandscapeType[size][size];
        Arrays.stream(surface)
                .map((c) -> Arrays.stream(c)
                        .map((landscapeType) -> landscapeType = LandscapeType.UNKNOWN));
        center = new Point(size/2, size/2);
        surface[center.y][center.x] = LandscapeType.FREE;
    }
}


