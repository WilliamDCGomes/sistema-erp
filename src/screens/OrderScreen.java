package screens;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import connectionbd.ConnectionModule;
import javax.swing.UnsupportedLookAndFeelException;
public class OrderScreen extends javax.swing.JFrame {
    Connection connection = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    public OrderScreen() {
        initComponents();
        ConnectionModule connect = new ConnectionModule();
        connection = connect.getConnectionMySQL();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtFormPayment = new javax.swing.JLabel();
        outputInCash = new javax.swing.JRadioButton();
        outputTerm = new javax.swing.JRadioButton();
        txtPaymentMethod = new javax.swing.JLabel();
        outputFormPayment = new javax.swing.JComboBox<>();
        txtDateOfSale = new javax.swing.JLabel();
        outputDateOfSale = new javax.swing.JFormattedTextField();
        txtObservation = new javax.swing.JLabel();
        outputDeliveryForecast = new javax.swing.JFormattedTextField();
        txtTotal = new javax.swing.JLabel();
        outputTotal = new javax.swing.JLabel();
        txtOrder = new javax.swing.JLabel();
        buttonLocale = new javax.swing.JButton();
        txtStatus = new javax.swing.JLabel();
        outputFinishSale = new javax.swing.JRadioButton();
        outputPendingSale = new javax.swing.JRadioButton();
        txtDeliveryForecast = new javax.swing.JLabel();
        observationOrder = new javax.swing.JScrollPane();
        outputObservation = new javax.swing.JTextArea();
        buttonEdit = new javax.swing.JButton();
        tableOrder = new javax.swing.JScrollPane();
        tableItems = new javax.swing.JTable();
        txtItems = new javax.swing.JLabel();
        buttonCancel = new javax.swing.JButton();
        buttonPrinter = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pedido: 15");
        setResizable(false);
        getContentPane().setLayout(null);

        txtFormPayment.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtFormPayment.setText("Forma de Pagamento");
        getContentPane().add(txtFormPayment);
        txtFormPayment.setBounds(20, 370, 149, 27);

        outputInCash.setText("A Vista");
        outputInCash.setEnabled(false);
        getContentPane().add(outputInCash);
        outputInCash.setBounds(16, 400, 61, 23);

        outputTerm.setText("A Prazo");
        outputTerm.setEnabled(false);
        getContentPane().add(outputTerm);
        outputTerm.setBounds(110, 400, 64, 23);

        txtPaymentMethod.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtPaymentMethod.setText("Meio de Pagamento");
        getContentPane().add(txtPaymentMethod);
        txtPaymentMethod.setBounds(290, 370, 140, 27);

        outputFormPayment.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecionar", "Dinheiro", "Boleto", "Carnê", "Cartão", "Cheque" }));
        outputFormPayment.setEnabled(false);
        outputFormPayment.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                outputFormPaymentItemStateChanged(evt);
            }
        });
        outputFormPayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                outputFormPaymentActionPerformed(evt);
            }
        });
        getContentPane().add(outputFormPayment);
        outputFormPayment.setBounds(290, 400, 102, 25);

        txtDateOfSale.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtDateOfSale.setText("Data do Pedido");
        getContentPane().add(txtDateOfSale);
        txtDateOfSale.setBounds(20, 440, 108, 27);

        try {
            outputDateOfSale.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        outputDateOfSale.setEnabled(false);
        outputDateOfSale.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        getContentPane().add(outputDateOfSale);
        outputDateOfSale.setBounds(150, 440, 95, 25);

        txtObservation.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtObservation.setText("Observação");
        getContentPane().add(txtObservation);
        txtObservation.setBounds(530, 80, 86, 27);

        try {
            outputDeliveryForecast.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        outputDeliveryForecast.setEnabled(false);
        outputDeliveryForecast.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        getContentPane().add(outputDeliveryForecast);
        outputDeliveryForecast.setBounds(460, 440, 95, 25);

        txtTotal.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtTotal.setText("Total");
        getContentPane().add(txtTotal);
        txtTotal.setBounds(810, 370, 36, 20);

        outputTotal.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        outputTotal.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        outputTotal.setText("0,00");
        getContentPane().add(outputTotal);
        outputTotal.setBounds(740, 400, 110, 24);

        txtOrder.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        txtOrder.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtOrder.setText("PEDIDO");
        getContentPane().add(txtOrder);
        txtOrder.setBounds(340, 20, 190, 32);

        buttonLocale.setText("LOCALIZAR");
        buttonLocale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLocaleActionPerformed(evt);
            }
        });
        getContentPane().add(buttonLocale);
        buttonLocale.setBounds(120, 480, 110, 25);

        txtStatus.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtStatus.setText("Status:");
        getContentPane().add(txtStatus);
        txtStatus.setBounds(540, 370, 57, 27);

        outputFinishSale.setText("Finalizada");
        outputFinishSale.setEnabled(false);
        getContentPane().add(outputFinishSale);
        outputFinishSale.setBounds(533, 400, 73, 20);

        outputPendingSale.setText("Pendente");
        outputPendingSale.setEnabled(false);
        getContentPane().add(outputPendingSale);
        outputPendingSale.setBounds(630, 400, 90, 20);

        txtDeliveryForecast.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtDeliveryForecast.setText("Previsão de Entrega");
        getContentPane().add(txtDeliveryForecast);
        txtDeliveryForecast.setBounds(290, 440, 145, 27);

        outputObservation.setColumns(20);
        outputObservation.setRows(5);
        observationOrder.setViewportView(outputObservation);

        getContentPane().add(observationOrder);
        observationOrder.setBounds(530, 110, 320, 240);

        buttonEdit.setText("EDITAR");
        buttonEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEditActionPerformed(evt);
            }
        });
        getContentPane().add(buttonEdit);
        buttonEdit.setBounds(20, 480, 70, 25);

        tableItems.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Cód Produto", "Descrição", "Quantidade", "Valor", "Cliente", "Fornecedor"
            }
        ));
        tableOrder.setViewportView(tableItems);

        getContentPane().add(tableOrder);
        tableOrder.setBounds(20, 110, 490, 240);

        txtItems.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtItems.setText("Itens");
        getContentPane().add(txtItems);
        txtItems.setBounds(20, 80, 40, 20);

        buttonCancel.setText("CANCELAR PEDIDO");
        getContentPane().add(buttonCancel);
        buttonCancel.setBounds(380, 480, 140, 25);

        buttonPrinter.setText("IMPRIMIR");
        getContentPane().add(buttonPrinter);
        buttonPrinter.setBounds(260, 480, 90, 25);

        setSize(new java.awt.Dimension(878, 556));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void outputFormPaymentItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_outputFormPaymentItemStateChanged

    }//GEN-LAST:event_outputFormPaymentItemStateChanged

    private void outputFormPaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_outputFormPaymentActionPerformed
        
    }//GEN-LAST:event_outputFormPaymentActionPerformed

    private void buttonLocaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLocaleActionPerformed
        LocaleOrder localeOrder = new LocaleOrder();
        this.dispose();
        localeOrder.setVisible(true);
    }//GEN-LAST:event_buttonLocaleActionPerformed

    private void buttonEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEditActionPerformed
        NewOrder newOrder = new NewOrder();
        newOrder.txtNewOrder.setText("EDITAR PEDIDO");
        newOrder.setTitle("Editar Pedido");
        this.dispose();
        newOrder.setVisible(true);
    }//GEN-LAST:event_buttonEditActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws UnsupportedLookAndFeelException {
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
            java.util.logging.Logger.getLogger(OrderScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OrderScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OrderScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OrderScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCancel;
    private javax.swing.JButton buttonEdit;
    private javax.swing.JButton buttonLocale;
    private javax.swing.JButton buttonPrinter;
    private javax.swing.JScrollPane observationOrder;
    private javax.swing.JFormattedTextField outputDateOfSale;
    private javax.swing.JFormattedTextField outputDeliveryForecast;
    private javax.swing.JRadioButton outputFinishSale;
    private javax.swing.JComboBox<String> outputFormPayment;
    private javax.swing.JRadioButton outputInCash;
    private javax.swing.JTextArea outputObservation;
    private javax.swing.JRadioButton outputPendingSale;
    private javax.swing.JRadioButton outputTerm;
    private javax.swing.JLabel outputTotal;
    private javax.swing.JTable tableItems;
    private javax.swing.JScrollPane tableOrder;
    private javax.swing.JLabel txtDateOfSale;
    private javax.swing.JLabel txtDeliveryForecast;
    private javax.swing.JLabel txtFormPayment;
    private javax.swing.JLabel txtItems;
    private javax.swing.JLabel txtObservation;
    private javax.swing.JLabel txtOrder;
    private javax.swing.JLabel txtPaymentMethod;
    private javax.swing.JLabel txtStatus;
    private javax.swing.JLabel txtTotal;
    // End of variables declaration//GEN-END:variables
}
