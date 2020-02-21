/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import java.util.List;

/**
 *
 * @author LENOVO
 */
public interface IOffres<T> {
    void insert(T t);
    void update(T t , int id);
    boolean delete(int id);
    List<T> displayAll();
    
}
