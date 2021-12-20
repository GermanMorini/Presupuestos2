package com.presupuestos.presupuestos2;

import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

public class MainApplicationController {
    @FXML
    private MenuItem elegirDestinoMItem;

    @FXML
    protected void elegirDestinoAP() { elegirDestinoMItem.setText("FUNCIONÓ!!"); }
}