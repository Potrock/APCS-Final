package sample.uiElements;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CameraStage {

    public Stage camera_stg;
    public Scene cam_scn;

    private static double xOffset = 0;
    private static double yOffset = 0;

    private int cam_w = 500;
    private int cam_h = cam_w*16/9;

    public CameraStage() throws Exception{
        camera_stg  = new Stage();
        BorderPane layout = new BorderPane();
        Pane camera_pn = new Pane();
        cam_scn = new Scene(layout);

        layout.setId("transp");
        camera_pn.setId("transp");
        cam_scn.setFill(null);
        camera_stg.initStyle(StageStyle.TRANSPARENT);

        cam_scn.getStylesheets().addAll("sample/uiElements/cameraStage.css", "sample/uiElements/ui/rootStylesheet.css");

        //Top

        Button close_btn = new Button("Close");
        close_btn.setId("button_toolbar");
        close_btn.setOnAction((event) -> {
            camera_stg.close();
        });

        ToolBar cam_tool = new ToolBar(close_btn);
        cam_tool.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        cam_tool.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                camera_stg.setX(event.getScreenX() - xOffset);
                camera_stg.setY(event.getScreenY() - yOffset);
            }
        });

        // Center
        Rectangle question_reticle = new Rectangle(cam_w, cam_h);
        question_reticle.setTranslateX(cam_w);
        question_reticle.setTranslateY(cam_h);
        question_reticle.setId("reticle");
//        System.out.println("question_reticle at: " + (cam_w/(720.0f/80.0f)) + ", " + (cam_h/(1280.0f/200.0f)));

        //80, 200
        //640, 270

        Rectangle border_reticle = new Rectangle(cam_w, cam_h);
        border_reticle.setId("reticle");

        camera_pn.getChildren().addAll(border_reticle, question_reticle);

        layout.setCenter(camera_pn);
        layout.setTop(cam_tool);

        camera_stg.setTitle("HQ Cheat");
        camera_stg.setScene(cam_scn);
        camera_stg.show();

//        Stage stage = new Stage();
//        try {
//            Label lbl = new Label("LABEL");
//            VBox p = new VBox(lbl);
//
//            //make the background of the label white and opaque
//            lbl.setStyle("-fx-background-color: rgba(255, 255, 255, 1);");
//
//            //add some borders to visualise the element' locations
//            lbl.setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, null, null)));
//            p.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, null, null)));
//
//            Scene scene = new Scene(p);
//            stage.setScene(scene);
//
//            //this is where the transparency is achieved:
//            //the three layers must be made transparent
//            //(i)  make the VBox transparent (the 4th parameter is the alpha)
//            p.setStyle("-fx-background-color: rgba(0, 0, 0, 0);");
//            //(ii) set the scene fill to transparent
//            scene.setFill(null);
//            //(iii) set the stage background to transparent
//            stage.initStyle(StageStyle.TRANSPARENT);
//
//            stage.setWidth(200);
//            stage.setHeight(100);
//            stage.show();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }



    }

}
