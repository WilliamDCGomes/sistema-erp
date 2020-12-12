package screens;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import connectionbd.ConnectionModule;
import javax.swing.JOptionPane;

public class SaleScreen extends javax.swing.JFrame {
    int x = 0;
    Connection connection = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    PreparedStatement pst2 = null;
    ResultSet rs2 = null;
    String cpfClient = null;
    public SaleScreen() {
        initComponents();
        ConnectionModule connect = new ConnectionModule();
        connection = connect.getConnectionMySQL();
    }
    private void getData(){
        String[] aux = this.getTitle().split(" ");
        int codSale = Integer.parseInt(aux[1]);
        String sql ="select codSaller, paymentForm, paymentMethod, codClient, dateSale, statusSale, totalValue from sale where codSale = ?";
        try {
            pst=connection.prepareStatement(sql);
            pst.setInt(1, codSale);
            rs= pst.executeQuery();
            if(rs.next()) {
                outputCodSaller.setText(Integer.toString(rs.getInt(1)));
                outputFormPayment.setText(rs.getString(2));
                outputPaymentMethod.setSelectedItem(rs.getString(3));
                cpfClient = rs.getString(4);
                outputDateSale.setText(rs.getString(5));
                outputStatus.setSelectedItem(rs.getString(6));
                outputTotal.setText(rs.getString(7).replace(".", ","));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtCodSale = new javax.swing.JLabel();
        outputCodSale = new javax.swing.JTextField();
        txtCodSaller = new javax.swing.JLabel();
        outputCodSaller = new javax.swing.JTextField();
        txtFormPayment = new javax.swing.JLabel();
        outputFormPayment = new javax.swing.JTextField();
        txtPaymentMethod = new javax.swing.JLabel();
        outputPaymentMethod = new javax.swing.JComboBox<>();
        txtClient = new javax.swing.JLabel();
        outputClient = new javax.swing.JTextField();
        txtDateOfSale = new javax.swing.JLabel();
        txtTotal = new javax.swing.JLabel();
        outputTotal = new javax.swing.JLabel();
        tableSale = new javax.swing.JScrollPane();
        tableSoldItems = new javax.swing.JTable();
        txtItems = new javax.swing.JLabel();
        buttonEdit = new javax.swing.JButton();
        buttonDelete = new javax.swing.JButton();
        buttonLocale = new javax.swing.JButton();
        buttonAllSales = new javax.swing.JButton();
        txtSale = new javax.swing.JLabel();
        txtStatus = new javax.swing.JLabel();
        buttonShowPaymentMethod = new javax.swing.JButton();
        buttonPrinter = new javax.swing.JButton();
        txtNameEmployee = new javax.swing.JLabel();
        inputNameEmployee = new javax.swing.JTextField();
        outputStatus = new javax.swing.JComboBox<>();
        outputDateSale = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Venda");
        setResizable(false);
        getContentPane().setLayout(null);

        txtCodSale.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        txtCodSale.setText("Código da Venda");
        getContentPane().add(txtCodSale);
        txtCodSale.setBounds(11, 65, 123, 19);
        getContentPane().add(outputCodSale);
        outputCodSale.setBounds(11, 90, 80, 30);

        txtCodSaller.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        txtCodSaller.setText("Código do Vendedor");
        getContentPane().add(txtCodSaller);
        txtCodSaller.setBounds(210, 60, 148, 30);
        getContentPane().add(outputCodSaller);
        outputCodSaller.setBounds(210, 90, 100, 30);

        txtFormPayment.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        txtFormPayment.setText("Forma de Pagamento");
        getContentPane().add(txtFormPayment);
        txtFormPayment.setBounds(520, 130, 156, 30);
        getContentPane().add(outputFormPayment);
        outputFormPayment.setBounds(520, 160, 170, 30);

        txtPaymentMethod.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtPaymentMethod.setText("Meio de Pagamento");
        getContentPane().add(txtPaymentMethod);
        txtPaymentMethod.setBounds(10, 130, 140, 27);

        outputPaymentMethod.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecionar", "Dinheiro", "Boleto", "Carnê", "Cartão", "Cheque" }));
        outputPaymentMethod.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                outputPaymentMethodItemStateChanged(evt);
            }
        });
        outputPaymentMethod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                outputPaymentMethodActionPerformed(evt);
            }
        });
        getContentPane().add(outputPaymentMethod);
        outputPaymentMethod.setBounds(10, 160, 120, 30);

        txtClient.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtClient.setText("Cliente");
        getContentPane().add(txtClient);
        txtClient.setBounds(180, 130, 51, 27);
        getContentPane().add(outputClient);
        outputClient.setBounds(180, 160, 310, 30);

        txtDateOfSale.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtDateOfSale.setText("Data da Venda");
        getContentPane().add(txtDateOfSale);
        txtDateOfSale.setBounds(10, 200, 101, 27);

        txtTotal.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtTotal.setText("Total");
        getContentPane().add(txtTotal);
        txtTotal.setBounds(710, 490, 40, 20);

        outputTotal.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        outputTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        outputTotal.setText("0,00");
        getContentPane().add(outputTotal);
        outputTotal.setBounds(620, 510, 130, 24);

        tableSoldItems.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código do Produto", "Nome do Produto", "Preço de Venda", "Valor Parcial", "Quantidade"
            }
        ));
        tableSale.setViewportView(tableSoldItems);

        getContentPane().add(tableSale);
        tableSale.setBounds(10, 300, 740, 176);

        txtItems.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtItems.setText("Itens");
        getContentPane().add(txtItems);
        txtItems.setBounds(10, 270, 35, 27);

        buttonEdit.setText("EDITAR");
        buttonEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEditActionPerformed(evt);
            }
        });
        getContentPane().add(buttonEdit);
        buttonEdit.setBounds(140, 510, 80, 25);

        buttonDelete.setText("EXCLUIR");
        buttonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDeleteActionPerformed(evt);
            }
        });
        getContentPane().add(buttonDelete);
        buttonDelete.setBounds(240, 510, 90, 25);

        buttonLocale.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        buttonLocale.setText("PESQUISAR");
        getContentPane().add(buttonLocale);
        buttonLocale.setBounds(93, 90, 100, 30);

        buttonAllSales.setText("TODAS AS VENDAS");
        buttonAllSales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAllSalesActionPerformed(evt);
            }
        });
        getContentPane().add(buttonAllSales);
        buttonAllSales.setBounds(350, 510, 140, 25);

        txtSale.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtSale.setText("Venda");
        getContentPane().add(txtSale);
        txtSale.setBounds(340, 20, 74, 29);

        txtStatus.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtStatus.setText("Status:");
        getContentPane().add(txtStatus);
        txtStatus.setBounds(170, 200, 49, 27);

        buttonShowPaymentMethod.setText("PAGAMENTO");
        buttonShowPaymentMethod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonShowPaymentMethodActionPerformed(evt);
            }
        });
        getContentPane().add(buttonShowPaymentMethod);
        buttonShowPaymentMethod.setBounds(10, 510, 110, 25);

        buttonPrinter.setText("IMPRIMIR");
        getContentPane().add(buttonPrinter);
        buttonPrinter.setBounds(510, 510, 90, 25);

        txtNameEmployee.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        txtNameEmployee.setText("Nome do Vendedor");
        getContentPane().add(txtNameEmployee);
        txtNameEmployee.setBounds(370, 60, 160, 30);

        inputNameEmployee.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        getContentPane().add(inputNameEmployee);
        inputNameEmployee.setBounds(370, 90, 380, 30);

        outputStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pendente", "Finalizado" }));
        outputStatus.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                outputStatusItemStateChanged(evt);
            }
        });
        outputStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                outputStatusActionPerformed(evt);
            }
        });
        getContentPane().add(outputStatus);
        outputStatus.setBounds(170, 230, 110, 30);
        getContentPane().add(outputDateSale);
        outputDateSale.setBounds(10, 230, 100, 30);

        setSize(new java.awt.Dimension(773, 579));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void outputPaymentMethodItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_outputPaymentMethodItemStateChanged

    }//GEN-LAST:event_outputPaymentMethodItemStateChanged

    private void outputPaymentMethodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_outputPaymentMethodActionPerformed
        
    }//GEN-LAST:event_outputPaymentMethodActionPerformed

    private void buttonShowPaymentMethodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonShowPaymentMethodActionPerformed
        FormPayment formPayment = new FormPayment();
        formPayment.buttonFinish.setText("DEBITAR");
        formPayment.setVisible(true);
    }//GEN-LAST:event_buttonShowPaymentMethodActionPerformed

    private void buttonAllSalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAllSalesActionPerformed
        PendingSale pendingSale = new PendingSale();
        pendingSale.txtPendingSale.setText("Todas as Vendas");
        pendingSale.setTitle("Todas as Vendas");
        this.dispose();
        pendingSale.setVisible(true);
    }//GEN-LAST:event_buttonAllSalesActionPerformed

    private void buttonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDeleteActionPerformed
        JOptionPane.showMessageDialog(null, "VENDA EXCLUIDA COM SUCESSO");
    }//GEN-LAST:event_buttonDeleteActionPerformed

    private void buttonEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEditActionPerformed
        NewSale newSale = new NewSale();
        newSale.txtNewSale.setText("Editar Venda");
        newSale.setTitle("Editar Venda");
        newSale.buttonCancele.setText("EXCLUIR");
        this.dispose();
        newSale.setVisible(true);
    }//GEN-LAST:event_buttonEditActionPerformed

    private void outputStatusItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_outputStatusItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_outputStatusItemStateChanged

    private void outputStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_outputStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_outputStatusActionPerformed

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
            java.util.logging.Logger.getLogger(SaleScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SaleScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SaleScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SaleScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SaleScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAllSales;
    private javax.swing.JButton buttonDelete;
    private javax.swing.JButton buttonEdit;
    private javax.swing.JButton buttonLocale;
    private javax.swing.JButton buttonPrinter;
    private javax.swing.JButton buttonShowPaymentMethod;
    private javax.swing.JTextField inputNameEmployee;
    private javax.swing.JTextField outputClient;
    private javax.swing.JTextField outputCodSale;
    private javax.swing.JTextField outputCodSaller;
    private javax.swing.JTextField outputDateSale;
    private javax.swing.JTextField outputFormPayment;
    private javax.swing.JComboBox<String> outputPaymentMethod;
    private javax.swing.JComboBox<String> outputStatus;
    private javax.swing.JLabel outputTotal;
    private javax.swing.JScrollPane tableSale;
    private javax.swing.JTable tableSoldItems;
    private javax.swing.JLabel txtClient;
    private javax.swing.JLabel txtCodSale;
    private javax.swing.JLabel txtCodSaller;
    private javax.swing.JLabel txtDateOfSale;
    private javax.swing.JLabel txtFormPayment;
    private javax.swing.JLabel txtItems;
    private javax.swing.JLabel txtNameEmployee;
    private javax.swing.JLabel txtPaymentMethod;
    private javax.swing.JLabel txtSale;
    private javax.swing.JLabel txtStatus;
    private javax.swing.JLabel txtTotal;
    // End of variables declaration//GEN-END:variables
}
