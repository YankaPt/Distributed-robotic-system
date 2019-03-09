public class BoidRequestToWorld extends BoidRequest {
    private TypesOfBoidRequestToWorld typeOfBoidRequestToWorld;
    private Directions direction;

    public BoidRequestToWorld(Boid source, TypesOfBoidRequestToWorld typeOfBoidRequestToWorld, Directions direction) {
        super(source);
        this.typeOfBoidRequestToWorld = typeOfBoidRequestToWorld;
        this.direction = direction;
    }
}
