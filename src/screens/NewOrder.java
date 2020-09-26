/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screens;

import functioncontroller.GetDate;

/**
 *
 * @author Alunos
 */
public class NewOrder extends javax.swing.JFrame {
    int x = 0;
    /**
     * Creates new form NewOrder
     */
    public NewOrder() {
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

        groupFormPayment = new javax.swing.ButtonGroup();
        groupStatus = new javax.swing.ButtonGroup();
        txtItems = new javax.swing.JLabel();
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
        inputDateOfSale = new javax.swing.JFormattedTextField();
        txtDeliveryForecast = new javax.swing.JLabel();
        inputDeliveryForecast = new javax.swing.JFormattedTextField();
        txtSubTotal = new javax.swing.JLabel();
        outputSubTotal = new javax.swing.JLabel();
        txtTotal = new javax.swing.JLabel();
        outputTotal = new javax.swing.JLabel();
        txtNewOrder = new javax.swing.JLabel();
        buttonSave = new javax.swing.JButton();
        txtStatus = new javax.swing.JLabel();
        inputFinishSale = new javax.swing.JRadioButton();
        inputPendingSale = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Novo Pedido");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(null);

        txtItems.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtItems.setText("Itens");
        getContentPane().add(txtItems);
        txtItems.setBounds(20, 280, 40, 20);

        inputProductCode.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(inputProductCode);
        inputProductCode.setBounds(20, 120, 160, 25);

        buttonNewProduct.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        buttonNewProduct.setText("NOVO");
        buttonNewProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNewProductActionPerformed(evt);
            }
        });
        getContentPane().add(buttonNewProduct);
        buttonNewProduct.setBounds(280, 120, 60, 20);

        txtQuantity.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtQuantity.setText("Quantidade");
        getContentPane().add(txtQuantity);
        txtQuantity.setBounds(370, 90, 90, 20);

        inputQuantity.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(inputQuantity);
        inputQuantity.setBounds(370, 120, 70, 25);

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
                "Código Produto", "Descrição", "Quantidade", "Valor", "Cliente", "Fornecedor"
            }
        ));
        tableNewOrder.setViewportView(tableItems);

        getContentPane().add(tableNewOrder);
        tableNewOrder.setBounds(20, 310, 780, 240);

        txtProductCode.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtProductCode.setText("Código do Produto");
        getContentPane().add(txtProductCode);
        txtProductCode.setBounds(20, 90, 140, 20);

        txtClient.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtClient.setText("Cliente");
        getContentPane().add(txtClient);
        txtClient.setBounds(480, 90, 60, 20);

        inputClient.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(inputClient);
        inputClient.setBounds(480, 120, 160, 25);

        buttonLocaleClient.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        buttonLocaleClient.setText("LOCALIZAR");
        buttonLocaleClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLocaleClientActionPerformed(evt);
            }
        });
        getContentPane().add(buttonLocaleClient);
        buttonLocaleClient.setBounds(650, 120, 90, 20);

        buttonNewClient.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        buttonNewClient.setText("NOVO");
        getContentPane().add(buttonNewClient);
        buttonNewClient.setBounds(740, 120, 60, 20);

        txtProvider.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtProvider.setText("Fornecedor");
        getContentPane().add(txtProvider);
        txtProvider.setBounds(20, 160, 110, 20);

        inputProvider.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(inputProvider);
        inputProvider.setBounds(20, 190, 160, 25);

        buttonLocaleProduct.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        buttonLocaleProduct.setText("LOCALIZAR");
        buttonLocaleProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLocaleProductActionPerformed(evt);
            }
        });
        getContentPane().add(buttonLocaleProduct);
        buttonLocaleProduct.setBounds(190, 120, 90, 20);

        buttonNewProvider.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        buttonNewProvider.setText("NOVO");
        getContentPane().add(buttonNewProvider);
        buttonNewProvider.setBounds(280, 190, 60, 20);

        buttonLocaleProvider.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        buttonLocaleProvider.setText("LOCALIZAR");
        buttonLocaleProvider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLocaleProviderActionPerformed(evt);
            }
        });
        getContentPane().add(buttonLocaleProvider);
        buttonLocaleProvider.setBounds(190, 190, 90, 20);

        txtFormPayment.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtFormPayment.setText("Forma de Pagamento");
        getContentPane().add(txtFormPayment);
        txtFormPayment.setBounds(370, 160, 149, 27);

        groupFormPayment.add(inputInCash);
        inputInCash.setText("A Vista");
        getContentPane().add(inputInCash);
        inputInCash.setBounds(370, 190, 72, 28);

        groupFormPayment.add(inputTerm);
        inputTerm.setText("A Prazo");
        getContentPane().add(inputTerm);
        inputTerm.setBounds(460, 190, 76, 28);

        txtPaymentMethod.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtPaymentMethod.setText("Meio de Pagamento");
        getContentPane().add(txtPaymentMethod);
        txtPaymentMethod.setBounds(600, 160, 140, 27);

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
        inputFormPayment.setBounds(600, 190, 102, 25);

        txtDateOfSale.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtDateOfSale.setText("Data do Pedido");
        getContentPane().add(txtDateOfSale);
        txtDateOfSale.setBounds(20, 240, 108, 27);

        try {
            inputDateOfSale.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        inputDateOfSale.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        getContentPane().add(inputDateOfSale);
        inputDateOfSale.setBounds(150, 240, 95, 25);

        txtDeliveryForecast.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtDeliveryForecast.setText("Previsão de Entrega");
        getContentPane().add(txtDeliveryForecast);
        txtDeliveryForecast.setBounds(260, 240, 145, 27);

        try {
            inputDeliveryForecast.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        inputDeliveryForecast.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        getContentPane().add(inputDeliveryForecast);
        inputDeliveryForecast.setBounds(430, 240, 95, 25);

        txtSubTotal.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtSubTotal.setText("SubTotal");
        getContentPane().add(txtSubTotal);
        txtSubTotal.setBounds(170, 565, 64, 20);

        outputSubTotal.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        outputSubTotal.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        outputSubTotal.setText("0,00");
        getContentPane().add(outputSubTotal);
        outputSubTotal.setBounds(250, 565, 100, 24);

        txtTotal.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtTotal.setText("Total");
        getContentPane().add(txtTotal);
        txtTotal.setBounds(370, 565, 36, 20);

        outputTotal.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        outputTotal.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        outputTotal.setText("0,00");
        getContentPane().add(outputTotal);
        outputTotal.setBounds(410, 565, 110, 24);

        txtNewOrder.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        txtNewOrder.setText("NOVO PEDIDO");
        getContentPane().add(txtNewOrder);
        txtNewOrder.setBounds(310, 30, 170, 32);

        buttonSave.setText("SALVAR");
        getContentPane().add(buttonSave);
        buttonSave.setBounds(20, 560, 77, 32);

        txtStatus.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtStatus.setText("Status:");
        getContentPane().add(txtStatus);
        txtStatus.setBounds(540, 240, 57, 27);

        groupStatus.add(inputFinishSale);
        inputFinishSale.setText("Finalizada");
        getContentPane().add(inputFinishSale);
        inputFinishSale.setBounds(600, 245, 88, 20);

        groupStatus.add(inputPendingSale);
        inputPendingSale.setText("Pendente");
        getContentPane().add(inputPendingSale);
        inputPendingSale.setBounds(690, 245, 90, 20);

        setSize(new java.awt.Dimension(820, 636));
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
    private javax.swing.JButton buttonLocaleClient;
    private javax.swing.JButton buttonLocaleProduct;
    private javax.swing.JButton buttonLocaleProvider;
    private javax.swing.JButton buttonNewClient;
    private javax.swing.JButton buttonNewProduct;
    private javax.swing.JButton buttonNewProvider;
    private javax.swing.JButton buttonSave;
    private javax.swing.ButtonGroup groupFormPayment;
    private javax.swing.ButtonGroup groupStatus;
    private javax.swing.JTextField inputClient;
    private javax.swing.JFormattedTextField inputDateOfSale;
    private javax.swing.JFormattedTextField inputDeliveryForecast;
    private javax.swing.JRadioButton inputFinishSale;
    private javax.swing.JComboBox<String> inputFormPayment;
    private javax.swing.JRadioButton inputInCash;
    private javax.swing.JRadioButton inputPendingSale;
    private javax.swing.JTextField inputProductCode;
    private javax.swing.JTextField inputProvider;
    private javax.swing.JTextField inputQuantity;
    private javax.swing.JRadioButton inputTerm;
    private javax.swing.JLabel outputSubTotal;
    private javax.swing.JLabel outputTotal;
    private javax.swing.JTable tableItems;
    private javax.swing.JScrollPane tableNewOrder;
    private javax.swing.JLabel txtClient;
    private javax.swing.JLabel txtDateOfSale;
    private javax.swing.JLabel txtDeliveryForecast;
    private javax.swing.JLabel txtFormPayment;
    private javax.swing.JLabel txtItems;
    private javax.swing.JLabel txtNewOrder;
    private javax.swing.JLabel txtPaymentMethod;
    private javax.swing.JLabel txtProductCode;
    private javax.swing.JLabel txtProvider;
    private javax.swing.JLabel txtQuantity;
    private javax.swing.JLabel txtStatus;
    private javax.swing.JLabel txtSubTotal;
    private javax.swing.JLabel txtTotal;
    // End of variables declaration//GEN-END:variables
}
