package main;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.uiElements.page.LoginPage;
import main.uiElements.page.CamMenuPage;

/**
 * The Main Class inits and implements the main stage of the application as well as handling the Page objects
 *
 * @author sawyertang
 */
public class Main extends Application {

    private static Group pageGroup;
    private static CamMenuPage menuPage;
    private static LoginPage loginPage;
    public static Label loginPrompt_lbl;

    private static double xOffset = 0;
    private static double yOffset = 0;
    public static boolean logged_in = false;

    /**
     * Start method needed for a JavaFX application
     *
     * @param primary not actually included in the program... just needed for JavaFX method declaration
     */
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

        loginPrompt_lbl = new Label(" ");
        loginPrompt_lbl.setId("label_toolbar");

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
            if (logged_in) {
                goCamMenu();
            } else {
                loginPrompt_lbl.setText("Login!");
            }
        });

        ToolBar toolBar = new ToolBar(imgView, new Separator(), quit_btn, Cam_btn, loginPrompt_lbl);
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

    /**
     * Enables class LoginPage is able to change the Page to CamMenuPage
     */
    public static void goCamMenu(){
        pageGroup.getChildren().clear();
        pageGroup.getChildren().add(menuPage.get());
    }

    /**
     * main Java method calls the JavaFX launch(String... args) method which stats the application
     *
     * @param args Java args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
