package model;

import controller.GUIController;

public class GetGUIController {
    private static GUIController guiController;

    public static GUIController getGuiController() {
        if (guiController == null) {
            guiController = new GUIController();
        }
        return guiController;
    }
}