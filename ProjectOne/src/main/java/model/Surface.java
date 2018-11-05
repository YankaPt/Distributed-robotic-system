package model;

public class Surface {
    LandscapeType[][] surface;

    public Surface() {
    }

    public Surface(int size) {
        surface = new LandscapeType[size][size];
    }

    public int getSize() {
        return surface.length;
    }

    public LandscapeType[][] getSurface() {
        return surface;
    }
}
