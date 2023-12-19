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
public class Modele extends ObjetCRUD {
    private int id;
    private String nom;
    private int id_taille;
    private int id_type;
    private int id_look;

    public Modele() throws Exception {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getId_taille() {
        return id_taille;
    }

    public void setId_taille(int id_taille) {
        this.id_taille = id_taille;
    }

    public int getId_type() {
        return id_type;
    }

    public void setId_type(int id_type) {
        this.id_type = id_type;
    }

    public int getId_look() {
        return id_look;
    }

    public void setId_look(int id_look) {
        this.id_look = id_look;
    }
    
    
}
