package main.utility;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import main.uiElements.page.LoginPage;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JSONUtility {

    private static JSONArray jsonArray = new JSONArray();
    private static ObservableList<User> data = FXCollections.observableArrayList();

    public static void saveUsers(){
        try{
            getUsers();
            FileWriter writer = new FileWriter("users.json");
            JSONObject newScore = new JSONObject();
            newScore.put("username", LoginPage.username_txtfield.getText());
            newScore.put("password", LoginPage.password_txtfield.getText());
            jsonArray.add(newScore);
            writer.write(jsonArray.toJSONString());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void getUsers() {
        try {
            FileReader reader = new FileReader("users.json");
            JSONParser parser = new JSONParser();
            jsonArray = (JSONArray) parser.parse(reader);
            reader.close();
            data.clear();
            for (Object i : jsonArray) {
                JSONObject person = (JSONObject) i;

                String username = (String) person.get("username");
                System.out.print(username + ", ");

                String password = (String) person.get("password");
                System.out.println(password);

                data.add(new User(username, password));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
