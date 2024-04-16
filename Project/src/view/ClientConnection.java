package view;



import controller.ClientController;
import controller.ConnectionController;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;



public class ClientConnection extends Thread {

    private Connection connection;
    private String host = "127.0.0.1";
    private int port = 2343; // for windows
    private int port2 = 780; // for Mac

    private Socket socket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private ConnectionController connectionController;
    private Thread thread;
    private Object userConnected;
    private int intention;
    boolean ackReceived = false;
    private boolean started;
    private boolean ListenForObjects = false;
    public ClientConnection() {

    }


    public ClientConnection(String host, int port, ConnectionController connectionController, Object object) {
        this.host = host;
        this.port = port;
        this.connectionController = connectionController;
        this.userConnected = object;


    }


    public void socketConnect() throws IOException {
        try {

        if (socket == null) {
            socket = new Socket(host, port);
            System.out.println("Connected on " + host + ", " + port);
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
        }

        } catch (IOException e) {
            System.out.println("Could not connect " + e.getMessage());
        }
    }

    public void closeSocket() {
        try {
            if (socket != null) {
                socket.close();
                System.out.println("Socket is now closed");
            }
        } catch (IOException e) {
            System.out.println("Could not close socket " + e.getMessage());
        }
    }

    public void run() {
        if (socket == null) {
            try {
                socketConnect();
                this.started = true;

                while (this.thread.isAlive() && !Thread.interrupted()) {

                    if (ois.available() == 0) {
                        Thread.sleep(100);
                    } else {
                        intention = ois.readInt();
                        if (intention == 0) {
                            ackReceived = true;
                        } else {

                            System.out.println("Server has broadcasted following intention: " + intention);
                            connectionController.revealServerIntention(intention);
                        }
                    }

                    if (ListenForObjects) {
                        System.out.println("listening for object");
                        Object obj = ois.readObject();
                        connectionController.handleInput(obj);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    if (socket != null) {
                        closeSocket();


                        System.out.println("Connection is now closed");
                    }
                    if (oos != null) {
                        oos.close();
                    }
                    if (ois != null) {
                        ois.close();
                    }
                } catch (IOException e) {
                    System.out.println("Could not close socket " + e.getMessage());
                }

            }
        }
    }

    public void sendObject(Object object) throws IOException {
        if (oos == null) {
            oos = new ObjectOutputStream(socket.getOutputStream());
        }
        try {
            oos.writeObject(object);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);

        }
    }

    public void sendIntention(int intention) throws IOException {
        if (oos == null) {
            oos = new ObjectOutputStream(socket.getOutputStream());
        }
        try {
            oos.writeInt(intention);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }


    }
}
