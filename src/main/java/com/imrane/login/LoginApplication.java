package com.imrane.login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginApplication.class.getResource("sign-in.fxml"));

        Scene loginScene = new Scene(fxmlLoader.load());

        stage.setTitle("Login Form");
        stage.setScene(loginScene);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}