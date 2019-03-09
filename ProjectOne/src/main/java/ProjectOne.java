import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.robot.Hub;
import model.robot.RobotPrototype;
import model.world.World;
import strategies.CustomStrategy;
import strategies.Strategy;

import java.awt.*;

public class ProjectOne extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        GridPane root = new GridPane();
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 720, 700));
        primaryStage.show();
        World world = new World("surface.txt");
        world.setTarget(new Point(5, 5));
        //repaintField(world, root);
        Button targetButton = new Button();
        targetButton.setStyle("-fx-background-color: #e03e2f; ");
        targetButton.setMinSize(120, 115);
        root.add(targetButton, world.getTarget().y, world.getTarget().x);
        Hub hub = new Hub();
        hub.setWorld(world);
        RobotPrototype prototype = new RobotPrototype();
        prototype.setWorld(world);
        hub.addRobot(prototype);
        world.registerHub(hub, new Point(1,1));
        Strategy strategy = new CustomStrategy(hub);
        strategy.runStrategy();
        repaintField(world, root);
    }


    public static void main(String[] args) {
        launch(args);
    }

    private void repaintField(World world, GridPane gridPane) {
        for (int i = 0; i < world.getSurface().getSize(); i++) {
            for (int j = 0; j < world.getSurface().getSize(); j++) {
                Button button = new Button();
                switch (world.getSurface().getSurface()[i][j]) {
                    case UNKN:
                        button.setStyle("-fx-background-color: rgba(74,71,92,0.49); ");
                        break;
                    case FREE:
                        button.setStyle("-fx-background-color: #ffffff; ");
                        break;
                    case OBST:
                        button.setStyle("-fx-background-color: #000000; ");
                        break;
                }
                button.setMinSize(120, 115);
                button.setDisable(true);
                gridPane.add(button, j, i);
            }
        }
        Button button = new Button();
        button.setStyle("-fx-background-color: #475bff; ");
        button.setMinSize(120, 115);
        button.setDisable(true);
        world.getRobotsAndLocations().forEach(((robot, point) -> gridPane.add(button, point.x, point.y)));
        Button hubButton = new Button("Hub");
        hubButton.setStyle("-fx-background-color: #38bf25; ");
        hubButton.setMinSize(120, 115);
        gridPane.add(hubButton, 1, 1);
    }
}
