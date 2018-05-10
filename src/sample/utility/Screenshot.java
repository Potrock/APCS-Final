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

    public static void saveScreenshot() throws AWTException, IOException{
        Robot robot = new Robot();

        BufferedImage screenShot = robot.createScreenCapture(new Rectangle(0, 0, 600, 600));
        ImageIO.write(screenShot, "png", new File("myScreenShot.png"));
    }

}
