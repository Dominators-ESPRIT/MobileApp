/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.entity;

import java.util.Date;

/**
 *
 * @author zeyne
 */
public class Oeuvre {

     private int id;
     private String id_panier_id;
     private String nom;
     private String description;
     private Date date_ajout;
     private Double prix;
     private String image; 

    public Oeuvre(int id, String id_panier_id, String nom, String description, Date date_ajout, Double prix, String image) {
        this.id = id;
        this.id_panier_id = id_panier_id;
        this.nom = nom;
        this.description = description;
        this.date_ajout = date_ajout;
        this.prix = prix;
        this.image = image;
    }

    public Oeuvre(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getId_panier_id() {
        return id_panier_id;
    }

    public void setId_panier_id(String id_panier_id) {
        this.id_panier_id = id_panier_id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate_ajout() {
        return date_ajout;
    }

    public void setDate_ajout(Date date_ajout) {
        this.date_ajout = date_ajout;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    
 


}
