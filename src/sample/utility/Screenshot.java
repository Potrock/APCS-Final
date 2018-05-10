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

// The hard part is knowing WHERE to capture the screen shot from
        BufferedImage screenShot = robot.createScreenCapture(new Rectangle(0, 0, 600, 600));

// Save your screen shot with its label
        ImageIO.write(screenShot, "png", new File("myScreenShot.png"));
    }

}
