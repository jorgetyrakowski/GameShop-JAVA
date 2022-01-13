/**
 *UNIVERSIDAD NACIONAL DE ITAPUA                    
 *PROJECT GAMESHOP                                     
 *                                                  
 * 2020 - SEGUNDO SEMESTRE                          
 *                                                  
 *  Jorge Tyrakowski & Pamela Horn                  
 **/

package py.edu.fiuni.gameshop.controller.client;

//imports
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import py.edu.fiuni.gameshop.dao.mysql.MySQLClientDAO;
import py.edu.fiuni.gameshop.model.Client;
import py.edu.fiuni.gameshop.view.client.PnelClients;
import py.edu.fiuni.gameshop.view.tools.ViewController;

/**
 * This controller implements the ActionListeners for the main 
 * Panel of Clients. Allowing the buttons to perform.
 */
public class ClientController implements ActionListener {

    //Panel
    private PnelClients panel = null;
    
    //database  
    private MySQLClientDAO dao = null;
    
    //model class
    private Client client = null;

    public ClientController(MySQLClientDAO dao, PnelClients panel, Client client) {
        this.dao = dao;
        this.panel = panel;
        this.client = client;
        addListeners(panel);
    }

    /**
     * Performs the action of every listener added. 
     * Buttons specifically.
     * @param ae gets the source to perform
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == panel.getJbAdd()) {
            add();
        }

        if (ae.getSource() == panel.getJbCancel()) {
            cancel();
        }

        if (ae.getSource() == panel.getJbEdit()) {
            edit();

        }

        if (ae.getSource() == panel.getJbSave()) {
            save();

        }

        if (ae.getSource() == panel.getJbttSearch()) {
            search();

        }

        if (ae.getSource() == panel.getJbRemove()) {
            delete();
        }

        if (ae.getSource() == panel.getJbBack()) {
            ViewController.backToMenu(panel);
        }
    }

    /**
     * Allows the modification of the client data.
     */
    private void edit() {
        
        //if there is a row selected with an actual dni 
        if (panel.getSelectedClient().length() > 0) {
            client = dao.getByDni(panel.getSelectedClient());
        }
        //text fields values are setted to the client.
        client.setDni(panel.getClientLogger().getJtxtDni().getText());
        client.setName(panel.getClientLogger().getJtxtName().getText());
        client.setSurname(panel.getClientLogger().getJtxtSurname().getText());
        client.setAdress(panel.getClientLogger().getJtxtAdress().getText());
        client.setEmail(panel.getClientLogger().getJtxtEmail().getText());
        client.setNumber(panel.getClientLogger().getJtxtNumber().getText());
        
        //modifies
        panel.modify(client);

    }

    /**
     * Disables buttons, cleans the text fields and updates the table.
     */
    private void cancel() {
        panel.getClientLogger().setEditable(false);
        panel.getJtClientList().clearSelection();
        panel.getJbSave().setEnabled(false);
        panel.getJbCancel().setEnabled(false);
        panel.getJbRemove().setEnabled(false);
        panel.getJbEdit().setEnabled(false);
        panel.clean();
        panel.update();
    }

    /**
     * 
     */
    private void add() {
        //enables and disables button (most a visual thing than anything)
        panel.getJbSave().setEnabled(true);
        panel.getJbCancel().setEnabled(true);
        panel.getJbEdit().setEnabled(false);
        panel.getJbRemove().setEnabled(false);
        panel.getClientLogger().setEditable(true);
        //cleans area
        panel.clean();
        
        //gets focus
        panel.getClientLogger().getJtxtName().requestFocus();
        
        //dnis are not editable
        panel.getClientLogger().getJtxtDni().setEditable(true);
        panel.getClientLogger().getJtxtDni().setEnabled(true);
    }

    /**
     * Saves and updates a client onto the database, therefore on the table.
     */
    private void save() {
        //creates new client 
        client = new Client();
        
        //set values
        client.setDni(panel.getClientLogger().getJtxtDni().getText());
        client.setName(panel.getClientLogger().getJtxtName().getText());
        client.setSurname(panel.getClientLogger().getJtxtSurname().getText());
        client.setAdress(panel.getClientLogger().getJtxtAdress().getText());
        client.setEmail(panel.getClientLogger().getJtxtEmail().getText());
        client.setNumber((panel.getClientLogger().getJtxtNumber().getText()));
        
        
        if (dao.add(client)) {
            JOptionPane.showMessageDialog(null, "The client has been successfully added");
            //updates tables and clean the text fields
            panel.update();
            panel.clean();
        } else {
            JOptionPane.showMessageDialog(null, "The client could not be added");

        }
    }

    /**
     * deletes the client and updates the table.
     */
    private void delete() {
        if (panel.getSelectedClient().length() > 0 && panel.getSelectedClient() != null) {
            if (dao.remove(dao.getByDni(panel.getSelectedClient()))) {
                panel.update();
                JOptionPane.showMessageDialog(null, "The client has been successfully deleted");
            } else {
                JOptionPane.showMessageDialog(null, "Client cannot be found");

            }

        }
    }

    /**
     * searches by dni the rows.
     */
    private void search() {
        panel.redrawTableWithDniSearch();
        panel.getJbCancel().setEnabled(true);
    }

    /**
     * If a client does not exist, it returns a new one.
     * @return client.
     */
    private Client getModel() {
        if (client == null) {
            client = new Client();
        }
        return client;

    }

    /**
     * Addas listeners onto the current controller.
     * @param panel1 where the view is setted.
     */
    private void addListeners(PnelClients panel1) {
        panel1.getJbAdd().addActionListener(this);
        panel1.getJbRemove().addActionListener(this);
        panel1.getJbCancel().addActionListener(this);
        panel1.getJbEdit().addActionListener(this);
        panel1.getJbSave().addActionListener(this);
        panel1.getJbttSearch().addActionListener(this);
        panel1.getJbBack().addActionListener(this);
    }

}
