package tin.bank.server;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.util.converter.DoubleStringConverter;
import tin.bank.server.model.Account;
import tin.bank.server.model.DataHandle;
import tin.bank.server.model.Ledger;

import java.io.File;

public class mainController {
    // TODO: Feb 01 23
    // 1. Create table to view all accounts
    // 2. Create table to view all transactions
    // 3. Create function to add new account
    // 4. Create function to alter account
    // 5. Create function to delete account
    @FXML
    private MFXButton exportBtn;

    @FXML
    private TableView<Account> usrTable;
    @FXML
    private TableColumn<Account, Integer> idCol;
    @FXML
    private TableColumn<Account, String> firstNameCol;
    @FXML
    private TableColumn<Account, String> lastNameCol;
    @FXML
    private TableColumn<Account, Double> balanceCol;
    @FXML
    private TableColumn<Account, String> emailCol;
    @FXML
    private TableColumn<Account, String> phoneCol;
    @FXML
    private TableColumn<Account, String> addressCol;
    @FXML
    private TableColumn<Account, String> DOBCol;
    @FXML
    private TableColumn<Account, String> cityCol;
    @FXML
    private TableColumn<Account, String> stateCol;
    @FXML
    private TableColumn<Account, String> zipCodeCol;
    @FXML
    private TableColumn<Account, String> usernameCol;
    @FXML
    private TableColumn<Account, String> passwordCol;
    @FXML
    private AnchorPane ac2;

    @FXML
    private TextField findTf;
    @FXML
    private Button findBtn;
    @FXML
    private MFXButton exportBtn1;
    @FXML
    private TextField nameTf;
    @FXML
    private TableColumn<Account, Void> updateCol;
    private Button updateBtn;

