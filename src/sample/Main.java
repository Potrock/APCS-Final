package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sample.uiElements.page.LoginPage;
import sample.uiElements.page.MainMenuPage;
import sample.utility.Screenshot;

import java.awt.*;
import java.io.IOException;

public class Main extends Application {

    private static Group pageGroup;
    public static boolean logged_in = false;

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane layout_pn = new BorderPane();
        Scene main_scn = new Scene(layout_pn);
        pageGroup = new Group();

        MainMenuPage menuPage = new MainMenuPage();
        LoginPage loginPage = new LoginPage();

        layout_pn.getStylesheets().addAll("sample/uiElements/ui/rootStylesheet.css");

        //Top
        ImageView imgView = new ImageView(new Image("sample/uiElements/ui/logo.png"));
        imgView.setFitWidth(30);
        imgView.setFitHeight(30);
        imgView.setId("button_toolbar");
        Tooltip.install(imgView, new Tooltip("Login page"));
        imgView.setOnMousePressed((event) -> {
            // --> Login
            pageGroup.getChildren().clear();
            pageGroup.getChildren().add(loginPage.get());
        });

        Button quit_btn = new Button("Quit");
        quit_btn.setId("button_toolbar");
        quit_btn.setOnAction((event) -> {
            System.exit(0);
        });

        Button menu_btn = new Button("Menu");
        menu_btn.setId("button_toolbar");
        menu_btn.setOnAction((event) -> {
            // --> Menu
            pageGroup.getChildren().clear();
        });

        Button test_screenschot = new Button("TEST TAKE SCREENSHOT");
        test_screenschot.setId("button_toolbar");
        test_screenschot.setOnAction((event) -> {
            try {
                Screenshot.saveScreenshot();
            } catch (AWTException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        ToolBar toolBar = new ToolBar(imgView, new Separator(), menu_btn, quit_btn, test_screenschot);

        //Center
        pageGroup.getChildren().add(loginPage.get());

        //
        layout_pn.setTop(toolBar);
        layout_pn.setCenter(pageGroup);

        primaryStage.setTitle("HQ Cheat");
        primaryStage.setScene(main_scn);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
