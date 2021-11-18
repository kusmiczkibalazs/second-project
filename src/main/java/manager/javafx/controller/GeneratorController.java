package manager.javafx.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.stage.Stage;
import javafx.util.Duration;
import manager.model.ManagerModel;
import org.tinylog.Logger;

import java.io.IOException;

public class GeneratorController {

    private ManagerModel model = new ManagerModel();

    @FXML
    private Slider lengthSlider;

    @FXML
    private CheckBox capitalLetterCheckbox;

    @FXML
    private CheckBox numberCheckbox;

    @FXML
    private CheckBox specialCharacterCheckbox;

    @FXML
    private Label infoLabel;

    @FXML
    private void onPasswordGeneratorClick() {
        model.copyToClipboard(model.generatePassword((int)lengthSlider.getValue(), capitalLetterCheckbox.isSelected(), numberCheckbox.isSelected(), specialCharacterCheckbox.isSelected()));

        Logger.debug("Length:" + (int) lengthSlider.getValue());
        Logger.debug("Capitals:" + capitalLetterCheckbox.isSelected() + " " +
                     "Numbers:" + numberCheckbox.isSelected() + " " +
                     "Specials:" + specialCharacterCheckbox.isSelected());
        //TODO vágólapra másolás
        infoLabel.setText("Jelszó vágólapra másolva");
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> infoLabel.setText("")));
        timeline.play();
    }

    @FXML
    private void onBackClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("menu.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

}
