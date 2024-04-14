package view;



import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;


public class ClientConnection {
    private Connection connection;
    private String ip = "127.0.0.1";
    private int port = 2343 ; // for windows
    private int port2 = 780; // for Mac

    private Socket socket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    public ClientConnection() {

    }
    public Connection getDatabaseConnection(){
        return connection;
    }
    public void closeConnection(){
        try {
            if(!connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    public void establishConnection(){
        System.out.println("Attempting to connect to server at IP: " + ip + " on port: " + port);
        try{

                socket = new Socket(ip, port);
                oos = new ObjectOutputStream(socket.getOutputStream());
                ois = new ObjectInputStream((socket.getInputStream()));


        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    public void sendObject(Object object){
        try {
            oos.writeObject(object);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);

        }
    }
}
