/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entity.MailUtil;
import Entity.Offres;
import Service.OffresService;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import datasource.DataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class IsertOffresController implements Initializable {
    Connection cnx ; 
    @FXML
    private JFXDatePicker id_deb;
    @FXML
    private JFXDatePicker id_fin;
    @FXML
    private JFXTextField id_Lib;
    @FXML
    private Button btn_ajout;
    @FXML
    private Button btn_retour;
    @FXML
    private JFXTextField idRem;

     public IsertOffresController() throws SQLException{
              cnx = DataSource.getInstance().getCnx();
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjouterOffre(ActionEvent event) throws IOException, Exception {
         String Lib= id_Lib.getText();
         
         Date  DateDeb = java.sql.Date.valueOf(id_deb.getValue());
         Date  DateFin = java.sql.Date.valueOf(id_fin.getValue());
         
         if (DateDeb.compareTo(DateFin)>0){
             JOptionPane.showMessageDialog(null, "Date debut is after date fin");}
             else if (Lib.isEmpty())
                 JOptionPane.showMessageDialog(null, "Vous devez saisir une libellé");
                         else if (idRem.getText().isEmpty())
                             JOptionPane.showMessageDialog(null, "Vous devez saisir un remise");
                     
         
         else{
                             int Rem = Integer.parseInt(idRem.getText());
         Offres o =new Offres(Lib,DateDeb,DateFin,Rem);
             OffresService OS =new OffresService();
         OS.insert(o);
         JOptionPane.showMessageDialog(null, "Ajout effectué");
             MailUtil.sendMail("aminebenabdallah15@gmail.com");
         }
         
    }

    @FXML
    private void retour(ActionEvent event)  {
        Stage stage = (Stage) btn_retour.getScene().getWindow();
    // do what you have to do
    stage.close();
    }
    }
    

