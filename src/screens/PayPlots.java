package screens;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import connectionbd.ConnectionModule;
import functioncontroller.RoundNumber;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author willi
 */
public class PayPlots extends javax.swing.JFrame {
    int x = 0;
    Connection connection = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    PreparedStatement pst2 = null;
    ResultSet rs2 = null;
    RoundNumber roundNumber = new RoundNumber();
    public ArrayList<String> plotsToPass = null;
    public PayPlots() {
        initComponents();
        ConnectionModule connect = new ConnectionModule();
        connection = connect.getConnectionMySQL();
    }

    private void insertInTable(){
        if(plotsToPass != null){
            DefaultTableModel table = (DefaultTableModel) tableOfPlots.getModel();
            int aux = 0;
            while(true){
                if(aux<plotsToPass.size()){
                    String[] data = plotsToPass.get(aux).split(";");
                    table.addRow(data);
                    aux++;
                }
                else{
                    break;
                }
            }
        }
    }
    private void updateProduct(String value, int currentInstallment, int check){
        String[] aux = this.getTitle().split(" ");
        int codSale = Integer.parseInt(aux[2]);
        String sql = "update paymentForm set valuePayed=?, currentInstallment=? where codSale=?";
        try {
            pst=connection.prepareStatement(sql);
            pst.setString(1,value);
            pst.setInt(2, currentInstallment);
            pst.setInt(3, codSale);
            pst.executeUpdate();
            if(check == 1){
                JOptionPane.showMessageDialog(null,"PARCELA PAGA!");
            }
            this.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }
    private String getData(){
        String[] aux = this.getTitle().split(" ");
        int codSale = Integer.parseInt(aux[2]);
        String sql ="select valuePayed, currentInstallment from paymentForm where codSale = ?";
        try {
            pst2=connection.prepareStatement(sql);
            pst2.setInt(1, codSale);
            rs2= pst2.executeQuery();
            if(rs2.next()) {
                String data = rs2.getString(1) + ";";
                data += Integer.toString( rs2.getInt(2) );
                return data;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtPayPlots = new javax.swing.JLabel();
        tableFormPayment = new javax.swing.JScrollPane();
        tableOfPlots = new javax.swing.JTable();
        txtPlots = new javax.swing.JLabel();
        buttonPay = new javax.swing.JButton();
        txtValueToPay = new javax.swing.JLabel();
        outputValueToPay = new javax.swing.JLabel();
        txtValuePayed = new javax.swing.JLabel();
        outputValuePayed = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pagar Parcela");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(null);

        txtPayPlots.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        txtPayPlots.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtPayPlots.setText("Pagamento de Parcelas");
        getContentPane().add(txtPayPlots);
        txtPayPlots.setBounds(130, 20, 320, 32);

        tableOfPlots.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Parcela", "Valor", "Vencimento", "Situação"
            }
        ));
        tableFormPayment.setViewportView(tableOfPlots);

        getContentPane().add(tableFormPayment);
        tableFormPayment.setBounds(20, 90, 550, 210);

        txtPlots.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtPlots.setText("Parcelas");
        getContentPane().add(txtPlots);
        txtPlots.setBounds(20, 60, 61, 20);

        buttonPay.setText("PAGAR");
        buttonPay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPayActionPerformed(evt);
            }
        });
        getContentPane().add(buttonPay);
        buttonPay.setBounds(20, 320, 90, 23);

        txtValueToPay.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtValueToPay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtValueToPay.setText("Valor a Pagar");
        getContentPane().add(txtValueToPay);
        txtValueToPay.setBounds(350, 320, 110, 20);

        outputValueToPay.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        outputValueToPay.setText("0,00");
        getContentPane().add(outputValueToPay);
        outputValueToPay.setBounds(480, 315, 90, 30);

        txtValuePayed.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtValuePayed.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtValuePayed.setText("Valor Pago");
        getContentPane().add(txtValuePayed);
        txtValuePayed.setBounds(150, 320, 90, 20);

        outputValuePayed.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        outputValuePayed.setText("0,00");
        getContentPane().add(outputValuePayed);
        outputValuePayed.setBounds(250, 315, 100, 30);

        setSize(new java.awt.Dimension(600, 390));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonPayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPayActionPerformed
        int[] set = tableOfPlots.getSelectedRows();
        for(int i=0;i<set.length;i++){
            String[] data = getData().split(";");
            if(set[i]>=0 && data!=null){
                if(tableOfPlots.getModel().getValueAt(set[i],3).toString().equals("Fechada")){
                    if(set.length == 1){
                        JOptionPane.showMessageDialog(null, "A PARCELA QUE VOCÊ SELECIONOU JÁ ESTÁ PAGA");
                    }
                    else if(set.length > 1){
                        JOptionPane.showMessageDialog(null, "AO MENOS UMA DAS PARCELAS QUE VOCÊ SELECIONOU JÁ ESTÁ PAGA");
                    }
                    break;
                }
                else{
                    double valuePlot = Double.parseDouble(tableOfPlots.getModel().getValueAt(set[i],1).toString().replace(",", "."));
                    double valuePayed = Double.parseDouble(data[0]);
                    double value = valuePlot + valuePayed;
                    int currentInstallment= Integer.parseInt(data[1]) + 1;
                    updateProduct(roundNumber.doRound(value).replace(",", "."), currentInstallment, set.length - i);
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "SELECIONE AO MENOS UM REGISTRO ANTES");
                break;
            }
        }
    }//GEN-LAST:event_buttonPayActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        if(x == 0){
            x++;
            insertInTable();
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
            java.util.logging.Logger.getLogger(PayPlots.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PayPlots.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PayPlots.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PayPlots.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PayPlots().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton buttonPay;
    public static javax.swing.JLabel outputValuePayed;
    public static javax.swing.JLabel outputValueToPay;
    private javax.swing.JScrollPane tableFormPayment;
    private javax.swing.JTable tableOfPlots;
    private javax.swing.JLabel txtPayPlots;
    private javax.swing.JLabel txtPlots;
    private javax.swing.JLabel txtValuePayed;
    private javax.swing.JLabel txtValueToPay;
    // End of variables declaration//GEN-END:variables
}
