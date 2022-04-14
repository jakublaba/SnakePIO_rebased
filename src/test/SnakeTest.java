package snakegame.Tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import snakegame.PointVector;
import snakegame.Snake;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class SnakeTest {
    static Snake sampleSnake;
    @BeforeAll
    public static void prepareSnake() {
        List<PointVector> prepared = new ArrayList<>();
        prepared.add(new PointVector(1, 1));
        sampleSnake = new Snake(prepared);
    }
    @Test
    public void checkMove () {
        //w tym teście tworzone są dwie sztuczne pozycje myszy, które względem głowy snake'a leżą na tej samej prostej - obie pozycje powinny zaskutkować identycznym ruchem węża
        Snake snakeCopy1 = sampleSnake;
        Snake snakeCopy2 = sampleSnake;
        PointVector mousePos1 = new PointVector(2, 2);
        PointVector mousePos2 = new PointVector(100, 100);
        snakeCopy1.move(mousePos1);
        snakeCopy2.move(mousePos2);
        assertEquals(snakeCopy1.getHead(), snakeCopy2.getHead());
    }
}
