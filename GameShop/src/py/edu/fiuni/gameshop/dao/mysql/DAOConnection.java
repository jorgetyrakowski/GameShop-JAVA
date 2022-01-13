/**
 *UNIVERSIDAD NACIONAL DE ITAPUA                    
 *PROJECT GAMESHOP                                     
 *                                                  
 * 2020 - SEGUNDO SEMESTRE                          
 *                                                  
 *  Jorge Tyrakowski & Pamela Horn                  
 **/

package py.edu.fiuni.gameshop.dao.mysql;

//imports
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * Gets the direct path of the connections and the driver manager.
 * 
 * */
 
public class DAOConnection {
    
    private final String gameshop = "gameshop";
    private final String user = "gameshop";
    private final String password = "Vz2H2!_fm4db";
    private final String url = "jdbc:mysql://den1.mysql1.gear.host/gameshop";
    private Connection con = null;

    /**
     * 
     * Returns the connections to the path from the database.
     */
    public Connection getConexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(url, user, password);

        } catch (SQLException e) {
            System.out.println(e);
        } catch (ClassNotFoundException ex) { 
            Logger.getLogger(DAOConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

        return con;
    }
    
    
}
