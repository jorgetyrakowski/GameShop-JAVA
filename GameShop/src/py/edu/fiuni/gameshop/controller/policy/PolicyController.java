/**
 *UNIVERSIDAD NACIONAL DE ITAPUA                    
 *PROJECT GAMESHOP                                     
 *                                                  
 * 2020 - SEGUNDO SEMESTRE                          
 *                                                  
 *  Jorge Tyrakowski & Pamela Horn                  
 **/
package py.edu.fiuni.gameshop.controller.policy;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import py.edu.fiuni.gameshop.dao.mysql.MySQLPolicyDAO;
import py.edu.fiuni.gameshop.model.Policy;
import py.edu.fiuni.gameshop.view.policy.PanelPolicy;

/**
 * This controller implements the ActionListeners for the Policy panel
 * . Allowing the buttons to perform.
 */
public class PolicyController implements ActionListener {

    //model
    private Policy policy = null;
    //panel
    private PanelPolicy panel = null;
    
    //database connection dao
    private MySQLPolicyDAO dao = null;

    
    public PolicyController(PanelPolicy panel, MySQLPolicyDAO dao) {
        this.panel = panel;
        this.dao = dao;
        //adds listeners of the panel 
        addListeners(panel);
    }

    /**
     * Allow performance of the buttons.
     * @param ae used to get the sources
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == panel.getJbttSave()) {
            modify();
        }

        if (ae.getSource() == panel.getJbttEdit()) {
            edit();
        }

        if (ae.getSource() == panel.getJbttCancel()) {
            cancel();
        }

    }

    /**
     * Modifies the policy data.
     */
    private void modify() {
        if (values(getModel()) && dao.modify(getModel())) {
            //set the values added
            JOptionPane.showMessageDialog(null, "Changes succesfully saved");
        } else {
            //repaints the view with the old values.
            panel.updateData();
        }
        panel.setTextEditable(false);
    }

    /**
     * Analyzes if the values are convertible  to the numeric form needed,
     * @param policy 
     * @return true or false in each case.
     * @throws HeadlessException 
     */
    private boolean values(Policy policy) throws HeadlessException {
        policy.setObservations(panel.getJtxtAPolicy().getText());
        try {
            policy.setDayLimit(Integer.parseInt(panel.getJtxtDayLimit().getText()));
            policy.setLimit(Integer.parseInt(panel.getJtxtGameLimit().getText()));
            policy.setCharges(Double.parseDouble(panel.getJtxtPenalty().getText()));
            return true;
        } catch (NumberFormatException eo) {
            JOptionPane.showMessageDialog(null, "Some fields contemplate only numeric values");
            return false;
        }
    }

    /**
     * Allows the text fields and text area editing.
     */
    private void edit() {
        panel.setTextEditable(true);
        panel.getJtxtGameLimit().requestFocus();
    }

    /**
     * Updates with the current data to erase any change
     */
    private void cancel() {
        panel.setTextEditable(false);
        panel.updateData();
    }

    /**
     * @returns a new policy in case of this being null
     */
    private Policy getModel() {
        if (policy == null) {
            policy = new Policy();
        }
        return policy;
    }

    /**
     * Listeners are added to the current controller.
     * @param panel1 where the buttons are.
     */
    private void addListeners(PanelPolicy panel1) {
        panel1.getJbttSave().addActionListener(this);
        panel1.getJbttEdit().addActionListener(this);
        panel1.getJbttCancel().addActionListener(this);
    }

}
