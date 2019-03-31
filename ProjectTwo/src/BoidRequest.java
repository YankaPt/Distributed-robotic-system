public abstract class BoidRequest {
    private Boid source;

    public BoidRequest(Boid source) {
        this.source = source;
    }

    public Boid getSource() {
        return source;
    }
}
