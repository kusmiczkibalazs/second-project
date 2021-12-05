package manager.javafx.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import manager.model.ManagerModel;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import manager.model.exceptions.IncorrectUsernameOrPasswordException;
import manager.model.exceptions.PasswordTooLongException;
import manager.model.exceptions.UserAlreadyExistsException;

import java.io.IOException;

public class LoginController {

    private final ManagerModel model = new ManagerModel();

    @FXML
    private Label infoLabel;

    @FXML
    private TextField userNameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private void initialize(){
        Platform.runLater(() -> loginButton.requestFocus());
    }

    @FXML
    private void onRegistrationClick(){
        String userName = userNameField.getText();
        String userPassword = passwordField.getText();
        if(isEmpty(userName, userPassword)) {
            displayErrorMessage("Minden mező kitöltése kötelező");
        } else {
            try {
                model.register(userName, userPassword);
                displayInfoMessage("Sikeres regisztráció");
            } catch (UserAlreadyExistsException e) {
                displayErrorMessage(e.getMessage());
            } catch (PasswordTooLongException e){
                displayErrorMessage(e.getMessage());
            }
        }
    }

    @FXML
    private void onLoginClick(ActionEvent event) throws IOException {
        String userName = userNameField.getText();
        String userPassword = passwordField.getText();
        if (isEmpty(userName, userPassword)) {
            displayErrorMessage("Minden mező kitöltése kötelező");
        } else {
            try {
                model.login(userName, userPassword);
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("menu.fxml"));
                Parent root = fxmlLoader.load();
                MenuController menuController = fxmlLoader.<MenuController>getController();
                menuController.setCurrentUser(userName);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IncorrectUsernameOrPasswordException e) {
                displayErrorMessage(e.getMessage());
            }
        }
    }

    private boolean isEmpty(String userName, String userPassword) {
        return userName.equals("") || userPassword.equals("");
    }

    private void displayErrorMessage(String message){
        infoLabel.setTextFill(Color.RED);
        infoLabel.setText(message);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> infoLabel.setText("")));
        timeline.play();
    }

    private void displayInfoMessage(String message){
        infoLabel.setTextFill(Color.BLACK);
        infoLabel.setText(message);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> infoLabel.setText("")));
        timeline.play();
    }
}
