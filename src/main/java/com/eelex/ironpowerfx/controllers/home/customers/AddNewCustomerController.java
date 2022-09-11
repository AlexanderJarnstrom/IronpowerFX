package com.eelex.ironpowerfx.controllers.home.customers;

import com.eelex.ironpowerfx.templates.CustomerObject;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddNewCustomerController {
    @FXML
    TextField tfCompanyName;
    @FXML
    TextField tfContactPerson;
    @FXML
    TextField tfPhoneNumber;
    @FXML
    TextField tfEmail;
    @FXML
    TextField tfAddress;
    @FXML
    TextField tfZipCode;

    private CustomerObject customer;

    @FXML
    public void addNewCustomer() {
        customer = new CustomerObject();
        customer.setCompanyName(tfCompanyName.getText());
        customer.setContactPerson(tfContactPerson.getText());
        customer.setPhoneNumber(tfPhoneNumber.getText());
        customer.setEmail(tfEmail.getText());
        customer.setAddress(tfAddress.getText());
        customer.setZipCode(tfZipCode.getText());

        System.out.println(customer.getCompanyName());
    }

    @FXML
    public void close() {
        Stage stage = (Stage) tfCompanyName.getScene().getWindow();
        stage.close();
    }
}
