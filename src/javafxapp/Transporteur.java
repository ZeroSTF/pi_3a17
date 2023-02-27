package javafxapp;

import java.sql.Blob;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

public class Transporteur {

    private Integer id;
    private String nom;
    private String prenom;
    private Integer num_tel;
    private byte[]  photo;

    public Transporteur(Integer id, String nom, String prenom, Integer num_tel, byte[] photo) {
    this.id =  id;
    this.nom = nom;
    this.prenom = prenom;
    this.num_tel = num_tel;
    this.photo = photo;
}

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
     public int getNum() {
        return num_tel;
    }

    public void setNum(Integer num_tel) {
        this.num_tel = num_tel;
    }
    
    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
    
}
