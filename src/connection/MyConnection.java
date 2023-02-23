package connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafxapp.Transporteur;
import javax.swing.JOptionPane;

/**
 *
 * @author azizn
 */
public class MyConnection {
   Connection con=null;
   
   public static Connection connectDb(){
   try {
   Class.forName("com.mysql.jdbc.Driver");
   Connection con=(Connection) DriverManager.getConnection("jdbc:mysql://localhost/tunitroc","root", "");
  JOptionPane.showMessageDialog(null,"Connection etablie");
   return con;
   } catch(Exception e) {
   JOptionPane.showMessageDialog(null, e);
   return null;
   }
   }
  public static ObservableList<Transporteur> getTransporteurs() {
      ObservableList<Transporteur> list=FXCollections.observableArrayList();
        Connection con = connectDb();

      try {
          PreparedStatement ps =(PreparedStatement) con.prepareStatement("select * from transporteur");
          ResultSet rs= ps.executeQuery();
          while(rs.next()) {
          list.add(new Transporteur (Integer.parseInt(rs.getString("id")), rs.getString("nom"),  rs.getString("prenom"), Integer.parseInt(rs.getString("num_tel")), rs.getString("photo")));
          }
           
      }catch(Exception e) {
             JOptionPane.showMessageDialog(null, e);

      }
      return list;
  } 
}
