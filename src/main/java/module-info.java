module com.eelex.ironpowerfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;


    opens com.eelex.ironpowerfx to javafx.fxml;
    exports com.eelex.ironpowerfx;
    exports com.eelex.ironpowerfx.controllers;
    opens com.eelex.ironpowerfx.controllers to javafx.fxml;
    exports com.eelex.ironpowerfx.controllers.dialogs;
    opens com.eelex.ironpowerfx.controllers.dialogs to javafx.fxml;
    exports com.eelex.ironpowerfx.controllers.authentication;
    opens com.eelex.ironpowerfx.controllers.authentication to javafx.fxml;
    exports com.eelex.ironpowerfx.controllers.home;
    opens com.eelex.ironpowerfx.controllers.home to javafx.fxml;
    exports com.eelex.ironpowerfx.controllers.home.tanks;
    opens com.eelex.ironpowerfx.controllers.home.tanks to javafx.fxml;
    exports com.eelex.ironpowerfx.controllers.home.customers;
    opens com.eelex.ironpowerfx.controllers.home.customers to javafx.fxml;
}