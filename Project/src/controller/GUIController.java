package controller;


import model.User;
import view.NorthPanel;
import view.SouthPanel;

public class GUIController {

    private ClientController clientController;
    private RecipeController recipeController;
    private SouthPanel southPanel;
    private ConnectionController connectionController;

    public GUIController(ConnectionController connectionController) {
        southPanel = new SouthPanel(this);


        new NorthPanel();
    }

    public User getUserName() {
        return southPanel.getUserName();
    }
}