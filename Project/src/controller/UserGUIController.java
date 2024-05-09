package controller;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.GetGUIController;
import view.userStage.*;

import javax.swing.*;

public class UserGUIController {
    private UserWestPanel userWestPanel;
    private UserMainScene userMainScene;
    private RegisterWestPanel registerWestPanel;
    private RegisterMainScene registerMainScene;
    private MainGUIController mainGUIController;
    private UserController userController;

    public UserGUIController() {
        userMainScene = new UserMainScene();
        mainGUIController = GetGUIController.getGuiController();
    }


    public void createRegisterWindow() {
        registerMainScene = new RegisterMainScene(this);
        registerMainScene.createRegisterWindow();
    }

    public TextField getLoginUserNameTextField() {
        if (userWestPanel.getUserNameTextField() != null) {
            TextField userName = userWestPanel.getUserNameTextField();
            String stringUserName = userName.getText().trim();
            if (!stringUserName.isEmpty()) {
                return userName;
            }
        }
        return null;
    }

    public TextField getTextLogInUserNameTextField() {
        return userWestPanel.getUserNameTextField();
    }

    public TextField getPasswordField() {
        return userWestPanel.getPasswordTextField();
    }

    public UserWestPanel getWestPanel() {
        return userWestPanel;
    }

    public RegisterWestPanel getRegisterWestPanel() {
        return registerWestPanel;
    }

    public PasswordField getLoginPasswordField() {
        if (userWestPanel.getPasswordTextField() != null) {
            PasswordField password = userWestPanel.getPasswordTextField();
            String passwordField = password.getText().trim();
            if (!passwordField.isEmpty()) {
                return password;
            }
        }
        return null;
    }

    public TextField getRegisterUsername() {
        if (registerWestPanel.getRegisterUserName() != null) {
            TextField userName = registerWestPanel.getRegisterUserName();
            String stringUserName = userName.getText().trim();
            if (!stringUserName.isEmpty()) {
                return userName;
            }
        }
        return null;
    }

    public PasswordField getRegisterFirstPassword() {
        if (registerWestPanel.getRegisterFirstPassword() != null) {
            PasswordField password = registerWestPanel.getRegisterFirstPassword();
            String passwordField = password.getText().trim();
            if (!passwordField.isEmpty()) {
                return password;
            }
        }
        return null;
    }

    public TextField getRegisterSecondPassword() {
        if (registerWestPanel.getRegisterSecondPassword() != null) {
            PasswordField password = registerWestPanel.getRegisterSecondPassword();
            String passwordField = password.getText().trim();
            if (!passwordField.isEmpty()) {
                return password;
            }
        }
        return null;
    }

    public void setUserWestPanel(UserWestPanel userWestPanel) {
        this.userWestPanel = userWestPanel;
    }

    public void setRegisterWestPanel(RegisterWestPanel registerWestPanel) {
        this.registerWestPanel = registerWestPanel;
    }

    public void setLoggedInStatus(boolean loggedIn) {
        mainGUIController.setLoginStatus(loggedIn);
    }

    public boolean tryToLogIn(String userName, String password) {
        boolean userExists = userController.checkIfUserExists(userName);
        System.out.println("Användaren fans: " + userExists);
        if (userExists) {
            boolean successfulLogIn = userController.tryToLogIn(userName, password);
            if (successfulLogIn) {
                setLoggedInStatus(true);
                return true;
            }
        }
        return false;
    }

    public boolean tryToRegister(String userName, String password) {
        boolean userAlreadyExists = userController.checkIfUserExists(userName);
        if (userAlreadyExists) {
            return false;
        }
        userController.createNewUser(userName, password);
        return true;
    }

    public void setUserController(UserController userController) {
        this.userController = userController;
    }
}