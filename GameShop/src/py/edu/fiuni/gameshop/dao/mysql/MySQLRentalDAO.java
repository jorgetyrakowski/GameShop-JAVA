/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.edu.fiuni.gameshop.dao.mysql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import py.edu.fiuni.gameshop.dao.RentalDAO;
import py.edu.fiuni.gameshop.model.Rental;

/**
 *
 * @author Pamela Horn
 */
public class MySQLRentalDAO implements RentalDAO {

    private DAOConnection connection = null;
    private final String INSERT = "INSERT INTO rental (gameName, clientName, clientDNI, saleDate, returnDate) VALUES(?,?,?,?,?)";
    private final String GETDIFF = "SELECT DATEDIFF(now(), returnDate) as difference FROM rental where idRental = ?";
    String GET_BY_GAME_NAME = "SELECT idRental, gameName, clientName, clientDNI, saleDate, returnDate FROM rental WHERE gameName=?";
    private final String DELETE = "DELETE FROM rental WHERE gameName=?";

    public MySQLRentalDAO(DAOConnection con) {
        this.connection = con;
    }

    @Override
    public boolean add(Rental m) {
        PreparedStatement ps = null;
        Connection con = connection.getConexion();

        try {
            ps = con.prepareStatement(INSERT);
            ps.setString(1, m.getGameName());
            ps.setString(2, m.getClientName());
            ps.setString(3, m.getClientDNI());
            ps.setDate(4, m.getSaleDate());
            ps.setDate(5, m.getReturnDate());
            ps.execute();

            return true;
        } catch (SQLException e) {

            System.out.println(e);
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

    public Rental getByGameName(String gameName) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = connection.getConexion();
        Rental rental = null;

        try {
            ps = con.prepareStatement(GET_BY_GAME_NAME);
            ps.setString(1, gameName);
            rs = ps.executeQuery();

            if (rs.next()) {
                rental = this.convert(rs);
            }
        } catch (SQLException e) {

            System.out.println(e);
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
        return rental;
    }

    public int getDiff(int idRental) {

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = connection.getConexion();
        int diff = 0;

        try {
            ps = con.prepareStatement(GETDIFF);
            ps.setInt(1, idRental);
            rs = ps.executeQuery();
            while (rs.next()) {
                diff = rs.getInt("difference");
            }

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

        return diff;
    }

    public boolean hasDebt(String DNI) {
        Connection con = this.connection.getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String SELECT = "SELECT DATEDIFF(now(), returnDate) as difference FROM rental  where clientDNI = ?";
        try {
            ps = con.prepareStatement(SELECT);
            ps.setString(1, DNI);
            rs = ps.executeQuery();

            while (rs.next()) {

                if (Integer.parseInt(rs.getString("difference")) > 0) {
                    return true;
                }
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
                if (ps != null) {
                    try {
                        ps.close();

                    } catch (SQLException ex) {
                        Logger.getLogger(MySQLClientDAO.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

        }
        return false;
    }

    private Rental convert(ResultSet rs) throws SQLException {
        int idRental = rs.getInt("idRental");
        String gameName = rs.getString("gameName");
        String clientName = rs.getString("clientName");
        String clientDNI = rs.getString("clientDNI");
        Date saleDate = rs.getDate("saleDate");
        Date returnDate = rs.getDate("returnDate");

        Rental rental = new Rental(gameName, clientName, clientDNI, saleDate, returnDate);
        rental.setId(idRental);

        return rental;
    }

    @Override
    public boolean remove(Rental m) {
        PreparedStatement ps = null;
        Connection con = connection.getConexion();

        try {
            ps = con.prepareStatement(DELETE);
            ps.setString(1, m.getGameName());
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

    @Override
    public boolean modify(Rental m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Rental getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
