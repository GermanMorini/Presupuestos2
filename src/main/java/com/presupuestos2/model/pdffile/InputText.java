package com.presupuestos2.model.pdffile;

import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;

public class InputText extends Paragraph {

    public InputText(String title, String text, String font, int size, int spacing) {
        setSpacingBefore(spacing);
        setSpacingAfter(spacing);
        setIndentationLeft(10);
        setFont(FontFactory.getFont(font, size + 2, Font.UNDERLINE));
        add(title + ": ");
        setFont(FontFactory.getFont(font, size, Font.NORMAL));
        add(text);
    }
}
