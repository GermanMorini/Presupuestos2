package com.presupuestos2.controller;

import com.presupuestos2.MainApplication;
import com.presupuestos2.model.Budget;
import com.presupuestos2.model.other.Utilities;
import com.presupuestos2.model.pdffile.Img;
import com.presupuestos2.model.pdffile.MainTable;
import com.presupuestos2.model.pdffile.PDFDocument;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MainApplicationController {

    @FXML
    private TextField cliente;
    @FXML
    private DatePicker fecha;
    @FXML
    private TextField total;
    @FXML
    private VBox trabajosTable;
    @FXML
    private VBox detallesTable;
    @FXML
    protected void elegirDestinoAP() {
        DirectoryChooser dc = new DirectoryChooser();
        dc.setTitle("Selección de destino");
        File f;

        if ((f = dc.showDialog(null)) != null) {
            MainApplication.setSavePath(f.getPath());
            Utilities.showPopupMessage(Alert.AlertType.INFORMATION, "El nuevo destino es " + f.getPath(), "Información");
        } else {
            Utilities.showPopupMessage(Alert.AlertType.WARNING, "No se ha seleccionado ninguna ruta.\nLa ruta de guardado no ha cambiado\n" + MainApplication.getSavePath(), "Advertencia");
        }
    }

    @FXML
    protected void guardarAP() {
        if (validateEntries()) {
            String[] trabajos = getTableContent(trabajosTable);
            String[] detalles = getTableContent(detallesTable);
            String savePath = MainApplication.getSavePath() + File.separator + cliente.getText().strip() + ".pspto";

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(savePath)))
            {
                oos.writeObject(new Budget(
                        cliente.getText().strip(),
                        fecha.getValue().format(DateTimeFormatter.ofPattern("d/M/y")),
                        total.getText().strip(),
                        trabajos,
                        detalles));
                Utilities.showPopupMessage(Alert.AlertType.INFORMATION, "Datos guardados con éxito en " + savePath, "Información");
            } catch (Exception e) {
                e.printStackTrace();
                Utilities.showPopupMessage(Alert.AlertType.ERROR, "Ha ocurrido un error. Si el mismo persiste contactar al servicio de ayuda", "Error");
            }
        } else {
            Utilities.showPopupMessage(Alert.AlertType.WARNING, "Faltan rellenar algunas entradas", "Advertencia");
        }
    }

    @FXML
    protected void guardarGenerarAP() {
        guardarAP();

        if (validateEntries()) {
            String[] trabajos = getTableContent(trabajosTable);
            String[] detalles = getTableContent(detallesTable);
            String savePath = MainApplication.getSavePath() + File.separator + cliente.getText().strip() + ".pdf";

            try {
                new PDFDocument(
                        savePath,
                        new Img(MainApplication.class.getResource("MarceloDiaz.png").getPath()).getImg(),
                        new MainTable(
                                cliente.getText().strip(),
                                fecha.getValue().format(DateTimeFormatter.ofPattern("d/M/y")),
                                total.getText().strip(),
                                trabajos,
                                detalles)
                        );
                Utilities.showPopupMessage(Alert.AlertType.INFORMATION, "PDF generado con éxito en " + savePath, "Información");
            } catch (Exception e) {
                e.printStackTrace();
                Utilities.showPopupMessage(Alert.AlertType.ERROR, "Ha ocurrido un error. Si el mismo persiste contactar al servicio de ayuda", "Error");
            }
        } else {
            Utilities.showPopupMessage(Alert.AlertType.WARNING, "Faltan rellenar algunas entradas", "Advertencia");
        }
    }

    private boolean validateEntries() {
        return cliente.getText() != null && fecha.getValue() != null &&
                total.getText() != null && getTableContent(trabajosTable).length != 0;
    }

    private String[] getTableContent(Pane table) {
        ArrayList<String> list = new ArrayList<>();

        for (int i = 0; i < 15; i++) {
            TextField tf = (TextField) table.getChildren().get(i);
            if (tf.getText() != null && !tf.getText().isBlank()) {
                list.add(tf.getText().strip());
            }
        }

        return list.toArray(new String[] {});
    }

    @FXML
    protected void colorAP() {
        System.out.println("TODO: escribir función para el menu item color");
    }
}