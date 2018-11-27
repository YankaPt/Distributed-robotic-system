package model.robot;

import model.world.Direction;
import model.world.LandscapeType;

public class RobotPrototype extends Robot {
    private Hub hub;
    private boolean isFree  = true;

    public void checkAndMove(Direction direction) throws InterruptedException{
        isFree = false;
        Thread.sleep(1000);
        checkCell(direction);
        move(direction);
        isFree = true;
    }

    @Override
    public void move(Direction direction) {
        Boolean movingIsPossible = hub.verifyDirectionAndCommitMove(this, direction);
        if (movingIsPossible) {
            //move
        }
    }

    @Override
    public boolean interact() {
        if (hub.getRelativeLocationFor(this).equals(world.getRelativeTargetLocationFor(hub))) {
            return true;
        } else {
            return false;
        }
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

    public boolean isFree() {
        return isFree;
    }

    @Override
    public void run() {

    }
}
