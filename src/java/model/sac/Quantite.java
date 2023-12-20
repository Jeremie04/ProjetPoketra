/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.sac;

import rc.annotation.AField;
import rc.data.ObjetCRUD;

/**
 *
 * @author itu
 */
public class Quantite extends ObjetCRUD{
    int id;
    @AField(column = "id_modele")
    int idModele;
    @AField(column = "id_matiere_look") 
    int idMatiereLook;
    @AField(column = "id_taille")
    int idTaille;
    double quantite;

    public Quantite(int id, int idMatiereLook, int idModele, int idTaille, double quantite) throws Exception {
        this.id = id;
        this.idMatiereLook = idMatiereLook;
        this.idModele = idModele;
        this.idTaille = idTaille;
        this.quantite = quantite;
    }

    public Quantite() throws Exception{
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdMatiereLook() {
        return idMatiereLook;
    }

    public void setIdMatiereLook(int idMatiereLook) {
        this.idMatiereLook = idMatiereLook;
    }

    public int getIdModele() {
        return idModele;
    }

    public void setIdModele(int idModele) {
        this.idModele = idModele;
    }

    public int getIdTaille() {
        return idTaille;
    }

    public void setIdTaille(int idTaille) {
        this.idTaille = idTaille;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }
    
    
}
