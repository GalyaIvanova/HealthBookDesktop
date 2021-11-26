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

public class LoginController {

    @FXML
    private Label lblErrors;
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPassword;
    @FXML
    private Button btnSignin;


    @FXML
    public void handleButtonAction(MouseEvent event) {
        AuthenticationResult authenticationResult = this.logIn();
        if (authenticationResult.isSuccessful()) {
           // System.out.printf(authenticationResult.getRole());

            if(authenticationResult.getRole().contains("Admin")){
                try {

                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();
                    Scene scene = new Scene((Parent) FXMLLoader.load(Objects.requireNonNull(this.getClass()
                            .getResource("admin.fxml"))));

                    stage.setScene(scene);
                    stage.show();
                    stage.setMaximized(true);
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                    System.out.println("can't find path reference of admin");
                }
            }else{
            try {

                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();
                Scene scene = new Scene((Parent) FXMLLoader.load(Objects.requireNonNull(this.getClass()
                        .getResource("dashboard.fxml"))));

                stage.setScene(scene);
                stage.show();
                stage.setMaximized(true);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }}
        } else {
            lblErrors.setText("Wrong username or password!");
        }

        App.Storage.setAccessToken(authenticationResult.getAccessToken());

        //System.out.println("maafaka: " + App.Storage.getAccessToken());

    }


    private AuthenticationResult logIn() {
        String username = this.txtUsername.getText();
        String password = this.txtPassword.getText();

        AuthenticationResult authenticationResult = new AuthenticationResult();

        try {
            authenticationResult = postRequest.call_me(username, password);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return authenticationResult;
    }


}