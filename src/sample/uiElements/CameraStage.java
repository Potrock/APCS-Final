package sample.uiElements;

import javafx.scene.Scene;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.uiElements.page.CamMenuPage;

public class CameraStage {

    public Stage camera_stg;
    private Pane camera_pn;

    private static double xOffset = 0;
    private static double yOffset = 0;

    public int cam_w;
    public int cam_h;

    public CameraStage() throws Exception{

        this.camera_stg  = new Stage();
        BorderPane layout = new BorderPane();
        camera_pn = new Pane();
        Scene cam_scn = new Scene(layout);

        layout.setId("transp");
        camera_pn.setId("transp");
        cam_scn.setFill(null);
        this.camera_stg.initStyle(StageStyle.TRANSPARENT);

        cam_scn.getStylesheets().addAll("sample/uiElements/cameraStage.css", "sample/uiElements/ui/rootStylesheet.css");

        //Top

        ImageView imgView = new ImageView(new Image("sample/uiElements/ui/logo.png"));
        imgView.setFitWidth(30);
        imgView.setFitHeight(30);
//        imgView.setId("button_toolbar");

        ToolBar cam_tool = new ToolBar(imgView);
        cam_tool.setOnMousePressed((event) -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        cam_tool.setOnMouseDragged((event) -> {
            this.camera_stg.setX(event.getScreenX() - xOffset);
            this.camera_stg.setY(event.getScreenY() - yOffset);
        });

        layout.setCenter(camera_pn);
        layout.setTop(cam_tool);

        this.camera_stg.setTitle("Camera");
        this.camera_stg.setScene(cam_scn);
        this.camera_stg.setResizable(false);

    }

    public void setDim(int width){
        cam_w = width;
        cam_h = width*16/9;
        // Center
        Rectangle question_reticle = new Rectangle(592*cam_w/720, 240*cam_h/1280);
        question_reticle.setTranslateX(64*cam_w/720);
        question_reticle.setTranslateY(224*cam_h/1280);
        question_reticle.setId("reticle");

        Rectangle ans1_reticle = new Rectangle(592*cam_w/720, 96*cam_h/1280);
        ans1_reticle.setTranslateX(64*cam_w/720);
        ans1_reticle.setTranslateY(496*cam_h/1280);
        ans1_reticle.setId("reticle");

        Rectangle ans2_reticle = new Rectangle(592*cam_w/720, 96*cam_h/1280);
        ans2_reticle.setTranslateX(64*cam_w/720);
        ans2_reticle.setTranslateY((624)*cam_h/1280);
        ans2_reticle.setId("reticle");

        Rectangle ans3_reticle = new Rectangle(592*cam_w/720, 96*cam_h/1280);
        ans3_reticle.setTranslateX(64*cam_w/720);
        ans3_reticle.setTranslateY((753)*cam_h/1280);
        ans3_reticle.setId("reticle");

        Rectangle border_reticle = new Rectangle(cam_w, cam_h);
        border_reticle.setId("reticle");

        camera_pn.getChildren().addAll(border_reticle, question_reticle, ans1_reticle, ans2_reticle, ans3_reticle);

    }

    public void openWindow(){
        CamMenuPage.cameraOpen = true;
        this.camera_stg.show();
    }

    public void hideWindow(){
        CamMenuPage.cameraOpen = false;
        camera_pn.getChildren().clear();
        this.camera_stg.hide();
    }

}
