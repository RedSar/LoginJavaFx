package com.imrane.login;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.sql.*;
import java.util.ResourceBundle;


public class DBUtils {
    public static void changeScene(ActionEvent event, String fxmlFile, String title, String username, String bio, String view) throws IOException {
        Parent root = null;

        if (username != null && bio != null) {
            FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
            root = loader.load();
            WelcomeController controller = loader.getController();
            controller.setUserInformation(username, bio);
        } else {
            root = FXMLLoader.load(DBUtils.class.getResource(fxmlFile));
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setTitle(title);
        if (view.equals("login")) {
            stage.setScene(new Scene(root, 800, 400));
            System.out.println("view" + view);
        } else {
            stage.setScene(new Scene(root, 800, 500));
        }

        stage.show();
    }

    public static void signInUser(ActionEvent event, String username, String password) {
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        connection = getConnection();

        try {
            pstm = connection.prepareStatement("SELECT * FROM user WHERE username=?");
            pstm.setString(1, username);

            rs = pstm.executeQuery();

            if (!rs.isBeforeFirst()) {
                showAlert(Alert.AlertType.ERROR, "Login Error", "⚠️ Invalid credentials, Please try again!");
            } else {
                if (rs.next()) {
                    if (BCrypt.checkpw(password, rs.getString("password"))) {
                        changeScene(event, "welcome.fxml", "Welcome", username, rs.getString("bio"), "welcome");
                    } else {
                        showAlert(Alert.AlertType.ERROR, "Login Error", "⚠️ Invalid credentials, Please try again!");

                    }

                }
            }


        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (connection != null) connection.close();
                if (pstm != null) pstm.close();
                if (rs != null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }


    }

    public static void signUpUser(ActionEvent event, String username, String password, String bio) {
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        connection = getConnection();

        try {
            pstm = connection.prepareStatement("SELECT * FROM user WHERE username=? ");
            pstm.setString(1, username);

            rs = pstm.executeQuery();
            if (rs.isBeforeFirst()) {
                showAlert(Alert.AlertType.ERROR, "Registration Error", "⚠️ Username is already taken");
            } else {
                pstm = connection.prepareStatement("INSERT INTO user(username, password, bio) VALUES(?, ?, ?)");
                pstm.setString(1, username);
                pstm.setString(2, BCrypt.hashpw(password, BCrypt.gensalt()));
                pstm.setString(3, bio);

                pstm.executeUpdate();
                changeScene(event, "welcome.fxml", "Welcome", username, bio, "welcome");
            }


        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) connection.close();
                if (pstm != null) pstm.close();
                if (rs != null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }

    }

    public static void showAlert(Alert.AlertType type, String title, String content) {
        System.out.println(content);
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.show();
    }

    private static Connection getConnection() {
        ResourceBundle rb = ResourceBundle.getBundle("db-config");
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    rb.getString("mysql.url"),
                    rb.getString("user"),
                    rb.getString("password"));

            System.out.println("✅ Connection succeeded: ");


        } catch (SQLException e) {
            System.out.println("⚠️ Connection Failed: ");
            e.printStackTrace();
        }

        return connection;
    }

    public static void main(String[] args) {
        getConnection();
    }

}
