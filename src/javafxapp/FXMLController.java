package javafxapp;

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

public class FXMLController implements Initializable {
    @FXML
    private TableColumn<Transporteur, Integer> IdColumn;

    @FXML
    private TableColumn<Transporteur, String> NomColumn;

    @FXML
    private TableColumn<Transporteur, String> PrenomColumn;

    @FXML
    private TableColumn<Transporteur, Integer> NumColumn;

    @FXML
    private TableColumn<Transporteur, String> PhotoColumn;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableView<Transporteur> table;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtNom;

    @FXML
    private TextField txtNum;

    @FXML
    private TextField txtPrenom;

    private Connection con;
    private PreparedStatement pst;
    private int myIndex;

    @FXML
    void Add(ActionEvent event) {
        int id, num_tel;
        id = Integer.parseInt(txtId.getText());
        num_tel = Integer.parseInt(txtNum.getText());
        String nom, prenom, photo;
        nom = txtNom.getText();
        prenom = txtPrenom.getText();
        photo = ""; // initialize photo

        try {
            pst = con.prepareStatement("insert into transporteur(id, nom, prenom, num_tel, photo) values (?, ?, ?, ?, ?)");
            pst.setInt(1, id);
            pst.setString(2, nom);
            pst.setString(3, prenom);
            pst.setInt(4, num_tel);
            pst.setString(5, photo);

            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ajout du transporteur");
            alert.setHeaderText("Ajout");
            alert.setContentText("Transporter Added!");
            alert.showAndWait();

            // clear text fields
            txtId.setText("");
            txtNom.setText("");
            txtPrenom.setText("");
            txtNum.setText("");
            txtId.requestFocus();
            table(); // refresh table
        } catch (SQLException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void Delete(ActionEvent event) {
        // TODO: implement delete functionality
    }

    @FXML
    void Update(ActionEvent event) {
        // TODO: implement update functionality
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Connect();
        table(); // populate table
    }

    private void Connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/tunitroc", "root", "");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

private void table() {
    Connect();
    ObservableList<Transporteur> transporteurList = FXCollections.observableArrayList();
}

}