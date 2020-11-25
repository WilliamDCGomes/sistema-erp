package screens;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import connectionbd.ConnectionModule;
import formattingmask.MaskCPFAndCNPJ;
import functioncontroller.UpperLetter;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
public class LocaleEmployee extends javax.swing.JFrame {
    int x = 0;
    int x2 = 0;
    int x3 = 0;
    Connection connection = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    /**
     * Creates new form LocaleScreen
     */
    public LocaleEmployee() {
        initComponents();
        ConnectionModule connect = new ConnectionModule();
        connection = connect.getConnectionMySQL();
        inputNameEmployee.setDocument(new UpperLetter());
        inputCPFEmployee.setDocument(new MaskCPFAndCNPJ());
    }
    private void getAllEmployees(){
        String sql ="select id as 'Código', nameEmployee as 'Nome', cpf as 'CPF', email as 'Email', phone as 'Telefone', cellPhone as 'Celular' from employee";
        try {
            pst=connection.prepareStatement(sql);
            rs= pst.executeQuery();
            tableEmployees.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void getEmployeesCPF(){
        String sql ="select id as 'Código', nameEmployee as 'Nome', cpf as 'CPF', email as 'Email', phone as 'Telefone', cellPhone as 'Celular' from employee where cpf like '" + inputCPFEmployee.getText() + "%'";
        try {
            pst=connection.prepareStatement(sql);
            rs = pst.executeQuery();
            tableEmployees.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void getEmployeesName(){
        String sql ="select id as 'Código', nameEmployee as 'Nome', cpf as 'CPF', email as 'Email', phone as 'Telefone', cellPhone as 'Celular' from employee where nameEmployee like '" + inputNameEmployee.getText() + "%'";
        try {
            pst=connection.prepareStatement(sql);
            rs = pst.executeQuery();
            tableEmployees.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void getEmployeesCPFAndName(){
        String sql ="select id as 'Código', nameEmployee as 'Nome', cpf as 'CPF', email as 'Email', phone as 'Telefone', cellPhone as 'Celular' from employee where cpf like '" + inputCPFEmployee.getText() + "%' and nameEmployee like '" + inputNameEmployee.getText() + "%'";
        try {
            pst=connection.prepareStatement(sql);
            rs = pst.executeQuery();
            tableEmployees.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtLocaleEmployee = new javax.swing.JLabel();
        inputNameEmployee = new javax.swing.JTextField();
        tableLocaleEmplyees = new javax.swing.JScrollPane();
        tableEmployees = new javax.swing.JTable();
        buttonShowClient = new javax.swing.JButton();
        inputCPFEmployee = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Localizar Funcionário");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(null);

        txtLocaleEmployee.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        txtLocaleEmployee.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtLocaleEmployee.setText("LOCALIZAR FUNCIONÁRIO");
        getContentPane().add(txtLocaleEmployee);
        txtLocaleEmployee.setBounds(180, 20, 340, 32);

        inputNameEmployee.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        inputNameEmployee.setText("NOME DO FUNCIONÁRIO");
        inputNameEmployee.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                inputNameEmployeeFocusGained(evt);
            }
        });
        inputNameEmployee.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputNameEmployeeKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                inputNameEmployeeKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                inputNameEmployeeKeyTyped(evt);
            }
        });
        getContentPane().add(inputNameEmployee);
        inputNameEmployee.setBounds(10, 70, 256, 30);

        tableEmployees.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Código", "Nome", "CPF", "Email", "Telefone", "Celular"
            }
        ));
        tableLocaleEmplyees.setViewportView(tableEmployees);

        getContentPane().add(tableLocaleEmplyees);
        tableLocaleEmplyees.setBounds(10, 120, 690, 290);

        buttonShowClient.setText("MOSTRAR");
        buttonShowClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonShowClientActionPerformed(evt);
            }
        });
        getContentPane().add(buttonShowClient);
        buttonShowClient.setBounds(570, 70, 90, 30);

        inputCPFEmployee.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        inputCPFEmployee.setText("CPF DO FUNCIONÁRIO");
        inputCPFEmployee.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                inputCPFEmployeeFocusGained(evt);
            }
        });
        inputCPFEmployee.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputCPFEmployeeKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                inputCPFEmployeeKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                inputCPFEmployeeKeyTyped(evt);
            }
        });
        getContentPane().add(inputCPFEmployee);
        inputCPFEmployee.setBounds(290, 70, 256, 30);

        setSize(new java.awt.Dimension(724, 454));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void inputNameEmployeeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputNameEmployeeFocusGained
        inputNameEmployee.selectAll();
    }//GEN-LAST:event_inputNameEmployeeFocusGained

    private void inputNameEmployeeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputNameEmployeeKeyPressed

    }//GEN-LAST:event_inputNameEmployeeKeyPressed

    private void inputNameEmployeeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputNameEmployeeKeyReleased
        if(!inputNameEmployee.getText().equals("NOME DO FUNCIONÁRIO")&&inputCPFEmployee.getText().equals("CPF DO FUNCIONÁRIO")){
            getEmployeesName();
        }
        else if(!inputCPFEmployee.getText().equals("CPF DO FUNCIONÁRIO")&&!inputNameEmployee.getText().equals("NOME DO FUNCIONÁRIO")){
            getEmployeesCPFAndName();
        }
    }//GEN-LAST:event_inputNameEmployeeKeyReleased

    private void inputNameEmployeeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputNameEmployeeKeyTyped
        if(inputNameEmployee.getText().equals("")){
            inputNameEmployee.setText("NOME DO FUNCIONÁRIO");
            getAllEmployees();
            x=0;
        }
        else if(x==0){
            x++;
            inputNameEmployee.setText("");
        }
    }//GEN-LAST:event_inputNameEmployeeKeyTyped

    private void buttonShowClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonShowClientActionPerformed
        int set = tableEmployees.getSelectedRow();
        EmployeeScreen employeeScreen = new EmployeeScreen();
        employeeScreen.setTitle("Funcionário: " + tableEmployees.getModel().getValueAt(set,0).toString());
        employeeScreen.setVisible(true);
    }//GEN-LAST:event_buttonShowClientActionPerformed

    private void inputCPFEmployeeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputCPFEmployeeFocusGained
        inputCPFEmployee.selectAll();
    }//GEN-LAST:event_inputCPFEmployeeFocusGained

    private void inputCPFEmployeeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputCPFEmployeeKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputCPFEmployeeKeyPressed

    private void inputCPFEmployeeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputCPFEmployeeKeyReleased
        if(!inputCPFEmployee.getText().equals("CPF DO FUNCIONÁRIO")&&inputNameEmployee.getText().equals("NOME DO FUNCIONÁRIO")){
            getEmployeesCPF();
        }
        else if(!inputCPFEmployee.getText().equals("CPF DO FUNCIONÁRIO")&&!inputNameEmployee.getText().equals("NOME DO FUNCIONÁRIO")){
            getEmployeesCPFAndName();
        }
    }//GEN-LAST:event_inputCPFEmployeeKeyReleased

    private void inputCPFEmployeeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputCPFEmployeeKeyTyped
        if(inputCPFEmployee.getText().equals("")){
            inputCPFEmployee.setText("CPF DO FUNCIONÁRIO");
            getAllEmployees();
            x2=0;
        }
        else if(x2==0){
            x2++;
            inputCPFEmployee.setText("");
        }
    }//GEN-LAST:event_inputCPFEmployeeKeyTyped

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        if(x3==0){
            x3++;
            inputNameEmployee.setText("NOME DO FUNCIONÁRIO");
            inputCPFEmployee.setText("CPF DO FUNCIONÁRIO");
            getAllEmployees();
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
            java.util.logging.Logger.getLogger(LocaleEmployee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LocaleEmployee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LocaleEmployee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LocaleEmployee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LocaleEmployee().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonShowClient;
    private javax.swing.JTextField inputCPFEmployee;
    private javax.swing.JTextField inputNameEmployee;
    private javax.swing.JTable tableEmployees;
    private javax.swing.JScrollPane tableLocaleEmplyees;
    private javax.swing.JLabel txtLocaleEmployee;
    // End of variables declaration//GEN-END:variables
}
