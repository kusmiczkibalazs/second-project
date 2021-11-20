package manager.javafx.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import org.tinylog.Logger;

import java.io.IOException;
import java.util.List;

public class ManagerController {

    @FXML
    private ComboBox<String> dropDownMenu;

    @FXML
    private void initialize () {
        Platform.runLater(() -> {
            dropDownMenu.requestFocus();
            Stage stage = (Stage) dropDownMenu.getScene().getWindow();
            stage.centerOnScreen();
        });

        List<String> testList = List.of("first", "second", "third");

        ObservableList<String> observableList = FXCollections.observableArrayList(testList);
        dropDownMenu.setItems(observableList);
    }

    @FXML
    private void onChooseProfileClick() {
        Logger.trace("Chosen profile: " + dropDownMenu.getValue());
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
