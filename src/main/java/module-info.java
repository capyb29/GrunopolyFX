module sia.enjoyers.grunopolyfx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.desktop;

    opens sia.enjoyers.grunopolyfx to javafx.fxml;
    exports sia.enjoyers.grunopolyfx;
}