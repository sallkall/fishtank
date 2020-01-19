package fishtank;

import java.awt.*;


/**
 * A fish.
 */
public class HungryFish extends Fish {


    public HungryFish() {
        colour = Color.cyan.darker().darker().darker();
        appearance = "><MEHUNGRY>";
        goingRight = true;
    }


}
