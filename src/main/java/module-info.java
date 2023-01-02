module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires jbarcode;
    requires barbecue;
    requires java.desktop;
    requires java.sql;
    requires kernel;
    requires io;
    requires layout;


    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
}