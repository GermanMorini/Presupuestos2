package com.presupuestos2.model.pdffile;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Image;
import com.presupuestos2.model.other.Utilities;
import javafx.scene.control.Alert;

public class Img {

    private Image img;

    public Img(String path) {
        try {
            img = Image.getInstance(path);
            img.setSpacingAfter(30);
            img.scaleToFit(447, 315);
            img.setAlignment(Chunk.ALIGN_CENTER);
        } catch (Exception e) {
            e.printStackTrace();
            Utilities.showPopupMessage(Alert.AlertType.ERROR, "Ha ocurrido un error. Si el mismo persiste contactar al servicio de ayuda", "Error");
        }
    }

    public Image getImg() {return img;}
}
