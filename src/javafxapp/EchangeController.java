/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxapp;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfWriter;
import connection.MyConnection;
import static connection.MyConnection.connectDb;
import java.awt.HeadlessException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;




public class EchangeController implements Initializable {
      @FXML
    private TableColumn<Echange, String> EtatColumn;

    @FXML
    private TableColumn<Echange, Integer> IdColumn;

    @FXML
    private TableColumn<Echange, Integer> Prod_rColumn;

    @FXML
    private TableColumn<Echange, Integer> Prod_sColumn;

    @FXML
    private TableColumn<Echange, Integer> TransporteurBColumn;

    @FXML
    private TableColumn<Echange, Integer> TransporteurColumn;
   
    @FXML
    private ImageView imageView;
    
     @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;
    
      @FXML
    private TableView<Echange> table_e;

    @FXML
    private ComboBox<String> combo_e;

    @FXML
    private ComboBox<Integer> combo_t;
    
     @FXML
    private TextField txtId;
    
   private Stage stage;
    private Scene scene;
    private Parent root;
     ObservableList<Echange> ListE;
    int index =-1;
    Connection con = null;
    ResultSet rs=null;
    PreparedStatement ps=null;
   
    
     @FXML
       public void Archive(ActionEvent event) throws IOException{
    Parent root = FXMLLoader.load(getClass().getResource("Archive.fxml"));
    stage =(Stage)((Node)event.getSource()).getScene().getWindow();
       scene = new Scene(root);
        stage.setScene(scene);
        stage.show();   
    }
    
     public void switchToTransporteur(ActionEvent event) throws IOException{
    Parent root = FXMLLoader.load(getClass().getResource("FXML.fxml"));
    stage =(Stage)((Node)event.getSource()).getScene().getWindow();
       scene = new Scene(root);
        stage.setScene(scene);
        stage.show();   
     }
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MyConnection.startDeletionTask();
        ObservableList<String> list= FXCollections.observableArrayList("par default", "Archivé", "Non Archivé");
        combo_e.setItems(list);
        Connection con = connectDb();
          try {
              ResultSet rs= con.createStatement().executeQuery("select id from transporteur");
              ObservableList list_t = FXCollections.observableArrayList();
              while(rs.next()){
list_t.add(Integer.valueOf(rs.getString("id"))); 
              } 
              combo_t.setItems(list_t);
          } catch(NumberFormatException | SQLException exception){
              Logger.getLogger(EchangeController.class.getName()).log(Level.SEVERE, null, exception);}
          UpdateTable();
          }
   
  

        public void UpdateTable () {
    IdColumn.setCellValueFactory(new PropertyValueFactory<Echange, Integer>("id"));
        EtatColumn.setCellValueFactory(new PropertyValueFactory<Echange, String>("etat"));
    Prod_sColumn.setCellValueFactory(new PropertyValueFactory<Echange, Integer>("produit_s"));
     Prod_rColumn.setCellValueFactory(new PropertyValueFactory<Echange, Integer>("produit_r"));
    TransporteurBColumn.setCellValueFactory(new PropertyValueFactory<Echange, Integer>("transporteurB"));
        TransporteurColumn.setCellValueFactory(new PropertyValueFactory<Echange, Integer>("id_transporteur"));

ListE =MyConnection.getEchanges();
table_e.setItems(ListE);    
    }   
    
    
    
    @FXML
    void Update(ActionEvent event) {
        try {
       
        con=MyConnection.connectDb();
      
            
String sql = "UPDATE echange SET etat ='"+combo_e.getValue()+"' , id_transporteur = '"+combo_t.getValue()+"'where id = '"+txtId.getText()+"'";
            ps=con.prepareStatement(sql);
            ps.execute();
            JOptionPane.showMessageDialog(null,"Echange a été modifié avec succés");        
        } catch (Exception e) {
                       JOptionPane.showMessageDialog(null, e);

        }
            UpdateTable();

    }
    
    @FXML
    void Delete(ActionEvent event) {
       con = MyConnection.connectDb();
       String sql="Delete FROM  `echange` WHERE id= ?";
       try{
           
       ps=  con.prepareStatement(sql);
       ps.setString(1, txtId.getText());
       ps.execute();
       JOptionPane.showMessageDialog(null, "delete echange");
                   UpdateTable();
       }
       catch   (HeadlessException | SQLException e) {
           JOptionPane.showMessageDialog(null, e);
        }
       }
    @FXML
public void getSelected(MouseEvent event){
    index = table_e.getSelectionModel().getSelectedIndex();
    if(index<=-1){
    return;
    }
    try {
        txtId.setText(IdColumn.getCellData(index).toString());
    combo_e.setValue(EtatColumn.getCellData(index).toString());
   combo_t.setValue(TransporteurColumn.getCellData(index));

    }
    catch (Exception e)
            {   
        JOptionPane.showMessageDialog(null, e);

    }
}
    @FXML
void PDF(ActionEvent event) {
    Connection con = MyConnection.connectDb();
    try {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("C:/Users/azizn/OneDrive/Documents/pdfs/pdfEchange.pdf"));
        document.open();

        // Retrieve data from the database
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM echange");

        // Loop through the data and add it to the document
        while (rs.next()) {
            int id = rs.getInt("id");
            String etat = rs.getString("etat");
             int prd_s = rs.getInt("produit_s");
               int prd_r = rs.getInt("produit_r");
            int transporteur = rs.getInt("id_transporteur");
           
        
            // Create a paragraph with the data and add it to the document
            Paragraph paragraph = new Paragraph("ID: " + id + "\nEtat: " + etat + "\nExpéditeur de produit: " + prd_s + "\n\"Récepteur de produit: " + prd_r + "\nTransporteur:" + transporteur + "\n--------------------------------------------------------------------------------------------");
            document.add(paragraph);
        }

        document.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
  

}
    
    
     
        
        
        
        
     
    

