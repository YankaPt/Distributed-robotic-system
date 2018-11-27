package strategies;

import model.robot.Hub;
import model.robot.RobotPrototype;

public class OneHandStrategy implements SimpleStrategy {
    Hub hub;

    public OneHandStrategy(Hub hub) {
        this.hub = hub;
    }

    @Override
    public void runStrategy() throws InterruptedException {

    }
}
