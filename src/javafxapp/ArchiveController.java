/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxapp;

import connection.MyConnection;
import static connection.MyConnection.connectDb;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author azizn
 */
public class ArchiveController implements Initializable {

      @FXML
    private TableColumn<Echange, String> EtatColumn;

    @FXML
    private TableColumn<Echange, Integer> IdColumn;

    @FXML
    private TableColumn<Echange,Integer> Prod_rColumn;

    @FXML
    private TableColumn<Echange, Integer> Prod_sColumn;

    @FXML
    private TableColumn<Echange, Integer> TransporteurBColumn;

    @FXML
    private TableColumn<Echange, Integer> TransporteurColumn;

    @FXML
    private TableView<Echange> table_a;

        ObservableList<Echange> ListA;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
       MyConnection.startDeletionTask();
         Connection con = connectDb();
          IdColumn.setCellValueFactory(new PropertyValueFactory<Echange, Integer>("id"));
        EtatColumn.setCellValueFactory(new PropertyValueFactory<Echange, String>("etat"));
    Prod_sColumn.setCellValueFactory(new PropertyValueFactory<Echange, Integer>("produit_s"));
     Prod_rColumn.setCellValueFactory(new PropertyValueFactory<Echange, Integer>("produit_r"));
    TransporteurBColumn.setCellValueFactory(new PropertyValueFactory<Echange, Integer>("transporteurB"));
        TransporteurColumn.setCellValueFactory(new PropertyValueFactory<Echange, Integer>("id_transporteur"));

ListA =MyConnection.getArchive();
table_a.setItems(ListA);    
   }    
    
}
