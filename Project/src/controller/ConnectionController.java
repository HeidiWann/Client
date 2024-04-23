package controller;

import model.Recipe;
import model.User;
import view.ClientConnection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Callable;

import static controller.Constans.*;

public class ConnectionController {
    private UserController userController;
    private RecipeController recipeController;
    private ClientConnection clientConnection;

 public ConnectionController(UserController userController, RecipeController recipeController) throws IOException {
     this.userController = userController;
     this.recipeController = recipeController;


 }
 public void connectToServer() throws IOException {
     if (clientConnection != null) {
         if (clientConnection.isAlive()) {
             System.out.println("Already connected");
             return;
         }
         else {
             System.out.println("Closing the connection");
             serverDisconnected();
         }

         }
     clientConnection = new ClientConnection("127.0.0.1", 2343, this);
     clientConnection.start();
     System.out.println("Trying to connect");

 }
 public void serverDisconnected() {
     System.out.println("Server disconnected");
     closeResources();
 }
 public void closeResources() {
     if (clientConnection != null) {
         clientConnection.interrupt();
         clientConnection = null;
     }
 }
 private void sendUserData(ObjectOutputStream oos) throws IOException {
        HashMap<User, ConnectionController> listOfUsers = userController.getNewUserInfo();
        oos.writeObject(listOfUsers);
    }
    private void sendRecipeData(ObjectOutputStream oos) throws IOException {
        HashMap<Recipe, ConnectionController> listOfRecipes = recipeController.getRecipeInformation();
        oos.writeObject(listOfRecipes);
    }

    public synchronized void revealClientIntention(int intention, ClientConnection clientConnection) throws SQLException, IOException, ClassNotFoundException {
        ObjectOutputStream oos = clientConnection.getObjectOutputStream();
        ObjectInputStream ois = clientConnection.getObjectInputStream();
        switch (intention) {
            case S_WANTUSER:
                userHandling(ois, oos);
                break;
            case S_WANTRECIPE:
                recipeHandling(ois, oos);
                break;
            default:
                System.out.println("Don´t know what intention this: " + intention + " wants to do");
                break;
        }

    }

    private void recipeHandling(ObjectInputStream ois, ObjectOutputStream oos) throws IOException, ClassNotFoundException {
        Recipe recipe = getRecipeFromServer(ois);
        recipeController.handleTheRecipes(recipe);
        sendRecipeData(oos);
        recipeController.getRecipeInformation();
        oos.writeObject(recipe);
    }

    private void userHandling(ObjectInputStream ois, ObjectOutputStream oos) throws IOException, ClassNotFoundException {
        User user = getUserFromServer(ois);
        userController.handleTheUsers(user);
        userController.getNewUserInfo();
        sendUserData(oos);
        oos.writeObject(user);
    }

    private Recipe getRecipeFromServer(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        return (Recipe) ois.readObject();
    }
    private User getUserFromServer(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        return (User) ois.readObject();
    }
    public void sendLoginRequest(String userName, String password) {
    }
}
