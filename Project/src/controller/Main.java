package controller;


import javafx.application.Application;
import view.MainStage;

public class Main {
    public static void main(String[] args) {
        Application.launch(MainStage.class, args);
        new ClientController();
    }
}