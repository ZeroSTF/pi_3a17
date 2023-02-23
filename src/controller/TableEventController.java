/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Evenement;
import entities.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.CRUDEvenement;
import services.CRUDUser;

/**
 * FXML Controller class
 *
 * @author ZeroS TF
 */


public class TableEventController implements Initializable{

    public String username;
    public String photo;
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
    private Button btn_ajout_event;

    @FXML
    private Button btn_modifier;
    
    @FXML
    private Button btn_disconnect;

    @FXML
    private TableView<Evenement> table_events;

    @FXML
    private TableColumn<Evenement, String> id_event;

    @FXML
    private TableColumn<Evenement, String> nom_event;

    @FXML
    private TableColumn<Evenement, String> description_event;

    @FXML
    private TableColumn<Evenement, String> dd_event;

    @FXML
    private TableColumn<Evenement, String> df_event;


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
    
    @FXML
    void click_events(MouseEvent event) {
        CRUDEvenement sa = new CRUDEvenement();
    
    List<Evenement> eventsListFromDatabase=null;
        try {
            eventsListFromDatabase = sa.afficherEvenements();
        } catch (SQLException ex) {
            Logger.getLogger(TableEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
ObservableList<Evenement> eventList = FXCollections.observableArrayList();
eventList.addAll(eventsListFromDatabase);

id_event.setCellValueFactory(new PropertyValueFactory<>("id"));
nom_event.setCellValueFactory(new PropertyValueFactory<>("nom"));
description_event.setCellValueFactory(new PropertyValueFactory<>("description"));
dd_event.setCellValueFactory(new PropertyValueFactory<>("date_d"));
df_event.setCellValueFactory(new PropertyValueFactory<>("date_f"));

table_events.setItems(eventList);
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
    void click_modif_event(MouseEvent event) {
        ModifEventController modifeventcontroller = new ModifEventController();
            modifeventcontroller.setUsername(username);
            modifeventcontroller.setEmail(email);

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/ModifEvent.fxml"));
                
                // set the controller instance
                loader.setController(modifeventcontroller);
                
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
    void click_ajout_event(MouseEvent event) {
         AjoutEventController ajouteventcontroller = new AjoutEventController();
            ajouteventcontroller.setUsername(username);
            ajouteventcontroller.setEmail(email);

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/AjoutEvent.fxml"));
                
                // set the controller instance
                loader.setController(ajouteventcontroller);
                
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        label_nomUser.setText(this.getUsername());
    
    CRUDEvenement sa = new CRUDEvenement();
    
    List<Evenement> eventsListFromDatabase=null;
        try {
            eventsListFromDatabase = sa.afficherEvenements();
        } catch (SQLException ex) {
            Logger.getLogger(TableEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
ObservableList<Evenement> eventList = FXCollections.observableArrayList();
eventList.addAll(eventsListFromDatabase);

id_event.setCellValueFactory(new PropertyValueFactory<>("id"));
nom_event.setCellValueFactory(new PropertyValueFactory<>("nom"));
description_event.setCellValueFactory(new PropertyValueFactory<>("description"));
dd_event.setCellValueFactory(new PropertyValueFactory<>("date_d"));
df_event.setCellValueFactory(new PropertyValueFactory<>("date_f"));

table_events.setItems(eventList);
    }
    }

