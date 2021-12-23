package com.presupuestos2;

import com.presupuestos2.model.other.Utilities;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {

    static private String savePath = System.getProperty("user.home");

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 700);
        stage.setTitle("Presupuestos Marcelo");
        stage.setScene(scene);
        stage.setOnCloseRequest(e -> {
            e.consume();
            Utilities.showConfirmDialog("Deseas salir?", "Salir", response -> {
                if (response == ButtonType.OK) {
                    stage.close();
                }
            });
        });
        stage.show();
    }

    static public void setSavePath(String savePath) {
        MainApplication.savePath = savePath;
    }

    static public String getSavePath() {
        return savePath;
    }

    public static void main(String[] args) {
        launch();
    }
}