package com.presupuestos2.model.other;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.File;

public class Dialog {

    static final private java.awt.Dimension DIMENSION = new java.awt.Dimension(600, 420);

    static public File showDirectoryChooserDialog(String title) {
        DirectoryChooser dc = new DirectoryChooser();
        dc.setTitle(title);

        return dc.showDialog(null);
    }

    static public File showFileChooserDialog(String title, File initialDir) {
        FileChooser fc = new FileChooser();
        fc.setTitle(title);
        fc.setInitialDirectory(initialDir);

        return fc.showOpenDialog(null);
    }

    static public void showMessageDialog(Alert.AlertType msgType, String msg, String title) {
        java.awt.Toolkit.getDefaultToolkit().beep();
        Alert al = new Alert(msgType, msg);
        al.setTitle(title);
        al.setWidth(DIMENSION.width);
        al.setHeight(DIMENSION.height);
        al.setResizable(true);
        al.show();
    }

    static public void showConfirmDialog(String msg, String title, java.util.function.Consumer<? super ButtonType> action) {
        java.awt.Toolkit.getDefaultToolkit().beep();
        Alert al = new Alert(Alert.AlertType.CONFIRMATION, msg);
        al.setTitle(title);
        al.setWidth(DIMENSION.width);
        al.setHeight(DIMENSION.height);
        al.setResizable(true);
        al.showAndWait().ifPresent(action);
    }
}
