package controller;


import model.User;
import view.ClientConnection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class UserController {
    private HashMap<String, User> users; // <username, user>
    private HashMap<User, ConnectionController> usersInformation;

    public UserController() {
        users = new HashMap<>();
        usersInformation = new HashMap<>();
    }

    public User createNewUser(String userName, String passWord) {
        if (users.containsKey(userName)) {
            return null;
        }
        User newUser = new User(userName, passWord);
        users.put(userName, newUser);
        return newUser;
    }

 
  
    public void removeUser(String userName) {
        User user = users.get(userName);
        if (user != null) {
            users.remove(userName);
            usersInformation.remove(user);
        }
    }
    public User userLoggedIn(String userName, String passWord) {
        User user = users.get(userName);
        if (user != null && user.getPassWord().equals(passWord)) {
            return user;
        }
        return null;
    }
    public void addUserConnection(User user, ConnectionController connectionController) {
        usersInformation.put(user, connectionController);
    }
    public void removeUserConnection(User user) {
        usersInformation.remove(user);
    }


    public User getUserFromObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        return (User) ois.readObject();
    }
    public HashMap<String, User> getUsers() {
        return new HashMap<>(users);
    }

    public HashMap<User, ConnectionController> getNewUserInfo() {
        // Return a copy of the userConnections map to avoid outside modification
        return new HashMap<>(usersInformation);
    }

    public void handleTheUsers(User userupdate) {
        if (userupdate == null || !users.containsKey(userupdate.getUserName())) {
            System.out.println("Can´t find the user");
            return;
        }
        User currentUser = users.get(userupdate.getUserName());
        currentUser.setPassWord(userupdate.getPassWord());
        currentUser.setProfilePicture(userupdate.getProfilePicture());
        System.out.println("Check to see if it works: " + currentUser.getProfilePicture());
    }
}
