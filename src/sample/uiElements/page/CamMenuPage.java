package sample.uiElements.page;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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

        //open cam
        dim_txtfield = new TextField("300");

        showCamera_btn = new Button("Show Camera");
        showCamera_btn.setTranslateX(30);
        showCamera_btn.setOnAction((event) -> {
            //Camera
            if(!cameraOpen) {
                try {
                    camera.setDim(Integer.parseInt(dim_txtfield.getText()));
                    camera.openWindow();
                    showCamera_btn.setText("Hide Camera");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                try {
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

        //take screenshot
        takeScreenshot_btn = new Button("Show Camera");
        takeScreenshot_btn.setTranslateX(30);
        takeScreenshot_btn.setOnAction((event) -> {
            //Camera
            getScreenschot();
        });

        VBox layout_pn = new VBox(openCam_pn);

        out.getChildren().addAll(layout_pn);

    }

    void getScreenschot(){
        try {
            Screenshot.save(Screenshot.captureScreenshot(0, 0, 2, 2), "screenshot.png");
        } catch (AWTException | IOException e) {
            e.printStackTrace();
        }
    }

    void setStyles(){
        out.getStylesheets().addAll("sample/uiElements/page/page.css", "sample/uiElements/ui/ui.css");
    }

}
