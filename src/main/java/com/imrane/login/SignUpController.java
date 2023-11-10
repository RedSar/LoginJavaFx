package com.imrane.login;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {

    @FXML
    private Button btn_signIn;
    @FXML
    private Button btn_register;
    @FXML
    private TextField txf_username;

    @FXML
    private PasswordField txf_password;

    @FXML
    private TextArea txa_bio;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btn_register.setOnAction(event -> {
            DBUtils.signUpUser(event, txf_username.getText(), txf_password.getText(), txa_bio.getText());
        });

        btn_signIn.setOnAction(event -> {
            try {
                DBUtils.changeScene(event, "sign-in.fxml", "Login", null, null, "login");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
