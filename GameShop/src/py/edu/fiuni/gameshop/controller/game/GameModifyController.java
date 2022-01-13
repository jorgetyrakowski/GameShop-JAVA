/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.edu.fiuni.gameshop.controller.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import py.edu.fiuni.gameshop.dao.mysql.MySQLGameDAO;
import py.edu.fiuni.gameshop.model.Game;
import py.edu.fiuni.gameshop.view.game.PanelGames;
import py.edu.fiuni.gameshop.view.game.FrmModify;

/*
 *UNIVERSIDAD NACIONAL DE ITAPUA
 *PROJECT GAMESHOP
 *
 * 2020 - SEGUNDO SEMESTRE
 *
 *  Jorge Tyrakowski & Pamela Horn
 */

/**
 *
 * This class is in charge of controlling the modify games form and contains the
 * necessary methods to update it in the database
 */
public class GameModifyController implements ActionListener {

    private MySQLGameDAO dao;
    private PanelGames panel;
    private FrmModify frm;
    private Game game = null;

    public GameModifyController(PanelGames panel, FrmModify frm, MySQLGameDAO dao, Game game) {
        this.dao = dao;
        this.panel = panel;
        this.frm = frm;
        this.game = game;
        addListeners();

    }

    /**
     * This method is in charge of adding the Action listeners
     */
    private void addListeners() {
        frm.jbttClear.addActionListener(this);
        frm.jbttModify.addActionListener(this);
    }

    /**
     * This method determines the button that has been clicked and executes the
     * necessary methods.
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == frm.jbttClear) {
            clear();
        }
        if (ae.getSource() == frm.jbttModify) {
            modify();

        }
    }

    /**
     * Method in charge of cleaning the text fields
     */
    private void clear() {
        frm.txtName.setText(null);
        frm.txtCompany.setText(null);
        frm.txtGenre.setText(null);
        frm.txtConsole.setText(null);
        frm.txtDescription.setText(null);
        frm.txtPrice.setText(null);
    }

    /**
     *
     * This method is responsible for modifying the video game and updating it
     * in the database.
     *
     * @return true if the game has been modified false if the no game has been
     * modified
     */
    private boolean modify() {
        game.setName(frm.txtName.getText());
        game.setConsole(frm.txtConsole.getText());
        game.setCompany(frm.txtCompany.getText());
        game.setGenre(frm.txtGenre.getText());
        game.setDescription(frm.txtDescription.getText());
        game.setPrice(Double.valueOf(frm.txtPrice.getText()));

        if (dao.modify(game)) {
            panel.update();
            JOptionPane.showMessageDialog(null, "The game has been successfully modifycated");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "It was not possible to modify the game.");
            return false;
        }

    }

}
