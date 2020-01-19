package fishtank;

import java.awt.*;

/**
 * In Java, an "abstract class" is just a class that doesn't implement
 * some of its methods.
 * <p>
 * In CSC148, you've seen things like this before, where every method in a class
 * simply raised a NotImplementedError. Those are also called abstract classes,
 * and fulfill a similar purpose (try replacing a usage of FishTankEntity with
 * Object in FishTank.java and see if you can understand why it doesn't work.)
 */
public abstract class FishTankEntity {

    protected int x;
    protected int y;
    protected String appearance;
    protected Color colour;

    private boolean exists = true;

    /**
     * The font used to draw instances of this class.
     */
    static final Font FONT = new Font("Monospaced", Font.PLAIN, 10);

    public FishTankEntity() {
    }

    abstract void update();

    /**
     * Set this item's location.
     *
     * @param a the first coordinate.
     * @param b the second coordinate.
     */

    public void setLocation(int a, int b) {
        x = a;
        y = b;
    }

    void delete() {
        exists = false;
    }

    boolean exists() {
        return this.exists;
    }

    protected int getX() {
        return x;
    }

    protected int getY() {
        return y;
    }


    /**
     * Draws the given string in the given graphics context at
     * at the given cursor location.
     *
     * @param g the graphics context in which to draw the string.
     * @param s the string to draw.
     * @param x the x-coordinate of the string's cursor location.
     * @param y the y-coordinate of the string's cursor location.
     */
    public void drawString(Graphics g, String s, int x, int y) {
        g.setColor(colour);
        g.setFont(FONT);
        FontMetrics fm = g.getFontMetrics(FONT);
        g.drawString(s, x * fm.charWidth('W'), y * fm.getAscent());
    }

    /**
     * Draws this fish tank item.
     *
     * @param g the graphics context in which to draw this item.
     */
    public void draw(Graphics g) {
        drawString(g, appearance, x, y);
    }

}
