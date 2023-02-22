package javafxapp;

import com.mysql.cj.MysqlConnection;
import connection.MyConnection;
import java.awt.HeadlessException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableRow;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.InputStream;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static jdk.nashorn.internal.runtime.Debug.id;
import static org.omg.CORBA.AnySeqHelper.insert;
import javafx.scene.input.MouseEvent;
import javafxapp.Transporteur;


public class FXMLController implements Initializable {
   
    @FXML
    private TableColumn<Transporteur, Integer> IdColumn;

    @FXML
    private TableColumn<Transporteur, String> NomColumn;

    @FXML
    private TableColumn<Transporteur, String> PrenomColumn;

  @FXML
    private TableColumn<Transporteur, Integer> Num_telColumn;

    @FXML
    private TableColumn<Transporteur, String> PhotoColumn;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;
    
     @FXML
     private Button btnImage;

    @FXML
    private TableView<Transporteur> table;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtNom;
    
@FXML
    private TextField txtNum_tel;

    @FXML
    private TextField txtPrenom;
    
     @FXML
    private TextField txtPhoto;
     
     @FXML
private ImageView imageView;
     
      @FXML
    private Button btnParcourir;
   
    
    ObservableList<Transporteur> ListM;
    int index =-1;
    Connection con = null;
    ResultSet rs=null;
    PreparedStatement ps=null;
   


 @FXML
    void Add(ActionEvent event) {
        con=MyConnection.connectDb();
        String sql="insert into transporteur(id,nom,prenom,num_tel,photo) values(?,?,?,?,?)";
        try {
        ps=con.prepareStatement(sql);
        ps.setString(1,txtId.getText());
        ps.setString(2,txtNom.getText());
        ps.setString(3,txtPrenom.getText());
       ps.setString(4,txtNum_tel.getText());
        ps.setString(5,txtPhoto.getText());


ps.execute();
JOptionPane.showMessageDialog(null, "Transporteur a été ajouté avec succés");
        } catch(Exception e){
        JOptionPane.showMessageDialog(null,e);
        }
            UpdateTable();

    }

    @FXML
    void Delete(ActionEvent event) {
       con = MyConnection.connectDb();
       String sql="Delete FROM  `transporteur` WHERE id= ?";
       try{
           
       ps=  con.prepareStatement(sql);
       ps.setString(1, txtId.getText());
       ps.execute();
       JOptionPane.showMessageDialog(null, "delete transport");
                   UpdateTable();
       }
       catch   (HeadlessException | SQLException e) {
           JOptionPane.showMessageDialog(null, e);
        }

       }
 

       

    @FXML
    void Update(ActionEvent event) {
        try {
       
        con=MyConnection.connectDb();
      
            
String sql="update transporteur set id='"+txtId.getText()+"',nom='"+txtNom.getText()+"',prenom='"+txtPrenom.getText()+"',photo='"+txtPhoto.getText()+"'";
            ps=con.prepareStatement(sql);
            ps.execute();
            JOptionPane.showMessageDialog(null,"transporteur a été modifié avec succés");        
        } catch (Exception e) {
                       JOptionPane.showMessageDialog(null, e);

        }
            UpdateTable();

    }
    
    public void UpdateTable () {
    IdColumn.setCellValueFactory(new PropertyValueFactory<Transporteur, Integer>("id"));
        NomColumn.setCellValueFactory(new PropertyValueFactory<Transporteur, String>("nom"));
    PrenomColumn.setCellValueFactory(new PropertyValueFactory<Transporteur, String>("prenom"));
     Num_telColumn.setCellValueFactory(new PropertyValueFactory<Transporteur, Integer>("num_tel"));
    PhotoColumn.setCellValueFactory(new PropertyValueFactory<Transporteur, String>("photo"));
ListM =MyConnection.getTransporteurs();
table.setItems(ListM);    
    }   
    @FXML
private void Parcourir(ActionEvent event) {
    
}

@Override
public void initialize(URL url, ResourceBundle rb) {
    UpdateTable();
} 
@FXML
private void refresh() {
   
    }

@FXML
public void getSelected(MouseEvent event){
    index = table.getSelectionModel().getSelectedIndex();
    if(index<=-1){
    return;
    }
    try {
    txtId.setText(IdColumn.getCellData(index).toString());
    txtNom.setText(NomColumn.getCellData(index).toString());
    txtPrenom.setText(PrenomColumn.getCellData(index).toString());
   txtNum_tel.setText(Num_telColumn.getCellData(index).toString());
    txtPhoto.setText(PhotoColumn.getCellData(index).toString()); }
    catch (Exception e)
            {   
        JOptionPane.showMessageDialog(null, e);

    }
}
}