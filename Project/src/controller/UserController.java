package controller;


import model.User;

import java.util.ArrayList;

public class UserController {
    private ArrayList<User> users;
    public UserController() {
        users = new ArrayList<>();
    }

    public User createNewUser(String userName, String passWord) {
        User user = new User(userName, passWord);
        users.add(user);
        return user;
    }
    public void removeUser(String userName) {
        for(User user : users){
            if(user.getUserName().equals(userName)){
                users.remove(user);
            }
        }
    }

    public User userLoggedIn(String userName, String passWord) {
        for(User user : users){
            if(user.getUserName().equals(userName) && user.getPassWord().equals(passWord)){
                return user;
            }
        }
        return null;
    }
}
