package main.utility;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 *
 */
public class Screenshot {

    public static BufferedImage captureScreenshot(int x, int y, int w, int h) throws AWTException, IOException{
        Robot robot = new Robot();

        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screen.getWidth();
        double height = screen.getHeight();
        return robot.createScreenCapture(new Rectangle(x, y, w, h));
    }

    public static BufferedImage save(BufferedImage c) throws IOException{
        return c;
    }

}
