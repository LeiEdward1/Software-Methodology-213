module com.example.assignment_3_fx {
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;


    opens com.example.assignment_3_fx to javafx.fxml;
    exports com.example.assignment_3_fx;
}