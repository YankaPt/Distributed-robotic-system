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
    public boolean runStrategy() throws InterruptedException {
        RobotPrototype prototype = (RobotPrototype) hub.getRobots().keySet().stream().findFirst().get();
        long t1 = System.nanoTime();
        long t2 = System.nanoTime();
        while (true/*t2 - t1 < 50000000L*/) {
            while (!prototype.isFree()) {
                wait();
            }
            prototype.checkAndMove(chooseDirectionFor(prototype));
            if (prototype.interact()) {
                System.out.println("Target is reached!");
                return true;
            }
            t2 = System.nanoTime();
        }
        //return false;
    }

    private Direction chooseDirectionFor(RobotPrototype robot) {
        double p = Math.random();
        Point robotLocation = hub.getRelativeLocationFor(robot);
        Point targetLocation = robot.getWorld().getRelativeTargetLocationFor(hub);
        int dx = targetLocation.x - robotLocation.x;
        int dy = targetLocation.y - robotLocation.y;
        if (Math.abs(dx) >= Math.abs(dy)) {
            if (dx >= 0) {
                robot.checkCell(Direction.RIGHT);
                return robot.getWorld().checkCell(robot, Direction.RIGHT).equals(LandscapeType.FREE) ? Direction.RIGHT : p>0.5 ? Direction.FORWARD : Direction.BACKWARD;
            } else {
                robot.checkCell(Direction.LEFT);
                return robot.getWorld().checkCell(robot, Direction.LEFT).equals(LandscapeType.FREE) ? Direction.LEFT : p>0.5 ? Direction.FORWARD : Direction.BACKWARD;
            }
        } else if (dy >= 0) {
            robot.checkCell(Direction.BACKWARD);
            return robot.getWorld().checkCell(robot, Direction.BACKWARD).equals(LandscapeType.FREE) ? Direction.BACKWARD : p>0.5 ? Direction.RIGHT : Direction.LEFT;
        } else {
            robot.checkCell(Direction.FORWARD);
            return robot.getWorld().checkCell(robot, Direction.FORWARD).equals(LandscapeType.FREE) ? Direction.FORWARD : p>0.5 ? Direction.RIGHT : Direction.LEFT;
        }
    }
}
