module com.presupuestos2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires itextpdf;
    requires java.desktop;

    opens com.presupuestos2 to javafx.fxml;
    exports com.presupuestos2;
    opens com.presupuestos2.controller to javafx.fxml;
    exports com.presupuestos2.controller;
}