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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import py.edu.fiuni.gameshop.dao.ClientDAO;
import py.edu.fiuni.gameshop.model.Client;

/**
 * Gets the clients data access object on the data base and manages
 * to perform various changes as insert, update, remove, select as called. 
 */
public class MySQLClientDAO implements ClientDAO {

    // statements
    private final String INSERT = "INSERT INTO client(dni, name, surname, adress, email, phone, games, debt) VALUES(?,?,?,?,?,?,?,?)";
    private final String UPDATE = "UPDATE client SET name = ?, surname = ?,adress = ?,email = ?, phone = ?, debt = ? , games = ? WHERE dni = ?";
    private final String DELETE = "DELETE FROM client WHERE dni = ?";
    private final String GETDNI = "SELECT dni, name, surname, adress, email, phone, games, debt, games FROM client WHERE dni = ?";
    private final String GETDEBT = "SELECT debt FROM client WHERE dni = ?";
    //stablish connections
    private DAOConnection connection = null;

    public MySQLClientDAO(DAOConnection con) {
        this.connection = con;
    }

    /**
     * Adds client to the database table.
     * @param c client.
     * @return true or false
     */
    @Override
    public boolean add(Client c) {
        PreparedStatement stat = null;
        Connection con = connection.getConexion();
        try {
            stat = con.prepareStatement(INSERT);

            stat.setString(1, c.getDni());
            stat.setString(2, c.getName());
            stat.setString(3, c.getSurname());
            stat.setString(5, c.getAdress());
            stat.setString(4, c.getEmail());
            stat.setString(6, c.getNumber());
            stat.setBoolean(7, c.isDebt());
            stat.setInt(8, c.getGames());

            if (stat.executeUpdate() == 0) {
                JOptionPane.showMessageDialog(null, "Error obtaining client data");
            }
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(MySQLClientDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                    ex.printStackTrace();

                }

            }
        }
        return false;
    }

    /***
     * Removes or deletes client's data
     * @param c client
     * @return  true or false
     */
    @Override
    public boolean remove(Client c) {
        PreparedStatement ps = null;
        Connection con = connection.getConexion();
        try {
            ps = con.prepareStatement(DELETE);
            ps.setString(1, c.getDni());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }

    /**
     * Modifies client data
     * @param c client
     * @return true or false
     */
    @Override
    public boolean modify(Client c) {
        PreparedStatement ps = null;
        Connection con = connection.getConexion();

        try {
            ps = con.prepareStatement(UPDATE);

            ps.setString(1, c.getName());
            ps.setString(2, c.getSurname());
            ps.setString(3, c.getAdress());
            ps.setString(4, c.getEmail());
            ps.setString(5, c.getNumber());
            ps.setBoolean(6, c.isDebt());
            ps.setInt(7, c.getGames());
            ps.setString(8, c.getDni());
            ps.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {

                con.close();
            } catch (SQLException e) {

                System.out.println(e);
            }
        }
    }

    /**
     * Returns a Client Object by getting the client's dni
     * @param dni 
     */
    public Client getByDni(String dni) {
        PreparedStatement stat = null;
        ResultSet rs = null;
        Connection con = connection.getConexion();
        Client client = null;
        try {
            stat = con.prepareStatement(GETDNI);
            stat.setString(1, dni);
            rs = stat.executeQuery();
            if (rs.next()) {
                client = convert(rs);
            } else {
      
            }
        } catch (SQLException ex) {
            ex.printStackTrace();

        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(MySQLClientDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (stat != null) {
                    try {
                        stat.close();

                    } catch (SQLException ex) {
                        Logger.getLogger(MySQLClientDAO.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

        }
        return client;

    }

    /**
     * 
     * Returns true or false if the client (dni) owner has or not debts.
     * @param dni
     * 
     */
    public boolean isDebtbyDNI(String dni) {
        PreparedStatement stat = null;
        ResultSet rs = null;
        Connection con = connection.getConexion();
        boolean debt = false;
        try {
            stat = con.prepareStatement(GETDEBT);
            stat.setString(1, dni);
            rs = stat.executeQuery();
            while (rs.next()) {
                debt = rs.getBoolean("debt");
            }
            return debt;

        } catch (SQLException ex) {
            Logger.getLogger(MySQLClientDAO.class.getName()).log(Level.SEVERE, null, ex);
            return debt;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(MySQLClientDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (stat != null) {
                    try {
                        stat.close();

                    } catch (SQLException ex) {
                        Logger.getLogger(MySQLClientDAO.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

        }
    }

    /**
     * Converts to given model
     * @throws SQLException 
     */
    private Client convert(ResultSet rs) throws SQLException {

        String dni = rs.getString("DNI");
        String name = rs.getString("NAME");
        String surname = rs.getString("SURNAME");
        String adress = rs.getString("ADRESS");
        String email = rs.getString("EMAIL");
        String phone = rs.getString("PHONE");
        boolean debt = rs.getBoolean("DEBT");
        int games = rs.getInt("games");

        //sets client
        Client client = new Client();
        client.setDni(dni);
        client.setName(name);
        client.setSurname(surname);
        client.setEmail(email);
        client.setAdress(adress);
        client.setNumber(phone);
        client.setDebt(debt);
        client.setGames(games);
        return client;

    }

    /**
     * Unused.
     */
    @Override
    public Client getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
