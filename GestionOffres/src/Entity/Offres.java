/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.sql.Date;

/**
 *
 * @author LENOVO
 */
public class Offres {
    private int id_offre ;
    private String Lib;
    private Date dateDeb,dateFin;
    private int remise ; 

    public Offres(int id_offre, String Lib, Date dateDeb, Date dateFin,int remise) {
        this.id_offre = id_offre;
        this.Lib = Lib;
        this.dateDeb = dateDeb;
        this.dateFin = dateFin;
        this.remise = remise ; 
    }
    
    

    public Offres(String Lib, Date dateDeb, Date dateFin,int remise) {
        this.Lib = Lib;
        this.dateDeb = dateDeb;
        this.dateFin = dateFin;
        this.remise = remise ; 
    }

    public Offres() {
    }

    public int getId_offre() {
        return id_offre;
    }

    public String getLib() {
        return Lib;
    }

    public Date getDateDeb() {
        return dateDeb;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public int getRemise() {
        return remise;
    }
    
    

    public void setId_offre(int id_offre) {
        this.id_offre = id_offre;
    }

    public void setLib(String Lib) {
        this.Lib = Lib;
    }

    public void setDateDeb(Date dateDeb) {
        this.dateDeb = dateDeb;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public void setRemise(int remise) {
        this.remise = remise;
    }

    @Override
    public String toString() {
        return "Offres{" + "id_offre=" + id_offre + ", Lib=" + Lib + ", dateDeb=" + dateDeb + ", dateFin=" + dateFin + ", remise=" + remise + '}';
    }
    
    

    

    
    
    
    

   
}
