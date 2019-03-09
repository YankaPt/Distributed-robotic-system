import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class World {
    private SurfaceModel surfaceModel;
    private Set<Boid> boidSet = new HashSet<>();
    private Queue<BoidRequestToWorld> requestsToWorld = new ArrayDeque<>();
    private Queue<BoidCommunicationRequest> communicationRequests = new ArrayDeque<>();

    public World(SurfaceModel surfaceModel) {
        this.surfaceModel = surfaceModel;
    }

    //main method
    public void nextMove() throws InterruptedException{
        processRequestsToWorld();
        processCommunicationRequests();
        //TODO: calculate probabilities of events and perform it
        Thread.sleep(1000);
    }

    private void processRequestsToWorld() {
        while (!requestsToWorld.isEmpty()) {
            BoidRequestToWorld boidRequest = requestsToWorld.poll();

        }
    }

    private void processCommunicationRequests() {
        while (!communicationRequests.isEmpty()) {
            BoidCommunicationRequest boidRequest = communicationRequests.poll();
        }
    }

    public SurfaceModel getSurfaceModel() {
        return surfaceModel;
    }

    public void setSurfaceModel(SurfaceModel surfaceModel) {
        this.surfaceModel = surfaceModel;
    }

    public Set<Boid> getBoidSet() {
        return boidSet;
    }

    public void setBoidSet(Set<Boid> boidSet) {
        this.boidSet = boidSet;
    }
}
