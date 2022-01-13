/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.edu.fiuni.gameshop.model;

import java.sql.Date;

/**
 *
 * @author Pamela Horn
 */
public class Rental {

    private int id = 0;
    private String gameName = null;
    private String clientSurname = null;
    private String clientDNI = null;
    private Date saleDate = null;
    private Date returnDate = null;
    private double totalCost = 0.0;

    public Rental(String gameName, String clientName, String clientDNI, Date saleDate, Date returnDate) {
        this.gameName = gameName;
        this.clientSurname = clientName;
        this.clientDNI = clientDNI;
        this.saleDate = saleDate;
        this.returnDate = returnDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getClientName() {
        return clientSurname;
    }

    public void setClientName(String clientName) {
        this.clientSurname = clientName;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date retuasdrnDate) {
        this.returnDate = retuasdrnDate;
    }

    public String getClientDNI() {
        return clientDNI;
    }

    public void setClientDNI(String clientDNI) {
        this.clientDNI = clientDNI;
    }

}
