module Serenity.ORM.Coursework {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires java.desktop;
    requires net.sf.jasperreports.core;
    requires java.mail;
    requires jbcrypt;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.naming;
    requires com.google.protobuf;

    opens com.gdse.serenity.entity to org.hibernate.orm.core;
    opens com.gdse.serenity.config to jakarta.persistence;
    opens com.gdse.serenity.view.tdm to javafx.base;
    opens com.gdse.serenity.controller to javafx.fxml;

    exports com.gdse.serenity;
}