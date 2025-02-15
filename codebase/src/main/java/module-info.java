module com.example.codebase {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.opencsv;
    requires org.json;

  opens com.example.codebase to javafx.fxml;
    exports com.example.codebase;
}