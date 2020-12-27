package screens;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import connectionbd.ConnectionModule;
import formattingmask.MaskJustNumbers;
public class LocaleSale extends javax.swing.JFrame {
    Connection connection = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    public LocaleSale() {
        initComponents();
        ConnectionModule connect = new ConnectionModule();
        connection = connect.getConnectionMySQL();
        inputCodOfSale.setDocument(new MaskJustNumbers());
    }
    private boolean hasPaymentForm(){
        String sql ="select codSale from sale where codSale = ?";
        try {
            pst=connection.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(inputCodOfSale.getText()));
            rs= pst.executeQuery();
            if(rs.next()) {
                return true;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return false;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtCodOfSale = new javax.swing.JLabel();
        inputCodOfSale = new javax.swing.JTextField();
        buttonLocale = new javax.swing.JButton();
        buttonAllSales = new javax.swing.JButton();
        txtLocaleSale = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Localizar Venda");
        setResizable(false);
        getContentPane().setLayout(null);

        txtCodOfSale.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtCodOfSale.setText("Código da Venda");
        getContentPane().add(txtCodOfSale);
        txtCodOfSale.setBounds(40, 60, 120, 20);

        inputCodOfSale.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(inputCodOfSale);
        inputCodOfSale.setBounds(40, 90, 230, 30);

        buttonLocale.setText("LOCALIZAR");
        buttonLocale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLocaleActionPerformed(evt);
            }
        });
        getContentPane().add(buttonLocale);
        buttonLocale.setBounds(40, 140, 100, 25);

        buttonAllSales.setText("TODAS AS VENDAS");
        buttonAllSales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAllSalesActionPerformed(evt);
            }
        });
        getContentPane().add(buttonAllSales);
        buttonAllSales.setBounds(170, 140, 140, 25);

        txtLocaleSale.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        txtLocaleSale.setText("Localizar Venda");
        getContentPane().add(txtLocaleSale);
        txtLocaleSale.setBounds(80, 10, 183, 32);

        setSize(new java.awt.Dimension(358, 222));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonAllSalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAllSalesActionPerformed
        PendingSale pendingSale = new PendingSale();
        pendingSale.txtPendingSale.setText("Todas as Vendas");
        pendingSale.setTitle("Todas as Vendas");
        this.dispose();
        pendingSale.setVisible(true);
    }//GEN-LAST:event_buttonAllSalesActionPerformed

    private void buttonLocaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLocaleActionPerformed
        if(inputCodOfSale.getText().equals("")){
            JOptionPane.showMessageDialog(null, "INSIRA O CÓDIGO DE UMA VENDA!");
        }
        else if(hasPaymentForm()){
            SaleScreen saleScreen = new SaleScreen();
            saleScreen.setTitle("Venda: " + inputCodOfSale.getText());
            this.dispose();
            saleScreen.setVisible(true);
        }
        else{
            JOptionPane.showMessageDialog(null, "ESSA VENDA NÃO EXISTE NO BANCO DE DADOS");
        }
    }//GEN-LAST:event_buttonLocaleActionPerformed

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
            java.util.logging.Logger.getLogger(LocaleSale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LocaleSale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LocaleSale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LocaleSale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LocaleSale().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAllSales;
    private javax.swing.JButton buttonLocale;
    private javax.swing.JTextField inputCodOfSale;
    private javax.swing.JLabel txtCodOfSale;
    private javax.swing.JLabel txtLocaleSale;
    // End of variables declaration//GEN-END:variables
}
