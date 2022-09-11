package com.eelex.ironpowerfx;

import com.eelex.ironpowerfx.controllers.LauncherController;
import com.eelex.ironpowerfx.controllers.authentication.RegisterController;
import com.eelex.ironpowerfx.controllers.authentication.SignInController;
import com.eelex.ironpowerfx.controllers.dialogs.DialogController;
import com.eelex.ironpowerfx.controllers.home.HomeController;
import com.eelex.ironpowerfx.services.UserTempFileHandler;
import com.eelex.ironpowerfx.templates.DialogButtonMethod;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class ProgramHandler {
    private Stage stage;

    private Scene launcher;
    private LauncherController launcherController;
    private Scene signIn;
    private SignInController signInController;
    private Scene register;
    private RegisterController registerController;
    private Scene home;
    private HomeController homeController;

    public ProgramHandler() throws IOException, InterruptedException {
        stage = new Stage();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                stop();
            }
        });

        FXMLLoader launcherLoader = new FXMLLoader();
        launcherController = new LauncherController(this);
        launcherLoader.setLocation(Main.class.getResource("launcher.fxml"));
        launcherLoader.setController(launcherController);
        launcher = new Scene(launcherLoader.load());

        FXMLLoader signInLoader = new FXMLLoader();
        signInController = new SignInController(this);
        signInLoader.setLocation(Main.class.getResource("sign-in.fxml"));
        signInLoader.setController(signInController);
        signIn = new Scene(signInLoader.load());

        FXMLLoader registerLoader = new FXMLLoader();
        registerController = new RegisterController(this);
        registerLoader.setLocation(Main.class.getResource("register.fxml"));
        registerLoader.setController(registerController);
        register = new Scene(registerLoader.load());

        FXMLLoader homeLoader = new FXMLLoader();
        homeController = new HomeController();
        homeLoader.setLocation(Main.class.getResource("home.fxml"));
        homeLoader.setController(homeController);
        home = new Scene(homeLoader.load());

        openLauncher();
    }

    private void centerWindow() {
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
    }

    private void stop() {
        UserTempFileHandler.deleteTempFile();
    }

    public void openLauncher() {
        stage.setScene(launcher);
        try {
            launcherController.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        stage.show();
    }
    public void openSignIn() {
        stage.setScene(signIn);
        centerWindow();
        stage.show();
    }
    public void openRegister() {
        stage.setScene(register);
        stage.show();
    }

    public void openMainProgram() {
        stage.setScene(home);
        centerWindow();

        try {
            homeController.loadContent();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();
    }

    public void openErrorWindow(DialogButtonMethod okMethod, String message) {
        FXMLLoader loader = new FXMLLoader();
        DialogController edc = new DialogController();

        loader.setLocation(Main.class.getResource("dialog.fxml"));
        loader.setController(edc);

        try {
            stage.setScene(new Scene(loader.load()));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        edc.setOkMethod(okMethod);
        edc.setCloseButtonVisible(false);
        edc.setErrorMessage(message);
    }
}
