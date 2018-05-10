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
import javafx.stage.StageStyle;
import sample.uiElements.CameraStage;
import sample.uiElements.page.LoginPage;
import sample.uiElements.page.CamMenuPage;

public class Main extends Application {

    private static Group pageGroup;
    public static boolean logged_in = false;

    private static double xOffset = 0;
    private static double yOffset = 0;

    @Override
    public void start(Stage primary) throws Exception {
        Stage main_stg = new Stage(StageStyle.TRANSPARENT);
        BorderPane layout_pn = new BorderPane();
        Scene main_scn = new Scene(layout_pn);
        pageGroup = new Group();

        CamMenuPage menuPage = new CamMenuPage();
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

        Button Cam_btn = new Button("Cam");
        Cam_btn.setId("button_toolbar");
        Cam_btn.setOnAction((event) -> {
            // --> Menu
            pageGroup.getChildren().clear();
            pageGroup.getChildren().add(menuPage.get());
        });

        Button test_screenschot = new Button("TEST");
        test_screenschot.setId("button_toolbar");
        test_screenschot.setOnAction((event) -> {
            ///screenschot
//            try {
//                Screenshot.saveScreenshot();
//            } catch (AWTException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

            // JSON get
//            JSONUtility.getUsers();

            //Camera
//            try {
//                CameraStage camera = new CameraStage(500);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }

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
        pageGroup.getChildren().add(menuPage.get());

        //
        layout_pn.setTop(toolBar);
        layout_pn.setCenter(pageGroup);

        main_stg.setTitle("HQ Cheat");
        main_stg.setScene(main_scn);
        main_stg.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
