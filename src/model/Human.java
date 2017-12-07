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
public class Human extends AbstractVehicle {
    /**
     * DEATH_TIME is an int that describes this class's death time.
     */
    public static final int DEATH_TIME = 45;
    /**
     * myFloor stores the vehicle's given Terrain.
     */
    private final Terrain myFloor;

    /**
     * Human's constructor calls to the parent class for initialization.
     * 
     * @param theVehicleX is received then passed to parent class as an int.
     * @param theVehicleY is received then passed to parent class as an int.
     * @param theValueOf is received then passed to parent class as a Direction.
     * @param valueOf2 is received then stored into myFloor.
     */
    public Human(final int theVehicleX, final int theVehicleY, final Direction theValueOf,
                 final Terrain valueOf2) {
        super(theVehicleX, theVehicleY, theValueOf, DEATH_TIME);
        myFloor = valueOf2;
    }

    /**
     * canPass() tells us that this vehicle can only pass on the terrain it was
     * initially on.
     */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        boolean canpass = false;

        if ((myFloor == Terrain.STREET || myFloor == Terrain.LIGHT)
            && (theTerrain == Terrain.LIGHT || theTerrain == Terrain.STREET)) {

            canpass = true;

        } else if (myFloor == theTerrain) {

            canpass = true;

        } else {
            canpass = false;
        }
        return canpass;

    }

    /**
     * chooseDirection() returns a random direction.
     */
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        Direction newstep = Direction.random();

        int foundstep = 0;
        while (foundstep == 0) {
            newstep = Direction.random();
            if ((myFloor == Terrain.STREET || myFloor == Terrain.LIGHT)
                && (theNeighbors.get(newstep) == Terrain.STREET
                    || theNeighbors.get(newstep) == Terrain.LIGHT)) {
                foundstep++;
            } else if ((myFloor == theNeighbors.get(newstep))
                     && (theNeighbors.get(newstep) != Terrain.WALL)) {
                foundstep++;
            }

        }
        return newstep;
    }

}
