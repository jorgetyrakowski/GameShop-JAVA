/**
 *UNIVERSIDAD NACIONAL DE ITAPUA
 *PROJECT GAMESHOP
 *
 * 2020 - SEGUNDO SEMESTRE
 *
 *  Jorge Tyrakowski & Pamela Horn
 * */
package py.edu.fiuni.gameshop.view.client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import py.edu.fiuni.gameshop.controller.client.ClientController;
import py.edu.fiuni.gameshop.dao.mysql.DAOConnection;
import py.edu.fiuni.gameshop.dao.mysql.MySQLClientDAO;
import py.edu.fiuni.gameshop.model.Client;

/**
 *
 * View of Clients table and buttons.
 */
public class PnelClients extends javax.swing.JPanel {

    /**
     * Creates new form ClientView
     */
    //conncetion 
    private DAOConnection connection = null;
    private MySQLClientDAO dao = null;

    //controller
    private ClientController controller = null;

    //client
    private Client client = null;
    private String selectedClient = "";

    /**
     * Innits data.
     */
    public PnelClients() {
        initComponents();
        initConnections();
        controller = new ClientController(dao, this, client);
        enabledButtons();
        update();
    }

    /**
     * Dissables or enables buttons depending on the state of selection of the
     * rows
     *
     */
    private void enabledButtons() {
        boolean value = (this.getSelectedClient().length() > 0);
        jbRemove.setEnabled(value);
        jbEdit.setEnabled(value);
        jbSave.setEnabled(false);
        jbBack.setEnabled(true);

    }

    /**
     * Initiates connections
     */
    private void initConnections() {
        connection = new DAOConnection();
        this.connection = new DAOConnection();
        this.dao = new MySQLClientDAO(connection);
    }

