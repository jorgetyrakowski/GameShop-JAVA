/**
 *UNIVERSIDAD NACIONAL DE ITAPUA
 *PROJECT GAMESHOP
 *
 * 2020 - SEGUNDO SEMESTRE
 *
 *  Jorge Tyrakowski & Pamela Horn
 * */
package py.edu.fiuni.gameshop.model;

/**
 * 
 * Client model.
 */
public class Client extends Model {

    //variables
    private int id = 0;
    private String name = null;
    private String surname = null;
    private String dni = null;
    private String email = null;
    private String adress = null;
    private String number = null;
    private int games = 0;
    private boolean debt = false;
    private int totalGames = 0;
    private int gameLimit = 0;

    /**
     * Constructor to define a client with parameters.
     * @param dni
     * @param name
     * @param surname
     * @param email
     * @param adress
     * @param number 
     */
    public Client(String dni, String name, String surname, String email,
            String adress, String number) {
        this.name = name;
        this.surname = surname;
        this.dni = dni;
        this.email = email;
        this.adress = adress;
        this.number = number;
    }

    /**
     * Empty constructor
     */
    public Client() {
    }

    @Override
    public String toString() {
        return "[Nombre" + getName() + "]";
    }

    //GETTERS & SETTERS
    public int getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDni() {
        return dni;
    }

    public void setId(int newId) {
        this.id = newId;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTotalGames() {
        return totalGames;
    }

    public void setTotalGames(int totalGames) {
        this.totalGames = totalGames;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getGameLimit() {
        return gameLimit;
    }

    public void setGameLimit(int gameLimit) {
        this.gameLimit = gameLimit;
    }

    public boolean isDebt() {
        return debt;
    }

    public String getDebtToString() {
        return (this.isDebt() == true) ? "Has Debt" : "Free of debt";
    }

    public void setDebt(boolean debt) {
        this.debt = debt;
    }

    public int getGames() {
        return games;
    }

    public void setGames(int games) {
        this.games = games;
    }

    public void rentAGame() {
        this.games++;
    }

    public void returnAGame() {
        this.games--;
    }

}
