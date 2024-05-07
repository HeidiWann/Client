package controller;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import view.userStage.*;

import javax.swing.*;

public class UserGUIController {
    private UserEastPanel userEastPanel;
    private UserWestPanel userWestPanel;
    private UserSouthPanel userSouthPanel;
    private UserMainScene userMainScene;
    private RegisterNorthPanel registerNorthPanel;
    private RegisterWestPanel registerWestPanel;
    private RegisterSouthPanel registerSouthPanel;
    private RegisterMainScene registerMainScene;

    public UserGUIController() {
        userMainScene = new UserMainScene();
    }

    public void createUserWindow() {
        userMainScene.createUserWindow();
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

    public void loginFailed() {
        JOptionPane.showMessageDialog(null, "The entered values did not match a login");
    }
}
