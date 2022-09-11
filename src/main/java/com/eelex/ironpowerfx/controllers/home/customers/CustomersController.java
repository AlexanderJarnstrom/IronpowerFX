package com.eelex.ironpowerfx.controllers.home.customers;

import com.eelex.ironpowerfx.Main;
import com.eelex.ironpowerfx.services.ServerHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class CustomersController {

    public void loadContent() {
        try {
            ServerHandler.getCustomers();
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void openAddNewCustomer() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        AddNewCustomerController controller = new AddNewCustomerController();

        loader.setLocation(Main.class.getResource("add-new-customer.fxml"));
        loader.setController(controller);

        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }
}
