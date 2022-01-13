/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.edu.fiuni.gameshop.controller.rental;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import py.edu.fiuni.gameshop.dao.mysql.MySQLGameDAO;
import py.edu.fiuni.gameshop.model.Game;
import py.edu.fiuni.gameshop.view.game.GameInfo;
import py.edu.fiuni.gameshop.view.rental.GameSelect;

/*
 *UNIVERSIDAD NACIONAL DE ITAPUA
 *PROJECT GAMESHOP
 *
 * 2020 - SEGUNDO SEMESTRE
 *
 *  Jorge Tyrakowski & Pamela Horn
 */

/**
 * This class is in charge of controlling the advantage to manually select a video game
 */
public class GameSelectController implements ActionListener {

    private MySQLGameDAO dao;
    private GameSelect panel;

    public GameSelectController(GameSelect panel, MySQLGameDAO dao) {
        this.panel = panel;
        this.dao = dao;

    }

    /**
     * This method is in charge of adding the Action listeners
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == panel.jbttSeeMore) {
            seeMore();
        }

        if (ae.getSource() == panel.jbttSelect) {
            selectGame();
        }
    }

    /**
     * This method is in charge of opening a window with all the information of the selected video game.
     */
    private void seeMore() {
        if (!panel.getSelectedGame().isEmpty()) {
            GameInfo gameInfo = new GameInfo(dao.searchByName(panel.getSelectedGame()));
        }
    }

    
    /**
     * This method is in charge of selecting the video game that will be rented.
     */
    private void selectGame() {
        if (!panel.getSelectedGame().isEmpty()) {
            Game game = dao.searchByName(panel.getSelectedGame());
            JOptionPane.showMessageDialog(null, "Search successful.");
            panel.panel.txtGameSearch.setText(game.getName());
            panel.panel.txtGame.setText(game.getName());
            panel.panel.txtPrice.setText(String.valueOf(game.getPrice()));
            panel.panel.txtState.setText(game.getAvailability());
            panel.panel.setGame(game);
        }
    }
}
