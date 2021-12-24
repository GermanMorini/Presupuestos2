package com.presupuestos2.model.pdffile;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Image;

import java.io.IOException;

public class Img {

    private Image img;

    public Img(String path) throws BadElementException, IOException {
        img = Image.getInstance(path);
        img.setSpacingAfter(30);
        img.scaleToFit(447, 315);
        img.setAlignment(Chunk.ALIGN_CENTER);
    }

    public Image getImg() {return img;}
}
