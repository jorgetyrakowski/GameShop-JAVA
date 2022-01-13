/**
 *UNIVERSIDAD NACIONAL DE ITAPUA
 *PROJECT GAMESHOP
 *
 * 2020 - SEGUNDO SEMESTRE
 *
 *  Jorge Tyrakowski & Pamela Horn
 * */
package py.edu.fiuni.gameshop.model;

import java.sql.Date;

/**
 * 
 *Rental detail model. Class used to get reports.
 */
public class RentalDetail extends Model {

    //Varibles
    private int id = 0;
    private String clientName = null;
    private String clientSurname = null;
    private String clientDNI = null;
    private String gameName = null;
    private double gamePrice = 0.0;
    private Date returnDate = null;
    private Date saleDate = null;

    /**
     * Constructor
     * @param clientName
     * @param clientSurname
     * @param clientDNI
     * @param gameName
     * @param gamePrice
     * @param saleDate
     * @param returnDate 
     */
    public RentalDetail(String clientName, String clientSurname, String clientDNI, String gameName, double gamePrice, Date saleDate, Date returnDate) {
        this.clientName = clientName;
        this.clientSurname = clientSurname;
        this.clientDNI = clientDNI;
        this.gameName = gameName;
        this.gamePrice = gamePrice;
        this.saleDate = saleDate;
        this.returnDate = returnDate;

    }

    //GETTERS AND SETTERS
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientSurname() {
        return clientSurname;
    }

    public void setClientSurname(String clientSurname) {
        this.clientSurname = clientSurname;
    }

    public String getClientDNI() {
        return clientDNI;
    }

    public void setClientDNI(String clientDNI) {
        this.clientDNI = clientDNI;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public double getGamePrice() {
        return gamePrice;
    }

    public void setGamePrice(double gamePrice) {
        this.gamePrice = gamePrice;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

}
