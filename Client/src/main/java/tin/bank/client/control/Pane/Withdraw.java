package tin.bank.client.control.Pane;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.enums.FloatMode;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.PasswordField;
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
        if ((amountDouble <= 0.0) || (amountDouble > DataHandle.mainAccount.getBalance())) {
            amountTextField.setText("Withdraw failed");
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("Confirm");
            dialog.setHeaderText(
                    "Exceeded the limit of the account or the amount is less than 0. Please try again");
            dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
            dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
            dialog.showAndWait();
        } else {
            // dialog to check password
            Dialog<PasswordField> dialog = new Dialog<>();
            dialog.setTitle("Confirm");
            dialog.setHeaderText("Please enter your password to confirm");
            //add textfield to dialog
            PasswordField textField = new PasswordField();
            dialog.getDialogPane().setContent(textField);
            dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
            dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
            dialog.showAndWait();
            //check password
            if (!DataHandle.checkLoginHashed(DataHandle.mainAccount.getUsername(), textField.getText())) {
                amountTextField.setText("Withdraw failed");
                Dialog<ButtonType> dialog1 = new Dialog<>();
                dialog1.setTitle("Confirm");
                dialog1.setHeaderText("Wrong password. Please try again");
                dialog1.getDialogPane().getButtonTypes().add(ButtonType.OK);
                dialog1.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
                dialog1.showAndWait();

            } else {
                DataHandle.withdrawMoney(DataHandle.mainAccount.getId(), amountDouble);
                DataHandle.getMainAccount(DataHandle.mainAccount.getUsername());
                currentBtn.setText(DataHandle.mainAccount.getBalance().toString());
                amountTextField.setText("Withdraw success");
            }

        }
    }
}
