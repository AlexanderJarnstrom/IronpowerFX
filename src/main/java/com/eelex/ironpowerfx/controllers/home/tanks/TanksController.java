package com.eelex.ironpowerfx.controllers.home.tanks;

import com.eelex.ironpowerfx.Main;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class TanksController {

    @FXML
    public TableView<Tank> tableView;

    @FXML
    public void openAddNewTank() throws IOException {
        AddNewTankController controller = new AddNewTankController();
        FXMLLoader loader = new FXMLLoader();
        loader.setController(controller);
        loader.setLocation(Main.class.getResource("add-new-tank.fxml"));

        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setScene(scene);

        stage.show();

        controller.loadContent();
    }

    public void loadTableContent() {
        TableColumn<Tank, String> colANumber = new TableColumn<Tank, String>("A Number");
        colANumber.setMinWidth("A Number".length() * 24);
        colANumber.setCellValueFactory(
                new PropertyValueFactory<Tank, String>("aNumber")
        );
        TableColumn<Tank, String> colCustomer = new TableColumn<Tank, String>("Customer");
        colCustomer.setMinWidth("Customer".length() * 24);
        colCustomer.setCellValueFactory(
                new PropertyValueFactory<Tank, String>("customer")
        );
        TableColumn<Tank, String> colOwner = new TableColumn<Tank, String>("Owner");
        colOwner.setMinWidth("Owner".length() * 24);
        colOwner.setCellValueFactory(
                new PropertyValueFactory<Tank, String>("owner")
        );
        TableColumn<Tank, String> colLocation = new TableColumn<Tank, String>("Location");
        colLocation.setMinWidth("Location".length() * 24);
        colLocation.setCellValueFactory(
                new PropertyValueFactory<Tank, String>("location")
        );
        TableColumn<Tank, String> colBuildYear = new TableColumn<Tank, String>("Build Year");
        colBuildYear.setMinWidth("Build Year".length() * 24);
        colBuildYear.setCellValueFactory(
                new PropertyValueFactory<Tank, String>("buildYear")
        );
        TableColumn<Tank, String> colSize = new TableColumn<Tank, String>("Size");
        colSize.setMinWidth("Size".length() * 24);
        colSize.setCellValueFactory(
                new PropertyValueFactory<Tank, String>("size")
        );
        TableColumn<Tank, String> colOther = new TableColumn<Tank, String>("Other");
        colOther.setMinWidth("Other".length() * 24);
        colOther.setCellValueFactory(
                new PropertyValueFactory<Tank, String>("other")
        );

        ObservableList<Tank> list = FXCollections.observableArrayList(
                new Tank("b","b","b","b","b","b","b"),
                new Tank("a","a","a","a","a","a","a"),
                new Tank("a","a","a","a","a","a","a")
        );

        for (int i = 0; i < 40; i++) {
            list.add(
                    new Tank("a","a","a","a","a","a","a")
            );
        }

        tableView.setItems(list);
        tableView.getColumns().addAll(colANumber, colCustomer, colOwner, colLocation, colBuildYear, colSize, colOther);
    }

    public static class Tank {
        private final SimpleStringProperty aNumber;
        private final SimpleStringProperty customer;
        private final SimpleStringProperty owner;
        private final SimpleStringProperty location;
        private final SimpleStringProperty buildYear;
        private final SimpleStringProperty size;
        private final SimpleStringProperty other;

        private Tank(String aNumber, String customer, String owner, String location, String buildYear, String size, String other) {
            this.aNumber = new SimpleStringProperty(aNumber);
            this.customer = new SimpleStringProperty(customer);
            this.owner = new SimpleStringProperty(owner);
            this.location = new SimpleStringProperty(location);
            this.buildYear = new SimpleStringProperty(buildYear);
            this.size = new SimpleStringProperty(size);
            this.other = new SimpleStringProperty(other);
        }

        public String getANumber() {
            return aNumber.get();
        }

        public String getCustomer() {
            return customer.get();
        }

        public String getOwner() {
            return owner.get();
        }

        public String getLocation() {
            return location.get();
        }

        public String getBuildYear() {
            return buildYear.get();
        }

        public String getSize() {
            return size.get();
        }

        public String getOther() {
            return other.get();
        }
    }
}
