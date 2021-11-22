package manager.javafx.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import manager.database.HandleData;
import manager.database.StoredData;
import manager.model.ManagerModel;
import org.tinylog.Logger;

import java.io.IOException;
import java.util.List;

public class ManagerController {

    private String currentUser;
    private ManagerModel managerModel = new ManagerModel();

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }

    @FXML
    private TextField profileNameField;

    @FXML
    private TextField userNameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label createUpdateLabel;

    @FXML
    private ComboBox<String> dropDownMenu;

    @FXML
    private Label userNameDisplayLabel;

    @FXML
    private Label chooseProfileInfoLabel;

    @FXML
    private void initialize () {
        Platform.runLater(() -> {
            dropDownMenu.requestFocus();
            Stage stage = (Stage) dropDownMenu.getScene().getWindow();
            stage.centerOnScreen();

            updateAppList();
        });
    }

    @FXML
    private void onSaveNewProfileClick() {
        if(isEmpty(profileNameField.getText(), userNameField.getText(), passwordField.getText())) {
            displayErrorMessage("Minden mező kitöltése kötelező", createUpdateLabel);
        } else {
             try{
                HandleData.storeProfile(currentUser, profileNameField.getText(), userNameField.getText(), passwordField.getText());
                updateAppList();
                displayInfoMessage("Profil mentve", createUpdateLabel);
                clearInputs();
            } catch (IllegalArgumentException e) {
                displayErrorMessage("Már létezik ilyen profil", createUpdateLabel);
            }
        }
    }

    @FXML
    private void onUpdateProfileClick() {
        if(isEmpty(profileNameField.getText(), userNameField.getText(), passwordField.getText())) {
            displayErrorMessage("Minden mező kitöltése kötelező", createUpdateLabel);
        } else {
            try {
                HandleData.updateProfile(currentUser, profileNameField.getText(), userNameField.getText(), passwordField.getText());
                updateAppList();
                displayInfoMessage("Profil frissítve", createUpdateLabel);
                clearInputs();
            } catch (IllegalArgumentException e) {
                displayErrorMessage("Nem létezik ilyen profil", createUpdateLabel);
            }
        }
    }

    @FXML
    private void onChooseProfileClick() {
        Logger.trace("Chosen profile: " + dropDownMenu.getValue());
        StoredData storedData = HandleData.returnProfileData(currentUser, dropDownMenu.getValue());
        managerModel.copyToClipboard(storedData.getAppPassword());
        userNameDisplayLabel.setText("Felhasználónév: " + storedData.getAppUser());
        displayInfoMessage("Jelszó vágólapra másolva", chooseProfileInfoLabel);
    }

    @FXML
    private void onBackClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("menu.fxml"));
        Parent root = fxmlLoader.load();
        MenuController menuController = fxmlLoader.<MenuController>getController();
        menuController.setCurrentUser(currentUser);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void updateAppList() {
        List<String> appList = HandleData.getApps(currentUser);
        ObservableList<String> observableList = FXCollections.observableArrayList(appList);
        dropDownMenu.setItems(observableList);
    }

    private boolean isEmpty(String profileNameField, String userNameField, String passwordField) {
        return profileNameField.equals("") || userNameField.equals("") || passwordField.equals("");
    }


    private void displayErrorMessage(String message, Label label){
        label.setTextFill(Color.RED);
        label.setText(message);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> label.setText("")));
        timeline.play();
    }

    private void displayInfoMessage(String message, Label label){
        label.setTextFill(Color.BLACK);
        label.setText(message);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> label.setText("")));
        timeline.play();
    }

    private void clearInputs() {
        profileNameField.clear();
        userNameField.clear();
        passwordField.clear();
    }
}
