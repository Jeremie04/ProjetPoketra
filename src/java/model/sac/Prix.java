/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.sac;

import rc.data.ObjetCRUD;

/**
 *
 * @author Ranto Jeremy
 */
public class Prix extends ObjetCRUD{
    private int id;
    private int id_look;
    private double prix;

    public Prix() throws Exception {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_look() {
        return id_look;
    }

    public void setId_look(int id_look) {
        this.id_look = id_look;
    }

    
    
}
