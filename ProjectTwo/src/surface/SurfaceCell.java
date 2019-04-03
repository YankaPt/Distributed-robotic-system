package surface;

public class SurfaceCell {
    private double difficultOfTerrain;
    SurfaceObject surfaceObject;

    public SurfaceCell() {
    }

    public SurfaceCell(double difficultOfTerrain) {
        this.difficultOfTerrain = difficultOfTerrain;
    }

    public SurfaceCell(double difficultOfTerrain, SurfaceObject surfaceObject) {
        this.difficultOfTerrain = difficultOfTerrain;
        this.surfaceObject = surfaceObject;
    }

    public double getDifficultOfTerrain() {
        return difficultOfTerrain;
    }

    public void setDifficultOfTerrain(double difficultOfTerrain) {
        this.difficultOfTerrain = difficultOfTerrain;
    }

    public SurfaceObject getSurfaceObject() {
        return surfaceObject;
    }

    public void setSurfaceObject(SurfaceObject surfaceObject) {
        this.surfaceObject = surfaceObject;
    }
}
