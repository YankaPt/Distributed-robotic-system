package strategies;

import model.robot.Hub;
import model.robot.Robot;
import model.robot.RobotPrototype;
import model.world.Direction;
import model.world.LandscapeType;

import java.awt.*;

public class OneHandStrategy implements SimpleStrategy {
    Hub hub;
    private boolean isLeft = false;

    public OneHandStrategy(Hub hub) {
        this.hub = hub;
    }

    @Override
    public boolean runStrategy() throws InterruptedException {
       /* RobotPrototype prototype = (RobotPrototype) hub.getRobots().keySet().stream().findFirst().get();
        long t1 = System.nanoTime();
        long t2 = System.nanoTime();
        while (t2 - t1 < 50000000L) {
            while (!prototype.isFree()) {
                wait();
            }
            prototype.checkAndMove(chooseDirectionFor(prototype));
            if (prototype.interact()) {
                //System.out.println("Target is reached!");
                return true;
            }
            t2 = System.nanoTime();
        }*/
        return false;
    }
/*
    private Direction chooseDirectionFor(Robot robot) {
        Point robotLocation = hub.getRelativeLocationFor(robot);
        Point targetLocation = robot.getWorld().getRelativeTargetLocationFor(hub);
        int dx = targetLocation.x - robotLocation.x;
        int dy = targetLocation.y - robotLocation.y;
        if (Math.abs(dx) >= Math.abs(dy)) {
            if (dx >= 0) {
                if (robot.getWorld().checkCell(robot, Direction.RIGHT).equals(LandscapeType.FREE)) {
                    return Direction.RIGHT;
                }
            } else {
                return robot.getWorld().checkCell(robot, Direction.LEFT).equals(LandscapeType.FREE) ? Direction.LEFT ;
            }
        } else if (dy >= 0) {
            return robot.getWorld().checkCell(robot, Direction.BACKWARD).equals(LandscapeType.FREE) ? Direction.BACKWARD;
        } else {
            return robot.getWorld().checkCell(robot, Direction.FORWARD).equals(LandscapeType.FREE) ? Direction.FORWARD : p>0.5 ? Direction.RIGHT : Direction.LEFT;
        }
    }*/
}
