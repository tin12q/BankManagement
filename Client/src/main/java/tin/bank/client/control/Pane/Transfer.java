package tin.bank.client.control.Pane;

import java.util.LinkedList;
import javafx.scene.control.ButtonType;

import io.github.palexdev.materialfx.controls.MFXButton;

import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.layout.HBox;
import tin.bank.client.model.Account;
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
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("Confirm");
            dialog.setHeaderText(
                    "Are you sure you want to transfer " + amount + " to " + DataHandle.getName(destinationId) + "?");
            dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
            dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
            dialog.showAndWait();
            if (dialog.getResult() == ButtonType.OK) {
                DataHandle.transferMoney(DataHandle.mainAccount.getId(), destinationId, amount);
                amountTextfield.setText("Transfer success");
            } else {
                amountTextfield.setText("Transfer failed");
            }
            // Show the result

            // Update the current account balance
            DataHandle.getMainAccount(DataHandle.mainAccount.getUsername());
            currentBtn.setText(DataHandle.mainAccount.getBalance().toString());
        }
    }
}
