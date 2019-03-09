package model;

import model.world.LandscapeType;
import model.world.SurfaceModel;
import org.junit.Test;

import static org.junit.Assert.*;

public class SurfaceModelTest {
    private SurfaceModel surfaceModel;
    private static final int SAMPLE_SIZE = 10;

    @Test
    public void surfaceModelConstructor() {
        LandscapeType[][] sampleModel = new LandscapeType[SAMPLE_SIZE][SAMPLE_SIZE];
        for (int i = 0; i < SAMPLE_SIZE; i++) {
            for (int j = 0; j < SAMPLE_SIZE; j++) {
                sampleModel[i][j] = LandscapeType.UNKN;
            }
        }
        sampleModel[SAMPLE_SIZE/2][SAMPLE_SIZE/2] = LandscapeType.FREE;
        surfaceModel = new SurfaceModel(SAMPLE_SIZE);
        assertArrayEquals(sampleModel, surfaceModel.getSurfaceModel());
    }
}