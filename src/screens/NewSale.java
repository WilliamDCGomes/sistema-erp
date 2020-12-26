package screens;

import functioncontroller.GetDate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import connectionbd.ConnectionModule;
import formattingmask.MaskCPFAndCNPJ;
import formattingmask.MaskCash;
import formattingmask.MaskDate;
import formattingmask.MaskJustNumbers;
import formattingmask.MaskUpperLetter;
import functioncontroller.RoundNumber;
import java.util.ArrayList;
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
    PreparedStatement pst2 = null;
    ResultSet rs2 = null;
    PreparedStatement pst3 = null;
    ResultSet rs3 = null;
    RoundNumber roundNumber = new RoundNumber();
    ArrayList<String> products = new ArrayList<>();
    ArrayList<String> listProducts = new ArrayList<>();
    public NewSale() {
        initComponents();
        ConnectionModule connect = new ConnectionModule();
        connection = connect.getConnectionMySQL();
        inputInCash.setSelected(true);
        inputFinishSale.setSelected(true);
        inputCod.setDocument(new MaskUpperLetter());
        inputNameProduct.setDocument(new MaskUpperLetter());
        inputAmount.setDocument(new MaskJustNumbers());
        inputCodOfEmployee.setDocument(new MaskJustNumbers());
        inputNameEmployee.setDocument(new MaskUpperLetter());
        inputClient.setDocument(new MaskCPFAndCNPJ());
        inputNameClient.setDocument(new MaskUpperLetter());
        inputDateOfSale.setDocument(new MaskDate());
        inputDiscount.setDocument(new MaskCash());
    }
    private void getSubTotal(){
        double total = Double.parseDouble(outputTotal.getText().replace(",", "."));
        outputSubTotal.setText(roundNumber.doRound(valueNow));
        double discountValue = valueNow - total;
        outputDescont.setText(roundNumber.doRound(discountValue));
        value = total;
    }
    private void insertInTable(double price){
        DefaultTableModel table = (DefaultTableModel) tableSoldItems.getModel();
        double partialPrice = price * Integer.parseInt( inputAmount.getText() );
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
        product += ";" + roundNumber.doRound(partialPrice).replace('.', ',') + ";" + inputAmount.getText();
        String[] data = product.split(";");
        table.addRow(data);
        inputCod.setText("");
        inputNameProduct.setText("");
        inputAmount.setText("");
        inputCod.requestFocus();
    }
    private int getSale(){
        String sql ="select max(codSale) from sale";
        try {
            pst=connection.prepareStatement(sql);
            rs= pst.executeQuery();
            if(rs.next()) {
                return rs.getInt(1) + 1;
            }
            else{
                return 1;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return 0;
    }
    private void add(){
        String[] aux = this.getTitle().split(" ");
        int codSale = Integer.parseInt(aux[1]);
        String sql = "insert into sale(codSale, codSaller, paymentForm, paymentMethod, codClient, dateSale, statusSale, discount, totalValue)values(?,?,?,?,?,?,?,?,?)";
        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1,codSale);
            pst.setInt(2,Integer.parseInt(inputCodOfEmployee.getText()));
            if(inputInCash.isSelected()){
                pst.setString(3,"A Vista");
            }
            else if(inputTerm.isSelected()){
                pst.setString(3,"A Prazo");
            }
            pst.setString(4,inputFormPayment.getSelectedItem().toString());
            pst.setString(5,inputClient.getText());
            pst.setString(6,inputDateOfSale.getText());
            if(inputFinishSale.isSelected()){
                pst.setString(7,"Finalizada");
            }
            else if(inputPendingSale.isSelected()){
                pst.setString(7,"Pendente");
            }
            pst.setString(8,inputDiscount.getText());
            pst.setString(9,outputTotal.getText().replace(",", "."));
            pst.executeUpdate();
            addProducts(codSale);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void addProducts(int codSale){
        DefaultTableModel table = (DefaultTableModel) tableSoldItems.getModel();
        String sql = "insert into productsOfSale(codSale, barCodeProd, quantity, price)values(?,?,?,?)";
        try {
            for(int i=table.getRowCount()-1; i >= 0; i--){
                pst2 = connection.prepareStatement(sql);
                pst2.setInt(1,codSale);
                pst2.setString(2, tableSoldItems.getModel().getValueAt(i,0).toString());
                pst2.setInt(3, Integer.parseInt( tableSoldItems.getModel().getValueAt(i,4).toString() ));
                pst2.setString(4, tableSoldItems.getModel().getValueAt(i,2).toString().replace(",", "."));
                pst2.executeUpdate();
            }
            JOptionPane.showMessageDialog(null,"VENDA SALVA COM SUCESSO");
            SaleScreen saleScreen = new SaleScreen();
            saleScreen.setTitle("Venda: " + codSale);
            this.dispose();
            saleScreen.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void update(){
        String[] aux = this.getTitle().split(" ");
        int codSale = Integer.parseInt(aux[aux.length - 1]);
        String sql = "update sale set codSaller=?, paymentForm=?, paymentMethod=?, codClient=?, dateSale=?, statusSale=?, discount=?, totalValue=? where codSale=?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1,Integer.parseInt(inputCodOfEmployee.getText()));
            if(inputInCash.isSelected()){
                pst.setString(2,"A Vista");
            }
            else if(inputTerm.isSelected()){
                pst.setString(2,"A Prazo");
            }
            pst.setString(3,inputFormPayment.getSelectedItem().toString());
            pst.setString(4,inputClient.getText());
            pst.setString(5,inputDateOfSale.getText());
            if(inputFinishSale.isSelected()){
                pst.setString(6,"Finalizada");
            }
            else if(inputPendingSale.isSelected()){
                pst.setString(6,"Pendente");
            }
            pst.setString(7,inputDiscount.getText());
            pst.setString(8,outputTotal.getText().replace(",", "."));
            pst.setInt(9,codSale);
            pst.executeUpdate();
            deleteProducts(codSale);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void deleteProducts(int codSale){
        String sql = "delete from productsOfSale where codSale=?";
        try {
            pst2 = connection.prepareStatement(sql);
            pst2.setInt(1, codSale);
            pst2.executeUpdate();
            updateProducts(codSale);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void updateProducts(int codSale){
        DefaultTableModel table = (DefaultTableModel) tableSoldItems.getModel();
        String sql = "insert into productsOfSale(codSale, barCodeProd, quantity, price)values(?,?,?,?)";
        try {
            for(int i=table.getRowCount()-1; i >= 0; i--){
                pst3 = connection.prepareStatement(sql);
                pst3.setInt(1,codSale);
                pst3.setString(2, tableSoldItems.getModel().getValueAt(i,0).toString());
                pst3.setInt(3, Integer.parseInt( tableSoldItems.getModel().getValueAt(i,4).toString() ));
                pst3.setString(4, tableSoldItems.getModel().getValueAt(i,2).toString().replace(",", "."));
                pst3.executeUpdate();
            }
            JOptionPane.showMessageDialog(null,"VENDA ATUALIZADA COM SUCESSO");
            SaleScreen saleScreen = new SaleScreen();
            saleScreen.setTitle("Venda: " + codSale);
            this.dispose();
            saleScreen.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
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
    private void getData(){
        String[] aux = this.getTitle().split(" ");
        int codSale = Integer.parseInt(aux[2]);
        String sql ="select codSaller, paymentForm, paymentMethod, codClient, dateSale, statusSale, discount, totalValue from sale where codSale = ?";
        try {
            pst=connection.prepareStatement(sql);
            pst.setInt(1, codSale);
            rs= pst.executeQuery();
            if(rs.next()) {
                inputCodOfEmployee.setText(Integer.toString(rs.getInt(1)));
                if(rs.getString(2).equals("A Vista")){
                    inputInCash.setSelected(true);
                }
                else if(rs.getString(2).equals("A Prazo")){
                    inputTerm.setSelected(true);
                }
                inputFormPayment.setSelectedItem(rs.getString(3));
                inputClient.setText(rs.getString(4));
                inputDateOfSale.setText(rs.getString(5));
                if(rs.getString(6).equals("Finalizada")){
                    inputFinishSale.setSelected(true);
                }
                else if(rs.getString(6).equals("Pendente")){
                    inputPendingSale.setSelected(true);
                }
                inputDiscount.setText(rs.getString(7).replace(".", ","));
                outputTotal.setText(rs.getString(8).replace(".", ","));
            }
            getItens(codSale);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void getItens(int codSale){
        String sql ="select barCodeProd, quantity, price from productsOfSale where codSale = ?";
        try {
            pst2=connection.prepareStatement(sql);
            pst2.setInt(1, codSale);
            rs2= pst2.executeQuery();
            while(rs2.next()) {
                products.add(rs2.getString(1) + ";" + Integer.toString( rs2.getInt(2) ) + ";" + rs2.getString(3));
                valueNow += rs2.getInt(2) * Double.parseDouble(rs2.getString(3));
            }
            getList();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void getList(){
        for(int i =0; i<products.size(); i++){
            String[] id = products.get(i).split(";");
            double value = Integer.parseInt(id[1]) * Double.parseDouble(id[2].replace(",", "."));
            String sql ="select nameProduct from product where barCode = ?";
            try {
                pst3=connection.prepareStatement(sql);
                pst3.setString(1, id[0]);
                rs3= pst3.executeQuery();
                if(rs3.next()) {
                    listProducts.add(id[0] + ";" + rs3.getString(1) + ";" + id[2] + ";" + roundNumber.doRound(value).replace(".", ",") + ";" + id[1]);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        insertInTable();
    }
    private void insertInTable(){
        DefaultTableModel table = (DefaultTableModel) tableSoldItems.getModel();
        for(int i =0; i<listProducts.size(); i++){
            String[] data = listProducts.get(i).split(";");
            table.addRow(data);
        }
        nameSaller();
    }
    private void nameSaller(){
        String sql ="select nameEmployee from employee where id = ?";
        try {
            pst=connection.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt( inputCodOfEmployee.getText() ));
            rs= pst.executeQuery();
            if(rs.next()) {
                inputNameEmployee.setText(rs.getString(1));
            }
            getClient();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void getClient(){
        String sql ="select clientName from clients where cpf = ?";
        try {
            pst2=connection.prepareStatement(sql);
            pst2.setString(1, inputClient.getText());
            rs2= pst2.executeQuery();
            if(rs2.next()) {
                inputNameClient.setText(rs2.getString(1));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        getSubTotal();
    }
    private boolean hasPaymentForm(){
        String[] aux = this.getTitle().split(" ");
        int codSale = Integer.parseInt(aux[2]);
        String sql ="select codSale from paymentForm where codSale = ?";
        try {
            pst=connection.prepareStatement(sql);
            pst.setInt(1, codSale);
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
        txtFormPayment = new javax.swing.JLabel();
        inputInCash = new javax.swing.JRadioButton();
        inputTerm = new javax.swing.JRadioButton();
        txtDiscount1 = new javax.swing.JLabel();
        txtStatus = new javax.swing.JLabel();
        inputFinishSale = new javax.swing.JRadioButton();
        inputPendingSale = new javax.swing.JRadioButton();
        buttonCancele = new javax.swing.JButton();
        buttonLocaleProduct = new javax.swing.JButton();
        buttonAllProduct = new javax.swing.JButton();
        txtNameProduct = new javax.swing.JLabel();
        inputNameProduct = new javax.swing.JTextField();
        buttonLocaleEmployee = new javax.swing.JButton();
        txtNameEmployee = new javax.swing.JLabel();
        inputNameEmployee = new javax.swing.JTextField();
        txtNameClient = new javax.swing.JLabel();
        inputNameClient = new javax.swing.JTextField();
        inputDateOfSale = new javax.swing.JTextField();
        buttonAllEmployees = new javax.swing.JButton();
        txtSubTotal1 = new javax.swing.JLabel();
        outputDescont = new javax.swing.JLabel();
        txtRequiredField3 = new javax.swing.JLabel();
        txtRequiredField4 = new javax.swing.JLabel();
        txtRequiredField5 = new javax.swing.JLabel();
        txtRequiredField6 = new javax.swing.JLabel();
        txtRequiredField7 = new javax.swing.JLabel();
        txtRequiredField8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Venda:");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(null);

        txtNewSale.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        txtNewSale.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtNewSale.setText("Nova Venda");
        getContentPane().add(txtNewSale);
        txtNewSale.setBounds(257, 10, 370, 32);

        txtCod.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtCod.setText("Código");
        getContentPane().add(txtCod);
        txtCod.setBounds(20, 50, 63, 27);

        inputCod.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        inputCod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputCodKeyPressed(evt);
            }
        });
        getContentPane().add(inputCod);
        inputCod.setBounds(20, 80, 90, 30);

        txtAmount.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtAmount.setText("Quantidade");
        getContentPane().add(txtAmount);
        txtAmount.setBounds(710, 50, 82, 27);

        inputAmount.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        inputAmount.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                inputAmountFocusLost(evt);
            }
        });
        inputAmount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputAmountKeyPressed(evt);
            }
        });
        getContentPane().add(inputAmount);
        inputAmount.setBounds(710, 80, 104, 30);

        txtCodOfSaller.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtCodOfSaller.setText("Cod Vendedor");
        getContentPane().add(txtCodOfSaller);
        txtCodOfSaller.setBounds(20, 120, 102, 27);

        inputCodOfEmployee.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        inputCodOfEmployee.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputCodOfEmployeeKeyPressed(evt);
            }
        });
        getContentPane().add(inputCodOfEmployee);
        inputCodOfEmployee.setBounds(20, 150, 90, 30);

        txtPaymentMethod.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtPaymentMethod.setText("Meio de Pagamento");
        getContentPane().add(txtPaymentMethod);
        txtPaymentMethod.setBounds(20, 300, 140, 27);

        inputFormPayment.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
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
        inputFormPayment.setBounds(20, 330, 110, 30);

        txtClient.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtClient.setText("CPF Cliente");
        getContentPane().add(txtClient);
        txtClient.setBounds(20, 190, 110, 27);

        inputClient.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        inputClient.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputClientKeyPressed(evt);
            }
        });
        getContentPane().add(inputClient);
        inputClient.setBounds(20, 220, 143, 30);

        buttonLocaleClient.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        buttonLocaleClient.setText("LOCALIZAR");
        buttonLocaleClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLocaleClientActionPerformed(evt);
            }
        });
        getContentPane().add(buttonLocaleClient);
        buttonLocaleClient.setBounds(170, 220, 100, 30);

        buttonNewClient.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        buttonNewClient.setText("NOVO");
        buttonNewClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNewClientActionPerformed(evt);
            }
        });
        getContentPane().add(buttonNewClient);
        buttonNewClient.setBounds(280, 220, 79, 30);

        txtDiscount.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtDiscount.setText("Desconto");
        getContentPane().add(txtDiscount);
        txtDiscount.setBounds(550, 260, 68, 27);

        inputDiscount.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
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
        inputDiscount.setBounds(630, 260, 78, 30);

        txtSubTotal.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtSubTotal.setText("SubTotal");
        getContentPane().add(txtSubTotal);
        txtSubTotal.setBounds(564, 620, 70, 20);

        outputSubTotal.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        outputSubTotal.setText("0,00");
        getContentPane().add(outputSubTotal);
        outputSubTotal.setBounds(640, 615, 100, 30);

        txtTotal.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtTotal.setText("Total");
        getContentPane().add(txtTotal);
        txtTotal.setBounds(736, 620, 40, 20);

        outputTotal.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        outputTotal.setText("0,00");
        getContentPane().add(outputTotal);
        outputTotal.setBounds(780, 615, 90, 30);

        tableSoldItems.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código do Produto", "Nome do Produto", "Preço de Venda", "Valor Parcial", "Quantidade"
            }
        ));
        tableNewSale.setViewportView(tableSoldItems);

        getContentPane().add(tableNewSale);
        tableNewSale.setBounds(20, 400, 850, 200);

        buttonSave.setText("SALVAR");
        buttonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSaveActionPerformed(evt);
            }
        });
        buttonSave.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                buttonSaveKeyPressed(evt);
            }
        });
        getContentPane().add(buttonSave);
        buttonSave.setBounds(20, 620, 80, 25);

        buttomRemoveItems.setText("REMOVER");
        buttomRemoveItems.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttomRemoveItemsActionPerformed(evt);
            }
        });
        getContentPane().add(buttomRemoveItems);
        buttomRemoveItems.setBounds(130, 620, 90, 25);

        txtItems.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtItems.setText("Itens");
        getContentPane().add(txtItems);
        txtItems.setBounds(20, 370, 35, 27);

        txtDateOfSale.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtDateOfSale.setText("Data da Venda");
        getContentPane().add(txtDateOfSale);
        txtDateOfSale.setBounds(20, 260, 101, 27);

        txtFormPayment.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtFormPayment.setText("Forma de Pagamento");
        getContentPane().add(txtFormPayment);
        txtFormPayment.setBounds(730, 120, 149, 27);

        groupFormPayment.add(inputInCash);
        inputInCash.setText("A Vista");
        getContentPane().add(inputInCash);
        inputInCash.setBounds(730, 150, 61, 30);

        groupFormPayment.add(inputTerm);
        inputTerm.setText("A Prazo");
        getContentPane().add(inputTerm);
        inputTerm.setBounds(820, 150, 64, 30);

        txtDiscount1.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        txtDiscount1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtDiscount1.setText("%");
        getContentPane().add(txtDiscount1);
        txtDiscount1.setBounds(710, 260, 20, 27);

        txtStatus.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtStatus.setText("Status:");
        getContentPane().add(txtStatus);
        txtStatus.setBounds(280, 260, 57, 30);

        groupStatus.add(inputFinishSale);
        inputFinishSale.setText("Finalizada");
        getContentPane().add(inputFinishSale);
        inputFinishSale.setBounds(360, 260, 80, 30);

        groupStatus.add(inputPendingSale);
        inputPendingSale.setText("Pendente");
        getContentPane().add(inputPendingSale);
        inputPendingSale.setBounds(440, 260, 90, 30);

        buttonCancele.setText("CANCELAR");
        buttonCancele.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCanceleActionPerformed(evt);
            }
        });
        getContentPane().add(buttonCancele);
        buttonCancele.setBounds(250, 620, 100, 25);

        buttonLocaleProduct.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        buttonLocaleProduct.setText("LOCALIZAR");
        buttonLocaleProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLocaleProductActionPerformed(evt);
            }
        });
        getContentPane().add(buttonLocaleProduct);
        buttonLocaleProduct.setBounds(120, 80, 100, 30);

        buttonAllProduct.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        buttonAllProduct.setText("TODOS");
        buttonAllProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAllProductActionPerformed(evt);
            }
        });
        getContentPane().add(buttonAllProduct);
        buttonAllProduct.setBounds(230, 80, 79, 30);

        txtNameProduct.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        txtNameProduct.setText("Nome do Produto");
        getContentPane().add(txtNameProduct);
        txtNameProduct.setBounds(320, 50, 140, 30);

        inputNameProduct.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        getContentPane().add(inputNameProduct);
        inputNameProduct.setBounds(320, 80, 380, 30);

        buttonLocaleEmployee.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        buttonLocaleEmployee.setText("LOCALIZAR");
        buttonLocaleEmployee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLocaleEmployeeActionPerformed(evt);
            }
        });
        getContentPane().add(buttonLocaleEmployee);
        buttonLocaleEmployee.setBounds(120, 150, 100, 30);

        txtNameEmployee.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        txtNameEmployee.setText("Nome do Vendedor");
        getContentPane().add(txtNameEmployee);
        txtNameEmployee.setBounds(320, 120, 160, 30);

        inputNameEmployee.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        getContentPane().add(inputNameEmployee);
        inputNameEmployee.setBounds(320, 150, 380, 30);

        txtNameClient.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        txtNameClient.setText("Nome do Cliente");
        getContentPane().add(txtNameClient);
        txtNameClient.setBounds(380, 190, 160, 30);

        inputNameClient.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        getContentPane().add(inputNameClient);
        inputNameClient.setBounds(380, 220, 380, 30);

        inputDateOfSale.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        getContentPane().add(inputDateOfSale);
        inputDateOfSale.setBounds(140, 260, 100, 30);

        buttonAllEmployees.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        buttonAllEmployees.setText("TODOS");
        buttonAllEmployees.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAllEmployeesActionPerformed(evt);
            }
        });
        getContentPane().add(buttonAllEmployees);
        buttonAllEmployees.setBounds(230, 150, 79, 30);

        txtSubTotal1.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtSubTotal1.setText("Desconto");
        getContentPane().add(txtSubTotal1);
        txtSubTotal1.setBounds(374, 620, 80, 20);

        outputDescont.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        outputDescont.setText("0,00");
        getContentPane().add(outputDescont);
        outputDescont.setBounds(450, 615, 100, 30);

        txtRequiredField3.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        txtRequiredField3.setForeground(new java.awt.Color(255, 0, 51));
        txtRequiredField3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtRequiredField3.setText("*");
        getContentPane().add(txtRequiredField3);
        txtRequiredField3.setBounds(130, 120, 20, 30);

        txtRequiredField4.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        txtRequiredField4.setForeground(new java.awt.Color(255, 0, 51));
        txtRequiredField4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtRequiredField4.setText("*");
        getContentPane().add(txtRequiredField4);
        txtRequiredField4.setBounds(110, 190, 20, 30);

        txtRequiredField5.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        txtRequiredField5.setForeground(new java.awt.Color(255, 0, 51));
        txtRequiredField5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtRequiredField5.setText("*");
        getContentPane().add(txtRequiredField5);
        txtRequiredField5.setBounds(120, 260, 20, 30);

        txtRequiredField6.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        txtRequiredField6.setForeground(new java.awt.Color(255, 0, 51));
        txtRequiredField6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtRequiredField6.setText("*");
        getContentPane().add(txtRequiredField6);
        txtRequiredField6.setBounds(330, 260, 20, 30);

        txtRequiredField7.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        txtRequiredField7.setForeground(new java.awt.Color(255, 0, 51));
        txtRequiredField7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtRequiredField7.setText("*");
        getContentPane().add(txtRequiredField7);
        txtRequiredField7.setBounds(160, 300, 20, 30);

        txtRequiredField8.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        txtRequiredField8.setForeground(new java.awt.Color(255, 0, 51));
        txtRequiredField8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtRequiredField8.setText("*");
        getContentPane().add(txtRequiredField8);
        txtRequiredField8.setBounds(880, 120, 20, 30);

        setSize(new java.awt.Dimension(912, 688));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void inputFormPaymentItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_inputFormPaymentItemStateChanged
        
    }//GEN-LAST:event_inputFormPaymentItemStateChanged

    private void inputFormPaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputFormPaymentActionPerformed
        if(inputTerm.isSelected()){
            FormPayment formPayment = new FormPayment();
            String[] aux = this.getTitle().split(" ");
            if(txtNewSale.getText().equals("Nova Venda")){
                formPayment.setTitle(formPayment.getTitle() + ": " + aux[1]);
            }
            else{
                formPayment.setTitle(formPayment.getTitle() + ": " + aux[2]);
            }
            formPayment.inputSaleValue.setText(outputTotal.getText());
            if(txtNewSale.getText().equals("Editar Venda") && hasPaymentForm()){
                formPayment.newPayment = false;
            }
            formPayment.setVisible(true);
        }
    }//GEN-LAST:event_inputFormPaymentActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        if (x==0){
            x++;
            if(txtNewSale.getText().equals("Nova Venda")){
                GetDate getDateSystem = new GetDate();
                inputDateOfSale.setText(getDateSystem.dateOfSystem());
                this.setTitle(this.getTitle() + " " + getSale());
            }
            else if(txtNewSale.getText().equals("Editar Venda")){
                getData();
            }
        }
    }//GEN-LAST:event_formWindowActivated

    private void buttonCanceleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCanceleActionPerformed
        this.dispose();
    }//GEN-LAST:event_buttonCanceleActionPerformed

    private void buttonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSaveActionPerformed
        if(outputTotal.getText().equals("0,00")&&outputSubTotal.getText().equals("0,00")&&outputDescont.getText().equals("0,00")){
            JOptionPane.showMessageDialog(null, "VOCÊ NÃO ADICIONOU NENHUM ITEM NA VENDA");
        }
        else if(inputCodOfEmployee.getText().equals("")||inputClient.getText().equals("")||inputDateOfSale.getText().equals("")||(!inputFinishSale.isSelected()&&!inputPendingSale.isSelected())||(!inputInCash.isSelected()&&!inputTerm.isSelected())||inputFormPayment.getSelectedItem().equals("Selecionar")){
            JOptionPane.showMessageDialog(null, "PREENCHA TODOS OS CAMPOS OBRIGATÓRIOS ANTES DE FINALIZAR A VENDA");
        }
        else if(txtNewSale.getText().equals("Nova Venda")){
            add();
        }
        else if(txtNewSale.getText().equals("Editar Venda")){
            update();
        }
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

    private void buttonLocaleProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLocaleProductActionPerformed
        if(!inputCod.getText().equals("")){
            getNameProduct();
        }
        else{
            JOptionPane.showMessageDialog(null, "POR FAVOR INSIRA O CÓDIGO DO PRODUTO");
        }
    }//GEN-LAST:event_buttonLocaleProductActionPerformed

    private void buttonAllProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAllProductActionPerformed
        AllProducts allProducts = new AllProducts();
        allProducts.setVisible(true);
    }//GEN-LAST:event_buttonAllProductActionPerformed

    private void buttonLocaleEmployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLocaleEmployeeActionPerformed
        if(!inputCodOfEmployee.getText().equals("")){
            getNameEmployee();
        }
        else{
            JOptionPane.showMessageDialog(null, "POR FAVOR INSIRA O CÓDIGO DO VENDEDOR");
        }
    }//GEN-LAST:event_buttonLocaleEmployeeActionPerformed

    private void buttonLocaleClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLocaleClientActionPerformed
        if(!inputClient.getText().equals("")){
            getClientName();
        }
        else{
            JOptionPane.showMessageDialog(null, "POR FAVOR INSIRA O CPF DO CLIENTE");
        }
    }//GEN-LAST:event_buttonLocaleClientActionPerformed

    private void buttonNewClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNewClientActionPerformed
        NewClient newClient = new NewClient();
        newClient.cameFromSale(this);
        newClient.setVisible(true);
    }//GEN-LAST:event_buttonNewClientActionPerformed

    private void buttonAllEmployeesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAllEmployeesActionPerformed
        LocaleEmployee localeEmployee = new LocaleEmployee();
        localeEmployee.setVisible(true);
    }//GEN-LAST:event_buttonAllEmployeesActionPerformed

    private void inputDiscountFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputDiscountFocusLost
        if(!inputDiscount.getText().equals("")){
            double valueDescont = (Double.parseDouble(outputSubTotal.getText().replace(",", ".")) * Double.parseDouble(inputDiscount.getText().replace(",", ".")) ) / 100;
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

    private void buttomRemoveItemsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttomRemoveItemsActionPerformed
        int set = tableSoldItems.getSelectedRow();
        if(set>=0){
            valueNow -= Double.parseDouble(tableSoldItems.getModel().getValueAt(set,3).toString().replace(",", "."));
            value = valueNow;
            outputSubTotal.setText(roundNumber.doRound(valueNow).replace(".", ","));
            if(!inputDiscount.getText().equals("")){
                double valueDescont = (Double.parseDouble(outputSubTotal.getText().replace(",", ".")) * Double.parseDouble(inputDiscount.getText().replace(",", ".")) ) / 100;
                outputDescont.setText(roundNumber.doRound(valueDescont).replace(".", ","));
                value -= valueDescont;
                outputTotal.setText(roundNumber.doRound(value).replace(".", ","));
            }
            else{
                value = valueNow;
                outputTotal.setText(roundNumber.doRound(value).replace(".", ","));
            }
            DefaultTableModel table = (DefaultTableModel) tableSoldItems.getModel();
            table.removeRow(set);
            JOptionPane.showMessageDialog(null, "ITEM REMOVIDO DA VENDA");
        }
        else{
            JOptionPane.showMessageDialog(null, "SELECIONE UM REGISTRO ANTES");
        }
    }//GEN-LAST:event_buttomRemoveItemsActionPerformed

    private void inputAmountFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputAmountFocusLost
        if(!inputCod.getText().equals("") && !inputNameProduct.getText().equals("") && !inputAmount.getText().equals("")){
            getProduct();
        }
    }//GEN-LAST:event_inputAmountFocusLost

    private void inputDiscountKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputDiscountKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            if(!inputDiscount.getText().equals("")){
                double valueDescont = (Double.parseDouble(outputSubTotal.getText().replace(",", ".")) * Double.parseDouble(inputDiscount.getText().replace(",", ".")) ) / 100;
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

    private void buttonSaveKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buttonSaveKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            if(outputTotal.getText().equals("0,00")&&outputSubTotal.getText().equals("0,00")&&outputDescont.getText().equals("0,00")){
                JOptionPane.showMessageDialog(null, "VOCÊ NÃO ADICIONOU NENHUM ITEM NA VENDA");
            }
            else if(inputCodOfEmployee.getText().equals("")||inputClient.getText().equals("")||inputDateOfSale.getText().equals("")||(!inputFinishSale.isSelected()&&!inputPendingSale.isSelected())||(!inputInCash.isSelected()&&!inputTerm.isSelected())||inputFormPayment.getSelectedItem().equals("Selecionar")){
                JOptionPane.showMessageDialog(null, "PREENCHA TODOS OS CAMPOS OBRIGATÓRIOS ANTES DE FINALIZAR A VENDA");
            }
            else{
                add();
            }
        }
    }//GEN-LAST:event_buttonSaveKeyPressed

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
    private javax.swing.JButton buttonAllEmployees;
    private javax.swing.JButton buttonAllProduct;
    public static javax.swing.JButton buttonCancele;
    private javax.swing.JButton buttonLocaleClient;
    private javax.swing.JButton buttonLocaleEmployee;
    private javax.swing.JButton buttonLocaleProduct;
    private javax.swing.JButton buttonNewClient;
    private javax.swing.JButton buttonSave;
    private javax.swing.ButtonGroup groupFormPayment;
    private javax.swing.ButtonGroup groupStatus;
    private javax.swing.JTextField inputAmount;
    public static javax.swing.JTextField inputClient;
    private javax.swing.JTextField inputCod;
    private javax.swing.JTextField inputCodOfEmployee;
    private javax.swing.JTextField inputDateOfSale;
    private javax.swing.JTextField inputDiscount;
    private javax.swing.JRadioButton inputFinishSale;
    private javax.swing.JComboBox<String> inputFormPayment;
    private javax.swing.JRadioButton inputInCash;
    public static javax.swing.JTextField inputNameClient;
    private javax.swing.JTextField inputNameEmployee;
    private javax.swing.JTextField inputNameProduct;
    private javax.swing.JRadioButton inputPendingSale;
    private javax.swing.JRadioButton inputTerm;
    private javax.swing.JLabel outputDescont;
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
    private javax.swing.JLabel txtRequiredField3;
    private javax.swing.JLabel txtRequiredField4;
    private javax.swing.JLabel txtRequiredField5;
    private javax.swing.JLabel txtRequiredField6;
    private javax.swing.JLabel txtRequiredField7;
    private javax.swing.JLabel txtRequiredField8;
    private javax.swing.JLabel txtStatus;
    private javax.swing.JLabel txtSubTotal;
    private javax.swing.JLabel txtSubTotal1;
    private javax.swing.JLabel txtTotal;
    // End of variables declaration//GEN-END:variables
}
