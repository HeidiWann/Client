package model;

import javafx.scene.image.ImageView;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 222333444L;
    private int userID;
    private String userName;
    private String passWord;
    private ImageView profilePicture;
    private ArrayList<Recipe> favoriteRecipes;

    public User(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
        this.favoriteRecipes = new ArrayList<>();
    }

    public String getUserName() {
        return userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public void setProfilePicture(ImageView profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getPassword() {
        return passWord;
    }

    public ImageView getProfilePicture() {
        return profilePicture;
    }
    public ArrayList<Recipe> getFavoriteRecipes() {
        return favoriteRecipes;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}