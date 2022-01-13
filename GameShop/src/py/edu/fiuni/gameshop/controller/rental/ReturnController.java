/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.edu.fiuni.gameshop.controller.rental;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.Calendar;
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
import py.edu.fiuni.gameshop.view.rental.PanelReturnGames;
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
 * This class is in charge of controlling the video games return panel, it
 * contains the methods to carry out the processes
 *
 * @author Rafa
 */
public class ReturnController implements ActionListener {

    private MySQLRentalDetailDAO rddao;
    private MySQLRentalDAO rdao;
    private MySQLClientDAO cdao;
    private MySQLGameDAO gdao;
    private MySQLPolicyDAO pdao;
    private PanelReturnGames panel;

    public ReturnController(MySQLRentalDAO rdao, MySQLRentalDetailDAO rddao, MySQLClientDAO cdao, MySQLGameDAO gdao, MySQLPolicyDAO pdao, PanelReturnGames panel) {
        this.rdao = rdao;
        this.rddao = rddao;
        this.cdao = cdao;
        this.gdao = gdao;
        this.pdao = pdao;
        this.panel = panel;

    }

    /**
     * This method is in charge of adding the Action listeners
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.panel.jbttBack) {
            ViewController.backToMenu(panel);
        }

        if (ae.getSource() == this.panel.jbttReturnGame) {
            if (returnGame()) {
                panel.update();
                JOptionPane.showMessageDialog(null, "The game has been successfully returned.");
            } else {

            }
        }

        if (ae.getSource() == this.panel.jbttSearchClient) {
            searchClient();
        }
    }

    /**
     * This method is in charge of searching and selecting a client in the
     * database
     */
    private boolean searchClient() {
        String dni = panel.txtClientDNI.getText();
        if (dni.isEmpty()) {

            JOptionPane.showMessageDialog(null, "Enter the client's ID.");
            return false;
        } else {

            Client client = cdao.getByDni(dni);
            if (client == null) {
                JOptionPane.showMessageDialog(null, "The DNI does not belong to any registered client.");
                return false;
            } else {
                panel.setClientDNI(client.getDni());
                panel.setClient(client);
                panel.update();
                JOptionPane.showMessageDialog(null, "The client has been found successfully");
                return true;
            }
        }
    }

    /**
     * Method in charge to verify if the client has a debt and then make the
     * return of a video game.
     *
     * @return true if the return has been made false if the return could not be
     * made
     */
    private boolean returnGame() {
        Rental rental = rdao.getByGameName(panel.getSelectedGame());
        if (rental != null) {
            Game game = gdao.searchByName(panel.getSelectedGame());
            int days = rdao.getDiff(rental.getId());
            panel.setRental(rental);
            panel.setGame(game);

            if (days > 0) {
                double debt = calculateDebt(days);
                payTheDebt(days, debt); //Pagar deuda //Cargar en registro //hacer que el cliente deje de tener deuda
                generalUpdate();
                return true;
            } else {
                generalUpdate();
                return true;
            }
        }
        return false;
    }

    /**
     *
     * Method in charge of changing the state of a game to available and
     * updating it in the database
     */
    private void setGameAvailable() {
        panel.getGame().setAvailable(true);
        gdao.modify(panel.getGame());
    }

    /**
     * Method in charge of calculating a client's debt
     *
     * @param days the number of days the client has been in debt.
     *
     * @return double, represents total debt.
     */
    private double calculateDebt(int days) {
        double penaltyFee = pdao.getPenaltyFee();
        return panel.getGame().getPrice() * days * penaltyFee;
    }

    /**
     * Method in charge of making the client pay a debt and also records it in
     * the report table
     *
     * @param days the number of days the client has been in debt.
     * @param debt total debt.
     */
    private void payTheDebt(int days, double debt) {
        JOptionPane.showMessageDialog(null, "You have a debt for " + days + " days, you must pay $ " + debt);
        String clientName = panel.getClient().getName();
        String clientSurname = panel.getClient().getSurname();
        String clientDNI = panel.getClient().getDni();
        String gameName = panel.getGame().getName();
        double totalDebt = debt;
        Date debtStart = getDebtStart();
        Date debtFinish = getDebtFinish();

        RentalDetail rentalDetail = new RentalDetail(clientName, clientSurname, clientDNI, gameName, totalDebt, debtStart, debtFinish);
        rddao.add(rentalDetail);
        this.setClientWithoutDebt();
    }

    /**
     * Method in charge of adding dates
     *
     * @param date Date to be added.
     * @param daysDays to add.
     * @return
     */
    private Date addDates(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, days);
        Date newDate = new Date(calendar.getTimeInMillis());
        return newDate;
    }

    //La deuda comienza el dia siguiente que debia de devolver el juego
    private Date getDebtStart() {
        return addDates(panel.getRental().getReturnDate(), 1);
    }

    /**
     * Method in charge of calculating the date the client's debt began.
     *
     * @return Date the debt started.
     */
    private Date getDebtFinish() {
        Calendar calendar = Calendar.getInstance();
        Date date = new Date(calendar.getTimeInMillis());
        return date;
    }

    /**
     * Method in charge of removing the game from the table of rented games
     */
    private void updateRent() {
        rdao.remove(panel.getRental());
    }

    /**
     * Method in charge of updating the client's status to debt free.
     */
    private void setClientWithoutDebt() {
        panel.getClient().setDebt(false);
    }

    /**
     * Method in charge of modifying the client in the database.
     */
    private void modifyClient() {
        cdao.modify(panel.getClient());
    }

    /**
     *
     * Method in charge of changing the state of the client and the game, also
     * updates the table of rented games.
     */
    private void generalUpdate() {
        setGameAvailable(); //hacer el juego disponible
        panel.getClient().returnAGame(); //hacer que el cliente tenga uno menos alquilado
        modifyClient(); // Modifica todo del cliente
        updateRent(); // Elimina de la fila de tabla de rentados.
    }
}
