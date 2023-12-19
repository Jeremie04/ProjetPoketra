/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.sac;

import rc.annotation.AField;
import rc.data.ObjetCRUD;

/**
 *
 * @author Ranto Jeremy
 */
public class Matiere extends ObjetCRUD{
    private int id;
    private String nom;
    private int id_unite;
    
    @AField (ignored = true)
    private Unite unite;

    public Matiere() throws Exception {
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

    public int getId_unite() {
        return id_unite;
    }

    public void setId_unite(int id_unite) {
        this.id_unite = id_unite;
    }

    public Unite getUnite() {
        return unite;
    }

    public void setUnite(Unite unite) {
        this.unite = unite;
    }
    
    
}
