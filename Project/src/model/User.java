package model;


import javafx.scene.image.Image;

public class User {
    private String userName;
    private String passWord;
    private Image profilePicture;
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
    public void setProfilePicture(Image profilePicture){
        this.profilePicture = profilePicture;
    }

}
