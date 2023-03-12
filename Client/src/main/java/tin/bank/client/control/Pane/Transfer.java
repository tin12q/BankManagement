package tin.bank.client.control.Pane;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import tin.bank.client.model.DataHandle;

public class Transfer {

    @FXML
    private MFXTextField idTf;

    @FXML
    private MFXTextField amountTextfield;

    @FXML
    private MFXButton currentBtn;
    @FXML
    private HBox idHb;
    @FXML
    private HBox acHb;

    @FXML
    private MFXButton okBtn;
    @FXML
    private TextArea des;

    @FXML
    private void initialize() {
        // Set the current account balance
        currentBtn.setText(DataHandle.mainAccount.getBalance().toString());
        // Add all accounts name to the ComboBox

        okBtn.setOnAction(this::handleTransfer);
    }

    // @FXML
    // NOTE: I will improve this method later
    // TODO: Jan 07 2023
    private void handleTransfer(ActionEvent event) {
        // Get the selected account from the ComboBox
        // Account selectedAccount = accountBox.getSelectionModel().getSelectedItem();

        // Continue with the rest of the transfer process...
        // Get the amount from the textfield
        // TODO: Add regex
        Double amount = Double.parseDouble(amountTextfield.getText());
        if (amount <= 0) {
            amountTextfield.setText("Transfer failed");

        } else {
            // DataHandle.transferMoney(DataHandle.mainAccount.getId(),
            // accountBox.getValue().getId(), amount);
            // create dialog to confirm
            String destinationId = idTf.getText();
            Dialog<TextField> dialog = new Dialog<>();
            dialog.setTitle("Confirm");
            dialog.setHeaderText(
                    "Are you sure you want to transfer " + amount + " to " + DataHandle.getName(destinationId) + "?");
            // add textfield to dialog
            PasswordField textField = new PasswordField();
            dialog.getDialogPane().setContent(textField);
            dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
            dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
            dialog.showAndWait();
            // check password
            if (!DataHandle.checkLoginHashed(DataHandle.mainAccount.getUsername(), textField.getText())) {
                amountTextfield.setText("Transfer failed");
                Dialog<ButtonType> dialog1 = new Dialog<>();
                dialog1.setTitle("Confirm");
                dialog1.setHeaderText("Wrong password. Please try again");
                dialog1.getDialogPane().getButtonTypes().add(ButtonType.OK);
                dialog1.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
                dialog1.showAndWait();
            } else {
                // transfer money
                DataHandle.transferMoney(DataHandle.mainAccount.getId(), destinationId, amount, des.getText());
                // Show the result
                amountTextfield.setText("Transfer success");
                // Update the current account balance
                DataHandle.getMainAccount(DataHandle.mainAccount.getUsername());
                currentBtn.setText(DataHandle.mainAccount.getBalance().toString());
            }
            // Show the result
            // amountTextfield.setText("Transfer success");
            // Update the current account balance

        }
    }
}
