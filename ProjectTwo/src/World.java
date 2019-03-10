import java.awt.*;
import java.util.*;

public class World {
    private static final String SUCCESSFUL_MOVEMENT = "successful movement";
    private static final String MOVEMENT_FAIL_BY_OBSTACLE = "movement failed by obstacle";
    private static final String MOVEMENT_FAIL_BY_NOT_ENOUGH_ENERGY = "not enough energy of movement on difficult terrain";
    private static final String MOVEMENT_FAIL_WITH_DAMAGE = "TODO";
    private SurfaceModel surfaceModel;
    private Map<Boid, Point> boid2locationMap = new HashMap<>();
    private Queue<BoidRequestToWorld> requestsToWorld = new ArrayDeque<>();
    private Queue<BoidCommunicationRequest> communicationRequests = new ArrayDeque<>();

    public World(SurfaceModel surfaceModel) {
        this.surfaceModel = surfaceModel;
    }

    //main method
    public void nextMove() throws InterruptedException {
        processRequestsToWorld();
        processCommunicationRequests();
        //TODO: calculate probabilities of events and perform it
        boid2locationMap.forEach((boid, location) -> boid.setActive(true));
        Thread.sleep(1000);
    }

    private void processRequestsToWorld() {
        while (!requestsToWorld.isEmpty()) {
            BoidRequestToWorld boidRequest = requestsToWorld.poll();
            if (boidRequest.getTypeOfBoidRequestToWorld().equals(TypesOfBoidRequestToWorld.REQUEST_TO_MOVE)) {
                String result = performMove(boidRequest.getSource(), boidRequest.getDirection());
                //TODO: replace with normal event
                if (!result.equals(SUCCESSFUL_MOVEMENT)) {
                    System.out.println(result);
                }
            }
        }
    }

    private String performMove(Boid boid, Directions direction) {
        Integer semiSize = boid.getSemiSize();
        Point boidLocation = boid2locationMap.get(boid);
        Double difficultOfMove = 0.0;

        switch (direction) {
            case UP: {
                for (int i = boidLocation.x - semiSize + 1; i <= boidLocation.x + semiSize - 1; i++) {
                    if (surfaceModel.surface[boidLocation.y - 1][i].getSurfaceObject() != null) {
                        return MOVEMENT_FAIL_BY_OBSTACLE;
                    }
                    difficultOfMove += surfaceModel.surface[boidLocation.y - 1][i].getDifficultOfTerrain();
                }
                if (difficultOfMove > boid.getEnergyOfMovement()) {
                    return MOVEMENT_FAIL_BY_NOT_ENOUGH_ENERGY;
                }
                boid.setEnergyOfMovement((int) Math.round(boid.getEnergyOfMovement() - difficultOfMove));
                boidLocation.y--;
                return SUCCESSFUL_MOVEMENT;
            }
            case DOWN: {
                for (int i = boidLocation.x - semiSize + 1; i <= boidLocation.x + semiSize - 1; i++) {
                    if (surfaceModel.surface[boidLocation.y + 1][i].getSurfaceObject() != null) {
                        return MOVEMENT_FAIL_BY_OBSTACLE;
                    }
                    difficultOfMove += surfaceModel.surface[boidLocation.y + 1][i].getDifficultOfTerrain();
                }
                if (difficultOfMove > boid.getEnergyOfMovement()) {
                    return MOVEMENT_FAIL_BY_NOT_ENOUGH_ENERGY;
                }
                boid.setEnergyOfMovement((int) Math.round(boid.getEnergyOfMovement() - difficultOfMove));
                boidLocation.y++;
                return SUCCESSFUL_MOVEMENT;
            }
            case LEFT: {
                for (int i = boidLocation.y - semiSize + 1; i <= boidLocation.y + semiSize - 1; i++) {
                    if (surfaceModel.surface[i][boidLocation.x - 1].getSurfaceObject() != null) {
                        return MOVEMENT_FAIL_BY_OBSTACLE;
                    }
                    difficultOfMove += surfaceModel.surface[i][boidLocation.x - 1].getDifficultOfTerrain();
                }
                if (difficultOfMove > boid.getEnergyOfMovement()) {
                    return MOVEMENT_FAIL_BY_NOT_ENOUGH_ENERGY;
                }
                boid.setEnergyOfMovement((int) Math.round(boid.getEnergyOfMovement() - difficultOfMove));
                boidLocation.x--;
                return SUCCESSFUL_MOVEMENT;
            }
            case RIGHT: {
                for (int i = boidLocation.y - semiSize + 1; i <= boidLocation.y + semiSize - 1; i++) {
                    if (surfaceModel.surface[i][boidLocation.x + 1].getSurfaceObject() != null) {
                        return MOVEMENT_FAIL_BY_OBSTACLE;
                    }
                    difficultOfMove += surfaceModel.surface[i][boidLocation.x + 1].getDifficultOfTerrain();
                }
                if (difficultOfMove > boid.getEnergyOfMovement()) {
                    return MOVEMENT_FAIL_BY_NOT_ENOUGH_ENERGY;
                }
                boid.setEnergyOfMovement((int) Math.round(boid.getEnergyOfMovement() - difficultOfMove));
                boidLocation.x++;
                return SUCCESSFUL_MOVEMENT;
            }
        }
        throw new MoveException("Undermined trouble with move");
    }

    private void processCommunicationRequests() {
        while (!communicationRequests.isEmpty()) {
            BoidCommunicationRequest boidRequest = communicationRequests.poll();
            //TODO implement this branch
        }
    }

    public SurfaceModel getSurfaceModel() {
        return surfaceModel;
    }

    public void setSurfaceModel(SurfaceModel surfaceModel) {
        this.surfaceModel = surfaceModel;
    }

    public Map<Boid, Point> getBoid2locationMap() {
        return boid2locationMap;
    }

    public void setBoid2locationMap(Map<Boid, Point> boid2locationMap) {
        this.boid2locationMap = boid2locationMap;
    }

    public Boid createBoidAndGet(Point location) {
        if (location.x <0 || location.y <0) {
            throw new ArithmeticException("wrong bound of location");
        }
        Boid newBoid = new Boid(this);
        newBoid.setActive(true);
        boid2locationMap.put(newBoid, location);
        return newBoid;
    }

    public Queue<BoidRequestToWorld> getRequestsToWorld() {
        return requestsToWorld;
    }

    public void setRequestsToWorld(Queue<BoidRequestToWorld> requestsToWorld) {
        this.requestsToWorld = requestsToWorld;
    }

    public Queue<BoidCommunicationRequest> getCommunicationRequests() {
        return communicationRequests;
    }

    public void setCommunicationRequests(Queue<BoidCommunicationRequest> communicationRequests) {
        this.communicationRequests = communicationRequests;
    }
}
