module com.example.bankinformationsystem {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;
    requires org.apache.commons.codec;

    opens com.example.bankinformationsystem to javafx.fxml;
    exports com.example.bankinformationsystem;
    exports com.example.bankinformationsystem.UI;
    opens com.example.bankinformationsystem.UI to javafx.fxml;
    exports com.example.bankinformationsystem.DB;
    opens com.example.bankinformationsystem.DB to javafx.fxml;
    exports com.example.bankinformationsystem.utils;
    opens com.example.bankinformationsystem.utils to javafx.fxml;
}