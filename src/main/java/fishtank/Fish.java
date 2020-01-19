package fishtank;

import java.awt.*;

/**
 * A fish.
 */
public class Fish extends FishTankEntity {

    /**
     * Indicates whether this fish is moving right.
     */
    boolean goingRight;

    /**
     * Constructs a new fish.
     */

    public Fish() {
        colour = Color.cyan.darker().darker().darker();
        appearance = "><>";
        goingRight = true;
    }

    /**
     * Causes this fish to blow a bubble.
     */
    protected void blowBubble() {

        Bubble b = new Bubble();
        if (y > 0 && FishTank.getEntity(x, y - 1) == null) {
            System.out.println(x + " " + (y - 1));
            FishTank.addEntity(x, y - 1, b);
        } // else stay still

    }

    /**
     * Build and initialize this fish's forward and backward
     * appearances.
     */
    protected void reverseAppearance() {
        StringBuilder reverseBuilder = new StringBuilder();
        for (int i = appearance.length() - 1; i >= 0; i--)
            switch (appearance.charAt(i)) {
                case ')':
                    reverseBuilder.append('(');
                    break;
                case '(':
                    reverseBuilder.append(')');
                    break;
                case '>':
                    reverseBuilder.append('<');
                    break;
                case '<':
                    reverseBuilder.append('>');
                    break;
                case '}':
                    reverseBuilder.append('{');
                    break;
                case '{':
                    reverseBuilder.append('}');
                    break;
                case '[':
                    reverseBuilder.append(']');
                    break;
                case ']':
                    reverseBuilder.append('[');
                    break;
                default:
                    reverseBuilder.append(appearance.charAt(i));
                    break;
            }
        appearance = reverseBuilder.toString();
    }


    /**
     * Turns this fish around, causing it to reverse direction.
     */
    protected void turnAround() {
        goingRight = !goingRight;
        reverseAppearance();
    }

    public void setGoingRight(boolean goingRight) {
        if (this.goingRight != goingRight) {
            turnAround();
        }
    }

    /**
     * Causes this item to take its turn in the fish-tank simulation.
     */
    @Override
    public void update() {

        // Figure out whether I turn around.
        double d = Math.random();
        if (d < 0.1) {
            turnAround();
        }

        // Move one spot to the right or left.

        if (goingRight) {
            if (x + 1 + appearance.length() < FishTank.width && FishTank.getEntity(x + 1, y) == null) {
                x++;
            } else {
                turnAround();
            }
        } else {    // going left
            if (x > 0 && FishTank.getEntity(x - 1, y) == null) {
                x--;
            } else {
                turnAround();
            }
        }

        // Figure out whether to move up or down, or neither.
        d = Math.random();
        if (d < 0.1) {  //doing down
            if (y + 1 < FishTank.height && FishTank.getEntity(x, y + 1) == null) {
                y++;
            }
        } else if (d < 0.2) {
            if (y > 0 && FishTank.getEntity(x, y - 1) == null) {
                y--;
            }
        }

        // Figure out whether I blow a bubble.
        d = Math.random();
        if (d < 0.1) {
            blowBubble();
        }

    }
}
