package com.imrane.login;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WelcomeController implements Initializable {

    @FXML
    private Label lbl_username;

    @FXML
    private Label lbl_bio;
    @FXML
    private Button btn_logout;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btn_logout.setOnAction(event -> {
            try {
                DBUtils.changeScene(event, "sign-in.fxml", "Login Form", null, null, "welcome");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void setUserInformation(String username, String bio) {
        lbl_username.setText("Hi " + username + ", nice to have you aboard!");
        lbl_bio.setText(bio);
    }
}
