package controller;

import model.GetGUIController;
import model.Recipe;
import model.User;
import view.ClientConnection;

import java.io.IOException;
import java.util.ArrayList;

import static controller.Constants.*;

public class ConnectionController {
    private final MainGUIController mainGUIController;
    private ClientConnection clientConnection;
    private final UserController userController;
    private final UserGUIController userGUIController;

    public ConnectionController(UserController userController) throws IOException {
        this.mainGUIController = GetGUIController.getGuiController();
        this.userGUIController = GetGUIController.getUserGUIController();
        this.userController = userController;
    }

    public void connectToServer() throws IOException {
        if (clientConnection != null) {
            if (clientConnection.isAlive()) {
                System.out.println("Already connected");
                return;
            } else {
                System.out.println("Closing the connection");
                serverDisconnected();
            }
        }

        clientConnection = new ClientConnection("127.0.0.1", 2343, this);
        clientConnection.start();
    }

    /**
     * This method disconnects the {@link ClientConnection} from the server. It first makes it so the Client can't
     * accept more intentions or Objects. It then sends an intention to the server that it wants to disconnect. Lastly,
     * a method is called to close the connection.
     * @author Anton Persson
     * @author Heidi Wänmann
     */
    public void serverDisconnected() {
        clientConnection.setListenForIntention(false);
        clientConnection.setListenForObject(false);
        clientConnection.sendIntention(C_WANTS_TO_DISCONNECT);
        clientConnection.closeResources();
    }

    /**
     * This method packs up an {@link Object} from the server. Based on the intention in the server the Object is
     * unpacked differently.
     * @param object The {@link Object} that is to be unpacked
     * @author Anton Persson
     * @author Heidi Wänmann
     */
    public void packUpObject(Object object, int intention) {
        switch (intention) {
            case S_SEND_ALL_RECIPES:
                ArrayList<Recipe> recipes = (ArrayList<Recipe>) object;
                mainGUIController.setRecipes(recipes.toArray(new Recipe[0]));
                clientConnection.setListenForIntention(true);
                clientConnection.setListenForObject(false);
                break;
            case S_SEND_ALL_USERS:
                ArrayList<User> users = (ArrayList<User>) object;
                userController.setUsers(users);

                clientConnection.setListenForIntention(true);
                clientConnection.setListenForObject(false);
                break;
            case S_UPDATE_C_LIST_OF_USERS:
                ArrayList<User> newUsers = (ArrayList<User>) object;
                userController.updateListOfUsers(newUsers);

                clientConnection.setListenForIntention(true);
                clientConnection.setListenForObject(false);
        }
    }

    /**
     * This method reveals the intention of a client and then does something based on the intention.
     * @param intention An int  that decides what happens
     */
    public void revealClientIntention(int intention)  {
        switch (intention) {
            default:
                clientConnection.setListenForIntention(false);
                clientConnection.setListenForObject(true);
                break;
        }
    }

    /**
     * This method calls methods in {@link ClientConnection} to send a {@link User}
     * @param user The User to send
     * @author Anton Persson
     */
    public void registerNewUser(User user) {
        clientConnection.sendIntention(C_WANT_TO_REGISTER);
        clientConnection.sendObject(user);
    }
}