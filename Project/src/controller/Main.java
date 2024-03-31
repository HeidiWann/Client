package controller;

import view.MainPanel;

public class Main {
    public static void main(String[] args) {
        GUIController guiController = new GUIController();
        MainPanel mainPanel = guiController.getMainPanel();
    }
}