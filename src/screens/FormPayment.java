package screens;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import connectionbd.ConnectionModule;
import javax.swing.JOptionPane;
import functioncontroller.GetDate;

public class FormPayment extends javax.swing.JFrame {
    int x = 0;
    Connection connection = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    public FormPayment() {
        initComponents();
        ConnectionModule connect = new ConnectionModule();
        connection = connect.getConnectionMySQL();
    }
    private void add(){
        String[] aux = this.getTitle().split(" ");
        int codSale = Integer.parseInt(aux[3]);
        String sql = "insert into paymentForm(codSale, firstPaymentDate, saleValue, inputValue, plots, plotsTime, currentInstallment)values(?,?,?,?,?,?,?)";
        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, codSale);
            pst.setString(2,inputExpirationDate.getText());
            pst.setString(3,inputSaleValue.getText());
            pst.setString(4,inputEnterValue.getText());
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
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
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
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Parcela", "Valor", "Vencimento", "Situação"
            }
        ));
        tableFormPayment.setViewportView(tableOfPayments);

        getContentPane().add(tableFormPayment);
        tableFormPayment.setBounds(20, 170, 740, 250);

        txtPayment.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtPayment.setText("Pagamento");
        getContentPane().add(txtPayment);
        txtPayment.setBounds(20, 140, 79, 20);

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
            }
        }
    }//GEN-LAST:event_formWindowActivated

    private void buttonFinishActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonFinishActionPerformed
        this.dispose();
    }//GEN-LAST:event_buttonFinishActionPerformed

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
    private javax.swing.JTextField inputEnterValue;
    private javax.swing.JTextField inputExpirationDate;
    private javax.swing.JTextField inputPlots;
    private javax.swing.JComboBox<String> inputPlotsPeriod;
    private javax.swing.JTextField inputSaleValue;
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
