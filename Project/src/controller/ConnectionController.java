package controller;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.GetGUIController;
import model.Ingredient;
import model.Recipe;
import model.User;
import view.ClientConnection;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static controller.Constants.*;

public class ConnectionController {
    private final MainGUIController mainGUIController;
    private ClientConnection clientConnection;
    private final UserController userController;
    private final UserGUIController userGUIController;
    private RecipeController recipeController;
    private RecipeCreationController recipeCreationController;
    private IngredientController ingredientController;
    private UserRecipeController userRecipeController;

    public ConnectionController(UserController userController, RecipeController recipeController, UserRecipeController userRecipeController) throws IOException {
        this.recipeCreationController = GetGUIController.getRecipeCreationController();
        this.mainGUIController = GetGUIController.getGuiController();
        this.userGUIController = GetGUIController.getUserGUIController();
        this.userController = userController;
        this.recipeController = recipeController;
        this.ingredientController = GetGUIController.getIngredientController();
        this.userRecipeController = userRecipeController;
        recipeCreationController.setConnectionController(this);
        userController.setConnectionController(this);

    }

    public void connectToServer() throws IOException {
        if (clientConnection != null) {
            if (clientConnection.isAlive()) {
                System.out.println("Already connected");
                return;
            } else {
                System.out.println("Closing the connection");
                serverDisconnected();
            }
        }

        clientConnection = new ClientConnection("127.0.0.1", 2343, this);
        clientConnection.start();
    }

    /**
     * This method disconnects the {@link ClientConnection} from the server. It first makes it so the Client can't
     * accept more intentions or Objects. It then sends an intention to the server that it wants to disconnect. Lastly,
     * a method is called to close the connection.
     *
     * @author Anton Persson
     * @author Heidi Wänmann
     */
    public void serverDisconnected() {
        clientConnection.setListenForIntention(false);
        clientConnection.setListenForObject(false);
        clientConnection.sendIntention(C_WANTS_TO_DISCONNECT);
        clientConnection.closeResources();
    }

    /**
     * This method packs up an {@link Object} from the server. Based on the intention in the server the Object is
     * unpacked differently.
     *
     * @param object The {@link Object} that is to be unpacked
     * @author Anton Persson
     * @author Heidi Wänmann
     */
    public void packUpObject(Object object, int intention) {
        switch (intention) {
            case S_SEND_ALL_RECIPES:
                ArrayList<Recipe> recipes = (ArrayList<Recipe>) object;
                ArrayList<Recipe> convertedRecipe = new ArrayList<>();
                for (Recipe recipe : recipes) {
                    Recipe recipe1 = recipeCreationController.convertRecipe(recipe);
                    convertedRecipe.add(recipe1);
                }
                recipeController.setRecipes(convertedRecipe);
                clientConnection.setListenForIntention(true);
                clientConnection.setListenForObject(false);
                break;
            case S_SEND_ALL_USERS:
                ArrayList<User> users = (ArrayList<User>) object;
                userController.setUsers(users);

                clientConnection.setListenForIntention(true);
                clientConnection.setListenForObject(false);
                break;
            case S_UPDATE_C_LIST_OF_USERS:
                ArrayList<User> newUsers = (ArrayList<User>) object;
                userController.updateListOfUsers(newUsers);

                clientConnection.setListenForIntention(true);
                clientConnection.setListenForObject(false);
                break;
            case S_SEND_ALL_INGREDIENTS:
                ArrayList<Ingredient> ingredients = (ArrayList<Ingredient>) object;
                ingredientController.setIngredients(ingredients);
                clientConnection.setListenForIntention(true);
                clientConnection.setListenForObject(false);
                break;
            case C_USER_WANT_OWN_RECIPES:
                ArrayList<Recipe> ownRecipes = (ArrayList<Recipe>) object;
                System.out.println("REACHED C_USER_WANT_OWN_RECIPES ----------------------------");
                userRecipeController.setUsersOwnRecipes(ownRecipes);
                clientConnection.setListenForIntention(true);
                clientConnection.setListenForObject(false);
                break;
            case C_USER_WANT_FAVORITES:
                ArrayList<Recipe> favoriteRecipes = (ArrayList<Recipe>) object;
                userRecipeController.setFavoriteRecipes(favoriteRecipes);
                clientConnection.setListenForIntention(true);
                clientConnection.setListenForObject(false);
        }
    }

    /**
     * This method reveals the intention of a client and then does something based on the intention.
     *
     * @param intention An int  that decides what happens
     */
    public void revealClientIntention(int intention) {
        switch (intention) {
            default:
                clientConnection.setListenForIntention(false);
                clientConnection.setListenForObject(true);
                break;
        }
    }

    /**
     * This method calls methods in {@link ClientConnection} to send a {@link User}
     *
     * @param user The User to send
     * @author Anton Persson
     */
    public void registerNewUser(User user) {
        clientConnection.sendIntention(C_WANT_TO_REGISTER);
        clientConnection.sendObject(user);
    }

    public static BufferedImage convert(ImageView imageView) {

        Image image = imageView.getImage();
        BufferedImage bufferedImage = new BufferedImage((int) image.getWidth(), (int) image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        java.awt.Graphics graphics = bufferedImage.createGraphics();
        graphics.drawImage(SwingFXUtils.fromFXImage(image, null), 0, 0, null);
        graphics.dispose();

        return bufferedImage;
    }

    public static byte[] compressImage(BufferedImage originalImage, String formatName, float quality) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(compress(originalImage, quality), formatName, outputStream);

        return outputStream.toByteArray();
    }

    private static BufferedImage compress(BufferedImage originalImage, float quality) {
        BufferedImage compressedImage = new BufferedImage(originalImage.getWidth(), originalImage.getHeight(), originalImage.getType());
        compressedImage.createGraphics().drawImage(originalImage, 0, 0, null);

        return compressedImage;
    }


    public void createNewRecipe(Recipe recipe) {
        if (recipe.getImageViewOfRecipe() != null && recipe.getImageOfRecipe() == null) {
            try {
                BufferedImage bufferedImage = convert(recipe.getImageViewOfRecipe());
                byte[] compressedImage = compressImage(bufferedImage, "png", 0.5f);
                recipe.setImageOfRecipe(compressedImage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        clientConnection.sendIntention(C_CREATE_RECIPE);
        clientConnection.sendObject(recipe);
    }

    public void userLogIn(User user) {
        clientConnection.sendIntention(C_WANT_TO_LOGIN);
        clientConnection.sendObject(user);
    }

    public void updateUserDetails(User user) {
        clientConnection.sendIntention(C_UPDATE_USER_DETAILS);
        clientConnection.sendObject(user);
    }
    public void getOwnRecipes(User user) {
        clientConnection.sendIntention(C_USER_WANT_OWN_RECIPES);
        clientConnection.sendObject(user);
    }
    public void getFavoriteRecipes(User user) {
        System.out.println("Favorit get");
        clientConnection.sendIntention(C_USER_WANT_FAVORITES);
        clientConnection.sendObject(user);
        System.out.println("End favorite get");
    }
    public void addFavoriteRecipe(Recipe recipe) {
        User user = userController.getLoggedInUser();
        HashMap<String, Object> addFavoriteRecipe = new HashMap<>();
        addFavoriteRecipe.put("user", user);
        addFavoriteRecipe.put("recipe", recipe);
        revealClientIntention(C_WANTS_TO_ADD_FAVORITE);
        clientConnection.sendObject(addFavoriteRecipe);
    }
}