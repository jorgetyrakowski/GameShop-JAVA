/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.edu.fiuni.gameshop.model;

import javax.swing.ImageIcon;

public class Game extends Model {
    
    
    
    private int id = 0;
    private String name = null;
    private String console = null;
    private String company = null;
    private String genre = null;
    private String description = null;
    private String state = null;
    private double price = 0.0;
    private boolean available = false;
    
    
    // GETTERS AND SETTERS.

    public int getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public String getConsole() {
        return console;
    }

    public String getCompany() {
        return company;
    }

    public String getGenre() {
        return genre;
    }

    public String getDescription() {
        if (null == this.description) {
            return "";
        }
        return description;
    }

    public double getPrice() {
        return this.price;
    }

    public String getAvailability() {
        return (this.available == true) ? "Available" : "Not available";
    }

    public boolean isAvailable() {
        return available;
    }

    public void setId(int newId) {
        this.id = newId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setConsole(String console) {
        this.console = console;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double newPrice) {
        this.price = newPrice;
    }

    public void setAvailable(boolean state) {
        this.available = state;
    }

    public void setState(String newState) {
        this.state = newState;
    }


}
