/**
 *UNIVERSIDAD NACIONAL DE ITAPUA
 *PROJECT GAMESHOP
 *
 * 2020 - SEGUNDO SEMESTRE
 *
 *  Jorge Tyrakowski & Pamela Horn
 * */

package py.edu.fiuni.gameshop.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import py.edu.fiuni.gameshop.dao.RentalDetailDAO;
import py.edu.fiuni.gameshop.model.RentalDetail;


/**
 * Gets the rental data access object on the data base and manages to perform
 * insertions as called. This is used on the reports.
 */
public class MySQLRentalDetailDAO implements RentalDetailDAO {

    private DAOConnection connection;
    private final String INSERT = "INSERT INTO rental_details (client_name, client_surname, client_dni, game_name, rent_date, return_date, game_price) VALUES(?,?,?,?,?,?,?)";

    public MySQLRentalDetailDAO(DAOConnection con) {
        this.connection = con;
    }

    /**
     * Adds to table
     * @param m detal
     * @return true or false.
     */
    @Override
    public boolean add(RentalDetail m) {
        PreparedStatement ps = null;
        Connection con = connection.getConexion();

        try {
            ps = con.prepareStatement(INSERT);
            ps.setString(1, m.getClientName());
            ps.setString(2, m.getClientSurname());
            ps.setString(3, m.getClientDNI());
            ps.setString(4, m.getGameName());
            ps.setDate(5, m.getSaleDate());
            ps.setDate(6, m.getReturnDate());
            ps.setDouble(7, m.getGamePrice());
            ps.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    
    //UNUSED
    @Override
    public boolean remove(RentalDetail m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean modify(RentalDetail m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RentalDetail getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
