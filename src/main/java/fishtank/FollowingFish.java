package fishtank;

import java.awt.*;

/**
 * A fish.
 */
public class FollowingFish extends Fish {
    /**
     * The entity that our fish is following
     */
    final Fish de;

    /**
     * Constructs a new hungry fish.
     */
    public FollowingFish(Fish f) {
        colour = Color.cyan.darker().darker().darker();
        appearance = "><FOLLOW>";
        goingRight = true;
        de = f;
    }


    /**
     * Turns this fish to fc
     */
    protected void turnToFace() {
        if (de.getX() < this.getX() && goingRight) {
            goingRight = false;
            // assigned appearance to reverse ?
            reverseAppearance();
        } else if (de.getX() > this.getX() && !goingRight) {
            goingRight = true;
            reverseAppearance();
        }
    }

    /**
     * Causes this item to take its turn in the fish-tank simulation.
     */
    public void update() {
        turnToFace();

        // Move one spot to the right or left.
        if (goingRight) {
            if (x + 1 + appearance.length() <= FishTank.width && FishTank.getEntity(x + 1, y) == null) {
                x++;
            }
        } else {    // going left
            if (x > 0 && FishTank.getEntity(x - 1, y) == null) {
                x--;
            }
        }

        if (Math.abs(de.getY() - y) > 2) {
            if (de.getY() < y) {
                if (y > 0 && FishTank.getEntity(x, y - 1) == null) {
                    y -= 1;
                }
            } else {
                if (y + 1 < FishTank.height && FishTank.getEntity(x, y + 1) == null) {
                    y += 1;
                }
            }
        }
    }
}

