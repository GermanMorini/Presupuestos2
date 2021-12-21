package com.presupuestos2.model.pdffile;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;

public class PDFDocument {

    public PDFDocument(String savePath, Image img, MainTable table) throws IOException, DocumentException {
        Document doc = new Document();
        PdfWriter.getInstance(doc, new FileOutputStream(savePath));

        doc.open();

        doc.add(img);
        doc.add(table);

        doc.close();
    }
}
