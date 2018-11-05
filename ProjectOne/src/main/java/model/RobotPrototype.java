package model;

import java.awt.*;

public class RobotPrototype extends Robot {
    private Hub hub;

    @Override
    void move(Direction direction) {
        Boolean movingIsPossible = hub.verifyDirectionAndMove(this, direction);
        if (movingIsPossible) {
            //move
        }
    }

    @Override
    void interact() {
        //interact
    }

    public Hub getHub() {
        return hub;
    }

    public void setHub(Hub hub) {
        this.hub = hub;
    }

    public void checkCell(Direction direction) {
        LandscapeType landscapeType = this.world.checkCell(this, direction);
        hub.improveSurfaceModel(this, direction, landscapeType);
    }

    public void moveToTarget() {
        Point targetLocation = world.getRelativeTargetLocation(hub);
        //TODO: moveToTarget
    }
}
