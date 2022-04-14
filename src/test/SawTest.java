
package snakegame.Tests;
import org.junit.jupiter.api.Test;
import snakegame.PointVector;
import snakegame.Saw;

import static org.junit.jupiter.api.Assertions.*;

public class SawTest {
    Saw saw = new Saw(1,40);
    
    @Test
    public void verticalMove_aboveTop() {
        //given
        saw.setLocation(new PointVector(10,10));
        //when
        saw.verticalMove();
        //then
        double expectedSpeedY = -1;
        double expectedY = 9;
        assertEquals(expectedSpeedY, saw.getSpeedY());
        assertEquals(expectedY, saw.getY());
    }
    @Test
    public void verticalMove_onTop() {
        //given
        saw.setLocation(new PointVector(10,20));
        //when
        saw.verticalMove();
        //then
        double expectedSpeedY = -1;
        double expectedY = 19;
        assertEquals(expectedSpeedY, saw.getSpeedY());
        assertEquals(expectedY, saw.getY());
    }
    @Test
    public void verticalMove_inMiddle() {
        //given
        saw.setLocation(new PointVector(10,100));
        //when
        saw.verticalMove();
        //then
        double expectedSpeedY = 1;
        double expectedY = 101;
        assertEquals(expectedSpeedY, saw.getSpeedY());
        assertEquals(expectedY, saw.getY());
    }
    @Test
    public void verticalMove_onBottom() {
        //given
        saw.setLocation(new PointVector(10,780));
        //when
        saw.verticalMove();
        //then
        double expectedSpeedY = -1;
        double expectedY = 779;
        assertEquals(expectedSpeedY, saw.getSpeedY());
        assertEquals(expectedY, saw.getY());
    }
    @Test
    public void verticalMove_underBottom() {
        //given
        saw.setLocation(new PointVector(10,789));
        //when
        saw.verticalMove();
        //then
        double expectedSpeedY = -1;
        double expectedY = 788;
        assertEquals(expectedSpeedY, saw.getSpeedY());
        assertEquals(expectedY, saw.getY());
    }
    
    
    
    
    
    
    @Test
    public void horizontalMove_overLeft() {
        //given
        saw.setLocation(new PointVector(7,10));
        //when
        saw.horizontalMove();
        //then
        double expectedSpeedX = -1;
        double expectedX = 6;
        assertEquals(expectedSpeedX, saw.getSpeedX());
        assertEquals(expectedX, saw.getX());
    }
    @Test
    public void horizontalMove_onLeft() {
        //given
        saw.setLocation(new PointVector(20,10));
        //when
        saw.horizontalMove();
        //then
        double expectedSpeedX = -1;
        double expectedX = 19;
        assertEquals(expectedSpeedX, saw.getSpeedX());
        assertEquals(expectedX, saw.getX());
    }
    @Test
    public void horizontalMove_inMiddle() {
        //given
        saw.setLocation(new PointVector(50,10));
        //when
        saw.horizontalMove();
        //then
        double expectedSpeedX = 1;
        double expectedX = 51;
        assertEquals(expectedSpeedX, saw.getSpeedX());
        assertEquals(expectedX, saw.getX());
    }
    @Test
    public void horizontalMove_onRight() {
        //given
        saw.setLocation(new PointVector(780,10));
        //when
        saw.horizontalMove();
        //then
        double expectedSpeedX = -1;
        double expectedX = 779;
        assertEquals(expectedSpeedX, saw.getSpeedX());
        assertEquals(expectedX, saw.getX());
    }
    @Test
    public void horizontalMove_overRight() {
        //given
        saw.setLocation(new PointVector(781,10));
        //when
        saw.horizontalMove();
        //then
        double expectedSpeedX = -1;
        double expectedX = 780;
        assertEquals(expectedSpeedX, saw.getSpeedX());
        assertEquals(expectedX, saw.getX());
    }
    
