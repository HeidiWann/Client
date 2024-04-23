package view;

import controller.ConnectionController;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import java.sql.SQLException;

import static controller.Constans.OK;


public class ClientConnection extends Thread {
    private String host;
    private int port; // for windows 2343
    //private int portMac; // for Mac 780
    private Socket socket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private ConnectionController connectionController;




    public ClientConnection(String host, int port, ConnectionController connectionController) {
        this.host = host;
        this.port = port;
        this.connectionController = connectionController;

    }


    @Override
    public void run() {
        try {
            socket = new Socket(host, port);
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
            System.out.println("Connected to server at " + host + ":" + port);

            while (!Thread.interrupted()) {
                try {
                    if (ois.available() > OK) {
                        int intention = ois.readInt();
                        System.out.println("Received intention: " + intention);
                        connectionController.revealClientIntention(intention, this);
                    }
                } catch (IOException | ClassNotFoundException | SQLException e) {
                    System.out.println("Could not talk to server: " + e.getMessage());
                    break; // Exit the loop on exception
                }
            }
        } catch (IOException e) {
            System.out.println("Could not connect: " + e.getMessage());
        } finally {
            closeResources();
        }
    }

    public void closeResources() {
        try {
            if (ois != null) {
                ois.close();
            }
            if (oos != null) {
                oos.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            System.out.println("Could not close resources " + e.getMessage());
        }
    }


    public ObjectOutputStream getObjectOutputStream() {
        return oos;
    }

    public ObjectInputStream getObjectInputStream() {
        return ois;
    }

    public void sendObject(Object object) throws IOException {
        oos.writeObject(object);
        oos.flush();
    }

    public void sendIntention(int intention) throws IOException {
        oos.writeInt(intention);
        oos.flush();
    }

}


