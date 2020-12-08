package screens;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import connectionbd.ConnectionModule;
import functioncontroller.GetFutureDates;
import javax.swing.JOptionPane;
import functioncontroller.RoundNumber;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class FormPayment extends javax.swing.JFrame {
    int x = 0;
    Connection connection = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    PreparedStatement pst2 = null;
    ResultSet rs2 = null;
    ArrayList<String> allPlots = new ArrayList<>();
    GetFutureDates getFutureDates = new GetFutureDates();
    RoundNumber roundNumber = new RoundNumber();
    public FormPayment() {
        initComponents();
        ConnectionModule connect = new ConnectionModule();
        connection = connect.getConnectionMySQL();
        buttonPayment.setVisible(false);
    }
    private void add(){
        String[] aux = this.getTitle().split(" ");
        int codSale = Integer.parseInt(aux[3]);
        String sql = "insert into paymentForm(codSale, firstPaymentDate, saleValue, inputValue, plots, plotsTime, currentInstallment)values(?,?,?,?,?,?,?)";
        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, codSale);
            pst.setString(2,inputExpirationDate.getText());
            pst.setString(3,inputSaleValue.getText().replace(",", "."));
            pst.setString(4,inputEnterValue.getText().replace(",", "."));
            pst.setInt(5,Integer.parseInt(inputPlots.getText()));
            if(inputPlotsPeriod.getSelectedItem().equals("10 dias")){
                pst.setInt(6, 10);
            }
            else if(inputPlotsPeriod.getSelectedItem().equals("15 dias")){
                pst.setInt(6, 15);
            }
            else if(inputPlotsPeriod.getSelectedItem().equals("20 dias")){
                pst.setInt(6, 20);
            }
            else if(inputPlotsPeriod.getSelectedItem().equals("30 dias")){
                pst.setInt(6, 30);
            }
            else if(inputPlotsPeriod.getSelectedItem().equals("45 dias")){
                pst.setInt(6, 45);
            }
            else if(inputPlotsPeriod.getSelectedItem().equals("60 dias")){
                pst.setInt(6, 60);
            }
            pst.setInt(7, 1);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "FORMA DE PAGAMENTO ADICIONADA COM SUCESSO");
            this.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void clearTable(){
        allPlots.clear();
        DefaultTableModel table = (DefaultTableModel) tableOfPayments.getModel();
        for(int i=table.getRowCount()-1; i >= 0; i--){
            table.removeRow(i);
        }
    }
    private void getDataToTable(){
        clearTable();
        int plots = Integer.parseInt(inputPlots.getText());
        double value = Double.parseDouble(inputSaleValue.getText().replace(",", ".")) - Double.parseDouble(inputEnterValue.getText().replace(",", "."));
        double valueToUse = (value) / plots;
        int timeDifference = 0;
        if(inputPlotsPeriod.getSelectedItem().equals("10 dias")){
            timeDifference = 10;
        }
        else if(inputPlotsPeriod.getSelectedItem().equals("15 dias")){
            timeDifference = 15;
        }
        else if(inputPlotsPeriod.getSelectedItem().equals("20 dias")){
            timeDifference = 20;
        }
        else if(inputPlotsPeriod.getSelectedItem().equals("30 dias")){
            timeDifference = 30;
        }
        else if(inputPlotsPeriod.getSelectedItem().equals("45 dias")){
            timeDifference = 45;
        }
        else if(inputPlotsPeriod.getSelectedItem().equals("60 dias")){
            timeDifference = 60;
        }
        String lastDate = inputExpirationDate.getText();
        for(int i = 0; i < plots; i++){
            String aux = Integer.toString(i+1) + ";";
            aux += roundNumber.doRound(valueToUse) + ";";
            if(i == 0){
                aux += lastDate + ";";
            }
            else{
                aux += getFutureDates.getDate(timeDifference, lastDate) + ";";
                lastDate = getFutureDates.getDate(timeDifference, lastDate);
            }
            int situation = getSituation();
            if(i+1 < situation){
                aux += "Fechada;";
            }
            else{
                aux += "Em aberto;";
            }
            allPlots.add(aux);
        }
        insertInTable();
        getValuePayed();
    }
    private int getSituation(){
        String[] aux = this.getTitle().split(" ");
        int codSale = Integer.parseInt(aux[3]);
        String sql ="select currentInstallment from paymentForm where codSale = ?";
        try {
            pst2=connection.prepareStatement(sql);
            pst2.setInt(1, codSale);
            rs2= pst2.executeQuery();
            if(rs2.next()) {
                return rs2.getInt(1);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return 0;
    }
    private void getValuePayed(){
        String[] aux = this.getTitle().split(" ");
        int codSale = Integer.parseInt(aux[3]);
        String sql ="select saleValue, inputValue from paymentForm where codSale = ?";
        try {
            pst2=connection.prepareStatement(sql);
            pst2.setInt(1, codSale);
            rs2= pst2.executeQuery();
            if(rs2.next()) {
                outputValuePayed.setText(rs2.getString(2).replace(".", ","));
                outputValueToPay.setText(roundNumber.doRound(Double.parseDouble(rs2.getString(1)) - Double.parseDouble(rs2.getString(2))).replace(".", ","));
            }
            else{
                outputValuePayed.setText(inputEnterValue.getText());
                outputValueToPay.setText(roundNumber.doRound(Double.parseDouble(inputSaleValue.getText().replace(",", ".")) - Double.parseDouble(inputEnterValue.getText().replace(",", "."))).replace(".", ","));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void getPayments(){
        String[] aux = this.getTitle().split(" ");
        int codSale = Integer.parseInt(aux[3]);
        String sql ="select firstPaymentDate, saleValue, inputValue, plots, plotsTime from paymentForm where codSale = ?";
        try {
            pst=connection.prepareStatement(sql);
            pst.setInt(1, codSale);
            rs= pst.executeQuery();
            if(rs.next()) {
                inputExpirationDate.setText(rs.getString(1));
                inputSaleValue.setText(rs.getString(2).replace(".", ","));
                inputEnterValue.setText(rs.getString(3).replace(".", ","));
                inputPlots.setText(Integer.toString( rs.getInt(4) ));
                switch (rs.getInt(5)) {
                    case 10:
                        inputPlotsPeriod.setSelectedItem("10 dias");
                        break;
                    case 15:
                        inputPlotsPeriod.setSelectedItem("15 dias");
                        break;
                    case 20:
                        inputPlotsPeriod.setSelectedItem("20 dias");
                        break;
                    case 30:
                        inputPlotsPeriod.setSelectedItem("30 dias");
                        break;
                    case 45:
                        inputPlotsPeriod.setSelectedItem("45 dias");
                        break;
                    case 60:
                        inputPlotsPeriod.setSelectedItem("60 dias");
                        break;
                    default:
                        break;
                }
                getDataToTable();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void insertInTable(){
        DefaultTableModel table = (DefaultTableModel) tableOfPayments.getModel();
        int aux = 0;
        while(true){
            if(aux<allPlots.size()){
                String[] data = allPlots.get(aux).split(";");
                table.addRow(data);
                aux++;
            }
            else{
                break;
            }
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtExpirationDate = new javax.swing.JLabel();
        txtPlots = new javax.swing.JLabel();
        inputPlots = new javax.swing.JTextField();
        txtSaleValue = new javax.swing.JLabel();
        tableFormPayment = new javax.swing.JScrollPane();
        tableOfPayments = new javax.swing.JTable();
        txtPayment = new javax.swing.JLabel();
        txtEnterValue = new javax.swing.JLabel();
        txtPlotsPeriod = new javax.swing.JLabel();
        inputPlotsPeriod = new javax.swing.JComboBox<>();
        txtFormPayment = new javax.swing.JLabel();
        buttonFinish = new javax.swing.JButton();
        inputSaleValue = new javax.swing.JTextField();
        inputEnterValue = new javax.swing.JTextField();
        inputExpirationDate = new javax.swing.JTextField();
        txtRequiredField3 = new javax.swing.JLabel();
        txtRequiredField4 = new javax.swing.JLabel();
        txtRequiredField5 = new javax.swing.JLabel();
        txtRequiredField6 = new javax.swing.JLabel();
        txtRequiredField7 = new javax.swing.JLabel();
        txtValuePayed = new javax.swing.JLabel();
        outputValuePayed = new javax.swing.JLabel();
        txtValueToPay = new javax.swing.JLabel();
        outputValueToPay = new javax.swing.JLabel();
        buttonQuote = new javax.swing.JButton();
        buttonPayment = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Forma de Pagamento");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(null);

        txtExpirationDate.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtExpirationDate.setText("Data do Primeiro Vencimento");
        getContentPane().add(txtExpirationDate);
        txtExpirationDate.setBounds(20, 70, 220, 20);

        txtPlots.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtPlots.setText("Parcelas");
        getContentPane().add(txtPlots);
        txtPlots.setBounds(520, 70, 61, 20);

        inputPlots.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        getContentPane().add(inputPlots);
        inputPlots.setBounds(520, 100, 61, 30);

        txtSaleValue.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtSaleValue.setText("Valor da Venda");
        getContentPane().add(txtSaleValue);
        txtSaleValue.setBounds(250, 70, 105, 20);

        tableOfPayments.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Parcela", "Valor", "Vencimento", "Situação"
            }
        ));
        tableFormPayment.setViewportView(tableOfPayments);

        getContentPane().add(tableFormPayment);
        tableFormPayment.setBounds(20, 210, 740, 210);

        txtPayment.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtPayment.setText("Pagamento");
        getContentPane().add(txtPayment);
        txtPayment.setBounds(20, 180, 79, 20);

        txtEnterValue.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtEnterValue.setText("Valor de Entrada");
        getContentPane().add(txtEnterValue);
        txtEnterValue.setBounds(380, 70, 117, 20);

        txtPlotsPeriod.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtPlotsPeriod.setText("Período das Parcelas");
        getContentPane().add(txtPlotsPeriod);
        txtPlotsPeriod.setBounds(610, 70, 150, 20);

        inputPlotsPeriod.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        inputPlotsPeriod.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecionar", "10 dias", "15 dias", "20 dias", "30 dias", "45 dias", "60 dias" }));
        inputPlotsPeriod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputPlotsPeriodKeyPressed(evt);
            }
        });
        getContentPane().add(inputPlotsPeriod);
        inputPlotsPeriod.setBounds(610, 100, 150, 30);

        txtFormPayment.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        txtFormPayment.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtFormPayment.setText("Forma de Pagamento");
        getContentPane().add(txtFormPayment);
        txtFormPayment.setBounds(270, 20, 244, 32);

        buttonFinish.setText("FINALIZAR");
        buttonFinish.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonFinishActionPerformed(evt);
            }
        });
        getContentPane().add(buttonFinish);
        buttonFinish.setBounds(20, 440, 120, 23);

        inputSaleValue.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        getContentPane().add(inputSaleValue);
        inputSaleValue.setBounds(250, 100, 94, 30);

        inputEnterValue.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        getContentPane().add(inputEnterValue);
        inputEnterValue.setBounds(380, 100, 94, 30);
        getContentPane().add(inputExpirationDate);
        inputExpirationDate.setBounds(20, 100, 110, 30);

        txtRequiredField3.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        txtRequiredField3.setForeground(new java.awt.Color(255, 0, 51));
        txtRequiredField3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtRequiredField3.setText("*");
        getContentPane().add(txtRequiredField3);
        txtRequiredField3.setBounds(220, 70, 20, 30);

        txtRequiredField4.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        txtRequiredField4.setForeground(new java.awt.Color(255, 0, 51));
        txtRequiredField4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtRequiredField4.setText("*");
        getContentPane().add(txtRequiredField4);
        txtRequiredField4.setBounds(350, 70, 20, 30);

        txtRequiredField5.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        txtRequiredField5.setForeground(new java.awt.Color(255, 0, 51));
        txtRequiredField5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtRequiredField5.setText("*");
        getContentPane().add(txtRequiredField5);
        txtRequiredField5.setBounds(490, 70, 20, 30);

        txtRequiredField6.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        txtRequiredField6.setForeground(new java.awt.Color(255, 0, 51));
        txtRequiredField6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtRequiredField6.setText("*");
        getContentPane().add(txtRequiredField6);
        txtRequiredField6.setBounds(580, 70, 20, 30);

        txtRequiredField7.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        txtRequiredField7.setForeground(new java.awt.Color(255, 0, 51));
        txtRequiredField7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtRequiredField7.setText("*");
        getContentPane().add(txtRequiredField7);
        txtRequiredField7.setBounds(760, 70, 20, 30);

        txtValuePayed.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtValuePayed.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtValuePayed.setText("Valor Pago");
        getContentPane().add(txtValuePayed);
        txtValuePayed.setBounds(360, 440, 90, 20);

        outputValuePayed.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        outputValuePayed.setText("0,00");
        getContentPane().add(outputValuePayed);
        outputValuePayed.setBounds(460, 435, 100, 30);

        txtValueToPay.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtValueToPay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtValueToPay.setText("Valor a Pagar");
        getContentPane().add(txtValueToPay);
        txtValueToPay.setBounds(550, 440, 110, 20);

        outputValueToPay.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        outputValueToPay.setText("0,00");
        getContentPane().add(outputValueToPay);
        outputValueToPay.setBounds(670, 435, 90, 30);

        buttonQuote.setText("COTAR");
        buttonQuote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonQuoteActionPerformed(evt);
            }
        });
        getContentPane().add(buttonQuote);
        buttonQuote.setBounds(20, 140, 80, 23);

        buttonPayment.setText("PAGAMENTO");
        buttonPayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPaymentActionPerformed(evt);
            }
        });
        getContentPane().add(buttonPayment);
        buttonPayment.setBounds(190, 440, 120, 23);

        setSize(new java.awt.Dimension(793, 506));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        if(x==0){
            x++;
            if(buttonFinish.getText().equals("DEBITAR")){
                txtRequiredField3.setVisible(false);
                txtRequiredField4.setVisible(false);
                txtRequiredField5.setVisible(false);
                txtRequiredField6.setVisible(false);
                txtRequiredField7.setVisible(false);
                buttonQuote.setEnabled(false);
                buttonPayment.setVisible(true);
            }
            getPayments();
        }
    }//GEN-LAST:event_formWindowActivated

    private void buttonFinishActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonFinishActionPerformed
        if(inputExpirationDate.getText().equals("") || inputSaleValue.getText().equals("") || inputEnterValue.getText().equals("") || inputPlots.getText().equals("") || inputPlotsPeriod.getSelectedItem().equals("Selecionar")){
            JOptionPane.showMessageDialog(null, "PREENCHA OS CAMPOS OBRIGATÓRIOS ANTES DE SALVAR");
        }
        else if(allPlots.isEmpty()){
            JOptionPane.showMessageDialog(null, "FAÇA A COTAÇÃO ANTES DE ADICIONAR A FORMA DE PAGAMENTO");
        }
        else{
            add();
        }
    }//GEN-LAST:event_buttonFinishActionPerformed

    private void inputPlotsPeriodKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputPlotsPeriodKeyPressed
     
    }//GEN-LAST:event_inputPlotsPeriodKeyPressed

    private void buttonQuoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonQuoteActionPerformed
        if(inputExpirationDate.getText().equals("") || inputSaleValue.getText().equals("") || inputEnterValue.getText().equals("") || inputPlots.getText().equals("") || inputPlotsPeriod.getSelectedItem().equals("Selecionar")){
            JOptionPane.showMessageDialog(null, "PREENCHA OS CAMPOS OBRIGATÓRIOS ANTES DE FAZER A COTAÇÃO");
        }
        else{
            getDataToTable();
        }
    }//GEN-LAST:event_buttonQuoteActionPerformed

    private void buttonPaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPaymentActionPerformed
        String[] aux = this.getTitle().split(" ");
        PayPlots payPlots = new PayPlots();
        payPlots.setTitle(payPlots.getTitle() + ": " + aux[3]);
        payPlots.setVisible(true);
    }//GEN-LAST:event_buttonPaymentActionPerformed

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
            java.util.logging.Logger.getLogger(FormPayment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormPayment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormPayment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormPayment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormPayment().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton buttonFinish;
    private javax.swing.JButton buttonPayment;
    public static javax.swing.JButton buttonQuote;
    private javax.swing.JTextField inputEnterValue;
    private javax.swing.JTextField inputExpirationDate;
    private javax.swing.JTextField inputPlots;
    private javax.swing.JComboBox<String> inputPlotsPeriod;
    public static javax.swing.JTextField inputSaleValue;
    private javax.swing.JLabel outputValuePayed;
    private javax.swing.JLabel outputValueToPay;
    private javax.swing.JScrollPane tableFormPayment;
    private javax.swing.JTable tableOfPayments;
    private javax.swing.JLabel txtEnterValue;
    private javax.swing.JLabel txtExpirationDate;
    private javax.swing.JLabel txtFormPayment;
    private javax.swing.JLabel txtPayment;
    private javax.swing.JLabel txtPlots;
    private javax.swing.JLabel txtPlotsPeriod;
    private javax.swing.JLabel txtRequiredField3;
    private javax.swing.JLabel txtRequiredField4;
    private javax.swing.JLabel txtRequiredField5;
    private javax.swing.JLabel txtRequiredField6;
    private javax.swing.JLabel txtRequiredField7;
    public static javax.swing.JLabel txtSaleValue;
    private javax.swing.JLabel txtValuePayed;
    private javax.swing.JLabel txtValueToPay;
    // End of variables declaration//GEN-END:variables
}
