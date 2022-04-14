package snakegame.Tests;

import org.junit.jupiter.api.Test;
import snakegame.GameSettings;
import snakegame.SpecialFood;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SpecialFoodTest {

    @Test
    void checkBordersAndTeleport_positionX0Y0_noTeleport() {
        //given
        SpecialFood mySpecialFood = new SpecialFood();
        mySpecialFood.getPosition().set(0, 0);
        //when
        mySpecialFood.checkBordersAndTeleport();
        //then
        assertEquals(0, mySpecialFood.getX());
        assertEquals(0, mySpecialFood.getY());
    }

    @Test
    void checkBordersAndTeleport_positionXmaxYmax_noTeleport() {
        //given
        SpecialFood mySpecialFood = new SpecialFood();
        mySpecialFood.getPosition().set(GameSettings.BOARD_WIDTH, GameSettings.BOARD_HEIGHT);
        //when
        mySpecialFood.checkBordersAndTeleport();
        //then
        assertEquals(GameSettings.BOARD_WIDTH, mySpecialFood.getX());
        assertEquals(GameSettings.BOARD_HEIGHT, mySpecialFood.getY());
    }

    @Test
    void checkBordersAndTeleport_positionXlow0Ylow0_Teleport() {
        //given
        SpecialFood mySpecialFood = new SpecialFood();
        mySpecialFood.getPosition().set(-1, -1);
        //when
        mySpecialFood.checkBordersAndTeleport();
        //then
        assertEquals(GameSettings.BOARD_WIDTH, mySpecialFood.getX());
        assertEquals(GameSettings.BOARD_HEIGHT, mySpecialFood.getY());
    }

    @Test
    void checkBordersAndTeleport_positionXHiMaxYHiMax_Teleport() {
        //given
        SpecialFood mySpecialFood = new SpecialFood();
        mySpecialFood.getPosition().set(GameSettings.BOARD_WIDTH + 1, GameSettings.BOARD_HEIGHT + 1);

        //when
        mySpecialFood.checkBordersAndTeleport();
        //then
        assertEquals(0, mySpecialFood.getX());
        assertEquals(0, mySpecialFood.getY());
    }

    @Test
    void move_deadFood_noMovement() {
        //given
        SpecialFood mySpecialFood = new SpecialFood();
        mySpecialFood.getPosition().set(420, 420);
        mySpecialFood.setLongevity(0);
        //when
        mySpecialFood.move();
        //then
        assertEquals(420, mySpecialFood.getX());
        assertEquals(420, mySpecialFood.getY());
    }


}