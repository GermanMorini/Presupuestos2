package com.presupuestos2.controller;

import com.itextpdf.text.DocumentException;
import com.presupuestos2.MainApplication;
import com.presupuestos2.model.Budget;
import com.presupuestos2.model.other.Stream;
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
import java.io.FileNotFoundException;
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
        saveBudgetInfoIfAllFieldsCompleted();
    }

    private void saveBudgetInfoIfAllFieldsCompleted() {
        if (allFieldsCompleted()) {
            String savePath = MainApplication.getSavePath() + File.separator + cliente.getText().strip() + ".pspto";
            Budget presupuesto = new Budget(
                    cliente.getText().strip(),
                    fecha.getValue().format(DateTimeFormatter.ofPattern("d/M/y")),
                    total.getText().strip(),
                    getTableContent(trabajosTable),
                    getTableContent(detallesTable)
            );
            Stream.writeObject(presupuesto, savePath);
        } else {
            Utilities.showPopupMessage(Alert.AlertType.WARNING, "Faltan rellenar algunas entradas", "Advertencia");
        }
    }

    @FXML
    protected void cargarAP() {
        // TODO: añadir funcionalidad
        //Budget presupuesto = Stream.readObject();
    }

    @FXML
    protected void guardarGenerarAP() {
        guardarAP();
        String savePath = MainApplication.getSavePath() + File.separator + cliente.getText().strip() + ".pdf";

        try {
            createFileIfAllFieldsCompleted(savePath);
        } catch (Exception e) {
            e.printStackTrace();
            Utilities.showPopupMessage(Alert.AlertType.ERROR, "Ha ocurrido un error durante la generación del archivo PDF\nSi el mismo persiste contactar al servicio de ayuda", "Error");
        }
    }

    private void createFileIfAllFieldsCompleted(String savePath) throws DocumentException, FileNotFoundException {
        if (allFieldsCompleted()) {
            PDFDocument pdf = new PDFDocument(savePath);
            pdf.createBudget(
                    new Img(MainApplication.class.getResource("MarceloDiaz.png").getPath()).getImg(),
                    new MainTable(
                            cliente.getText().strip(),
                            fecha.getValue().format(DateTimeFormatter.ofPattern("d/M/y")),
                            total.getText().strip(),
                            getTableContent(trabajosTable),
                            getTableContent(detallesTable))
            );
            Utilities.showPopupMessage(Alert.AlertType.INFORMATION, "PDF generado con éxito en " + savePath, "Información");
        } else {
            Utilities.showPopupMessage(Alert.AlertType.WARNING, "Faltan rellenar algunas entradas", "Advertencia");
        }
    }

    private boolean allFieldsCompleted() {
        return cliente.getText() != null && !cliente.getText().isBlank() &&
               total.getText() != null && !total.getText().isBlank() &&
               getTableContent(trabajosTable).length != 0 &&
               fecha.getValue() != null;
    }

    private String[] getTableContent(Pane table) {
        ArrayList<String> list = new ArrayList<>();

        for (int i = 0; i < table.getChildren().size(); i++) {
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