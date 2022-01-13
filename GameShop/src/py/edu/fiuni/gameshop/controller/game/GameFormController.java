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
import py.edu.fiuni.gameshop.view.game.FrmGame;

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
 * This class is in charge of controlling the games form and contains the
 * necessary methods to add a game to the store
 */
public class GameFormController implements ActionListener {

    private MySQLGameDAO dao;
    private PanelGames panel;
    private FrmGame frm;
    private Game game = null;

    public GameFormController(PanelGames panel, FrmGame frm, MySQLGameDAO dao) {
        this.dao = dao;
        this.frm = frm;
        this.panel = panel;
        addListeners();

    }

    /**
     * This method is in charge of adding the Action listeners
     */
    private void addListeners() {
        frm.jbttClear.addActionListener(this);
        frm.jbttSave.addActionListener(this);
    }

    /**
     * This method determines the button that has been clicked and executes the
     * necessary methods.
     */
    @Override
    public void actionPerformed(ActionEvent ae) {

        //If the save button has been clicked
        if (ae.getSource() == frm.jbttSave) {
            addGame(getModel());
        }

        //If the clean button has been clicked
        if (ae.getSource() == frm.jbttClear) {
            clean();
        }
    }

    /**
     *
     * This method is in charge of obtaining the information of the video game
     * and adding it to the database.
     *
     * @param game the game that will be added to the database.
     */
    public void addGame(Game game) {
        game.setName(frm.txtName.getText());
        game.setConsole(frm.txtConsole.getText());
        game.setCompany(frm.txtCompany.getText());
        game.setGenre(frm.txtGenre.getText());
        game.setDescription(frm.txtDescription.getText());
        game.setPrice(Double.parseDouble(frm.txtPrice.getText()));

        if (dao.add(game)) {
            JOptionPane.showMessageDialog(null, "The game has been successfully added");
            panel.update();
            clean();
        } else {
            JOptionPane.showMessageDialog(null, "The game could not be added");
        }
    }

    /**
     * Method in charge of returning the model that will be used to add the
     * games.
     *
     * @return model that will be used to add the games.
     */
    public Game getModel() {
        if (game == null) {
            game = new Game();
        }
        return game;
    }

    /**
     *
     * Method in charge of cleaning the text fields
     */
    public void clean() {
        frm.txtName.setText(null);
        frm.txtCompany.setText(null);
        frm.txtGenre.setText(null);
        frm.txtConsole.setText(null);
        frm.txtDescription.setText(null);
        frm.txtPrice.setText(null);
    }

}
