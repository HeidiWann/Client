package controller;


import javafx.application.Application;
import controller.ClientController;
import view.mainStage.MainStage;

import java.io.IOException;

public class Main {
<<<<<<< HEAD
    private ConnectionController connectionController;
    public static void main(String[] args) throws IOException {
        Application.launch(MainStage.class, args);
       new ClientController();

=======
    /**
     * This method starts that program. It launches the MainStage class which leads to the launch of JavaFX.
     * @param args
     * @author Anton Persson
     */
    public static void main(String[] args) {
        Application.launch(MainStage.class, args);
        //new ClientController();
>>>>>>> b0e88959740b428bedcbd838a73541ef22331601
    }
}