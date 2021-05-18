package snakegame.Tests;

import org.junit.jupiter.api.Test;
import snakegame.PointVector;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PointVectorTest {

    private final static double EPSILON = 0.0001;

    @Test
    void normalize_zeroVector() {
        //given
        PointVector myVector = new PointVector(0, 0);
        //when
        myVector.normalize();
        //then
        assertAll(
                () -> assertEquals(0, myVector.getY()),
                () -> assertEquals(0, myVector.length()),
                () -> assertEquals(0, myVector.getX())
        );

    }

    @Test
    void normalize_normalisedVector() {
        //given
        PointVector myVector = new PointVector(0, 1);
        //when
        myVector.normalize();
        //then
        assertAll(
                () -> assertEquals(0, myVector.getX()),
                () -> assertEquals(1, myVector.getY()),
                () -> assertEquals(1, myVector.length())
        );
    }

    @Test
    void normalize_oneVector() {
        //given
        PointVector myVector = new PointVector(1, 1);
        //when
        myVector.normalize();
        //then
        assertAll(
                () -> assertEquals(Math.sqrt(2) / 2, myVector.getX(), EPSILON),
                () -> assertEquals(Math.sqrt(2) / 2, myVector.getY(), EPSILON),
                () -> assertEquals(1, myVector.length(), EPSILON)
        );
    }

    @Test
    void normalize_minusOneVector() {
        //given
        PointVector myVector = new PointVector(-1, -1);
        //when
        myVector.normalize();
        //then
        assertAll(
                () -> assertEquals(-Math.sqrt(2) / 2, myVector.getX(), EPSILON),
                () -> assertEquals(-Math.sqrt(2) / 2, myVector.getY(), EPSILON),
                () -> assertEquals(1, myVector.length(), EPSILON)
        );
    }

    @Test
    void setConstantSpeed_zeroVector_oneShouldBeZero() {
        //given
        PointVector myVector = new PointVector(0, 0);
        //when
        myVector.setConstantSpeed(1);
        //then
        assertAll(
                () -> assertEquals(0, myVector.getX(), EPSILON),
                () -> assertEquals(0, myVector.getY(), EPSILON),
                () -> assertEquals(0, myVector.length(), EPSILON)
        );
    }

    @Test
    void setConstantSpeed_oneVector_oneShouldBeOne() {
        //given
        PointVector myVector = new PointVector(1, 1);
        //when
        myVector.setConstantSpeed(1);
        //then
        assertAll(
                () -> assertEquals(Math.sqrt(2) / 2, myVector.getX(), EPSILON),
                () -> assertEquals(Math.sqrt(2) / 2, myVector.getY(), EPSILON),
                () -> assertEquals(1, myVector.length(), EPSILON)
        );
    }

    @Test
    void setConstantSpeed_minusOneVector_oneShouldBeOne() {
        //given
        PointVector myVector = new PointVector(-1, -1);
        //when
        myVector.setConstantSpeed(1);
        //then
        assertAll(
                () -> assertEquals(-Math.sqrt(2) / 2, myVector.getX(), EPSILON),
                () -> assertEquals(-Math.sqrt(2) / 2, myVector.getY(), EPSILON),
                () -> assertEquals(1, myVector.length(), EPSILON)
        );
    }

    @Test
    void setConstantSpeed_oneVector_oneShouldBeZero() {
        //given
        PointVector myVector = new PointVector(1, 1);
        //when
        myVector.setConstantSpeed(0);
        //then
        assertAll(
                () -> assertEquals(0, myVector.getX(), EPSILON),
                () -> assertEquals(0, myVector.getY(), EPSILON),
                () -> assertEquals(0, myVector.length(), EPSILON)
        );
    }

    @Test
    void setConstantSpeed_oneVector_oneShouldBeSomethinglol() {
        //given
        PointVector myVector = new PointVector(2, 2);
        //when
        myVector.setConstantSpeed(-4);
        //then
        assertAll(
                () -> assertEquals(-Math.sqrt(4 * 2), myVector.getX(), EPSILON),
                () -> assertEquals(-Math.sqrt(4 * 2), myVector.getY(), EPSILON),
                () -> assertEquals(4, myVector.length(), EPSILON)
        );
    }
}