import model.world.LandscapeType;
import model.world.Surface;

public class MapGenerator {
    public static Surface generateNewSurface(int size) {
        Surface surface = new Surface(size);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                surface.getSurface()[i][j] = generateCell();
            }
        }
        return surface;
    }

    private static LandscapeType generateCell() {
        double randomValue = Math.random();
        return randomValue > 0.8 ? LandscapeType.FREE : LandscapeType.OBST;
    }
}
