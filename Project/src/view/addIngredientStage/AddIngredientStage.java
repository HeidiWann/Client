package view.addIngredientStage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AddIngredientStage extends Application {

    @Override
    public void start(Stage addIngredientStage)  {
        Parent root=null;
        try {
            root= FXMLLoader.load(getClass().getResource("../addIngredientStage/AddIngredientStage.fxml"));
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e);
            throw new RuntimeException();
        }
        addIngredientStage.setScene(new Scene(root));
        addIngredientStage.setResizable(false);
        addIngredientStage.show();
    }



}
