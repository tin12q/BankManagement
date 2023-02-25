package tin.bank.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import tin.bank.client.model.DataHandle;

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
    // 1. Add a method to check if the user is logged in [X]
    // 2. Add a method to log in [X]
    // 3. Add a method to log out [X]
    // 4. Add a method to register [x]
    // 5. Add a method to change password [In progress]

    public static void main(String[] args) {

        // for (int i = 100; i <= 10000; i++) {
        // String fname = "acc";
        // String lname = Integer.toString(i);
        // Date dob = Date.valueOf(LocalDate.of(2000, 01, 10));
        // String email = "acc" + Integer.toString(i) + "@abcd.com";
        // String phone = "1234567890" + Integer.toString(i);
        // String address = "1234 Main St " + Integer.toString(i);
        // String city = "New York " + Integer.toString(i);
        // String state = "NY " + Integer.toString(i);
        // String zip = "12345 " + Integer.toString(i);
        // String username = "acc" + Integer.toString(i);
        // String password = "abcd";
        // Double init = 0.0;
        // DataHandle.createUser(fname, lname, dob, email, phone, address, city, state,
        // zip, username, password, init);
        // // DataHandle.createUser("acc", Integer.toString(i),new LocalDate().now(),
        // // STYLESHEET_CASPIAN, STYLESHEET_CASPIAN, STYLESHEET_CASPIAN,
        // // STYLESHEET_CASPIAN, STYLESHEET_CASPIAN, STYLESHEET_CASPIAN,
        // // STYLESHEET_MODENA, STYLESHEET_CASPIAN, null);
        // }
        // for (int i = 1; i <= 3000; i++) {
        // DataHandle.depositMoney(Integer.toString(i + 1002), 100000.0);
        // }
        launch();
    }

}