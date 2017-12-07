/*
 * TCSS 305 – Autumn 2015 Assignment 3 – easystreet
 */
package model;

import java.util.Map;
/**
 * AbstractVehicle is an common parent class.
 * 
 * @author Kevin Nguyen
 * @version 4.5.0 October 2015
 */
public class Car extends AbstractVehicle {
    /**
     * DEATH_TIME is an int that describes this class's death time.
     */
    public static final int DEATH_TIME = 5;
    /**
     * Car's constructor calls to the parent class for initialization.
     * @param theVehicleX is received then passed to parent class as an int.
     * @param theVehicleY is received then passed to parent class as an int.
     * @param theValueOf is received then passed to parent class as a direction.
     */
    public Car(final int theVehicleX, final int theVehicleY, final Direction theValueOf) {
        super(theVehicleX, theVehicleY, theValueOf, DEATH_TIME);
    }
    /**
     * canPass() tells us that Car can only pass streets and lights. 
     * Also It can only pass traffic lights that are either green or yellow.
     */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        boolean canpass = false;

        if (theTerrain == Terrain.STREET) {

            canpass = true;
        } else if (theTerrain == Terrain.LIGHT
                 && ((theLight == Light.GREEN) || (theLight == Light.YELLOW))) {

            canpass = true;
        } else {
            canpass = false;
        }
        return canpass;
    }
    /**
     * chooseDirection() returns a straight direction if possible, 
     * if not it turns left, if not left, it turns right. if the direction 
     * right is not possible, last resort it turns around.
     */
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {

        Direction go;
        if (theNeighbors.get(getDirection()) == Terrain.STREET
            || theNeighbors.get(getDirection()) == Terrain.LIGHT) {
            go = getDirection();
        } else if (theNeighbors.get(getDirection().left()) == Terrain.STREET
                 || theNeighbors.get(getDirection()) == Terrain.LIGHT) {
            go = getDirection().left();
        } else if (theNeighbors.get(getDirection().right()) == Terrain.STREET
                 || theNeighbors.get(getDirection()) == Terrain.LIGHT) {
            go = getDirection().right();
        } else {
            go = getDirection().reverse();
        }

        return go;
    }

}
