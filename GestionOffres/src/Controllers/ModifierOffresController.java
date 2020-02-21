/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entity.Offres;
import Service.OffresService;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class ModifierOffresController implements Initializable {

    @FXML
    private Label idO;
    @FXML
    private JFXDatePicker debO;
    @FXML
    private JFXDatePicker finO;
    @FXML
    private JFXTextField labO;
    @FXML
    private Button btnModif;
    @FXML
    private Button btnFerm;
    @FXML
    private JFXTextField remO;

    private Offres selectedOffre;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println();
       // selectedOffre=GestionOffresController.o;
        //System.out.println(selectedOffre.toString());
        
    }    
     
        public void setLabelId(int labelId) {
        idO.setText(String.valueOf(labelId)); 
        
    }
      
        
        public void setData(int o)
        {
            idO.setText(String.valueOf(o)); 
        }

    @FXML
    private void Modifier(ActionEvent event) {
         OffresService os = new OffresService();
         Offres om =new Offres();
           om.setLib(labO.getText());
           om.setDateDeb(java.sql.Date.valueOf(debO.getValue()));
           om.setDateFin(java.sql.Date.valueOf(finO.getValue()));
           om.setRemise(Integer.parseInt(remO.getText()));
            String id = idO.getText();
           os.update(om,Integer.parseInt(id));
           
             JOptionPane.showMessageDialog(null, "Modification effectué");
             
    }

    @FXML
    private void Fermer(ActionEvent event) {
       Stage stage = (Stage) btnFerm.getScene().getWindow();
    // do what you have to do
    stage.close();
    }
    
}
