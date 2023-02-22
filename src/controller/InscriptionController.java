/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.User;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import services.CRUDUser;

/**
 * FXML Controller class
 *
 * @author Firas
 */
public class InscriptionController implements Initializable {

    @FXML
    private TextField TF_email;

    @FXML
    private TextField TF_mdp;

    @FXML
    private TextField TF_nom;

    @FXML
    private TextField TF_prenom;

    @FXML
    private TextField TF_num;

    @FXML
    private ComboBox<String> villes_box;

    @FXML
    private Button Button_inscrire;

    @FXML
    private Button button_annuler;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        villes_box.setItems(FXCollections.observableArrayList(
                "Ariana",
                "Beja",
                "Ben Arous",
                "Bizerte",
                "Gabes",
                "Gafsa",
                "Jendouba",
                "Kairouan",
                "Kasserine",
                "Kebili",
                "Kef",
                "Mahdia",
                "Manouba",
                "Médenine",
                "Monastir",
                "Nabeul",
                "Sfax",
                "Sidi Bouzid",
                "Siliana",
                "Sousse",
                "Tataouine",
                "Tozeur",
                "Tunis",
                "Zaghouan"));
    }

    @FXML
    private void ON_inscrire_clicked(ActionEvent event) throws SQLException {
        CRUDUser sa = new CRUDUser();
        User u = new User("","","","","",0,"",0,false);
        u.setEmail(TF_email.getText());
        u.setPwd(TF_mdp.getText());
        u.setNom(TF_nom.getText());
        u.setPrenom(TF_prenom.getText());
        u.setNum_tel(Integer.parseInt(TF_num.getText()));
        u.setVille(villes_box.getValue());

        sa.ajouterUser(u);
    }

    @FXML
    private void on_annuler_clicked(ActionEvent event) {
        
        TF_mdp.setText("");
        TF_nom.setText("");
        TF_email.setText("");
        TF_prenom.setText("");
        TF_num.setText("");

    }

}
