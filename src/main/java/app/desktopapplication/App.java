package app.desktopapplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import storage.IStorage;
import storage.LocalStorage;
import storage.PersistentStorage;
import utils.auth;

import java.io.IOException;
import java.util.Objects;

/**
 * JavaFX App
 */
public class App extends Application {

    public static IStorage Storage;

    @Override
    public void start(Stage stage) throws Exception {
        Storage = new PersistentStorage();

        try {
            FXMLLoader fxmlLoader;
            if (Storage.getAccessToken().isEmpty()) {
                fxmlLoader = new FXMLLoader(App.class.getResource("login-signin.fxml"));
            } else if( String.valueOf(auth.call_auth(Storage.getAccessToken()).getRole()).equals("Admin") ){
                fxmlLoader = new FXMLLoader(App.class.getResource("admin.fxml"));
                stage.setMaximized(true);
            } else {
                fxmlLoader = new FXMLLoader(App.class.getResource("dashboard.fxml"));
                stage.setMaximized(true);
            }

            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Health Book");
            stage.getIcons().add(new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/medicine-book-red.png"))));
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            System.out.println("not found or something");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        launch();
    }

}