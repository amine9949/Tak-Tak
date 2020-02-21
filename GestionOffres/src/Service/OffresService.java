/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Offres;
import datasource.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LENOVO
 */
public class OffresService implements IOffres<Offres>{
private Connection cnx;
    private Statement st;
    private PreparedStatement pst;
    private ResultSet rs;
      public OffresService() {
    cnx=DataSource.getInstance().getCnx();
    }
    @Override
    public void insert(Offres t) {
         try{
        
            
             String req = "INSERT INTO `offres`( `Libellé`,`Date_debut`,`Date_fin`,`Remise`) VALUES (?,?,?,?)";
             PreparedStatement pstm = cnx.prepareStatement(req);
             pstm.setString(1, t.getLib());
             pstm.setDate(2,  t.getDateDeb());
             pstm.setDate(3,  t.getDateFin());
             pstm.setInt(4, t.getRemise());
             
            
            
             
             pstm.executeUpdate();
     }catch(Exception e){
         Logger.getLogger(OffresService.class.getName()).log(Level.SEVERE, null, e);
     }
    }

@Override
    public void update( Offres t , int id )  {
        Statement stm = null;
    try {
        stm = cnx.createStatement();
    } catch (SQLException ex) {
        Logger.getLogger(OffresService.class.getName()).log(Level.SEVERE, null, ex);
    }
      String req="UPDATE offres SET Libellé='"+t.getLib()+"',Date_debut='"+t.getDateDeb()+"',Date_fin='"+t.getDateFin()+"',Remise='"+t.getRemise()+"' WHERE id_offre='"+id+"'";
    try { 
        stm.executeUpdate(req);
    } catch (SQLException ex) {
        Logger.getLogger(OffresService.class.getName()).log(Level.SEVERE, null, ex);
    }
      
        
   
    }

    @Override
    public boolean delete(int id) {
        String req="delete from offres where id_offre=?";
          try {
            pst=cnx.prepareStatement(req);
            pst.setInt(1,id);
            pst.executeUpdate();
            return  true;
        } catch (SQLException ex) {
            Logger.getLogger(OffresService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
     public Offres getBysh(String lib) throws SQLException
   {
       
       
        String req = "SELECT `id_offre`, `Libellé`, `Date_debut`, `Date_fin`,`Remise` WHERE `Libellé`='"+lib+"'";
     Offres o = new Offres();
     Statement stm= cnx.createStatement();
      
                 ResultSet resultat = stm.executeQuery(req);
                 if(resultat.next())
                 {
                     //nom ref cat prix qua ima
                     o.setLib(resultat.getString(2));
                     o.setDateDeb(resultat.getDate(3));
                     o.setDateFin(resultat.getDate(4));
                    
                            
                             
                 }
                 return o;
                             
                             
                    
                 }

    @Override
    public List<Offres> displayAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public  ArrayList<Offres> getAllOffre() throws SQLException {
       ArrayList<Offres> retour = new ArrayList<>();
       Statement stm = cnx.createStatement();
        String req = "SELECT * FROM offres";
        ResultSet resultat = stm.executeQuery(req);
        while(resultat.next()){
           int id_offre= resultat.getInt("id_offre");
           String Lib= resultat.getString("Libellé");
           Date Date_deb = resultat.getDate("Date_debut");
             Date Date_fin = resultat.getDate("Date_fin");
             int Remise = resultat.getInt("Remise");
            
           
           retour.add(new Offres(id_offre, Lib,Date_deb,Date_fin,Remise));
            
        }
            
        return retour;
    }
       public void supprimerOffers(int id) throws SQLException
    {
         Statement stm = cnx.createStatement();
        String req = "DELETE FROM `offres` WHERE `id_offre` like '%"+id+"%' ";
     stm.executeUpdate(req);
        //.out.println(p.getId_produit());
    
    }
    
}
