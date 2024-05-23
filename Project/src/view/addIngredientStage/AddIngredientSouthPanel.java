package view.addIngredientStage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import model.GetGUIController;
import model.Measurement;

import java.net.URL;
import java.util.ResourceBundle;


/**
 *Class that represents the bottom panel of the "Add new ingredient"-window.
 * It contains a TextField for the amount, a ChoiceBox the measurments and the add Button
 * @author Anton Jansson
 */

public class AddIngredientSouthPanel implements Initializable {

    @FXML
    public ChoiceBox<Measurement> measurmentsChoiceBox;
    @FXML
    public Button add;
    @FXML
    public TextField amount;
    private Measurement[] measurments = {Measurement.DL, Measurement.KG, Measurement.G, Measurement.L, Measurement.CL, Measurement.Burk, Measurement.ML, Measurement.ST};

    public void addIngredient(ActionEvent actionEvent) {
        System.out.println("Test lägg till knapp");
        String amountGiven = amount.getText();
        double amountGivenDouble = 0;
        boolean goodInput = false;
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        try {
            amountGivenDouble = Double.parseDouble(amountGiven);
            goodInput = true;
        } catch (NullPointerException ne) {
            System.out.println("Vänligen ange en mängd");
            alert.setTitle("Vänligen ange en mängd");
            alert.setHeaderText(null);
            alert.setContentText("Alla rutor är inte fyllda. Vänligen ange något i alla rutor och försök igen");
            alert.showAndWait();
        } catch (NumberFormatException nfe) {
            System.out.println("Vänligen ange endast siffror i mängden");
            alert.setTitle("Vänligen ange endast siffror i mängden");
            alert.setHeaderText(null);
            alert.setContentText("Vänligen ange en siffra som mängd och försök igen");
            alert.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
            alert.setTitle("Grattis!");
            alert.setHeaderText(null);
            alert.setContentText("Du har lyckats skapa ett error eller hittat en bugg, bra jobbat! :)\n Vänlig kontakta ansvarig");
            alert.showAndWait();
            throw new RuntimeException(e);
        }
        Measurement mesurment = measurmentsChoiceBox.getValue();
        if (mesurment == null) {
            alert.setTitle("Vänligen ange en enehet");
            alert.setHeaderText(null);
            alert.setContentText("Vänligen ange hur mycket som behövs av ingrediensen ");
            alert.showAndWait();
        }
        if (goodInput && amountGivenDouble > 0 && mesurment != null) {
            String mesurmentString = mesurment.toString();
            System.out.println("output:" + amountGivenDouble + mesurmentString);
            //Todo koppla in redan skriven kod
            GetGUIController.getAddIngredientGUIController().addIngredient(amountGiven, mesurmentString);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        measurmentsChoiceBox.getItems().addAll(measurments);
    }
}
