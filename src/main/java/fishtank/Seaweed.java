package fishtank;

import java.awt.*;

/**
 * Seaweed.
 */
public class Seaweed extends FishTankEntity {

    /**
     * The number of weed segments.
     */
    int l;
    static final int MAX_UPDATE = 200;
    private final int OG_LENGTH;
    private int tickCounter;

    /**
     * Indicates whether the bottom segment is leaning right.
     */
    boolean leanRight;

    /**
     * Constructs a new seaweed item at the specified cursor
     * location (x,y),l segments tall.
     *
     * @param l the number of segments this seaweed is tall.
     */
    public Seaweed(int l) {
        this.l = l;
        OG_LENGTH = l;
        colour = Color.green.darker().darker();
        tickCounter = 0;
    }

    public int getLength() {
        return l;
    }


    /**
     * Draws this fish tank item.  Looks lovely waving in the current, doesn't
     * it?
     *
     * @param g the graphics context in which to draw this item.
     */
    @Override
    public void draw(Graphics g) {

        // WWhich way does the first segment lean?
        boolean lr = leanRight;
        for (int i = 0; i < l; i++) {
            // Draw a "/" seaweed segment: even numbered and leaning to the right.
            if (lr) {
                drawString(g, "/", x, y - i);
            } else {
                drawString(g, "\\", x, y - i);
            }
            lr = !lr;
        }
    }

    private void grow() {
        if (tickCounter == MAX_UPDATE && this.l < OG_LENGTH) {
            this.l++;
            tickCounter = 0;
        }
    }

    /**
     * Causes this item to take its turn in the fish-tank simulation.
     */
    public void update() {
        leanRight = !leanRight;
        tickCounter++;
        grow();

        for (int i = 1; i < l; i++) {
            if (y - i < 0) {
                return;
            }
            for (int j = x; j >= 0; j--) {
                FishTankEntity e = FishTank.getEntity(j, y - i);
                if (e instanceof Fish && !(e instanceof FollowingFish)) {
                    Fish f = (Fish) e;
                    if (f.goingRight && f.getX() + f.appearance.length() > x || !f.goingRight && f.getX() == x) {
                        l = i;
                        return;
                    }
                }
            }
        }
    }


}
