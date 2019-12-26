module org.openjfx.FxmlAppfx {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.openjfx.FxmlAppfx to javafx.fxml;
    exports org.openjfx.FxmlAppfx;
}