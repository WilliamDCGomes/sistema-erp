package screens;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import connectionbd.ConnectionModule;
import formattingmask.MaskCPFAndCNPJ;
import formattingmask.MaskUpperLetter;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

public class AllProviders extends javax.swing.JFrame {
    int x = 0;
    int x2 = 0;
    int x3 = 0;
    Connection connection = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    public AllProviders() {
        initComponents();
        ConnectionModule connect = new ConnectionModule();
        connection = connect.getConnectionMySQL();
        inputCompanyName.setDocument(new MaskUpperLetter());
        inputCPFOrCNPJ.setDocument(new MaskCPFAndCNPJ());
    }
    private void getAllProviders(){
        String sql ="select codeProvider as 'Código', fantasyName as 'Nome Fantasia', cpf as 'CPF / CNPJ', email as 'Email', phone as 'Telefone', cellPhone as 'Celular' from provider";
        try {
            pst=connection.prepareStatement(sql);
            rs= pst.executeQuery();
            tableProviders.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void getProvidersCPF(){
        String sql ="select codeProvider as 'Código', fantasyName as 'Nome Fantasia', cpf as 'CPF / CNPJ', email as 'Email', phone as 'Telefone', cellPhone as 'Celular' from provider where cpf like '" + inputCPFOrCNPJ.getText() + "%'";
        try {
            pst=connection.prepareStatement(sql);
            rs = pst.executeQuery();
            tableProviders.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void getProvidersName(){
        String sql ="select codeProvider as 'Código', fantasyName as 'Nome Fantasia', cpf as 'CPF / CNPJ', email as 'Email', phone as 'Telefone', cellPhone as 'Celular' from provider where fantasyName like '" + inputCompanyName.getText() + "%'";
        try {
            pst=connection.prepareStatement(sql);
            rs = pst.executeQuery();
            tableProviders.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void getProvidersCPFAndName(){
        String sql ="select codeProvider as 'Código', fantasyName as 'Nome Fantasia', cpf as 'CPF / CNPJ', email as 'Email', phone as 'Telefone', cellPhone as 'Celular' from provider where cpf like '" + inputCPFOrCNPJ.getText() + "%' and fantasyName like '" + inputCompanyName.getText() + "%'";
        try {
            pst=connection.prepareStatement(sql);
            rs = pst.executeQuery();
            tableProviders.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtAllProviders = new javax.swing.JLabel();
        tableAllProviders = new javax.swing.JScrollPane();
        tableProviders = new javax.swing.JTable();
        inputCPFOrCNPJ = new javax.swing.JTextField();
        buttonShow = new javax.swing.JButton();
        inputCompanyName = new javax.swing.JTextField();
        buttonPrinter = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Todos os Fornecedores");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(null);

        txtAllProviders.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        txtAllProviders.setText("TODOS OS FORNECEDORES");
        getContentPane().add(txtAllProviders);
        txtAllProviders.setBounds(210, 10, 340, 32);

        tableProviders.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Cód Pedido", "CPF / CNPJ", "Nome Fantasia", "Telefone", "Endereço", "Número"
            }
        ));
        tableAllProviders.setViewportView(tableProviders);

        getContentPane().add(tableAllProviders);
        tableAllProviders.setBounds(20, 130, 700, 300);

        inputCPFOrCNPJ.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        inputCPFOrCNPJ.setText("CPF / CNPJ");
        inputCPFOrCNPJ.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                inputCPFOrCNPJFocusGained(evt);
            }
        });
        inputCPFOrCNPJ.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                inputCPFOrCNPJKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                inputCPFOrCNPJKeyTyped(evt);
            }
        });
        getContentPane().add(inputCPFOrCNPJ);
        inputCPFOrCNPJ.setBounds(20, 80, 190, 30);

        buttonShow.setText("MOSTRAR");
        buttonShow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonShowActionPerformed(evt);
            }
        });
        getContentPane().add(buttonShow);
        buttonShow.setBounds(520, 80, 90, 30);

        inputCompanyName.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        inputCompanyName.setText("NOME FANTASIA");
        inputCompanyName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                inputCompanyNameFocusGained(evt);
            }
        });
        inputCompanyName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputCompanyNameKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                inputCompanyNameKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                inputCompanyNameKeyTyped(evt);
            }
        });
        getContentPane().add(inputCompanyName);
        inputCompanyName.setBounds(240, 80, 256, 30);

        buttonPrinter.setText("IMPRIMIR");
        getContentPane().add(buttonPrinter);
        buttonPrinter.setBounds(630, 80, 90, 30);

        setSize(new java.awt.Dimension(744, 472));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void inputCPFOrCNPJFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputCPFOrCNPJFocusGained
        inputCPFOrCNPJ.selectAll();
    }//GEN-LAST:event_inputCPFOrCNPJFocusGained

    private void inputCPFOrCNPJKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputCPFOrCNPJKeyTyped
        if(inputCPFOrCNPJ.getText().equals("")){
            inputCPFOrCNPJ.setText("CPF / CNPJ");
            getAllProviders();
            x=0;
        }
        else if(x==0){
            x++;
            inputCPFOrCNPJ.setText("");
        }
    }//GEN-LAST:event_inputCPFOrCNPJKeyTyped

    private void buttonShowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonShowActionPerformed
        int set = tableProviders.getSelectedRow();
        if(set>=0){
            ProviderScreen providerScreen = new ProviderScreen();
            providerScreen.setTitle("Fornecedor: " + tableProviders.getModel().getValueAt(set,0).toString());
            providerScreen.setVisible(true);
        }
        else{
            JOptionPane.showMessageDialog(null, "SELECIONE UM REGISTRO ANTES");
        }
    }//GEN-LAST:event_buttonShowActionPerformed

    private void inputCompanyNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputCompanyNameFocusGained
        inputCompanyName.selectAll();
    }//GEN-LAST:event_inputCompanyNameFocusGained

    private void inputCompanyNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputCompanyNameKeyPressed

    }//GEN-LAST:event_inputCompanyNameKeyPressed

    private void inputCompanyNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputCompanyNameKeyReleased
        if(inputCPFOrCNPJ.getText().equals("CPF / CNPJ")&&!inputCompanyName.getText().equals("NOME FANTASIA")){
            getProvidersName();
        }
        else if(!inputCPFOrCNPJ.getText().equals("CPF / CNPJ")&&!inputCompanyName.getText().equals("NOME FANTASIA")){
            getProvidersCPFAndName();
        }
    }//GEN-LAST:event_inputCompanyNameKeyReleased

    private void inputCompanyNameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputCompanyNameKeyTyped
        if(inputCompanyName.getText().equals("")){
            inputCompanyName.setText("NOME FANTASIA");
            getAllProviders();
            x2=0;
        }
        else if(x2==0){
            x2++;
            inputCompanyName.setText("");
        }
    }//GEN-LAST:event_inputCompanyNameKeyTyped

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        if(x3==0){
            x3++;
            inputCPFOrCNPJ.setText("CPF / CNPJ");
            inputCompanyName.setText("NOME FANTASIA");
            getAllProviders();
        }
    }//GEN-LAST:event_formWindowActivated

    private void inputCPFOrCNPJKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputCPFOrCNPJKeyReleased
        if(!inputCPFOrCNPJ.getText().equals("CPF / CNPJ")&&inputCompanyName.getText().equals("NOME FANTASIA")){
            getProvidersCPF();
        }
        else if(!inputCPFOrCNPJ.getText().equals("CPF / CNPJ")&&!inputCompanyName.getText().equals("NOME FANTASIA")){
            getProvidersCPFAndName();
        }
    }//GEN-LAST:event_inputCPFOrCNPJKeyReleased

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
            java.util.logging.Logger.getLogger(AllProviders.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AllProviders.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AllProviders.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AllProviders.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AllProviders().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonPrinter;
    private javax.swing.JButton buttonShow;
    private javax.swing.JTextField inputCPFOrCNPJ;
    private javax.swing.JTextField inputCompanyName;
    private javax.swing.JScrollPane tableAllProviders;
    private javax.swing.JTable tableProviders;
    private javax.swing.JLabel txtAllProviders;
    // End of variables declaration//GEN-END:variables
}
