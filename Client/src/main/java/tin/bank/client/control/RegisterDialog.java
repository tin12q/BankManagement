package tin.bank.client.control;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tin.bank.client.model.DataHandle;

import javafx.scene.Node;

public class RegisterDialog {
    // private String pattern = "yyyy-MM-dd";

    @FXML
    private AnchorPane ac;

    @FXML
    private MFXTextField addr;

    @FXML
    private MFXTextField city;

    @FXML
    private MFXDatePicker datePicker;

    @FXML
    private MFXTextField email;

    @FXML
    private MFXTextField fname;

    @FXML
    private MFXTextField lname;

    @FXML
    private MFXTextField phone;

    @FXML
    private MFXPasswordField pwd;

    @FXML
    private MFXButton registerBtn;

    @FXML
    private MFXTextField state;

    @FXML
    private MFXTextField usr;

    @FXML
    private MFXTextField zipcode;

    @FXML
    private void initialize() {
        // DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
        // get the DayCell class from the MFXDatePicker

        registerBtn.setOnAction(event -> loadPage("LogInDialog", event));

    }

    private void loadPage(String page, ActionEvent event) {
        try {
            if (DataHandle.checkUserExists(usr.getText())) {
                System.out.println("User already exists");
                usr.setText("Username already exists");
                return;
            } else {
                LocalDate date = datePicker.getValue();
                Date sqlDate = Date.valueOf(date);
                DataHandle.createUser(fname.getText(), lname.getText(), sqlDate, email.getText(), phone.getText(),
                        addr.getText(), city.getText(), state.getText(), zipcode.getText(), usr.getText(),
                        pwd.getText(), (double) 0);
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

        }

        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
