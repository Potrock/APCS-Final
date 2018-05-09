package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import sample.UIElements.Page.MainMenuPage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        BorderPane layout_pn = new BorderPane();
        Scene main_scn = new Scene(layout_pn);

        main_scn.getStylesheets().addAll("sample/UIElements/Page/PageElements.css", "sample/UIElements/UI/UI.css");

        //Top
        ImageView imgView = new ImageView(new Image("sample/UIElements/UI/logo.png"));
        imgView.setFitWidth(30);
        imgView.setFitHeight(30);
        imgView.setId("button_toolbar");
        imgView.setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent t) {
               // --> Home
            }
        });

        Button quit_btn = new Button("Quit");
        quit_btn.setId("button_toolbar");
        quit_btn.setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent t) {
                System.exit(0);
            }
        });

        Button menu_btn = new Button("Menu");
        menu_btn.setId("button_toolbar");
        menu_btn.setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent t) {
                // --> Menu
            }
        });

        ToolBar toolBar = new ToolBar(imgView, new Separator(), menu_btn, quit_btn);

        //Center
        MainMenuPage menuScene = new MainMenuPage();

        //
        layout_pn.setTop(toolBar);
        layout_pn.setCenter(menuScene.get());

        primaryStage.setTitle("HQ Cheat");
        primaryStage.setScene(main_scn);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
