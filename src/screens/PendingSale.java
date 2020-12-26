package screens;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import connectionbd.ConnectionModule;
import net.proteanit.sql.DbUtils;
public class PendingSale extends javax.swing.JFrame {
    Connection connection = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    int x = 0;
    int x2 = 0;
    int x3 = 0;
    public PendingSale() {
        initComponents();
        ConnectionModule connect = new ConnectionModule();
        connection = connect.getConnectionMySQL();
    }
    private void getAllSales(){
        String sql ="select codSale as 'Código da Venda', totalValue as 'Valor', dateSale as 'Data', paymentForm as 'Forma de Pagamento', paymentMethod as 'Meio de Pagamento' from sale";
        try {
            pst=connection.prepareStatement(sql);
            rs= pst.executeQuery();
            tablePending.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void getSalesCPF(){
        String sql ="select codSale as 'Código da Venda', totalValue as 'Valor', dateSale as 'Data', paymentForm as 'Forma de Pagamento', paymentMethod as 'Meio de Pagamento' from sale where codClient like '" + inputCPF.getText() + "%'";
        try {
            pst=connection.prepareStatement(sql);
            rs = pst.executeQuery();
            tablePending.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void getAllSalesPending(){
        String sql ="select codSale as 'Código da Venda', totalValue as 'Valor', dateSale as 'Data', paymentForm as 'Forma de Pagamento', paymentMethod as 'Meio de Pagamento' from sale where statusSale = 'Pendente'";
        try {
            pst=connection.prepareStatement(sql);
            rs= pst.executeQuery();
            tablePending.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void getSalesCPFPending(){
        String sql ="select codSale as 'Código da Venda', totalValue as 'Valor', dateSale as 'Data', paymentForm as 'Forma de Pagamento', paymentMethod as 'Meio de Pagamento' from sale where codClient like '" + inputCPF.getText() + "%' and statusSale = 'Pendente'";
        try {
            pst=connection.prepareStatement(sql);
            rs = pst.executeQuery();
            tablePending.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtPendingSale = new javax.swing.JLabel();
        tablePendingSale = new javax.swing.JScrollPane();
        tablePending = new javax.swing.JTable();
        buttonShow = new javax.swing.JButton();
        inputCPF = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Vendas Pendete");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(null);

        txtPendingSale.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        txtPendingSale.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtPendingSale.setText("Vendas Pendentes");
        getContentPane().add(txtPendingSale);
        txtPendingSale.setBounds(192, 17, 280, 32);

        tablePending.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Código da Venda", "Valor", "Data", "Forma de Pagamento", "Meio de Pagamento"
            }
        ));
        tablePendingSale.setViewportView(tablePending);

        getContentPane().add(tablePendingSale);
        tablePendingSale.setBounds(10, 110, 620, 304);

        buttonShow.setText("MOSTRAR");
        buttonShow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonShowActionPerformed(evt);
            }
        });
        getContentPane().add(buttonShow);
        buttonShow.setBounds(220, 70, 100, 30);

        inputCPF.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        inputCPF.setText("CPF DO CLIENTE");
        inputCPF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                inputCPFFocusGained(evt);
            }
        });
        inputCPF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                inputCPFKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                inputCPFKeyTyped(evt);
            }
        });
        getContentPane().add(inputCPF);
        inputCPF.setBounds(10, 70, 190, 30);

        setSize(new java.awt.Dimension(653, 474));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonShowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonShowActionPerformed
        int set = tablePending.getSelectedRow();
        if(set>=0){
            SaleScreen saleScreen = new SaleScreen();
            saleScreen.setTitle("Venda: " + tablePending.getModel().getValueAt(set,0).toString());
            saleScreen.setVisible(true);
        }
        else{
            JOptionPane.showMessageDialog(null, "SELECIONE UM REGISTRO ANTES");
        }
    }//GEN-LAST:event_buttonShowActionPerformed

    private void inputCPFFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputCPFFocusGained
        inputCPF.selectAll();
    }//GEN-LAST:event_inputCPFFocusGained

    private void inputCPFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputCPFKeyReleased
        if(!inputCPF.getText().equals("CPF DO CLIENTE") && txtPendingSale.getText().equals("Todas as Vendas")){
            getSalesCPF();
        }
        else if(!inputCPF.getText().equals("CPF DO CLIENTE") && txtPendingSale.getText().equals("Vendas Pendentes")){
            getSalesCPFPending();
        }
    }//GEN-LAST:event_inputCPFKeyReleased

    private void inputCPFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputCPFKeyTyped
        if(txtPendingSale.getText().equals("Todas as Vendas")){
            if(inputCPF.getText().equals("")){
                inputCPF.setText("CPF DO CLIENTE");
                getAllSales();
                x=0;
            }
            else if(x==0){
                x++;
                inputCPF.setText("");
            }
        }
        else if(txtPendingSale.getText().equals("Vendas Pendentes")){
            if(inputCPF.getText().equals("")){
                inputCPF.setText("CPF DO CLIENTE");
                getAllSalesPending();
                x=0;
            }
            else if(x==0){
                x++;
                inputCPF.setText("");
            }
        }
    }//GEN-LAST:event_inputCPFKeyTyped

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        if(x3==0){
            x3++;
            inputCPF.setText("CPF DO CLIENTE");
            if(txtPendingSale.getText().equals("Todas as Vendas")){
                getAllSales();
            }
            else if(txtPendingSale.getText().equals("Vendas Pendentes")){
                getAllSalesPending();
            }
        }
    }//GEN-LAST:event_formWindowActivated

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
            java.util.logging.Logger.getLogger(PendingSale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PendingSale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PendingSale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PendingSale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PendingSale().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonShow;
    private javax.swing.JTextField inputCPF;
    private javax.swing.JTable tablePending;
    private javax.swing.JScrollPane tablePendingSale;
    public static javax.swing.JLabel txtPendingSale;
    // End of variables declaration//GEN-END:variables
}
