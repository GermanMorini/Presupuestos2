package com.presupuestos2.model.pdffile;

import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;

public class TextFactory {

    static private Font font =  FontFactory.getFont(Font.FontFamily.HELVETICA.name(), 14, Font.UNDERLINE);
    static private int spacing = 8;
    static private int indentation = 10;

    public static Font getFont() { return font; }

    public static void setFont(Font font) { TextFactory.font = font; }

    public static int getSpacing() { return spacing; }

    public static void setSpacing(int spacing) { TextFactory.spacing = spacing; }

    public static int getIndentation() { return indentation; }

    public static void setIndentation(int indentation) { TextFactory.indentation = indentation; }

    static public Text createText(String text, int pos) {
        return new Text(text, font, spacing, pos, indentation);
    }

    static public Text createText(String text, int pos, Font font) {
        return new Text(text, font, spacing, pos, indentation);
    }

    static public Text createText(String text, int pos, int size) {
        return new Text(text, FontFactory.getFont(font.getFamilyname(), size, font.getStyle()), spacing, pos, indentation);
    }

    static public InputText createInputText(String title, String text) {
        return new InputText(title, text, font, spacing, indentation);
    }

    static public InputText createInputText(String title, String text, int spacing) {
        return new InputText(title, text, font, spacing, indentation);
    }

    static public Item createItem(String text, char symbol) {
        return new Item(text, font, spacing, symbol);
    }
}
