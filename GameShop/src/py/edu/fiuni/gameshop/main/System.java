/**
 *UNIVERSIDAD NACIONAL DE ITAPUA
 *PROJECT GAMESHOP
 *
 * 2020 - SEGUNDO SEMESTRE
 *
 *  Jorge Tyrakowski & Pamela Horn
 * */
package py.edu.fiuni.gameshop.main;

import java.sql.SQLException;
import py.edu.fiuni.gameshop.view.View;


/**
 * 
 * Excecutes the system.
 */
public class System {
    public static void main(String[] args) throws SQLException {
        View view = new View();
        view.setVisible(true);
    }
    
}
