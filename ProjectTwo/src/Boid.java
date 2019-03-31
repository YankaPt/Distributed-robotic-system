import java.awt.*;

public class Boid {
    private World world;
    //TODO: set id
    private Long id;
    private Integer semiSize = 2; //Total size == 2*semiSize - 1
    private Integer powerOfAntenna = 100;
    private Integer distanceOfView = 2; //Must be >= semiSize
    private Integer velocity = 1;
    private boolean isActive = true;
    private Integer energyOfMovement = 0;
    private SurfaceModel internalMap;
    private Point place;
    private Integer capacity = 1;

    public Boid(World world) {
        this.world = world;
        //this.internalMap = generateEmptyMap();
        this.internalMap = world.getSurfaceModel(); //for simplicity
        this.place = world.getBoid2locationMap().get(this);
    }

    private SurfaceModel generateEmptyMap() {
        SurfaceCell[][] surfaceCells = new SurfaceCell[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                surfaceCells[i][j] = new SurfaceCell(0.0);
                surfaceCells[i][j].setSurfaceObject(new SurfaceObject("Unknown"));
            }
        }
        return new SurfaceModel(surfaceCells);
    }

    public void move(Directions direction) {
        if (isActive) {
            world.getRequestsToWorld().add(new BoidRequestToWorld(this, TypesOfBoidRequestToWorld.REQUEST_TO_MOVE, direction));
            isActive = false;
        }
    }

    public void lookAround() {
        internalMap = world.getSurfaceModel();
        place = world.getBoid2locationMap().get(this);
        //TODO: with distance of View boid can update internal map
    }

    public void performAction(Directions direction) {
        if (isActive) {
            world.getRequestsToWorld().add(new BoidRequestToWorld(this, TypesOfBoidRequestToWorld.REQUEST_TO_ACTION, direction));
            isActive = false;
        }
    }

    public void runSimpleStrategy() {
        lookAround();
        Point target = getTarget();

    }

    private Point getTarget() {
        Point optimalTarget = null;
        //TODO: find optimal, not last
        for (int k = 1; k < internalMap.surface.length; k++) {
            for (int i = place.y - k; i <= place.y + k; i++) {
                if ("Resource".equals(internalMap.surface[i][place.x - k].getSurfaceObject().getType())) {
                    optimalTarget = new Point(place.x - k, i);
                } else if ("Resource".equals(internalMap.surface[i][place.x + k].getSurfaceObject().getType())) {
                    optimalTarget = new Point(place.x + k, i);
                }
            }
            for (int j = place.x - k; j <= place.x + k; j++) {
                if ("Resource".equals(internalMap.surface[place.y - k][j].getSurfaceObject().getType())) {
                    optimalTarget = new Point(j, place.y - k);
                } else if ("Resource".equals(internalMap.surface[place.y + k][j].getSurfaceObject().getType())) {
                    optimalTarget = new Point(j, place.y + k);
                }
            }
        }
        return optimalTarget;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSemiSize() {
        return semiSize;
    }

    public void setSemiSize(Integer semiSize) {
        this.semiSize = semiSize;
    }

    public Integer getPowerOfAntenna() {
        return powerOfAntenna;
    }

    public void setPowerOfAntenna(Integer powerOfAntenna) {
        this.powerOfAntenna = powerOfAntenna;
    }

    public Integer getVelocity() {
        return velocity;
    }

    public void setVelocity(Integer velocity) {
        this.velocity = velocity;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
        if (active) {
            energyOfMovement += velocity;
            //TODO: secure this - avoid multiple boid activation
        }
    }

    public Integer getDistanceOfView() {
        return distanceOfView;
    }

    public void setDistanceOfView(Integer distanceOfView) {
        this.distanceOfView = distanceOfView;
    }

    public Integer getEnergyOfMovement() {
        return energyOfMovement;
    }

    public void setEnergyOfMovement(Integer energyOfMovement) {
        this.energyOfMovement = energyOfMovement;
    }
}
