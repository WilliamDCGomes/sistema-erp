package screens;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import connectionbd.ConnectionModule;
import formattingmask.MaskDate;
import formattingmask.MaskJustNumbers;
import formattingmask.MaskUpperLetter;
import functioncontroller.RoundNumber;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class SaleScreen extends javax.swing.JFrame {
    int x = 0;
    Connection connection = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    PreparedStatement pst2 = null;
    ResultSet rs2 = null;
    PreparedStatement pst3 = null;
    ResultSet rs3 = null;
    PreparedStatement pst4 = null;
    ResultSet rs4 = null;
    String cpfClient = null;
    ArrayList<String> products = new ArrayList<>();
    ArrayList<String> listProducts = new ArrayList<>();
    RoundNumber roundNumber = new RoundNumber();
    public SaleScreen() {
        initComponents();
        ConnectionModule connect = new ConnectionModule();
        connection = connect.getConnectionMySQL();
        outputCodSale.setDocument(new MaskJustNumbers());
        outputCodSaller.setDocument(new MaskJustNumbers());
        inputNameEmployee.setDocument(new MaskUpperLetter());
        outputClient.setDocument(new MaskUpperLetter());
        outputFormPayment.setDocument(new MaskUpperLetter());
        outputDateSale.setDocument(new MaskDate());
    }
    private void clearTable(){
        products.clear();
        listProducts.clear();
        DefaultTableModel table = (DefaultTableModel) tableSoldItems.getModel();
        for(int i=table.getRowCount()-1; i >= 0; i--){
            table.removeRow(i);
        }
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
                outputCodSale.setText(aux[1]);
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
            pst.setInt(1, Integer.parseInt( outputCodSaller.getText() ));
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
            pst2.setString(1, cpfClient);
            rs2= pst2.executeQuery();
            if(rs2.next()) {
                outputClient.setText(rs2.getString(1));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private boolean hasPaymentForm(){
        String[] aux = this.getTitle().split(" ");
        int codSale = Integer.parseInt(aux[1]);
        String sql ="select codSale from paymentForm where codSale = ?";
        try {
            pst4=connection.prepareStatement(sql);
            pst4.setInt(1, codSale);
            rs4= pst4.executeQuery();
            if(rs4.next()) {
                return true;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return false;
    }
    private void deleteSale(int codSale){
        int confirma = JOptionPane.showConfirmDialog(null, "TEM CERTEZA QUE DESEJA DELETAR ESSE VENDA?","ATENÇÃO",JOptionPane.YES_NO_OPTION);
        if(confirma==JOptionPane.YES_OPTION){
            String sql = "delete from sale where codSale=?";
            try {
                pst = connection.prepareStatement(sql);
                pst.setInt(1, codSale);
                pst.executeUpdate();
                deleteProducts(codSale);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }
    private void deleteProducts(int codSale){
        String sql = "delete from productsOfSale where codSale=?";
        try {
            pst2 = connection.prepareStatement(sql);
            pst2.setInt(1, codSale);
            pst2.executeUpdate();
            if(hasPaymentForm()){
                deletePaymentForm(codSale);
            }
            else{
                JOptionPane.showMessageDialog(null, "VENDA DELETADA COM SUCESSO");
                this.dispose();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void deletePaymentForm(int codSale){
        String sql = "delete from paymentForm where codSale=?";
        try {
            pst3 = connection.prepareStatement(sql);
            pst3.setInt(1, codSale);
            pst3.executeUpdate();
            JOptionPane.showMessageDialog(null, "VENDA DELETADA COM SUCESSO");
            this.dispose();
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
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(null);

        txtCodSale.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        txtCodSale.setText("Código da Venda");
        getContentPane().add(txtCodSale);
        txtCodSale.setBounds(11, 65, 123, 19);

        outputCodSale.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(outputCodSale);
        outputCodSale.setBounds(11, 90, 80, 30);

        txtCodSaller.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        txtCodSaller.setText("Código do Vendedor");
        getContentPane().add(txtCodSaller);
        txtCodSaller.setBounds(180, 60, 148, 30);

        outputCodSaller.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(outputCodSaller);
        outputCodSaller.setBounds(180, 90, 100, 30);

        txtFormPayment.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        txtFormPayment.setText("Forma de Pagamento");
        getContentPane().add(txtFormPayment);
        txtFormPayment.setBounds(520, 130, 156, 30);

        outputFormPayment.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(outputFormPayment);
        outputFormPayment.setBounds(520, 160, 170, 30);

        txtPaymentMethod.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtPaymentMethod.setText("Meio de Pagamento");
        getContentPane().add(txtPaymentMethod);
        txtPaymentMethod.setBounds(10, 130, 140, 27);

        outputPaymentMethod.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        outputPaymentMethod.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecionar", "Dinheiro", "Boleto", "Carnê", "Cartão", "Cheque" }));
        outputPaymentMethod.setEnabled(false);
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

        outputClient.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
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
        outputTotal.setBounds(610, 510, 140, 24);

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
        buttonEdit.setBounds(130, 510, 80, 25);

        buttonDelete.setText("EXCLUIR");
        buttonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDeleteActionPerformed(evt);
            }
        });
        getContentPane().add(buttonDelete);
        buttonDelete.setBounds(230, 510, 90, 25);

        buttonAllSales.setText("TODAS AS VENDAS");
        buttonAllSales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAllSalesActionPerformed(evt);
            }
        });
        getContentPane().add(buttonAllSales);
        buttonAllSales.setBounds(340, 510, 140, 25);

        txtSale.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtSale.setText("Venda");
        getContentPane().add(txtSale);
        txtSale.setBounds(340, 20, 74, 29);

        txtStatus.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtStatus.setText("Status:");
        getContentPane().add(txtStatus);
        txtStatus.setBounds(170, 200, 49, 27);

        buttonShowPaymentMethod.setText("PARCELAS");
        buttonShowPaymentMethod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonShowPaymentMethodActionPerformed(evt);
            }
        });
        getContentPane().add(buttonShowPaymentMethod);
        buttonShowPaymentMethod.setBounds(10, 510, 100, 25);

        buttonPrinter.setText("IMPRIMIR");
        getContentPane().add(buttonPrinter);
        buttonPrinter.setBounds(500, 510, 90, 25);

        txtNameEmployee.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        txtNameEmployee.setText("Nome do Vendedor");
        getContentPane().add(txtNameEmployee);
        txtNameEmployee.setBounds(370, 60, 160, 30);

        inputNameEmployee.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        getContentPane().add(inputNameEmployee);
        inputNameEmployee.setBounds(370, 90, 380, 30);

        outputStatus.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        outputStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pendente", "Finalizado" }));
        outputStatus.setEnabled(false);
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

        outputDateSale.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
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
        if(hasPaymentForm()){
            FormPayment formPayment = new FormPayment();
            String[] aux = this.getTitle().split(" ");
            formPayment.setTitle(formPayment.getTitle() + ": " + aux[1]);
            formPayment.buttonFinish.setText("DEBITAR");
            formPayment.inputSaleValue.setText(outputTotal.getText());
            formPayment.buttonRefresh.setVisible(true);
            formPayment.setVisible(true);
        }
        else{
            JOptionPane.showMessageDialog(null, "ESSA VENDA NÃO FOI PARCELADA");
        }
    }//GEN-LAST:event_buttonShowPaymentMethodActionPerformed

    private void buttonAllSalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAllSalesActionPerformed
        PendingSale pendingSale = new PendingSale();
        pendingSale.txtPendingSale.setText("Todas as Vendas");
        pendingSale.setTitle("Todas as Vendas");
        this.dispose();
        pendingSale.setVisible(true);
    }//GEN-LAST:event_buttonAllSalesActionPerformed

    private void buttonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDeleteActionPerformed
        String[] aux = this.getTitle().split(" ");
        int id = Integer.parseInt(aux[aux.length - 1]);
        deleteSale(id);
    }//GEN-LAST:event_buttonDeleteActionPerformed

    private void buttonEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEditActionPerformed
        String[] aux = this.getTitle().split(" ");
        NewSale newSale = new NewSale();
        newSale.txtNewSale.setText("Editar Venda");
        newSale.setTitle("Editar Venda: " + aux[1]);
        this.dispose();
        newSale.setVisible(true);
    }//GEN-LAST:event_buttonEditActionPerformed

    private void outputStatusItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_outputStatusItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_outputStatusItemStateChanged

    private void outputStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_outputStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_outputStatusActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        if(x == 0){
            x++;
            clearTable();
            getData();
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
