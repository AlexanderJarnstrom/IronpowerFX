package com.eelex.ironpowerfx.controllers.home.tanks;

import com.eelex.ironpowerfx.templates.TankObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class AddNewTankController {
    @FXML
    public TextField tfANumber;
    @FXML
    public TextField tfOwner;
    @FXML
    public TextField tfLocation;
    @FXML
    public TextField tfBuildYear;
    @FXML
    public TextField tfSize;
    @FXML
    public TextArea taOther;
    @FXML
    public ChoiceBox<String> cbCustomer;
    @FXML
    public Label lErrorText;

    private TankObject tank;
    private ObservableList<String> olCustomers;

    public void loadContent() {
        List<String> tempList = new ArrayList<String>();
        tempList.add("Hello");
        tempList.add("Hello1");
        tempList.add("Hello2");

        olCustomers = FXCollections.observableList(tempList);

        cbCustomer.setItems(olCustomers);
    }

    @FXML
    public void addNewTank() {
        tank = new TankObject();
        tank.setANumber(tfANumber.getText());
        tank.setCustomer(cbCustomer.getValue());
        tank.setOwner(tfOwner.getText());
        tank.setLocation(tfLocation.getText());
        tank.setBuildYear(tfBuildYear.getText());
        tank.setSize(tfSize.getText());
        tank.setOther(taOther.getText());

        tank.printValues();
    }
}
