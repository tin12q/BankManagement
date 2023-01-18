package tin.bank.client.control.Pane;

import java.sql.Date;
import java.time.LocalDate;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import tin.bank.client.model.DataHandle;

public class ProfileController {

    @FXML
    private MFXTextField addr;

    @FXML
    private MFXButton changeBtn;

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
    private MFXTextField state;

    @FXML
    private MFXTextField zipcode;

    @FXML
    private void initialize() {

        fname.setText(DataHandle.mainAccount.getFname());
        lname.setText(DataHandle.mainAccount.getLname());
        datePicker.setValue(DataHandle.mainAccount.getDOB().toLocalDate());
        email.setText(DataHandle.mainAccount.getEmail());
        phone.setText(DataHandle.mainAccount.getPhone());
        addr.setText(DataHandle.mainAccount.getAddress());
        city.setText(DataHandle.mainAccount.getCity());
        state.setText(DataHandle.mainAccount.getState());
        zipcode.setText(DataHandle.mainAccount.getZipCode());
        datePicker.setValue(DataHandle.mainAccount.getDOB().toLocalDate());
        changeBtn.setOnAction(event -> {
            LocalDate date = datePicker.getValue();
            Date sqlDate = Date.valueOf(date);
            DataHandle.updateCustomer(DataHandle.mainAccount.getId(), fname.getText(), lname.getText(),
                    email.getText(),
                    phone.getText(),
                    addr.getText(), city.getText(), state.getText(), zipcode.getText(), sqlDate);
        });

    }
}
