package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import entities.User;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import services.CRUDUser;

public class TableUserController implements Initializable {

    //CONSTANT STUFF TO COPY
    public User currentUser;

    private ObservableList<User> userList = FXCollections.observableArrayList();

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
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
    private ImageView img_user;

    @FXML
    void click_disconnect(MouseEvent event) throws SQLException {
        CRUDUser sa = new CRUDUser();
        sa.logout(currentUser.getEmail());
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
        } else if (btn.equals(btn_events)) {
            btn_events.setStyle("-fx-background-color: rgb(232, 171, 0); -fx-text-fill: white;");
        }
    }

    @FXML
    void mExit(MouseEvent event) {
        Button btn = (Button) event.getSource();
        if (btn.equals(btn_users)) {
            btn_users.setStyle("-fx-background-color: rgb(252, 215, 69); -fx-text-fill: white;");
        } else if (btn.equals(btn_events)) {
            btn_events.setStyle("-fx-background-color: rgb(252, 215, 69); -fx-text-fill: white;");
        }
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////

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
    private TableColumn<User, byte[]> photo_user;

    @FXML
    private TableColumn<User, String> num_user;

    @FXML
    private TableColumn<User, String> ville_user;

    @FXML
    private TableColumn<User, String> fidelite_user;

    @FXML
    private TableColumn<User, String> role_user;

    @FXML
    private TableColumn<User, String> etat_user;

    @FXML
    private Button btn_ajouter;
    @FXML
    private Button btn_modifier;
    @FXML
    private Button btn_supprimer;

    @FXML
    private TextField searchBox;

    @FXML
    void click_events(MouseEvent event) {
        TableEventController tableEventController = new TableEventController();
        tableEventController.setCurrentUser(currentUser);

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
    void click_supp_user(MouseEvent event) {
        // Récupération de l'utilisateur sélectionné
        User selectedUser = table_users.getSelectionModel().getSelectedItem();
        if (selectedUser == null) {
            // Aucun utilisateur sélectionné, afficher un message d'avertissement
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Aucun utilisateur sélectionné");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner un utilisateur à supprimer.");
            alert.showAndWait();
        } else {
            // Afficher une boîte de dialogue de confirmation avant de supprimer l'utilisateur
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation de suppression");
            alert.setHeaderText(null);
            alert.setContentText("Êtes-vous sûr de vouloir supprimer l'utilisateur sélectionné ?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    CRUDUser cr = new CRUDUser();
                    cr.supprimerUser(selectedUser.getEmail());
                    table_users.getItems().remove(selectedUser);
                    Alert confirmation = new Alert(AlertType.INFORMATION, "L'utilisateur a été supprimé avec succès.");
                    confirmation.showAndWait();
                } catch (SQLException e) {
                    Alert error = new Alert(AlertType.ERROR, "Une erreur s'est produite lors de la suppression de l'utilisateur.");
                    error.showAndWait();
                    e.printStackTrace();
                }
            }
        }
    }

    @FXML
    void click_modif_user(MouseEvent event) {

        User selectedUser = table_users.getSelectionModel().getSelectedItem();
        if (selectedUser == null) {
            // Aucun utilisateur sélectionné, afficher un message d'avertissement
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Aucun utilisateur sélectionné");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner un utilisateur à modifier.");
            alert.showAndWait();
        } else {
            ModifUserController modifusercontroller = new ModifUserController();
            modifusercontroller.setCurrentUser(currentUser);

            modifusercontroller.setUser(selectedUser);

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/ModifUser.fxml"));

                // set the controller instance
                loader.setController(modifusercontroller);

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

    @FXML
    void click_users(MouseEvent event) {
        CRUDUser sa = new CRUDUser();

        List<User> userListFromDatabase = null;
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
        photo_user.setCellFactory(column -> {
            return new TableCell<User, byte[]>() {
                private final ImageView imageView = new ImageView();

                @Override
                protected void updateItem(byte[] item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setGraphic(null);
                    } else {
                        // Load the image and resize it
                        Image image = new Image(new ByteArrayInputStream(item));
                        imageView.setImage(image);
                        imageView.setFitWidth(50); // set the width of the ImageView
                        imageView.setFitHeight(50); // set the height of the ImageView
                        setGraphic(imageView);
                    }
                }
            };
        });

        num_user.setCellValueFactory(new PropertyValueFactory<>("num_tel"));
        ville_user.setCellValueFactory(new PropertyValueFactory<>("ville"));
        fidelite_user.setCellValueFactory(new PropertyValueFactory<>("valeur_fidelite"));
        role_user.setCellValueFactory(cellData -> {
            Boolean isAdmin = cellData.getValue().isRole();
            String role = isAdmin ? "Admin" : "Client";
            return new ReadOnlyStringWrapper(role);
        });

        table_users.setItems(userList);

    }

    @FXML
    void click_ajout_user(MouseEvent event) {
        AjoutUserController ajoutusercontroller = new AjoutUserController();
        ajoutusercontroller.setCurrentUser(currentUser);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/AjoutUser.fxml"));

            // set the controller instance
            loader.setController(ajoutusercontroller);

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
    public void initialize(URL url, ResourceBundle rb) {
        label_nomUser.setText(currentUser.getPrenom() + " " + currentUser.getNom());
        InputStream inputStream = new ByteArrayInputStream(currentUser.getPhoto());
        Image image = new Image(inputStream);
        img_user.setImage(image);
        img_user.setPreserveRatio(true);

        CRUDUser sa = new CRUDUser();

        List<User> userListFromDatabase = null;
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
        photo_user.setCellFactory(column -> {
            return new TableCell<User, byte[]>() {
                private final ImageView imageView = new ImageView();

                @Override
                protected void updateItem(byte[] item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setGraphic(null);
                    } else {
                        // Load the image and resize it
                        Image image = new Image(new ByteArrayInputStream(item));
                        imageView.setImage(image);
                        imageView.setFitWidth(50); // set the width of the ImageView
                        imageView.setFitHeight(50); // set the height of the ImageView
                        setGraphic(imageView);
                    }
                }
            };
        });

        num_user.setCellValueFactory(new PropertyValueFactory<>("num_tel"));
        ville_user.setCellValueFactory(new PropertyValueFactory<>("ville"));
        fidelite_user.setCellValueFactory(new PropertyValueFactory<>("valeur_fidelite"));
        role_user.setCellValueFactory(cellData -> {
            Boolean isAdmin = cellData.getValue().isRole();
            String role = isAdmin ? "Admin" : "Client";
            return new ReadOnlyStringWrapper(role);
        });
        etat_user.setCellValueFactory(new PropertyValueFactory<>("etat"));

        table_users.setItems(userList);

        //RECHERCHE//////////////////////////
        // Set up search box listener
        searchBox.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                userList.clear();
                userList.addAll(sa.recherche(newValue));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
}
