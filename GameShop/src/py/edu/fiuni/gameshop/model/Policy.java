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
 * Policy class. Allows data of the policy.
 */
public class Policy {

    //variables 
    private double charges = 0.0;
    private int limit = 0;
    private int dayLimit = 0;
    private String observations = null;

    
    
    
    //GETTERS AND SETTERS 
    public double getCharges() {
        return charges;
    }

    public void setCharges(double charges) {
        this.charges = charges;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getDayLimit() {
        return dayLimit;
    }

    public void setDayLimit(int dayLimit) {
        this.dayLimit = dayLimit;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

}
