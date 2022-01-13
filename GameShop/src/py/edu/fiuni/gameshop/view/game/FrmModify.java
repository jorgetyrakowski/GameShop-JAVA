/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.edu.fiuni.gameshop.view.game;

import java.awt.Dimension;
import py.edu.fiuni.gameshop.controller.game.GameModifyController;
import py.edu.fiuni.gameshop.dao.mysql.MySQLGameDAO;
import py.edu.fiuni.gameshop.model.Game;

/*
 *UNIVERSIDAD NACIONAL DE ITAPUA
 *PROJECT GAMESHOP
 *
 * 2020 - SEGUNDO SEMESTRE
 *
 *  Jorge Tyrakowski & Pamela Horn
 */

/**
 * Class that contains the form to modify video games.
 */
public class FrmModify extends javax.swing.JFrame {

    private GameModifyController controller = null;
    private MySQLGameDAO dao = null;
    private PanelGames panel = null;
    private Game game = null;

    /**
     * Constructor where the necessary for the use of the panel is initialized.
     */
    public FrmModify(PanelGames panel, MySQLGameDAO dao, Game game) {
        initComponents();
        this.setSize(new Dimension(900, 620));
        this.setPreferredSize(new Dimension(900, 620));
        this.setResizable(false);
        setTitle("Game Form");
        setLocationRelativeTo(null);

        this.panel = panel;
        this.dao = dao;
        this.game = game;

        completeFields();
        controller = new GameModifyController(panel, this, dao, game);
        setVisible(true);
    }

    /**
     * Method in charge of completing the text fields with the information of the video game.
     */
    private void completeFields() {
        this.txtCompany.setText(game.getCompany());
        this.txtConsole.setText(game.getConsole());
        this.txtDescription.setText(game.getDescription());
        this.txtGenre.setText(game.getGenre());
        this.txtName.setText(game.getName());
        this.txtPrice.setText(Double.toString(game.getPrice()));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpnPrincipal = new javax.swing.JPanel();
        jlbCompany = new javax.swing.JLabel();
        jlbName = new javax.swing.JLabel();
        jlbGenre = new javax.swing.JLabel();
        jlbDescription = new javax.swing.JLabel();
        jlbPrice = new javax.swing.JLabel();
        jlblConsole = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        txtConsole = new javax.swing.JTextField();
        txtCompany = new javax.swing.JTextField();
        txtGenre = new javax.swing.JTextField();
        txtPrice = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();
        jpnUp = new javax.swing.JPanel();
        jlbGameForm = new javax.swing.JLabel();
        jplDown = new javax.swing.JPanel();
        jbttClear = new javax.swing.JButton();
        jbttModify = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(900, 620));
        setMinimumSize(new java.awt.Dimension(900, 620));
        setResizable(false);
        setSize(new java.awt.Dimension(900, 620));
        getContentPane().setLayout(null);

        jpnPrincipal.setBackground(new java.awt.Color(255, 255, 255));
        jpnPrincipal.setPreferredSize(new java.awt.Dimension(1280, 400));

        jlbCompany.setBackground(new java.awt.Color(0, 0, 0));
        jlbCompany.setFont(new java.awt.Font("Lucida Sans Unicode", 0, 18)); // NOI18N
        jlbCompany.setText("Company :");

        jlbName.setBackground(new java.awt.Color(0, 0, 0));
        jlbName.setFont(new java.awt.Font("Lucida Sans Unicode", 0, 18)); // NOI18N
        jlbName.setText("Name : ");

        jlbGenre.setBackground(new java.awt.Color(0, 0, 0));
        jlbGenre.setFont(new java.awt.Font("Lucida Sans Unicode", 0, 18)); // NOI18N
        jlbGenre.setText("Genre :");

        jlbDescription.setBackground(new java.awt.Color(0, 0, 0));
        jlbDescription.setFont(new java.awt.Font("Lucida Sans Unicode", 0, 18)); // NOI18N
        jlbDescription.setText("Description :");

        jlbPrice.setBackground(new java.awt.Color(0, 0, 0));
        jlbPrice.setFont(new java.awt.Font("Lucida Sans Unicode", 0, 18)); // NOI18N
        jlbPrice.setText("Price :");

        jlblConsole.setBackground(new java.awt.Color(0, 0, 0));
        jlblConsole.setFont(new java.awt.Font("Lucida Sans Unicode", 0, 18)); // NOI18N
        jlblConsole.setText("Console :");

        txtName.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N

        txtConsole.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N

        txtCompany.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N

        txtGenre.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N

        txtPrice.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N

