package main.game;


import main.utility.GoogleSearch;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.LoadLibs;
import nu.pattern.OpenCV;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

//TODO: finish Javadoc comments at <desc. here>'s

/**
 * <desc. here>
 *
 * @author patrickblais
 */
public class Game {

    /**
     * Crops and pulls text from a BufferedImage and returns the answer
     *
     * @param buffImg The BufferedImage the text will be pulled from
     * @return String answer to the question
     */
    public static String handleScreenshot(BufferedImage buffImg) throws IOException, TesseractException {
        System.setProperty("jna.library.path", "32".equals(System.getProperty("sun.arch.data.model")) ? "lib/win32-x86" : "lib/win32-x86-64");

        OpenCV.loadLocally();
        Mat img = bufferedImageToMat(buffImg);

        Mat imgGray = new Mat();
        Imgproc.cvtColor(img, imgGray, Imgproc.COLOR_BGR2GRAY);


        //DOESNT WORK WELL WITH THE NEW SCREENSHOT SYSTEM FROM SAWYER. TOO LOW RES FOR AdaptiveThreshold and GaussianBlur.

//        Mat imgGaussianBlur = new Mat();
//        Imgproc.GaussianBlur(imgGray,imgGaussianBlur,new Size(3, 3),0);
//        Imgcodecs.imwrite("preprocess/img.png", imgGaussianBlur);

//        Mat imgAdaptiveThreshold = new Mat();
//        Imgproc.adaptiveThreshold(imgGray, imgAdaptiveThreshold, 255, Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C ,Imgproc.THRESH_BINARY, 99, 4);
//        Imgcodecs.imwrite("preprocess/img.png", imgAdaptiveThreshold);

        //TODO: Merge with Sawyer's screenshot code
        //TODO: Retrieve x,y,w,h from screenshot system
        //TODO: FIX CROPPING WITH SCALED CAMERA... Scale cropping like with reticles

        int x = 0, y = 100, w = 300, h = 100;
        BufferedImage dst = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Image src = matToBufferedImage(imgGray);
        dst.getGraphics().drawImage(src, 0, 0, w, h, x, y, x + w, y + h, null);
        ITesseract instance = new Tesseract();
        File tessDataFolder = LoadLibs.extractTessResources("tessdata"); // Maven build bundles English data
        instance.setDatapath(tessDataFolder.getPath());
        instance.setLanguage("eng");
        String question = instance.doOCR(dst);
        x = 0;
        y = 200;
        w = 300;
        h = 170;
        dst = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        dst.getGraphics().drawImage(src, 0, 0, w, h, x, y, x + w, y + h, null);
        String[] answers = instance.doOCR(dst).split("\n");
        Imgcodecs.imwrite("img.png", bufferedImageToMat(dst));

        System.out.println(question);
        //Filter out lowercase L and spaces from front of answers (shitty OCR tbh, oh well) and print them.
        for (int i = 0; i < answers.length; i++) {
            if (answers[i].charAt(0) == 'l' || answers[i].charAt(0) == '1' || answers[i].charAt(0) == '|') {
                answers[i] = answers[i].substring(2);
                System.out.println(answers[i]);
            } else {
                System.out.println(answers[i]);
            }
        }
        System.out.println("Answer: " + GoogleSearch.search(question, answers));
        return "Answer: " + GoogleSearch.search(question, answers);
    }

    /**
     * Converts a BufferedImage to a Mat
     *
     * @param image The BufferedImage to be converted
     * @return The converted Mat
     */
    private static Mat bufferedImageToMat(BufferedImage image) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", byteArrayOutputStream);
        byteArrayOutputStream.flush();
        return Imgcodecs.imdecode(new MatOfByte(byteArrayOutputStream.toByteArray()), Imgcodecs.CV_LOAD_IMAGE_UNCHANGED);
    }

    /**
     * Converts a Mat to a BufferedImage
     *
     * @param matrix The Mat to be converted
     * @return The converted BufferedImage
     */
    private static BufferedImage matToBufferedImage(Mat matrix) throws IOException {
        MatOfByte mob = new MatOfByte();
        Imgcodecs.imencode(".jpg", matrix, mob);
        return ImageIO.read(new ByteArrayInputStream(mob.toArray()));
    }
}
