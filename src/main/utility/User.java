package main.utility;

import java.util.ArrayList;

/**
 * Class used by JSONUtility to store user data before writing
 *
 * @author sawyertang
 */
public class User {
    public static ArrayList<User> users = new ArrayList<>();

    public String username;
    public String password;

    /**
     * Person Construstor: inits username and password for the User and adds itsself to the list of Users
     *
     * @param u The username
     * @param p The password
     */
    public User(String u, String p) {
        this.username = u;
        this.password = p;
        users.add(this);
    }


}
