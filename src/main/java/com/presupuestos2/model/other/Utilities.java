package com.presupuestos2.model.other;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.File;

public class Utilities {

    static public File showDirectoryChooserDialog(String title) {
        DirectoryChooser dc = new DirectoryChooser();
        dc.setTitle(title);
        File f;

        if ((f = dc.showDialog(null)) != null) {
            return f;
        } else {
            return null;
        }
    }

    static public File showFileChooserDialog(String title, File initialDir) {
        FileChooser fc = new FileChooser();
        fc.setTitle(title);
        fc.setInitialDirectory(initialDir);
        File f;

        if ((f = fc.showOpenDialog(null)) != null) {
            return f;
        } else {
            return null;
        }
    }

    static public void  showPopupMessage(Alert.AlertType msgType, String msg, String title) {
        java.awt.Toolkit.getDefaultToolkit().beep();
        Alert al = new Alert(msgType, msg);
        al.setTitle(title);
        al.setWidth(600);
        al.setHeight(420);
        al.setResizable(true);
        al.show();
    }

    static public void showConfirmDialog(String msg, String title, java.util.function.Consumer<? super ButtonType> action) {
        java.awt.Toolkit.getDefaultToolkit().beep();
        Alert al = new Alert(Alert.AlertType.CONFIRMATION, msg);
        al.setTitle(title);
        al.setWidth(600);
        al.setHeight(420);
        al.setResizable(true);
        al.showAndWait().ifPresent(action);
    }
}
