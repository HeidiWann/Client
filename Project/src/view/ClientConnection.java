package view;

import controller.ConnectionController;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import static controller.Constants.OK;


public class ClientConnection extends Thread {
    private String host;
    private int port; // for windows 2343
    //private int portMac; // for Mac 780
    private Socket socket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private ConnectionController connectionController;
    private boolean listenForIntention;
    private boolean listenForObject;


    /**
     * Constructor that initializes some of the instance variables.
     * @param host
     * @param port
     * @param connectionController
     * @author Heidi Wännman
     */
    public ClientConnection(String host, int port, ConnectionController connectionController) {
        this.host = host;
        this.port = port;
        this.connectionController = connectionController;
    }

    /**
     * Closes the connection
     * @author Heidi Wänmann
     */
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

    /**
     * This method sends an Object to the server by using {@link ObjectOutputStream}
     * @param object The {@link Object} to send
     * @author Anton Persson
     */
    public void sendObject(Object object) {
        try {
            oos.writeObject(object);
            oos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method sends an intention to the server by using {@link ObjectOutputStream}
     * @param intention An int that tells the server what to do
     * @author Anton Persson
     */
    public void sendIntention(int intention) {
        try {
            oos.writeInt(intention);
            oos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method first calls a method that establishes a connection to the server. After that, while the thread isn't
     * interrupted, the method checks if the {@link ObjectInputStream} is available. If it is, the method proceeds to
     * either listen for an intention or an object and send them to the {@link ConnectionController} for processing.
     * If the inputs stream isn't available, the thread sleeps for 100 milliseconds. Finally, the method closes the
     * connection
     * @author Anton Persson
     * @author Salma Omar
     * @author Heidi Wänmann
     */
    @Override
    public void run() {
        try {
            establishConnection();
            while (!Thread.interrupted()) {
                if (ois.available() > OK) {
                    if (listenForIntention) {
                        int intention = ois.readInt();
                        connectionController.revealClientIntention(intention);
                    }
                    if (listenForObject){
                        Object objectFromServer = ois.readObject();
                        connectionController.packUpObject(objectFromServer);
                    }
                }
                else {
                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {
                        System.out.println("Blev fel på grund av hur sleep fungerar");
                    }
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    /**
     * This method gives values to some instance variables and by doing this, it also creates a connection to the server
     * @author Heidi Wänmann
     * @author Anton Persson
     * @throws IOException
     */
    private void establishConnection() throws IOException{
        socket = new Socket(host, port);
        oos = new ObjectOutputStream(socket.getOutputStream());
        ois = new ObjectInputStream(socket.getInputStream());

        setListenForIntention(true);
        setListenForObject(false);

        System.out.println("Connected to server at " + host + ":" + port);
    }

    /**
     * This method sets the value of a boolean
     * @param intention The value to give the boolean
     * @author Anton Persson
     */
    public void setListenForIntention(boolean intention) {
        listenForIntention = intention;
    }

    /**
     * This method sets the value of a boolean
     * @param listenForObject The value to give the boolean
     * @author Anton Persson
     */
    public void setListenForObject(boolean listenForObject) {
        this.listenForObject = listenForObject;
    }
}