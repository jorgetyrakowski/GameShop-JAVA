/**
 *UNIVERSIDAD NACIONAL DE ITAPUA
 *PROJECT GAMESHOP
 *
 * 2020 - SEGUNDO SEMESTRE
 *
 *  Jorge Tyrakowski & Pamela Horn
 * */
package py.edu.fiuni.gameshop.view.reports;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import py.edu.fiuni.gameshop.controller.reports.ReportController;
import py.edu.fiuni.gameshop.dao.mysql.DAOConnection;
import py.edu.fiuni.gameshop.view.tools.ViewController;

/**
 * 
 * Gets all the transactions allowed displayed.
 */
public class PanelReport extends javax.swing.JPanel {

    private DAOConnection connection = null;
    private ReportController controller = null;
    private String value = "";

    /**
     * Creates new form PanelReport
     */
    public PanelReport() {
        initComponents();
        connection = new DAOConnection();
        controller = new ReportController(this);
        updateTable();

    }

    /**
     * Draws table.
     */
    private void consultTable() {
        try {
            DefaultTableModel model = new DefaultTableModel();
            this.jtblReports.setModel(model);
            Connection con = this.connection.getConexion();
            PreparedStatement ps = null;
            ResultSet rs = null;

            //gest the selections and orders it by desc 
            String SELECT = getStatement(returnDate()) + " ORDER BY rent_date DESC";
            ps = con.prepareStatement(SELECT);
            rs = ps.executeQuery();

            ResultSetMetaData rsMd = rs.getMetaData();
            int columnsCant = rsMd.getColumnCount();

            model.addColumn("CLIENT NAME");
            model.addColumn("CLIENT SURNAME");
            model.addColumn("CLIENT DNI");
            model.addColumn("GAME");
            model.addColumn("RENT DATE");
            model.addColumn("RETURN DATE");
            model.addColumn("GAME PRICE");

            while (rs.next()) {

                Object[] rows = new Object[columnsCant];

                for (int i = 0; i < columnsCant; i++) {
                    rows[i] = rs.getObject(i + 1);
                }
                model.addRow(rows);

            }

            model.fireTableDataChanged();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the additions of all the game price columns.
     *  
     */
    private String getTotal() {
        DefaultTableModel model = new DefaultTableModel();
        this.jtblReports.setModel(model);
        Connection con = this.connection.getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String adition = null;

        try {
            String SELECT = getStatementSum(returnDate());
            ps = con.prepareStatement(SELECT);
            rs = ps.executeQuery();
            while (rs.next()) {
                adition = rs.getString("sum");
            }
        } catch (SQLException e) {
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
            }

        }
        return adition;
    }

    /**
     * Updates table and set the total value.
     */
    public void updateTable() {
        setTotal();
        consultTable();

    }

    /**
     * To get the combobox value.
     * @return 
     */
    private int returnDate() {
        int day = 0;
        if (value.equals("Last week") || jComboBox1.getSelectedIndex() == 0) {
            day = 7;
        }

        if (value.equals("Last 30 days")) {
            day = 30;
        }

        if (value.equals("Last 60 days")) {
            day = 60;
        }
        if (value.equals("All")) {
            day = 0;
        }

        return day;
    }

    /**
     * Set the addittions to the jtxt
     */
    private void setTotal() {
        jtxtValue.setText(getTotal());
    }

    /**
     * Gets the statement to be used to filter tables by the combobox selections.
     * @param days
     * @return 
     */
    private String getStatement(int days) {
        if (days > 0) {
            // to get by an intervale
            return "SELECT client_name, client_surname, client_dni,"
                    + " game_name, rent_date, return_date, game_price  FROM gameshop.rental_details WHERE "
                    + "rent_date BETWEEN date_sub(now(), interval " + days + " day) AND (current_date())";
        }
        //to get all
        return "SELECT client_name, client_surname, client_dni,"
                + " game_name, rent_date, return_date, game_price as sum  FROM rental_details";

    }

    private String getStatementSum(int days) {
        if (days > 0) {
            //to get the addition of an interval
            return "SELECT SUM(game_price) as sum FROM gameshop.rental_details WHERE "
                    + "rent_date BETWEEN date_sub(now(), interval " + days + " day) AND (current_date())";
        }
        //to get the total addition.
        return "SELECT SUM(game_price) as sum FROM rental_details";
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblReports = new javax.swing.JTable();
        jComboBox1 = new javax.swing.JComboBox<>();
        jbttLoad = new javax.swing.JButton();
        jtxtValue = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jlblReports = new javax.swing.JLabel();
        jbttBack = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(60, 0, 0));
        setMaximumSize(new java.awt.Dimension(1280, 800));
        setMinimumSize(new java.awt.Dimension(1280, 800));
        setPreferredSize(new java.awt.Dimension(1280, 800));
        setLayout(null);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(102, 0, 0));
        jPanel1.setOpaque(false);

        jtblReports.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jtblReports);

        jComboBox1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Last week", "Last 30 days", "Last 60 days", "All" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        jbttLoad.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jbttLoad.setText("Go");

        jtxtValue.setEditable(false);

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel1.setText("Total: ");

        jPanel2.setBackground(new java.awt.Color(60, 0, 0));

        jlblReports.setBackground(new java.awt.Color(60, 0, 0));
        jlblReports.setFont(new java.awt.Font("Dialog", 1, 70)); // NOI18N
        jlblReports.setForeground(new java.awt.Color(255, 255, 255));
        jlblReports.setText("R E P O R T S");

        jbttBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/py/edu/fiuni/gameshop/view/icons/mini-icons/return.png"))); // NOI18N
        jbttBack.setContentAreaFilled(false);
        jbttBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbttBackActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1020, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 12, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 12, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jbttBack)
                .addGap(191, 191, 191)
                .addComponent(jlblReports, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jlblReports, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbttBack)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jtxtValue, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jbttLoad, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox1)
                    .addComponent(jbttLoad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(41, 41, 41)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jtxtValue)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
                .addGap(53, 53, 53))
        );

        add(jPanel1);
        jPanel1.setBounds(160, 70, 1020, 670);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/py/edu/fiuni/gameshop/view/icons/icon5.jpg"))); // NOI18N
        jLabel2.setText("jLabel2");
        jLabel2.setPreferredSize(new java.awt.Dimension(1280, 800));
        add(jLabel2);
        jLabel2.setBounds(0, 0, 1280, 800);
    }// </editor-fold>//GEN-END:initComponents

    private void jbttBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbttBackActionPerformed
        ViewController.backToMenu(this);
    }//GEN-LAST:event_jbttBackActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        value = jComboBox1.getSelectedItem().toString();
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    
    //GETTERS AND SETTERS 
    public JButton getJbttLoad() {
        return jbttLoad;
    }

    public void setJbttLoad(JButton jbttLoad) {
        this.jbttLoad = jbttLoad;
    }

    public JComboBox<String> getjComboBox1() {
        return jComboBox1;
    }

    public void setjComboBox1(JComboBox<String> jComboBox1) {
        this.jComboBox1 = jComboBox1;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbttBack;
    private javax.swing.JButton jbttLoad;
    private javax.swing.JLabel jlblReports;
    private javax.swing.JTable jtblReports;
    private javax.swing.JTextField jtxtValue;
    // End of variables declaration//GEN-END:variables

}
