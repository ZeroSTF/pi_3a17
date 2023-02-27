package connection;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Timer;
import java.util.TimerTask;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafxapp.Echange;
import javafxapp.Transporteur;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MyConnection {
    private static final long DELAY = 15 * 24 * 60 * 60 * 1000; // 15 days in milliseconds
    Connection con = null;

    public static Connection connectDb() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/tunitroc", "root", "");
            JOptionPane.showMessageDialog(null, "Connection etablie");
            return con;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    public static ObservableList<Transporteur> getTransporteurs() {
        ObservableList<Transporteur> list = FXCollections.observableArrayList();
        Connection con = connectDb();

        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement("select * from transporteur");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Transporteur(rs.getInt("id"), rs.getString("nom"),
                        rs.getString("prenom"), rs.getInt("num_tel"), rs.getBytes("photo")));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        }
        return list;
    }

     public static ObservableList<Echange> getEchanges() {
    ObservableList<Echange> list_e = FXCollections.observableArrayList();
    Connection con = connectDb();

    try {
        PreparedStatement ps = (PreparedStatement) con.prepareStatement("select id, etat, produit_s, produit_r, transporteurB, id_transporteur from echange");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list_e.add(new Echange(rs.getInt("id"), rs.getString("etat"),
                       rs.getInt("produit_s"),rs.getInt("produit_r"),rs.getInt("transporteurB"),rs.getInt("id_transporteur")));
           
        }

        // Delete the values from the "panier" table
        PreparedStatement deleteStmt = con.prepareStatement("DELETE FROM panier");
        deleteStmt.executeUpdate();

        // close all resources
        rs.close();
        deleteStmt.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return list_e;
}
     
  
  


    public static void startDeletionTask() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Connection con = connectDb();
                if (con != null) {
                    try {
                        PreparedStatement selectStmt = con.prepareStatement("SELECT id, etat FROM echange");
                        ResultSet rs = selectStmt.executeQuery();
                        PreparedStatement deleteStmt = con.prepareStatement("DELETE FROM echange WHERE id = ?");
                        while (rs.next()) {
                            String etat = rs.getString("etat");
                            if (etat.equals("par defaut")) {
                                int id = rs.getInt("id");
                                deleteStmt.setInt(1, id);
                                deleteStmt.executeUpdate();
                                continue; // skip adding to list if row was deleted
                            }
                        }

                        // close all resources
                        rs.close();
                        selectStmt.close();
                        deleteStmt.close();
                        con.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Connection not established");
                }
            }
        }, DELAY, DELAY);
    }
    
    
    
      public static ObservableList<Echange> getArchive() {
    ObservableList<Echange> list_a = FXCollections.observableArrayList();
    Connection con = connectDb();

    try {
PreparedStatement ps = con.prepareStatement("SELECT id, etat, produit_s, produit_r, transporteurB, id_transporteur FROM echange WHERE etat = ?");
ps.setString(1, "Archivé");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list_a.add(new Echange(rs.getInt("id"), rs.getString("etat"),
                       rs.getInt("produit_s"),rs.getInt("produit_r"),rs.getInt("transporteurB"),rs.getInt("id_transporteur")));
           
        }

        

        // close all resources
        rs.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return list_a;
}
    
   
    
    
    
    
    
    
    
    
    
}
