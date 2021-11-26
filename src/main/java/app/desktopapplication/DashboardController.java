
package app.desktopapplication;


import app.desktopapplication.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.authentication.AuthenticationResult;
import utils.postRequest;

import java.io.IOException;
import java.util.Objects;
//import java.sql.SQLException;

public class DashboardController {


    @FXML
    public void logOut(MouseEvent mouseEvent) {
        App.Storage.ClearStorage();
        try {

            Node node = (Node) mouseEvent.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();
            Scene scene = new Scene((Parent) FXMLLoader.load(Objects.requireNonNull(this.getClass()
                    .getResource("login-signin.fxml"))));

            stage.setScene(scene);
            stage.show();
            stage.setMaximized(false);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}