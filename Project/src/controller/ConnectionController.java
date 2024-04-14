package controller;

import view.ClientConnection;

public class ConnectionController {
    private ClientConnection clientConnection;

    public ConnectionController(){
        this.clientConnection = new ClientConnection();

        //clientConnection.establishConnection();
    }
    public void sendObject(Object object){
        clientConnection.sendObject(object);
    }
}
