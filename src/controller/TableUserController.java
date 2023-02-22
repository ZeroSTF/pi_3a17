package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.CRUDUser;

public class TableUserController implements Initializable{
    
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
    private TableView<User> table_users;

    @FXML
    private TableColumn<User, String> id_user;

    @FXML
    private TableColumn<User, String> email_user;

    @FXML
    private TableColumn<User, String> pwd_user;

    @FXML
    private TableColumn<User, String> nom_user;

    @FXML
    private TableColumn<User, String> prenom_user;

    @FXML
    private TableColumn<User, String> photo_user;

    @FXML
    private TableColumn<User, String> num_user;

    @FXML
    private TableColumn<User, String> ville_user;

    @FXML
    private TableColumn<User, String> fidelite_user;

    @FXML
    private TableColumn<User, String> role_user;

    @FXML
    private Button btn_ajout_user;

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
        btn_users.setStyle("-fx-background-color: rgb(252, 186, 3); -fx-text-fill: white;");
        }
        else if (btn.equals(btn_events)) {
        btn_events.setStyle("-fx-background-color: rgb(252, 186, 3); -fx-text-fill: white;");
        }
    }
    
    @FXML
    void click_events(MouseEvent event) {
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
    

    @FXML
    void click_users(MouseEvent event) {      
        CRUDUser sa = new CRUDUser();
    
    List<User> userListFromDatabase=null;
        try {
            userListFromDatabase = sa.afficherUsers();
        } catch (SQLException ex) {
            Logger.getLogger(TableUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
ObservableList<User> userList = FXCollections.observableArrayList();
userList.addAll(userListFromDatabase);

id_user.setCellValueFactory(new PropertyValueFactory<>("id"));
email_user.setCellValueFactory(new PropertyValueFactory<>("email"));
pwd_user.setCellValueFactory(new PropertyValueFactory<>("pwd"));
nom_user.setCellValueFactory(new PropertyValueFactory<>("nom"));
prenom_user.setCellValueFactory(new PropertyValueFactory<>("prenom"));
photo_user.setCellValueFactory(new PropertyValueFactory<>("photo"));
num_user.setCellValueFactory(new PropertyValueFactory<>("num_tel"));
ville_user.setCellValueFactory(new PropertyValueFactory<>("ville"));
fidelite_user.setCellValueFactory(new PropertyValueFactory<>("valeur_fidelite"));
role_user.setCellValueFactory(new PropertyValueFactory<>("role"));

table_users.setItems(userList);

    }
    
    @FXML
    void click_ajout_user(MouseEvent event) {
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    label_nomUser.setText(this.getUsername());
    
    CRUDUser sa = new CRUDUser();
    
    List<User> userListFromDatabase=null;
        try {
            userListFromDatabase = sa.afficherUsers();
        } catch (SQLException ex) {
            Logger.getLogger(TableUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
ObservableList<User> userList = FXCollections.observableArrayList();
userList.addAll(userListFromDatabase);

id_user.setCellValueFactory(new PropertyValueFactory<>("id"));
email_user.setCellValueFactory(new PropertyValueFactory<>("email"));
pwd_user.setCellValueFactory(new PropertyValueFactory<>("pwd"));
nom_user.setCellValueFactory(new PropertyValueFactory<>("nom"));
prenom_user.setCellValueFactory(new PropertyValueFactory<>("prenom"));
photo_user.setCellValueFactory(new PropertyValueFactory<>("photo"));
num_user.setCellValueFactory(new PropertyValueFactory<>("num_tel"));
ville_user.setCellValueFactory(new PropertyValueFactory<>("ville"));
fidelite_user.setCellValueFactory(new PropertyValueFactory<>("valeur_fidelite"));
role_user.setCellValueFactory(new PropertyValueFactory<>("role"));

table_users.setItems(userList);
    }
}