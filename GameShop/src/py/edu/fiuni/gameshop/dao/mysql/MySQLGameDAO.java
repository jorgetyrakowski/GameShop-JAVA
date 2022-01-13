/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.edu.fiuni.gameshop.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import py.edu.fiuni.gameshop.dao.GameDAO;
import py.edu.fiuni.gameshop.model.Game;

/**
 *
 * @author Rafa
 */
public class MySQLGameDAO implements GameDAO {

    private final String INSERT = "INSERT INTO games (name, console, company, genre, description, price, availability) VALUES(?,?,?,?,?,?,?)";
    private final String UPDATE = "UPDATE games SET name=?, console=?, company=?, genre=?, description=?, price=?, availability WHERE id=?";
    private final String DELETE = "DELETE FROM games WHERE id=? ";
    private DAOConnection connection;

    public MySQLGameDAO(DAOConnection dao) {
        this.connection = dao;
    }

    @Override
    public boolean add(Game game) {
        PreparedStatement ps = null;
        Connection con = connection.getConexion();

        try {
            ps = con.prepareStatement(INSERT);
            ps.setString(1, game.getName());
            ps.setString(2, game.getConsole());
            ps.setString(3, game.getCompany());
            ps.setString(4, game.getGenre());
            ps.setString(5, game.getDescription());
            ps.setDouble(6, game.getPrice());
            ps.setBoolean(7, true);
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
    public boolean remove(Game game) {
        PreparedStatement ps = null;
        Connection con = connection.getConexion();

        try {
            ps = con.prepareStatement(DELETE);
            ps.setInt(1, game.getId());
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
    public boolean modify(Game game) {
        PreparedStatement ps = null;
        Connection con = connection.getConexion();
        String UPDATE1 = "UPDATE games SET name=?, console=?, company=?, genre=?, description=?, price=?, availability = ? WHERE id=?";
        
        try {
            ps = con.prepareStatement(UPDATE1);

            ps.setString(1, game.getName());
            ps.setString(2, game.getConsole());
            ps.setString(3, game.getCompany());
            ps.setString(4, game.getGenre());
            ps.setString(5, game.getDescription());
            ps.setDouble(6, game.getPrice());
            ps.setBoolean(7, game.isAvailable());
            ps.setInt(8, game.getId());
            ps.execute();

            return true;
        } catch (SQLException e) {

            System.out.println(e);
            e.toString();
            return false;
        } finally {
            try {

                con.close();
            } catch (SQLException e) {

                System.out.println(e);
            }
        }
    }

    public Game searchByName(String name) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = connection.getConexion();
        Game game = null;
        String sql = "SELECT id, name, console, genre, description, company, price, availability FROM games WHERE name=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, name);
            rs = ps.executeQuery();

            if (rs.next()) {
                game = this.convert(rs);
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
        return game;
    }

    private Game convert(ResultSet rs) throws SQLException {
        int id = Integer.parseInt(rs.getString("id"));
        String name = rs.getString("name");
        String console = rs.getString("console");
        String genre = rs.getString("genre");
        String description = rs.getString("description");
        String company = rs.getString("company");
        boolean availability = rs.getBoolean("availability");
        Double price = rs.getDouble("price");

        Game game = new Game();
        game.setId(id);
        game.setName(name);
        game.setCompany(company);
        game.setConsole(console);
        game.setDescription(description);
        game.setPrice(price);
        game.setGenre(genre);
        game.setAvailable(availability);

        return game;
    }


    @Override
    public Game getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
