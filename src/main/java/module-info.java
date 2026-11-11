module edu.utsa.cs3443.foundtimeproj {
    requires javafx.controls;
    requires javafx.fxml;


    opens edu.utsa.cs3443.foundtimeproj to javafx.fxml;
    exports edu.utsa.cs3443.foundtimeproj;
}