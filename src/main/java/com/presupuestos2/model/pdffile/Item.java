package com.presupuestos2.model.pdffile;

import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;

public class Item extends Paragraph {

    public Item(String text, String font, int size, int spacing, char symbol) {
        setSpacingBefore(spacing / 4);
        setSpacingAfter(spacing / 4);
        setIndentationLeft(40);
        setFont(FontFactory.getFont(font, size + 2, Font.NORMAL));
        add(symbol + " ");
        setFont(FontFactory.getFont(font, size, Font.NORMAL));
        add(text);
    }
}
