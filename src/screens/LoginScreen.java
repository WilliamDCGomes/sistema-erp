package screens;
import connectionbd.ConnectionModule;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 *
 * @author William Douglas
 */
public class LoginScreen extends javax.swing.JFrame {
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    public LoginScreen() {
        initComponents();
        //conexao = ConnectionModule.conector();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtDoLogin = new javax.swing.JLabel();
        inputPassword = new javax.swing.JPasswordField();
        txtLogin = new javax.swing.JLabel();
        txtPassoword = new javax.swing.JLabel();
        buttonLogin = new javax.swing.JButton();
        inputLogin = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("LOGIN");
        setResizable(false);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });
        addWindowStateListener(new java.awt.event.WindowStateListener() {
            public void windowStateChanged(java.awt.event.WindowEvent evt) {
                formWindowStateChanged(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(null);

        txtDoLogin.setFont(new java.awt.Font("Dialog", 1, 30)); // NOI18N
        txtDoLogin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtDoLogin.setText("FAÇA LOGIN");
        getContentPane().add(txtDoLogin);
        txtDoLogin.setBounds(170, 30, 184, 39);

        inputPassword.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        getContentPane().add(inputPassword);
        inputPassword.setBounds(230, 225, 220, 40);

        txtLogin.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        txtLogin.setText("LOGIN");
        getContentPane().add(txtLogin);
        txtLogin.setBounds(90, 130, 105, 30);

        txtPassoword.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        txtPassoword.setText("SENHA");
        getContentPane().add(txtPassoword);
        txtPassoword.setBounds(90, 230, 105, 30);

        buttonLogin.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        buttonLogin.setText("LOGAR");
        buttonLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLoginActionPerformed(evt);
            }
        });
        getContentPane().add(buttonLogin);
        buttonLogin.setBounds(90, 320, 90, 30);

        inputLogin.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        inputLogin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecionar" }));
        getContentPane().add(inputLogin);
        inputLogin.setBounds(230, 125, 220, 40);

        setSize(new java.awt.Dimension(536, 430));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        
    }//GEN-LAST:event_formWindowActivated

    private void buttonLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLoginActionPerformed
        MainMenu mainMenu = new MainMenu();
        this.dispose();
        mainMenu.setVisible(true);
    }//GEN-LAST:event_buttonLoginActionPerformed

    private void formWindowStateChanged(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowStateChanged
        
    }//GEN-LAST:event_formWindowStateChanged

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        
    }//GEN-LAST:event_formComponentResized

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
            java.util.logging.Logger.getLogger(LoginScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonLogin;
    private javax.swing.JComboBox<String> inputLogin;
    private javax.swing.JPasswordField inputPassword;
    private javax.swing.JLabel txtDoLogin;
    private javax.swing.JLabel txtLogin;
    private javax.swing.JLabel txtPassoword;
    // End of variables declaration//GEN-END:variables

    
}
