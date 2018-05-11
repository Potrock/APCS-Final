package sample.uiElements.page;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import sample.Main;
import sample.utility.JSONUtility;
import sample.utility.User;

public class LoginPage extends PageElement{

    public static TextField username_txtfield;
    public static TextField password_txtfield;


    private Label username_feedback;
    private Label password_feedback;

    public LoginPage() {
        super();
        setStyles();

        Label registered_lbl = new Label(" ");

        //username
        Label username_lbl = new Label("Username: ");
        username_txtfield = new TextField("Not yet Functional");
        username_feedback = new Label("");
        username_feedback.setId("feedBack");
        VBox username_pn = new VBox(username_lbl, username_txtfield, username_feedback);

        //password
        Label password_lbl = new Label("Password: ");
        password_txtfield = new PasswordField();
        password_feedback = new Label("");
        password_feedback.setId("feedBack");
        VBox password_pn = new VBox(password_lbl, password_txtfield, password_feedback);

        //btns

        Button login_btn = new Button("Login");
        login_btn.setId("button");
        login_btn.setOnAction((event) -> {
            JSONUtility.getUsers();
            boolean uExists = false;
            boolean pExists = false;
            System.out.println("==: " + username_txtfield.getText() + ", " + password_txtfield.getText());
            username_feedback.setText(" ");
            password_feedback.setText(" ");
            for(User n : User.users){
                if(n.username.equals(username_txtfield.getText())){
                    uExists = true;
                    if(n.password.equals(password_txtfield.getText())){
                        pExists = true;
                    }
                }
            }
            if(uExists && pExists){
                Main.logged_in = true;
                Main.goCamMenu();
            }else if(uExists){
                password_feedback.setText("incorrect password");
            }else{
                username_feedback.setText("incorrect username");
                password_feedback.setText("incorrect password");
            }

        });

        Button register_btn = new Button("Register");
        register_btn.setId("button");
        register_btn.setTranslateX(30);
        register_btn.setOnAction((event) -> {
            User.users.add(new User(username_feedback.getText(), password_feedback.getText()));
            JSONUtility.saveUsers();
            registered_lbl.setText("Registered!");
        });

        HBox btns = new HBox(login_btn, register_btn);

        //layout
        VBox layout_pn = new VBox(username_pn, password_pn, btns, registered_lbl);
        layout_pn.setId("outer_pn");

        out.getChildren().add(layout_pn);

    }

    void setStyles(){
        out.getStylesheets().addAll("sample/uiElements/page/page.css", "sample/uiElements/ui/ui.css");
    }

}
