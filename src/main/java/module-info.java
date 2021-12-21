module com.presupuestos2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires itextpdf;

    opens com.presupuestos2 to javafx.fxml;
    exports com.presupuestos2;
}