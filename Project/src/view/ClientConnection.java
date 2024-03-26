package view;

import controller.RecipeController;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class ClientConnection {

    private RecipeController recipeController;
    private Socket socket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    public ClientConnection() {

    }
}
