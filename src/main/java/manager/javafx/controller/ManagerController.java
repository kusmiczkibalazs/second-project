package manager.javafx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ManagerController {

    @FXML
    private Label tempUsernameLabel;

    public void setTempUsernameLabel(String userName){
        tempUsernameLabel.setText(userName);
    }
}
