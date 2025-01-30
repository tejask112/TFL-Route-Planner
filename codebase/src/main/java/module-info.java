module com.example.codebase {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.codebase to javafx.fxml;
    exports com.example.codebase;
}