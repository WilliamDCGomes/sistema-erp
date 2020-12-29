package screens;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import connectionbd.ConnectionModule;
public class LocaleOrder extends javax.swing.JFrame {
    Connection connection = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    public LocaleOrder() {
        initComponents();
        ConnectionModule connect = new ConnectionModule();
        connection = connect.getConnectionMySQL();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtLocaleOrder = new javax.swing.JLabel();
        txtOrderCode = new javax.swing.JLabel();
        inputOrderCode = new javax.swing.JTextField();
        buttonLocale = new javax.swing.JButton();
        buttonAllOrders = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Localizar Pedido");
        setResizable(false);
        getContentPane().setLayout(null);

        txtLocaleOrder.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        txtLocaleOrder.setText("LOCALIZAR PEDIDO");
        getContentPane().add(txtLocaleOrder);
        txtLocaleOrder.setBounds(80, 10, 260, 32);

        txtOrderCode.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtOrderCode.setText("Código do Pedido");
        getContentPane().add(txtOrderCode);
        txtOrderCode.setBounds(40, 70, 150, 20);

        inputOrderCode.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(inputOrderCode);
        inputOrderCode.setBounds(40, 100, 180, 30);

        buttonLocale.setText("LOCALIZAR");
        buttonLocale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLocaleActionPerformed(evt);
            }
        });
        getContentPane().add(buttonLocale);
        buttonLocale.setBounds(40, 150, 120, 25);

        buttonAllOrders.setText("TODOS OS PEDIDOS");
        buttonAllOrders.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAllOrdersActionPerformed(evt);
            }
        });
        getContentPane().add(buttonAllOrders);
        buttonAllOrders.setBounds(200, 150, 160, 25);

        setSize(new java.awt.Dimension(405, 231));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonLocaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLocaleActionPerformed
        OrderScreen orderScreen = new OrderScreen();
        this.dispose();
        orderScreen.setVisible(true);
    }//GEN-LAST:event_buttonLocaleActionPerformed

    private void buttonAllOrdersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAllOrdersActionPerformed
        AllOrders allOrders = new AllOrders();
        this.dispose();
        allOrders.setVisible(true);
    }//GEN-LAST:event_buttonAllOrdersActionPerformed

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
            java.util.logging.Logger.getLogger(LocaleOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LocaleOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LocaleOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LocaleOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LocaleOrder().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAllOrders;
    private javax.swing.JButton buttonLocale;
    private javax.swing.JTextField inputOrderCode;
    private javax.swing.JLabel txtLocaleOrder;
    private javax.swing.JLabel txtOrderCode;
    // End of variables declaration//GEN-END:variables
}
