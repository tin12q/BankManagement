package tin.bank.client.control.Pane;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.enums.FloatMode;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderRepeat;
import tin.bank.client.model.DataHandle;

public class Withdraw {
    @FXML
    private MFXButton okBtn;
    @FXML
    private MFXTextField amountTextField;
    @FXML
    private MFXButton currentBtn;

    @FXML
    private void initialize() {
        currentBtn.setText(DataHandle.mainAccount.getBalance().toString());
        amountTextField.setFloatMode(FloatMode.BORDER);
        okBtn.setText("Withdraw");
        okBtn.setOnAction(event -> withdraw(amountTextField.getText()));

    }

    private void withdraw(String amount) {
        Double amountDouble = Double.parseDouble(amount);
        if (amountDouble <= 0) {
            amountTextField.setText("Withdraw failed");
        } else {
            DataHandle.withdrawMoney(DataHandle.mainAccount.getId(), amountDouble);
            DataHandle.getMainAccount(DataHandle.mainAccount.getUsername());
            currentBtn.setText(DataHandle.mainAccount.getBalance().toString());
        }
    }
}
