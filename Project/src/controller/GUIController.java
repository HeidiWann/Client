package controller;

import view.MainFrame;
import view.MainPanel;

public class GUIController {
    private MainFrame mainFrame;
    private ClientController clientController;
    private RecipeController recipeController;

    public GUIController() {
        mainFrame = new MainFrame();
    }

    public MainPanel getMainPanel() {
        return mainFrame.getMainPanel();
    }
}
