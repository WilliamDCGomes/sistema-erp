/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screens;

/**
 *
 * @author Alunos
 */
public class LocaleUser extends javax.swing.JFrame {

    /**
     * Creates new form LocaleUser
     */
    public LocaleUser() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtCPF = new javax.swing.JLabel();
        inputCPF = new javax.swing.JFormattedTextField();
        buttonLocale = new javax.swing.JButton();
        buttonAllUsers = new javax.swing.JButton();
        txtLocaleUser = new javax.swing.JLabel();
        txtLogin = new javax.swing.JLabel();
        inputLogin = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Localizar Usuário");
        setResizable(false);
        getContentPane().setLayout(null);

        txtCPF.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtCPF.setText("CPF");
        getContentPane().add(txtCPF);
        txtCPF.setBounds(30, 50, 36, 24);

        inputCPF.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(inputCPF);
        inputCPF.setBounds(30, 80, 160, 30);

        buttonLocale.setText("LOCALIZAR");
        buttonLocale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLocaleActionPerformed(evt);
            }
        });
        getContentPane().add(buttonLocale);
        buttonLocale.setBounds(30, 200, 100, 32);

        buttonAllUsers.setText("TODOS OS CLIENTES");
        buttonAllUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAllUsersActionPerformed(evt);
            }
        });
        getContentPane().add(buttonAllUsers);
        buttonAllUsers.setBounds(170, 200, 160, 32);

        txtLocaleUser.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        txtLocaleUser.setText("LOCALIZAR USUÁRIO");
        getContentPane().add(txtLocaleUser);
        txtLocaleUser.setBounds(49, 6, 253, 32);

        txtLogin.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtLogin.setText("Login");
        getContentPane().add(txtLogin);
        txtLogin.setBounds(30, 120, 90, 24);
        getContentPane().add(inputLogin);
        inputLogin.setBounds(30, 150, 160, 30);

        setSize(new java.awt.Dimension(373, 283));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonLocaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLocaleActionPerformed
        UserScreen userScreen = new UserScreen();
        this.dispose();
        userScreen.setVisible(true);
    }//GEN-LAST:event_buttonLocaleActionPerformed

    private void buttonAllUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAllUsersActionPerformed
        AllUsers allUsers = new AllUsers();
        this.dispose();
        allUsers.setVisible(true);
    }//GEN-LAST:event_buttonAllUsersActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(LocaleUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LocaleUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LocaleUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LocaleUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LocaleUser().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAllUsers;
    private javax.swing.JButton buttonLocale;
    private javax.swing.JFormattedTextField inputCPF;
    private javax.swing.JTextField inputLogin;
    private javax.swing.JLabel txtCPF;
    private javax.swing.JLabel txtLocaleUser;
    private javax.swing.JLabel txtLogin;
    // End of variables declaration//GEN-END:variables
}