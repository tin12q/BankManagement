module tin.bank.server {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires MaterialFX;
    requires java.sql;
    requires com.microsoft.sqlserver.jdbc;

    opens tin.bank.server to javafx.fxml;

    exports tin.bank.server;

    opens tin.bank.server.model to javafx.base;

}