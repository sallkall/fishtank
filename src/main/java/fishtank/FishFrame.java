package fishtank;

import java.awt.*;
import javax.swing.*;

/**
 * Displays the fish tank.
 */

public class FishFrame extends JFrame {

    public FishFrame() {
        JComponent tankCanvas = new JComponent() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Get my width and height.
                int w = getBounds().width;
                int h = getBounds().height;

                // Paint the window white.
                g.setColor(Color.white);
                g.fillRect(0, 0, w, h);

                for (int x = 0; x < FishTank.width; x++) {
                    for (int y = 0; y < FishTank.height; y++) {
                        FishTankEntity e = FishTank.getEntity(x, y);
                        if (e != null) {
                            e.draw(g);
                        }
                    }
                }
            }
        };
        this.

                add(tankCanvas);
        this.

                setLocation(0, 0);
        this.

                setVisible(true);
    }
}
