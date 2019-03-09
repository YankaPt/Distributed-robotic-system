public class SurfaceCell {
    private double passability;
    SurfaceObject surfaceObject;

    public SurfaceCell() {
    }

    public SurfaceCell(double passability) {
        this.passability = passability;
    }

    public SurfaceCell(double passability, SurfaceObject surfaceObject) {
        this.passability = passability;
        this.surfaceObject = surfaceObject;
    }

    public double getPassability() {
        return passability;
    }

    public void setPassability(double passability) {
        this.passability = passability;
    }

    public SurfaceObject getSurfaceObject() {
        return surfaceObject;
    }

    public void setSurfaceObject(SurfaceObject surfaceObject) {
        this.surfaceObject = surfaceObject;
    }
}
