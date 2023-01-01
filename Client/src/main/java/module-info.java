module tin.bank.client {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires MaterialFX;
    requires java.sql;
    requires com.microsoft.sqlserver.jdbc;

    opens tin.bank.client to javafx.fxml;
    exports tin.bank.client;
    opens tin.bank.client.control to javafx.fxml;
    exports tin.bank.client.control;
    opens tin.bank.client.control.Pane to javafx.fxml;
    exports tin.bank.client.control.Pane;
}