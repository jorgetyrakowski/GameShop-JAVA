/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.edu.fiuni.gameshop.controller.rental;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JOptionPane;
import py.edu.fiuni.gameshop.dao.mysql.MySQLClientDAO;
import py.edu.fiuni.gameshop.dao.mysql.MySQLGameDAO;
import py.edu.fiuni.gameshop.dao.mysql.MySQLPolicyDAO;
import py.edu.fiuni.gameshop.dao.mysql.MySQLRentalDAO;
import py.edu.fiuni.gameshop.dao.mysql.MySQLRentalDetailDAO;
import py.edu.fiuni.gameshop.model.Client;
import py.edu.fiuni.gameshop.model.Game;
import py.edu.fiuni.gameshop.model.Rental;
import py.edu.fiuni.gameshop.model.RentalDetail;
import py.edu.fiuni.gameshop.view.rental.GameSelect;
import py.edu.fiuni.gameshop.view.rental.PanelRental;
import py.edu.fiuni.gameshop.view.tools.ViewController;

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
 * This class is in charge of controlling the panel that is used to rent a video
 * game, it contains all the methods to carry it out.
 *
 */
public class RentalController implements ActionListener {

    private PanelRental panel;
    private MySQLClientDAO cdao;
    private MySQLGameDAO gdao;
    private MySQLPolicyDAO pdao;
    private MySQLRentalDAO rdao;
    private MySQLRentalDetailDAO rddao;

    public RentalController(PanelRental panel, MySQLClientDAO cdao, MySQLGameDAO gdao, MySQLPolicyDAO pdao, MySQLRentalDAO rdao, MySQLRentalDetailDAO rddao) {
        this.panel = panel;
        this.cdao = cdao;
        this.gdao = gdao;
        this.pdao = pdao;
        this.rdao = rdao;
        this.rddao = rddao;
    }

    /**
     * This method determines the button that has been clicked and executes the
     * necessary methods.
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        //If the search client button has been clicked
        if (ae.getSource() == this.panel.jbttClientSearch) {
            searchClient();
            verifyIfHasDebt();
        }

        //If the game client button has been clicked
        if (ae.getSource() == this.panel.jbttGameSearch) {
            searchGame();
        }

        //If the back button has been clicked
        if (ae.getSource() == this.panel.jbttBack) {
            back();
        }

        //If the confrim days to rent button has been clicked
        if (ae.getSource() == this.panel.jbttConfirmDaysToRent) {
            confirmDaysToRent();
        }

        //If the confirm button has been clicked
        if (ae.getSource() == this.panel.jbttConfirm) {
            if (confirm()) {
                panel.update();
                updateGameAvailability();
                updateClientGames();
                JOptionPane.showMessageDialog(null, "The game " + panel.getGame().getName() + " has been successfully rented by " + panel.getClient().getName() + " " + panel.getClient().getSurname());
                panel.clean();

            }
        }

        //If the total cost button has been clicked
        if (ae.getSource() == this.panel.jbttTotal) {
            setTotal();
        }

        //If the cancel button has been clicked
        if (ae.getSource() == this.panel.jbttCancel) {
            cancel();
        }

    }

    /**
     * Method in charge of returning to the menu
     */
    private void back() {
        ViewController.backToMenu(panel);
    }

    /**
     * This method is in charge of searching and selecting a client in the
     * database
     */
    private void searchClient() {
        String dni = panel.txtClientDNISearch.getText();
        if (dni.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Enter the client's ID.");
        } else {
            Client client = cdao.getByDni(dni);
            if (client != null) {
                JOptionPane.showMessageDialog(null, "Search successful.");
                panel.txtClientDNISearch.setText(client.getDni());
                panel.txtClientNameSurname.setText(client.getName() + " " + client.getSurname());
                panel.jlblTable.setText("Games that are being rented by ".concat(client.getName() + " " + client.getSurname()));
                panel.setClient(client);
            } else {
                JOptionPane.showMessageDialog(null, "The DNI does not belong to any registered client.");
            }
        }
    }

