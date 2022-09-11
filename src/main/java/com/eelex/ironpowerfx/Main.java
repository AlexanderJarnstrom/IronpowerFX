package com.eelex.ironpowerfx;

import com.eelex.ironpowerfx.controllers.LauncherController;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        try {
            ProgramHandler ph = new ProgramHandler();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}