package screens;
import functioncontroller.GetDate;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import connectionbd.ConnectionModule;
import functioncontroller.RoundNumber;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import static screens.NewSale.inputClient;
import static screens.NewSale.inputNameClient;
public class NewOrder extends javax.swing.JFrame {
    int x = 0;
    Connection connection = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    PreparedStatement pst2 = null;
    ResultSet rs2 = null;
    PreparedStatement pst3 = null;
    ResultSet rs3 = null;
    String product = "";
    double value = 0;
    double valueNow = 0;
    RoundNumber roundNumber = new RoundNumber();
    ArrayList<String> products = new ArrayList<>();
    ArrayList<String> listProducts = new ArrayList<>();
    public NewOrder() {
        initComponents();
        ConnectionModule connect = new ConnectionModule();
        connection = connect.getConnectionMySQL();
    }
    private void add(){
        String[] aux = this.getTitle().split(" ");
        int id = Integer.parseInt( aux[aux.length - 1] );
        String sql = "insert into orderProduct(codOrder, codClient, dateOrder, deliveryForecast, statusOrder, totalValue, subTotalValue, obs, provider)values(?,?,?,?,?,?,?,?,?)";
        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, id);
            pst.setString(2, inputClient.getText());
            pst.setString(3, inputDateOfSale.getText());
            pst.setString(4, inputDeliveryForecast.getText());
            if(inputFinishSale.isSelected()){
                pst.setString(5, "Finalizada");
            }
            else if(inputPendingSale.isSelected()){
                pst.setString(5, "Pendente");
            }
            pst.setString(6, outputTotal.getText().replace(",", "."));
            pst.setString(7, outputDescont.getText().replace(",", "."));
            pst.setString(8, inputObservation.getText());
            pst.setString(9, inputProvider.getText());
            pst.executeUpdate();
            addProducts(id);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void addProducts(int id){
        DefaultTableModel table = (DefaultTableModel) tableItems.getModel();
        String sql = "insert into addProducts(codOrder, barCode, quantity, price)values(?,?,?,?)";
        try {
            for(int i=table.getRowCount()-1; i >= 0; i--){
                pst = connection.prepareStatement(sql);
                pst.setInt(1, id);
                pst.setString(2, tableItems.getModel().getValueAt(i,0).toString());
                pst.setInt(3, Integer.parseInt(tableItems.getModel().getValueAt(i,2).toString()));
                pst.setString(4, tableItems.getModel().getValueAt(i,3).toString().replace(",", "."));
                pst.executeUpdate();
            }
            JOptionPane.showMessageDialog(null,"PEDIDO CADASTRADO COM SUCESSO");
            OrderScreen orderScreen = new OrderScreen();
            this.dispose();
            orderScreen.setTitle("Pedido: " + Integer.toString(id));
            orderScreen.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private int newOrderId(){
        String sql ="select max(id) from orderProduct";
        try {
            pst=connection.prepareStatement(sql);
            rs= pst.executeQuery();
            if(rs.next()){
                return rs.getInt(1) + 1;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return 1;
    }
    private void getProduct(){
        String sql ="select barCode as 'Código do Produto', nameProduct as 'Nome do Produto', price as 'Preço de Venda' from product where barCode = ?";
        try {
            pst=connection.prepareStatement(sql);
            pst.setString(1, inputProductCode.getText());
            rs= pst.executeQuery();
            if(rs.next()) {
                product = rs.getString(1) + ";" + rs.getString(2) + ";" + rs.getString(3).replace('.', ',');
                insertInTable(Double.parseDouble(rs.getString(3)));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void insertInTable(double price){
        DefaultTableModel table = (DefaultTableModel) tableItems.getModel();
        double partialPrice = price * Integer.parseInt( inputQuantity.getText() );
        value += partialPrice;
        valueNow += partialPrice;
        outputSubTotal.setText(roundNumber.doRound(valueNow).replace(",", "."));
        if(!inputDiscount.getText().equals("")){
            double valueDescont = (Double.parseDouble(outputSubTotal.getText().replace(",", ".")) * Double.parseDouble(inputDiscount.getText().replace(",", ".")) ) / 100;
            outputDescont.setText(roundNumber.doRound(valueDescont).replace(".", ","));
            value -= valueDescont;
            outputTotal.setText(roundNumber.doRound(value).replace(".", ","));
        }
        else{
            outputDescont.setText("0,00");
            value = valueNow;
            outputTotal.setText(roundNumber.doRound(value).replace(".", ","));
        }
        product += ";" + roundNumber.doRound(partialPrice).replace('.', ',') + ";" + inputQuantity.getText() + ";" + getProvider();
        String[] data = product.split(";");
        table.addRow(data);
        inputProductCode.setText("");
        inputNameProduct.setText("");
        inputQuantity.setText("");
        inputProductCode.requestFocus();
    }
    private String getProvider(){
        String sql ="select provider from product where barCode = ?";
        try {
            pst2=connection.prepareStatement(sql);
            pst2.setString(1, inputProductCode.getText());
            rs2= pst2.executeQuery();
            if(rs2.next()) {
                return rs2.getString(1);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }
    private void getNameProduct(){
        String sql ="select nameProduct from product where barCode = ?";
        try {
            pst=connection.prepareStatement(sql);
            pst.setString(1, inputProductCode.getText());
            rs= pst.executeQuery();
            if(rs.next()) {
                inputNameProduct.setText(rs.getString(1));
                inputQuantity.requestFocus();
            }
            else{
                JOptionPane.showMessageDialog(null, "PRODUTO NÃO ENCONTRADO NO BANCO DE DADOS");
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
        txtObservation = new javax.swing.JLabel();
        inputProductCode = new javax.swing.JTextField();
        buttonAllProducts = new javax.swing.JButton();
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
        txtDateOfSale = new javax.swing.JLabel();
        txtDeliveryForecast = new javax.swing.JLabel();
        txtDescont = new javax.swing.JLabel();
        outputDescont = new javax.swing.JLabel();
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
        inputDateOfSale = new javax.swing.JTextField();
        inputDeliveryForecast = new javax.swing.JTextField();
        txtNameProduct = new javax.swing.JLabel();
        inputNameProduct = new javax.swing.JTextField();
        txtNameClient = new javax.swing.JLabel();
        inputNameClient = new javax.swing.JTextField();
        txtNameProvider = new javax.swing.JLabel();
        inputNameProvider = new javax.swing.JTextField();
        txtRequiredField3 = new javax.swing.JLabel();
        txtRequiredField4 = new javax.swing.JLabel();
        txtRequiredField5 = new javax.swing.JLabel();
        txtRequiredField6 = new javax.swing.JLabel();
        txtRequiredField8 = new javax.swing.JLabel();
        txtRequiredField10 = new javax.swing.JLabel();
        txtRequiredField11 = new javax.swing.JLabel();
        txtDiscount = new javax.swing.JLabel();
        inputDiscount = new javax.swing.JTextField();
        txtDiscount1 = new javax.swing.JLabel();
        outputSubTotal = new javax.swing.JLabel();
        txtSubTotal2 = new javax.swing.JLabel();

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
        txtObservation.setBounds(20, 350, 110, 30);

        inputProductCode.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(inputProductCode);
        inputProductCode.setBounds(20, 100, 90, 30);

        buttonAllProducts.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        buttonAllProducts.setText("TODOS");
        buttonAllProducts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAllProductsActionPerformed(evt);
            }
        });
        getContentPane().add(buttonAllProducts);
        buttonAllProducts.setBounds(230, 100, 80, 30);

        txtQuantity.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtQuantity.setText("Quantidade");
        getContentPane().add(txtQuantity);
        txtQuantity.setBounds(790, 70, 90, 30);

        inputQuantity.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        inputQuantity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputQuantityKeyPressed(evt);
            }
        });
        getContentPane().add(inputQuantity);
        inputQuantity.setBounds(790, 100, 70, 30);

        tableItems.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cód Produto", "Descrição", "Preço", "Valor Parcial", "Quantidade", "Fornecedor"
            }
        ));
        tableNewOrder.setViewportView(tableItems);

        getContentPane().add(tableNewOrder);
        tableNewOrder.setBounds(310, 380, 560, 190);

        txtProductCode.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtProductCode.setText("Código do Produto");
        getContentPane().add(txtProductCode);
        txtProductCode.setBounds(20, 70, 140, 30);

        txtClient.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtClient.setText("CPF / CNPJ Cliente");
        getContentPane().add(txtClient);
        txtClient.setBounds(20, 140, 150, 30);

        inputClient.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(inputClient);
        inputClient.setBounds(20, 170, 160, 30);

        buttonLocaleClient.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        buttonLocaleClient.setText("LOCALIZAR");
        buttonLocaleClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLocaleClientActionPerformed(evt);
            }
        });
        getContentPane().add(buttonLocaleClient);
        buttonLocaleClient.setBounds(190, 170, 100, 30);

