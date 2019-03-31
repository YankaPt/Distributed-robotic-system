import java.awt.*;
import java.util.Map;

public class ProjectTwo {
    public static void main(String... args) throws InterruptedException {
        ProjectTwo projectTwo = new ProjectTwo();
        int counter;
        SurfaceModel surfaceModel = SurfaceGenerator.generateEmptySurface(18);
        World world = new World(surfaceModel);
        Boid firstBoid = world.createBoidAndGet(new Point(5,5));
        Boid secondBoid = world.createBoidAndGet(new Point(9, 8));
        projectTwo.showMap(world);
        firstBoid.move(Directions.DOWN);
        world.nextMove();
        projectTwo.showMap(world);
        firstBoid.move(Directions.DOWN);
        world.nextMove();
        projectTwo.showMap(world);
        firstBoid.move(Directions.RIGHT);
        world.nextMove();
        projectTwo.showMap(world);
        firstBoid.move(Directions.DOWN);
        world.nextMove();
        projectTwo.showMap(world);
        firstBoid.move(Directions.DOWN);
        world.nextMove();
        projectTwo.showMap(world);
    }

    private void showMap(World world) {
        //TODO: refactor this shit
        SurfaceModel surfaceModel = world.getSurfaceModel();
        int sizeOfMap = surfaceModel.surface.length;
        SurfaceCellModel[][] surfaceCellModels = new SurfaceCellModel[sizeOfMap][sizeOfMap];
        for (int i = 0; i< sizeOfMap; i++) {
            for (int j = 0; j < sizeOfMap; j++) {
                SurfaceCell surfaceCell = surfaceModel.surface[i][j];
                final Point currentPoint = new Point(j, i);
                Boid boidOnCurrentCell = world.getBoid2locationMap()
                        .entrySet()
                        .stream()
                        .filter(entry -> entry.getValue().equals(currentPoint))
                        .findAny().map(Map.Entry::getKey).orElse(null);
                surfaceCellModels[i][j] = new SurfaceCellModel(surfaceCell.getDifficultOfTerrain(), surfaceCell.getSurfaceObject(), boidOnCurrentCell);
            }
        }
        for (SurfaceCellModel[] row : surfaceCellModels) {
            for (SurfaceCellModel cell : row) {
                System.out.print(cell);
            }
            System.out.println("\n");
        }
        System.out.println("_____________________________________________");
    }

    private class SurfaceCellModel {
        //TODO replace this
        Double difficultOfTerrain;
        Boid boid;
        SurfaceObject surfaceObject;

        public SurfaceCellModel(Double difficultOfTerrain, SurfaceObject surfaceObject, Boid boid) {
            this.difficultOfTerrain = difficultOfTerrain;
            this.boid = boid;
            this.surfaceObject = surfaceObject;
        }

        @Override
        public String toString() {
            //TODO replace this
            String view=difficultOfTerrain.toString() + " ";
            if (surfaceObject != null) {
                view = view + surfaceObject.toString() + "   ";
                return view;
            }
            /*if (boid != null) {
                view = view + "B   ";
                return view;
            }*/
            return view + "    ";
        }
    }
}
