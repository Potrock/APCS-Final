package main.utility;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Screenshot handeling
 *
 * @author sawyertang
 */
public class Screenshot {

    public static BufferedImage captureScreenshot(int x, int y, int w, int h) throws AWTException, IOException {
        Robot robot = new Robot();
        return robot.createScreenCapture(new Rectangle(x, y, w, h));
    }

}