    /**
     * Creates displayable table with client data.
     */
    private void consultTable() {
        try {
            DefaultTableModel model = new DefaultTableModel();
            this.jtClientList.setModel(model);

            Connection con = this.connection.getConexion();
            PreparedStatement ps = null;
            ResultSet rs = null;

            String SELECT = "SELECT dni, name, surname, adress, email, phone FROM client";
            ps = con.prepareStatement(SELECT);
            rs = ps.executeQuery();

            ResultSetMetaData rsMd = rs.getMetaData();
            int columnsCant = rsMd.getColumnCount();

            //columns
            model.addColumn("DNI");
            model.addColumn("NAME");
            model.addColumn("SURNAME");
            model.addColumn("ADRESS");
            model.addColumn("EMAIL");
            model.addColumn("PHONE NUMBER");

            while (rs.next()) {

                Object[] rows = new Object[columnsCant];

                for (int i = 0; i < columnsCant; i++) {
                    rows[i] = rs.getObject(i + 1);
                }
                model.addRow(rows);
            }
            model.fireTableDataChanged();
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    /**
     * Searches a client through the JtextField designated and 
     * displays it on the table.
     */
    public void redrawTableWithDniSearch() {
        try {
            DefaultTableModel model = new DefaultTableModel();
            this.jtClientList.setModel(model);

            Connection con = this.connection.getConexion();
            PreparedStatement ps = null;
            ResultSet rs = null;

            String SELECT = "SELECT dni, name, surname, adress, email, phone FROM client WHERE dni = ?";
            ps = con.prepareStatement(SELECT);
            ps.setString(1, jtxtSearch.getText());
            rs = ps.executeQuery();

            ResultSetMetaData rsMd = rs.getMetaData();
            int columnsCant = rsMd.getColumnCount();

            model.addColumn("DNI");
            model.addColumn("NAME");
            model.addColumn("SURNAME");
            model.addColumn("ADRESS");
            model.addColumn("EMAIL");
            model.addColumn("PHONE NUMBER");

            while (rs.next()) {

                Object[] rows = new Object[columnsCant];

                for (int i = 0; i < columnsCant; i++) {
                    rows[i] = rs.getObject(i + 1);
                }
                model.addRow(rows);

            }

            model.fireTableDataChanged();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    /**
     * updates main table
     */
    public void update() {
        consultTable();
    }

    /**
     * Displays selected row on each designates jtext.
     * @param x 
     */
    public void displaySelected(int x) {
        DefaultTableModel model = (DefaultTableModel) jtClientList.getModel();
        this.clientLogger2.getJtxtDni().setText(model.getValueAt(x, 0).toString());
        this.clientLogger2.getJtxtName().setText(model.getValueAt(x, 1).toString());
        this.clientLogger2.getJtxtSurname().setText(model.getValueAt(x, 2).toString());
        this.clientLogger2.getJtxtAdress().setText(model.getValueAt(x, 3).toString());
        this.clientLogger2.getJtxtEmail().setText(model.getValueAt(x, 4).toString());
        this.clientLogger2.getJtxtNumber().setText(model.getValueAt(x, 5).toString());

    }

    /**
     * Cleans or erase the content of the jtext fields.
     */
    public void clean() {
        clientLogger2.getJtxtDni().setText("");
        clientLogger2.getJtxtName().setText("");
        clientLogger2.getJtxtSurname().setText("");
        clientLogger2.getJtxtAdress().setText("");
        clientLogger2.getJtxtEmail().setText("");
        clientLogger2.getJtxtNumber().setText("");
        repaint();
    }

    /**
     * Modifies client value.
     * @param client
     * @return 
     */
    public boolean modify(Client client) {
        DefaultTableModel model = (DefaultTableModel) getJtClientList().getModel();
        if (dao.modify(client)) {
            update();
            model.fireTableDataChanged();
            JOptionPane.showMessageDialog(null, "The client has been successfully modificated");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "ERROR");
            return false;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlblBackground = new javax.swing.JLabel();
        jlblBg = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtClientList = new javax.swing.JTable();
        clientLogger2 = new py.edu.fiuni.gameshop.view.client.ClientLogger();
        jPanel3 = new javax.swing.JPanel();
        jbAdd = new javax.swing.JButton();
        jbEdit = new javax.swing.JButton();
        jbRemove = new javax.swing.JButton();
        jbCancel = new javax.swing.JButton();
        jbSave = new javax.swing.JButton();
        jbBack = new javax.swing.JButton();
        jtxtSearch = new javax.swing.JTextField();
        jbttSearch = new javax.swing.JButton();
        jlblbg = new javax.swing.JLabel();

        jlblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/py/edu/fiuni/gameshop/view/icons/icon4.jpg"))); // NOI18N

        jlblBg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/py/edu/fiuni/gameshop/view/icons/icon3.jpg"))); // NOI18N

        setBackground(new java.awt.Color(204, 204, 255));
        setMinimumSize(new java.awt.Dimension(1280, 800));
        setPreferredSize(new java.awt.Dimension(1280, 800));
        setLayout(null);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setPreferredSize(new java.awt.Dimension(1080, 600));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setLayout(new java.awt.BorderLayout());

        jtClientList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "DNI", "NAME", "SURNAME", "ADRESS", "EMAIL", "PHONE NUMBER"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtClientList.setGridColor(new java.awt.Color(153, 153, 255));
        jtClientList.getTableHeader().setReorderingAllowed(false);
        jtClientList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtClientListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtClientList);
        if (jtClientList.getColumnModel().getColumnCount() > 0) {
            jtClientList.getColumnModel().getColumn(0).setResizable(false);
            jtClientList.getColumnModel().getColumn(1).setResizable(false);
            jtClientList.getColumnModel().getColumn(2).setResizable(false);
            jtClientList.getColumnModel().getColumn(4).setResizable(false);
        }

        jPanel2.add(jScrollPane1, java.awt.BorderLayout.CENTER);
        jPanel2.add(clientLogger2, java.awt.BorderLayout.LINE_END);

        jPanel1.add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel3.setBackground(new java.awt.Color(60, 0, 0));
        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 255, 255), new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204), new java.awt.Color(204, 255, 255)));
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.setToolTipText(" save changes");
        jPanel3.setMinimumSize(new java.awt.Dimension(1280, 800));

        jbAdd.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jbAdd.setForeground(new java.awt.Color(255, 255, 255));
        jbAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/py/edu/fiuni/gameshop/view/icons/mini-icons/3855592-48 (1).png"))); // NOI18N
        jbAdd.setText("add");
        jbAdd.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jbAdd.setContentAreaFilled(false);

        jbEdit.setForeground(new java.awt.Color(255, 255, 255));
        jbEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/py/edu/fiuni/gameshop/view/icons/mini-icons/maintenance.png"))); // NOI18N
        jbEdit.setText("save changes");
        jbEdit.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jbEdit.setContentAreaFilled(false);

        jbRemove.setForeground(new java.awt.Color(255, 255, 255));
        jbRemove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/py/edu/fiuni/gameshop/view/icons/mini-icons/trash.png"))); // NOI18N
        jbRemove.setText("remove");
        jbRemove.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jbRemove.setContentAreaFilled(false);

        jbCancel.setForeground(new java.awt.Color(255, 255, 255));
        jbCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/py/edu/fiuni/gameshop/view/icons/mini-icons/cancel.png"))); // NOI18N
        jbCancel.setText("cancel");
        jbCancel.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jbCancel.setContentAreaFilled(false);
        jbCancel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        jbSave.setForeground(new java.awt.Color(255, 255, 255));
        jbSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/py/edu/fiuni/gameshop/view/icons/mini-icons/save.png"))); // NOI18N
        jbSave.setText("save new");
        jbSave.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jbSave.setContentAreaFilled(false);