    @FXML
    private void initialize() {

        DataHandle.getAllCustomers();
        // viewList();
        exportBtn.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save file");
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XLS files (*.xls)", "*.xls");
            fileChooser.getExtensionFilters().add(extFilter);
            // Show the dialog and get the selected file
            File file = fileChooser.showSaveDialog(exportBtn.getScene().getWindow());

            if (file != null) {
                System.out.println("Selected file: " + file.getAbsolutePath());
                DataHandle.exportAccountsToExcel(DataHandle.accounts, file.getAbsolutePath());
                System.out.println("Done");
                // Call your export function with the selected file path
                // exportAccountsToExcel(accounts, file.getAbsolutePath());
            }
        });
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("fname"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lname"));
        balanceCol.setCellValueFactory(new PropertyValueFactory<>("balance"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        DOBCol.setCellValueFactory(new PropertyValueFactory<>("DOB"));
        cityCol.setCellValueFactory(new PropertyValueFactory<>("city"));
        stateCol.setCellValueFactory(new PropertyValueFactory<>("state"));
        zipCodeCol.setCellValueFactory(new PropertyValueFactory<>("zipCode"));
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        passwordCol.setCellValueFactory(new PropertyValueFactory<>("password"));

        // usrTable.getColumns().add(updateCol);

        ObservableList<Account> accountsList = FXCollections.observableArrayList(DataHandle.accounts);
        usrTable.setEditable(true);
        usrTable.setItems(accountsList);
        // set editable
        firstNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        firstNameCol.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setFname(e.getNewValue());
        });
        lastNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        lastNameCol.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setLname(e.getNewValue());
        });
        balanceCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        balanceCol.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setBalance(e.getNewValue());
        });
        emailCol.setCellFactory(TextFieldTableCell.forTableColumn());
        emailCol.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setEmail(e.getNewValue());
        });
        phoneCol.setCellFactory(TextFieldTableCell.forTableColumn());
        phoneCol.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setPhone(e.getNewValue());
        });
        addressCol.setCellFactory(TextFieldTableCell.forTableColumn());
        addressCol.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setAddress(e.getNewValue());
        });
        // DOBCol.setCellFactory(TextFieldTableCell.forTableColumn());
        // DOBCol.setOnEditCommit(e -> {
        // e.getTableView().getItems().get(e.getTablePosition().getRow()).setStringDob(e.getNewValue());
        // });
        cityCol.setCellFactory(TextFieldTableCell.forTableColumn());
        cityCol.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setCity(e.getNewValue());
        });
        stateCol.setCellFactory(TextFieldTableCell.forTableColumn());
        stateCol.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setState(e.getNewValue());
        });
        zipCodeCol.setCellFactory(TextFieldTableCell.forTableColumn());
        zipCodeCol.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setZipCode(e.getNewValue());
        });
        usernameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        usernameCol.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setUsername(e.getNewValue());
        });
        passwordCol.setCellFactory(TextFieldTableCell.forTableColumn());
        passwordCol.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setPassword(e.getNewValue());
        });
        // check if a row is being edited
        usrTable.editableProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != oldVal) {
                // A row is being edited, show the update button
                updateBtn.setVisible(true);
            } else {
                // No rows are being edited, hide the update button
                updateBtn.setVisible(false);
            }
        });
        findBtn.setOnAction(e -> {
            String keyword = findTf.getText();
            if (keyword != null) {
                ObservableList<Account> accountsList2 = FXCollections
                        .observableArrayList(DataHandle.searchCustomerByName(keyword));
                usrTable.setItems(accountsList2);
            }
        });
        // check changes in findTf
        findTf.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                ObservableList<Account> accountsList2 = FXCollections
                        .observableArrayList(DataHandle.searchCustomerByName(newValue));
                usrTable.setItems(accountsList2);
            }
        });
        // on edited row add a update button then update the row in the database
        // Create TableView and columns
        DataHandle.getLedger();
        TableView<Ledger> table = new TableView<>();
        TableColumn<Ledger, Integer> transactionIdCol = new TableColumn<>("Transaction ID");
        TableColumn<Ledger, String> transactionTypeCol = new TableColumn<>("Transaction Type");
        TableColumn<Ledger, Double> amountCol = new TableColumn<>("Amount");
        TableColumn<Ledger, String> dateCol = new TableColumn<>("Date");
        TableColumn<Ledger, String> descriptionCol = new TableColumn<>("Description");
        TableColumn<Ledger, Integer> destCustomerIdCol = new TableColumn<>("Destination Customer ID");
        TableColumn<Ledger, String> destNameCol = new TableColumn<>("Destination Name");

        // Set cell value factories for each column
        transactionIdCol.setCellValueFactory(new PropertyValueFactory<>("transactionId"));
        transactionTypeCol.setCellValueFactory(new PropertyValueFactory<>("transactionType"));
        amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("transactionDate"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        destCustomerIdCol.setCellValueFactory(new PropertyValueFactory<>("destinationCustomerId"));
        destNameCol.setCellValueFactory(new PropertyValueFactory<>("destinationName"));

        // Add columns to TableView and set items to ledgerList
        table.getColumns().addAll(transactionIdCol, transactionTypeCol, amountCol, dateCol, descriptionCol,
                destCustomerIdCol, destNameCol);
        table.setItems(FXCollections.observableArrayList(DataHandle.ledgers));
        ac2.getChildren().add(table);

        table.setEditable(true);
        // set table to 1483 x 869
        table.setPrefSize(1483, 869);
        table.toBack();
        AnchorPane.setBottomAnchor(table, (double) 0);
        AnchorPane.setTopAnchor(table, 50.0);
        nameTf.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                ObservableList<Ledger> ledgers2 = FXCollections
                        .observableArrayList(DataHandle.getLedgerName(newValue));
                table.setItems(ledgers2);
            }
        });
        exportBtn1.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save file");
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XLS files (*.xls)", "*.xls");
            fileChooser.getExtensionFilters().add(extFilter);
            // Show the dialog and get the selected file
            File file = fileChooser.showSaveDialog(exportBtn.getScene().getWindow());

            if (file != null) {
                System.out.println("Selected file: " + file.getAbsolutePath());
                DataHandle.exportLedgerToExcel(DataHandle.ledgers, file.getAbsolutePath());
                System.out.println("Done");
                // Call your export function with the selected file path
                // exportAccountsToExcel(accounts, file.getAbsolutePath());
            }
        });
    }

    // Create table to view all accounts

}
