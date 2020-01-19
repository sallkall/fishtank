package fishtank;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FishTest {

    private Fish fish;
    private static final int COUNT = 1000;

    @Before
    public void setUp() {
        fish = new Fish();
    }

    @Test
    public void testFishBubbles() {
        //Note: This test currently fails, but should pass once you've
        // refactored &
        //fixed the starter code.
        boolean bubbleMade = false;
        for (int i = 0; i < COUNT; i++) {
            fish.setLocation(5, 10);
            fish.goingRight =
                    false; //notice: I can edit package private attributes!
            fish.update();
            //fish should move one tile left and eventually blow a bubble.
            FishTankEntity e = FishTank.getEntity(5 - 1, 10);
            if (e instanceof Bubble) {
                bubbleMade = true;
                break;
            }
        }
        //You could also write "assert bubbleMade", but using the JUnit version
        //makes the message much nicer if it fails.
        assertTrue(bubbleMade);
    }

    /**
     * Test fish movement
     */

    @Test
    public void testUpdateFish() {
        int countUp = 0;
        int countDown = 0;
        int countTurn = 0;
        int countBubble = 0;


        for (int i = 0; i < COUNT; i++) {
            FishTank.clearTank();
            fish.setGoingRight(true);
            fish.setLocation(5, 10);
            fish.update();
            // fish should turn around 10% of the time
            if (fish.getX() == 4) {
                countTurn++;    //turns
            }
            // fish should move up/down 10% of the time
            if (fish.getY() == 9) {
                countUp++;
            } else if (fish.getY() == 11) {
                countDown++;
            }

            // fish should blow bubble at their location (after moving) 10% of the time
            FishTankEntity e = FishTank.getEntity(fish.getX(), fish.getY() - 1);
            if (e instanceof Bubble) {
                countBubble++;
            }
        }
        assertEquals(0.1, countUp / ((double) COUNT), 0.05);
        assertEquals(0.1, countDown / ((double) COUNT), 0.05);
        assertEquals(0.1, countTurn / ((double) COUNT), 0.05);
        assertEquals(0.1, countBubble / ((double) COUNT), 0.05);

    }

    /**
     * Test fish for going right / left
     */

    @Test
    public void testGoingRightFromRight() {

        fish.goingRight = true;
        fish.appearance = "><>";
        fish.setGoingRight(true);

        assertEquals("><>", fish.appearance);
        assertTrue(fish.goingRight);
    }

    @Test
    public void testGoingRightFromLeft() {
        fish.goingRight = false;
        fish.appearance = "<><";
        fish.setGoingRight(true);

        assertEquals("><>", fish.appearance);
        assertTrue(fish.goingRight);
    }

    @Test
    public void testGoingLeftFromLeft() {
        fish.goingRight = false;
        fish.appearance = "<><";
        fish.setGoingRight(false);

        assertEquals("<><", fish.appearance);
        assertFalse(fish.goingRight);
    }

    @Test
    public void testGoingLeftFromRight() {
        fish.goingRight = true;
        fish.appearance = "><>";
        fish.setGoingRight(false);

        assertEquals("<><", fish.appearance);
        assertFalse(fish.goingRight);
    }

    /**
     * Test for boundary
     */
    @Test
    public void testLeftBound() {
        int countHit = 0; // hit left bound, turns around
        int countRight = 0; // goes right

        for (int i = 0; i < COUNT; i++) {
            FishTank.clearTank();
            fish.setGoingRight(false);
            fish.setLocation(0, 10);

            fish.update();
            if (fish.getX() == 0) {
                countHit++;
            } else if (fish.getX() == 1) {
                countRight++;
            }
        }

        assertEquals(0.1, countRight / ((double) COUNT), 0.05);
        assertEquals(COUNT, countRight + countHit);
    }

    @Test
    public void testRightBound() {
        int countHit = 0; // hit right bound, turns around
        int countLeft = 0; // goes left

        for (int i = 0; i < COUNT; i++) {
            FishTank.clearTank();
            fish.setGoingRight(true);
            fish.setLocation(FishTank.width - fish.appearance.length() - 1, 10);

            fish.update();
            if (fish.getX() == FishTank.width - fish.appearance.length() - 1) {
                countHit++;
            } else if (fish.getX() == FishTank.width - fish.appearance.length() - 2) {
                countLeft++;
            }
        }

        assertEquals(0.1, countLeft / ((double) COUNT), 0.05);
        assertEquals(COUNT, countLeft + countHit);
    }

    @Test
    public void testCeiling() {
        int countHit = 0; // hit upper bound
        int countDown = 0; // moves down

        for (int i = 0; i < COUNT; i++) {
            FishTank.clearTank();
            fish.setGoingRight(true);
            fish.setLocation(5, 0);
            fish.update();

            if (fish.getY() == 0) {
                countHit++;
            } else if (fish.getY() == 1) {
                countDown++;
            }
        }

        assertEquals(0.1, countDown / ((double) COUNT), 0.05);
        assertEquals(COUNT, countDown + countHit);
    }

    @Test
    public void testFloor() {
        int countHit = 0; // hit bottom bound
        int countUp = 0; // moves up

        for (int i = 0; i < COUNT; i++) {
            FishTank.clearTank();
            fish.setLocation(5, FishTank.height - 1);
            fish.update();

            if (fish.getY() == FishTank.height - 1) {
                countHit++;
            } else if (fish.getY() == FishTank.height - 2) {
                countUp++;
            }
        }
        assertEquals(0.1, countUp / ((double) COUNT), 0.05);
        assertEquals(COUNT, countUp + countHit);
    }

    /**
     * Test fish collision with other entities
     */

    @Test
    public void testCollisionRight() {
        int countHit = 0; // hit right entity
        int countLeft = 0;  // goes left

        for (int i = 0; i < COUNT; i++) {
            FishTank.clearTank();
            FishTank.addEntity(6, 10, new Fish());

            fish.setGoingRight(true);
            fish.setLocation(5, 10);
            fish.update();
            if (fish.getX() == 5) {
                countHit++;
            } else if (fish.getX() == 4) {
                countLeft++;
            }
        }

        assertEquals(0.1, countLeft / ((double) COUNT), 0.05);
        assertEquals(COUNT, countLeft + countHit);
    }

    @Test
    public void testCollisionLeft() {
        int countHit = 0; // hit right entity
        int countRight = 0;  // goes left

        for (int i = 0; i < COUNT; i++) {
            FishTank.clearTank();
            FishTank.addEntity(4, 10, new Fish());

            fish.setGoingRight(false);
            fish.setLocation(5, 10);
            fish.update();
            if (fish.getX() == 5) {
                countHit++;
            } else if (fish.getX() == 6) {
                countRight++;
            }
        }

        assertEquals(0.1, countRight / ((double) COUNT), 0.05);
        assertEquals(COUNT, countRight + countHit);
    }

    @Test
    public void testCollisionUp() {
        int countHit = 0; // hit upper entity
        int countDown = 0;  // goes down

        for (int i = 0; i < COUNT; i++) {
            FishTank.clearTank();
            FishTank.addEntity(4, 9, new Fish());
            FishTank.addEntity(5, 9, new Fish());
            FishTank.addEntity(6, 9, new Fish());

            fish.setGoingRight(true);
            fish.setLocation(5, 10);
            fish.update();

            if (fish.getY() == 10) {
                countHit++;
            } else if (fish.getY() == 11) {
                countDown++;
            }
        }

        assertEquals(0.1, countDown / ((double) COUNT), 0.05);
        assertEquals(COUNT, countDown + countHit);
    }

    @Test
    public void testCollisionDown() {
        int countHit = 0; // hit lower entity
        int countUp = 0;  // goes up

        for (int i = 0; i < COUNT; i++) {
            FishTank.clearTank();
            FishTank.addEntity(4, 11, new Fish());
            FishTank.addEntity(5, 11, new Fish());
            FishTank.addEntity(6, 11, new Fish());

            fish.setGoingRight(true);
            fish.setLocation(5, 10);
            fish.update();

            if (fish.getY() == 10) {
                countHit++;
            } else if (fish.getY() == 9) {
                countUp++;
            }
        }

        assertEquals(0.1, countUp / ((double) COUNT), 0.05);
        assertEquals(COUNT, countUp + countHit);
    }

    @Test
    public void testNoBubble() {
        FishTank.clearTank();
        FishTank.addEntity(4, 10, new Fish());
        FishTank.addEntity(5, 10, new Fish());
        FishTank.addEntity(6, 10, new Fish());
        FishTank.addEntity(4, 9, new Fish());
        FishTank.addEntity(5, 9, new Fish());
        FishTank.addEntity(6, 9, new Fish());

        fish.setLocation(5, 10);
        fish.update();

        assertFalse(FishTank.getEntity(fish.getX(), fish.getY() - 1) instanceof Bubble);
    }
}
