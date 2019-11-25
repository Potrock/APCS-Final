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
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Inits all UI elements for CamMenuPage
 *
 * @author sawyertang
 */
public class CamMenuPage extends PageElement {

    public static boolean cameraOpen = false;

    private boolean isImage = false;

    private Button showCamera_btn;
    private Button takeScreenshot_btn;
    private TextField dim_txtfield;
    private Label screenshotFeedback_lbl;
    private StackPane answer_pn;
    private ImageView imgView;
    private Label answer_lbl = new Label();

    private BufferedImage screenshot;

    private CameraStage camera;


    /**
     * Constructor that inits all UI elements for CamMenuPage and adds them to a JavaFX Group
     */
    public CamMenuPage() throws Exception{
        super(); // superclass constructor call
        this.setStyles();

        this.camera = new CameraStage(); // stage init

        // camWidth text field. Opens camStage on keyboard click 'ENTER'.
        this.dim_txtfield = new TextField("300");
        this.dim_txtfield.setOnKeyPressed(ke -> {
            if (ke.getCode().equals(KeyCode.ENTER))
            {
                if(!cameraOpen) {
                    try {
                        this.takeScreenshot_btn.setTextFill(Color.web("#383C95",1.0));
                        this.camera.setDim(Integer.parseInt(this.dim_txtfield.getText()));
                        this.camera.openWindow();
                        this.showCamera_btn.setText("Hide Camera");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        // showCam button. Alternate method of opening camStage.
        this.showCamera_btn = new Button("Show Camera"); // Changes text to "Hide Camera" when camStage is open
        this.showCamera_btn.setId("button");
        this.showCamera_btn.setTranslateX(10);
        this.showCamera_btn.setOnAction((event) -> {
            //Camera
            if(!cameraOpen) {
                try {
                    this.takeScreenshot_btn.setTextFill(Color.web("#383C95",1.0));
                    this.camera.setDim(Integer.parseInt(this.dim_txtfield.getText()));
                    this.camera.openWindow();
                    this.showCamera_btn.setText("Hide Camera");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                try {
                    this.takeScreenshot_btn.setTextFill(Color.LIGHTBLUE);
                    this.camera.hideWindow();
                    this.showCamera_btn.setText("Show Camera");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // Label "Camera width:"
        Label lbl = new Label("Camera width:");

        // lbl and text field arrangement
        VBox input_pn = new VBox(lbl, this.dim_txtfield);
        input_pn.setId("outer_pn");

        // Label "Screenshot Taken!"
        Label lbl2 = new Label(" ");

        // lbl2 and button arrangement
        VBox btn_pn = new VBox(lbl2, this.showCamera_btn);
        btn_pn.setId("outer_pn");

        // page layout
        HBox openCam_pn = new HBox(input_pn, btn_pn);

        // Take screenshot button. Captures screenshot and runs it through OCR and GoogleSearch.
        //   - Produces imgView of screenshot and question answer
        this.takeScreenshot_btn = new Button("Take Screenshot");
        this.takeScreenshot_btn.setId("buttonOFF");
        this.takeScreenshot_btn.setTextFill(Color.LIGHTBLUE);
        this.takeScreenshot_btn.setTranslateX(7);
        this.takeScreenshot_btn.setOnAction((event) -> {
            //Camera
            if(cameraOpen) {
                this.screenshotFeedback_lbl.setText("Screenshot Taken!");
                if(this.isImage)
                    this.answer_pn.getChildren().removeAll(this.imgView, this.answer_lbl);
                try {
                    this.screenshot = Screenshot.captureScreenshot((int)this.camera.camera_stg.getX() /*+ 226*/, (int)this.camera.camera_stg.getY()+44/*+127*/, this.camera.cam_w, this.camera.cam_h/*-44*/);
                    this.imgView = new ImageView(SwingFXUtils.toFXImage(this.screenshot, null));
                } catch (AWTException | IOException e) {
                    e.printStackTrace();
                }
                this.imgView.setFitWidth(100);
                this.imgView.setFitHeight(100*16/9);
                this.imgView.setTranslateX(-100);


                try {
                    answer_lbl = new Label(Game.handleScreenshot(this.screenshot));
                    answer_lbl.setTranslateX(50);
                    this.answer_pn.getChildren().add(answer_lbl);
                } catch (IOException | TesseractException e) {
                    e.printStackTrace();
                }

                this.answer_pn.getChildren().add(this.imgView);
                this.isImage = true;
            }
        });

        this.screenshotFeedback_lbl = new Label(" ");
        this.screenshotFeedback_lbl.setTranslateX(20);
        this.screenshotFeedback_lbl.setTranslateY(25);

        // button and feedback label layout
        HBox takeScreenshot_pn = new HBox(this.takeScreenshot_btn, this.screenshotFeedback_lbl);

        // Answer stuff
        Rectangle border = new Rectangle(325, 235, Color.TRANSPARENT);
        border.setId("border");
        Label answerTitle_lbl = new Label("Answer:");
        answerTitle_lbl.setStyle("-fx-underline: true;");
        answerTitle_lbl.setTranslateX(-130);
        answerTitle_lbl.setTranslateY(-105);
        Label answer_lbl = new Label(" ");

        this.answer_pn = new StackPane(border, answerTitle_lbl, answer_lbl);
        this.answer_pn.setTranslateX(10);

        // page layout
        VBox layout_pn = new VBox(openCam_pn, takeScreenshot_pn, this.answer_pn);
        layout_pn.setSpacing(18);
        this.out.getChildren().addAll(layout_pn);

    }

    /**
     * method that sets the .css stylesheets for CamMenuPage
     */
    void setStyles(){
        this.out.getStylesheets().addAll("main/uiElements/page/page.css", "main/uiElements/ui/ui.css");
    }

}
