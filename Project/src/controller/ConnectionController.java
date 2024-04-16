package controller;

import model.Recipe;
import model.User;
import view.ClientConnection;

import java.io.IOException;
import java.util.ArrayList;

public class ConnectionController {
    private MainGUIController mainGuiController;
    private ClientController clientController;
    private UserController userController;
    private ClientConnection clientConnection;
    private RecipeController recipeController;

    public ConnectionController(ClientController clientController) throws IOException {
        this.clientController = clientController;
        userController = new UserController();
        this.clientConnection = new ClientConnection();
        clientConnection.socketConnect();
    }
    public void userDisconnected(Object object) {
        User user = (User) object;

        clientConnection.closeSocket();
    }
    public void register(String userName, String password) {
        User newUser = userController.createNewUser(userName, password);

        try {

            //newUser = guiController.getUserName();


        }
        catch (Exception e) {
            System.out.println("User not logged in");
        }

        userController.createNewUser(newUser.getUserName(), newUser.getPassWord());
    }

    public User login(String userLogin, String userPassword) {
        User userThatAreLoggedIn = userController.userLoggedIn(userLogin, userPassword);
        try {
           //userLogin = guiController.getUserName().getPassword();
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
                    //clientConnection.sendObject();
                    break;
                case 2:
                    //userController.createNewUser();
                    break;
                case 3:
                    //userController.userLoggedIn();
                    break;
                case 4:
                    //userController.removeUser();

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
            //recipeController.createRecipe(recipe);
        }
        else if (object instanceof ArrayList<?>) {
            ArrayList<Recipe> recipes = (ArrayList<Recipe>) object;
            for (Recipe recipe : recipes) {
                //recipeController.createRecipe(recipe);
            }

        }
        else {
            System.err.println("No recipe found");
        }
    }
    public void sendObject(Object object) throws IOException {
        clientConnection.sendObject(object);
    }
}
