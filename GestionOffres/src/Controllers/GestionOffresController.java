    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entity.Offres;
import Service.OffresService;
import datasource.DataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class GestionOffresController implements Initializable {
Connection cnxC;
    
     public GestionOffresController () throws SQLException {
       cnxC = DataSource.getInstance().getCnx();
    }   
    @FXML
    private Button bntModif;
    @FXML
    private Button btnAjout;
    @FXML
    private Button btnSup;
    @FXML
    private TableView<Offres> tableOffres;
    @FXML
    private TableColumn<Offres, Integer> idO;
    @FXML
    private TableColumn<Offres, String> libO;
    @FXML
    private TableColumn<Offres, Date> debO;
    @FXML
    private TableColumn<Offres, Date> finO;
    @FXML
    private TableColumn<Offres, Integer> remO;
    @FXML
    private TextField filterInputs;
    
    public ObservableList<Offres> obb;

    public static Offres o;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LoadData();
        
}       
            
            
     
        public void LoadData()
        {
            OffresService ps = new OffresService(); //end initialize
        ArrayList<Offres> list = null;
        try {
            list = ps.getAllOffre();
        } catch (SQLException ex) {
            Logger.getLogger(GestionOffresController.class.getName()).log(Level.SEVERE, null, ex);
        }
        obb = FXCollections.observableArrayList(list);
        idO.setCellValueFactory(new PropertyValueFactory<> ("id_offre"));
        libO.setCellValueFactory(new PropertyValueFactory<> ("Lib"));
        debO.setCellValueFactory(new PropertyValueFactory<> ("dateDeb"));
        finO.setCellValueFactory(new PropertyValueFactory<> ("dateFin"));
        remO.setCellValueFactory(new PropertyValueFactory<> ("remise"));
        tableOffres.setItems(obb);
        //add Listener to filterField
//      
        
         FilteredList<Offres> filteredData = new FilteredList<>(obb,b-> true);
        
            
            filterInputs.textProperty().addListener((observable,oldValue,newValue) ->{
                    filteredData.setPredicate(offre -> {
                        
                        if (newValue == null || newValue.isEmpty()){
                            return true;
                        }
                        String LowerCaseFilter = newValue.toLowerCase();
                        return offre.getLib().toLowerCase().indexOf(LowerCaseFilter) != -1;
                        
                       
                    });
                    SortedList<Offres> sortedData = new SortedList<>(filteredData);
                    sortedData.comparatorProperty().bind(tableOffres.comparatorProperty());
                    tableOffres.setItems(sortedData);
    });
        }
    
        
        
     

    @FXML
    private boolean  Modifier(ActionEvent event) throws IOException {
        int id = tableOffres.getSelectionModel().getSelectedItem().getId_offre();
      //  String x = Integer.toString(idO) ; 
         System.out.println(tableOffres.getSelectionModel().getSelectedItem().getId_offre());
        
         System.out.println(String.valueOf(id));
      
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(GestionOffresController.class.getResource("/Views/ModifierOffres.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        ModifierOffresController c = loader.getController();
        c.setData(id);
        
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Modifier un offre");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        dialogStage.showAndWait();
        ModifierOffresController rc = loader.getController();
        
        o=tableOffres.getSelectionModel().getSelectedItem();
        rc.setLabelId(id);
        System.out.println(id);
        return false;
        
            
        
    }

    @FXML
    private boolean Ajout(ActionEvent event) throws IOException {
         
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(GestionOffresController.class.getResource("/Views/InsertOffres.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Ajouter un offre");
        dialogStage.initModality(Modality.WINDOW_MODAL);

        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        dialogStage.showAndWait();
        return false;
        
    }

    @FXML
    private void Supprimer(ActionEvent event) throws SQLException {
           System.out.println(tableOffres.getSelectionModel().getSelectedItem().getId_offre());
       tableOffres.getItems().removeAll(tableOffres.getSelectionModel().getSelectedItem());
        String query = "DELETE FROM offres where id_offre ='"+tableOffres.getSelectionModel().getSelectedItem().getId_offre()+"' " ;
        System.out.println(tableOffres.getSelectionModel().getSelectedItem().getId_offre());
         PreparedStatement pstm     =   cnxC.prepareStatement(query);
        pstm.executeUpdate();
      
        
    }

    
}
