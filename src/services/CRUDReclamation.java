/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Reclamation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.DBConnection;

/**
 *
 * @author Hedi
 */
public class CRUDReclamation implements InterfaceCRUDReclamation{
    Connection TuniTrocDB = DBConnection.getConnection();
    @Override
    public void ajouterReclamation(Reclamation reclamation) throws SQLException {      
        PreparedStatement stmt = TuniTrocDB.prepareStatement("INSERT INTO reclamation(id_userS, id_userR, cause,etat) VALUES(?, ?, ?, ?)");
        stmt.setInt(1, reclamation.getId_userS());
        stmt.setInt(2, reclamation.getId_userR());
        stmt.setString(3, reclamation.getCause());
        stmt.setBoolean(4, reclamation.IsEtat());
        stmt.executeUpdate();
    }

    @Override
    public void modifierReclamation(Reclamation reclamation, int id) throws SQLException {
        PreparedStatement stmt = TuniTrocDB.prepareStatement("UPDATE reclamation SET id_userS=?, id_userR=?, cause=?, etat=? WHERE id=?");
        stmt.setInt(1, reclamation.getId_userS());
        stmt.setInt(2, reclamation.getId_userR());
        stmt.setString(3, reclamation.getCause());
        stmt.setBoolean(4, reclamation.IsEtat());
        stmt.setInt(5, id);
        stmt.executeUpdate();
    }

    @Override
    public void supprimerReclamation(int id) throws SQLException {
        PreparedStatement stmt = TuniTrocDB.prepareStatement("DELETE FROM reclamation WHERE id=?");
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }

    @Override
    public List<Reclamation> afficherReclamations() throws SQLException {
        List<Reclamation> reclamations = new ArrayList<>();
        Statement stmt = TuniTrocDB.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM reclamation");
        while (rs.next()) {
            int id = rs.getInt("id");
            int id_userS = rs.getInt("id_userS");
            int id_userR = rs.getInt("id_userR");
            String cause = rs.getString("cause");
            boolean etat= rs.getBoolean("etat");
            Reclamation reclamation = new Reclamation(id, id_userS, id_userR, cause, etat);
            reclamations.add(reclamation);
        }
        System.out.println(reclamations);
        return reclamations;
    }
    
}