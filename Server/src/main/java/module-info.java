module tin.bank.server {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires MaterialFX;
    requires java.sql;
    requires com.microsoft.sqlserver.jdbc;

    opens tin.bank.server to javafx.fxml;
    exports tin.bank.server;

}