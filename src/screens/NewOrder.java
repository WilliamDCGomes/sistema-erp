package screens;
import functioncontroller.GetDate;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import connectionbd.ConnectionModule;
public class NewOrder extends javax.swing.JFrame {
    int x = 0;
    Connection connection = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    public NewOrder() {
        initComponents();
        ConnectionModule connect = new ConnectionModule();
        connection = connect.getConnectionMySQL();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        groupFormPayment = new javax.swing.ButtonGroup();
        groupStatus = new javax.swing.ButtonGroup();
        txtObservation = new javax.swing.JLabel();
        inputProductCode = new javax.swing.JTextField();
        buttonNewProduct = new javax.swing.JButton();
        txtQuantity = new javax.swing.JLabel();
        inputQuantity = new javax.swing.JTextField();
        tableNewOrder = new javax.swing.JScrollPane();
        tableItems = new javax.swing.JTable();
        txtProductCode = new javax.swing.JLabel();
        txtClient = new javax.swing.JLabel();
        inputClient = new javax.swing.JTextField();
        buttonLocaleClient = new javax.swing.JButton();
        buttonNewClient = new javax.swing.JButton();
        txtProvider = new javax.swing.JLabel();
        inputProvider = new javax.swing.JTextField();
        buttonLocaleProduct = new javax.swing.JButton();
        buttonNewProvider = new javax.swing.JButton();
        buttonLocaleProvider = new javax.swing.JButton();
        txtFormPayment = new javax.swing.JLabel();
        inputInCash = new javax.swing.JRadioButton();
        inputTerm = new javax.swing.JRadioButton();
        txtPaymentMethod = new javax.swing.JLabel();
        inputFormPayment = new javax.swing.JComboBox<>();
        txtDateOfSale = new javax.swing.JLabel();
        txtDeliveryForecast = new javax.swing.JLabel();
        txtSubTotal = new javax.swing.JLabel();
        outputSubTotal = new javax.swing.JLabel();
        txtTotal = new javax.swing.JLabel();
        outputTotal = new javax.swing.JLabel();
        txtNewOrder = new javax.swing.JLabel();
        buttonRemove = new javax.swing.JButton();
        txtStatus = new javax.swing.JLabel();
        inputFinishSale = new javax.swing.JRadioButton();
        inputPendingSale = new javax.swing.JRadioButton();
        txtItems = new javax.swing.JLabel();
        observationNewOrder = new javax.swing.JScrollPane();
        inputObservation = new javax.swing.JTextArea();
        buttonSave = new javax.swing.JButton();
        buttonAdd = new javax.swing.JButton();
        inputDateOfSale = new javax.swing.JTextField();
        inputDeliveryForecast = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Novo Pedido");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(null);

        txtObservation.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtObservation.setText("Observações");
        getContentPane().add(txtObservation);
        txtObservation.setBounds(20, 260, 110, 20);

        inputProductCode.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(inputProductCode);
        inputProductCode.setBounds(20, 100, 160, 30);

        buttonNewProduct.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        buttonNewProduct.setText("NOVO");
        buttonNewProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNewProductActionPerformed(evt);
            }
        });
        getContentPane().add(buttonNewProduct);
        buttonNewProduct.setBounds(300, 100, 70, 30);

        txtQuantity.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtQuantity.setText("Quantidade");
        getContentPane().add(txtQuantity);
        txtQuantity.setBounds(390, 70, 90, 20);

        inputQuantity.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(inputQuantity);
        inputQuantity.setBounds(390, 100, 70, 30);

        tableItems.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cód Produto", "Descrição", "Quantidade", "Valor", "Cliente", "Fornecedor"
            }
        ));
        tableNewOrder.setViewportView(tableItems);

        getContentPane().add(tableNewOrder);
        tableNewOrder.setBounds(310, 290, 540, 240);

        txtProductCode.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtProductCode.setText("Código do Produto");
        getContentPane().add(txtProductCode);
        txtProductCode.setBounds(20, 70, 140, 20);

        txtClient.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtClient.setText("Cliente");
        getContentPane().add(txtClient);
        txtClient.setBounds(500, 70, 60, 20);

        inputClient.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(inputClient);
        inputClient.setBounds(500, 100, 160, 30);

        buttonLocaleClient.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        buttonLocaleClient.setText("LOCALIZAR");
        buttonLocaleClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLocaleClientActionPerformed(evt);
            }
        });
        getContentPane().add(buttonLocaleClient);
        buttonLocaleClient.setBounds(670, 100, 100, 30);

        buttonNewClient.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        buttonNewClient.setText("NOVO");
        getContentPane().add(buttonNewClient);
        buttonNewClient.setBounds(780, 100, 70, 30);

        txtProvider.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtProvider.setText("Fornecedor");
        getContentPane().add(txtProvider);
        txtProvider.setBounds(20, 140, 110, 20);

        inputProvider.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(inputProvider);
        inputProvider.setBounds(20, 170, 160, 30);

        buttonLocaleProduct.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        buttonLocaleProduct.setText("LOCALIZAR");
        buttonLocaleProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLocaleProductActionPerformed(evt);
            }
        });
        getContentPane().add(buttonLocaleProduct);
        buttonLocaleProduct.setBounds(190, 100, 100, 30);

        buttonNewProvider.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        buttonNewProvider.setText("NOVO");
        getContentPane().add(buttonNewProvider);
        buttonNewProvider.setBounds(300, 170, 70, 30);

        buttonLocaleProvider.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        buttonLocaleProvider.setText("LOCALIZAR");
        buttonLocaleProvider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLocaleProviderActionPerformed(evt);
            }
        });
        getContentPane().add(buttonLocaleProvider);
        buttonLocaleProvider.setBounds(190, 170, 100, 30);

        txtFormPayment.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtFormPayment.setText("Forma de Pagamento");
        getContentPane().add(txtFormPayment);
        txtFormPayment.setBounds(430, 140, 149, 27);

        groupFormPayment.add(inputInCash);
        inputInCash.setText("A Vista");
        getContentPane().add(inputInCash);
        inputInCash.setBounds(430, 170, 61, 23);

        groupFormPayment.add(inputTerm);
        inputTerm.setText("A Prazo");
        getContentPane().add(inputTerm);
        inputTerm.setBounds(520, 170, 64, 23);

        txtPaymentMethod.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtPaymentMethod.setText("Meio de Pagamento");
        getContentPane().add(txtPaymentMethod);
        txtPaymentMethod.setBounds(640, 140, 140, 27);

        inputFormPayment.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecionar", "Dinheiro", "Boleto", "Carnê", "Cartão", "Cheque" }));
        inputFormPayment.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                inputFormPaymentItemStateChanged(evt);
            }
        });
        inputFormPayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputFormPaymentActionPerformed(evt);
            }
        });
        getContentPane().add(inputFormPayment);
        inputFormPayment.setBounds(640, 170, 102, 30);

        txtDateOfSale.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtDateOfSale.setText("Data do Pedido");
        getContentPane().add(txtDateOfSale);
        txtDateOfSale.setBounds(20, 220, 108, 27);

        txtDeliveryForecast.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtDeliveryForecast.setText("Previsão de Entrega");
        getContentPane().add(txtDeliveryForecast);
        txtDeliveryForecast.setBounds(270, 220, 145, 27);

        txtSubTotal.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtSubTotal.setText("SubTotal");
        getContentPane().add(txtSubTotal);
        txtSubTotal.setBounds(500, 540, 64, 20);

        outputSubTotal.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        outputSubTotal.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        outputSubTotal.setText("0,00");
        getContentPane().add(outputSubTotal);
        outputSubTotal.setBounds(580, 540, 100, 24);

        txtTotal.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtTotal.setText("Total");
        getContentPane().add(txtTotal);
        txtTotal.setBounds(700, 540, 36, 20);

        outputTotal.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        outputTotal.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        outputTotal.setText("0,00");
        getContentPane().add(outputTotal);
        outputTotal.setBounds(740, 540, 110, 24);

        txtNewOrder.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        txtNewOrder.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtNewOrder.setText("NOVO PEDIDO");
        getContentPane().add(txtNewOrder);
        txtNewOrder.setBounds(310, 20, 260, 32);

        buttonRemove.setText("REMOVER");
        buttonRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRemoveActionPerformed(evt);
            }
        });
        getContentPane().add(buttonRemove);
        buttonRemove.setBounds(140, 540, 100, 25);

        txtStatus.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtStatus.setText("Status:");
        getContentPane().add(txtStatus);
        txtStatus.setBounds(570, 220, 57, 27);

        groupStatus.add(inputFinishSale);
        inputFinishSale.setText("Finalizada");
        getContentPane().add(inputFinishSale);
        inputFinishSale.setBounds(630, 220, 73, 30);

        groupStatus.add(inputPendingSale);
        inputPendingSale.setText("Pendente");
        getContentPane().add(inputPendingSale);
        inputPendingSale.setBounds(720, 220, 90, 30);

        txtItems.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtItems.setText("Itens");
        getContentPane().add(txtItems);
        txtItems.setBounds(310, 260, 40, 20);

        inputObservation.setColumns(20);
        inputObservation.setRows(5);
        observationNewOrder.setViewportView(inputObservation);

        getContentPane().add(observationNewOrder);
        observationNewOrder.setBounds(20, 290, 280, 240);

        buttonSave.setText("SALVAR");
        buttonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSaveActionPerformed(evt);
            }
        });
        getContentPane().add(buttonSave);
        buttonSave.setBounds(260, 540, 78, 25);

        buttonAdd.setText("ADICIONAR");
        buttonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddActionPerformed(evt);
            }
        });
        getContentPane().add(buttonAdd);
        buttonAdd.setBounds(20, 540, 100, 25);

        inputDateOfSale.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(inputDateOfSale);
        inputDateOfSale.setBounds(140, 220, 90, 30);

        inputDeliveryForecast.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(inputDeliveryForecast);
        inputDeliveryForecast.setBounds(430, 220, 90, 30);

        setSize(new java.awt.Dimension(874, 609));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonLocaleClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLocaleClientActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonLocaleClientActionPerformed

    private void buttonLocaleProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLocaleProductActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonLocaleProductActionPerformed

    private void buttonLocaleProviderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLocaleProviderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonLocaleProviderActionPerformed

    private void inputFormPaymentItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_inputFormPaymentItemStateChanged

    }//GEN-LAST:event_inputFormPaymentItemStateChanged

    private void inputFormPaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputFormPaymentActionPerformed
        if(inputTerm.isSelected()){
            FormPayment formPayment = new FormPayment();
            formPayment.setVisible(true);
        }
    }//GEN-LAST:event_inputFormPaymentActionPerformed

    private void buttonNewProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNewProductActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonNewProductActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        if(x==0){
            x++;
            GetDate getDate = new GetDate();
            inputDateOfSale.setText(getDate.dateOfSystem());
        }
    }//GEN-LAST:event_formWindowActivated

    private void buttonRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRemoveActionPerformed
        
    }//GEN-LAST:event_buttonRemoveActionPerformed

    private void buttonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSaveActionPerformed
        JOptionPane.showMessageDialog(null, "PEDIDO CADASTRADO COM SUCESSO");
        OrderScreen orderScreen = new OrderScreen();
        this.dispose();
        orderScreen.setVisible(true);
    }//GEN-LAST:event_buttonSaveActionPerformed

    private void buttonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonAddActionPerformed

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
            java.util.logging.Logger.getLogger(NewOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewOrder().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAdd;
    private javax.swing.JButton buttonLocaleClient;
    private javax.swing.JButton buttonLocaleProduct;
    private javax.swing.JButton buttonLocaleProvider;
    private javax.swing.JButton buttonNewClient;
    private javax.swing.JButton buttonNewProduct;
    private javax.swing.JButton buttonNewProvider;
    private javax.swing.JButton buttonRemove;
    private javax.swing.JButton buttonSave;
    private javax.swing.ButtonGroup groupFormPayment;
    private javax.swing.ButtonGroup groupStatus;
    private javax.swing.JTextField inputClient;
    private javax.swing.JTextField inputDateOfSale;
    private javax.swing.JTextField inputDeliveryForecast;
    private javax.swing.JRadioButton inputFinishSale;
    private javax.swing.JComboBox<String> inputFormPayment;
    private javax.swing.JRadioButton inputInCash;
    private javax.swing.JTextArea inputObservation;
    private javax.swing.JRadioButton inputPendingSale;
    private javax.swing.JTextField inputProductCode;
    private javax.swing.JTextField inputProvider;
    private javax.swing.JTextField inputQuantity;
    private javax.swing.JRadioButton inputTerm;
    private javax.swing.JScrollPane observationNewOrder;
    private javax.swing.JLabel outputSubTotal;
    private javax.swing.JLabel outputTotal;
    private javax.swing.JTable tableItems;
    private javax.swing.JScrollPane tableNewOrder;
    private javax.swing.JLabel txtClient;
    private javax.swing.JLabel txtDateOfSale;
    private javax.swing.JLabel txtDeliveryForecast;
    private javax.swing.JLabel txtFormPayment;
    private javax.swing.JLabel txtItems;
    public static javax.swing.JLabel txtNewOrder;
    private javax.swing.JLabel txtObservation;
    private javax.swing.JLabel txtPaymentMethod;
    private javax.swing.JLabel txtProductCode;
    private javax.swing.JLabel txtProvider;
    private javax.swing.JLabel txtQuantity;
    private javax.swing.JLabel txtStatus;
    private javax.swing.JLabel txtSubTotal;
    private javax.swing.JLabel txtTotal;
    // End of variables declaration//GEN-END:variables
}
