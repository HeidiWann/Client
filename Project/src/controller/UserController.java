package controller;


import model.GetGUIController;
import model.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class UserController {
    private HashMap<String, User> users; // <username, user>
    private User loggedInUser;
    private ConnectionController connectionController;

    public UserController() {
        users = new HashMap<>();
        createTestData();
        GetGUIController.getUserGUIController().setUserController(this);
        GetGUIController.getGuiController().setUserController(this);
    }

    public void createNewUser(String userName, String passWord) {
        User newUser = new User(userName, passWord);
        loggedInUser = newUser;
        users.put(userName, newUser);
        connectionController.registerNewUser(newUser);
    }

    public boolean checkIfUserExists(String userName) {
        if (users.containsKey(userName)) {
            return true;
        }
        return false;
    }

    public boolean tryToLogIn(String userName, String enteredPassword) {
        String usersPassword = users.get(userName).getPassword();
        System.out.println("Det angivna lösenordet: " + enteredPassword);
        System.out.println("Det faktiska lösenordet: " + usersPassword);
        if (usersPassword.equals(enteredPassword)) {
            loggedInUser = users.get(userName);
            return true;
        } else {
            return false;
        }
    }

    /**
     * @author Anton Persson
     * @param listOfUsers
     */
    public void setUsers(ArrayList<User> listOfUsers) {
        for (User user : listOfUsers) {
            String userName = user.getUserName();
            users.put(userName, user);
        }
    }

    public void updateListOfUsers(ArrayList<User> listOfUsers) {
        users.clear();
        for (User user : listOfUsers) {
            String userName = user.getUserName();
            users.put(userName,user);
        }
    }

    public void createTestData() {
        users.put("Anton", new User("Anton", "Persson"));
        users.put("Heidi", new User("Heidi", "Wännman"));
    }

    public void logOut() {
        loggedInUser = null;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setConnectionController(ConnectionController connectionController) {
        this.connectionController = connectionController;
    }
}
