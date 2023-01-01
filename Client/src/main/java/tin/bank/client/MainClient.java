package tin.bank.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainClient extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainClient.class.getResource("LogInDialog.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 300, 200);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }
    // TODO: Jan 01 2023
    // 1. Add a method to check if the user is logged in DONE
    // 2. Add a method to log in DONE
    // 3. Add a method to log out DONE
    // 4. Add a method to register In progress
    // 5. Add a method to change password Not started

    public static void main(String[] args) {
        launch();
    }

}