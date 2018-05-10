package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.util.LoadLibs;
import nu.pattern.OpenCV;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
         System.setProperty("jna.library.path", "32".equals(System.getProperty("sun.arch.data.model")) ? "lib/win32-x86" : "lib/win32-x86-64");

        OpenCV.loadLocally();
        Mat img = Imgcodecs.imread("HQquestion.png");

        Mat imgGray = new Mat();
        Imgproc.cvtColor(img, imgGray, Imgproc.COLOR_BGR2GRAY);
        Imgcodecs.imwrite("preprocess/img.png", imgGray);

        Mat imgGaussianBlur = new Mat();
        Imgproc.GaussianBlur(imgGray,imgGaussianBlur,new Size(3, 3),0);
        Imgcodecs.imwrite("preprocess/img.png", imgGaussianBlur);

        Mat imgAdaptiveThreshold = new Mat();
        Imgproc.adaptiveThreshold(imgGaussianBlur, imgAdaptiveThreshold, 255, Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C ,Imgproc.THRESH_BINARY, 99, 4);
        Imgcodecs.imwrite("preprocess/img.png", imgAdaptiveThreshold);
        File imageFile = new File("preprocess/img.png");
        int x = 0, y = 200, w = 719, h = 250;
        BufferedImage dst = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Image src = ImageIO.read(new File("preprocess/img.png"));
        dst.getGraphics().drawImage(src, 0, 0, w, h, x, y, x + w, y + h, null);
        ImageIO.write(dst, "png", new File("preprocess/img.png"));
        ITesseract instance = new Tesseract();
        File tessDataFolder = LoadLibs.extractTessResources("tessdata"); // Maven build bundles English data
        instance.setDatapath(tessDataFolder.getPath());
        instance.setLanguage("eng");
        String question = instance.doOCR(imageFile);
        x = 65; y = 500; w = 500; h = 1280/4;
        dst = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        dst.getGraphics().drawImage(src, 0, 0, w, h, x, y, x + w, y + h, null);
        ImageIO.write(dst, "png", new File("preprocess/img.png"));
        String[] answers = instance.doOCR(imageFile).split("\n");

        System.out.println(question);
        //Filter out lowercase L and spaces from front of answers (shitty OCR tbh, oh well) and print them.
        for(String i : answers) {
            if (i.charAt(0) == 'l') {
                i = i.substring(2);
                System.out.println(i);
            }
        }

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}