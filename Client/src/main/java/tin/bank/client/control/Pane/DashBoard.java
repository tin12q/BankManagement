package tin.bank.client.control.Pane;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import tin.bank.client.control.MainView;
import tin.bank.client.model.DataHandle;
import tin.bank.client.model.Ledger;
import tin.bank.client.control.MainView;

public class DashBoard {
    @FXML
    private MFXButton balanceBtn;
    @FXML
    private MFXButton historyBtn;
    @FXML
    private MFXButton transferBtn;
    @FXML
    private MFXButton withdrawBtn;
    @FXML
    private Label id;

    @FXML
    private void initialize() {

        id.setText(DataHandle.mainAccount.getId());
        DataHandle.getLedger();
        balanceBtn.setText(DataHandle.mainAccount.getBalance().toString());
        // historyBtn.setOnAction(event -> loadPane("History", event));
        getHistory();
        getTransfer();
        getWithdraw();

    }

    private void getHistory() {
        historyBtn.setFont(new Font(25));
        String label = "";
        for (Ledger ledger : DataHandle.ledgers) {
            label += ledger.getDate() + " " + ledger.getTransactionType() + " " + ledger.getAmount()
                    + "\n";

        }

        historyBtn.setText(label);
        historyBtn.setTextAlignment(TextAlignment.LEFT);
        // historyBtn.setOnAction(e -> ex.geth().fire());

    }

    private void getTransfer() {
        transferBtn.setFont(new Font(25));
        String label = "";
        for (Ledger ledger : DataHandle.ledgers) {
            if (ledger.getTransactionType().equals("transfer")) {
                label += ledger.getDate() + " To " + ledger.getDestinationName() + " " + ledger.getAmount()
                        + "\n";
            }

        }

        transferBtn.setText(label);
        transferBtn.setTextAlignment(TextAlignment.LEFT);
        // transferBtn.setOnAction(e -> ex.getT().fire());
    }

    private void getWithdraw() {
        withdrawBtn.setFont(new Font(25));
        String label = "";
        for (Ledger ledger : DataHandle.ledgers) {
            if (ledger.getTransactionType().equals("Withdraw")) {
                label += ledger.getDate() + " " + ledger.getAmount()
                        + "\n";
            }

        }

        withdrawBtn.setText(label);
        withdrawBtn.setTextAlignment(TextAlignment.LEFT);
        // withdrawBtn.setOnAction(e -> ex.getW().fire());
    }
}
