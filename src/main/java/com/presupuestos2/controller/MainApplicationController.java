package com.presupuestos2.controller;

import com.presupuestos2.MainApplication;
import com.presupuestos2.model.Budget;
import javafx.fxml.FXML;
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
            System.out.println("TODO: Dialog con la ruta seleccionada");
        } else {
            System.out.println("TODO: Dialog con la leyenda 'No se ha seleccionado ningún destino'");
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
                oos.writeObject(new Budget(cliente.getText().strip(), fecha.getValue().format(DateTimeFormatter.ofPattern("d/M/y")), total.getText().strip(), trabajos, detalles));
                System.out.println("TODO: Dialog con la leyenda 'PDF guardado con éxito en <savePath>' " + MainApplication.getSavePath());
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("TODO: Dialog con la leyenda 'Ha ocurrido un error. Si el mismo persiste contactar al servicio de ayuda'");
            }
        } else {
            System.out.println("TODO: Dialog con la leyenda 'Faltan rellenar algunas entradas'");
        }
    }

    private boolean validateEntries() {
        return cliente.getText() != null && fecha.getValue() != null &&
                total.getText() != null && getTableContent(trabajosTable).length != 0;
    }

    private String[] getTableContent(Pane table) {
        ArrayList<String> list = new ArrayList<>();

        for (int i = 0; i < 16; i++) {
            TextField tf = (TextField) table.getChildren().get(i);
            if (tf.getText() != null && !tf.getText().isBlank()) {
                list.add(tf.getText().strip());
            }
        }

        return list.toArray(new String[] {});
    }

    @FXML
    protected void guardarGenerarAP() {
        System.out.println("TODO: escribir función para el menu item guardarYGenerar");
    }

    @FXML
    protected void colorAP() {
        System.out.println("TODO: escribir función para el menu item color");
    }
}