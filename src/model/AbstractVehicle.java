/*
 * TCSS 305 – Autumn 2015 Assignment 3 – easystreet
 */

package model;
/**
 * AbstractVehicle is an common parent class.
 * 
 * @author Kevin Nguyen
 * @version 4.5.0 October 2015
 */
public abstract class AbstractVehicle implements Vehicle {
    /**
     * myX stores the vehicle's x position as an int.
     */
    private int myX;
    /**
     * myY stores the vehicle's y position as an int.
     */
    private int myY;
    /**
     * myDirectionValue stores the vehicle's direction value as an Direction.
     */
    private Direction myDirectionValue;
    /**
     * myResetX stores the vehicle's x position as an int, for future resets.
     */
    private final int myResetX;
    /**
     * myResetY the vehicle's y position as an int, for future resets.
     */
    private final int myResetY;
    /**
     * myResetDirectionValue stores the vehicle's direction value as an Direction, 
     * for future resets.
     */
    private final Direction myResetDirectionValue;
    /**
     * myPokeCount stores the number of pokes the vehicle has taken while it is dead.
     */
    private int myPokeCount;
    /**
     * myDeathTime stores the vehicle's death time as an int.
     */
    private final int myDeathTime;
 

    /**
     * Constructor class AbstractVehicle stores the vehicle's (x,y) and Direction values.
     * It also stores a backup of the parameters passed for future resets.
     * @param theVehicleX is stored into myX as an int.
     * @param theVehicleY is stored into myY as an int.
     * @param theValueOf is stored into myDirectionValue as an int.
     * @param theDeathTime is stored into myDeathTime as an int.
     */
    protected AbstractVehicle(final int theVehicleX, final int theVehicleY, 
                              final Direction theValueOf, final int theDeathTime) {
        myX = theVehicleX;
        myY = theVehicleY;
        myDirectionValue = theValueOf;
        myDeathTime = theDeathTime;
        myResetX = theVehicleX;
        myResetY = theVehicleY;
        myResetDirectionValue = theValueOf;
        

    }
    /**
     * Collide() notifies a vehicle if it has collide with another vehicle. 
     * It only takes effect when both vehicles are alive and different.
     */
    @Override
    public final void collide(final Vehicle theOther) {
        if (theOther.isAlive() && this.isAlive()) {
            if (theOther.getDeathTime() > this.getDeathTime()) {
                theOther.poke();
            } else if (theOther.getDeathTime() < this.getDeathTime()) {
                this.poke();
            }
            
        } 

    }
    /**
     * getDeathTime() returns an int value of a vehicle's death time.
     * @return a int value of a vehicle's death/wait time.
     */
    @Override
    public final int getDeathTime() {       
        return myDeathTime;
    }
    /**
     * getImageFileName() returns a gif an a vehicle that could either be dead or alive.
     * @return an image displaying the vehicles object. 
     */
    @Override
    public final String getImageFileName() {
        String image;
        if (this.isAlive()) {
            image = getClass().getSimpleName().toLowerCase() + ".gif";
        } else {
            image = getClass().getSimpleName().toLowerCase() + "_dead.gif";
        } 
        return image;
    }
    /**
     * getDirection() returns the direction the vehicle pointed towards.
     * @return the direction the vehicle is facing.
     */
    @Override
    public final Direction getDirection() {
        return myDirectionValue;
    }
    /**
     * getX() returns the x value that the vehicle has.
     * @return the x coordinates of the vehicle.
     */
    @Override
    public final int getX() {
        return myX;
    }
    /**
     * getY() returns the y value that the vehicle has.
     * @return the y coordinates of the vehicle.
     */
    @Override
    public final int getY() {
        return myY;
    }
    /**
     * isAlive() a boolean determining whether the vehicle is alive or not.
     * @return a boolean true if the vehicle is still alive, false otherwise.
     */
    @Override
    public final boolean isAlive() {
        boolean alive = false;
        if (myPokeCount > 0) {
            alive = false;
        } else {
            alive = true;
        } 
        return alive;
    }
    /**
     * poke() is called once per turn, keeps track of how long the vehicle has been dead.
     * It then revive the vehicle appropriately.
     */
    @Override
    public final void poke() {
        myPokeCount++;
        if (myPokeCount > getDeathTime()) {
            this.setDirection(Direction.random());
            myPokeCount = 0;
        }

    }
    /**
     * reset() instructs the vehicle to return to it's initial state.
     */
    @Override
    public final void reset() {
        setX(myResetX);
        setY(myResetY);
        setDirection(myResetDirectionValue);

    }  
    /**
     *setDirection() sets a new direction.
     *@param theDir is used as an parameter to set the vehicle's new direction.
     */

    @Override
    public final void setDirection(final Direction theDir) {
        myDirectionValue = theDir;

    }
    /**
     * setX() sets a new x coordinate.
     * @param theX is used as an parameter to set the vehicle's new x coordinate.
     */
    @Override
    public final void setX(final int theX) {
        myX = theX;

    }
    /**
     * setY() sets a new y coordinate.
     * @param theY is used as an parameter to set the vehicle's new y coordinate.
     */
    @Override
    public final void setY(final int theY) {
        myY = theY;

    }
    /**
     * String toString() returns a string of the class's SimpleName.
     * @return a string of the vehicle's name.
     */
    @Override
    public final String toString() {
        return this.getClass().getSimpleName();

    }

}
