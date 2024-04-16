package controller;


import javafx.application.Application;
import view.mainStage.MainStage;

public class Main {
    public static void main(String[] args) {
        Application.launch(MainStage.class, args);
        new ClientController();
    }
}