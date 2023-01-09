package tin.bank.client.control.Pane;

import javafx.fxml.FXML;

import javafx.geometry.Insets;
import javafx.scene.control.Label;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import tin.bank.client.model.DataHandle;

import tin.bank.client.model.Ledger;

public class History {
    @FXML
    private GridPane gridPane;

    @FXML
    private void initialize() {
        DataHandle.getLedger();
        System.out.println("oke");
        int row = 0;
        int col = 0;
        // TODO: Phân loại các giao dịch theo loại

        for (Ledger Ledger : DataHandle.ledgers) {
            // System.out.println(Ledger.getAmount());

            // set VBox
            VBox vBox = new VBox();
            vBox.setSpacing(20);
            vBox.setPrefWidth(300);
            vBox.setPadding(new Insets(10));
            vBox.getStyleClass().add("transaction-box");
            // set Label infomation
            Label destinationId = new Label();
            switch (Ledger.getTransactionType()) {
                case "transfer":
                    destinationId.setText("To " + Ledger.getDestinationName());
                    break;
                case "Withdraw":
                    destinationId.setText("Withdraw");
                    break;
                case "Deposit":
                    destinationId.setText("Deposit");
                    break;

            }
            // Label destinationId = new Label("To " + Ledger.getDestinationCustomerId());
            Label amount = new Label(Ledger.getAmount());
            Label date = new Label(Ledger.getTransactionDate());
            // check description
            if (Ledger.getDescription() != null)
                vBox.getChildren().addAll(destinationId, amount, date, new Label(Ledger.getDescription()));
            else
                vBox.getChildren().addAll(destinationId, amount, date);
            // check to nextline
            gridPane.add(vBox, col, row);
            col++;
            if (col > 1) {
                col = 0;
                row++;
            }
        }
    }
}
