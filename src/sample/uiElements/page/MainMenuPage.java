package sample.uiElements.page;

import javafx.scene.control.Button;

public class MainMenuPage extends PageElement {

    private Button showCamera_btn;

    public MainMenuPage() {
        super();

        showCamera_btn = new Button("Register");
        showCamera_btn.setTranslateX(30);
        showCamera_btn.setOnAction((event) -> {
            //
        });

        setStyles();
    }

    void setStyles(){

    }

}
