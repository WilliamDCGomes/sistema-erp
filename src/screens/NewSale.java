package screens;

import functioncontroller.GetDate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import connectionbd.ConnectionModule;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
public class NewSale extends javax.swing.JFrame {
    int x = 0;
    String product = "";
    double value = 0;
    double valueNow = 0;
    Connection connection = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    public NewSale() {
        initComponents();
        ConnectionModule connect = new ConnectionModule();
        connection = connect.getConnectionMySQL();
    }
    private void filter(){
        clearTable();
    }
    private void clearTable(){
        DefaultTableModel table = (DefaultTableModel) tableSoldItems.getModel();
        for(int i=table.getRowCount()-1; i >= 0; i--){
            table.removeRow(i);
        }
    }
    private void insertInTable(double price){
        DefaultTableModel table = (DefaultTableModel) tableSoldItems.getModel();
        double partialPrice = price * Integer.parseInt( inputAmount.getText() );
        value += partialPrice;
        valueNow += partialPrice;
        outputSubTotal.setText(Double.toString(valueNow).replace('.', ','));
        outputTotal.setText(Double.toString(value).replace('.', ','));
        product += ";" + Double.toString(partialPrice).replace('.', ',') + ";" + inputAmount.getText();
        String[] data = product.split(";");
        table.addRow(data);
        inputCod.setText("");
        inputNameProduct.setText("");
        inputAmount.setText("");
        inputCod.requestFocus();
    }
    private void getProduct(){
        String sql ="select barCode as 'Código do Produto', nameProduct as 'Nome do Produto', price as 'Preço de Venda' from product where barCode = ?";
        try {
            pst=connection.prepareStatement(sql);
            pst.setString(1, inputCod.getText());
            rs= pst.executeQuery();
            if(rs.next()) {
                product = rs.getString(1) + ";" + rs.getString(2) + ";" + rs.getString(3).replace('.', ',');
                insertInTable(Double.parseDouble(rs.getString(3)));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void getNameProduct(){
        String sql ="select nameProduct from product where barCode = ?";
        try {
            pst=connection.prepareStatement(sql);
            pst.setString(1, inputCod.getText());
            rs= pst.executeQuery();
            if(rs.next()) {
                inputNameProduct.setText(rs.getString(1));
                inputAmount.requestFocus();
            }
            else{
                JOptionPane.showMessageDialog(null, "PRODUTO NÃO ENCONTRADO NO BANCO DE DADOS");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void getNameEmployee(){
        String sql ="select nameEmployee from employee where id = ?";
        try {
            pst=connection.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt (inputCodOfEmployee.getText() ));
            rs= pst.executeQuery();
            if(rs.next()) {
                inputNameEmployee.setText(rs.getString(1));
                inputClient.requestFocus();
            }
            else{
                JOptionPane.showMessageDialog(null, "VENDEDOR NÃO ENCONTRADO NO BANCO DE DADOS");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void getClientName(){
        String sql ="select clientName from clients where cpf = ?";
        try {
            pst=connection.prepareStatement(sql);
            pst.setString(1, inputClient.getText());
            rs= pst.executeQuery();
            if(rs.next()) {
                inputNameClient.setText(rs.getString(1));
            }
            else{
                JOptionPane.showMessageDialog(null, "CLIENTE NÃO ENCONTRADO NO BANCO DE DADOS");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        groupFormPayment = new javax.swing.ButtonGroup();
        groupStatus = new javax.swing.ButtonGroup();
        txtNewSale = new javax.swing.JLabel();
        txtCod = new javax.swing.JLabel();
        inputCod = new javax.swing.JTextField();
        txtAmount = new javax.swing.JLabel();
        inputAmount = new javax.swing.JTextField();
        txtCodOfSaller = new javax.swing.JLabel();
        inputCodOfEmployee = new javax.swing.JTextField();
        txtPaymentMethod = new javax.swing.JLabel();
        inputFormPayment = new javax.swing.JComboBox<>();
        txtClient = new javax.swing.JLabel();
        inputClient = new javax.swing.JTextField();
        buttonLocaleClient = new javax.swing.JButton();
        buttonNewClient = new javax.swing.JButton();
        txtDiscount = new javax.swing.JLabel();
        inputDiscount = new javax.swing.JTextField();
        txtSubTotal = new javax.swing.JLabel();
        outputSubTotal = new javax.swing.JLabel();
        txtTotal = new javax.swing.JLabel();
        outputTotal = new javax.swing.JLabel();
        tableNewSale = new javax.swing.JScrollPane();
        tableSoldItems = new javax.swing.JTable();
        buttonSave = new javax.swing.JButton();
        buttomRemoveItems = new javax.swing.JButton();
        txtItems = new javax.swing.JLabel();
        txtDateOfSale = new javax.swing.JLabel();
        inputDateOfSale = new javax.swing.JFormattedTextField();
        txtFormPayment = new javax.swing.JLabel();
        inputInCash = new javax.swing.JRadioButton();
        inputTerm = new javax.swing.JRadioButton();
        txtDiscount1 = new javax.swing.JLabel();
        txtStatus = new javax.swing.JLabel();
        inputFinishSale = new javax.swing.JRadioButton();
        inputPendingSale = new javax.swing.JRadioButton();
        buttonCancele = new javax.swing.JButton();
        buttonLocaleProduct = new javax.swing.JButton();
        buttonNewProduct = new javax.swing.JButton();
        txtNameProduct = new javax.swing.JLabel();
        inputNameProduct = new javax.swing.JTextField();
        buttonLocaleEmployee = new javax.swing.JButton();
        txtNameEmployee = new javax.swing.JLabel();
        inputNameEmployee = new javax.swing.JTextField();
        txtNameClient = new javax.swing.JLabel();
        inputNameClient = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nova Venda");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(null);

        txtNewSale.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        txtNewSale.setText("Nova Venda");
        getContentPane().add(txtNewSale);
        txtNewSale.setBounds(341, 11, 137, 32);

        txtCod.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtCod.setText("Código");
        getContentPane().add(txtCod);
        txtCod.setBounds(20, 50, 63, 27);

        inputCod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputCodKeyPressed(evt);
            }
        });
        getContentPane().add(inputCod);
        inputCod.setBounds(20, 80, 104, 30);

        txtAmount.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtAmount.setText("Quantidade");
        getContentPane().add(txtAmount);
        txtAmount.setBounds(690, 50, 82, 27);

        inputAmount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputAmountKeyPressed(evt);
            }
        });
        getContentPane().add(inputAmount);
        inputAmount.setBounds(690, 80, 104, 30);

        txtCodOfSaller.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtCodOfSaller.setText("Cod Vendedor");
        getContentPane().add(txtCodOfSaller);
        txtCodOfSaller.setBounds(20, 120, 102, 27);

        inputCodOfEmployee.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputCodOfEmployeeKeyPressed(evt);
            }
        });
        getContentPane().add(inputCodOfEmployee);
        inputCodOfEmployee.setBounds(20, 150, 100, 30);

        txtPaymentMethod.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtPaymentMethod.setText("Meio de Pagamento");
        getContentPane().add(txtPaymentMethod);
        txtPaymentMethod.setBounds(540, 260, 140, 27);

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
        inputFormPayment.setBounds(700, 260, 102, 30);

        txtClient.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtClient.setText("CPF Cliente");
        getContentPane().add(txtClient);
        txtClient.setBounds(20, 190, 110, 27);

        inputClient.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputClientKeyPressed(evt);
            }
        });
        getContentPane().add(inputClient);
        inputClient.setBounds(20, 220, 143, 30);

        buttonLocaleClient.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        buttonLocaleClient.setText("Localizar");
        getContentPane().add(buttonLocaleClient);
        buttonLocaleClient.setBounds(170, 220, 77, 25);

        buttonNewClient.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        buttonNewClient.setText("Adicionar");
        getContentPane().add(buttonNewClient);
        buttonNewClient.setBounds(260, 220, 79, 25);

        txtDiscount.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtDiscount.setText("Desconto");
        getContentPane().add(txtDiscount);
        txtDiscount.setBounds(20, 300, 68, 27);
        getContentPane().add(inputDiscount);
        inputDiscount.setBounds(20, 330, 78, 30);

        txtSubTotal.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtSubTotal.setText("SubTotal");
        getContentPane().add(txtSubTotal);
        txtSubTotal.setBounds(510, 620, 64, 20);

        outputSubTotal.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        outputSubTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        outputSubTotal.setText("0,00");
        getContentPane().add(outputSubTotal);
        outputSubTotal.setBounds(570, 615, 100, 30);

        txtTotal.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtTotal.setText("Total");
        getContentPane().add(txtTotal);
        txtTotal.setBounds(680, 620, 36, 20);

        outputTotal.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        outputTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        outputTotal.setText("0,00");
        getContentPane().add(outputTotal);
        outputTotal.setBounds(720, 615, 90, 30);

        tableSoldItems.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código do Produto", "Nome do Produto", "Preço de Venda", "Valor Parcial", "Quantidade"
            }
        ));
        tableNewSale.setViewportView(tableSoldItems);

        getContentPane().add(tableNewSale);
        tableNewSale.setBounds(20, 400, 790, 200);

        buttonSave.setText("SALVAR");
        buttonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSaveActionPerformed(evt);
            }
        });
        getContentPane().add(buttonSave);
        buttonSave.setBounds(20, 620, 80, 23);

        buttomRemoveItems.setText("REMOVER");
        getContentPane().add(buttomRemoveItems);
        buttomRemoveItems.setBounds(130, 620, 90, 23);

        txtItems.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtItems.setText("Itens");
        getContentPane().add(txtItems);
        txtItems.setBounds(20, 370, 35, 27);

        txtDateOfSale.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtDateOfSale.setText("Data da Venda");
        getContentPane().add(txtDateOfSale);
        txtDateOfSale.setBounds(20, 260, 101, 27);

        try {
            inputDateOfSale.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        inputDateOfSale.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        getContentPane().add(inputDateOfSale);
        inputDateOfSale.setBounds(130, 260, 101, 30);

        txtFormPayment.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtFormPayment.setText("Forma de Pagamento");
        getContentPane().add(txtFormPayment);
        txtFormPayment.setBounds(640, 120, 149, 27);

        groupFormPayment.add(inputInCash);
        inputInCash.setText("A Vista");
        getContentPane().add(inputInCash);
        inputInCash.setBounds(640, 150, 59, 30);

        groupFormPayment.add(inputTerm);
        inputTerm.setText("A Prazo");
        getContentPane().add(inputTerm);
        inputTerm.setBounds(730, 150, 63, 30);

        txtDiscount1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtDiscount1.setText("%");
        getContentPane().add(txtDiscount1);
        txtDiscount1.setBounds(110, 330, 10, 27);

        txtStatus.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtStatus.setText("Status:");
        getContentPane().add(txtStatus);
        txtStatus.setBounds(280, 260, 57, 30);

        groupStatus.add(inputFinishSale);
        inputFinishSale.setText("Finalizada");
        getContentPane().add(inputFinishSale);
        inputFinishSale.setBounds(343, 260, 90, 30);

        groupStatus.add(inputPendingSale);
        inputPendingSale.setText("Pendente");
        getContentPane().add(inputPendingSale);
        inputPendingSale.setBounds(430, 260, 90, 30);

        buttonCancele.setText("CANCELAR");
        buttonCancele.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCanceleActionPerformed(evt);
            }
        });
        getContentPane().add(buttonCancele);
        buttonCancele.setBounds(250, 620, 100, 23);

        buttonLocaleProduct.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        buttonLocaleProduct.setText("Localizar");
        getContentPane().add(buttonLocaleProduct);
        buttonLocaleProduct.setBounds(130, 80, 77, 30);

        buttonNewProduct.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        buttonNewProduct.setText("Adicionar");
        getContentPane().add(buttonNewProduct);
        buttonNewProduct.setBounds(210, 80, 79, 30);

        txtNameProduct.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        txtNameProduct.setText("Nome do Produto");
        getContentPane().add(txtNameProduct);
        txtNameProduct.setBounds(300, 50, 140, 30);
        getContentPane().add(inputNameProduct);
        inputNameProduct.setBounds(300, 80, 380, 30);

        buttonLocaleEmployee.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        buttonLocaleEmployee.setText("Localizar");
        getContentPane().add(buttonLocaleEmployee);
        buttonLocaleEmployee.setBounds(130, 150, 77, 30);

        txtNameEmployee.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        txtNameEmployee.setText("Nome do Funcionário");
        getContentPane().add(txtNameEmployee);
        txtNameEmployee.setBounds(230, 120, 160, 30);
        getContentPane().add(inputNameEmployee);
        inputNameEmployee.setBounds(230, 150, 380, 30);

        txtNameClient.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        txtNameClient.setText("Nome do Cliente");
        getContentPane().add(txtNameClient);
        txtNameClient.setBounds(380, 190, 160, 30);
        getContentPane().add(inputNameClient);
        inputNameClient.setBounds(380, 220, 380, 30);

        setSize(new java.awt.Dimension(830, 688));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void inputFormPaymentItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_inputFormPaymentItemStateChanged
        
    }//GEN-LAST:event_inputFormPaymentItemStateChanged

    private void inputFormPaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputFormPaymentActionPerformed
        if(inputTerm.isSelected()){
            FormPayment formPayment = new FormPayment();
            formPayment.setVisible(true);
        }
    }//GEN-LAST:event_inputFormPaymentActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        if (x==0){
            x++;
            GetDate getDateSystem = new GetDate();
            inputDateOfSale.setText(getDateSystem.dateOfSystem());
        }
    }//GEN-LAST:event_formWindowActivated

    private void buttonCanceleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCanceleActionPerformed
        this.dispose();
    }//GEN-LAST:event_buttonCanceleActionPerformed

    private void buttonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSaveActionPerformed
        JOptionPane.showMessageDialog(null, "VENDA FINALIZADA COM SUCESSO");
        SaleScreen saleScreen = new SaleScreen();
        this.dispose();
        saleScreen.setVisible(true);
    }//GEN-LAST:event_buttonSaveActionPerformed

    private void inputAmountKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputAmountKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER && !inputCod.getText().equals("") && !inputNameProduct.getText().equals("") && !inputAmount.getText().equals("")){
            getProduct();
        }
        else if(evt.getKeyCode() == evt.VK_ENTER){
            JOptionPane.showMessageDialog(null, "POR FAVOR PREENCHA OS CAMPOS DO PRODUTO PARA INSERIR NA TABELA DE COMPRAS");
        }
    }//GEN-LAST:event_inputAmountKeyPressed

    private void inputCodKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputCodKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER && !inputCod.getText().equals("")){
            getNameProduct();
        }
        else if(evt.getKeyCode() == evt.VK_ENTER){
            JOptionPane.showMessageDialog(null, "POR FAVOR INSIRA O CÓDIGO DO PRODUTO");
        }
    }//GEN-LAST:event_inputCodKeyPressed

    private void inputCodOfEmployeeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputCodOfEmployeeKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER && !inputCodOfEmployee.getText().equals("")){
            getNameEmployee();
        }
        else if(evt.getKeyCode() == evt.VK_ENTER){
            JOptionPane.showMessageDialog(null, "POR FAVOR INSIRA O CÓDIGO DO VENDEDOR");
        }
    }//GEN-LAST:event_inputCodOfEmployeeKeyPressed

    private void inputClientKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputClientKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER && !inputClient.getText().equals("")){
            getClientName();
        }
        else if(evt.getKeyCode() == evt.VK_ENTER){
            JOptionPane.showMessageDialog(null, "POR FAVOR INSIRA O CPF DO CLIENTE");
        }
    }//GEN-LAST:event_inputClientKeyPressed

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
            java.util.logging.Logger.getLogger(NewSale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewSale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewSale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewSale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewSale().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttomRemoveItems;
    public static javax.swing.JButton buttonCancele;
    private javax.swing.JButton buttonLocaleClient;
    private javax.swing.JButton buttonLocaleEmployee;
    private javax.swing.JButton buttonLocaleProduct;
    private javax.swing.JButton buttonNewClient;
    private javax.swing.JButton buttonNewProduct;
    private javax.swing.JButton buttonSave;
    private javax.swing.ButtonGroup groupFormPayment;
    private javax.swing.ButtonGroup groupStatus;
    private javax.swing.JTextField inputAmount;
    private javax.swing.JTextField inputClient;
    private javax.swing.JTextField inputCod;
    private javax.swing.JTextField inputCodOfEmployee;
    private javax.swing.JFormattedTextField inputDateOfSale;
    private javax.swing.JTextField inputDiscount;
    private javax.swing.JRadioButton inputFinishSale;
    private javax.swing.JComboBox<String> inputFormPayment;
    private javax.swing.JRadioButton inputInCash;
    private javax.swing.JTextField inputNameClient;
    private javax.swing.JTextField inputNameEmployee;
    private javax.swing.JTextField inputNameProduct;
    private javax.swing.JRadioButton inputPendingSale;
    private javax.swing.JRadioButton inputTerm;
    private javax.swing.JLabel outputSubTotal;
    private javax.swing.JLabel outputTotal;
    private javax.swing.JScrollPane tableNewSale;
    private javax.swing.JTable tableSoldItems;
    private javax.swing.JLabel txtAmount;
    private javax.swing.JLabel txtClient;
    private javax.swing.JLabel txtCod;
    private javax.swing.JLabel txtCodOfSaller;
    private javax.swing.JLabel txtDateOfSale;
    private javax.swing.JLabel txtDiscount;
    private javax.swing.JLabel txtDiscount1;
    private javax.swing.JLabel txtFormPayment;
    private javax.swing.JLabel txtItems;
    private javax.swing.JLabel txtNameClient;
    private javax.swing.JLabel txtNameEmployee;
    private javax.swing.JLabel txtNameProduct;
    public static javax.swing.JLabel txtNewSale;
    private javax.swing.JLabel txtPaymentMethod;
    private javax.swing.JLabel txtStatus;
    private javax.swing.JLabel txtSubTotal;
    private javax.swing.JLabel txtTotal;
    // End of variables declaration//GEN-END:variables
}
