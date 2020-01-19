package fishtank;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashSet;
import java.util.Set;

/**
 * A fish tank simulation.
 */
public class FishTank {

    /**
     * The width of a character.
     */
    private static final int charWidth = 6;
    /**
     * The height of a character.
     */
    private static final int charHeight = 10;

    /**
     * The width (in pixels) of the frame
     */
    private static final int frameWidth = 640;

    /**
     * The height (in pixels) of the frame
     */
    private static final int frameHeight = 480;

    /**
     * The width (in entities) of the whole tank
     */
    public static final int width = frameWidth / charWidth;

    /**
     * The height (in entities) of the whole tank
     */
    public static final int height = frameHeight / charHeight;


    /**
     * (int)(640/6) columns, (int)(480/10) rows.
     */
    private static FishTankEntity[][] entities =
            new FishTankEntity[width][height];

    private static boolean running = true;

    public static void addEntity(int x, int y, FishTankEntity e) {
        entities[x][y] = e;
        e.setLocation(x, y);
    }

    public static FishTankEntity getEntity(int x, int y) {
        return entities[x][y];
    }

    public static void clearTank() {
        entities = new FishTankEntity[width][height];
    }

    public static void main(String[] args) {

        // The window in which the fish swim.
        FishFrame f = new FishFrame();
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                running = false;
            }
        });

        addEntity(23, 18, new Fish());
        addEntity(6, 12, new Fish());
        addEntity(17, 4, new Fish());
        addEntity(15, 28, new Fish());
        addEntity(23, 18, new Fish());
        addEntity(16, 35, new Fish());
        addEntity(6, 22, new Fish());
        addEntity(16, 20, new HungryFish());
        addEntity(10, 20, new FollowingFish((Fish) getEntity(23, 18)));
        addEntity(24, 33, new Seaweed(6));
        addEntity(32, 25, new Seaweed(7));
        addEntity(13, 25, new Seaweed(5));

        // Show it all!
        f.setSize(frameWidth, frameHeight);
        f.setLocation(10, 10);
        f.setVisible(true);

        while (running) {
            Set<FishTankEntity> updatedItems = new HashSet<>();
            Set<FishTankEntity> seaweeds = new HashSet<>();
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    FishTankEntity e = entities[x][y];
                    if (e != null && !updatedItems.contains(e)) {
                        if (e instanceof Seaweed) {
                            seaweeds.add(e);
                        } else {
                            entities[x][y].update();
                            updatedItems.add(e);
                            entities[x][y] = null;
                            if (e.exists()) {
                                entities[e.getX()][e.getY()] = e;
                            }
                        }
                    }
                }
            }

            for (FishTankEntity e : seaweeds) {
                e.update();
            }

            f.repaint();

            // Wait .3 seconds before redoing the queue.
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                //not a big deal
            }
        }

    }
}
