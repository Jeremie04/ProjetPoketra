/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.sac;

import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import rc.annotation.AField;
import rc.data.ObjetCRUD;

/**
 *
 * @author itu
 */
public class V_Matiere_Look extends ObjetCRUD{
    int id;
    @AField(column = "id_look")
    int idLook;
    @AField(column = "nom_look")
    String NomLook;
    @AField(column = "id_matiere")
    int idMatiere;
    @AField(column = "nom_matiere")
    String NomMatiere;
    @AField(column = "id_unite")
    int idUnite;
    @AField(column = "nom_unite")
    String NomUnite;

    public V_Matiere_Look() throws Exception {
    }

    public V_Matiere_Look(int id, int idLook, String NomLook, int idMatiere, String NomMatiere, int idUnite, String NomUnite) throws Exception {
        this.id = id;
        this.idLook = idLook;
        this.NomLook = NomLook;
        this.idMatiere = idMatiere;
        this.NomMatiere = NomMatiere;
        this.idUnite = idUnite;
        this.NomUnite = NomUnite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdLook() {
        return idLook;
    }

    public void setIdLook(int idLook) {
        this.idLook = idLook;
    }

    public String getNomLook() {
        return NomLook;
    }

    public void setNomLook(String NomLook) {
        this.NomLook = NomLook;
    }

    public int getIdMatiere() {
        return idMatiere;
    }

    public void setIdMatiere(int idMatiere) {
        this.idMatiere = idMatiere;
    }

    public String getNomMatiere() {
        return NomMatiere;
    }

    public void setNomMatiere(String NomMatiere) {
        this.NomMatiere = NomMatiere;
    }

    public int getIdUnite() {
        return idUnite;
    }

    public void setIdUnite(int idUnite) {
        this.idUnite = idUnite;
    }

    public String getNomUnite() {
        return NomUnite;
    }

    public void setNomUnite(String NomUnite) {
        this.NomUnite = NomUnite;
    }

    public PreparedStatement getStmt() {
        return stmt;
    }

    public void setStmt(PreparedStatement stmt) {
        this.stmt = stmt;
    }

    public ResultSet getRes() {
        return res;
    }

    public void setRes(ResultSet res) {
        this.res = res;
    }

    public static int getInitialize() {
        return initialize;
    }

    public static void setInitialize(int initialize) {
        ObjetCRUD.initialize = initialize;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
    
}
