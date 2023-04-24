module com.example.ecommers_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.example.ecommers_project to javafx.fxml;
    exports com.example.ecommers_project;
}