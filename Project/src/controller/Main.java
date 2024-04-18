package controller;


import javafx.application.Application;
import view.mainStage.MainStage;

public class Main {
    /**
     * This method starts that program. It launches the MainStage class which leads to the launch of JavaFX.
     * @param args
     * @author Anton Persson
     */
    public static void main(String[] args) {
        Application.launch(MainStage.class, args);
        //new ClientController();
    }
}