package tin.bank.client.control.Pane;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.enums.FloatMode;
import javafx.fxml.FXML;

import javafx.geometry.Insets;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
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
    private MFXButton byNameBtn;
    @FXML
    private MFXButton byTypeBtn;
    @FXML
    private MFXButton byAmountBtn;

    @FXML
    private MFXButton findBtn;
    @FXML
    private MFXTextField nameField;

    @FXML
    private void initialize() {
        DataHandle.ledgers.clear();
        DataHandle.getLedger();
        System.out.println("oke");

        byNameBtn.setOnAction(event -> sortBy("Name"));
        byTypeBtn.setOnAction(event -> sortBy("Type"));
        byAmountBtn.setOnAction(event -> sortBy("Amount"));
        /*
         * findBtn.setOnAction(event -> {
         * // create new dialog have textfield to get name and find by database
         * Dialog<String> dialog = new Dialog<>();
         * dialog.setTitle("Find");
         * dialog.setHeaderText("Find by name");
         * dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK,
         * ButtonType.CANCEL);
         * MFXTextField textField = new MFXTextField();
         * textField.setFloatMode(FloatMode.BORDER);
         * dialog.getDialogPane().setContent(textField);
         * dialog.setResultConverter(dialogButton -> {
         * if (dialogButton == ButtonType.OK) {
         * return textField.getText();
         * }
         * return null;
         * });
         * 
         * dialog.showAndWait().ifPresent(name -> {
         * gridPane.getChildren().clear();
         * DataHandle.ledgers.clear();
         * DataHandle.findName(name);
         * addToScene();
         * });
         * });
         */
        nameField.textProperty().addListener((observable, oldValue, newValue) -> {
            gridPane.getChildren().clear();
            DataHandle.ledgers.clear();
            DataHandle.findName(newValue);
            addToScene();
        });
        findBtn.setOnAction(event -> {
            gridPane.getChildren().clear();
            DataHandle.ledgers.clear();
            DataHandle.findName(nameField.getText());
            addToScene();
        });
        addToScene();

    }

    private void sortBy(String type) {
        gridPane.getChildren().clear();
        DataHandle.ledgers.clear();
        DataHandle.sortBy(type);
        addToScene();
    }

    /*
     * private void find() {
     * gridPane.getChildren().clear();
     * DataHandle.find();
     * addToScene();
     * }
     */
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
            Label amount = new Label("Amount: " + Ledger.getAmount());
            Label date = new Label("On: " + Ledger.getTransactionDate());
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
