module com.example.assignment_4 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.assignment_4 to javafx.fxml;
    exports com.example.assignment_4;
}