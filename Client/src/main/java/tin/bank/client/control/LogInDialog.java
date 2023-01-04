package tin.bank.client.control;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tin.bank.client.model.DataHandle;

import java.io.IOException;

public class LogInDialog {

    private String usrStr;
    @FXML
    private AnchorPane ac;
    @FXML
    private MFXButton logInBtn;

    @FXML
    private MFXPasswordField pwd;

    @FXML
    private MFXTextField usr;
    @FXML
    private MFXButton registerBtn;

    // Login button clicked
    @FXML
    private void initialize() {
        System.out.println("initialize");
        ac.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                enterLoadPage("MainView", event);
            }
        });
        logInBtn.setOnAction(event -> loadPage("MainView", event));
        registerBtn.setOnAction(event -> registerPage("Register", event));
    }

    private void registerPage(String page, ActionEvent event) {
        try {

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

    private void loadPage(String page, ActionEvent event) {
        try {
            // FIXME: Login check here
            boolean logInCheck = DataHandle.loginCheck(usr.getText(), pwd.getText());
            if (logInCheck) {
                DataHandle.getMainAccount(usr.getText());
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/tin/bank/client/" + page + ".fxml"));
                Parent content = loader.load();

                Scene scene = new Scene(content);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                stage.setScene(scene);
                stage.setX(0);
                stage.setY(0);
                stage.setTitle("Client");
                stage.show();
            } else {
                System.out.println("Wrong password or username");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void enterLoadPage(String page, KeyEvent event) {
        try {
            // FIXME: login check here again

            // logInCheck = new LogInCheck(usr.getText(), pwd.getText());
            boolean logInCheck = DataHandle.loginCheck(usr.getText(), pwd.getText());
            if (logInCheck) {
                DataHandle.getMainAccount(usr.getText());
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/tin/bank/client/" + page + ".fxml"));
                Parent content = loader.load();

                Scene scene = new Scene(content);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                stage.setScene(scene);
                stage.setX(0);
                stage.setY(0);
                stage.setTitle("Client");
                stage.show();
            } else {
                System.out.println("Wrong password or username");
            }

        } catch (IOException e) {
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
