package com.eelex.ironpowerfx.controllers.dialogs;

import com.eelex.ironpowerfx.templates.DialogButtonMethod;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class DialogController {
    @FXML
    public Label errorMessage;
    @FXML
    public Button bOk;
    @FXML
    public Button bClose;

    private DialogButtonMethod okMethod;
    private DialogButtonMethod cancelMethod;

    public void setErrorMessage(String message) {
        errorMessage.setText(message);
    }

    @FXML
    public void okAction() {
        okMethod.execute();
    }

    @FXML
    public void cancelAction() {
        cancelMethod.execute();
    }

    public void setCloseButtonVisible(boolean visible) {
        bClose.setVisible(visible);
    }

    public void setOkMethod(DialogButtonMethod okMethod) {
        this.okMethod = okMethod;
    }

    public void setCancelMethod(DialogButtonMethod cancelMethod) {
        this.cancelMethod = cancelMethod;
    }
}
