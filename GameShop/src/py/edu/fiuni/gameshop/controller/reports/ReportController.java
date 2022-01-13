/**
 *UNIVERSIDAD NACIONAL DE ITAPUA                    
 *PROJECT GAMESHOP                                     
 *                                                  
 * 2020 - SEGUNDO SEMESTRE                          
 *                                                  
 *  Jorge Tyrakowski & Pamela Horn                  
 **/
package py.edu.fiuni.gameshop.controller.reports;


//import
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import py.edu.fiuni.gameshop.view.reports.PanelReport;

/**
 * This controller implements the ActionListeners for the Repor panel
 * . Allowing the buttons to perform.
 */
public class ReportController implements ActionListener {

    PanelReport panel = null;

    public ReportController(PanelReport panel) {
        this.panel = panel;
        
        //adds listeners
        addListeners();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        
        if (ae.getSource() == panel.getJbttLoad()) {
            //updates the table. more about the implementation on the panel!!
            panel.updateTable();
        }
    }

    /**
     * Adds the listeners of the panel to the controller.
     */
    private void addListeners() {
        panel.getJbttLoad().addActionListener(this);

    }
}
