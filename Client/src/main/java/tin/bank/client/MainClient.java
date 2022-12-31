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
        Scene scene = new Scene(fxmlLoader.load(), 300 , 200);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }
    // TODO: 2023-01-01
    //  1. Add a method to check if the user is logged in
    //  2. Add a method to log in
    //  3. Add a method to log out
    //  4. Add a method to register
    //  5. Add a method to change password  


    public static void main(String[] args) {
        launch();
    }
}