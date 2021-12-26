package com.presupuestos2.controller;

import com.presupuestos2.MainApplication;
import com.presupuestos2.model.Budget;
import com.presupuestos2.model.other.Stream;
import com.presupuestos2.model.other.Dialog;
import com.presupuestos2.model.pdffile.Img;
import com.presupuestos2.model.pdffile.MainTable;
import com.presupuestos2.model.pdffile.PDFDocument;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;
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
        File selectedDirectory = Dialog.showDirectoryChooserDialog("Guardar en..");

        if (selectedDirectory != null) {
            MainApplication.setSavePath(selectedDirectory.getPath());
            Dialog.showMessageDialog(Alert.AlertType.INFORMATION, "El nuevo destino es " + selectedDirectory.getPath(), "Información");
        } else {
            Dialog.showMessageDialog(Alert.AlertType.WARNING, "No se ha seleccionado ninguna ruta.\nLa ruta de guardado no ha cambiado\n" + MainApplication.getSavePath(), "Advertencia");
        }
    }

    @FXML
    protected void guardarAP() {
        if (allFieldsCompleted()) {
            //TODO: añadir el directorio guardados al proyecto
            String savePath = "/home/german/" + cliente.getText().strip() + ".pspto";

            generateBudget(savePath);
        } else {
            Dialog.showMessageDialog(Alert.AlertType.WARNING, "Faltan rellenar algunas entradas", "Advertencia");
        }
    }

    private void generateBudget(String savePath) {
        try {
            Budget presupuesto = new Budget(
                    cliente.getText().strip(),
                    fecha.getValue(),
                    total.getText().strip(),
                    getTableContent(trabajosTable),
                    getTableContent(detallesTable)
            );
            Stream.writeObject(presupuesto, savePath);
            Dialog.showMessageDialog(Alert.AlertType.INFORMATION, "Datos guardados con éxito", "Información");
        } catch (IOException e) {
            e.printStackTrace();
            Dialog.showMessageDialog(Alert.AlertType.ERROR, "Ha ocurrido un error. Si el mismo persiste contactar al servicio de ayuda", "Error");
        }
    }

    @FXML
    protected void cargarAP() {
        File selectedFile = Dialog.showFileChooserDialog("Cargar..", new File("/home/german"));
        loadBudget(selectedFile);
    }

    private void loadBudget(File file) {
        try {
            Budget bud = (Budget) Stream.readObject(file.getPath());
            fillEntries(bud);
        } catch (Exception e) {
            e.printStackTrace();
            Dialog.showMessageDialog(Alert.AlertType.ERROR, "Ha ocurrido un error. Si el mismo persiste contactar al servicio de ayuda", "Error");
        }
    }

    private void fillEntries(Budget budget) {
        cliente.setText(budget.getCliente());
        fecha.setValue(budget.getFecha());
        total.setText(budget.getTotal());
        setTableContent(trabajosTable, budget.getTrabajos());
        setTableContent(detallesTable, budget.getDetalles());
    }

    @FXML
    protected void guardarGenerarAP() {
        guardarAP();

        if (allFieldsCompleted()) {
            String filePath = MainApplication.getSavePath() + File.separator + cliente.getText().strip() + ".pdf";
            generatePDFDocument(filePath);
        } else {
            Dialog.showMessageDialog(Alert.AlertType.WARNING, "Faltan rellenar algunas entradas", "Advertencia");
        }
    }

    private void generatePDFDocument(String filePath) {
        try {
            PDFDocument pdf = new PDFDocument(filePath);
            pdf.createBudget(
                    new Img(MainApplication.class.getResource("MarceloDiaz.png").getPath()).getImg(),
                    new MainTable(
                            cliente.getText().strip(),
                            fecha.getValue().format(DateTimeFormatter.ofPattern("d/M/y")),
                            total.getText().strip(),
                            getTableContent(trabajosTable),
                            getTableContent(detallesTable))
            );
            Dialog.showMessageDialog(Alert.AlertType.INFORMATION, "PDF generado con éxito en " + filePath, "Información");
        } catch (Exception e) {
            e.printStackTrace();
            Dialog.showMessageDialog(Alert.AlertType.ERROR, "Ha ocurrido un error durante la generación del archivo PDF\nSi el mismo persiste contactar al servicio de ayuda", "Error");
        }
    }

    private boolean allFieldsCompleted() {
        return cliente.getText() != null && !cliente.getText().isBlank() &&
               total.getText() != null && !total.getText().isBlank() &&
               getTableContent(trabajosTable).length != 0 &&
               fecha.getValue() != null;
    }

    private String[] getTableContent(Pane table) {
        ArrayList<String> content = new ArrayList<>();

        table.getChildren().forEach(field -> {
            TextField tf = (TextField) field;
            if (tf.getText() != null && !tf.getText().isBlank()) {
                content.add(tf.getText().strip());
            }
        });

        return content.toArray(new String[] {});
    }

    private void setTableContent(Pane table, String[] content) {
        for (int i = 0; i < content.length; i++) {
            ((TextField) table.getChildren().get(i)).setText(content[i]);
        }
    }

    @FXML
    protected void borrarTodoAP() {
        Dialog.showConfirmDialog("Deseas borrar todas las entradas?", "Advertencia", response -> {
            if (response == javafx.scene.control.ButtonType.OK) {
                cliente.setText("");
                total.setText("");
                clearTable(trabajosTable);
                clearTable(detallesTable);
            }
        });
    }

    private void clearTable(Pane table) {
        table.getChildren().forEach(field -> {
            ((TextField) field).setText("");
        });
    }

    @FXML
    protected void colorAP() {
        //TODO: escribir función para el menu item color
        System.out.println("TODO: escribir función para el menu item color");
    }
}
