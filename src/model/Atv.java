/*
 * TCSS 305 – Autumn 2015 Assignment 3 – easystreet
 */
package model;

import java.util.Map;
/**
 * Atv is a vehicle class that extends the AbstractVehicle Class.
 * 
 * @author Kevin Nguyen
 * @version 4.5.0 October 2015
 */
public class Atv extends AbstractVehicle {
    /**
     * DEATH_TIME is an int that describes this class's death time.
     */
    public static final int DEATH_TIME = 15;
    /**
     * Atv's constructor calls to the parent class for initialization.
     * @param theVehicleX is received then passed to parent class as an int.
     * @param theVehicleY is received then passed to parent class as an int.
     * @param theValueOf is received then passed to parent class as a direction.
     */
    public Atv(final int theVehicleX, final int theVehicleY, final Direction theValueOf) {
        super(theVehicleX, theVehicleY, theValueOf, DEATH_TIME);
    }
    /**
     * canPass() tells us that Atv can pass any terrain but walls. Also could
     * go through all traffic lights.
     */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {

        return theTerrain != Terrain.WALL;

    }
    /**
     * chooseDirection() returns a random direction but reverse direction.
     */
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        Direction go = Direction.random();
        if (go == getDirection().reverse()) {
            while (go == getDirection().reverse()) {
                go = Direction.random();
            }

        }

        return go;
    }

}
