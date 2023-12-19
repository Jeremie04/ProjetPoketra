/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.sac;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import rc.annotation.AField;
import rc.config.__Connection;
import rc.data.ObjetCRUD;

/**
 *
 * @author Ranto Jeremy
 */
public class Look extends ObjetCRUD{
    @AField (isId = true)
    private int id;
    private String nom;
    
    @AField (ignored = true)
    private Matiere[] matieres;

    public Look(int id, String nom) throws Exception {
        this.id = id;
        this.nom = nom;
    }

    public Look() throws Exception {
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

    public Matiere[] getMatieres() {
        return matieres;
    }

    public void setMatieres(Matiere[] matieres) {
        this.matieres = matieres;
    }
    
    
    public Matiere[] getMatieres (Connection co) throws Exception {
        List<Matiere> matieres = new ArrayList<>();
        boolean opened = false;
        if(co == null){
            __Connection __co = new __Connection();
            co = __co.getConnect();
            opened = true;
        }
        try {
            String request = "SELECT * from v_matiere_look where id_look =" + this.getId();
            System.out.println(request);
            try(Statement stat = co.createStatement()) {
                try(ResultSet res = stat.executeQuery(request)){
                    while(res.next()){
                        Unite unite = new Unite(res.getString("unite"));
                        Matiere matiere = new Matiere();
                        matiere.setId(res.getInt("id_matiere"));
                        matiere.setNom(res.getString("nom_matiere"));
                        matiere.setUnite(unite);
                        matieres.add(matiere);
                    }
                }
            }
        } finally {
            if(opened) co.close();
        }
        return matieres.toArray(new Matiere[matieres.size()]);
    }
}
