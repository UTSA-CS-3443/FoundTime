module edu.utsa.cs3443.foundtime {
    requires javafx.controls;
    requires javafx.fxml;


    opens edu.utsa.cs3443.foundtime to javafx.fxml;
    exports edu.utsa.cs3443.foundtime;
}