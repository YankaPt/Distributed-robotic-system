package surface;

public class SurfaceGenerator {

    public static SurfaceModel generateEmptySurface(int size) {
        SurfaceCell[][] surfaceCells = new SurfaceCell[size][size];
        for (int i = 0; i< size; i++) {
            for (int j = 0; j< size; j++) {
                surfaceCells[i][j] = new SurfaceCell(0.0);
            }
        }
        return new SurfaceModel(surfaceCells);
    }
}
