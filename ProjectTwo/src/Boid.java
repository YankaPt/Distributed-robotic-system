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

    public Boid(World world) {
        this.world = world;
    }

    public void move(Directions direction) {
        if (isActive) {
            world.getRequestsToWorld().add(new BoidRequestToWorld(this, TypesOfBoidRequestToWorld.REQUEST_TO_MOVE, direction));
            isActive = false;
        }
    }

    public void lookAround() {
        //TODO: with distance of View boid can update internal map
    }

    public void performAction(Directions direction) {
        if (isActive) {
            world.getRequestsToWorld().add(new BoidRequestToWorld(this, TypesOfBoidRequestToWorld.REQUEST_TO_ACTION, direction));
            isActive = false;
        }
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
            energyOfMovement+=velocity;
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
