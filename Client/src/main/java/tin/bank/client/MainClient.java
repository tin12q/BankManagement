package tin.bank.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tin.bank.client.model.DataHandle;
import tin.bank.client.model.Decrypt;

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

    public static void main(String[] args) {
        //System.out.println(Decrypt.decrypt("abcd"));
        //System.out.println("$2a$10$Kh3nwQk6cJVEP7PIEY0PHOQLXdtj1KKzo6XU4tOzkN4KymgB4j156");
        //System.out.println(Decrypt.checkPassword("abcd", "$2a$10$Kh3nwQk6cJVEP7PIEY0PHOQLXdtj1KKzo6XU4tOzkN4KymgB4j156"));
        launch();
       //DataHandle.updatePass();
        //tSystem.out.println("$2a$10$hgNjQNOxeHs8g5t1mBuWfuhZj0hEO40Y7SMlrgQWuzk".length());
        //System.out.println("0x243261243130244658357A644E2E3459742E5A784B51614167386D586546354232356D594E61646241514B6B793152336F6551444977704F77707543".length());
        //System.out.println(Decrypt.checkPassword("abcd",new String("0x243261243130244658357A644E2E3459742E5A784B51614167386D586546354232356D594E61646241514B6B793152336F6551444977704F77707543")));
    }

}