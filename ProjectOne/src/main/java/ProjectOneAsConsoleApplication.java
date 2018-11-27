import model.robot.Hub;
import model.robot.RobotPrototype;
import model.world.World;
import strategies.RunIntoAndTurnStrategy;
import strategies.Strategy;

import java.awt.*;

public class ProjectOneAsConsoleApplication {
    public static void main(String[] args) throws Exception{
        World world = new World("surface.txt");
        world.setTarget(new Point(5, 5));
        Hub hub = new Hub();
        hub.setWorld(world);
        RobotPrototype prototype = new RobotPrototype();
        prototype.setWorld(world);
        hub.addRobot(prototype);
        world.registerHub(hub, new Point(1,1));
        hub.runStrategies();
    }
}
