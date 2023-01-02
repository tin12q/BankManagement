package tin.bank.client.control.Pane;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import tin.bank.client.model.DataGet;

public class DashBoard {
    @FXML
    private MFXButton balanceBtn;

    @FXML
    private void initialize() {
        balanceBtn.setText(DataGet.mainAccount.getBalance().toString());
    }
}
