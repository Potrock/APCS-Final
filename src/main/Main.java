package main;

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
import javafx.stage.StageStyle;
import main.game.Game;
import main.uiElements.page.LoginPage;
import main.uiElements.page.CamMenuPage;

public class Main extends Application {

    private static Group pageGroup;
    public static boolean logged_in = false;

    private static double xOffset = 0;
    private static double yOffset = 0;

    public static CamMenuPage menuPage;
    public static LoginPage loginPage;
    public Game game = new Game();

    @Override
    public void start(Stage primary) throws Exception {
        Stage main_stg = new Stage(StageStyle.TRANSPARENT);
        BorderPane layout_pn = new BorderPane();
        Scene main_scn = new Scene(layout_pn);
        pageGroup = new Group();

        menuPage = new CamMenuPage();
        loginPage = new LoginPage();

        layout_pn.getStylesheets().addAll("main/uiElements/ui/rootStylesheet.css");

        //Top
        ImageView imgView = new ImageView(new Image("main/uiElements/ui/logo.png"));
        imgView.setFitWidth(30);
        imgView.setFitHeight(30);
        imgView.setId("button_toolbar");
        Tooltip.install(imgView, new Tooltip("Logout"));
        imgView.setOnMousePressed((event) -> {
            // --> Login
            logged_in = false;
            pageGroup.getChildren().clear();
            pageGroup.getChildren().add(loginPage.get());
        });

        Button quit_btn = new Button("Quit");
        quit_btn.setId("button_toolbar");
        quit_btn.setOnAction((event) -> System.exit(0));

        Button Cam_btn = new Button("Cam");
        Cam_btn.setId("button_toolbar");
        Cam_btn.setOnAction((event) -> {
            // --> Menu
            if(logged_in) {
                goCamMenu();
            }
        });

        ToolBar toolBar = new ToolBar(imgView, new Separator(), quit_btn, Cam_btn);
        toolBar.setOnMousePressed((event) -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        toolBar.setOnMouseDragged((event) -> {
            main_stg.setX(event.getScreenX() - xOffset);
            main_stg.setY(event.getScreenY() - yOffset);
        });


        //Center
        pageGroup.getChildren().add(loginPage.get());

        //
        layout_pn.setTop(toolBar);
        layout_pn.setCenter(pageGroup);

        main_stg.setTitle("HQ Cheat");
        main_stg.setScene(main_scn);
        main_stg.show();
    }

    public static void goCamMenu(){
        pageGroup.getChildren().clear();
        pageGroup.getChildren().add(menuPage.get());
    }

    public static void main(String[] args) {
        launch(args);
    }
}