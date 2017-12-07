/*
 * TCSS 305 – Autumn 2015 Assignment 3 – easystreet
 */

package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;
import model.Direction;
import model.Human;
import model.Light;
import model.Terrain;
import model.Truck;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for the Truck class.
 * 
 * @author Kevin Nguyen
 * @version 4.5.0 October 2015
 */
public class TruckTest {

    /**
     * Sample Truck to use for tests. myTruck will be the testing Truck.
     */
    private Truck myTruck;
    /**
     * myNeighbors is a map used for testing the truck class in given
     * Terrian conditions.
     */
    private Map<Direction, Terrain> myNeighbors;

    /**
     * setUp method initialized before each test is compiled.
     */
    @Before
    public void setUp() {
        myTruck = new Truck(1, 3, Direction.EAST);
        myNeighbors = new HashMap<Direction, Terrain>();
    }

    /** Test method for Truck constructor. */
    @Test
    public void testTruckConstructor() {
        assertEquals("Truck x coordinate failed!", 1, myTruck.getX());
        assertEquals("Truck y coordinate failed!", 3, myTruck.getY());
        assertEquals("Truck direction failed!", Direction.EAST, myTruck.getDirection());
        assertEquals("Truck deathtime failed!", 0, myTruck.getDeathTime());
        assertTrue("Truck isAlive() failed!", myTruck.isAlive());
    }

    /** Test method for Truck setters. */
    @Test
    public void testTruckSetters() {

        myTruck.setX(15);
        assertEquals("Truck setX failed!", 15, myTruck.getX());
        myTruck.setY(20);
        assertEquals("Truck setY failed!", 20, myTruck.getY());
        myTruck.setDirection(Direction.NORTH);
        assertEquals("Truck setDirection failed!", Direction.NORTH, myTruck.getDirection());
    }

    /**
     * Test method for {@link Truck#canPass(Terrain, Light)}.
     */
    @Test
    public void testCanPass() {
        for (final Terrain testterrain : Terrain.values()) {
            for (final Light testlight : Light.values()) {
                if (testterrain == Terrain.STREET || testterrain == Terrain.LIGHT) {
                    assertEquals("CanPass Failed!", true,
                                 myTruck.canPass(testterrain, testlight));
                } else {
                    assertEquals("CanPass Failed!", false,
                                 myTruck.canPass(testterrain, testlight));
                }

            }
        }

    }

    /**
     * Test method for {@link Truck#ChooseDirectionThreeWay()}.
     */
    @Test
    public void testChooseDirectionThreeWay() {
        myTruck = new Truck(0, 0, Direction.SOUTH); //Truck points South.
        myNeighbors.put(Direction.WEST, Terrain.STREET);
        myNeighbors.put(Direction.SOUTH, Terrain.STREET);
        myNeighbors.put(Direction.NORTH, Terrain.WALL);
        myNeighbors.put(Direction.EAST, Terrain.STREET);
        /**
         *  W 
         * S S 
         *  S
         */
        boolean seenEast = false;
        boolean seenWest = false;
        boolean seenSouth = false;
        //checks to see if the truck has seen south, west, and east.
        for (int i = 0; i <= 50; i++) {
            final Direction dir = myTruck.chooseDirection(myNeighbors);
            assertTrue(dir == Direction.EAST || dir == Direction.SOUTH
                       || dir == Direction.WEST);
            seenSouth = seenSouth || dir == Direction.SOUTH;
            seenWest = seenWest || dir == Direction.WEST;
            seenEast = seenEast || dir == Direction.EAST;

        }
        assertTrue("ChooseDirectionEast failed!", seenEast && seenWest & seenSouth);
    }

    /**
     * Test method for {@link Truck#ChooseDirectionTwoWay()}.
     */
    @Test
    public void testChooseDirectionTwoWay() {
        myTruck = new Truck(0, 0, Direction.SOUTH); //Truck points South.
        boolean seenEast = false;
        boolean seenWest = false;
        boolean seenSouth = false;
        myNeighbors.put(Direction.WEST, Terrain.STREET);
        myNeighbors.put(Direction.SOUTH, Terrain.WALL);
        myNeighbors.put(Direction.NORTH, Terrain.WALL);
        myNeighbors.put(Direction.EAST, Terrain.STREET);
        /**
         * W 
         *S S 
         * W
         */

        for (int i = 0; i <= 50; i++) {
            final Direction dir = myTruck.chooseDirection(myNeighbors);
            assertTrue(dir == Direction.EAST || dir == Direction.WEST);

            seenWest = seenWest || dir == Direction.WEST;
            seenEast = seenEast || dir == Direction.EAST;

        }
        assertTrue("ChooseDirectionEasaat failed!", seenEast);
        assertTrue("ChooseDirectionsdd dfailed!", seenWest);

        seenEast = false;
        seenWest = false;

        myNeighbors.put(Direction.WEST, Terrain.WALL);
        myNeighbors.put(Direction.SOUTH, Terrain.STREET);

        /**
         *  W 
         * W S 
         *  S
         */

        for (int i = 0; i <= 50; i++) {
            final Direction dir = myTruck.chooseDirection(myNeighbors);
            assertTrue(dir == Direction.EAST || dir == Direction.SOUTH);

            seenSouth = seenSouth || dir == Direction.SOUTH;

            seenEast = seenEast || dir == Direction.EAST;
        }
        assertTrue("ChooseDirectionEasaat failed!", seenEast);
        assertTrue("ChooseDirectionEasaat fassiled!", seenSouth);
        seenEast = false;
        seenSouth = false;
        
        myNeighbors.put(Direction.WEST, Terrain.STREET);
        myNeighbors.put(Direction.EAST, Terrain.WALL);
        /**
         *  W 
         * S W 
         *  S
         */
        
        for (int i = 0; i <= 50; i++) {
            final Direction dir = myTruck.chooseDirection(myNeighbors);
            assertTrue(dir == Direction.WEST || dir == Direction.SOUTH);

            seenSouth = seenSouth || dir == Direction.SOUTH;

            seenWest = seenWest || dir == Direction.WEST;

        }
        assertTrue("ChooseDirectionEasaat failed!", seenWest);
        assertTrue("ChooseDirectionEasaat fassiled!", seenSouth);

    }

