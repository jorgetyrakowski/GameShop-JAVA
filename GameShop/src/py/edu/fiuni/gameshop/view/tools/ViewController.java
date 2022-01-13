/**
 *UNIVERSIDAD NACIONAL DE ITAPUA
 *PROJECT GAMESHOP
 *
 * 2020 - SEGUNDO SEMESTRE
 *
 *  Jorge Tyrakowski & Pamela Horn
 * */

package py.edu.fiuni.gameshop.view.tools;

//import
import javax.swing.JPanel;
import py.edu.fiuni.gameshop.view.View;

/**
 * 
 * To manage the change of panels on the form.
 * */
public class ViewController {
    private static View view;

    /**
     * The view to be changed and managed.
     * @param view 
     */
    public ViewController(View view){
        this.view = view;
    }
    
    /**
     * 
     * Changes from the view to the panel.
     */
    public static void changePanel(JPanel panel){
        panel.setVisible(true);
        view.add(panel);
        view.desactivateMenu();
    }
    
    /**
     * 
     * Removes panel and gets back to the original view.
     */
    public static void backToMenu(JPanel panel){
        panel.setVisible(false);
        view.remove(panel);
        view.activateMenu();
    }
}
