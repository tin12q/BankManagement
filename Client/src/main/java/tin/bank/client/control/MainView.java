package tin.bank.client.control;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainView {

    @FXML
    private AnchorPane ac1;
    @FXML
    private MFXButton dBBtn;
    @FXML
    private void actionHandle()
    {
        dBBtn.setOnAction(event -> loadPage("DashBoard",event));
    }
    private void loadPage(String name, ActionEvent event) {

        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            if(stage.getTitle().equals(name))
            {
                return;
            }
            else {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/httt/server/Server/SerWinPane/" +name + ".fxml"));

                AnchorPane newPane = loader.load();

                // Set the loaded FXML file as the content of our main right-side pane
                ac1.getChildren().setAll(newPane);

                stage.setTitle(name);
                // Reset the anchors
                AnchorPane.setBottomAnchor(newPane, 0.0);
                AnchorPane.setLeftAnchor(newPane, 0.0);
                AnchorPane.setRightAnchor(newPane, 0.0);
                AnchorPane.setTopAnchor(newPane, 0.0);
                System.out.println("Loaded " + name + " page");
                System.out.println(stage.getTitle());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
