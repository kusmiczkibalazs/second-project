package manager.javafx.controller;

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

import java.io.IOException;

public class LoginController {

    private ManagerModel model = new ManagerModel();
    private String userName;
    private String userPassword;

    @FXML
    private Label infoLabel;

    @FXML
    private TextField userNameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button registrationButton;

    @FXML
    private Button loginButton;

    @FXML
    private void initialize(){
        Platform.runLater(() -> loginButton.requestFocus());
    }

    @FXML
    private void onRegistrationClick(){
        displayInfoMessage("Regisztáció");
    }

    @FXML
    private void onLoginClick(ActionEvent event) throws IOException {
        userName = userNameField.getText();
        userPassword = passwordField.getText();

        if (model.login(userName, userPassword)) {
            //Ha a loginfeltétel teljesül:
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("manager.fxml"));
            Parent root = fxmlLoader.load();
            ManagerController managerController = fxmlLoader.<ManagerController>getController();
            //itt passzolható paraméter a manager egy metódusának, ha kéne
            managerController.setTempUsernameLabel(userNameField.getText());

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } else{
            System.out.println("Érvénytelen adatok");
        }
    }

    private void displayErrorMessage(String message){
        infoLabel.setTextFill(Color.RED);
        infoLabel.setText(message);
    }

    private void displayInfoMessage(String message){
        infoLabel.setTextFill(Color.BLACK);
        infoLabel.setText(message);
    }

    private void clearLabel(){
        infoLabel.setText("");
    }
}
