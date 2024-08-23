module com.kldp.test {
    //requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires transitive javafx.controls;
	requires javafx.base;
	requires zip4j;

    opens com.kldp.test to javafx.fxml;
    opens com.kldp.second.archive to javafx.fxml;
    exports com.kldp.test;
    exports com.kldp.second.archive;
}
