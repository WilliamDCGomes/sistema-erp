package screens;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import connectionbd.ConnectionModule;
import formattingmask.MaskCPFAndCNPJ;
import formattingmask.MaskDate;
import formattingmask.MaskUpperLetter;
import functioncontroller.GetDateFormate;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author willi
 */
public class AllNewContracts extends javax.swing.JFrame {
    int x = 0;
    int x2 = 0;
    int x3 = 0;
    Connection connection = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    PreparedStatement pst2 = null;
    ResultSet rs2 = null;
    ArrayList<String> contracts = new ArrayList<>();
    ArrayList<String> readmitted = new ArrayList<>();
    GetDateFormate getDateFormate = new GetDateFormate();
    /**
     * Creates new form AllNewContracts
     */
    public AllNewContracts(){
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
    private void filter(){
        clearTable();
        contracts.clear();
        readmitted.clear();
    }
    private void showEmployee(){
        filter();
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
        insertInTable();
    }
    private void clearTable(){
        DefaultTableModel table = (DefaultTableModel) tableEmployees.getModel();
        for(int i=table.getRowCount()-1; i >= 0; i--){
            table.removeRow(i);
        }
    }
    private void insertInTable(){
        DefaultTableModel table = (DefaultTableModel) tableEmployees.getModel();
        int aux = 0;
        int aux2 = 0;
        while(true){
            if(aux<contracts.size()){
                String[] data = contracts.get(aux).split(";");
                table.addRow(data);
                aux++;
            }
            else if(aux2<readmitted.size()){
                String[] data = readmitted.get(aux2).split(";");
                table.addRow(data);
                aux2++;
            }
            else{
                break;
            }
        }
    }
    private void getAllEmployees(){
        String sql ="select typeSuport as 'Tipo', id as 'Código', nameEmployee as 'Nome', cpf as 'CPF', functionEmployee as 'Função', salary as 'Remuneração', admissionDate as 'Data da Contratação' from employee";
        try {
            pst=connection.prepareStatement(sql);
            rs= pst.executeQuery();
            while (rs.next()) {
                contracts.add(rs.getString(1) + ";" + Integer.toString(rs.getInt(2)) + ";" + rs.getString(3) + ";" + rs.getString(4) + ";" + rs.getString(5) + ";" + rs.getString(6) + ";" + rs.getString(7));
            }
            getAllEmployeesReadmited();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void getEmployeesCPF(){
        String sql ="select typeSuport as 'Tipo', id as 'Código', nameEmployee as 'Nome', cpf as 'CPF', functionEmployee as 'Função', salary as 'Remuneração', admissionDate as 'Data da Contratação' from employee where cpf like '" + inputCPFEmployee.getText() + "%'";
        try {
            pst=connection.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                contracts.add(rs.getString(1) + ";" + Integer.toString(rs.getInt(2)) + ";" + rs.getString(3) + ";" + rs.getString(4) + ";" + rs.getString(5) + ";" + rs.getString(6) + ";" + rs.getString(7));
            }
            getEmployeesCPFReadmited();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void getEmployeesName(){
        String sql ="select typeSuport as 'Tipo', id as 'Código', nameEmployee as 'Nome', cpf as 'CPF', functionEmployee as 'Função', salary as 'Remuneração', admissionDate as 'Data da Contratação' from employee where nameEmployee like '" + inputNameEmployee.getText() + "%'";
        try {
            pst=connection.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                contracts.add(rs.getString(1) + ";" + Integer.toString(rs.getInt(2)) + ";" + rs.getString(3) + ";" + rs.getString(4) + ";" + rs.getString(5) + ";" + rs.getString(6) + ";" + rs.getString(7));
            }
            getEmployeesNameReadmited();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void getEmployeesCPFAndName(){
        String sql ="select typeSuport as 'Tipo', id as 'Código', nameEmployee as 'Nome', cpf as 'CPF', functionEmployee as 'Função', salary as 'Remuneração', admissionDate as 'Data da Contratação' from employee where cpf like '" + inputCPFEmployee.getText() + "%' and nameEmployee like '" + inputNameEmployee.getText() + "%'";
        try {
            pst=connection.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                contracts.add(rs.getString(1) + ";" + Integer.toString(rs.getInt(2)) + ";" + rs.getString(3) + ";" + rs.getString(4) + ";" + rs.getString(5) + ";" + rs.getString(6) + ";" + rs.getString(7));
            }
            getEmployeesCPFAndNameReadmited();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void getEmployeesDate(){
        String sql ="select typeSuport as 'Tipo', id as 'Código', nameEmployee as 'Nome', cpf as 'CPF', functionEmployee as 'Função', salary as 'Remuneração', admissionDate as 'Data da Contratação' from employee where str_to_date(admissionDate, '%d/%m/%Y') between '" + getDateFormate.getDate( inputbeginDate.getText() ) + "' and '" + getDateFormate.getDate( inputEndDate.getText() ) + "'";
        try {
            pst=connection.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                contracts.add(rs.getString(1) + ";" + Integer.toString(rs.getInt(2)) + ";" + rs.getString(3) + ";" + rs.getString(4) + ";" + rs.getString(5) + ";" + rs.getString(6) + ";" + rs.getString(7));
            }
            getEmployeesDateReadmitted();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void getEmployeesDateName(){
        String sql ="select typeSuport as 'Tipo', id as 'Código', nameEmployee as 'Nome', cpf as 'CPF', functionEmployee as 'Função', salary as 'Remuneração', admissionDate as 'Data da Contratação' from employee where str_to_date(admissionDate, '%d/%m/%Y') between '" + getDateFormate.getDate( inputbeginDate.getText() ) + "' and '" + getDateFormate.getDate( inputEndDate.getText() ) + "' and nameEmployee like '" + inputNameEmployee.getText() + "%'";
        try {
            pst=connection.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                contracts.add(rs.getString(1) + ";" + Integer.toString(rs.getInt(2)) + ";" + rs.getString(3) + ";" + rs.getString(4) + ";" + rs.getString(5) + ";" + rs.getString(6) + ";" + rs.getString(7));
            }
            getEmployeesDateNameReadmitted();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void getEmployeesDateCPF(){
        String sql ="select typeSuport as 'Tipo', id as 'Código', nameEmployee as 'Nome', cpf as 'CPF', functionEmployee as 'Função', salary as 'Remuneração', admissionDate as 'Data da Contratação' from employee where str_to_date(admissionDate, '%d/%m/%Y') between '" + getDateFormate.getDate( inputbeginDate.getText() ) + "' and '" + getDateFormate.getDate( inputEndDate.getText() ) + "' and cpf like '" + inputCPFEmployee.getText() + "%'";
        try {
            pst=connection.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                contracts.add(rs.getString(1) + ";" + Integer.toString(rs.getInt(2)) + ";" + rs.getString(3) + ";" + rs.getString(4) + ";" + rs.getString(5) + ";" + rs.getString(6) + ";" + rs.getString(7));
            }
            getEmployeesDateCPFReadmitted();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void getEmployeesDateNameAndCPF(){
        String sql ="select typeSuport as 'Tipo', id as 'Código', nameEmployee as 'Nome', cpf as 'CPF', functionEmployee as 'Função', salary as 'Remuneração', admissionDate as 'Data da Contratação' from employee where str_to_date(admissionDate, '%d/%m/%Y') between '" + getDateFormate.getDate( inputbeginDate.getText() ) + "' and '" + getDateFormate.getDate( inputEndDate.getText() ) + "' and nameEmployee like '" + inputNameEmployee.getText() + "%' and cpf like '" + inputCPFEmployee.getText() + "%'";
        try {
            pst=connection.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                contracts.add(rs.getString(1) + ";" + Integer.toString(rs.getInt(2)) + ";" + rs.getString(3) + ";" + rs.getString(4) + ";" + rs.getString(5) + ";" + rs.getString(6) + ";" + rs.getString(7));
            }
            getEmployeesDateNameAndCPFReadmitted();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void getAllEmployeesReadmited(){
        String sql ="select typeSuport as 'Tipo', codEmployee as 'Código', nameEmployee as 'Nome', cpf as 'CPF', functionEmployee as 'Função', salary as 'Remuneração', readmissionDate as 'Data da Contratação' from readmissionEmployee";
        try {
            pst2=connection.prepareStatement(sql);
            rs2= pst2.executeQuery();
            while (rs2.next()) {
                readmitted.add(rs2.getString(1) + ";" + Integer.toString(rs2.getInt(2)) + ";" + rs2.getString(3) + ";" + rs2.getString(4) + ";" + rs2.getString(5) + ";" + rs2.getString(6) + ";" + rs2.getString(7));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void getEmployeesCPFReadmited(){
        String sql ="select typeSuport as 'Tipo', codEmployee as 'Código', nameEmployee as 'Nome', cpf as 'CPF', functionEmployee as 'Função', salary as 'Remuneração', readmissionDate as 'Data da Contratação' from readmissionEmployee where cpf like '" + inputCPFEmployee.getText() + "%'";
        try {
            pst2=connection.prepareStatement(sql);
            rs2 = pst2.executeQuery();
            while (rs2.next()) {
                readmitted.add(rs2.getString(1) + ";" + Integer.toString(rs2.getInt(2)) + ";" + rs2.getString(3) + ";" + rs2.getString(4) + ";" + rs2.getString(5) + ";" + rs2.getString(6) + ";" + rs2.getString(7));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void getEmployeesNameReadmited(){
        String sql ="select typeSuport as 'Tipo', codEmployee as 'Código', nameEmployee as 'Nome', cpf as 'CPF', functionEmployee as 'Função', salary as 'Remuneração', readmissionDate as 'Data da Contratação' from readmissionEmployee where nameEmployee like '" + inputNameEmployee.getText() + "%'";
        try {
            pst2=connection.prepareStatement(sql);
            rs2 = pst2.executeQuery();
            while (rs2.next()) {
                readmitted.add(rs2.getString(1) + ";" + Integer.toString(rs2.getInt(2)) + ";" + rs2.getString(3) + ";" + rs2.getString(4) + ";" + rs2.getString(5) + ";" + rs2.getString(6) + ";" + rs2.getString(7));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void getEmployeesCPFAndNameReadmited(){
        String sql ="select typeSuport as 'Tipo', codEmployee as 'Código', nameEmployee as 'Nome', cpf as 'CPF', functionEmployee as 'Função', salary as 'Remuneração', readmissionDate as 'Data da Contratação' from readmissionEmployee where cpf like '" + inputCPFEmployee.getText() + "%' and nameEmployee like '" + inputNameEmployee.getText() + "%'";
        try {
            pst2=connection.prepareStatement(sql);
            rs2 = pst2.executeQuery();
            while (rs2.next()) {
                readmitted.add(rs2.getString(1) + ";" + Integer.toString(rs2.getInt(2)) + ";" + rs2.getString(3) + ";" + rs2.getString(4) + ";" + rs2.getString(5) + ";" + rs2.getString(6) + ";" + rs2.getString(7));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void getEmployeesDateReadmitted(){
        String sql ="select typeSuport as 'Tipo', id as 'Código', nameEmployee as 'Nome', cpf as 'CPF', functionEmployee as 'Função', salary as 'Remuneração', readmissionDate as 'Data da Contratação' from readmissionEmployee where str_to_date(readmissionDate, '%d/%m/%Y') between '" + getDateFormate.getDate( inputbeginDate.getText() ) + "' and '" + getDateFormate.getDate( inputEndDate.getText() ) + "'";
        try {
            pst=connection.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                readmitted.add(rs.getString(1) + ";" + Integer.toString(rs.getInt(2)) + ";" + rs.getString(3) + ";" + rs.getString(4) + ";" + rs.getString(5) + ";" + rs.getString(6) + ";" + rs.getString(7));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void getEmployeesDateNameReadmitted(){
        String sql ="select typeSuport as 'Tipo', id as 'Código', nameEmployee as 'Nome', cpf as 'CPF', functionEmployee as 'Função', salary as 'Remuneração', readmissionDate as 'Data da Contratação' from readmissionEmployee where str_to_date(readmissionDate, '%d/%m/%Y') between '" + getDateFormate.getDate( inputbeginDate.getText() ) + "' and '" + getDateFormate.getDate( inputEndDate.getText() ) + "' and nameEmployee like '" + inputNameEmployee.getText() + "%'";
        try {
            pst=connection.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                readmitted.add(rs.getString(1) + ";" + Integer.toString(rs.getInt(2)) + ";" + rs.getString(3) + ";" + rs.getString(4) + ";" + rs.getString(5) + ";" + rs.getString(6) + ";" + rs.getString(7));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void getEmployeesDateCPFReadmitted(){
        String sql ="select typeSuport as 'Tipo', id as 'Código', nameEmployee as 'Nome', cpf as 'CPF', functionEmployee as 'Função', salary as 'Remuneração', readmissionDate as 'Data da Contratação' from readmissionEmployee where str_to_date(readmissionDate, '%d/%m/%Y') between '" + getDateFormate.getDate( inputbeginDate.getText() ) + "' and '" + getDateFormate.getDate( inputEndDate.getText() ) + "' and cpf like '" + inputCPFEmployee.getText() + "%'";
        try {
            pst=connection.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                readmitted.add(rs.getString(1) + ";" + Integer.toString(rs.getInt(2)) + ";" + rs.getString(3) + ";" + rs.getString(4) + ";" + rs.getString(5) + ";" + rs.getString(6) + ";" + rs.getString(7));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void getEmployeesDateNameAndCPFReadmitted(){
        String sql ="select typeSuport as 'Tipo', id as 'Código', nameEmployee as 'Nome', cpf as 'CPF', functionEmployee as 'Função', salary as 'Remuneração', readmissionDate as 'Data da Contratação' from readmissionEmployee where str_to_date(readmissionDate, '%d/%m/%Y') between '" + getDateFormate.getDate( inputbeginDate.getText() ) + "' and '" + getDateFormate.getDate( inputEndDate.getText() ) + "' and nameEmployee like '" + inputNameEmployee.getText() + "%' and cpf like '" + inputCPFEmployee.getText() + "%'";
        try {
            pst=connection.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                readmitted.add(rs.getString(1) + ";" + Integer.toString(rs.getInt(2)) + ";" + rs.getString(3) + ";" + rs.getString(4) + ";" + rs.getString(5) + ";" + rs.getString(6) + ";" + rs.getString(7));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtLocaleEmployee = new javax.swing.JLabel();
        inputNameEmployee = new javax.swing.JTextField();
        tableLocaleEmplyees = new javax.swing.JScrollPane();
        tableEmployees = new javax.swing.JTable();
        buttonShowClient = new javax.swing.JButton();
        inputCPFEmployee = new javax.swing.JTextField();
        inputEndDate = new javax.swing.JTextField();
        txtA = new javax.swing.JLabel();
        inputbeginDate = new javax.swing.JTextField();
        buttonFilter = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Todas as Contratações");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(null);

        txtLocaleEmployee.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        txtLocaleEmployee.setForeground(new java.awt.Color(255, 255, 255));
        txtLocaleEmployee.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtLocaleEmployee.setText("TODAS AS CONTRATAÇÕES");
        getContentPane().add(txtLocaleEmployee);
        txtLocaleEmployee.setBounds(200, 20, 360, 32);

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
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Tipo", "Código", "Nome", "CPF", "Função", "Remuneração", "Data da Contratação"
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
        txtA.setForeground(new java.awt.Color(255, 255, 255));
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

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/plano e fundo.png"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 800, 490);

        setSize(new java.awt.Dimension(792, 510));
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
            if(tableEmployees.getModel().getValueAt(set,0).toString().equals("ADMISSÃO")){
                CheckNewContract checkNewContract = new CheckNewContract();
                checkNewContract.setAdmissal(tableEmployees.getModel().getValueAt(set,3).toString());
                checkNewContract.setVisible(true);
            }
            else if(tableEmployees.getModel().getValueAt(set,0).toString().equals("READMISSÃO")){
                CheckNewContract checkNewContract = new CheckNewContract();
                checkNewContract.setReadmissal(tableEmployees.getModel().getValueAt(set,3).toString(), tableEmployees.getModel().getValueAt(set,6).toString());
                checkNewContract.setVisible(true);
            }
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

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        if(x3==0){
            x3++;
            inputNameEmployee.setText("NOME DO FUNCIONÁRIO");
            inputCPFEmployee.setText("CPF DO FUNCIONÁRIO");
            getDate();
            showEmployee();
        }
    }//GEN-LAST:event_formWindowActivated

    private void buttonFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonFilterActionPerformed
        showEmployee();
    }//GEN-LAST:event_buttonFilterActionPerformed

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
            java.util.logging.Logger.getLogger(AllNewContracts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AllNewContracts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AllNewContracts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AllNewContracts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AllNewContracts().setVisible(true);
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTable tableEmployees;
    private javax.swing.JScrollPane tableLocaleEmplyees;
    private javax.swing.JLabel txtA;
    private javax.swing.JLabel txtLocaleEmployee;
    // End of variables declaration//GEN-END:variables
}
