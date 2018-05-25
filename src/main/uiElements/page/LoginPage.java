package main.uiElements.page;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.Main;
import main.utility.JSONUtility;
import main.utility.User;

/**
 * Inits all UI elements for LoginPage
 *
 * @author sawyertang
 */
public class LoginPage extends PageElement{

    public static TextField username_txtfield;
    public static TextField password_txtfield;


    private Label username_feedback;
    private Label password_feedback;

    /**
     * Constructor that inits all UI elements for LoginPage and adds them to a JavaFX Group
     */
    public LoginPage() {
        super();
        setStyles();

        Label registered_lbl = new Label(" ");

        //username
        Label username_lbl = new Label("Username: ");
        username_txtfield = new TextField("guest");
        this.username_feedback = new Label("");
        this.username_feedback.setId("feedBack");
        VBox username_pn = new VBox(username_lbl, username_txtfield, this.username_feedback);

        //password
        Label password_lbl = new Label("Password: ");
        password_txtfield = new PasswordField();
        this.password_feedback = new Label("");
        this.password_feedback.setId("feedBack");
        VBox password_pn = new VBox(password_lbl, password_txtfield, this.password_feedback);

        //btns

        Button login_btn = new Button("Login");
        login_btn.setId("button");
        login_btn.setOnAction((event) -> {
            JSONUtility.getUsers();
            boolean uExists = false;
            boolean pExists = false;
            System.out.println("==: " + username_txtfield.getText() + ", " + password_txtfield.getText());
            this.username_feedback.setText(" ");
            this.password_feedback.setText(" ");
            for(User n : User.users){
                if(n.username.equals(username_txtfield.getText()) || "guest".equals(username_txtfield.getText())){
                    uExists = true;
                    if(n.password.equals(password_txtfield.getText()) || "1234".equals(password_txtfield.getText())){
                        pExists = true;
                    }
                }
            }
            if(uExists && pExists){
                Main.logged_in = true;
                Main.loginPrompt_lbl.setText("");
                Main.goCamMenu();
            }else if(uExists){
                this.password_feedback.setText("incorrect password");
            }else{
                this.username_feedback.setText("incorrect username");
                this.password_feedback.setText("incorrect password");
            }

        });

        Button register_btn = new Button("Register");
        register_btn.setId("button");
        register_btn.setTranslateX(30);
        register_btn.setOnAction((event) -> {
            User.users.add(new User(this.username_feedback.getText(), this.password_feedback.getText()));
            JSONUtility.saveUsers();
            registered_lbl.setText("Registered!");
        });

        HBox btns = new HBox(login_btn, register_btn);

        //message
        Label message = new Label("guest login info:\nu: guest\np: 1234");

        //layout
        VBox layout_pn = new VBox(username_pn, password_pn, btns, registered_lbl, message);
        layout_pn.setId("outer_pn");

        this.out.getChildren().add(layout_pn);

    }

    /**
     * method that sets the .css stylesheets for LoginPage
     */
    void setStyles(){
        out.getStylesheets().addAll("main/uiElements/page/page.css", "main/uiElements/ui/ui.css");
    }

}
