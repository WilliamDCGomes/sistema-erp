package screens;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import connectionbd.ConnectionModule;
import formattingmask.MaskCPFAndCNPJ;
import formattingmask.MaskDate;
import formattingmask.MaskJustNumbers;
import formattingmask.MaskUpperLetter;
import functioncontroller.GetDate;
import functioncontroller.GetJustTheNumbers;
import functioncontroller.ValidateCNPJ;
import functioncontroller.ValidateCPF;
import javax.swing.JOptionPane;

public class EmployeeDismissal extends javax.swing.JFrame {
    int x = 0;
    int x2 = 0;
    int dismissalId = 0;
    Connection connection = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    PreparedStatement pst2 = null;
    ResultSet rs2 = null;
    PreparedStatement pst3 = null;
    ResultSet rs3 = null;
    GetDate getDate = new GetDate();
    boolean cpfValide = false;
    boolean cnpjValide = false;
    boolean isEdit = false;
    public EmployeeDismissal() {
        initComponents();
        ConnectionModule connect = new ConnectionModule();
        connection = connect.getConnectionMySQL();
        inputCodeEmployee.setDocument(new MaskJustNumbers());
        inputCPFEmployee.setDocument(new MaskCPFAndCNPJ());
        inputEmployeeName.setDocument(new MaskUpperLetter());
        inputDismissalDate.setDocument(new MaskDate());
        inputReasonDismissal.setDocument(new MaskUpperLetter());
        inputBeginAdvance.setDocument(new MaskDate());
        inputEndAdvance.setDocument(new MaskDate());
        inputObservation.setDocument(new MaskUpperLetter());
    }
    public void setScreen(boolean trueOrFalse){
        inputAffirmativeCause.setEnabled(trueOrFalse);
        inputNegativeCause.setEnabled(trueOrFalse);
        inputAffirmativeAdvance.setEnabled(trueOrFalse);
        inputNegativeAdvance.setEnabled(trueOrFalse);
        txtRequiredField8.setVisible(trueOrFalse);
        txtRequiredField9.setVisible(trueOrFalse);
        txtRequiredField10.setVisible(trueOrFalse);
        txtRequiredField11.setVisible(trueOrFalse);
        txtRequiredField12.setVisible(trueOrFalse);
        isEdit = trueOrFalse;
        if(trueOrFalse==false){
            buttonSave.setText("EDITAR");
        }
        else{
            buttonSave.setText("SALVAR");
        }
    }
    private void add(){
        String sql = "insert into dismissalEmployee(codEmployee, cpf, dismissalDate, dismissalReason, dismissalByCause, earlyWarning, beginEarlyWarning, endEarlyWarning, observation)values(?,?,?,?,?,?,?,?,?)";
        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1,Integer.parseInt (inputCodeEmployee.getText() ));
            pst.setString(2,inputCPFEmployee.getText());
            pst.setString(3,inputDismissalDate.getText());
            pst.setString(4,inputReasonDismissal.getText());
            if(inputAffirmativeCause.isSelected()){
                pst.setString(5,"Sim");
            }
            else if(inputNegativeCause.isSelected()){
                pst.setString(5,"Não");
            }
            if(inputAffirmativeAdvance.isSelected()){
                pst.setString(6,"Sim");
            }
            else if(inputNegativeAdvance.isSelected()){
                pst.setString(6,"Não");
            }
            pst.setString(7,inputBeginAdvance.getText());
            pst.setString(8,inputEndAdvance.getText());
            pst.setString(9,inputObservation.getText());
            pst.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void update(){
        String sql = "update employee set statusEmployee=? where cpf=?";
        try {
            pst2=connection.prepareStatement(sql);
            pst2.setString(1,"Inativo");
            pst2.setString(2,inputCPFEmployee.getText());
            pst2.executeUpdate();
            JOptionPane.showMessageDialog(null,"FUNCIONÁRIO DESLIGADO COM SUCESSO");
            setScreen(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }
    private void updateDismissal(){
        String sql = "update dismissalEmployee set codEmployee=?, cpf=?, dismissalDate=?, dismissalReason=?, dismissalByCause=?, earlyWarning=?, beginEarlyWarning=?, endEarlyWarning=?, observation=? where id=?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1,Integer.parseInt (inputCodeEmployee.getText() ));
            pst.setString(2,inputCPFEmployee.getText());
            pst.setString(3,inputDismissalDate.getText());
            pst.setString(4,inputReasonDismissal.getText());
            if(inputAffirmativeCause.isSelected()){
                pst.setString(5,"Sim");
            }
            else if(inputNegativeCause.isSelected()){
                pst.setString(5,"Não");
            }
            if(inputAffirmativeAdvance.isSelected()){
                pst.setString(6,"Sim");
            }
            else if(inputNegativeAdvance.isSelected()){
                pst.setString(6,"Não");
            }
            pst.setString(7,inputBeginAdvance.getText());
            pst.setString(8,inputEndAdvance.getText());
            pst.setString(9,inputObservation.getText());
            pst.setInt(10,dismissalId);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null,"DESLIGAMENTO ATUALIZADO COM SUCESSO");
            setScreen(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }
    private void getEmployeeId(){
        String sql ="select cpf, nameEmployee from employee where id = ?";
        try {
            pst=connection.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt( inputCodeEmployee.getText() ));
            rs = pst.executeQuery();
            if(rs.next()){
                inputCPFEmployee.setText(rs.getString(1));
                inputEmployeeName.setText(rs.getString(2));
                getId();
            }
            else{
                getEmployeeCPF();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void getEmployeeCPF(){
        String sql ="select id, cpf, nameEmployee from employee where cpf = ?";
        try {
            pst=connection.prepareStatement(sql);
            pst.setString(1, inputCPFEmployee.getText());
            rs = pst.executeQuery();
            if(rs.next()){
                inputCodeEmployee.setText(Integer.toString(rs.getInt(1)));
                inputEmployeeName.setText(rs.getString(2));
                getId();
            }
            else{
                JOptionPane.showMessageDialog(null, "FUNCIONÁRIO NÃO ENCONTRADO NO BANCO DE DADOS");
                inputCodeEmployee.setText("");
                inputCPFEmployee.setText("");
                inputEmployeeName.setText("");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private boolean isEmployee(){
        String sql ="select statusEmployee from employee where cpf = ?";
        try {
            pst=connection.prepareStatement(sql);
            pst.setString(1, inputCPFEmployee.getText());
            rs = pst.executeQuery();
            if(rs.next()){
                if(rs.getString(1).equals("Ativo")){
                    return true;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return false;
    }
    private boolean hasDismissal(){
        String sql ="select id from dismissalEmployee where cpf = ?";
        try {
            pst=connection.prepareStatement(sql);
            pst.setString(1, inputCPFEmployee.getText());
            rs = pst.executeQuery();
            if(rs.next()){
                return true;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return false;
    }
    private int valideCPF(){
        String sql ="select id from employee where cpf = ?";
        try {
            pst=connection.prepareStatement(sql);
            pst.setString(1, inputCPFEmployee.getText());
            rs = pst.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return 0;
    }
    private void getId(){
        String sql ="select max(id) from dismissalEmployee where cpf = ?";
        try {
            pst2=connection.prepareStatement(sql);
            pst2.setString(1, inputCPFEmployee.getText());
            rs2 = pst2.executeQuery();
            if(rs2.next()){
                dismissalId = rs2.getInt(1);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void getPrevius(){
        String sql = "SELECT id, codEmployee, cpf, dismissalDate, dismissalReason, dismissalByCause, earlyWarning, beginEarlyWarning, endEarlyWarning, observation FROM dismissalEmployee WHERE id = (SELECT MAX(id) FROM dismissalEmployee WHERE id < ?)";
        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1,dismissalId);
            rs=pst.executeQuery();
            if(rs.next()){
                dismissalId = rs.getInt(1);
                inputCodeEmployee.setText(rs.getString(2));
                inputCPFEmployee.setText(rs.getString(3));
                inputDismissalDate.setText(rs.getString(4));
                inputReasonDismissal.setText(rs.getString(5));
                if(rs.getString(6).equals("Sim")){
                    inputAffirmativeCause.setSelected(true);
                }
                else if(rs.getString(6).equals("Não")){
                    inputNegativeCause.setSelected(true);
                }
                if(rs.getString(7).equals("Sim")){
                    inputAffirmativeAdvance.setSelected(true);
                }
                else if(rs.getString(7).equals("Não")){
                    inputNegativeAdvance.setSelected(true);
                }
                inputBeginAdvance.setText(rs.getString(8));
                inputEndAdvance.setText(rs.getString(9));
                inputObservation.setText(rs.getString(10));
            }
            else{
                JOptionPane.showMessageDialog(null, "NÃO HÁ MAIS DEMISSÕES PARA SEREM MOSTRADAS");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }
    private void getNext(){
        String sql = "SELECT id, codEmployee, cpf, dismissalDate, dismissalReason, dismissalByCause, earlyWarning, beginEarlyWarning, endEarlyWarning, observation FROM dismissalEmployee WHERE id = (SELECT MIN(id) FROM dismissalEmployee WHERE id > ?)";
        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1,dismissalId);
            rs=pst.executeQuery();
            if(rs.next()){
                dismissalId = rs.getInt(1);
                inputCodeEmployee.setText(rs.getString(2));
                inputCPFEmployee.setText(rs.getString(3));
                inputDismissalDate.setText(rs.getString(4));
                inputReasonDismissal.setText(rs.getString(5));
                if(rs.getString(6).equals("Sim")){
                    inputAffirmativeCause.setSelected(true);
                }
                else if(rs.getString(6).equals("Não")){
                    inputNegativeCause.setSelected(true);
                }
                if(rs.getString(7).equals("Sim")){
                    inputAffirmativeAdvance.setSelected(true);
                }
                else if(rs.getString(7).equals("Não")){
                    inputNegativeAdvance.setSelected(true);
                }
                inputBeginAdvance.setText(rs.getString(8));
                inputEndAdvance.setText(rs.getString(9));
                inputObservation.setText(rs.getString(10));
            }
            else{
                JOptionPane.showMessageDialog(null, "NÃO HÁ MAIS DEMISSÕES PARA SEREM MOSTRADAS");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }
    public void getEmploye(){
        String sql = "SELECT id, codEmployee, cpf, dismissalDate, dismissalReason, dismissalByCause, earlyWarning, beginEarlyWarning, endEarlyWarning, observation FROM dismissalEmployee WHERE id = ?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1,dismissalId);
            rs=pst.executeQuery();
            if(rs.next()){
                dismissalId = rs.getInt(1);
                inputCodeEmployee.setText(rs.getString(2));
                inputCPFEmployee.setText(rs.getString(3));
                inputDismissalDate.setText(rs.getString(4));
                inputReasonDismissal.setText(rs.getString(5));
                if(rs.getString(6).equals("Sim")){
                    inputAffirmativeCause.setSelected(true);
                }
                else if(rs.getString(6).equals("Não")){
                    inputNegativeCause.setSelected(true);
                }
                if(rs.getString(7).equals("Sim")){
                    inputAffirmativeAdvance.setSelected(true);
                }
                else if(rs.getString(7).equals("Não")){
                    inputNegativeAdvance.setSelected(true);
                }
                inputBeginAdvance.setText(rs.getString(8));
                inputEndAdvance.setText(rs.getString(9));
                inputObservation.setText(rs.getString(10));
                getNameEmployee();
                setGetEmployee();
            }
            else{
                JOptionPane.showMessageDialog(null, "NÃO HÁ READMISSÃO PARA SER MOSTRADA");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }
    private void setGetEmployee(){
        setScreen(false);
        cpfValide=true;
        cnpjValide=true;
    }
    private void getNameEmployee(){
        String sql = "SELECT nameEmployee FROM employee WHERE cpf = ?";
        try {
            pst3 = connection.prepareStatement(sql);
            pst3.setString(1,inputCPFEmployee.getText());
            rs3=pst3.executeQuery();
            if(rs3.next()){
                inputEmployeeName.setText(rs3.getString(1));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        groupDimissalForCause = new javax.swing.ButtonGroup();
        groupAdvanceNoticeCompliance = new javax.swing.ButtonGroup();
        txtDismissalEmployee = new javax.swing.JLabel();
        txtDismissalDate = new javax.swing.JLabel();
        inputEndAdvance = new javax.swing.JTextField();
        txtReasonDismissal = new javax.swing.JLabel();
        inputReasonDismissal = new javax.swing.JTextField();
        txtDismissalForCause = new javax.swing.JLabel();
        inputNegativeCause = new javax.swing.JRadioButton();
        inputAffirmativeCause = new javax.swing.JRadioButton();
        txtAdvanceNoticeCompliance = new javax.swing.JLabel();
        inputNegativeAdvance = new javax.swing.JRadioButton();
        inputAffirmativeAdvance = new javax.swing.JRadioButton();
        txtEndAdvanceNoticeCompliance = new javax.swing.JLabel();
        txtBeginAdvanceNoticeCompliance = new javax.swing.JLabel();
        inputDismissalDate = new javax.swing.JTextField();
        inputBeginAdvance = new javax.swing.JTextField();
        txtPrevious = new javax.swing.JLabel();
        txtNext = new javax.swing.JLabel();
        txtObservation = new javax.swing.JLabel();
        inputEmployeeDismissal = new javax.swing.JScrollPane();
        inputObservation = new javax.swing.JTextArea();
        buttonSave = new javax.swing.JButton();
        buttonReadmittedEmployee = new javax.swing.JButton();
        inputCPFEmployee = new javax.swing.JTextField();
        inputCodeEmployee = new javax.swing.JTextField();
        buttonLocale = new javax.swing.JButton();
        txtEmployeeCode = new javax.swing.JLabel();
        txtEmployeeCPF = new javax.swing.JLabel();
        inputEmployeeName = new javax.swing.JTextField();
        txtEmployeeName = new javax.swing.JLabel();
        buttonLocaleAll = new javax.swing.JButton();
        txtRequiredField8 = new javax.swing.JLabel();
        txtRequiredField9 = new javax.swing.JLabel();
        txtRequiredField10 = new javax.swing.JLabel();
        txtRequiredField11 = new javax.swing.JLabel();
        txtRequiredField12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Desligamento de Funcionário");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(null);

        txtDismissalEmployee.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        txtDismissalEmployee.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtDismissalEmployee.setText("DESLIGAMENTO DE FUNCIONÁRIO");
        getContentPane().add(txtDismissalEmployee);
        txtDismissalEmployee.setBounds(190, 10, 440, 32);

        txtDismissalDate.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtDismissalDate.setText("Data da Demissão");
        getContentPane().add(txtDismissalDate);
        txtDismissalDate.setBounds(20, 120, 140, 20);
        getContentPane().add(inputEndAdvance);
        inputEndAdvance.setBounds(580, 220, 120, 30);

        txtReasonDismissal.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtReasonDismissal.setText("Motivo da Demissão");
        getContentPane().add(txtReasonDismissal);
        txtReasonDismissal.setBounds(190, 120, 160, 20);
        getContentPane().add(inputReasonDismissal);
        inputReasonDismissal.setBounds(190, 150, 380, 30);

        txtDismissalForCause.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtDismissalForCause.setText("Demissão Por Justa Causa");
        getContentPane().add(txtDismissalForCause);
        txtDismissalForCause.setBounds(610, 120, 200, 20);

        groupDimissalForCause.add(inputNegativeCause);
        inputNegativeCause.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        inputNegativeCause.setText("Não");
        getContentPane().add(inputNegativeCause);
        inputNegativeCause.setBounds(670, 150, 70, 30);

        groupDimissalForCause.add(inputAffirmativeCause);
        inputAffirmativeCause.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        inputAffirmativeCause.setText("Sim");
        getContentPane().add(inputAffirmativeCause);
        inputAffirmativeCause.setBounds(610, 150, 60, 30);

        txtAdvanceNoticeCompliance.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtAdvanceNoticeCompliance.setText("Cumprimento de Aviso Prévio");
        getContentPane().add(txtAdvanceNoticeCompliance);
        txtAdvanceNoticeCompliance.setBounds(20, 190, 230, 20);

        groupAdvanceNoticeCompliance.add(inputNegativeAdvance);
        inputNegativeAdvance.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        inputNegativeAdvance.setText("Não");
        getContentPane().add(inputNegativeAdvance);
        inputNegativeAdvance.setBounds(80, 220, 70, 30);

        groupAdvanceNoticeCompliance.add(inputAffirmativeAdvance);
        inputAffirmativeAdvance.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        inputAffirmativeAdvance.setText("Sim");
        getContentPane().add(inputAffirmativeAdvance);
        inputAffirmativeAdvance.setBounds(20, 220, 60, 30);

        txtEndAdvanceNoticeCompliance.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtEndAdvanceNoticeCompliance.setText("Dia do fim do Aviso Prévio");
        getContentPane().add(txtEndAdvanceNoticeCompliance);
        txtEndAdvanceNoticeCompliance.setBounds(580, 190, 200, 20);

        txtBeginAdvanceNoticeCompliance.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtBeginAdvanceNoticeCompliance.setText("Dia do início do Aviso Prévio");
        getContentPane().add(txtBeginAdvanceNoticeCompliance);
        txtBeginAdvanceNoticeCompliance.setBounds(300, 190, 220, 20);
        getContentPane().add(inputDismissalDate);
        inputDismissalDate.setBounds(20, 150, 120, 30);
        getContentPane().add(inputBeginAdvance);
        inputBeginAdvance.setBounds(300, 220, 120, 30);

        txtPrevious.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtPrevious.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/LeftArrow.png"))); // NOI18N
        txtPrevious.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtPreviousMouseClicked(evt);
            }
        });
        getContentPane().add(txtPrevious);
        txtPrevious.setBounds(450, 440, 30, 30);

        txtNext.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/RightArrow.png"))); // NOI18N
        txtNext.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNextMouseClicked(evt);
            }
        });
        getContentPane().add(txtNext);
        txtNext.setBounds(490, 440, 30, 30);

        txtObservation.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtObservation.setText("Observações");
        getContentPane().add(txtObservation);
        txtObservation.setBounds(20, 260, 110, 20);

        inputObservation.setColumns(20);
        inputObservation.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        inputObservation.setRows(5);
        inputEmployeeDismissal.setViewportView(inputObservation);

        getContentPane().add(inputEmployeeDismissal);
        inputEmployeeDismissal.setBounds(20, 290, 710, 140);

        buttonSave.setText("SALVAR");
        buttonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSaveActionPerformed(evt);
            }
        });
        getContentPane().add(buttonSave);
        buttonSave.setBounds(20, 440, 100, 30);

        buttonReadmittedEmployee.setText("FUNCIONÁRIO READMITIDO");
        buttonReadmittedEmployee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonReadmittedEmployeeActionPerformed(evt);
            }
        });
        getContentPane().add(buttonReadmittedEmployee);
        buttonReadmittedEmployee.setBounds(180, 440, 200, 30);

        inputCPFEmployee.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        inputCPFEmployee.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                inputCPFEmployeeFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                inputCPFEmployeeFocusLost(evt);
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
        inputCPFEmployee.setBounds(210, 80, 120, 30);

        inputCodeEmployee.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        inputCodeEmployee.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                inputCodeEmployeeFocusGained(evt);
            }
        });
        inputCodeEmployee.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputCodeEmployeeKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                inputCodeEmployeeKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                inputCodeEmployeeKeyTyped(evt);
            }
        });
        getContentPane().add(inputCodeEmployee);
        inputCodeEmployee.setBounds(20, 80, 70, 30);

        buttonLocale.setText("LOCALIZAR");
        buttonLocale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLocaleActionPerformed(evt);
            }
        });
        getContentPane().add(buttonLocale);
        buttonLocale.setBounds(370, 60, 100, 20);

        txtEmployeeCode.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtEmployeeCode.setText("Código do Funcionário");
        getContentPane().add(txtEmployeeCode);
        txtEmployeeCode.setBounds(20, 50, 180, 20);

        txtEmployeeCPF.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtEmployeeCPF.setText("CPF do Funcionário");
        getContentPane().add(txtEmployeeCPF);
        txtEmployeeCPF.setBounds(210, 50, 150, 20);

        inputEmployeeName.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        inputEmployeeName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                inputEmployeeNameFocusGained(evt);
            }
        });
        inputEmployeeName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputEmployeeNameKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                inputEmployeeNameKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                inputEmployeeNameKeyTyped(evt);
            }
        });
        getContentPane().add(inputEmployeeName);
        inputEmployeeName.setBounds(500, 80, 340, 30);

        txtEmployeeName.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtEmployeeName.setText("Nome do Funcionário");
        getContentPane().add(txtEmployeeName);
        txtEmployeeName.setBounds(500, 50, 210, 20);

        buttonLocaleAll.setText("TODOS");
        buttonLocaleAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLocaleAllActionPerformed(evt);
            }
        });
        getContentPane().add(buttonLocaleAll);
        buttonLocaleAll.setBounds(370, 90, 100, 20);

        txtRequiredField8.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        txtRequiredField8.setForeground(new java.awt.Color(255, 0, 51));
        txtRequiredField8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtRequiredField8.setText("*");
        getContentPane().add(txtRequiredField8);
        txtRequiredField8.setBounds(180, 50, 20, 30);

        txtRequiredField9.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        txtRequiredField9.setForeground(new java.awt.Color(255, 0, 51));
        txtRequiredField9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtRequiredField9.setText("*");
        getContentPane().add(txtRequiredField9);
        txtRequiredField9.setBounds(350, 50, 20, 30);

        txtRequiredField10.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        txtRequiredField10.setForeground(new java.awt.Color(255, 0, 51));
        txtRequiredField10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtRequiredField10.setText("*");
        getContentPane().add(txtRequiredField10);
        txtRequiredField10.setBounds(150, 120, 20, 30);

        txtRequiredField11.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        txtRequiredField11.setForeground(new java.awt.Color(255, 0, 51));
        txtRequiredField11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtRequiredField11.setText("*");
        getContentPane().add(txtRequiredField11);
        txtRequiredField11.setBounds(810, 120, 20, 30);

        txtRequiredField12.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        txtRequiredField12.setForeground(new java.awt.Color(255, 0, 51));
        txtRequiredField12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtRequiredField12.setText("*");
        getContentPane().add(txtRequiredField12);
        txtRequiredField12.setBounds(240, 190, 20, 30);

        setSize(new java.awt.Dimension(839, 525));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtPreviousMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPreviousMouseClicked
        if(inputCodeEmployee.getText().equals("")&&inputCPFEmployee.getText().equals("")){
            JOptionPane.showMessageDialog(null, "SELECIONE UM FUNCIONÁRIO PRIMEIRO, PARA QUE POSSA VER SUAS DEMISSÕES");
        }
        else{
            getPrevius();
            getNameEmployee();
            if(!inputCPFEmployee.getText().equals("")){
                setScreen(false);
            }
        }
    }//GEN-LAST:event_txtPreviousMouseClicked

    private void txtNextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNextMouseClicked
        if(inputCodeEmployee.getText().equals("")&&inputCPFEmployee.getText().equals("")){
            JOptionPane.showMessageDialog(null, "SELECIONE UM FUNCIONÁRIO PRIMEIRO, PARA QUE POSSA VER SUAS DEMISSÕES");
        }
        else{
            getNext();
            getNameEmployee();
            if(!inputCPFEmployee.getText().equals("")){
                setScreen(false);
            }
        }
    }//GEN-LAST:event_txtNextMouseClicked

    private void buttonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSaveActionPerformed
        if(buttonSave.getText().equals("EDITAR")){
            setScreen(true);
        }
        else{
            if(inputCodeEmployee.getText().equals("")||inputCPFEmployee.getText().equals("")||inputDismissalDate.getText().equals("")||(!inputAffirmativeCause.isSelected() && !inputNegativeCause.isSelected())||(!inputAffirmativeAdvance.isSelected() && !inputNegativeAdvance.isSelected())){
                JOptionPane.showMessageDialog(null, "POR FAVOR, PREENCHA TODOS OS CAMPOS");
            }
            else if(cpfValide==false&&cnpjValide==false){
                JOptionPane.showMessageDialog(null, "O CPF OU O CNPJ DIGITADO É INVÁLIDO!");
            }
            else if(isEdit){
                updateDismissal();
            }
            else if(!isEmployee()){
                JOptionPane.showMessageDialog(null, "O FUNCIONÁRIO EM QUESTÃO JÁ ESTÁ DESLIGADO DA EMPRESA");
            }
            else{
                add();
                update();
            }
        }
    }//GEN-LAST:event_buttonSaveActionPerformed

    private void buttonReadmittedEmployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonReadmittedEmployeeActionPerformed
        ReadmittedScreen readmittedScreen = new ReadmittedScreen();
        if(valideCPF()!=0){
            readmittedScreen.inputCodeEmployee.setText(Integer.toString(valideCPF()));
            readmittedScreen.getEmployeeId();
            readmittedScreen.setGetEmployee();
        }
        this.dispose();
        readmittedScreen.setVisible(true);
    }//GEN-LAST:event_buttonReadmittedEmployeeActionPerformed

    private void inputCPFEmployeeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputCPFEmployeeFocusGained
        inputCPFEmployee.selectAll();
    }//GEN-LAST:event_inputCPFEmployeeFocusGained

    private void inputCPFEmployeeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputCPFEmployeeKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputCPFEmployeeKeyPressed

    private void inputCPFEmployeeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputCPFEmployeeKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_inputCPFEmployeeKeyReleased

    private void inputCPFEmployeeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputCPFEmployeeKeyTyped
        
    }//GEN-LAST:event_inputCPFEmployeeKeyTyped

    private void inputCodeEmployeeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputCodeEmployeeFocusGained
        inputCodeEmployee.selectAll();
    }//GEN-LAST:event_inputCodeEmployeeFocusGained

    private void inputCodeEmployeeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputCodeEmployeeKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputCodeEmployeeKeyPressed

    private void inputCodeEmployeeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputCodeEmployeeKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_inputCodeEmployeeKeyReleased

    private void inputCodeEmployeeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputCodeEmployeeKeyTyped
        
    }//GEN-LAST:event_inputCodeEmployeeKeyTyped

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        if(x2==0){
            x2++;
            inputCodeEmployee.requestFocus();
            inputDismissalDate.setText(getDate.dateOfSystem());
            inputNegativeCause.setSelected(true);
            inputAffirmativeAdvance.setSelected(true);
        }
    }//GEN-LAST:event_formWindowActivated

    private void inputEmployeeNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputEmployeeNameFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_inputEmployeeNameFocusGained

    private void inputEmployeeNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputEmployeeNameKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputEmployeeNameKeyPressed

    private void inputEmployeeNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputEmployeeNameKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_inputEmployeeNameKeyReleased

    private void inputEmployeeNameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputEmployeeNameKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_inputEmployeeNameKeyTyped

    private void buttonLocaleAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLocaleAllActionPerformed
        LocaleEmployee localeEmployee = new LocaleEmployee();
        localeEmployee.setVisible(true);
    }//GEN-LAST:event_buttonLocaleAllActionPerformed

    private void buttonLocaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLocaleActionPerformed
        if(inputCodeEmployee.getText().equals("")&&inputCPFEmployee.getText().equals("")){
            JOptionPane.showMessageDialog(null, "POR FAVOR, INSIRA UM CÓDIGO OU UM CPF");
        }
        else{
            if(!inputCodeEmployee.getText().equals("")){
                getEmployeeId();
            }
            else if(!inputCPFEmployee.getText().equals("")){
                getEmployeeCPF();
            }
            if(hasDismissal()){
                getEmploye();
            }
        }
    }//GEN-LAST:event_buttonLocaleActionPerformed

    private void inputCPFEmployeeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputCPFEmployeeFocusLost
        if(!inputCPFEmployee.getText().equals("")){
            ValidateCPF validateCPF = new ValidateCPF();
            ValidateCNPJ validateCNPJ = new ValidateCNPJ();
            GetJustTheNumbers getJustTheNumbers = new GetJustTheNumbers();
            if(inputCPFEmployee.getText().length()>10 && inputCPFEmployee.getText().length()<15 && !validateCPF.isValide( getJustTheNumbers.getNumbers( inputCPFEmployee.getText() ) )){
                JOptionPane.showMessageDialog(null, "O CPF DIGITADO É INVÁLIDO");
                cpfValide = false;
            }
            else if(inputCPFEmployee.getText().length()>10 && inputCPFEmployee.getText().length()<15 && validateCPF.isValide( getJustTheNumbers.getNumbers( inputCPFEmployee.getText() ) )){
                cpfValide = true;
            }
            else if(inputCPFEmployee.getText().length()>13 && inputCPFEmployee.getText().length()<19 && !validateCNPJ.isValide( getJustTheNumbers.getNumbers( inputCPFEmployee.getText() ) )){
                JOptionPane.showMessageDialog(null, "O CNPJ DIGITADO É INVÁLIDO");
                cnpjValide = false;
            }
            else if(inputCPFEmployee.getText().length()>14 && inputCPFEmployee.getText().length()<19 && validateCNPJ.isValide( getJustTheNumbers.getNumbers( inputCPFEmployee.getText() ) )){
                cnpjValide = true;
            }
        }
    }//GEN-LAST:event_inputCPFEmployeeFocusLost

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
            java.util.logging.Logger.getLogger(EmployeeDismissal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EmployeeDismissal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EmployeeDismissal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EmployeeDismissal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EmployeeDismissal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonLocale;
    private javax.swing.JButton buttonLocaleAll;
    private javax.swing.JButton buttonReadmittedEmployee;
    private javax.swing.JButton buttonSave;
    private javax.swing.ButtonGroup groupAdvanceNoticeCompliance;
    private javax.swing.ButtonGroup groupDimissalForCause;
    private javax.swing.JRadioButton inputAffirmativeAdvance;
    private javax.swing.JRadioButton inputAffirmativeCause;
    private javax.swing.JTextField inputBeginAdvance;
    private javax.swing.JTextField inputCPFEmployee;
    private javax.swing.JTextField inputCodeEmployee;
    private javax.swing.JTextField inputDismissalDate;
    private javax.swing.JScrollPane inputEmployeeDismissal;
    private javax.swing.JTextField inputEmployeeName;
    private javax.swing.JTextField inputEndAdvance;
    private javax.swing.JRadioButton inputNegativeAdvance;
    private javax.swing.JRadioButton inputNegativeCause;
    private javax.swing.JTextArea inputObservation;
    private javax.swing.JTextField inputReasonDismissal;
    private javax.swing.JLabel txtAdvanceNoticeCompliance;
    private javax.swing.JLabel txtBeginAdvanceNoticeCompliance;
    private javax.swing.JLabel txtDismissalDate;
    private javax.swing.JLabel txtDismissalEmployee;
    private javax.swing.JLabel txtDismissalForCause;
    private javax.swing.JLabel txtEmployeeCPF;
    private javax.swing.JLabel txtEmployeeCode;
    private javax.swing.JLabel txtEmployeeName;
    private javax.swing.JLabel txtEndAdvanceNoticeCompliance;
    private javax.swing.JLabel txtNext;
    private javax.swing.JLabel txtObservation;
    private javax.swing.JLabel txtPrevious;
    private javax.swing.JLabel txtReasonDismissal;
    private javax.swing.JLabel txtRequiredField10;
    private javax.swing.JLabel txtRequiredField11;
    private javax.swing.JLabel txtRequiredField12;
    private javax.swing.JLabel txtRequiredField8;
    private javax.swing.JLabel txtRequiredField9;
    // End of variables declaration//GEN-END:variables
}
