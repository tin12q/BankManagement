package tin.bank.server;

import java.io.File;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import tin.bank.server.model.Account;
import tin.bank.server.model.DataHandle;

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
    private void initialize() {
        DataHandle.getAllCustomers();
        // viewList();
        exportBtn.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save file");
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XLSs files (*.xlss)", "*.xls");
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
        ObservableList<Account> accountsList = FXCollections.observableArrayList(DataHandle.accounts);
        usrTable.setItems(accountsList);
    }

    private void viewList() {
        for (Account a : DataHandle.accounts) {
            System.out.println(a.toString());
        }
    }
    // Create table to view all accounts

}
