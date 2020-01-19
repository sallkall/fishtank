package fishtank;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class BubbleTest {

    private Bubble bubble;
    private static final int COUNT = 100000;

    @Before
    public void setUp() {
        bubble = new Bubble();
    }

    @Test
    public void testDeleteBubble() {
        bubble.setLocation(5, 0); //bubble at ceiling
        bubble.update();
        assertFalse(bubble.exists());
    }

    @Test
    public void testBubbleFloat() {
        int straightUpCount = 0;
        int leftUpCount = 0;
        int rightUpCount = 0;

        for (int i = 0; i < COUNT; i++) {
            bubble.setLocation(5, 10);
            bubble.update();
            if (bubble.getX() == 4 && bubble.getY() == 9) {
                leftUpCount++;
            } else if (bubble.getX() == 5 && bubble.getY() == 9) {
                straightUpCount++;
            } else if (bubble.getX() == 6 && bubble.getY() == 9) {
                rightUpCount++;
            }
        }

        assertEquals(COUNT, leftUpCount + straightUpCount + rightUpCount);
        assertEquals(1.0 / 3, leftUpCount / ((double) COUNT), 1.0 / 3 * 0.05);
        assertEquals(1.0 / 3, straightUpCount / ((double) COUNT), 1.0 / 3 * 0.05);
        assertEquals(1.0 / 3, rightUpCount / ((double) COUNT), 1.0 / 3 * 0.05);
    }

    /**
     * Test for collision
     */
    @Test
    public void testCollisionAbove() {
        FishTank.clearTank();

        FishTank.addEntity(9, 9, new Fish());
        FishTank.addEntity(10, 9, new Fish());
        FishTank.addEntity(11, 9, new Fish());

        bubble.setLocation(10, 10);
        bubble.update();
        //  Bubble should not have moved
        assertEquals(10, bubble.getX());
        assertEquals(10, bubble.getY());
    }

    @Test
    public void testCollisionRight() {
        int straightUpCount = 0;
        int stillCount = 0;
        int leftUpCount = 0;

        for (int i = 0; i < COUNT; i++) {
            FishTank.clearTank();
            // Fish at up-right tile
            FishTank.addEntity(11, 9, new Fish());

            bubble.setLocation(10, 10);
            bubble.update();
            if (bubble.getX() == 10 && bubble.getY() == 10) {
                stillCount++;
            } else if (bubble.getX() == 10 && bubble.getY() == 9) {
                straightUpCount++;
            } else if (bubble.getX() == 9 && bubble.getY() == 9) {
                leftUpCount++;
            }
        }

        assertEquals(COUNT, stillCount + straightUpCount + leftUpCount);
        assertEquals(1.0 / 3, stillCount / ((double) COUNT), 1.0 / 3 * 0.05);
        assertEquals(1.0 / 3, straightUpCount / ((double) COUNT), 1.0 / 3 * 0.05);
        assertEquals(1.0 / 3, leftUpCount / ((double) COUNT), 1.0 / 3 * 0.05);
    }

    @Test
    public void testCollisionLeft() {
        int straightUpCount = 0;
        int stillCount = 0;
        int rightUpCount = 0;

        for (int i = 0; i < COUNT; i++) {
            FishTank.clearTank();
            // Fish at up-right tile
            FishTank.addEntity(9, 9, new Fish());

            bubble.setLocation(10, 10);
            bubble.update();
            if (bubble.getX() == 10 && bubble.getY() == 10) {
                stillCount++;
            } else if (bubble.getX() == 10 && bubble.getY() == 9) {
                straightUpCount++;
            } else if (bubble.getX() == 11 && bubble.getY() == 9) {
                rightUpCount++;
            }
        }

        assertEquals(COUNT, stillCount + straightUpCount + rightUpCount);
        assertEquals(1.0 / 3, stillCount / ((double) COUNT), 1.0 / 3 * 0.05);
        assertEquals(1.0 / 3, straightUpCount / ((double) COUNT), 1.0 / 3 * 0.05);
        assertEquals(1.0 / 3, rightUpCount / ((double) COUNT), 1.0 / 3 * 0.05);
    }

}
