package com.presupuestos2.model.pdffile;

import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;

public class Text extends Paragraph {

    final protected int INDENTATION = 10;

    public Text(String text, Font font, int spacing, int pos) {
        setSpacingBefore(spacing);
        setSpacingAfter(spacing);
        setIndentationLeft(INDENTATION);
        setAlignment(pos);
        setFont(font);
        add(text);
    }

    public Text(String text, String font, int size, int style, int spacing, int pos) {
        setSpacingBefore(spacing);
        setSpacingAfter(spacing);
        setIndentationLeft(INDENTATION);
        setAlignment(pos);
        setFont(FontFactory.getFont(font, size, style));
        add(text);
    }
}
