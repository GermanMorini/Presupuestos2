module com.presupuestos.presupuestos2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;

    opens com.presupuestos.presupuestos2 to javafx.fxml;
    exports com.presupuestos.presupuestos2;
}