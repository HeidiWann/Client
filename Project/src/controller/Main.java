package controller;


import javafx.application.Application;
import controller.ClientController;
import view.mainStage.MainStage;

import java.io.IOException;

public class Main {
    private ConnectionController connectionController;
    public static void main(String[] args) throws IOException {
        Application.launch(MainStage.class, args);
       new ClientController();

    }
}