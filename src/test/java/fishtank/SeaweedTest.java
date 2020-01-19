package fishtank;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SeaweedTest {
    private Seaweed seaweed;

    @Before
    public void setUp() {
        seaweed = new Seaweed(5);
    }

    @Test
    public void testGrowBack() {
        seaweed.l = 4;
        // Check if it grows back
        for (int i = 0; i < Seaweed.MAX_UPDATE; i++) {
            seaweed.update();
        }
        assertEquals(5, seaweed.getLength());

        // Check if it stops growing if already at max height
        for (int i = 0; i < Seaweed.MAX_UPDATE; i++) {
            seaweed.update();
        }
        assertEquals(5, seaweed.getLength());

    }

    @Test
    public void testWontEatRoot() {
        FishTank.clearTank();
        Fish fish = new Fish();
        fish.setGoingRight(false);

        seaweed.l = 1;
        seaweed.setLocation(10, 15);
        FishTank.addEntity(10, 15, fish);
        seaweed.update();
        assertEquals(1, seaweed.getLength());

    }

    @Test
    public void testEatSeaweedFromRight() {
        FishTank.clearTank();
        Fish fish = new Fish();
        fish.setGoingRight(false);
        seaweed.setLocation(10, 15);

        FishTank.addEntity(11, 12, fish);
        seaweed.update();
        assertEquals(5, seaweed.getLength());

        FishTank.addEntity(10, 12, fish);
        seaweed.update();
        assertEquals(3, seaweed.getLength());
    }

    @Test
    public void testEatSeaweedFromLeft() {
        FishTank.clearTank();
        Fish fish = new Fish();
        fish.setGoingRight(true);
        seaweed.setLocation(10, 15);

        FishTank.addEntity(7, 12, fish);
        seaweed.update();
        assertEquals(5, seaweed.getLength());

        FishTank.addEntity(8, 12, fish);
        seaweed.update();
        assertEquals(3, seaweed.getLength());
    }

}
