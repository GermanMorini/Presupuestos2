package com.presupuestos2.model.other;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.scene.control.Alert;

import java.io.*;

public class Stream {

    static public void writeObject(Object obj, String savePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(savePath)))
        {
            oos.writeObject(obj);
            Utilities.showPopupMessage(Alert.AlertType.INFORMATION, "Datos guardados con éxito", "Información");
        } catch (Exception e) {
            e.printStackTrace();
            Utilities.showPopupMessage(Alert.AlertType.ERROR, "Ha ocurrido un error. Si el mismo persiste contactar al servicio de ayuda", "Error");
        }

    }

    static public Object readObject(String savePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(savePath)))
        {
            return ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            Utilities.showPopupMessage(Alert.AlertType.ERROR, "Ha ocurrido un error. Si el mismo persiste contactar al servicio de ayuda", "Error");
            return null;
        }
    }
}
