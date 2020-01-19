package fishtank;

import java.awt.*;

/**
 * A bubble.
 */
public class Bubble extends FishTankEntity {

    /**
     * Use for random movement left and right.
     */
    public double d;

    /**
     * Constructs a new bubble at the specified cursor location (x, y).
     */
    public Bubble() {
        super();
        // Get a nice-looking grey for the bubble
        colour = Color.gray.darker().darker().darker();
        // start off with . as the appearance
        appearance = ".";
    }

    /**
     * Causes this item to take its turn in the fish-tank simulation, moving straight up.
     */
    private void changeAppearance() {
        d = Math.random();
        if (d < 0.05) {
            // If the appearance is a ., change it to an o
            if (appearance.equals(".")) appearance = "o";
                // If the appearance is an o, change it to a O
            else if (appearance.equals("o")) appearance = "O";
        }
    }

    public void floatStraightUp() {
        if (y > 0 && FishTank.getEntity(x, y - 1) == null) {
            y--;
            changeAppearance();
        }

    }

    public void floatLeftUp() {
        if (y > 0 && x - 1 >= 0 && FishTank.getEntity(x - 1, y - 1) == null) {
            y--;
            x -= 1; //left
            changeAppearance();
        }
    }

    /**
     * Causes this item to take its turn in the fish-tank simulation.
     */
    public void floatRightUp() {
        if (y > 0 && x + 1 < FishTank.width && FishTank.getEntity(x + 1, y - 1) == null) {
            y--;
            x += 1;// right
            changeAppearance();
        }
    }

    @Override
    public void update() {
        if (y == 0) {
            delete();
            return;
        }
        d = Math.random();

        if (d < 0.33) {
            floatStraightUp();
        } else if (d < 0.66) {
            floatRightUp();
        } else {
            floatLeftUp();
        }
    }

}
