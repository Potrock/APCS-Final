package sample.utility;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 *
 */
public class Screenshot {

    public static void saveScreenshot(int x, int y, int w, int h) throws AWTException, IOException{
        Robot robot = new Robot();

        BufferedImage screenShot = robot.createScreenCapture(new Rectangle(x, y, w, h));
        ImageIO.write(screenShot, "png", new File("screenshot.png"));
    }

}
