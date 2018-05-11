package sample.uiElements.page;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import sample.uiElements.CameraStage;
import sample.utility.Screenshot;

import java.awt.*;
import java.io.IOException;

public class CamMenuPage extends PageElement {
    public static boolean cameraOpen = false;

    private Button showCamera_btn;
    private Button takeScreenshot_btn;
    private TextField dim_txtfield;

    CameraStage camera;

    public CamMenuPage() throws Exception{
        super();
        setStyles();

        camera = new CameraStage();

        //take screenshot
        takeScreenshot_btn = new Button("Take Screenshot");
        takeScreenshot_btn.setId("buttonOFF");
        takeScreenshot_btn.setTextFill(Color.LIGHTBLUE);
        takeScreenshot_btn.setTranslateX(30);
        takeScreenshot_btn.setTranslateY(20);
        takeScreenshot_btn.setTextFill(Color.LIGHTBLUE);
        takeScreenshot_btn.setOnAction((event) -> {
            //Camera
            if(cameraOpen)
                getScreenschot();
        });

        //open cam
        dim_txtfield = new TextField("300");

        showCamera_btn = new Button("Show Camera");
        showCamera_btn.setId("button");
        showCamera_btn.setTranslateX(30);
        showCamera_btn.setOnAction((event) -> {
            //Camera
            if(!cameraOpen) {
                try {
                    takeScreenshot_btn.setTextFill(Color.web("#383C95",1.0));
                    camera.setDim(Integer.parseInt(dim_txtfield.getText()));
                    camera.openWindow();
                    showCamera_btn.setText("Hide Camera");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                try {
                    takeScreenshot_btn.setTextFill(Color.LIGHTBLUE);

                    camera.hideWindow();
                    showCamera_btn.setText("Show Camera");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        Label lbl = new Label("Camera width:");
        VBox input_pn = new VBox(lbl, dim_txtfield);
        input_pn.setId("outer_pn");
        Label lbl2 = new Label(" ");
        VBox btn_pn = new VBox(lbl2, showCamera_btn);
        btn_pn.setId("outer_pn");

        HBox openCam_pn = new HBox(input_pn, btn_pn);

        VBox layout_pn = new VBox(openCam_pn, takeScreenshot_btn);
        out.getChildren().addAll(layout_pn);

    }

    void getScreenschot(){
        try {
            System.out.print(camera.cam_w + ", " + (camera.cam_h-44));
//44
            Screenshot.save(Screenshot.captureScreenshot(
                    (int)camera.camera_stg.getX(),
                    (int)camera.camera_stg.getY()+44,
                    camera.cam_w,
                    camera.cam_h-44),
                    "screenshot.png");
        } catch (AWTException | IOException e) {
            e.printStackTrace();
        }
    }

    void setStyles(){
        out.getStylesheets().addAll("sample/uiElements/page/page.css", "sample/uiElements/ui/ui.css");
    }

}
