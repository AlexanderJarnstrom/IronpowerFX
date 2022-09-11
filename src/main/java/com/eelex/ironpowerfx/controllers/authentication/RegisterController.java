package com.eelex.ironpowerfx.controllers.authentication;

import com.eelex.ironpowerfx.Main;
import com.eelex.ironpowerfx.ProgramHandler;
import com.eelex.ironpowerfx.services.ServerHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterController {
    @FXML
    AnchorPane anchorPane;
    @FXML
    TextField tfEmail;
    @FXML
    TextField tfFirstName;
    @FXML
    TextField tfLastName;
    @FXML
    TextField tfPhoneNumber;
    @FXML
    PasswordField pf;
    @FXML
    PasswordField pfReentered;
    @FXML
    CheckBox cbUserAgreement;
    @FXML
    Label errorMessage;

    private boolean valid = false;

    private boolean isEmailValid = false;
    private boolean isFirstNameValid = false;
    private boolean isLastNameValid = false;
    private boolean isPhoneNumberValid = false;
    private boolean isPasswordValid = false;
    private boolean isReenteredPasswordValid = false;

    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String password;

    private int responseCode;

    private final ProgramHandler programHandler;

    public RegisterController(ProgramHandler programHandler) {
        this.programHandler = programHandler;
    }

    @FXML
    protected void register() {
        validate();

        if (valid) {
            email = tfEmail.getText();
            firstName = tfFirstName.getText();
            lastName = tfLastName.getText();
            phoneNumber = tfPhoneNumber.getText();
            password = pf.getText();

            try {
                RegisterThread registerThread = new RegisterThread();
                registerThread.start();
                registerThread.join();
                if (responseCode == 409) {
                    errorMessage.setText("Email already in use");
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println(valid);
    }

    private void validate() {
        validateEmail();
        validateFirstName();
        validateLastName();
        validatePhoneNumber();
        validatePassword();
        validateReenteredPassword();

        valid = isEmailValid &&
                isFirstNameValid &&
                isLastNameValid &&
                isPhoneNumberValid &&
                isPasswordValid &&
                isReenteredPasswordValid &&
                cbUserAgreement.isSelected();
    }

    @FXML
    protected void validateEmail() {
        String emailAddress = tfEmail.getText();
        String regexPattern = "^(.+)@(\\S+)$";
        isEmailValid = patternMatches(emailAddress, regexPattern);
        if (!isEmailValid) {
            tfEmail.setId("tf-error");
            errorMessage.setText("Not a valid Email");
        } else {
            tfEmail.setId("");
        }
    }

    @FXML
    protected void validateFirstName() {
        isFirstNameValid = tfFirstName.getText().length() > 0;
        if (!isFirstNameValid) {
            tfFirstName.setId("tf-error");
            errorMessage.setText("Not a valid First Name");
        } else {
            tfFirstName.setId("");
        }
    }

    @FXML
    protected void validateLastName() {
        isLastNameValid = tfLastName.getText().length() > 0;
        if (!isLastNameValid) {
            tfLastName.setId("tf-error");
            errorMessage.setText("Not a valid Last Name");
        } else {
            tfLastName.setId("");
        }
    }

    @FXML
    protected void validatePhoneNumber() {
        isPhoneNumberValid = tfPhoneNumber.getText().length() > 0;
        if (!isPhoneNumberValid) {
            tfPhoneNumber.setId("tf-error");
            errorMessage.setText("Not a valid Phone Number");
        } else {
            tfPhoneNumber.setId("");
        }
    }

    @FXML
    protected void validatePassword() {
        String password = pf.getText();

        // Regex to check valid password.
        String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=.])"
                + "(?=\\S+$).{8,20}$";

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        // If the password is empty
        // return false
        if (password == null) {
            isPasswordValid = false;
        } else {
            // Pattern class contains matcher() method
            // to find matching between given password
            // and regular expression.
            Matcher m = p.matcher(password);

            // Return if the password
            // matched the ReGex
            isPasswordValid = m.matches();
        }

        if (!isPasswordValid) {
            pf.setId("tf-error");
            errorMessage.setText("Password has to be between 8 and 20 character," +
                    " contain a Uppercase and a lowercase letter, " +
                    "a number and a special Character");
        } else {
            pf.setId("");
        }

    }

    @FXML
    protected void validateReenteredPassword() {
        isReenteredPasswordValid = pf.getText().equals(pfReentered.getText());
        if (!isReenteredPasswordValid) {
            pfReentered.setId("tf-error");

        } else {
            pfReentered.setId("");
        }
    }

    @FXML
    protected void goBack() {
        programHandler.openSignIn();
    }

    private static boolean patternMatches(String s, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(s)
                .matches();
    }

    private Stage getStage() {
        return (Stage) anchorPane.getScene().getWindow();
    }

    private class RegisterThread extends Thread {
        @Override
        public void run() {
            try {
                responseCode = ServerHandler.register(email, firstName, lastName, phoneNumber, password);
            } catch (IOException e) {
                errorMessage.setText("An error occurred");
                throw new RuntimeException(e);
            }
        }
    }
}
