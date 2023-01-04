package tin.bank.client.control;

import java.io.IOException;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tin.bank.client.model.Account;
import tin.bank.client.model.DataGet;

import javafx.scene.Node;

public class RegisterDialog {

    @FXML
    private AnchorPane ac;

    @FXML
    private MFXTextField name;

    @FXML
    private MFXPasswordField pwd;

    @FXML
    private MFXButton registerBtn;

    @FXML
    private MFXTextField usr;

    @FXML
    private void initialize() {
        // DataGet.getUsersWithID();
        // FIXME: getNumber later
        // int id = DataGet.getNumberOfAccounts() + 2;

        // System.out.println(id);

        registerBtn.setOnAction(event -> loadPage("LogInDialog", event));

    }

    private void loadPage(String page, ActionEvent event) {
        try {
            if (checkIfUserExists(usr.getText())) {
                System.out.println("User already exists");
                usr.setText("Username already exists");
                return;
            }
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/tin/bank/client/" + page + ".fxml"));
            Parent content = loader.load();

            Scene scene = new Scene(content);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(scene);
            stage.setX(300);
            stage.setY(200);
            stage.setTitle("Client");
            stage.show();

        }

        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean checkIfUserExists(String usrStr) {
        for (Account i : DataGet.accounts) {
            if (usrStr.equals(i.getUsername())) {
                return true;
            }
        }
        return false;
    }
}