        txtDescription.setColumns(20);
        txtDescription.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txtDescription.setLineWrap(true);
        txtDescription.setRows(5);
        txtDescription.setWrapStyleWord(true);
        txtDescription.setMaximumSize(new java.awt.Dimension(900, 620));
        txtDescription.setMinimumSize(new java.awt.Dimension(204, 169));
        jScrollPane1.setViewportView(txtDescription);

        javax.swing.GroupLayout jpnPrincipalLayout = new javax.swing.GroupLayout(jpnPrincipal);
        jpnPrincipal.setLayout(jpnPrincipalLayout);
        jpnPrincipalLayout.setHorizontalGroup(
            jpnPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnPrincipalLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(jpnPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlbName)
                    .addComponent(jlbCompany)
                    .addComponent(jlbDescription)
                    .addComponent(jlbPrice))
                .addGap(29, 29, 29)
                .addGroup(jpnPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpnPrincipalLayout.createSequentialGroup()
                        .addGroup(jpnPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCompany, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(147, 147, 147)
                        .addGroup(jpnPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlbGenre)
                            .addComponent(jlblConsole))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpnPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtConsole, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtGenre, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1))
                .addContainerGap(449, Short.MAX_VALUE))
        );
        jpnPrincipalLayout.setVerticalGroup(
            jpnPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnPrincipalLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(jpnPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlbName)
                    .addComponent(jlblConsole)
                    .addComponent(txtConsole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jpnPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnPrincipalLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(txtCompany, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpnPrincipalLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jlbCompany))
                    .addGroup(jpnPrincipalLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jpnPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlbGenre)
                            .addComponent(txtGenre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(26, 26, 26)
                .addGroup(jpnPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlbDescription)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jpnPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlbPrice))
                .addContainerGap(51, Short.MAX_VALUE))
        );

        getContentPane().add(jpnPrincipal);
        jpnPrincipal.setBounds(0, 112, 1299, 400);

        jpnUp.setBackground(new java.awt.Color(60, 0, 0));

        jlbGameForm.setBackground(new java.awt.Color(255, 255, 255));
        jlbGameForm.setFont(new java.awt.Font("Lucida Fax", 1, 70)); // NOI18N
        jlbGameForm.setForeground(new java.awt.Color(255, 255, 255));
        jlbGameForm.setText("Modify");

        javax.swing.GroupLayout jpnUpLayout = new javax.swing.GroupLayout(jpnUp);
        jpnUp.setLayout(jpnUpLayout);
        jpnUpLayout.setHorizontalGroup(
            jpnUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnUpLayout.createSequentialGroup()
                .addGap(304, 304, 304)
                .addComponent(jlbGameForm, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(713, Short.MAX_VALUE))
        );
        jpnUpLayout.setVerticalGroup(
            jpnUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnUpLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbGameForm, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jpnUp);
        jpnUp.setBounds(0, 0, 1299, 110);

        jplDown.setBackground(new java.awt.Color(60, 0, 0));
        jplDown.setPreferredSize(new java.awt.Dimension(1280, 100));

        jbttClear.setBackground(new java.awt.Color(255, 255, 255));
        jbttClear.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jbttClear.setText("CLEAR");

        jbttModify.setBackground(new java.awt.Color(255, 255, 255));
        jbttModify.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jbttModify.setText("MODIFY");

        javax.swing.GroupLayout jplDownLayout = new javax.swing.GroupLayout(jplDown);
        jplDown.setLayout(jplDownLayout);
        jplDownLayout.setHorizontalGroup(
            jplDownLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jplDownLayout.createSequentialGroup()
                .addContainerGap(657, Short.MAX_VALUE)
                .addComponent(jbttClear)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbttModify)
                .addGap(440, 440, 440))
        );
        jplDownLayout.setVerticalGroup(
            jplDownLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplDownLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jplDownLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbttClear)
                    .addComponent(jbttModify))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        getContentPane().add(jplDown);
        jplDown.setBounds(0, 512, 1299, 100);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JButton jbttClear;
    public javax.swing.JButton jbttModify;
    private javax.swing.JLabel jlbCompany;
    private javax.swing.JLabel jlbDescription;
    private javax.swing.JLabel jlbGameForm;
    private javax.swing.JLabel jlbGenre;
    private javax.swing.JLabel jlbName;
    private javax.swing.JLabel jlbPrice;
    private javax.swing.JLabel jlblConsole;
    private javax.swing.JPanel jplDown;
    private javax.swing.JPanel jpnPrincipal;
    private javax.swing.JPanel jpnUp;
    public javax.swing.JTextField txtCompany;
    public javax.swing.JTextField txtConsole;
    public javax.swing.JTextArea txtDescription;
    public javax.swing.JTextField txtGenre;
    public javax.swing.JTextField txtName;
    public javax.swing.JTextField txtPrice;
    // End of variables declaration//GEN-END:variables
}
