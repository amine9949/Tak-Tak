/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taktak;

import Entity.Offres;
import Service.OffresService;
import datasource.DataSource;
import java.sql.SQLException;
import java.text.ParseException;

/**
 *
 * @author LENOVO
 */
public class pidev {

    public static void main(String[] args) throws SQLException, ParseException {
        // TODO code application logic here
        DataSource ds = DataSource.getInstance();
        java.sql.Date date = java.sql.Date.valueOf("2020-02-18");
        // Offres o = new Offres("abc",new Date(,new Date(1994,02,02));
        System.out.println(date);
        OffresService ps = new OffresService();
        ps.insert(new Offres("abc",date,date,20));
        //ps.update(1012,o);

        // ps.getAllProduit().forEach(System.out::println);
        //ps.delete(1004);
        //  ps.insert(o);
    }
}
