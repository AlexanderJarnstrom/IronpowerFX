package com.eelex.ironpowerfx.controllers.authentication;

import com.eelex.ironpowerfx.Main;
import com.eelex.ironpowerfx.ProgramHandler;
import com.eelex.ironpowerfx.controllers.dialogs.DialogController;
import com.eelex.ironpowerfx.services.ServerHandler;
import com.eelex.ironpowerfx.services.UserCredentialsHandler;
import com.eelex.ironpowerfx.templates.DialogButtonMethod;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SignInController {
    @FXML
    TextField tfEmail;
    @FXML
    PasswordField pf;
    @FXML
    AnchorPane anchorPane;
    @FXML
    Label errorMessage;

    private final ProgramHandler programHandler;

    public SignInController(ProgramHandler programHandler) {
        this.programHandler = programHandler;
    }

    @FXML
    private void signIn() throws InterruptedException, IOException {
        int result = ServerHandler.signIn(tfEmail.getText(), pf.getText());

        if (result != 200) {
            errorMessage.setText("Couldn't sign in.");
        } else {
            Thread signInOp = new Thread(new SignInAction());
            signInOp.start();
        }
    }

    @FXML
    private void register() throws IOException {
        programHandler.openRegister();
    }

    private Stage getStage() {
        return (Stage) anchorPane.getScene().getWindow();
    }

    private class SignInAction implements Runnable {

        int resultCode = 0;

        @Override
        public void run() {

            Runnable openDialog = new Runnable() {
                @Override
                public void run() {
                    try {
                        openSaveCredentialsDialog();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            };

            try {
                resultCode = ServerHandler.signIn(tfEmail.getText(), pf.getText());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if (resultCode != 200) {
                errorMessage.setText("An error Occurred");
            } else {
                UserCredentialsHandler.writeToFile(tfEmail.getText(), pf.getText());
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        programHandler.openLauncher();
                    }
                });
            }
        }

        private void openSaveCredentialsDialog() throws IOException {
            FXMLLoader loader = new FXMLLoader();
            DialogController controller = new DialogController();

            loader.setLocation(Main.class.getResource("dialog.fxml"));
            loader.setController(controller);

            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.show();

            controller.setOkMethod(new SaveCredentialsDialog());
            controller.setCancelMethod(new DoNotSaveCredentials(stage));
            controller.setErrorMessage("Would you like to save your Credentials");
        }

        public int getResultCode() {
            return resultCode;
        }

        private class SaveCredentialsDialog implements DialogButtonMethod {
            @Override
            public void execute() {
                UserCredentialsHandler.writeToFile(tfEmail.getText(), pf.getText());
            }
        }

        private class DoNotSaveCredentials implements DialogButtonMethod {

            private final Stage stage;

            public DoNotSaveCredentials(Stage stage) {
                this.stage = stage;
            }

            @Override
            public void execute() {
                stage.close();
            }
        }
    }
}
