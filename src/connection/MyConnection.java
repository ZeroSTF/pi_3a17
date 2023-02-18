package connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author azizn
 */
public class MyConnection {
    String url="jdbc:mysql://localhost:3306/tunitroc";
    String login="root";
    String pwd="";
    public Connection cnx;
    public static MyConnection instance;

    public MyConnection() {
        try {
         cnx = DriverManager.getConnection(url, login, pwd);
            System.out.println("Connexion établie!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Connection getCnx() {
        return cnx;
    }
    
    public static MyConnection getInstance(){
        if(instance == null){
            instance = new MyConnection();
        }
         return instance;
    }
     
    
    
    
}
