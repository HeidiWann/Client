package model;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.swing.*;

public class User {
    private String userName;
    private String passWord;
    private ImageView profilePicture;
    public User(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }
    public String getUserName(){
        return userName;
    }
    public String getPassWord(){
        return passWord;
    }
    public void setPassWord(String passWord){
        this.passWord = passWord;

    }
    public void setProfilePicture(ImageView profilePicture){
        this.profilePicture = profilePicture;
    }

    public String getPassword() {
        return passWord;
    }
}
