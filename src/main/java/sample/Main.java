package main.java.sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.sourceforge.tess4j.*;

import java.io.File;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
        System.out.println();
        System.out.println(getImgText("HQquestion.png"));
    }


    public static void main(String[] args) {
        launch(args);
    }


    public String getImgText(String imageLocation) {
        ITesseract instance = new Tesseract();
        try
        {
            String imgText = instance.doOCR(new File("C:\\Users\\pwbla\\Downloads\\imagetest.png"));
            return imgText;
        }
        catch (TesseractException e)
        {
            e.getMessage();
            return "Error while reading image";
        }
    }
}