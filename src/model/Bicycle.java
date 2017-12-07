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
public class Bicycle extends AbstractVehicle {
    /**
     * DEATH_TIME is an int that describes this class's death time.
     */
    public static final int DEATH_TIME = 25;

    /**
     * Bicycle's constructor calls to the parent class for initialization.
     * 
     * @param theVehicleX is received then passed to parent class as an int.
     * @param theVehicleY is received then passed to parent class as an int.
     * @param theValueOf is received then passed to parent class as a direction.
     */
    public Bicycle(final int theVehicleX, final int theVehicleY, final Direction theValueOf) {
        super(theVehicleX, theVehicleY, theValueOf, DEATH_TIME);

    }

    /**
     * canPass() tells us that Bicycle can travel on streets and through lights,
     * but prefer trails. Once a bicycle is on a trail, they always go straight
     * ahead in the direction it's facing. It cannot pass traffic lights that
     * are yellow or red.
     */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        boolean canpass = false;

        if (theTerrain == Terrain.STREET || theTerrain == Terrain.TRAIL) {

            canpass = true;
        } else if (theTerrain == Terrain.LIGHT && ((theLight == Light.GREEN))) {

            canpass = true;
        } else {
            canpass = false;
        }
        return canpass;

    }

    /**
     * chooseDirection() returns a straight direction but only changes it
     * direction if a trail is next to it. Then it goes through the trail. But
     * if it is not on trail then it goes straight. But if straight is not
     * possible, it turns left and if not left, it turns right. But if all of
     * the above is not an option, it turns around.
     */
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        final Direction straight = getDirection();
        Direction go = null;
        go = checkStreet(theNeighbors, go); //check if left and right for trail.

        if (go == null) { // if not go straight, if not straight left, if not left right, if not right reverse.
            if (theNeighbors.get(straight) == Terrain.STREET 
                || theNeighbors.get(straight) == Terrain.LIGHT
                || theNeighbors.get(straight) == Terrain.TRAIL) {
                go = straight;
            } else if ((theNeighbors.get(getDirection().left()) == Terrain.STREET)
                     || (theNeighbors.get(getDirection().left()) == Terrain.LIGHT)) {
                go = getDirection().left();
            } else if (theNeighbors.get(getDirection().right()) == Terrain.STREET
                     || theNeighbors.get(getDirection().right()) == Terrain.LIGHT) {
                go = getDirection().right();
            } else {
                go = getDirection().reverse();
            }
        }

        return go;
    }

    /**
     * Checks if there is a trail on the left or right side of the bicycle. if
     * there is then it turns towards it and goes through it.
     * 
     * @param theNeighbors is passed so it can know where the bicycle's location
     *            is.
     * @param theInitialDirection is the direction passed that could be the final
     *            direction, but is checked first to see if the direction move
     *            is legal.
     * @return the final direction.
     */
    private Direction checkStreet(final Map<Direction, Terrain> theNeighbors,
                                  final Direction theInitialDirection) {
        Direction testgo = theInitialDirection;
        if ((theNeighbors.get(getDirection().left()) == Terrain.TRAIL
             && theNeighbors.get(getDirection().reverse()) == Terrain.STREET)
            || (theNeighbors.get(getDirection().right()) == Terrain.TRAIL
                && theNeighbors.get(getDirection().reverse()) == Terrain.STREET)) {

            if (theNeighbors.get(getDirection().left()) == Terrain.TRAIL) {
                testgo = getDirection().left();
            } else {
                testgo = getDirection().right();
            }
        }
        return testgo;
    }

}
