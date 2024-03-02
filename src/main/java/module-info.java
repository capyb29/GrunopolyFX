module sia.enjoyers.grunopolyfx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens sia.enjoyers.grunopolyfx to javafx.fxml;
    exports sia.enjoyers.grunopolyfx;
}