        jbBack.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.focus"));
        jbBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/py/edu/fiuni/gameshop/view/icons/mini-icons/return.png"))); // NOI18N
        jbBack.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jbBack.setContentAreaFilled(false);

        jtxtSearch.setForeground(new java.awt.Color(204, 204, 204));
        jtxtSearch.setText("search by dni");
        jtxtSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtxtSearchMouseClicked(evt);
            }
        });

        jbttSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/py/edu/fiuni/gameshop/view/icons/mini-icons/search.png"))); // NOI18N
        jbttSearch.setContentAreaFilled(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jbBack, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jbAdd)
                .addGap(43, 43, 43)
                .addComponent(jbEdit)
                .addGap(39, 39, 39)
                .addComponent(jbSave)
                .addGap(33, 33, 33)
                .addComponent(jbRemove)
                .addGap(31, 31, 31)
                .addComponent(jbCancel)
                .addGap(53, 53, 53)
                .addComponent(jtxtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jbttSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(241, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jbAdd, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 1, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jbBack)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jbttSearch, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jbEdit)
                                    .addComponent(jbRemove)
                                    .addComponent(jbSave)
                                    .addComponent(jbCancel)
                                    .addComponent(jtxtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(38, 38, 38))
        );

        jPanel1.add(jPanel3, java.awt.BorderLayout.PAGE_START);

        add(jPanel1);
        jPanel1.setBounds(98, 114, 1080, 600);

        jlblbg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/py/edu/fiuni/gameshop/view/icons/icon4.jpg"))); // NOI18N
        jlblbg.setText("jLabel1");
        add(jlblbg);
        jlblbg.setBounds(0, 0, 1280, 800);
    }// </editor-fold>//GEN-END:initComponents

/**
 * Gets the row and the value.
 * enables buttons and disables jtxtdni.
 * @param evt 
 */
    private void jtClientListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtClientListMouseClicked
        int row = this.jtClientList.rowAtPoint(evt.getPoint());
        this.selectedClient = String.valueOf(this.jtClientList.getValueAt(row, 0));
        displaySelected(row);
        enabledButtons();
        clientLogger2.setEditable(true);
        clientLogger2.getJtxtDni().setEditable(false);
        clientLogger2.getJtxtDni().setEnabled(false);

    }//GEN-LAST:event_jtClientListMouseClicked

    private void jtxtSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtxtSearchMouseClicked
        jtxtSearch.setText("");
    }//GEN-LAST:event_jtxtSearchMouseClicked

//GETTERS AND SETTERS
    public String getSelectedClient() {
        return selectedClient;
    }

    public JButton getJbAdd() {
        return jbAdd;
    }

    public void setJbAdd(JButton jbAdd) {
        this.jbAdd = jbAdd;
    }

    public JButton getJbBack() {
        return jbBack;
    }

    public void setJbBack(JButton jbBack) {
        this.jbBack = jbBack;
    }

    public JButton getJbCancel() {
        return jbCancel;
    }

    public void setJbCancel(JButton jbCancel) {
        this.jbCancel = jbCancel;
    }

    public JButton getJbEdit() {
        return jbEdit;
    }

    public void setJbEdit(JButton jbEdit) {
        this.jbEdit = jbEdit;
    }

    public JButton getJbRemove() {
        return jbRemove;
    }

    public void setJbRemove(JButton jbRemove) {
        this.jbRemove = jbRemove;
    }

    public JButton getJbSave() {
        return jbSave;
    }

    public void setJbSave(JButton jbSave) {
        this.jbSave = jbSave;
    }

    public ClientLogger getClientLogger() {
        return clientLogger2;
    }

    public void setClientLogger(ClientLogger clientLogger) {
        this.clientLogger2 = clientLogger;
    }

    public JButton getJbttSearch() {
        return jbttSearch;
    }

    public JTable getJtClientList() {
        return jtClientList;
    }

    public void setJtClientList(JTable jtClientList) {
        this.jtClientList = jtClientList;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private py.edu.fiuni.gameshop.view.client.ClientLogger clientLogger2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbAdd;
    private javax.swing.JButton jbBack;
    private javax.swing.JButton jbCancel;
    private javax.swing.JButton jbEdit;
    private javax.swing.JButton jbRemove;
    private javax.swing.JButton jbSave;
    private javax.swing.JButton jbttSearch;
    private javax.swing.JLabel jlblBackground;
    private javax.swing.JLabel jlblBg;
    private javax.swing.JLabel jlblbg;
    private javax.swing.JTable jtClientList;
    private javax.swing.JTextField jtxtSearch;
    // End of variables declaration//GEN-END:variables

}
