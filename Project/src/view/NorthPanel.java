package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class NorthPanel implements Initializable {
    @FXML
    private Button profileButton;
    public NorthPanel() {

    }

    public void showProfileWindow(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "You have chosen to log in");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image image = new Image(getClass().getResourceAsStream("/view/NotLoggedIn.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(45);
        imageView.setFitWidth(48);
        Rectangle clip = new Rectangle(imageView.getFitWidth(), imageView.getFitHeight());
        clip.setArcWidth(20);
        clip.setArcHeight(20);
        imageView.setClip(clip);

        profileButton.setGraphic(imageView);

        System.out.println("Hej");
    }
}
