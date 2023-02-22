/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.User;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import utils.DBConnection;

/**
 *
 * @author ZeroS TF
 */
public class CRUDUser implements InterfaceCRUDUser{

    Connection TuniTrocDB = DBConnection.getConnection();
    
    @Override
    public void ajouterUser(User user) throws SQLException {
        
         // Check if the email is already in use
        String sql = "SELECT COUNT(*) FROM user WHERE email = ?";
        try ( PreparedStatement pstmt = TuniTrocDB.prepareStatement(sql)) {
            pstmt.setString(1, user.getEmail());
            try ( ResultSet rs = pstmt.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    throw new SQLException("Email address already in use.");
                }
            }
        }

        // generating a random string (salt)
        byte[] chars = new byte[7];
        new Random().nextBytes(chars);
        String salt = new String(chars, Charset.forName("US-ASCII"));
        user.setSalt(salt);

        PreparedStatement stmt = TuniTrocDB.prepareStatement("INSERT INTO user(email,pwd, nom, prenom, photo, num_tel, ville, valeur_fidelite, role, salt, token, etat) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        stmt.setString(1, user.getEmail());
        stmt.setString(2, hashPassword(user.getPwd(), salt));
        stmt.setString(3, user.getNom());
        stmt.setString(4, user.getPrenom());
        stmt.setString(5, user.getPhoto());
        stmt.setInt(6, user.getNum_tel());
        stmt.setString(7, user.getVille());
        stmt.setInt(8, user.getValeur_fidelite());
        stmt.setBoolean(9, user.isRole());
        stmt.setString(10, salt);
        stmt.setString(11, null);
        stmt.setString(12, User.EtatUser.INACTIF.toString());
        stmt.executeUpdate();
    }

    @Override
    public void modifierUser(User user, String email) throws SQLException {
        PreparedStatement stmt = TuniTrocDB.prepareStatement("UPDATE user SET email=?, pwd=?, nom=?, prenom=?, photo=?, num_tel=?, ville=?, valeur_fidelite=?, role=?, salt=?, token=?, etat=? WHERE email=?");
        stmt.setString(1, user.getEmail());
        stmt.setString(2, user.getPwd());
        stmt.setString(3, user.getNom());
        stmt.setString(4, user.getPrenom());
        stmt.setString(5, user.getPhoto());
        stmt.setInt(6,user.getNum_tel());
        stmt.setString(7, user.getVille());
        stmt.setInt(8, user.getValeur_fidelite());
        stmt.setBoolean(9, user.isRole());
        stmt.setString(10, user.getSalt());
        stmt.setString(11, user.getToken());
        stmt.setString(12, user.getEtat().toString());
        stmt.setString(13, email);
        stmt.executeUpdate();
    }

    @Override
    public void supprimerUser(String email) throws SQLException {
        PreparedStatement stmt = TuniTrocDB.prepareStatement("DELETE FROM user WHERE email=?");
        stmt.setString(1, email);
        stmt.executeUpdate();
    }

    @Override
    public List<User> afficherUsers() throws SQLException {
        List<User> userList = new ArrayList<>();
        Statement stmt = TuniTrocDB.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM user");
        while (rs.next()) {
            userList.add(getUserFromResultSet(rs));
        }
        System.out.println(userList);
        return userList;
    }
    
    public User getUserByEmail(String email) throws SQLException {
        PreparedStatement stmt = TuniTrocDB.prepareStatement("SELECT * FROM user WHERE email=?");
        stmt.setString(1, email);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return getUserFromResultSet(rs);
        } else {
            return null;
        }
    }
    
    public boolean login(String email, String password) throws SQLException {
        User user = getUserByEmail(email);
        if (user != null && user.getEtat() != User.EtatUser.ACTIF) {
            String hashedPassword = hashPassword(password, user.getSalt());
            return hashedPassword.equals(user.getPwd());
        } else {
            return false;
        }
    }

    public String setToken(String email) throws SQLException {
        User user = getUserByEmail(email);
        if (user != null && user.getEtat() != User.EtatUser.ACTIF) {
            String token = generateToken();
            user.setToken(token);
            user.setEtat(User.EtatUser.INACTIF);
            modifierUser(user,email);
            return token;
        } else {
            return null;
        }
    }

    public void logout(String email) throws SQLException {
        User user = getUserByEmail(email);
        if (user != null) {
            user.setToken(null);
            modifierUser(user,email);
        }
    }

    private User getUserFromResultSet(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setEmail(rs.getString("email"));
        user.setPwd(rs.getString("pwd"));
        user.setNom(rs.getString("nom"));
        user.setPrenom(rs.getString("prenom"));
        user.setPhoto(rs.getString("photo"));
        user.setNum_tel(rs.getInt("num_tel"));
        user.setVille(rs.getString("ville"));
        user.setValeur_fidelite(rs.getInt("valeur_fidelite"));
        user.setRole(rs.getBoolean("role"));
        user.setSalt(rs.getString("salt"));
        user.setToken(rs.getString("token"));
        user.setEtat(User.EtatUser.valueOf(rs.getString("etat")));
        return user;
    }
    
    private String hashPassword(String password, String salt) {
        // Implement a secure password hashing algorithm, e.g. bcrypt
        // simple SHA-256 hash
        String saltedPassword = salt + password;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(saltedPassword.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Could not hash password", e);
        }
    }

    private String generateToken() {
        // Generate a secure random token (simple UUID)
        return UUID.randomUUID().toString();
    }
}