package screens.screenslogin;
import conexaobd.ModuloConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import screens.cadastrescreens.CadastreUsers;
import screens.mainmenu.MainMenu;
import screens.mainmenu.ScreenUserSize;
import setsoons.SetAndOrganizeSoons;
/**
 *
 * @author William Douglas
 */
public class LoginScreen extends setsoons.AuxiliaryJFrame {
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    public LoginScreen() {
        initComponents();
        //conexao = ModuloConexao.conector();
    }
    
    SetAndOrganizeSoons setAndOrganizeSoons = new SetAndOrganizeSoons();
    boolean inicializedWindow = false;
    private void CallMainMenu(){
        ScreenUserSize screenSize = new ScreenUserSize();
        int width = screenSize.sizeOfScreen();
        MainMenu mainMenu = new MainMenu();
        this.dispose();
        mainMenu.setSize(width, mainMenu.getHeight());
        mainMenu.setVisible(true);
    }
    private void CallCadastreScreen(){
        CadastreUsers cadastreScreen = new CadastreUsers();
        this.dispose();
        cadastreScreen.setVisible(true);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        soonProxxi = new javax.swing.JLabel();
        soonTavile = new javax.swing.JLabel();
        textMakeLogin = new javax.swing.JLabel();
        inputLogin = new javax.swing.JTextField();
        inputSenha = new javax.swing.JPasswordField();
        textLogin = new javax.swing.JLabel();
        textPassoword = new javax.swing.JLabel();
        buttonLogin = new javax.swing.JButton();
        buttonCadastre = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("LOGIN");
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

        textMakeLogin.setFont(new java.awt.Font("Dialog", 1, 30)); // NOI18N
        textMakeLogin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textMakeLogin.setText("FAÇA LOGIN");

        inputLogin.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

        inputSenha.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

        textLogin.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        textLogin.setText("LOGIN");

        textPassoword.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        textPassoword.setText("SENHA");

        buttonLogin.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        buttonLogin.setText("LOGAR");
        buttonLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLoginActionPerformed(evt);
            }
        });

        buttonCadastre.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        buttonCadastre.setText("CADASTRAR");
        buttonCadastre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCadastreActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(soonTavile, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 207, Short.MAX_VALUE)
                .addComponent(soonProxxi, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textPassoword, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textLogin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(inputSenha)
                    .addComponent(inputLogin))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(textMakeLogin)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonCadastre)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(soonTavile, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(soonProxxi, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(textMakeLogin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textPassoword, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonLogin)
                    .addComponent(buttonCadastre))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(569, 444));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        if(inicializedWindow==false){
            inicializedWindow = true;
            setAndOrganizeSoons.SetSoons(this);
            setAndOrganizeSoons.SetLocationSoons(this, soonTavile.getX(), soonTavile.getY(), soonProxxi.getX(), soonProxxi.getY());
        }
    }//GEN-LAST:event_formWindowActivated

    private void buttonCadastreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCadastreActionPerformed
       CallCadastreScreen();
    }//GEN-LAST:event_buttonCadastreActionPerformed

    private void buttonLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLoginActionPerformed
        CallMainMenu();
    }//GEN-LAST:event_buttonLoginActionPerformed

    private void formWindowStateChanged(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowStateChanged
        
    }//GEN-LAST:event_formWindowStateChanged

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        setAndOrganizeSoons.SetLocationSoons(this, soonTavile.getX(), soonTavile.getY(), soonProxxi.getX(), soonProxxi.getY());
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
    private javax.swing.JButton buttonCadastre;
    private javax.swing.JButton buttonLogin;
    private javax.swing.JTextField inputLogin;
    private javax.swing.JPasswordField inputSenha;
    public static javax.swing.JLabel soonProxxi;
    public static javax.swing.JLabel soonTavile;
    private javax.swing.JLabel textLogin;
    private javax.swing.JLabel textMakeLogin;
    private javax.swing.JLabel textPassoword;
    // End of variables declaration//GEN-END:variables

    
}
