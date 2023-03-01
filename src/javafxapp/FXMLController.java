package javafxapp;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.mysql.cj.MysqlConnection;
import connection.MyConnection;
import java.awt.HeadlessException;
import java.io.ByteArrayOutputStream;
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
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static jdk.nashorn.internal.runtime.Debug.id;
import static org.omg.CORBA.AnySeqHelper.insert;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafxapp.Transporteur;
import javax.swing.ImageIcon;
import javafx.stage.FileChooser;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn.CellDataFeatures;
import java.sql.Blob;
import javafx.beans.value.ObservableValue;
import javafx.util.Callback;


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
    private TextField filterField;
     
       @FXML
    private Label label;
     
      @FXML
    private Button btnParcourir;
   
    
      private Stage stage;
    private Scene scene;
    private Parent root;
public File file;
private FileInputStream fis;
        ObservableList<Transporteur> ListM;
    int index =-1;
    Connection con = null;
    ResultSet rs=null;
    PreparedStatement ps=null;
     public ImageIcon Format= null;
    String s;
    byte[] photo=null;
    ImageView imageView;
    
    

    
    @FXML
       public void switchToEchange(ActionEvent event) throws IOException{
    Parent root = FXMLLoader.load(getClass().getResource("Echange.fxml"));
    stage =(Stage)((Node)event.getSource()).getScene().getWindow();
       scene = new Scene(root);
        stage.setScene(scene);
        stage.show();   
    }
       
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
       
            
String sql="update transporteur set nom='"+txtNom.getText()+"',prenom='"+txtPrenom.getText()+"',num_tel='"+txtNum_tel.getText()+"',photo='"+txtPhoto.getText()+"'where id ='"+txtId.getText()+"'";
            ps=con.prepareStatement(sql);
            ps.execute(); 
            JOptionPane.showMessageDialog(null,"transporteur a été modifié avec succés");        
        } catch (Exception e) {
                       JOptionPane.showMessageDialog(null, e);

        }
            UpdateTable();

    }
    
    public void UpdateTable ()  {
    IdColumn.setCellValueFactory(new PropertyValueFactory<Transporteur, Integer>("id"));
        NomColumn.setCellValueFactory(new PropertyValueFactory<Transporteur, String>("nom"));
    PrenomColumn.setCellValueFactory(new PropertyValueFactory<Transporteur, String>("prenom"));
    Num_telColumn.setCellValueFactory(new PropertyValueFactory<Transporteur, Integer>("num_tel"));
    PhotoColumn.setCellValueFactory(new PropertyValueFactory<>("photo"));
ListM =MyConnection.getTransporteurs();
table.setItems(ListM);    
    }   
    
    
    
    @FXML
 void Parcourir(ActionEvent event) {
    
    MyConnection con= new MyConnection();
   FileChooser chooser = new FileChooser();
chooser.setTitle("Choisir une image ");
FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Images", "*.jpg", "*.jpeg", "*.png", "*.gif");
chooser.getExtensionFilters().add(filter);
file = chooser.showOpenDialog(null);
if (file != null) {
    Image image = new Image(file.toURI().toString());
    double labelWidth = label.getWidth();
    double labelHeight = label.getHeight();
     imageView = new ImageView(image);
    imageView.setPreserveRatio(true);
    imageView.setFitWidth(labelWidth);
    imageView.setFitHeight(labelHeight);
    label.setGraphic(imageView);
    txtPhoto.setText(file.getPath());
}
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
    txtNom.setText(NomColumn.getCellData(index));
    txtPrenom.setText(PrenomColumn.getCellData(index));
   txtNum_tel.setText(Num_telColumn.getCellData(index).toString());
 txtPhoto.setText(PhotoColumn.getCellData(index));
    }  catch (Exception e)
            {   
        JOptionPane.showMessageDialog(null, e);

    }
}
 @FXML
void PDF(ActionEvent event) {
    Connection con = MyConnection.connectDb();
    try {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("C:/Users/azizn/OneDrive/Documents/pdfs/pdf.pdf"));
        document.open();

        // Retrieve data from the database
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM transporteur");

        // Loop through the data and add it to the document
        while (rs.next()) {
            int id = rs.getInt("id");
            String nom = rs.getString("nom");
            String prenom = rs.getString("prenom");
            int num = rs.getInt("num_tel");
            Blob photo = rs.getBlob("photo");
        
            // Create a paragraph with the data and add it to the document
            Paragraph paragraph = new Paragraph("ID: " + id + "\nNom: " + nom + "\nPrénom: " + prenom + "\nNumero de telephone: " + num + "\nPhoto:" + photo + "\n--------------------------------------------------------------------------------------------");
            document.add(paragraph);
        }

        document.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
}