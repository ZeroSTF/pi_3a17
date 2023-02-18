/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxapp;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Transporteur {

    private final IntegerProperty id;
    private final StringProperty nom;
    private final StringProperty prenom;
    private final IntegerProperty num_tel;
    private final StringProperty photo;
    
    public Transporteur() {
        id = new SimpleIntegerProperty(this, "id");
        nom = new SimpleStringProperty(this, "nom");
        prenom = new SimpleStringProperty(this, "prenom");
        num_tel = new SimpleIntegerProperty(this, "num_tel");
        photo = new SimpleStringProperty(this, "photo");        
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public int getId() {
        return id.get();
    }

    public void setId(int newId) {
        id.set(newId);
    }

    public StringProperty nomProperty() {
        return nom;
    }

    public String getNom() {
        return nom.get();
    }

    public void setNom(String newNom) {
        nom.set(newNom);
    }

    public StringProperty prenomProperty() {
        return prenom;
    }

    public String getPrenom() {
        return prenom.get();
    }

    public void setPrenom(String newPrenom) {
        prenom.set(newPrenom);
    }

    public StringProperty photoProperty() {
        return photo;
    }

    public String getPhoto() {
        return photo.get();
    }

    public void setPhoto(String newPhoto) {
        photo.set(newPhoto);
    }

    public IntegerProperty num_telProperty() {
        return num_tel;
    }

    public int getNum_tel() {
        return num_tel.get();
    }

    public void setNum_tel(int newNum_tel) {
        num_tel.set(newNum_tel);
    }
        
    @Override
    public String toString() {
        return String.format("%s[id=%d, nom=%s, prenom=%s, num_tel=%d, photo=%s]", 
            getClass().getSimpleName(), getId(), getNom(), getPrenom(), getNum_tel(), getPhoto());
    }
}
