package tin.bank.client.control.Pane;

import java.util.LinkedList;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import tin.bank.client.model.Account;
import tin.bank.client.model.DataGet;

public class Transfer {
    // TODO: Jan 02 2023
    // IMPORTANT: CHECK CHATGPT INSTRUCTIONS
    // 1. Add a method to transfer money to another account DONE
    // 2. Add button to transfer money DONE
    // 3. Add combobox to select and find account DONE
    // 4. Add textfield to enter amount DONE
    // go go
    private LinkedList<Account> accounts = DataGet.accounts;
    @FXML
    private MFXComboBox<Account> accountBox;

    @FXML
    private MFXTextField amountTextfield;

    @FXML
    private MFXButton currentBtn;

    @FXML
    private MFXButton okBtn;

    @FXML
    private void initialize() {
        // Set the current account balance
        currentBtn.setText(DataGet.mainAccount.getBalance().toString());
        // Add all accounts name to the ComboBox
        accountBox.getItems().addAll(accounts);
        okBtn.setOnAction(this::handleTransfer);
    }

    // FIXME: Later or improve server structure then improve this method
    // @FXML
    // NOTE: I will improve this method later
    // TODO: Jan 07 2023
    private void handleTransfer(ActionEvent event) {
        // Get the selected account from the ComboBox
        // Account selectedAccount = accountBox.getSelectionModel().getSelectedItem();

        // Continue with the rest of the transfer process...
        // Get the amount from the textfield
        Double amount = Double.parseDouble(amountTextfield.getText());

        // FIXME: handle transfer later
        DataGet.transferMoney(DataGet.mainAccount.getId(), accountBox.getValue().getId(), amount);

        currentBtn.setText(DataGet.mainAccount.getBalance().toString());
    }
}
