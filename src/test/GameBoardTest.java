package snakegame.Tests;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import snakegame.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

public class GameBoardTest {

    public static GameBoard gameBoard;
    
    @BeforeAll
    public static void setUp(){
        gameBoard = new GameBoard(true);
    }

    @Test
    public void checkFood_HalfValueOfRadius_true(){
        //given
        double distance = Math.sqrt(GameSettings.SEGMENT_SIZE * GameSettings.SEGMENT_SIZE /8);
        PointVector firstPoint = new PointVector(distance,distance);
        PointVector secondPoint = new PointVector(0,0);
        //then
        assertTrue(gameBoard.getCheckFood(firstPoint, secondPoint));
    }
    @Test
    public void checkFood_ValueOfRadius_false(){
        //given
        double distance = Math.sqrt(GameSettings.SEGMENT_SIZE * GameSettings.SEGMENT_SIZE /2);
        PointVector firstPoint = new PointVector(distance,distance);
        PointVector secondPoint = new PointVector(0,0);
        //then
        assertFalse(gameBoard.getCheckFood(firstPoint, secondPoint));
    }
    @Test
    public void checkFood_OneAndHalfValueOfRadius_false(){
        //given
        double distance = Math.sqrt(GameSettings.SEGMENT_SIZE * GameSettings.SEGMENT_SIZE *(8.0/9.0));
        PointVector firstPoint = new PointVector(distance,distance);
        PointVector secondPoint = new PointVector(0,0);
        //then
        assertFalse(gameBoard.getCheckFood(firstPoint, secondPoint));
    }
    @Test
    public void checkSpecialFood_HalfValueOfRadius_true(){
        //given
        double distance = Math.sqrt(GameSettings.SEGMENT_SIZE * GameSettings.SEGMENT_SIZE /8);
        PointVector firstPoint = new PointVector(distance,distance);
        PointVector secondPoint = new PointVector(0,0);
        //then
        assertTrue(gameBoard.getCheckSpecialFood(firstPoint, secondPoint));
    }
    @Test
    public void checkSpecialFood_ValueOfRadius_false(){
        //given
        double distance = Math.sqrt(GameSettings.SEGMENT_SIZE * GameSettings.SEGMENT_SIZE /2);
        PointVector firstPoint = new PointVector(distance,distance);
        PointVector secondPoint = new PointVector(0,0);
        //then
        assertFalse(gameBoard.getCheckSpecialFood(firstPoint, secondPoint));
    }
    @Test
    public void checkSpecialFood_OneAndHalfValueOfRadius_false(){
        //given
        double distance = Math.sqrt(GameSettings.SEGMENT_SIZE * GameSettings.SEGMENT_SIZE*(8.0/9.0));
        PointVector firstPoint = new PointVector(distance,distance);
        PointVector secondPoint = new PointVector(0,0);
        //then
        assertFalse(gameBoard.getCheckSpecialFood(firstPoint, secondPoint));
    }
    @Test
    public void checkTailCollision_snakeInShapeOfCircle_true(){
        //given
        //Snake w kształcie koła o promieniu 50 pikseli i 200 elementach  - zdarzenie z ogonem
        List<PointVector> preparedSnake = new ArrayList<>();
        for(int i = -50; i <= 50; i++){
            preparedSnake.add(new PointVector(i*5, Math.sqrt(2500-(i*i))));
        }
        for(int i = 50; i >= -50; i--){
            preparedSnake.add(new PointVector(i*5, -Math.sqrt(2500-(i*i))));
        }
        Snake mySnake = new Snake(preparedSnake);
        //then
        assertTrue(gameBoard.getCheckTailCollision(mySnake));
    }
    @Test
    public void checkTailCollision_snakeInShapeOfIncompleteCircle_false(){
        //given
        //Snake w kształcie koła o promieniu 50 pikseli i 198 elementach  - zdarzenie z ogonem
        List<PointVector> preparedSnake = new ArrayList<>();
        for(int i = -50; i <= 50; i++){
            preparedSnake.add(new PointVector(i*5, Math.sqrt(2500-(i*i))));
        }
        for(int i = 50; i >= -48; i--){
            preparedSnake.add(new PointVector(i*5, -Math.sqrt(2500-(i*i))));
        }
        Snake mySnake = new Snake(preparedSnake);
        //then
        assertFalse(gameBoard.getCheckTailCollision(mySnake));
    }
    @Test
    public void checkBorders_headInTheMiddle_false () {
        //głowa snake'a gdzieś po środku mapy
        PointVector sampleHead = new PointVector(GameSettings.BOARD_WIDTH/2, GameSettings.BOARD_HEIGHT/2);
        assertFalse(gameBoard.checkBorders_testVersion(sampleHead));
    }
    @Test
    public void checkBorders_headOnTheVerge_false () {
        //głowa snake'a idealnie na krawędzi, krawędź głowy dotyka krawędzi planszy
        PointVector sampleHead = new PointVector(GameSettings.SEGMENT_SIZE/2, ThreadLocalRandom.current().nextDouble(0, GameSettings.BOARD_HEIGHT));
        assertFalse(gameBoard.checkBorders_testVersion(sampleHead));
    }
    @Test
    public void checkBorders_headOnTheVerge_true () {
        //głowa wykracza poza krawędź
        PointVector sampleHead = new PointVector(-1, ThreadLocalRandom.current().nextDouble(0, GameSettings.BOARD_HEIGHT));
        assertTrue(gameBoard.checkBorders_testVersion(sampleHead));
    }
}