        buttonNewClient.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        buttonNewClient.setText("NOVO");
        getContentPane().add(buttonNewClient);
        buttonNewClient.setBounds(300, 170, 70, 30);

        txtProvider.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtProvider.setText("Fornecedor");
        getContentPane().add(txtProvider);
        txtProvider.setBounds(20, 210, 100, 30);

        inputProvider.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(inputProvider);
        inputProvider.setBounds(20, 240, 90, 30);

        buttonLocaleProduct.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        buttonLocaleProduct.setText("LOCALIZAR");
        buttonLocaleProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLocaleProductActionPerformed(evt);
            }
        });
        getContentPane().add(buttonLocaleProduct);
        buttonLocaleProduct.setBounds(120, 100, 100, 30);

        buttonNewProvider.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        buttonNewProvider.setText("TODOS");
        getContentPane().add(buttonNewProvider);
        buttonNewProvider.setBounds(230, 240, 90, 30);

        buttonLocaleProvider.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        buttonLocaleProvider.setText("LOCALIZAR");
        buttonLocaleProvider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLocaleProviderActionPerformed(evt);
            }
        });
        getContentPane().add(buttonLocaleProvider);
        buttonLocaleProvider.setBounds(120, 240, 100, 30);

        txtDateOfSale.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtDateOfSale.setText("Data do Pedido");
        getContentPane().add(txtDateOfSale);
        txtDateOfSale.setBounds(20, 280, 108, 30);

        txtDeliveryForecast.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtDeliveryForecast.setText("Previsão de Entrega");
        getContentPane().add(txtDeliveryForecast);
        txtDeliveryForecast.setBounds(220, 280, 145, 27);

        txtDescont.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtDescont.setText("Desconto");
        getContentPane().add(txtDescont);
        txtDescont.setBounds(20, 570, 90, 40);

        outputDescont.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        outputDescont.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        outputDescont.setText("0,00");
        getContentPane().add(outputDescont);
        outputDescont.setBounds(120, 578, 120, 24);

        txtTotal.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtTotal.setText("Total");
        getContentPane().add(txtTotal);
        txtTotal.setBounds(490, 570, 80, 40);

        outputTotal.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        outputTotal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        outputTotal.setText("0,00");
        getContentPane().add(outputTotal);
        outputTotal.setBounds(550, 578, 120, 24);

        txtNewOrder.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        txtNewOrder.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtNewOrder.setText("NOVO PEDIDO");
        getContentPane().add(txtNewOrder);
        txtNewOrder.setBounds(310, 20, 280, 32);

        buttonRemove.setText("REMOVER");
        buttonRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRemoveActionPerformed(evt);
            }
        });
        getContentPane().add(buttonRemove);
        buttonRemove.setBounds(130, 610, 100, 25);

        txtStatus.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtStatus.setText("Status");
        getContentPane().add(txtStatus);
        txtStatus.setBounds(650, 280, 50, 27);

        groupStatus.add(inputFinishSale);
        inputFinishSale.setText("Finalizada");
        getContentPane().add(inputFinishSale);
        inputFinishSale.setBounds(650, 310, 80, 30);

        groupStatus.add(inputPendingSale);
        inputPendingSale.setText("Pendente");
        getContentPane().add(inputPendingSale);
        inputPendingSale.setBounds(740, 310, 90, 30);

        txtItems.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtItems.setText("Itens");
        getContentPane().add(txtItems);
        txtItems.setBounds(310, 350, 40, 30);

        inputObservation.setColumns(20);
        inputObservation.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        inputObservation.setRows(5);
        observationNewOrder.setViewportView(inputObservation);

        getContentPane().add(observationNewOrder);
        observationNewOrder.setBounds(20, 380, 280, 190);

        buttonSave.setText("SALVAR");
        buttonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSaveActionPerformed(evt);
            }
        });
        getContentPane().add(buttonSave);
        buttonSave.setBounds(20, 610, 78, 25);

        inputDateOfSale.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(inputDateOfSale);
        inputDateOfSale.setBounds(20, 310, 90, 30);

        inputDeliveryForecast.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(inputDeliveryForecast);
        inputDeliveryForecast.setBounds(220, 310, 90, 30);

        txtNameProduct.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtNameProduct.setText("Nome do Produto");
        getContentPane().add(txtNameProduct);
        txtNameProduct.setBounds(360, 70, 140, 30);

        inputNameProduct.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(inputNameProduct);
        inputNameProduct.setBounds(360, 100, 380, 30);

        txtNameClient.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtNameClient.setText("Nome do Cliente");
        getContentPane().add(txtNameClient);
        txtNameClient.setBounds(430, 140, 140, 30);

        inputNameClient.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(inputNameClient);
        inputNameClient.setBounds(430, 170, 380, 30);

        txtNameProvider.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtNameProvider.setText("Nome do Fornecedor");
        getContentPane().add(txtNameProvider);
        txtNameProvider.setBounds(380, 210, 170, 30);

        inputNameProvider.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(inputNameProvider);
        inputNameProvider.setBounds(380, 240, 380, 30);

        txtRequiredField3.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        txtRequiredField3.setForeground(new java.awt.Color(255, 0, 51));
        txtRequiredField3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtRequiredField3.setText("*");
        getContentPane().add(txtRequiredField3);
        txtRequiredField3.setBounds(160, 150, 20, 20);

        txtRequiredField4.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        txtRequiredField4.setForeground(new java.awt.Color(255, 0, 51));
        txtRequiredField4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtRequiredField4.setText("*");
        getContentPane().add(txtRequiredField4);
        txtRequiredField4.setBounds(550, 140, 30, 40);

        txtRequiredField5.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        txtRequiredField5.setForeground(new java.awt.Color(255, 0, 51));
        txtRequiredField5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtRequiredField5.setText("*");
        getContentPane().add(txtRequiredField5);
        txtRequiredField5.setBounds(110, 220, 20, 20);

        txtRequiredField6.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        txtRequiredField6.setForeground(new java.awt.Color(255, 0, 51));
        txtRequiredField6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtRequiredField6.setText("*");
        getContentPane().add(txtRequiredField6);
        txtRequiredField6.setBounds(530, 220, 20, 20);

        txtRequiredField8.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        txtRequiredField8.setForeground(new java.awt.Color(255, 0, 51));
        txtRequiredField8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtRequiredField8.setText("*");
        txtRequiredField8.setToolTipText("");
        txtRequiredField8.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        getContentPane().add(txtRequiredField8);
        txtRequiredField8.setBounds(700, 280, 20, 30);

        txtRequiredField10.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        txtRequiredField10.setForeground(new java.awt.Color(255, 0, 51));
        txtRequiredField10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtRequiredField10.setText("*");
        txtRequiredField10.setToolTipText("");
        txtRequiredField10.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        getContentPane().add(txtRequiredField10);
        txtRequiredField10.setBounds(130, 280, 20, 30);

        txtRequiredField11.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        txtRequiredField11.setForeground(new java.awt.Color(255, 0, 51));
        txtRequiredField11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtRequiredField11.setText("*");
        txtRequiredField11.setToolTipText("");
        getContentPane().add(txtRequiredField11);
        txtRequiredField11.setBounds(350, 350, 20, 40);

        txtDiscount.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtDiscount.setText("Desconto");
        getContentPane().add(txtDiscount);
        txtDiscount.setBounds(450, 280, 68, 27);

        inputDiscount.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        inputDiscount.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                inputDiscountFocusLost(evt);
            }
        });
        inputDiscount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputDiscountKeyPressed(evt);
            }
        });
        getContentPane().add(inputDiscount);
        inputDiscount.setBounds(450, 310, 78, 30);

        txtDiscount1.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        txtDiscount1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtDiscount1.setText("%");
        getContentPane().add(txtDiscount1);
        txtDiscount1.setBounds(530, 310, 20, 27);

        outputSubTotal.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        outputSubTotal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        outputSubTotal.setText("0,00");
        getContentPane().add(outputSubTotal);
        outputSubTotal.setBounds(350, 578, 120, 24);

        txtSubTotal2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtSubTotal2.setText("SubTotal");
        getContentPane().add(txtSubTotal2);
        txtSubTotal2.setBounds(260, 570, 80, 40);

        setSize(new java.awt.Dimension(903, 680));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonLocaleClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLocaleClientActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonLocaleClientActionPerformed

    private void buttonLocaleProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLocaleProductActionPerformed
        if(!inputProductCode.getText().equals("")){
            getNameProduct();
        }
        else{
            JOptionPane.showMessageDialog(null, "POR FAVOR INSIRA O CÓDIGO DO PRODUTO");
        }
    }//GEN-LAST:event_buttonLocaleProductActionPerformed

    private void buttonLocaleProviderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLocaleProviderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonLocaleProviderActionPerformed

    private void buttonAllProductsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAllProductsActionPerformed
        AllProducts allProducts = new AllProducts();
        allProducts.setVisible(true);
    }//GEN-LAST:event_buttonAllProductsActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        if(x==0){
            x++;
            GetDate getDate = new GetDate();
            inputDateOfSale.setText(getDate.dateOfSystem());
            this.setTitle( this.getTitle() + ": " + Integer.toString( newOrderId() ) );
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

    private void inputDiscountFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputDiscountFocusLost
        if(!inputDiscount.getText().equals("")){
            double valueDescont = (Double.parseDouble(outputDescont.getText().replace(",", ".")) * Double.parseDouble(inputDiscount.getText().replace(",", ".")) ) / 100;
            outputDescont.setText(roundNumber.doRound(valueDescont).replace(".", ","));
            value = valueNow;
            value -= valueDescont;
            outputTotal.setText(roundNumber.doRound(value).replace(".", ","));
        }
        else{
            outputDescont.setText("0,00");
            value = valueNow;
            outputTotal.setText(roundNumber.doRound(value).replace(".", ","));
        }
    }//GEN-LAST:event_inputDiscountFocusLost

    private void inputDiscountKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputDiscountKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            if(!inputDiscount.getText().equals("")){
                double valueDescont = (Double.parseDouble(outputDescont.getText().replace(",", ".")) * Double.parseDouble(inputDiscount.getText().replace(",", ".")) ) / 100;
                outputDescont.setText(roundNumber.doRound(valueDescont).replace(".", ","));
                value = valueNow;
                value -= valueDescont;
                outputTotal.setText(roundNumber.doRound(value).replace(".", ","));
            }
            else{
                outputDescont.setText("0,00");
                value = valueNow;
                outputTotal.setText(roundNumber.doRound(value).replace(".", ","));
            }
            buttonSave.requestFocus();
        }
    }//GEN-LAST:event_inputDiscountKeyPressed

    private void inputQuantityKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputQuantityKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER && !inputProductCode.getText().equals("") && !inputNameProduct.getText().equals("") && !inputProductCode.getText().equals("")){
            getProduct();
        }
        else if(evt.getKeyCode() == evt.VK_ENTER){
            JOptionPane.showMessageDialog(null, "POR FAVOR PREENCHA OS CAMPOS DO PRODUTO PARA INSERIR NA TABELA DE PEDIDOS");
        }
    }//GEN-LAST:event_inputQuantityKeyPressed

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
    private javax.swing.JButton buttonAllProducts;
    private javax.swing.JButton buttonLocaleClient;
    private javax.swing.JButton buttonLocaleProduct;
    private javax.swing.JButton buttonLocaleProvider;
    private javax.swing.JButton buttonNewClient;
    private javax.swing.JButton buttonNewProvider;
    private javax.swing.JButton buttonRemove;
    private javax.swing.JButton buttonSave;
    private javax.swing.ButtonGroup groupFormPayment;
    private javax.swing.ButtonGroup groupStatus;
    private javax.swing.JTextField inputClient;
    private javax.swing.JTextField inputDateOfSale;
    private javax.swing.JTextField inputDeliveryForecast;
    private javax.swing.JTextField inputDiscount;
    private javax.swing.JRadioButton inputFinishSale;
    public static javax.swing.JTextField inputNameClient;
    private javax.swing.JTextField inputNameProduct;
    private javax.swing.JTextField inputNameProvider;
    private javax.swing.JTextArea inputObservation;
    private javax.swing.JRadioButton inputPendingSale;
    private javax.swing.JTextField inputProductCode;
    private javax.swing.JTextField inputProvider;
    private javax.swing.JTextField inputQuantity;
    private javax.swing.JScrollPane observationNewOrder;
    private javax.swing.JLabel outputDescont;
    private javax.swing.JLabel outputSubTotal;
    private javax.swing.JLabel outputTotal;
    private javax.swing.JTable tableItems;
    private javax.swing.JScrollPane tableNewOrder;
    private javax.swing.JLabel txtClient;
    private javax.swing.JLabel txtDateOfSale;
    private javax.swing.JLabel txtDeliveryForecast;
    private javax.swing.JLabel txtDescont;
    private javax.swing.JLabel txtDiscount;
    private javax.swing.JLabel txtDiscount1;
    private javax.swing.JLabel txtItems;
    private javax.swing.JLabel txtNameClient;
    private javax.swing.JLabel txtNameProduct;
    private javax.swing.JLabel txtNameProvider;
    public static javax.swing.JLabel txtNewOrder;
    private javax.swing.JLabel txtObservation;
    private javax.swing.JLabel txtProductCode;
    private javax.swing.JLabel txtProvider;
    private javax.swing.JLabel txtQuantity;
    private javax.swing.JLabel txtRequiredField10;
    private javax.swing.JLabel txtRequiredField11;
    private javax.swing.JLabel txtRequiredField3;
    private javax.swing.JLabel txtRequiredField4;
    private javax.swing.JLabel txtRequiredField5;
    private javax.swing.JLabel txtRequiredField6;
    private javax.swing.JLabel txtRequiredField8;
    private javax.swing.JLabel txtStatus;
    private javax.swing.JLabel txtSubTotal2;
    private javax.swing.JLabel txtTotal;
    // End of variables declaration//GEN-END:variables
}
