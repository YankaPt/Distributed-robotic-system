package strategies;

import model.robot.Hub;
import model.robot.Robot;
import model.robot.RobotPrototype;
import model.world.Direction;
import model.world.LandscapeType;

import java.awt.*;

public class RunIntoAndTurnStrategy implements SimpleStrategy {
    private Hub hub;

    public RunIntoAndTurnStrategy(Hub hub) {
        this.hub = hub;
    }

    @Override
    public void runStrategy() throws InterruptedException {
        RobotPrototype prototype = (RobotPrototype) hub.getRobots().keySet().stream().findFirst().get();
        while (true) {
            while (!prototype.isFree()) {
                wait();
            }
            prototype.checkAndMove(chooseDirectionFor(prototype));
            if (prototype.interact()) {
                System.out.println("Target is reached!");
                return;
            }
        }
    }

    private Direction chooseDirectionFor(Robot robot) {
        Point robotLocation = hub.getRelativeLocationFor(robot);
        Point targetLocation = robot.getWorld().getRelativeTargetLocationFor(hub);
        int dx = targetLocation.x - robotLocation.x;
        int dy = targetLocation.y - robotLocation.y;
        if (Math.abs(dx) >= Math.abs(dy)) {
            if (dx >= 0) {
                return robot.getWorld().checkCell(robot, Direction.RIGHT).equals(LandscapeType.FREE) ? Direction.RIGHT : Direction.FORWARD;
            } else {
                return robot.getWorld().checkCell(robot, Direction.LEFT).equals(LandscapeType.FREE) ? Direction.LEFT : Direction.FORWARD;
            }
        } else if (dy >= 0) {
            return robot.getWorld().checkCell(robot, Direction.BACKWARD).equals(LandscapeType.FREE) ? Direction.BACKWARD : Direction.RIGHT;
        } else {
            return robot.getWorld().checkCell(robot, Direction.FORWARD).equals(LandscapeType.FREE) ? Direction.FORWARD : Direction.RIGHT;
        }
        //return Direction.BACKWARD;
    }
}
