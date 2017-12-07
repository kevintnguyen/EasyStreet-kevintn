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
public class Truck extends AbstractVehicle {
    /**
     * DEATH_TIME is an int that describes this class's death time.
     */
    public static final int DEATH_TIME = 0;

    /**
     * Truck's constructor calls to the parent class for initialization.
     * 
     * @param theVehicleX is received then passed to parent class as an int.
     * @param theVehicleY is received then passed to parent class as an int.
     * @param theValueOf is received then passed to parent class as a direction.
     */
    public Truck(final int theVehicleX, final int theVehicleY, final Direction theValueOf) {
        super(theVehicleX, theVehicleY, theValueOf, DEATH_TIME);
    }

    /**
     * canPass() tells us that Truck can travel on streets and lights. They can
     * move through all traffic lights!
     */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        boolean canpass = false;

        if (theTerrain == Terrain.STREET) {

            canpass = true;
        } else if (theTerrain == Terrain.LIGHT) {

            canpass = true;
        } else {
            canpass = false;
        }
        return canpass;
    }

    /**
     * chooseDirection() returns a random direction, but it can't be a reverse
     * direction. If it cannot turn a random direction, it's last resort is to
     * turn around.
     */
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        Direction go = Direction.random();
        // checks if street is two way, left and right
        if ((theNeighbors.get(getDirection().left()) == Terrain.STREET)
            && (theNeighbors.get(getDirection().right()) == Terrain.STREET)) {
            // checks if it's a three way street, sees if straight is avaliable
            if (theNeighbors.get(getDirection()) == Terrain.STREET) {
                go = changeReverse(getDirection().reverse(), go);
                // else finds a new or uses old direction excluding the straight
                // direction.
            } else {
                go = changeReverse(getDirection(), go);
            }

        } else if ((theNeighbors.get(getDirection().left()) == Terrain.STREET)
                 && (theNeighbors.get(getDirection()) == Terrain.STREET)) {
            go = changeReverse(getDirection().right(), go);
        } else if ((theNeighbors.get(getDirection().right()) == Terrain.STREET)
                 && (theNeighbors.get(getDirection()) == Terrain.STREET)) {

            go = changeReverse(getDirection().left(), go);
        } else {
            go = checkLane(theNeighbors, go);
        }

        return go;
    }

    /**
     * checkLane is the last option the trucks encounters. It is when the
     * streets are one way.
     * 
     * @param theNeighbors map is passed from chooseDirection() to see the
     *            trucks current location.
     * @param theInitialDirection is the direction passed that could be the
     *            final direction, but is checked first to see if the direction
     *            move is legal.
     * @return the final direction.
     */
    private Direction checkLane(final Map<Direction, Terrain> theNeighbors,
                                final Direction theInitialDirection) {
        Direction testgo = theInitialDirection;

        if ((theNeighbors.get(getDirection()) == Terrain.STREET)
            || (theNeighbors.get(getDirection()) == Terrain.LIGHT)) {
            testgo = getDirection();
        } else if ((theNeighbors.get(getDirection().left()) == Terrain.STREET)
                 || (theNeighbors.get(getDirection().left()) == Terrain.LIGHT)) {
            testgo = getDirection().left();
        } else if ((theNeighbors.get(getDirection().right()) == Terrain.STREET)
                 || (theNeighbors.get(getDirection().right()) == Terrain.LIGHT)) {
            testgo = getDirection().right();
        } else {
            testgo = getDirection().reverse();
        }
        return testgo;
    }

    /**
     * changeReverse takes in unwanted directions and a random direction
     * generated. It tells you if the direction you send in is equivalent to the
     * unwanted direction or a reverse direction, them generates a new
     * direction.
     * 
     * @param theUnwantedDirection is the direction you do not want it to go.
     * @param theInitialDirection is the direction passed that could be the final
     *            direction, but is checked first to see if the direction move
     *            is legal.
     * @return final direction.
     */
    private Direction changeReverse(final Direction theUnwantedDirection,
                                    final Direction theInitialDirection) {
        Direction testgo = theInitialDirection;
        if ((testgo == getDirection().reverse()) || (testgo == theUnwantedDirection)) {
            while ((testgo == getDirection().reverse()) || (testgo == theUnwantedDirection)) {
                testgo = Direction.random();
            }

        }
        return testgo;
    }

}
