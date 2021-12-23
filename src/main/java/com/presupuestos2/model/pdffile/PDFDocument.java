package com.presupuestos2.model.pdffile;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;


public class PDFDocument extends Document {

    public PDFDocument(String savePath) throws FileNotFoundException, DocumentException {
        PdfWriter.getInstance(this, new FileOutputStream(savePath));
    }

    public void createBudget(Image img, MainTable table) throws DocumentException {
        open();
        add(img);
        add(table);
        close();
    }
}
