package main.uiElements;

import javafx.scene.Scene;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.uiElements.page.CamMenuPage;

//TODO: some fixups

/**
 * Declares and manages a new Stage acting as a 'Camera' for the application to screenshot the HQ window
 *
 * @author sawyertang
 */
public class CameraStage {

    public Stage camera_stg;
    private Pane camera_pn;

    private static double xOffset = 0;
    private static double yOffset = 0;

    public static int cam_w;
    public static int cam_h;

    /**
     * Constructor inits Stage and its elements
     */
    public CameraStage() throws Exception {

        this.camera_stg = new Stage();
        BorderPane layout = new BorderPane();
        camera_pn = new Pane();
        Scene cam_scn = new Scene(layout);

        layout.setId("transp");
        camera_pn.setId("transp");
        cam_scn.setFill(null);
        this.camera_stg.initStyle(StageStyle.TRANSPARENT);

        cam_scn.getStylesheets().addAll("main/uiElements/cameraStage.css", "main/uiElements/ui/rootStylesheet.css");

        //Top
        ImageView imgView = new ImageView(new Image("main/uiElements/ui/logo.png"));
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

    /**
     * sets the size of the Stage and manages reticles to help the user with window position.
     *
     * @param width The inputted specified width of the window
     */
    public void setDim(int width) {
        cam_w = width;
        cam_h = width * 16 / 9;
        // Center
        Rectangle question_reticle = new Rectangle(592 * cam_w / 720, 240 * cam_h / 1280);
        question_reticle.setTranslateX(64 * cam_w / 720);
        question_reticle.setTranslateY(224 * cam_h / 1280);
        question_reticle.setId("reticle");

        Rectangle ans1_reticle = new Rectangle(592 * cam_w / 720, 96 * cam_h / 1280);
        ans1_reticle.setTranslateX(64 * cam_w / 720);
        ans1_reticle.setTranslateY(496 * cam_h / 1280);
        ans1_reticle.setId("reticle");

        Rectangle ans2_reticle = new Rectangle(592 * cam_w / 720, 96 * cam_h / 1280);
        ans2_reticle.setTranslateX(64 * cam_w / 720);
        ans2_reticle.setTranslateY((624) * cam_h / 1280);
        ans2_reticle.setId("reticle");

        Rectangle ans3_reticle = new Rectangle(592 * cam_w / 720, 96 * cam_h / 1280);
        ans3_reticle.setTranslateX(64 * cam_w / 720);
        ans3_reticle.setTranslateY((753) * cam_h / 1280);
        ans3_reticle.setId("reticle");

        Rectangle border_reticle = new Rectangle(cam_w, cam_h);
        border_reticle.setId("reticle");

        camera_pn.getChildren().addAll(border_reticle, question_reticle, ans1_reticle, ans2_reticle, ans3_reticle);

    }

    /**
     * Shows Stage
     */
    public void openWindow() {
        CamMenuPage.cameraOpen = true;
        this.camera_stg.show();
    }

    /**
     * Hides Stage
     */
    public void hideWindow() {
        CamMenuPage.cameraOpen = false;
        camera_pn.getChildren().clear();
        this.camera_stg.hide();
    }

}
