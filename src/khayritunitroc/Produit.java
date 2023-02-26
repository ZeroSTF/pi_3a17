/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khayritunitroc;

/**
 *
 * @author kheir
 */
public class Produit {
      private Integer produitid;
    private String type;
    private String categorie;
    private String nom;
    private String libelle;        
    private String ville;
    private String photo;
    

    public Produit(Integer produitid, String type, String categorie,String nom,String libelle,String ville, String photo){
         this.produitid =produitid;   
         this.type =type; 
         this.categorie =categorie; 
         this.nom =nom; 
         this.libelle =libelle; 
         this.ville =ville; 
         this.photo =photo; 
         
        

    }

    public Integer getProduitid() {
        return produitid;
    }

    public String getType() {
        return type;
    }

    public String getCategorie() {
        return categorie;
    }

    public String getNom() {
        return nom;
    }

    public String getLibelle() {
        return libelle;
    }

    public String getVille() {
        return ville;
    }

    public String getPhoto() {
        return photo;
    }

    public void setProduitid(Integer produitid) {
        this.produitid = produitid;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
    
    
}
