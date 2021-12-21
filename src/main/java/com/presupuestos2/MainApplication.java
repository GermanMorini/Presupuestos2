package com.presupuestos2;

import com.presupuestos2.model.pdffile.Img;
import com.presupuestos2.model.pdffile.MainTable;
import com.presupuestos2.model.pdffile.PDFDocument;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 700);
        stage.setTitle("Presupuestos Marcelo");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        //launch();
        try {
            new PDFDocument(
                    "/home/german/SAMPLEEE.pdf",
                    new Img("/home/german/Escritorio/Java/Proyectos/Presupuestos2/src/main/resources/com/presupuestos2/MarceloDiaz.png").getImg(),
                        new MainTable("Morini, Germán", "21/12/21", "12.300", new String[] {"sample", "sample", "sample", "sample", "sample", "sample", "sample"},
                                new String[] {"detail","detail","detail","detail"})
                    );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}