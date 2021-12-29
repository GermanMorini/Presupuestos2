package com.presupuestos2.model.pdffile;

import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;

public class InputText extends Text {

    public InputText(String title, String text, Font font, int spacing, int indentation) {
        super(
                title,
                FontFactory.getFont(font.getFamilyname(), font.getSize(), Font.UNDERLINE),
                spacing,
                Text.ALIGN_LEFT, indentation
        );
        setFont(FontFactory.getFont(font.getFamilyname(), font.getSize(), Font.NORMAL));
        add(": " + text);
    }
}
