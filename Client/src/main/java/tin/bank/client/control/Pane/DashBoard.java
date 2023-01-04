package tin.bank.client.control.Pane;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import tin.bank.client.model.DataHandle;

public class DashBoard {
    @FXML
    private MFXButton balanceBtn;

    @FXML
    private void initialize() {
        balanceBtn.setText(DataHandle.mainAccount.getBalance().toString());
    }
}
