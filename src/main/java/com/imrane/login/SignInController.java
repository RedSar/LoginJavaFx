package com.imrane.login;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignInController implements Initializable {

    @FXML
    private TextField txf_username;
    @FXML
    private TextField txf_password;
    @FXML
    private Button btn_login;
    @FXML
    private Button btn_signUp;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btn_login.setOnAction(event -> {
            DBUtils.signInUser(event, txf_username.getText(), txf_password.getText());
        });

        btn_signUp.setOnAction(event -> {
            try {
                DBUtils.changeScene(event, "sign-up.fxml", "Regitration form", null, null, "signUp");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}