package com.presupuestos2.model.pdffile;

import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;

public class InputText extends Text {

    public InputText(String title, String text, Font font, int spacing) {
        super(title, font, spacing, Text.ALIGN_LEFT);
        setFont(FontFactory.getFont(font.getFamilyname(), font.getSize(), Font.NORMAL));
        add(": " + text);
    }

    public InputText(String title, String text, String font, int size, int spacing) {
        super(title, font, size, Font.UNDERLINE, spacing, Text.ALIGN_LEFT);
        setFont(FontFactory.getFont(font, size, Font.NORMAL));
        add(": " + text);
    }
}
