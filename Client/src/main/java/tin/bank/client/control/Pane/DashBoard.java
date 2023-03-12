package tin.bank.client.control.Pane;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import tin.bank.client.model.DataHandle;
import tin.bank.client.model.Ledger;

import java.util.List;

public class DashBoard {
    private static final String WITHDRAW_TYPE = "Withdraw";
    private static final String DEPOSIT_TYPE = "Deposit";
    private static final String TRANSFER_TYPE = "transfer";
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
    private AnchorPane ac2;
    @FXML
    private HBox hb;
    @FXML
    private VBox vb;

    @FXML
    private void initialize() {
        id.setWrapText(true);
        // show text if text too long
        id.setTextAlignment(TextAlignment.CENTER);

        id.setText("ID: " + DataHandle.mainAccount.getId());


        balanceBtn.setText(DataHandle.mainAccount.getBalance().toString());
        // historyBtn.setOnAction(event -> loadPane("History", event));

        DataHandle.getLedger();

        getHistory();

        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();

        xAxis.setTickLabelFont(Font.font("System", FontWeight.BOLD, 20));
        yAxis.setTickLabelFont(Font.font("System", FontWeight.BOLD, 20));

        // Set the labels for the axes
        xAxis.setLabel("Transaction Type");
        yAxis.setLabel("Total Amount");

        // Create the bar chart and add the data
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Transaction History by Type");

        List<Ledger> ledgerList = DataHandle.ledgers; // Get the ledger data from somewhere

        ObservableList<XYChart.Series<String, Number>> seriesList = FXCollections.observableArrayList();

        double withdrawTotal = 0;
        double depositTotal = 0;
        double transferTotal = 0;

        // Calculate the total amount of each transaction type and add the data to the
        // chart
        for (Ledger ledger : ledgerList) {
            if (ledger.getType().equals(WITHDRAW_TYPE)) {
                withdrawTotal += ledger.getAmountDouble();
            } else if (ledger.getType().equals(DEPOSIT_TYPE)) {
                depositTotal += ledger.getAmountDouble();
            } else if (ledger.getType().equals("transfer")) {
                transferTotal += ledger.getAmountDouble();
            }
        }

        XYChart.Series<String, Number> withdrawSeries = new XYChart.Series<>();
        withdrawSeries.setName(WITHDRAW_TYPE);
        withdrawSeries.getData().add(new XYChart.Data<>(WITHDRAW_TYPE, withdrawTotal));
        seriesList.add(withdrawSeries);

        XYChart.Series<String, Number> depositSeries = new XYChart.Series<>();
        depositSeries.setName(DEPOSIT_TYPE);
        depositSeries.getData().add(new XYChart.Data<>(DEPOSIT_TYPE, depositTotal));
        seriesList.add(depositSeries);

        XYChart.Series<String, Number> transferSeries = new XYChart.Series<>();
        transferSeries.setName(TRANSFER_TYPE);
        transferSeries.getData().add(new XYChart.Data<>("Transfer", transferTotal));

        seriesList.add(transferSeries);

        barChart.setData(seriesList);
        barChart.setStyle("-fx-text-fill: #f4f5fc");
        barChart.setPrefSize(900, 600);
        barChart.setStyle("-fx-background-color: #8ebbff");
        barChart.setLegendVisible(false);

        // add bar chart to current pane
        // pane.getChildren().add(barChart);
        // vb.setVisible(false);
        hb.getChildren().add(barChart);

    }

    private void getHistory() {
        historyBtn.setFont(new Font(25));
        String label = "";
        for (Ledger ledger : DataHandle.ledgers) {
            label += ledger.getDate() + " " + ledger.getType() + " " + ledger.getAmount()
                    + "\n";

        }

        historyBtn.setText(label);
        historyBtn.setTextAlignment(TextAlignment.LEFT);

    }

    private void getTransfer() {
        transferBtn.setFont(new Font(25));
        String label = "";
        for (Ledger ledger : DataHandle.ledgers) {
            if (ledger.getType().equals("transfer")) {
                label += ledger.getDate() + " To " + ledger.getDestinationName() + " " + ledger.getAmount()
                        + "\n";
            }

        }

        transferBtn.setText(label);
        transferBtn.setTextAlignment(TextAlignment.LEFT);

    }

    private void getWithdraw() {
        withdrawBtn.setFont(new Font(25));
        String label = "";
        for (Ledger ledger : DataHandle.ledgers) {
            if (ledger.getType().equals("Withdraw")) {
                label += ledger.getDate() + " " + ledger.getAmount()
                        + "\n";
            }

        }

        withdrawBtn.setText(label);
        withdrawBtn.setTextAlignment(TextAlignment.LEFT);

    }
}
