module cr.ac.una.clinicauna {
    
    requires javafx.fxml;
    requires com.jfoenix;
    requires jakarta.xml.bind;
    requires jakarta.ws.rs;
    requires java.logging;
    requires javafx.controls;
    requires java.sql;
    requires java.base;
    requires jakarta.json; 
  

  
    
    
    
    opens cr.ac.una.clinicauna to javafx.fxml, javafx.graphics; 
    opens cr.ac.una.clinicauna.controller to javafx.fxml, javafx.controls, com.jfoenix; 
    opens cr.ac.una.clinicauna.util to javafx.fxml;
    exports cr.ac.una.clinicauna.model;
}