    /**
     * This method is in charge of searching and selecting a game in the
     * database
     */
    private void searchGame() {
        String name = panel.txtGameSearch.getText();
        int r;
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Enter the game's name.");
        } else {
            Game game = gdao.searchByName(name);
            if (game != null) {
                JOptionPane.showMessageDialog(null, "Search successful.");
                panel.txtGameSearch.setText(game.getName());
                panel.txtGame.setText(game.getName());
                panel.txtPrice.setText(String.valueOf(game.getPrice()));
                panel.txtState.setText(game.getAvailability());
                panel.setGame(game);
            } else {
                r = JOptionPane.showConfirmDialog(panel, "The game is not registered, do you want to manually search for it?");
                if (r == 0) {
                    GameSelect form = new GameSelect(panel);
                }
            }
        }
    }

    /**
     * This method is in charge of confirming the rental days and verifying if
     * the limit of days does not exceed
     *
     * @return true if the rental days have been confirmed false if the rent
     * days limit has been exceeded.
     */
    private boolean confirmDaysToRent() {
        if (!panel.txtDaysToRent.getText().isEmpty()) {
            int days = Integer.parseInt(panel.txtDaysToRent.getText());
            int limit = pdao.getDayLimit();
            if (days > limit && days > 0) {
                JOptionPane.showMessageDialog(null, "The number of deadlines to rent a game is " + limit);
                return false;
            } else {
                panel.setDaysToRent(days);
                panel.setReturnDate(days);
                setTotal();
            }
        }
        return true;
    }

    /**
     * This method is in charge of confirming the total cost of the rent
     */
    private void setTotal() {
        double totalCost = panel.calculateTotalCost();
        panel.setTotalCost(totalCost);
        panel.txtTotalCost.setText(String.valueOf(totalCost));
    }

    /**
     *
     * This method is in charge of verifying if the client and the video game
     * meet all the conditions to carry out the rent.
     *
     * Terms: - The game, the client and the rental days must be confirmed. -
     * The game must be available. - Client must be debt free. - The client must
     * not exceed the limit of games rented.
     *
     * @return
     */
    private boolean confirm() {
        if (!panel.isReady()) {
            return false;
        } else if (!panel.getGame().isAvailable()) {
            JOptionPane.showMessageDialog(null, "The game is not available");
            return false;
        } else if (panel.getClient().isDebt()) {
            JOptionPane.showMessageDialog(null, "The client has an outstanding debt.");
            return false;
        } else if (panel.getClient().getGames() >= pdao.getGameLimit()) {
            JOptionPane.showMessageDialog(null, "The client has an outstanding debt.");
            return false;
        }

        Rental rental = createRental();
        RentalDetail detail = createRentalDetail(rental);
        if (rdao.add(rental) && rddao.add(detail)) {
            return true;
        }
        return false;
    }

    /**
     * Method in charge of creating a rent to send it to the database.
     *
     * @return the rental.
     */
    private Rental createRental() {
        String gameName = panel.getGame().getName();
        String clientSurname = panel.getClient().getSurname();
        String clientDNI = panel.getClient().getDni();
        Date saleDate = panel.getCurrentDate();
        Date returnDate = panel.getReturnDate();

        Rental rental = new Rental(gameName, clientSurname, clientDNI, saleDate, returnDate);
        return rental;
    }

    /**
     *
     * Method in charge of creating the rental detail to add it to the records
     * table
     *
     * @param rental the rental.
     * @return the rental detail to add it to the records table.
     */
    private RentalDetail createRentalDetail(Rental rental) {
        String clientName = panel.getClient().getName();
        double totalCost = panel.calculateTotalCost();

        RentalDetail detail = new RentalDetail(clientName, rental.getClientName(), rental.getClientDNI(), rental.getGameName(), totalCost, rental.getSaleDate(), rental.getReturnDate());
        return detail;
    }

    /**
     * Method in charge of verifying if the client has a debt.
     */
    private void verifyIfHasDebt() {
        if (panel.getClient() != null) {
            if (panel.getClient().getGames() > 0 && !panel.getClient().isDebt()) {
                if (rdao.hasDebt(panel.getClient().getDni())) {
                    panel.getClient().setDebt(true);
                    cdao.modify(panel.getClient());
                }
            }
            panel.txtDebts.setText(panel.getClient().getDebtToString());
        }
    }

    /**
     * Updates the status of the video game to non available.
     */
    private void updateGameAvailability() {
        panel.getGame().setAvailable(false);
        gdao.modify(panel.getGame());
    }

    /**
     * Increase the client's rented games
     */
    private void updateClientGames() {
        panel.getClient().rentAGame();
        cdao.modify(panel.getClient());
    }

    /**
     * cancel the rent
     */
    private void cancel() {
        panel.clean();
    }

}
