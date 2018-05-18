package main.uiElements.page;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import main.game.Game;
import main.uiElements.CameraStage;
import main.utility.Screenshot;
import net.sourceforge.tess4j.TesseractException;

import java.awt.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class CamMenuPage extends PageElement {
    public static boolean cameraOpen = false;

    private Button showCamera_btn;
    private Button takeScreenshot_btn;
    private TextField dim_txtfield;
    private Label screenshotFeedback_lbl;
    private StackPane answer_pn;
    private ImageView imgView;
    BufferedImage screenshot;

    private boolean isImage = false;

    private CameraStage camera;

    public CamMenuPage() throws Exception{
        super();
        setStyles();

        camera = new CameraStage();

        //open cam
        dim_txtfield = new TextField("300");
        dim_txtfield.setOnKeyPressed(ke -> {
            if (ke.getCode().equals(KeyCode.ENTER))
            {
                if(!cameraOpen) {
                    try {
                        takeScreenshot_btn.setTextFill(Color.web("#383C95",1.0));
                        camera.setDim(Integer.parseInt(dim_txtfield.getText()));
                        camera.openWindow();
                        showCamera_btn.setText("Hide Camera");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        showCamera_btn = new Button("Show Camera");
        showCamera_btn.setId("button");
        showCamera_btn.setTranslateX(10);
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

        //take screenshot
        takeScreenshot_btn = new Button("Take Screenshot");
        takeScreenshot_btn.setId("buttonOFF");
        takeScreenshot_btn.setTextFill(Color.LIGHTBLUE);
        takeScreenshot_btn.setTranslateX(7);
        takeScreenshot_btn.setOnAction((event) -> {
            //Camera
            if(cameraOpen) {
                screenshotFeedback_lbl.setText("Screenshot Taken!");
                if(isImage)
                    answer_pn.getChildren().remove(imgView);
                try {
                    screenshot = Screenshot.captureScreenshot((int)camera.camera_stg.getX() /*+ 226*/, (int)camera.camera_stg.getY()/*+44*//*+127*/, camera.cam_w, camera.cam_h/*-44*/);
                    imgView = new ImageView(SwingFXUtils.toFXImage(screenshot, null));
                } catch (AWTException | IOException e) {
                    e.printStackTrace();
                }
                imgView.setFitWidth(100);
                imgView.setFitHeight(100*16/9);
                imgView.setTranslateX(-100);
                answer_pn.getChildren().add(imgView);
                try {
                    Game.handleScreenshot(getScreenshot());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (TesseractException e) {
                    e.printStackTrace();
                }
                isImage = true;
            }
        });

        screenshotFeedback_lbl = new Label(" ");
        screenshotFeedback_lbl.setTranslateX(20);
        screenshotFeedback_lbl.setTranslateY(25);

        HBox takeScreenshot_pn = new HBox(takeScreenshot_btn, screenshotFeedback_lbl);

        //Answer
        Rectangle border = new Rectangle(325, 235, Color.TRANSPARENT);
        border.setId("border");

        Label answerTitle_lbl = new Label("Answer:");
        answerTitle_lbl.setStyle("-fx-underline: true;");
        answerTitle_lbl.setTranslateX(-130);
        answerTitle_lbl.setTranslateY(-105);

        Label answer_lbl = new Label(" ");

        answer_pn = new StackPane(border, answerTitle_lbl, answer_lbl);
        answer_pn.setTranslateX(10);

        VBox layout_pn = new VBox(openCam_pn, takeScreenshot_pn, answer_pn);
        layout_pn.setSpacing(18);
        out.getChildren().addAll(layout_pn);

    }

    private BufferedImage getScreenshot(){

        if (screenshot != null) {
            return screenshot;
        } else {

            try {
//            System.out.println(camera.cam_w + ", " + (camera.cam_h-44));
                return Screenshot.save(Screenshot.captureScreenshot((int) camera.camera_stg.getX(), (int) camera.camera_stg.getY()/*+44*/, camera.cam_w, camera.cam_h/*-44*/));
            } catch (AWTException | IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    void setStyles(){
        out.getStylesheets().addAll("main/uiElements/page/page.css", "main/uiElements/ui/ui.css");
    }

}
