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
public class MatiereLook extends ObjetCRUD{
    private int id;
    private int id_look;
    private int id_matiere;

    public MatiereLook() throws Exception {
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

    public int getId_matiere() {
        return id_matiere;
    }

    public void setId_matiere(int id_matiere) {
        this.id_matiere = id_matiere;
    }
    
    
}
