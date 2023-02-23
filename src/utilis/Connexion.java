/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author waelb
 */
public class Connexion {
    String url="jdbc:mysql://localhost:3306/tunitroc";
    String login="root";
    String pwd="";
    private Connection cnx;
    private static Connexion instance;
    
    private Connexion(){
        try{
        cnx = DriverManager.getConnection(url,login,pwd);
        System.out.println("Connexion etablie");
        }
    
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        }

    public Connection getCnx() {
        return cnx;
    }
    public static Connexion getInstance(){
        if(instance == null){
            instance = new Connexion();
        }
            return instance;
    }         
}