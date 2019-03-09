package strategies;

import model.world.Direction;
import model.robot.Hub;
import model.robot.RobotPrototype;

import java.util.Arrays;
import java.util.List;

public class CustomStrategy implements SimpleStrategy {
    Hub hub;
    private List<Direction> directionList = Arrays.asList(Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT,
            Direction.BACKWARD, Direction.BACKWARD, Direction.LEFT, Direction.LEFT, Direction.LEFT, Direction.LEFT, Direction.LEFT,
            Direction.BACKWARD, Direction.BACKWARD, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT);

    public CustomStrategy(Hub hub) {
        this.hub = hub;
    }

    @Override
    public synchronized boolean runStrategy() throws  InterruptedException{
        RobotPrototype prototype = (RobotPrototype) hub.getRobots().keySet().stream().findFirst().get();
        for (Direction direction : directionList) {
            while (!prototype.isFree()) {
                wait();
            }
            prototype.checkAndMove(direction);
        }
        if (prototype.interact()) {
            System.out.println("Target is reached");
            return true;
        }
        return false;
    }
}
