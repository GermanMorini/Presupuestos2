package com.presupuestos2.model.pdffile;

import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPCell;

public class MainTable extends PdfPTable {

    final private PdfPCell content;

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

        content.addElement(TextFactory.createText("Presupuesto", ALIGN_CENTER, (int) TextFactory.getFont().getSize() + 2));
        content.addElement(TextFactory.createInputText("Cliente", cliente));
        content.addElement(TextFactory.createInputText("Fecha", fecha));
        content.addElement(TextFactory.createText("Trabajos:", ALIGN_LEFT));
        addList(trabajos);

        if (!(detalles.length == 0)) {
            content.addElement(TextFactory.createText("Detalles:", ALIGN_LEFT));
            addList(detalles);
        }

        content.addElement(TextFactory.createInputText("TOTAL PRESUPUESTO", "$" + total, TextFactory.getSpacing() + 10));

        addCell(content);
    }

    private void addList(String[] list) {
        for (String elem : list) {
            content.addElement(TextFactory.createItem(elem, '•'));
        }
    }
}
