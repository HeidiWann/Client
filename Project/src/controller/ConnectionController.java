package controller;

import model.Recipe;
import model.User;
import view.ClientConnection;

import java.io.IOException;
import java.util.ArrayList;

public class ConnectionController {
    private GUIController guiController;
    private ClientController clientController;
    private UserController userController;
    private ClientConnection clientConnection;
    private RecipeController recipeController;

    public ConnectionController(ClientController clientController) {
        this.clientController = clientController;
        userController = new UserController();
    }
    public void userDisconnected(Object object) {
        User user = (User) object;

        clientConnection.closeSocket();
    }
    public void register(String userName, String password) {
        User newUser = userController.createNewUser(userName, password);

        try {

            newUser = guiController.getUserName();


        }
        catch (Exception e) {
            System.out.println("User not logged in");
        }

        userController.createNewUser(newUser.getUserName(), newUser.getPassWord());
    }

    public User login(String userLogin, String userPassword) {
        User userThatAreLoggedIn = userController.userLoggedIn(userLogin, userPassword);
        try {
           userLogin = guiController.getUserName().getPassword();
        }
        catch (Exception e) {
            System.out.println("User not logged in");
        }
        userController.userLoggedIn(userLogin, userPassword);
        return userThatAreLoggedIn;
    }
    public void revealServerIntention(int intention) throws IOException {
        try {
            switch (intention) {
                case 1:
                    clientConnection.sendObject(guiController.getUserName());
                    break;
                case 2:
                    userController.createNewUser(guiController.getUserName().getUserName(), guiController.getUserName().getPassWord());
                    break;
                case 3:
                    userController.userLoggedIn(guiController.getUserName().getUserName(), guiController.getUserName().getPassWord());
                    break;
                case 4:
                    userController.removeUser(guiController.getUserName().getUserName());

                    break;
                default:
                    break;
            }
        }catch (Exception e) {
            System.out.println("User not logged in");
        }
    }
    public void handleInput(Object object) {
        if (object instanceof Recipe) {
            Recipe recipe = (Recipe) object;
            recipeController.createRecipe(recipe);
        }
        else if (object instanceof ArrayList<?>) {
            ArrayList<Recipe> recipes = (ArrayList<Recipe>) object;
            for (Recipe recipe : recipes) {
                recipeController.createRecipe(recipe);
            }

        }
        else {
            System.err.println("No recipe found");
        }
    }
}
