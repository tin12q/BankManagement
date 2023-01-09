package tin.bank.client.control.Pane;

import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import tin.bank.client.model.DataHandle;

import tin.bank.client.model.Ledger;

public class History {
    @FXML
    private MFXTableView<Ledger> table;
    @FXML
    private TableColumn date;


    @FXML
    private void initialize(){
        DataHandle.getLedger();
        table.setItems((ObservableList<Ledger>) DataHandle.ledgers);
    }
}