    /**
     * Test method for {@link Truck#ChooseDirectionOneWay()}.
     */
    @Test
    public void testChooseDirectionOneWay() {
        myTruck = new Truck(0, 0, Direction.SOUTH); //Truck points South.

        boolean seenEast = false;
        boolean seenWest = false;
        boolean seenSouth = false;
        boolean seenNorth = false;

        for (final Terrain t : Terrain.values()) {
            if (t == Terrain.GRASS || t == Terrain.TRAIL || t == Terrain.WALL) {
                continue;
            }
            myNeighbors.put(Direction.WEST, Terrain.WALL);
            myNeighbors.put(Direction.SOUTH, Terrain.WALL);
            myNeighbors.put(Direction.NORTH, t);
            myNeighbors.put(Direction.EAST, Terrain.WALL);
            /**
             *  t    , t could be light or street.
             * W W
             *  W
             */
            seenEast = false;

            for (int i = 0; i <= 50; i++) {
                final Direction dir = myTruck.chooseDirection(myNeighbors);
                assertSame(true, dir == Direction.NORTH);

                seenNorth = seenNorth || dir == Direction.NORTH;


            }
            assertTrue("ChooseDirectionEasaat failed!", seenNorth);


            myNeighbors.put(Direction.SOUTH, t);
            myNeighbors.put(Direction.NORTH, Terrain.WALL);
            /**
             *  W    
             * W W
             *  t
             */
            seenEast = false;

            for (int i = 0; i <= 50; i++) {
                final Direction dir = myTruck.chooseDirection(myNeighbors);
                assertSame(true, dir == Direction.SOUTH);

                seenSouth = seenSouth || dir == Direction.SOUTH;


            }
            assertTrue("ChooseDirectionEasaat failed!", seenSouth);

            myNeighbors.put(Direction.WEST, t);
            myNeighbors.put(Direction.SOUTH, Terrain.WALL);
            /**
             *  W    
             * t W
             *  W
             */
            seenEast = false;

            for (int i = 0; i <= 50; i++) {
                final Direction dir = myTruck.chooseDirection(myNeighbors);
                assertSame(true, dir == Direction.WEST);

                seenWest = seenWest || dir == Direction.WEST;


            }
            assertTrue("ChooseDirectionEasaat failed!", seenWest);

            myNeighbors.put(Direction.WEST, Terrain.WALL);
            myNeighbors.put(Direction.EAST, t);
            /**
             *  W    
             * W t
             *  W
             */
            seenEast = false;

            for (int i = 0; i <= 50; i++) {
                final Direction dir = myTruck.chooseDirection(myNeighbors);
                assertSame(true, dir == Direction.EAST);

                seenEast = seenEast || dir == Direction.EAST;


            }
            assertTrue("ChooseDirectionEasaat failed!", seenEast);

        }


    }

    /**
     * Test method for {@link Truck#poke()}.
     */
    @Test
    public void testPoke() {
        myTruck.poke();
        assertEquals("Poke failed!", true, myTruck.isAlive());

    }

    /**
     * Test method for {@link Truck#reset()}.
     */
    @Test
    public void testReset() {
        myTruck.setX(22);
        myTruck.setY(33);
        myTruck.reset();
        assertEquals("Reset failed!", 1, myTruck.getX());
        assertEquals("Reset failed!", 3, myTruck.getY());

    }

    /**
     * Test method for {@link Truck#getImageFileName()}.
     */
    @Test
    public void testGetImageFileName() {
        assertEquals("GetImage failed!", "truck.gif", myTruck.getImageFileName());
    }

    /**
     * Test method for {@link Truck#collide(model.Vehicle)}.
     */
    @Test
    public void testCollide() {
        final Human test = new Human(1, 7, Direction.EAST, Terrain.STREET);
        myTruck.collide(test);
        assertEquals("Collide failed!", false, test.isAlive());

    }
}
