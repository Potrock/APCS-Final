package sample.utility;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JSONUtility {

    private static JSONArray jsonArray = new JSONArray();
    private static ObservableList<User> data = FXCollections.observableArrayList();

    public static void saveUser(User usr){
        try{
            getUsers();
            FileWriter writer = new FileWriter("src/sample/utility/users.json");
            JSONObject newScore = new JSONObject();
            newScore.put("username", usr.username);
            newScore.put("password", usr.password);
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
            FileReader reader = new FileReader("src/sample/utility/users.json");
            JSONParser parser = new JSONParser();
            jsonArray = (JSONArray) parser.parse(reader);
            reader.close();
            data.clear();
            for (Object i : jsonArray) {
                JSONObject person = (JSONObject) i;

                String username = (String) person.get("username");
                System.out.println(username);

                String password = (String) person.get("password");
                System.out.println(password);

                data.add(new User(username, password));
                System.out.println(username + ", " + password);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
