module com.imrane.login {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires jbcrypt;


    opens com.imrane.login to javafx.fxml;
    exports com.imrane.login;
}