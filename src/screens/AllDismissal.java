package screens;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import connectionbd.ConnectionModule;
import formattingmask.MaskCPFAndCNPJ;
import formattingmask.MaskDate;
import formattingmask.MaskUpperLetter;
import functioncontroller.GetDateFormate;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 *
 * @author willi
 */
public class AllDismissal extends javax.swing.JFrame {
    int x = 0;
    int x2 = 0;
    int x3 = 0;
    Connection connection = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    GetDateFormate getDateFormate = new GetDateFormate();
    public AllDismissal() {
        initComponents();
        ConnectionModule connect = new ConnectionModule();
        connection = connect.getConnectionMySQL();
        inputNameEmployee.setDocument(new MaskUpperLetter());
        inputCPFEmployee.setDocument(new MaskCPFAndCNPJ());
        inputbeginDate.setDocument(new MaskDate());
        inputEndDate.setDocument(new MaskDate());
    }
    private void getDate(){
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        inputEndDate.setText(new SimpleDateFormat("dd/MM/yyyy").format(c.getTime()));
        c.set(Calendar.DAY_OF_MONTH, 1);
        inputbeginDate.setText(new SimpleDateFormat("dd/MM/yyyy").format(c.getTime()));
    }
    private void showEmployee(){
        if(!inputbeginDate.getText().equals("") && !inputEndDate.getText().equals("")&&!inputNameEmployee.getText().equals("NOME DO FUNCIONÁRIO")&&inputCPFEmployee.getText().equals("CPF DO FUNCIONÁRIO")){
            getEmployeesDateName();
        }
        else if(!inputbeginDate.getText().equals("") && !inputEndDate.getText().equals("")&&!inputCPFEmployee.getText().equals("CPF DO FUNCIONÁRIO")&&inputNameEmployee.getText().equals("NOME DO FUNCIONÁRIO")){
            getEmployeesDateCPF();
        }
        else if(!inputbeginDate.getText().equals("") && !inputEndDate.getText().equals("")&&!inputCPFEmployee.getText().equals("CPF DO FUNCIONÁRIO")&&!inputNameEmployee.getText().equals("NOME DO FUNCIONÁRIO")){
            getEmployeesDateNameAndCPF();
        }
        else if(!inputbeginDate.getText().equals("") && !inputEndDate.getText().equals("")){
            getEmployeesDate();
        }
        else if(!inputNameEmployee.getText().equals("NOME DO FUNCIONÁRIO")&&inputCPFEmployee.getText().equals("CPF DO FUNCIONÁRIO")){
            getEmployeesName();
        }
        else if(inputNameEmployee.getText().equals("NOME DO FUNCIONÁRIO")&&!inputCPFEmployee.getText().equals("CPF DO FUNCIONÁRIO")){
            getEmployeesCPF();
        }
        else if(!inputNameEmployee.getText().equals("NOME DO FUNCIONÁRIO")&&!inputCPFEmployee.getText().equals("CPF DO FUNCIONÁRIO")){
            getEmployeesCPFAndName();
        }
        else if(inputNameEmployee.getText().equals("NOME DO FUNCIONÁRIO")&&inputCPFEmployee.getText().equals("CPF DO FUNCIONÁRIO")){
            getAllEmployees();
        }
    }
    private void getAllEmployees(){
        String sql ="select typeSuport as 'Tipo', id as 'Código', nameEmployee as 'Nome', cpf as 'CPF', dismissalReason as 'Motivo da Demissão', dismissalDate as 'Data da Demissão' from dismissalEmployee";
        try {
            pst=connection.prepareStatement(sql);
            rs= pst.executeQuery();
            tableEmployees.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void getEmployeesCPF(){
        String sql ="select typeSuport as 'Tipo', id as 'Código', nameEmployee as 'Nome', cpf as 'CPF', dismissalReason as 'Motivo da Demissão', dismissalDate as 'Data da Demissão' from dismissalEmployee where cpf like '" + inputCPFEmployee.getText() + "%'";
        try {
            pst=connection.prepareStatement(sql);
            rs = pst.executeQuery();
            tableEmployees.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void getEmployeesName(){
        String sql ="select typeSuport as 'Tipo', id as 'Código', nameEmployee as 'Nome', cpf as 'CPF', dismissalReason as 'Motivo da Demissão', dismissalDate as 'Data da Demissão' from dismissalEmployee where nameEmployee like '" + inputNameEmployee.getText() + "%'";
        try {
            pst=connection.prepareStatement(sql);
            rs = pst.executeQuery();
            tableEmployees.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void getEmployeesCPFAndName(){
        String sql ="select typeSuport as 'Tipo', id as 'Código', nameEmployee as 'Nome', cpf as 'CPF', dismissalReason as 'Motivo da Demissão', dismissalDate as 'Data da Demissão' from dismissalEmployee where cpf like '" + inputCPFEmployee.getText() + "%' and nameEmployee like '" + inputNameEmployee.getText() + "%'";
        try {
            pst=connection.prepareStatement(sql);
            rs = pst.executeQuery();
            tableEmployees.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void getEmployeesDate(){
        String sql ="select typeSuport as 'Tipo', id as 'Código', nameEmployee as 'Nome', cpf as 'CPF', dismissalReason as 'Motivo da Demissão', dismissalDate as 'Data da Demissão' from dismissalEmployee where str_to_date(dismissalDate, '%d/%m/%Y') between '" + getDateFormate.getDate( inputbeginDate.getText() ) + "' and '" + getDateFormate.getDate( inputEndDate.getText() ) + "'";
        try {
            pst=connection.prepareStatement(sql);
            rs = pst.executeQuery();
            tableEmployees.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void getEmployeesDateName(){
        String sql ="select typeSuport as 'Tipo', id as 'Código', nameEmployee as 'Nome', cpf as 'CPF', dismissalReason as 'Motivo da Demissão', dismissalDate as 'Data da Demissão' from dismissalEmployee where str_to_date(dismissalDate, '%d/%m/%Y') between '" + getDateFormate.getDate( inputbeginDate.getText() ) + "' and '" + getDateFormate.getDate( inputEndDate.getText() ) + "' and nameEmployee like '" + inputNameEmployee.getText() + "%'";
        try {
            pst=connection.prepareStatement(sql);
            rs = pst.executeQuery();
            tableEmployees.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void getEmployeesDateCPF(){
        String sql ="select typeSuport as 'Tipo', id as 'Código', nameEmployee as 'Nome', cpf as 'CPF', dismissalReason as 'Motivo da Demissão', dismissalDate as 'Data da Demissão' from dismissalEmployee where str_to_date(dismissalDate, '%d/%m/%Y') between '" + getDateFormate.getDate( inputbeginDate.getText() ) + "' and '" + getDateFormate.getDate( inputEndDate.getText() ) + "' and cpf like '" + inputCPFEmployee.getText() + "%'";
        try {
            pst=connection.prepareStatement(sql);
            rs = pst.executeQuery();
            tableEmployees.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void getEmployeesDateNameAndCPF(){
        String sql ="select typeSuport as 'Tipo', id as 'Código', nameEmployee as 'Nome', cpf as 'CPF', dismissalReason as 'Motivo da Demissão', dismissalDate as 'Data da Demissão' from dismissalEmployee where str_to_date(dismissalDate, '%d/%m/%Y') between '" + getDateFormate.getDate( inputbeginDate.getText() ) + "' and '" + getDateFormate.getDate( inputEndDate.getText() ) + "' and nameEmployee like '" + inputNameEmployee.getText() + "%' and cpf like '" + inputCPFEmployee.getText() + "%'";
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

        txtDismissal = new javax.swing.JLabel();
        inputNameEmployee = new javax.swing.JTextField();
        tableLocaleEmplyees = new javax.swing.JScrollPane();
        tableEmployees = new javax.swing.JTable();
        buttonShowClient = new javax.swing.JButton();
        inputCPFEmployee = new javax.swing.JTextField();
        inputEndDate = new javax.swing.JTextField();
        txtA = new javax.swing.JLabel();
        inputbeginDate = new javax.swing.JTextField();
        buttonFilter = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Todas as Demissões");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(null);

        txtDismissal.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        txtDismissal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtDismissal.setText("TODAS AS DEMISSÕES");
        getContentPane().add(txtDismissal);
        txtDismissal.setBounds(200, 20, 360, 32);

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
                "Tipo", "Código", "Nome", "CPF", "Motivo da Demissão", "Data da Demissão"
            }
        ));
        tableLocaleEmplyees.setViewportView(tableEmployees);

        getContentPane().add(tableLocaleEmplyees);
        tableLocaleEmplyees.setBounds(10, 120, 760, 290);

        buttonShowClient.setText("MOSTRAR");
        buttonShowClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonShowClientActionPerformed(evt);
            }
        });
        getContentPane().add(buttonShowClient);
        buttonShowClient.setBounds(610, 70, 90, 30);

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
        inputCPFEmployee.setBounds(310, 70, 256, 30);

        inputEndDate.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        inputEndDate.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                inputEndDateFocusGained(evt);
            }
        });
        inputEndDate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputEndDateKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                inputEndDateKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                inputEndDateKeyTyped(evt);
            }
        });
        getContentPane().add(inputEndDate);
        inputEndDate.setBounds(140, 430, 90, 30);

        txtA.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtA.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtA.setText("A");
        getContentPane().add(txtA);
        txtA.setBounds(100, 430, 40, 30);

        inputbeginDate.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        inputbeginDate.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                inputbeginDateFocusGained(evt);
            }
        });
        inputbeginDate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputbeginDateKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                inputbeginDateKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                inputbeginDateKeyTyped(evt);
            }
        });
        getContentPane().add(inputbeginDate);
        inputbeginDate.setBounds(10, 430, 90, 30);

        buttonFilter.setText("FILTRAR");
        buttonFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonFilterActionPerformed(evt);
            }
        });
        getContentPane().add(buttonFilter);
        buttonFilter.setBounds(250, 430, 90, 30);

        setSize(new java.awt.Dimension(790, 510));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void inputNameEmployeeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputNameEmployeeFocusGained
        inputNameEmployee.selectAll();
    }//GEN-LAST:event_inputNameEmployeeFocusGained

    private void inputNameEmployeeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputNameEmployeeKeyPressed

    }//GEN-LAST:event_inputNameEmployeeKeyPressed

    private void inputNameEmployeeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputNameEmployeeKeyReleased
        showEmployee();
    }//GEN-LAST:event_inputNameEmployeeKeyReleased

    private void inputNameEmployeeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputNameEmployeeKeyTyped
        if(inputNameEmployee.getText().equals("")){
            inputNameEmployee.setText("NOME DO FUNCIONÁRIO");
            showEmployee();
            x=0;
        }
        else if(x==0){
            x++;
            inputNameEmployee.setText("");
        }
    }//GEN-LAST:event_inputNameEmployeeKeyTyped

    private void buttonShowClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonShowClientActionPerformed
        int set = tableEmployees.getSelectedRow();
        if(set>=0){
            EmployeeDismissal employeeDismissal = new EmployeeDismissal();
            employeeDismissal.dismissalId = Integer.parseInt( tableEmployees.getModel().getValueAt(set,1).toString() );
            employeeDismissal.getEmploye();
            employeeDismissal.setVisible(true);
        }
        else{
            JOptionPane.showMessageDialog(null, "SELECIONE UM REGISTRO ANTES");
        }
    }//GEN-LAST:event_buttonShowClientActionPerformed

    private void inputCPFEmployeeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputCPFEmployeeFocusGained
        inputCPFEmployee.selectAll();
    }//GEN-LAST:event_inputCPFEmployeeFocusGained

    private void inputCPFEmployeeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputCPFEmployeeKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputCPFEmployeeKeyPressed

    private void inputCPFEmployeeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputCPFEmployeeKeyReleased
        showEmployee();
    }//GEN-LAST:event_inputCPFEmployeeKeyReleased

    private void inputCPFEmployeeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputCPFEmployeeKeyTyped
        if(inputCPFEmployee.getText().equals("")){
            inputCPFEmployee.setText("CPF DO FUNCIONÁRIO");
            showEmployee();
            x2=0;
        }
        else if(x2==0){
            x2++;
            inputCPFEmployee.setText("");
        }
    }//GEN-LAST:event_inputCPFEmployeeKeyTyped

    private void inputEndDateFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputEndDateFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_inputEndDateFocusGained

    private void inputEndDateKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputEndDateKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputEndDateKeyPressed

    private void inputEndDateKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputEndDateKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_inputEndDateKeyReleased

    private void inputEndDateKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputEndDateKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_inputEndDateKeyTyped

    private void inputbeginDateFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputbeginDateFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_inputbeginDateFocusGained

    private void inputbeginDateKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputbeginDateKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputbeginDateKeyPressed

    private void inputbeginDateKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputbeginDateKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_inputbeginDateKeyReleased

    private void inputbeginDateKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputbeginDateKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_inputbeginDateKeyTyped

    private void buttonFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonFilterActionPerformed
        showEmployee();
    }//GEN-LAST:event_buttonFilterActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        if(x3==0){
            x3++;
            inputNameEmployee.setText("NOME DO FUNCIONÁRIO");
            inputCPFEmployee.setText("CPF DO FUNCIONÁRIO");
            getDate();
            showEmployee();
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
            java.util.logging.Logger.getLogger(AllDismissal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AllDismissal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AllDismissal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AllDismissal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AllDismissal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonFilter;
    private javax.swing.JButton buttonShowClient;
    private javax.swing.JTextField inputCPFEmployee;
    private javax.swing.JTextField inputEndDate;
    private javax.swing.JTextField inputNameEmployee;
    private javax.swing.JTextField inputbeginDate;
    private javax.swing.JTable tableEmployees;
    private javax.swing.JScrollPane tableLocaleEmplyees;
    private javax.swing.JLabel txtA;
    private javax.swing.JLabel txtDismissal;
    // End of variables declaration//GEN-END:variables
}
