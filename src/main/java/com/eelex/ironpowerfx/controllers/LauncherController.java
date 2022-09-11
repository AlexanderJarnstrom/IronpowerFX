package com.eelex.ironpowerfx.controllers;

import com.eelex.ironpowerfx.ProgramHandler;
import com.eelex.ironpowerfx.services.CheckDataBase;
import com.eelex.ironpowerfx.services.ServerHandler;
import com.eelex.ironpowerfx.services.UserCredentialsHandler;
import com.eelex.ironpowerfx.services.UserTempFileHandler;
import com.eelex.ironpowerfx.templates.DialogButtonMethod;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import org.json.simple.JSONObject;

import java.io.IOException;

public class LauncherController {

    @FXML
    ProgressBar progressBar;

    public void setProgress(double progress) {
        progressBar.setProgress(progress);
    }

    public static final int OPEN_MAIN_PROGRAM = 0;
    public static final int OPEN_SIGN_IN = 1;
    private int openWindow = OPEN_MAIN_PROGRAM;
    private int tasksToDo = 7;
    private int tasksDone = 0;
    private final ProgramHandler programHandler;
    private JSONObject userCredentials;

    private String error = "";

    public LauncherController(ProgramHandler programHandler) {
        this.programHandler = programHandler;
    }

    public void start() throws InterruptedException {
        Thread launch = new Thread(new Runnable() {
            @Override
            public void run() {
                Runnable updater = new Runnable() {
                    @Override
                    public void run() {
                        setProgress(calculateProgress());
                    }
                };

                Runnable openSignUp = new Runnable() {
                    @Override
                    public void run() {
                        programHandler.openSignIn();
                    }
                };

                Runnable openMainProgram = new Runnable() {
                    @Override
                    public void run() {
                        programHandler.openMainProgram();
                    }
                };

                Runnable openError = new Runnable() {
                    @Override
                    public void run() {
                        programHandler.openErrorWindow(new OkPressed(programHandler), error);
                    }
                };

                Platform.runLater(updater);

                userCredentials = UserCredentialsHandler.checkFileContent();

                if (userCredentials == null) {
                    System.out.println("user is null");
                    Platform.runLater(openSignUp);
                }

                Platform.runLater(updater);
                if (userCredentials != null) {
                    try {
                        int result = ServerHandler.signIn(
                                (String) userCredentials.get("email"),
                                (String) userCredentials.get("password")
                        );

                        Platform.runLater(updater);


                        if (result == 200) {

                            if (CheckDataBase.checkDatabase() == CheckDataBase.ERROR) {
                                error = "Failed to database";
                                Platform.runLater(openError);
                            } else {
                                Platform.runLater(updater);

                                if (CheckDataBase.checkTanks() == CheckDataBase.ERROR) {
                                    error = "Failed to check table";
                                    Platform.runLater(openError);
                                }
                                Platform.runLater(updater);

                                if (CheckDataBase.checkCustomers() == CheckDataBase.ERROR) {
                                    error = "Failed to check table";
                                    Platform.runLater(openError);
                                }
                                Platform.runLater(updater);

                                if (CheckDataBase.checkInventory() == CheckDataBase.ERROR) {
                                    error = "Failed to check table";
                                    Platform.runLater(openError);
                                }
                                Platform.runLater(updater);

                                Platform.runLater(openMainProgram);
                            }

                        } else if (result == 409) {
                            error = "User not found";
                            Platform.runLater(openError);
                        } else {
                            error = "Sign in failed";
                            Platform.runLater(openError);
                        }
                    } catch (IOException e) {
                        error = "Sign in failed";
                        Platform.runLater(openError);
                        throw new RuntimeException(e);
                    }
                }

            }
        }) ;

        launch.setDaemon(true);
        launch.start();
    }

    private double calculateProgress() {
        tasksDone++;
        System.out.println((double) tasksDone / tasksToDo);
        return (double) tasksDone / tasksToDo;
    }

    private record OkPressed(ProgramHandler ph) implements DialogButtonMethod {

        @Override
        public void execute() {
            ph.openSignIn();
        }
    }
}
