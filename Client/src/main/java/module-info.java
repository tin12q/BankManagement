module tin.bank.client {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires MaterialFX;

    opens tin.bank.client to javafx.fxml;
    exports tin.bank.client;
    opens tin.bank.client.control to javafx.fxml;
    exports tin.bank.client.control;
    opens tin.bank.client.control.pane to javafx.fxml;
    exports tin.bank.client.control.pane;
}