    @Test
    public void diagonalMoveUp_overLeft_aboveTop() {
        //given
        saw.setLocation(new PointVector(9,9));
        //when
        saw.diagonalMoveUp();
        //then
        double expectedSpeedX = -1;
        double expectedSpeedY = -1;
        double expectedX = 8;
        double expectedY = 10;
        assertEquals(expectedSpeedX, saw.getSpeedX());
        assertEquals(expectedSpeedY, saw.getSpeedY());
        assertEquals(expectedX, saw.getX());
        assertEquals(expectedY, saw.getY());
    }
    @Test
    public void diagonalMoveUp_onLeft_onTop() {
        //given
        saw.setLocation(new PointVector(20,20));
        //when
        saw.diagonalMoveUp();
        //then
        double expectedSpeedX = -1;
        double expectedSpeedY = -1;
        double expectedX = 19;
        double expectedY = 21;
        assertEquals(expectedSpeedX, saw.getSpeedX());
        assertEquals(expectedSpeedY, saw.getSpeedY());
        assertEquals(expectedX, saw.getX());
        assertEquals(expectedY, saw.getY());
    }
    @Test
    public void diagonalMoveUp_onRight_onBottom() {
        //given
        saw.setLocation(new PointVector(780,780));
        //when
        saw.diagonalMoveUp();
        //then
        double expectedSpeedX = -1;
        double expectedSpeedY = -1;
        double expectedX = 779;
        double expectedY = 781;
        assertEquals(expectedSpeedX, saw.getSpeedX());
        assertEquals(expectedSpeedY, saw.getSpeedY());
        assertEquals(expectedX, saw.getX());
        assertEquals(expectedY, saw.getY());
    }
    @Test
    public void diagonalMoveUp_inMiddle() {
        //given
        saw.setLocation(new PointVector(600,600));
        //when
        saw.diagonalMoveUp();
        //then
        double expectedSpeedX = 1;
        double expectedSpeedY = 1;
        double expectedX = 601;
        double expectedY = 599;
        assertEquals(expectedSpeedX, saw.getSpeedX());
        assertEquals(expectedSpeedY, saw.getSpeedY());
        assertEquals(expectedX, saw.getX());
        assertEquals(expectedY, saw.getY());
    }
    @Test
    public void diagonalMoveUp_overRight_underBottom() {
        //given
        saw.setLocation(new PointVector(785,785));
        //when
        saw.diagonalMoveUp();
        //then
        double expectedSpeedX = -1;
        double expectedSpeedY = -1;
        double expectedX = 784;
        double expectedY = 786;
        assertEquals(expectedSpeedX, saw.getSpeedX());
        assertEquals(expectedSpeedY, saw.getSpeedY());
        assertEquals(expectedX, saw.getX());
        assertEquals(expectedY, saw.getY());
    }
    
    
    
    
    
    @Test
    public void diagonalMoveDown_overLeft_underBottom() {
        //given
        saw.setLocation(new PointVector(9,781));
        //when
        saw.diagonalMoveDown();
        //then
        double expectedSpeedX = -1;
        double expectedSpeedY = -1;
        double expectedX = 8;
        double expectedY = 780;
        assertEquals(expectedSpeedX, saw.getSpeedX());
        assertEquals(expectedSpeedY, saw.getSpeedY());
        assertEquals(expectedX, saw.getX());
        assertEquals(expectedY, saw.getY());
    }
    @Test
    public void diagonalMoveDown_overLeft_onBottom() {
        //given
        saw.setLocation(new PointVector(10,780));
        //when
        saw.diagonalMoveDown();
        //then
        double expectedSpeedX = -1;
        double expectedSpeedY = -1;
        double expectedX = 9;
        double expectedY = 779;
        assertEquals(expectedSpeedX, saw.getSpeedX());
        assertEquals(expectedSpeedY, saw.getSpeedY());
        assertEquals(expectedX, saw.getX());
        assertEquals(expectedY, saw.getY());
    }
    @Test
    public void diagonalMoveDown_inMiddle() {
        //given
        saw.setLocation(new PointVector(90,90));
        //when
        saw.diagonalMoveDown();
        //then
        double expectedSpeedX = 1;
        double expectedSpeedY = 1;
        double expectedX = 91;
        double expectedY = 91;
        assertEquals(expectedSpeedX, saw.getSpeedX());
        assertEquals(expectedSpeedY, saw.getSpeedY());
        assertEquals(expectedX, saw.getX());
        assertEquals(expectedY, saw.getY());
    }
    @Test
    public void diagonalMoveDown_overLeft() {
        //given
        saw.setLocation(new PointVector(10,760));
        //when
        saw.diagonalMoveDown();
        //then
        double expectedSpeedX = -1;
        double expectedSpeedY = 1;
        double expectedX = 9;
        double expectedY = 761;
        assertEquals(expectedSpeedX, saw.getSpeedX());
        assertEquals(expectedSpeedY, saw.getSpeedY());
        assertEquals(expectedX, saw.getX());
        assertEquals(expectedY, saw.getY());
    }
    @Test
    public void diagonalMoveDown_overRight() {
        //given
        saw.setLocation(new PointVector(787,25));
        //when
        saw.diagonalMoveDown();
        //then
        double expectedSpeedX = -1;
        double expectedSpeedY = 1;
        double expectedX = 786;
        double expectedY = 26;
        assertEquals(expectedSpeedX, saw.getSpeedX());
        assertEquals(expectedSpeedY, saw.getSpeedY());
        assertEquals(expectedX, saw.getX());
        assertEquals(expectedY, saw.getY());
    }
    
}
