package tin.bank.client.control;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LogInDialog {

    private String pwdStr;
    private String usrStr;
    @FXML
    private MFXButton logInBtn;

    @FXML
    private PasswordField pwd;

    @FXML
    private TextField usr;
    //Login button clicked
    @FXML
    private void onLogInButtonClick() {
        System.out.println("Log in button clicked!");
        logInBtn.setOnAction(event -> loadPage("MainView",event));
    }

    private void loadPage(String page, ActionEvent event)  {
        try {
            pwdStr = pwd.toString();
            usrStr = usr.toString();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/tin/bank/client/"+page+".fxml"));
            Parent content = loader.load();

            Scene scene = new Scene(content);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(scene);
            stage.setX(0);
            stage.setY(0);
            stage.setTitle("Client");
            stage.show();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public String getUsrStr() {
        return usrStr;
    }

    public void setUsrStr(String usrStr) {
        this.usrStr = usrStr;
    }
}
