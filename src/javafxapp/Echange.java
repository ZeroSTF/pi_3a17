/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxapp;

public class Echange {
    private Integer id;
    private String etat;
     private Integer prod_s;
    private Integer prod_r;
    private Integer transporteurB;
    private Integer id_transporteur;

    
    public Echange() {
    }
    public Echange(Integer id, Integer prod_s, Integer prod_r, Integer transporteurB) {
        this.id = id;
        this.prod_s = prod_s;
        this.prod_r = prod_r;
        this.transporteurB = transporteurB;
        
    }
    
    
      public Echange(Integer id,String etat ,Integer prod_s, Integer prod_r, Integer transporteurB, Integer id_transporteur) {
        this.id = id;
        this.etat=etat;
        this.prod_s = prod_s;
        this.prod_r = prod_r;
        this.transporteurB = transporteurB;
        this.id_transporteur= this.id_transporteur;
        
    }

   
   


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Integer getProd_r() {
        return prod_r;
    }

    public void setProd_r(Integer prod_r) {
        this.prod_r = prod_r;
    }

    public Integer getProd_s() {
        return prod_s;
    }

    public void setProd_s(Integer prod_s) {
        this.prod_s = prod_s;
    }

    public Integer getTransporteurB() {
        return transporteurB;
    }

    public void setTransporteurB(Integer transporteurB) {
        this.transporteurB = transporteurB;
    }

    public Integer getId_transporteur() {
        return id_transporteur;
    }

    public void setId_transporteur(Integer id_transporteur) {
        this.id_transporteur = id_transporteur;
    }
    
    
    
    


    
    
}
