package tin.bank.client.control.Pane;

import javafx.fxml.FXML;

import javafx.geometry.Insets;
import javafx.scene.control.Label;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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

        // TODO: Phân loại các giao dịch theo loại
        // TODO: GetLedger byOrder
        addToScene();
    }

    private void addToScene() {
        int row = 0;
        int col = 0;
        for (Ledger Ledger : DataHandle.ledgers) {
            // System.out.println(Ledger.getAmount());

            // set VBox
            VBox vBox = new VBox();
            vBox.setSpacing(20);
            vBox.setPrefWidth(400);
            vBox.setPrefHeight(300);
            vBox.setPadding(new Insets(10, 0, 0, 0));
            vBox.getStyleClass().add("transaction-box");

            HBox hBox = new HBox();
            hBox.setPrefSize(400, 300);
            hBox.setMaxSize(400, 300);

            VBox vbox2 = new VBox();
            vbox2.setPrefSize(400, 300);
            vbox2.getStyleClass().add("transaction-amount");
            vbox2.setMaxSize(400, 300);

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
            destinationId.getStyleClass().add("transaction-destination");
            vBox.getChildren().addAll(destinationId, hBox);
            vBox.setMargin(destinationId, new Insets(0, 0, 0, 10));

            hBox.getChildren().addAll(vbox2);
            Label description = new Label();
            // check description
            if (Ledger.getDescription() != null) {
                description.setText(Ledger.getDescription());
                vbox2.getChildren().addAll(amount, date, description);
                vbox2.setMargin(description, new Insets(0, 0, 0, 10));
            } else
                vbox2.getChildren().addAll(amount, date);
            // check to nextline
            vBox.setAlignment(javafx.geometry.Pos.BOTTOM_LEFT);
            vbox2.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
            vbox2.setSpacing(10);
            // vbox set margin
            vbox2.setMargin(amount, new Insets(0, 0, 0, 10));
            vbox2.setMargin(date, new Insets(0, 0, 0, 10));

            gridPane.add(vBox, col, row);

            col++;
            if (col > 1) {
                col = 0;
                row++;
            }
        }
    }
}
