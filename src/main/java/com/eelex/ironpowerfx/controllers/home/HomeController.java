package com.eelex.ironpowerfx.controllers.home;

import com.eelex.ironpowerfx.Main;
import com.eelex.ironpowerfx.controllers.home.account.AccountController;
import com.eelex.ironpowerfx.controllers.home.customers.CustomersController;
import com.eelex.ironpowerfx.controllers.home.inventory.InventoryController;
import com.eelex.ironpowerfx.controllers.home.settings.SettingsController;
import com.eelex.ironpowerfx.controllers.home.tanks.TanksController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class HomeController {

    @FXML
    BorderPane holder;

    public static final int HOME_TANKS = 0;
    public static final int HOME_CUSTOMERS = 1;
    public static final int HOME_INVENTORY = 2;
    public static final int HOME_ACCOUNT = 3;
    public static final int HOME_SETTINGS = 4;

    private Node tanksNode;
    private Node customerNode;
    private Node inventoryNode;
    private Node accountNode;
    private Node settingsNode;
    TanksController tanksController;
    CustomersController customersController;
    InventoryController inventoryController;
    AccountController accountController;
    SettingsController settingsController;

    public void loadContent() throws IOException {
        FXMLLoader tanksLoader = new FXMLLoader();
        tanksController = new TanksController();
        tanksLoader.setLocation(Main.class.getResource("home-tanks.fxml"));
        tanksLoader.setController(tanksController);
        tanksNode = tanksLoader.load();

        FXMLLoader customersLoader = new FXMLLoader();
        customersController = new CustomersController();
        customersLoader.setLocation(Main.class.getResource("home-customers.fxml"));
        customersLoader.setController(customersController);
        customerNode = customersLoader.load();

        FXMLLoader inventoryLoader = new FXMLLoader();
        inventoryController = new InventoryController();
        inventoryLoader.setLocation(Main.class.getResource("home-inventory.fxml"));
        inventoryLoader.setController(inventoryController);
        inventoryNode = inventoryLoader.load();

        FXMLLoader accountLoader = new FXMLLoader();
        accountController = new AccountController();
        accountLoader.setLocation(Main.class.getResource("home-account.fxml"));
        accountLoader.setController(accountController);
        accountNode = accountLoader.load();

        FXMLLoader settingsLoader = new FXMLLoader();
        settingsController = new SettingsController();
        settingsLoader.setLocation(Main.class.getResource("home-settings.fxml"));
        settingsLoader.setController(settingsController);
        settingsNode = settingsLoader.load();

        setView(HOME_TANKS);
    }

    private void setView(int newView) {
        if (holder.getChildren().size() > 0) {
            holder.getChildren().remove(0);
        }

        switch (newView) {
            case HOME_TANKS -> {
                holder.centerProperty().set(tanksNode);
                tanksController.loadTableContent();
            }
            case HOME_CUSTOMERS -> {
                holder.centerProperty().set(customerNode);
                customersController.loadContent();
            }
            case HOME_INVENTORY -> holder.centerProperty().set(inventoryNode);
            case HOME_ACCOUNT -> holder.centerProperty().set(accountNode);
            case HOME_SETTINGS -> holder.centerProperty().set(settingsNode);
        }
    }

    @FXML
    public void changeToTanks() {
        setView(HOME_TANKS);
    }
    @FXML
    public void changeToCustomers() {
        setView(HOME_CUSTOMERS);
    }
    @FXML
    public void changeToInventory() {
        setView(HOME_INVENTORY);
    }
    @FXML
    public void changeToAccount() {
        setView(HOME_ACCOUNT);
    }
    @FXML
    public void changeToSettings() {
        setView(HOME_SETTINGS);
    }
}
