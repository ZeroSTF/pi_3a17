/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunitrockhayri;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author kheir
 */
public class AcceuilController implements Initializable {

    @FXML
    private Button panier;
    @FXML
    private Button prod;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void redirectionPanier(ActionEvent event) {
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("Panierinterface.fxml"));
            Parent root = loader.load();
            panier.getScene().setRoot(root);
        }catch(IOException ex){
            System.out.println(ex);
        }
    }

    @FXML
    private void redirectionProduit(ActionEvent event) {
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("Produitinterface.fxml"));
            Parent root = loader.load();
            prod.getScene().setRoot(root);
        }catch(IOException ex){
            System.out.println(ex);
        }
    }
    
}
