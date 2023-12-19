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
public class Unite  extends ObjetCRUD{
    private int id;
    private String intitule;

    public Unite() throws Exception {
    }

    public Unite(String intitule) throws Exception {
        this.intitule = intitule;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }
    
    
}
