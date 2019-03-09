import model.robot.Hub;
import model.robot.RobotPrototype;
import model.world.World;

import java.awt.*;

public class ProjectOneAsConsoleApplication {
    public static void main(String[] args) throws Exception{
        World world = new World("almostFlatSurface.txt");
        world.setTarget(new Point(1, 5));
        Hub hub = new Hub();
        hub.setWorld(world);
        RobotPrototype prototype = new RobotPrototype();
        prototype.setWorld(world);
        hub.addRobot(prototype);
        world.registerHub(hub, new Point(1,1));
        hub.runStrategies();
        /*int count = 0;
        boolean isComplete = false;
        for (int i = 0; i < 1000; i++) {
            world.clear();
            world.setSurface(MapGenerator.generateNewSurface(6));
            world.setTarget(new Point(1, 5));
            hub = new Hub();
            hub.setWorld(world);
            prototype = new RobotPrototype();
            prototype.setWorld(world);
            hub.addRobot(prototype);
            world.registerHub(hub, new Point(1,1));
            try {
                isComplete = hub.runStrategies();
            } catch (ArrayIndexOutOfBoundsException ex) {
            }
            if (isComplete) {
                count++;
            }
            System.out.println(isComplete);
        }
        System.out.println(count);*/
    }
}
