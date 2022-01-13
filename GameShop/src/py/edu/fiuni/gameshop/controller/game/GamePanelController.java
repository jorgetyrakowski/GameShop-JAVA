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
import py.edu.fiuni.gameshop.view.game.GameInfo;
import py.edu.fiuni.gameshop.view.game.PanelGames;
import py.edu.fiuni.gameshop.view.game.FrmGame;
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
 * @author Rafa
 */

/**
 * This class is in charge of controlling the video game panel and contains the 
 * necessary methods for the different processes.
 */
public class GamePanelController implements ActionListener{
    private MySQLGameDAO dao;
    private PanelGames panel;
    
    public GamePanelController(PanelGames panel, MySQLGameDAO dao){
        this.panel = panel;
        this.dao = dao;
        addListeners();
        
    }
    
    /**
     * This method is in charge of adding the Action listeners
     */
    private void addListeners(){
       panel.jbttDeleteGame.addActionListener(this);
       panel.jbttAddGame.addActionListener(this);
       panel.jbttModify.addActionListener(this);
       panel.jbttSeeMore.addActionListener(this);
       panel.jbttSearchByName.addActionListener(this);  
    }
    
     /**
     * This method determines the button that has been clicked and executes the
     * necessary methods.
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==panel.jbttDeleteGame){
            deleteGame();
        }
        if(ae.getSource() == panel.jbttAddGame){
            addGame();
        }
        if(ae.getSource() == panel.jbttModify){
            modifyGame();
        }
        if(ae.getSource()== panel.jbttSeeMore){
            seeMore();
        }
        if(ae.getSource() == panel.jbttSearchByName){
            search();
        }
        
         
    }
    
    /**
     * This method is in charge of delimiting a video game and updating it in the database.
     */
    private void deleteGame(){
        if(dao.remove(dao.searchByName(panel.getSelectedGame()))){
            panel.update();
            JOptionPane.showMessageDialog(null, "The game has been successfully deleted");
        } else {
            JOptionPane.showMessageDialog(null, "Could not remove the video game");
        }
    }
    
    /**
     * This method is in charge of executing the form to add games.
     */
    private void addGame(){
         FrmGame form = new FrmGame(this.panel, this.dao);
    }
    
    /**
     * This method is in charge of executing the form to modify video games.
     */
    private void modifyGame(){
        if(panel.getSelectedGame().length() > 1){
            FrmModify form = new FrmModify(this.panel, this.dao,  dao.searchByName(panel.getSelectedGame()));
        }
    }
    
    /**
     * This method is in charge of opening a window with all the information of the selected video game.
     */
    private void seeMore(){
        if(!panel.getSelectedGame().isEmpty()){
            GameInfo gameInfo = new GameInfo(dao.searchByName(panel.getSelectedGame()));
        }
    }
    
    /**
     *  method is in charge of updating the video game table.
     */
    private void search(){
        panel.updateSearch();
    }
}
