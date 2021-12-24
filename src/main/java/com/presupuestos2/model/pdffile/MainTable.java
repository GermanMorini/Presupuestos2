package com.presupuestos2.model.pdffile;

import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPCell;

public class MainTable extends PdfPTable {

    final private PdfPCell content;
    final private Font FONT = FontFactory.getFont(Font.FontFamily.HELVETICA.name(), 14, Font.UNDERLINE);
    final private int SPACING = 8;

    public MainTable(
            String cliente,
            String fecha,
            String total,
            String[] trabajos,
            String[] detalles)
    {
        super(1);
        setSpacingBefore(10);
        setSpacingAfter(10);
        setWidthPercentage(85);

        content = new PdfPCell();

        content.addElement(new Text("Presupuesto", FONT.getFamilyname(), (int) FONT.getSize() + 2, Font.UNDERLINE, SPACING, ALIGN_CENTER));
        content.addElement(new InputText("Cliente", cliente, FONT, SPACING));
        content.addElement(new InputText("Fecha", fecha, FONT, SPACING));
        content.addElement(new Text("Trabajos:", FONT, SPACING, ALIGN_LEFT));
        addList(trabajos);
        if (!(detalles.length == 0)) {
            content.addElement(new Text("Detalles:", FONT, SPACING, ALIGN_LEFT));
            addList(detalles);
        }
        content.addElement(new InputText("TOTAL PRESUPUESTO", "$" + total, FONT, SPACING + 10));

        addCell(content);
    }

    private void addList(String[] list) {
        for (String elem : list) {
            content.addElement(new Item(elem, FONT.getFamilyname(), (int) FONT.getSize(), SPACING, '•'));
        }
    }
}
