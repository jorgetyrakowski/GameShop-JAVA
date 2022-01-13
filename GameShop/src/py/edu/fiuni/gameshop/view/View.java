/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.edu.fiuni.gameshop.view;

import java.awt.Dimension;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import py.edu.fiuni.gameshop.view.tools.ViewController;
import py.edu.fiuni.gameshop.view.client.PnelClients;
import py.edu.fiuni.gameshop.view.game.PanelGames;
import py.edu.fiuni.gameshop.view.policy.PanelPolicy;
import py.edu.fiuni.gameshop.view.rental.PanelRental;
import py.edu.fiuni.gameshop.view.rental.PanelReturnGames;
import py.edu.fiuni.gameshop.view.reports.PanelReport;

/**
 *
 * @author Rafa
 */
public class View extends javax.swing.JFrame {

    // Panels
    public PanelGames pnlGames = null;
    public PnelClients pnlClients = null;
    public PanelRental pnlRental = null;
    public PanelPolicy pnlPolicy = null;
    public PanelReport pnlReports = null;
    public PanelReturnGames pnlReturnGames = null;
    // Controller
    private ViewController viewController = null;

    /**
     * Creates new form Menu
     */
    public View() throws SQLException {
        initComponents();
        this.setPreferredSize(new Dimension(1280, 800));
        this.setSize(1280, 800);
        this.setResizable(false);
        setLocationRelativeTo(null);
        this.setResizable(false);
        viewController = new ViewController(this);
        pnlGames = new PanelGames();
        pnlClients = new PnelClients();
        pnlRental = new PanelRental();
        pnlPolicy = new PanelPolicy();
        pnlReports = new PanelReport();
        pnlReturnGames = new PanelReturnGames();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlPrincipal = new javax.swing.JPanel();
        jpnMenu = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jbttGames = new javax.swing.JButton();
        jbttClients = new javax.swing.JButton();
        jbttReports = new javax.swing.JButton();
        jbttPoliticas = new javax.swing.JButton();
        jbttClose = new javax.swing.JButton();
        jbttRental = new javax.swing.JButton();
        jbttClients2 = new javax.swing.JButton();
        jlbBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(new java.awt.Dimension(1280, 800));

        pnlPrincipal.setLayout(null);

        jpnMenu.setOpaque(false);

        jLabel1.setBackground(java.awt.SystemColor.textHighlight);
        jLabel1.setFont(new java.awt.Font("Lucida Sans Unicode", 3, 60)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("     GAME SHOP");

        jbttGames.setBackground(new java.awt.Color(60, 0, 0));
        jbttGames.setFont(new java.awt.Font("Lucida Sans Unicode", 0, 36)); // NOI18N
        jbttGames.setForeground(new java.awt.Color(255, 255, 255));
        jbttGames.setText("GAMES");
        jbttGames.setBorderPainted(false);
        jbttGames.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbttGamesActionPerformed(evt);
            }
        });

        jbttClients.setBackground(new java.awt.Color(60, 0, 0));
        jbttClients.setFont(new java.awt.Font("Lucida Sans Unicode", 0, 36)); // NOI18N
        jbttClients.setForeground(new java.awt.Color(255, 255, 255));
        jbttClients.setText("CLIENTS");
        jbttClients.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbttClientsActionPerformed(evt);
            }
        });

        jbttReports.setBackground(new java.awt.Color(60, 0, 0));
        jbttReports.setFont(new java.awt.Font("Lucida Sans Unicode", 0, 30)); // NOI18N
        jbttReports.setForeground(new java.awt.Color(255, 255, 255));
        jbttReports.setText("REPORTS");
        jbttReports.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbttReportsActionPerformed(evt);
            }
        });

        jbttPoliticas.setBackground(new java.awt.Color(60, 0, 0));
        jbttPoliticas.setFont(new java.awt.Font("Lucida Sans Unicode", 0, 24)); // NOI18N
        jbttPoliticas.setForeground(new java.awt.Color(255, 255, 255));
        jbttPoliticas.setText("POLICY");
        jbttPoliticas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbttPoliticasActionPerformed(evt);
            }
        });

        jbttClose.setBackground(new java.awt.Color(60, 0, 0));
        jbttClose.setFont(new java.awt.Font("Lucida Sans Unicode", 0, 18)); // NOI18N
        jbttClose.setForeground(new java.awt.Color(255, 255, 255));
        jbttClose.setText("CLOSE");
        jbttClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbttCloseActionPerformed(evt);
            }
        });

        jbttRental.setBackground(new java.awt.Color(60, 0, 0));
        jbttRental.setFont(new java.awt.Font("Lucida Sans Unicode", 0, 24)); // NOI18N
        jbttRental.setForeground(new java.awt.Color(255, 255, 255));
        jbttRental.setText("RENT A GAME");
        jbttRental.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbttRentalActionPerformed(evt);
            }
        });

        jbttClients2.setBackground(new java.awt.Color(60, 0, 0));
        jbttClients2.setFont(new java.awt.Font("Lucida Sans Unicode", 0, 24)); // NOI18N
        jbttClients2.setForeground(new java.awt.Color(255, 255, 255));
        jbttClients2.setText("RETURN A GAME");
        jbttClients2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbttClients2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpnMenuLayout = new javax.swing.GroupLayout(jpnMenu);
        jpnMenu.setLayout(jpnMenuLayout);
        jpnMenuLayout.setHorizontalGroup(
            jpnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnMenuLayout.createSequentialGroup()
                .addGroup(jpnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnMenuLayout.createSequentialGroup()
                        .addGroup(jpnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jbttReports, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbttRental, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbttGames, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jpnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jbttClients2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jpnMenuLayout.createSequentialGroup()
                                .addGroup(jpnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jbttClients, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jbttPoliticas, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 26, Short.MAX_VALUE))))
                    .addGroup(jpnMenuLayout.createSequentialGroup()
                        .addGap(166, 166, 166)
                        .addComponent(jbttClose, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpnMenuLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jpnMenuLayout.setVerticalGroup(
            jpnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(jpnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbttGames, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbttClients, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jpnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbttRental, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbttClients2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jpnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbttReports, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbttPoliticas, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jbttClose, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(137, Short.MAX_VALUE))
        );

        pnlPrincipal.add(jpnMenu);
        jpnMenu.setBounds(350, 10, 600, 750);

        jlbBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/py/edu/fiuni/gameshop/view/icons/icon5.jpg"))); // NOI18N
        jlbBackground.setText("jLabel1");
        pnlPrincipal.add(jlbBackground);
        jlbBackground.setBounds(0, 0, 1280, 800);

        getContentPane().add(pnlPrincipal, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // ACTIONS LISTENERS 

    private void jbttGamesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbttGamesActionPerformed
        ViewController.changePanel(pnlGames);
    }//GEN-LAST:event_jbttGamesActionPerformed

    private void jbttClientsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbttClientsActionPerformed
        ViewController.changePanel(pnlClients);
    }//GEN-LAST:event_jbttClientsActionPerformed

    private void jbttRentalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbttRentalActionPerformed
        ViewController.changePanel(pnlRental);
    }//GEN-LAST:event_jbttRentalActionPerformed

    private void jbttClients2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbttClients2ActionPerformed
        ViewController.changePanel(pnlReturnGames);
    }//GEN-LAST:event_jbttClients2ActionPerformed

    private void jbttPoliticasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbttPoliticasActionPerformed
        ViewController.changePanel(pnlPolicy);
    }//GEN-LAST:event_jbttPoliticasActionPerformed

    private void jbttReportsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbttReportsActionPerformed
        ViewController.changePanel(pnlReports);    }//GEN-LAST:event_jbttReportsActionPerformed

    private void jbttCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbttCloseActionPerformed
        System.exit(0);

    }//GEN-LAST:event_jbttCloseActionPerformed

    public void activateMenu() {
        this.pnlPrincipal.setVisible(true);

    }

    public void desactivateMenu() {
        this.pnlPrincipal.setVisible(false);

    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new View().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton jbttClients;
    private javax.swing.JButton jbttClients2;
    private javax.swing.JButton jbttClose;
    private javax.swing.JButton jbttGames;
    private javax.swing.JButton jbttPoliticas;
    private javax.swing.JButton jbttRental;
    private javax.swing.JButton jbttReports;
    private javax.swing.JLabel jlbBackground;
    private javax.swing.JPanel jpnMenu;
    private javax.swing.JPanel pnlPrincipal;
    // End of variables declaration//GEN-END:variables
}
