package com.presupuestos2.model.pdffile;

import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;

public class Text extends Paragraph {

    public Text(String text, String font, int size, int style, int spacing, int pos) {
        setSpacingBefore(spacing);
        setSpacingAfter(spacing);
        setIndentationLeft(10);
        setAlignment(pos);
        setFont(FontFactory.getFont(font, size, style));
        add(text);
    }
}
