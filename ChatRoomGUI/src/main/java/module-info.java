module com.arabie {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.materialdesign2;
    requires javafx.swing;
    opens com.arabie to javafx.fxml;

    exports com.arabie;
}