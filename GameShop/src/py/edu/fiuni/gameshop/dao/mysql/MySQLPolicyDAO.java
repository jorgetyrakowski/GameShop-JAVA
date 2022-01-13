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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import py.edu.fiuni.gameshop.dao.PolicyDAO;
import py.edu.fiuni.gameshop.model.Policy;

/**
 * Gets the policy data access object on the data base and manages to perform
 * changes as called.
 */
public class MySQLPolicyDAO implements PolicyDAO {

    //gets connection 
    private DAOConnection connection = null;

    //statemets for mysql
    private final String UPDATE = "UPDATE gameshop.policy SET policy = ?, "
            + "return_time = ?, game_limit = ?,penalty_fee = ? WHERE idpolicy = 1";
    private final String GETGAMELIMIT = "SELECT game_limit FROM gameshop.policy WHERE idpolicy = ?";
    private final String GETDAYLIMIT = "SELECT return_time FROM gameshop.policy WHERE idpolicy = ?";
    private final String GETPENALTY = "SELECT penalty_fee FROM gameshop.policy WHERE idpolicy = ?";

    public MySQLPolicyDAO(DAOConnection dao) {
        this.connection = dao;
    }

    /**
     * Modifies values.
     *
     * @param p policy
     * @return true or false
     */
    @Override
    public boolean modify(Policy p) {
        PreparedStatement ps = null;
        Connection con = connection.getConexion();
        try {
            ps = con.prepareStatement(UPDATE);
            ps.setString(1, p.getObservations());
            ps.setInt(2, p.getDayLimit());
            ps.setInt(3, p.getLimit());
            ps.setDouble(4, p.getCharges());
            ps.execute();
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
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
     * @return int amount of games allowed.
     */
    public int getGameLimit() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = connection.getConexion();
        int limit = 0;
        try {
            ps = con.prepareStatement(GETGAMELIMIT);
            ps.setInt(1, 1);
            rs = ps.executeQuery();
            while (rs.next()) {
                limit = rs.getInt("game_limit");
            }
            return limit;

        } catch (SQLException ex) {
            Logger.getLogger(MySQLPolicyDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    System.out.println(ex);
                }

                if (ps != null) {
                    try {
                        ps.close();

                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }
                }
            }

        }
        return limit;

    }

    /**
     * @return int amount of days allowed.
     */
    public int getDayLimit() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = connection.getConexion();
        int limit = 0;
        try {
            ps = con.prepareStatement(GETDAYLIMIT);
            ps.setInt(1, 1);
            rs = ps.executeQuery();
            while (rs.next()) {
                limit = rs.getInt("return_time");
            }
            return limit;

        } catch (SQLException ex) {
            Logger.getLogger(MySQLPolicyDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    System.out.println(ex);
                }

                if (ps != null) {
                    try {
                        ps.close();

                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }
                }
            }

        }
        return limit;

    }

    /**
     * @return double penalty fee.
     */
    public double getPenaltyFee() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = connection.getConexion();
        double limit = 0;
        try {
            ps = con.prepareStatement(GETPENALTY);
            ps.setInt(1, 1);
            rs = ps.executeQuery();
            while (rs.next()) {
                limit = rs.getDouble("penalty_fee");
            }
            return limit;

        } catch (SQLException ex) {
            Logger.getLogger(MySQLPolicyDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    System.out.println(ex);
                }

                if (ps != null) {
                    try {
                        ps.close();

                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }
                }
            }

        }
        return limit;

    }
    
    //UNUSED

    @Override
    public boolean add(Policy m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean remove(Policy m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Policy getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
