package tin.bank.client.control.Pane;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import tin.bank.client.model.DataHandle;

public class Deposit {
    @FXML
    private MFXButton okBtn;
    @FXML
    private MFXTextField amountTextField;
    @FXML
    private MFXButton currentBtn;

    @FXML
    private void initialize() {
        okBtn.setOnAction(event -> Deposit(amountTextField.getText()));

    }

    private void Deposit(String amount) {
        Double amountDouble = Double.parseDouble(amount);
        if (amountDouble <= 0) {
            amountTextField.setText("Deposit failed");
        } else {
            DataHandle.depositMoney(DataHandle.mainAccount.getId(), amountDouble);
            DataHandle.getMainAccount(DataHandle.mainAccount.getUsername());
            currentBtn.setText(DataHandle.mainAccount.getBalance().toString());
        }
    }

}
