package sample.uiElements.page;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class LoginPage extends PageElement{

    private Label username_lbl;
    private TextField username_txtfield;
    private Label username_feedback;
    private Label password_lbl;
    private TextField password_txtfield;
    private Label password_feedback;
    private Button login_btn;

    public LoginPage() {
        super();

        username_lbl = new Label("Username: ");
        username_txtfield = new TextField();
        username_feedback = new Label("");
        username_feedback.setId("feedBack");
        VBox username_pn = new VBox(username_lbl, username_txtfield, username_feedback);

        password_lbl = new Label("Password: ");
        password_txtfield = new PasswordField();
        password_feedback = new Label("");
        password_feedback.setId("feedBack");
        VBox password_pn = new VBox(password_lbl, password_txtfield, password_feedback);

        login_btn = new Button("Login");
        login_btn.setOnAction((event) -> {
            //if: username exists
            //  if: password correct
            //      --> Menu
            //  else:
            //      --> "incorrect password"
            //else:
            //  --> "incorrect username" and "incorrect password"

            username_feedback.setText("incorrect username");
            password_feedback.setText("incorrect password");
        });

        VBox layout_pn = new VBox(username_pn, password_pn, login_btn);
        layout_pn.setId("login_pn");

        out.getChildren().add(layout_pn);

        setStyles();
    }

    void setStyles(){
        out.getStylesheets().addAll("sample/uiElements/page/loginPage.css", "sample/uiElements/ui/ui.css");
    }

}
