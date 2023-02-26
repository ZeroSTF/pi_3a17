/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khayritunitroc;

import java.awt.Button;
import java.awt.TextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author kheir
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private AnchorPane main_form;

    @FXML
    private AnchorPane list_form;

    @FXML
    private Button boutique_btn;

    @FXML
    private Button produi_btn;

    @FXML
    private Button panier_btn;

    @FXML
    private AnchorPane boutique_form;

    @FXML
    private AnchorPane produit_form;

    @FXML
    private ImageView addproduit_view;

    @FXML
    private Button addproduit_btn;

    @FXML
    private TextField addproduit_title;

    @FXML
    private TextField addproduit_libelle;

    @FXML
    private ComboBox<?> addproduit_type;

    @FXML
    private ComboBox<?> addproduit_categ;

    @FXML
    private ComboBox<?> addproduit_ville;

    @FXML
    private Button ajtproduit_btn;

    @FXML
    private Button restproduit_btn;

    @FXML
    private Button suppproduit_btn;

    @FXML
    private Button modproduit_btn;

    @FXML
    private TableView<?> addproduit_table;

    @FXML
    private TableColumn<?, ?> addproduit_tabletitle;

    @FXML
    private TableColumn<?, ?> addproduit_tabletype;

    @FXML
    private TableColumn<?, ?> addproduit_tablecate;

    @FXML
    private TableColumn<?, ?> addproduit_tableville;

    @FXML
    private TextField searchproduit;

    @FXML
    private AnchorPane panier_form;
    
   

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    
}
