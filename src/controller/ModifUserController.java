/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.CRUDUser;

/**
 * FXML Controller class
 *
 * @author ZeroS TF
 */
public class ModifUserController implements Initializable {
    //CONSTANT STUFF TO COPY
    
    public String username;
    public String Photo;
    public String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    @FXML
    private Label label_nomUser;

    @FXML
    private Button btn_users;

    @FXML
    private Button btn_events;
        
    @FXML
    private Button btn_disconnect;
    
     @FXML
    void click_disconnect(MouseEvent event) throws SQLException {
        CRUDUser sa = new CRUDUser();
        User u=sa.getUserByEmail(email);
        u.setEtat(User.EtatUser.INACTIF);
        sa.modifierUser(u, email);
        LoginUIController loginUIController = new LoginUIController();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/LoginUI.fxml"));
                
                // set the controller instance
                loader.setController(loginUIController);
                
                Parent root = loader.load();
                
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                
                Scene scene = new Scene(root);

                stage.setScene(scene);
                stage.show();
                
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
    }
    
    @FXML
    void mEnter(MouseEvent event) {
        Button btn = (Button) event.getSource();
        if (btn.equals(btn_users)) {
        btn_users.setStyle("-fx-background-color: rgb(232, 171, 0); -fx-text-fill: white;");
        }
        else if (btn.equals(btn_events)) {
        btn_events.setStyle("-fx-background-color: rgb(232, 171, 0); -fx-text-fill: white;");
        }
    }

    @FXML
    void mExit(MouseEvent event) {
        Button btn = (Button) event.getSource();
        if (btn.equals(btn_users)) {
        btn_users.setStyle("-fx-background-color: rgb(252, 215, 69); -fx-text-fill: white;");
        }
        else if (btn.equals(btn_events)) {
        btn_events.setStyle("-fx-background-color: rgb(252, 215, 69); -fx-text-fill: white;");
        }
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////

    @FXML
    private TextField txt_email;
    @FXML
    private PasswordField txt_pwd;
    @FXML
    private TextField txt_nom;
    @FXML
    private TextField txt_prenom;
    @FXML
    private TextField txt_numtel;
    @FXML
    private ComboBox<String> cbx_ville;
    @FXML
    private ComboBox<String> cbx_role;
    @FXML
    private Button btn_modif;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        label_nomUser.setText(this.getUsername());
        
        cbx_ville.setItems(FXCollections.observableArrayList(
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
        cbx_role.setItems(FXCollections.observableArrayList(
                "Admin",
                "Client"));
    }    

    @FXML
    private void click_modif(MouseEvent event) {
    }



    @FXML
    void click_users(MouseEvent event) {
        TableUserController tableUserController = new TableUserController();
        tableUserController.setUsername(username);
        tableUserController.setEmail(email);

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/TableUser.fxml"));
                
                // set the controller instance
                loader.setController(tableUserController);
                
                Parent root = loader.load();
                
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                
                Scene scene = new Scene(root);

                stage.setScene(scene);
                stage.show();
                
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
    }

    @FXML
    private void click_events(MouseEvent event) {
        TableEventController tableEventController = new TableEventController();
            tableEventController.setUsername(username);
            tableEventController.setEmail(email);

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/TableEvent.fxml"));
                
                // set the controller instance
                loader.setController(tableEventController);
                
                Parent root = loader.load();
                
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                
                Scene scene = new Scene(root);

                stage.setScene(scene);
                stage.show();
                
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
    }
    
    
}
