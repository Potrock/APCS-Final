package sample.utility;

import java.util.ArrayList;

public class User {
    public static ArrayList<User> users = new ArrayList<>();

    public String username;
    public String password;

    public User(String u, String p){
        this.username = u;
        this.password = p;
        users.add(this);
    }



}