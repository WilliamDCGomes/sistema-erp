package screens;

import functioncontroller.GetImageAdress;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import connectionbd.ConnectionModule;
import formattingmask.MaskCPFAndCNPJ;
import formattingmask.MaskCash;
import formattingmask.MaskCepAndHouseNumber;
import formattingmask.MaskDate;
import formattingmask.MaskJustNumbers;
import formattingmask.MaskPhone;
import formattingmask.MaskRG;
import formattingmask.MaskUpperLetter;
import java.awt.Image;
import javax.swing.ImageIcon;

public class EmployeeScreen extends javax.swing.JFrame {
    Connection connection = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    PreparedStatement pst2 = null;
    ResultSet rs2 = null;
    String imageAdress = null;
    GetImageAdress getImageAdress = new GetImageAdress();
    int x = 0;
    public EmployeeScreen() {
        initComponents();
        ConnectionModule connect = new ConnectionModule();
        connection = connect.getConnectionMySQL();
        outputFullName.setDocument(new MaskUpperLetter());
        outputBirthDay.setDocument(new MaskDate());
        outputRG.setDocument(new MaskRG());
        outputCPF.setDocument(new MaskCPFAndCNPJ());
        outputPhone.setDocument(new MaskPhone());
        outputCellPhone.setDocument(new MaskPhone());
        outputEmail.setDocument(new MaskUpperLetter());
        outputFatherName.setDocument(new MaskUpperLetter());
        outputMotherName.setDocument(new MaskUpperLetter());
        outputCEP.setDocument(new MaskCepAndHouseNumber());
        outputStreet.setDocument(new MaskUpperLetter());
        outputNumberHouse.setDocument(new MaskCepAndHouseNumber());
        outputNeighBorhood.setDocument(new MaskUpperLetter());
        outputCity.setDocument(new MaskUpperLetter());
        outputComplement.setDocument(new MaskUpperLetter());
        outputAdmissionDate.setDocument(new MaskDate());
        outputOccupation.setDocument(new MaskUpperLetter());
        outputSalary.setDocument(new MaskCash());
        outputCommission.setDocument(new MaskJustNumbers());
        outputFoodVoucher.setDocument(new MaskCash());
        outputMealTicket.setDocument(new MaskCash());
        outputTranportationVoucher.setDocument(new MaskCash());
        outputPIS.setDocument(new MaskCPFAndCNPJ());
        outputAgency.setDocument(new MaskCPFAndCNPJ());
        outputAccount.setDocument(new MaskCPFAndCNPJ());
        outputDismissalDate.setDocument(new MaskDate());
        outputReasonDismissal.setDocument(new MaskUpperLetter());
    }
    private void setProduct(){
        String[] id = this.getTitle().split(" ");
        String sql ="select nameEmployee, birthday, rg, cpf, sexo, phone, cellPhone, email, fatherName, motherName, cep, street, houseNumber, neighborhood, city, state, complement, admissionDate, functionEmployee, salary, commission, foodVoucher, mealTicket, transportationVouchers, pisAndPasep, bank, agency, accountBank, bankType, photoAdress, readmissionEmployee, statusEmployee from employee where id=?";
        try {
            pst=connection.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt( id[1] ));
            rs= pst.executeQuery();
            if(rs.next()){
                outputFullName.setText(rs.getString(1));
                outputBirthDay.setText(rs.getString(2));
                outputRG.setText(rs.getString(3));
                outputCPF.setText(rs.getString(4));
                outputSex.setSelectedItem(rs.getString(5));
                outputPhone.setText(rs.getString(6));
                outputCellPhone.setText(rs.getString(7));
                outputEmail.setText(rs.getString(8));
                outputFatherName.setText(rs.getString(9));
                outputMotherName.setText(rs.getString(10));
                outputCEP.setText(rs.getString(11));
                outputStreet.setText(rs.getString(12));
                outputNumberHouse.setText(rs.getString(13));
                outputNeighBorhood.setText(rs.getString(14));
                outputCity.setText(rs.getString(15));
                outputState.setSelectedItem(rs.getString(16));
                outputComplement.setText(rs.getString(17));
                if(rs.getString(31).equals("Não")){
                    outputAdmissionDate.setText(rs.getString(18));
                    outputOccupation.setText(rs.getString(19));
                    outputSalary.setText(rs.getString(20));
                    outputCommission.setText(rs.getString(21));
                    outputFoodVoucher.setText(rs.getString(22));
                    outputMealTicket.setText(rs.getString(23));
                    outputTranportationVoucher.setText(rs.getString(24));
                    outputPIS.setText(rs.getString(25));
                    outputBank.setSelectedItem(rs.getString(26));
                    outputAgency.setText(rs.getString(27));
                    outputAccount.setText(rs.getString(28));
                    outputAccountType.setSelectedItem(rs.getString(29));
                }
                else if(rs.getString(31).equals("Sim")){
                    setReadmissal();
                }
                if(rs.getString(30)!=null){
                    outputPhoto.setText("");
                    imageAdress = rs.getString(30);
                    ImageIcon imagen = new ImageIcon(imageAdress);
                    outputPhoto.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(outputPhoto.getWidth(), outputPhoto.getHeight(), Image.SCALE_DEFAULT)));
                }
                if(rs.getString(32).equals("Inativo")){
                    setDimissal();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void setDimissal(){
        String sql ="select dismissalDate, dismissalReason from dismissalEmployee where id=(select max(id) from dismissalEmployee where cpf=?)";
        try {
            pst2=connection.prepareStatement(sql);
            pst2.setString(1, outputCPF.getText());
            rs2= pst2.executeQuery();
            if(rs2.next()){
                outputDismissalDate.setText(rs2.getString(1));
                outputReasonDismissal.setText(rs2.getString(2));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void setReadmissal(){
        txtAdmissionDate.setText("Data de Readmissão");
        String sql ="select readmissionDate, functionEmployee, salary, commission, foodVoucher, mealTicket, transportationVouchers, pisAndPasep, bank, agency, accountBank, bankType from readmissionEmployee where id=(select max(id) from readmissionEmployee where cpf=?)";
        try {
            pst2=connection.prepareStatement(sql);
            pst2.setString(1, outputCPF.getText());
            rs2= pst2.executeQuery();
            if(rs2.next()){
                outputAdmissionDate.setText(rs2.getString(1));
                outputOccupation.setText(rs2.getString(2));
                outputSalary.setText(rs2.getString(3));
                outputCommission.setText(rs2.getString(4));
                outputFoodVoucher.setText(rs2.getString(5));
                outputMealTicket.setText(rs2.getString(6));
                outputTranportationVoucher.setText(rs2.getString(7));
                outputPIS.setText(rs2.getString(8));
                outputBank.setSelectedItem(rs2.getString(9));
                outputAgency.setText(rs2.getString(10));
                outputAccount.setText(rs2.getString(11));
                outputAccountType.setSelectedItem(rs2.getString(12));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private int getId(){
        String sql ="select max(id) from dismissalEmployee where cpf = ?";
        try {
            pst2=connection.prepareStatement(sql);
            pst2.setString(1, outputCPF.getText());
            rs2 = pst2.executeQuery();
            if(rs2.next()){
                return rs2.getInt(1);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return 0;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtEmployee = new javax.swing.JLabel();
        txtFullName = new javax.swing.JLabel();
        txtDismissalDate = new javax.swing.JLabel();
        outputDismissalDate = new javax.swing.JTextField();
        txtBirthDay = new javax.swing.JLabel();
        txtRG = new javax.swing.JLabel();
        txtCPF = new javax.swing.JLabel();
        txtSex = new javax.swing.JLabel();
        txtCEP = new javax.swing.JLabel();
        txtStreet = new javax.swing.JLabel();
        txtNumberHouse = new javax.swing.JLabel();
        txtNeighBorhood = new javax.swing.JLabel();
        txtCity = new javax.swing.JLabel();
        txtState = new javax.swing.JLabel();
        txtComplement = new javax.swing.JLabel();
        txtAdmissionDate = new javax.swing.JLabel();
        txtPhone = new javax.swing.JLabel();
        txtCellPhone = new javax.swing.JLabel();
        txtEmail = new javax.swing.JLabel();
        txtOccupation = new javax.swing.JLabel();
        txtCommission = new javax.swing.JLabel();
        outputPhoto = new javax.swing.JButton();
        txtSalary = new javax.swing.JLabel();
        txtFoodVoucher = new javax.swing.JLabel();
        txtTranportationVoucher = new javax.swing.JLabel();
        txtMealTicket = new javax.swing.JLabel();
        txtPIS = new javax.swing.JLabel();
        txtBank = new javax.swing.JLabel();
        txtAgency = new javax.swing.JLabel();
        txtAccount = new javax.swing.JLabel();
        txtAccountType = new javax.swing.JLabel();
        txtFatherName = new javax.swing.JLabel();
        txtMotherName = new javax.swing.JLabel();
        outputEmail = new javax.swing.JTextField();
        outputCPF = new javax.swing.JTextField();
        outputCellPhone = new javax.swing.JTextField();
        outputSex = new javax.swing.JComboBox<>();
        outputCEP = new javax.swing.JTextField();
        outputPhone = new javax.swing.JTextField();
        outputMotherName = new javax.swing.JTextField();
        outputFullName = new javax.swing.JTextField();
        outputComplement = new javax.swing.JTextField();
        outputRG = new javax.swing.JTextField();
        outputFatherName = new javax.swing.JTextField();
        outputBirthDay = new javax.swing.JTextField();
        outputOccupation = new javax.swing.JTextField();
        outputStreet = new javax.swing.JTextField();
        outputNeighBorhood = new javax.swing.JTextField();
        outputAccountType = new javax.swing.JComboBox<>();
        outputAdmissionDate = new javax.swing.JTextField();
        outputPIS = new javax.swing.JTextField();
        outputNumberHouse = new javax.swing.JTextField();
        outputAgency = new javax.swing.JTextField();
        outputAccount = new javax.swing.JTextField();
        outputState = new javax.swing.JComboBox<>();
        outputTranportationVoucher = new javax.swing.JTextField();
        outputCity = new javax.swing.JTextField();
        outputBank = new javax.swing.JComboBox<>();
        outputSalary = new javax.swing.JTextField();
        outputCommission = new javax.swing.JTextField();
        outputFoodVoucher = new javax.swing.JTextField();
        outputMealTicket = new javax.swing.JTextField();
        buttonEdit = new javax.swing.JButton();
        buttonLocale = new javax.swing.JButton();
        buttonTerminator = new javax.swing.JButton();
        txtReasonDismissal = new javax.swing.JLabel();
        outputReasonDismissal = new javax.swing.JTextField();
        txtStatus = new javax.swing.JLabel();
        outputStatus = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        buttonPrinter = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Funcionário: 150");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(null);

        txtEmployee.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        txtEmployee.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtEmployee.setText("FUNCIONÁRIO");
        getContentPane().add(txtEmployee);
        txtEmployee.setBounds(360, 10, 280, 32);

        txtFullName.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtFullName.setText("Nome Completo");
        getContentPane().add(txtFullName);
        txtFullName.setBounds(20, 60, 120, 20);

        txtDismissalDate.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtDismissalDate.setText("Data de Demissão");
        getContentPane().add(txtDismissalDate);
        txtDismissalDate.setBounds(190, 410, 140, 20);

        outputDismissalDate.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(outputDismissalDate);
        outputDismissalDate.setBounds(190, 440, 110, 30);

        txtBirthDay.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtBirthDay.setText("Data de Nascimento");
        getContentPane().add(txtBirthDay);
        txtBirthDay.setBounds(380, 60, 150, 20);

        txtRG.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtRG.setText("RG");
        getContentPane().add(txtRG);
        txtRG.setBounds(550, 60, 40, 20);

        txtCPF.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtCPF.setText("CPF");
        getContentPane().add(txtCPF);
        txtCPF.setBounds(690, 60, 50, 20);

        txtSex.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtSex.setText("Sexo");
        getContentPane().add(txtSex);
        txtSex.setBounds(20, 130, 50, 20);

        txtCEP.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtCEP.setText("CEP");
        getContentPane().add(txtCEP);
        txtCEP.setBounds(20, 270, 50, 20);

        txtStreet.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtStreet.setText("Rua");
        getContentPane().add(txtStreet);
        txtStreet.setBounds(150, 270, 40, 20);

        txtNumberHouse.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtNumberHouse.setText("Número");
        getContentPane().add(txtNumberHouse);
        txtNumberHouse.setBounds(580, 270, 60, 20);

        txtNeighBorhood.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtNeighBorhood.setText("Bairro");
        getContentPane().add(txtNeighBorhood);
        txtNeighBorhood.setBounds(20, 340, 50, 20);

        txtCity.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtCity.setText("Cidade");
        getContentPane().add(txtCity);
        txtCity.setBounds(390, 340, 60, 20);

        txtState.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtState.setText("Estado");
        getContentPane().add(txtState);
        txtState.setBounds(670, 340, 70, 20);

        txtComplement.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtComplement.setText("Complemento");
        getContentPane().add(txtComplement);
        txtComplement.setBounds(690, 270, 110, 20);

        txtAdmissionDate.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtAdmissionDate.setText("Data de Admissão");
        getContentPane().add(txtAdmissionDate);
        txtAdmissionDate.setBounds(20, 410, 170, 20);

        txtPhone.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtPhone.setText("Telefone");
        getContentPane().add(txtPhone);
        txtPhone.setBounds(170, 130, 80, 20);

        txtCellPhone.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtCellPhone.setText("Celular");
        getContentPane().add(txtCellPhone);
        txtCellPhone.setBounds(330, 130, 60, 20);

        txtEmail.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtEmail.setText("Email");
        getContentPane().add(txtEmail);
        txtEmail.setBounds(490, 130, 50, 20);

        txtOccupation.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtOccupation.setText("Função");
        getContentPane().add(txtOccupation);
        txtOccupation.setBounds(710, 410, 70, 20);

        txtCommission.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtCommission.setText("Comissão");
        getContentPane().add(txtCommission);
        txtCommission.setBounds(160, 480, 80, 20);

        outputPhoto.setText("FOTO");
        outputPhoto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        outputPhoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                outputPhotoActionPerformed(evt);
            }
        });
        getContentPane().add(outputPhoto);
        outputPhoto.setBounds(850, 60, 150, 210);

        txtSalary.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtSalary.setText("Remuneração");
        getContentPane().add(txtSalary);
        txtSalary.setBounds(20, 480, 110, 20);

        txtFoodVoucher.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtFoodVoucher.setText("Vale Alimentação");
        getContentPane().add(txtFoodVoucher);
        txtFoodVoucher.setBounds(300, 480, 130, 20);

        txtTranportationVoucher.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtTranportationVoucher.setText("Vale Transporte");
        getContentPane().add(txtTranportationVoucher);
        txtTranportationVoucher.setBounds(580, 480, 130, 20);

        txtMealTicket.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtMealTicket.setText("Vale Refeição");
        getContentPane().add(txtMealTicket);
        txtMealTicket.setBounds(440, 480, 110, 20);

        txtPIS.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtPIS.setText("PIS/PASEP");
        getContentPane().add(txtPIS);
        txtPIS.setBounds(740, 480, 90, 20);

        txtBank.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtBank.setText("Banco");
        getContentPane().add(txtBank);
        txtBank.setBounds(20, 550, 60, 20);

        txtAgency.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtAgency.setText("Agência");
        getContentPane().add(txtAgency);
        txtAgency.setBounds(410, 550, 70, 20);

        txtAccount.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtAccount.setText("Conta");
        getContentPane().add(txtAccount);
        txtAccount.setBounds(540, 550, 50, 20);

        txtAccountType.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtAccountType.setText("Tipo de Conta");
        getContentPane().add(txtAccountType);
        txtAccountType.setBounds(740, 550, 110, 20);

        txtFatherName.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtFatherName.setText("Nome do Pai");
        getContentPane().add(txtFatherName);
        txtFatherName.setBounds(20, 200, 100, 20);

        txtMotherName.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtMotherName.setText("Nome da Mãe");
        getContentPane().add(txtMotherName);
        txtMotherName.setBounds(400, 200, 110, 20);

        outputEmail.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(outputEmail);
        outputEmail.setBounds(490, 160, 340, 30);

        outputCPF.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(outputCPF);
        outputCPF.setBounds(690, 90, 130, 30);

        outputCellPhone.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(outputCellPhone);
        outputCellPhone.setBounds(330, 160, 130, 30);

        outputSex.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        outputSex.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Masculino", "Feminino" }));
        outputSex.setEnabled(false);
        getContentPane().add(outputSex);
        outputSex.setBounds(20, 160, 130, 30);

        outputCEP.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(outputCEP);
        outputCEP.setBounds(20, 300, 100, 30);

        outputPhone.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(outputPhone);
        outputPhone.setBounds(170, 160, 130, 30);

        outputMotherName.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(outputMotherName);
        outputMotherName.setBounds(400, 230, 340, 30);

        outputFullName.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(outputFullName);
        outputFullName.setBounds(20, 90, 330, 30);

        outputComplement.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(outputComplement);
        outputComplement.setBounds(690, 300, 310, 30);

        outputRG.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(outputRG);
        outputRG.setBounds(550, 90, 110, 30);

        outputFatherName.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(outputFatherName);
        outputFatherName.setBounds(20, 230, 330, 30);

        outputBirthDay.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(outputBirthDay);
        outputBirthDay.setBounds(380, 90, 110, 30);

        outputOccupation.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(outputOccupation);
        outputOccupation.setBounds(710, 440, 290, 30);

        outputStreet.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(outputStreet);
        outputStreet.setBounds(150, 300, 400, 30);

        outputNeighBorhood.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(outputNeighBorhood);
        outputNeighBorhood.setBounds(20, 370, 330, 30);

        outputAccountType.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        outputAccountType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECIONAR", "CONTA CORRENTE", "POUPANÇA", "CONTA SALÁRIO" }));
        outputAccountType.setEnabled(false);
        getContentPane().add(outputAccountType);
        outputAccountType.setBounds(740, 580, 260, 30);

        outputAdmissionDate.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(outputAdmissionDate);
        outputAdmissionDate.setBounds(20, 440, 110, 30);

        outputPIS.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(outputPIS);
        outputPIS.setBounds(740, 510, 220, 30);

        outputNumberHouse.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(outputNumberHouse);
        outputNumberHouse.setBounds(580, 300, 80, 30);

        outputAgency.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(outputAgency);
        outputAgency.setBounds(410, 580, 100, 30);

        outputAccount.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(outputAccount);
        outputAccount.setBounds(540, 580, 170, 30);

        outputState.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        outputState.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECIONAR", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
        outputState.setEnabled(false);
        getContentPane().add(outputState);
        outputState.setBounds(670, 370, 140, 30);

        outputTranportationVoucher.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(outputTranportationVoucher);
        outputTranportationVoucher.setBounds(580, 510, 100, 30);

        outputCity.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(outputCity);
        outputCity.setBounds(390, 370, 240, 30);

        outputBank.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        outputBank.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECIONAR", "ACESSO SOLUCOES DE PAGAMENTO S", "ADVANCED CORRET.CAMBIO LTDA", "AGK CORRETORA DE CAMBIO S.A.", "AL5 S.A.CRED. FINANC. E INVEST", "AMAZONIA CORRETORA DE CAMBIO L", "ATIVA INVESTIMENTOS S.A.", "AVISTA S.A. CREDITO.FINAN. E", "B.OF A.MERRILL LYNCH B.MULT.S.", "B&T ASSOCIADOS CORRETORA DE CA", "BANCO ABC-BRASIL S.A.", "BANCO ABN AMRO S.A.", "BANCO AGIBANK S.A.", "BANCO ALFA S.A.", "BANCO ALVORADA S.A.", "BANCO ANDBANK BRASIL S.A.", "BANCO ARBI S.A.", "BANCO B3 S.A.", "BANCO BARI INVESTIMENO E FINA", "BANCO BMG S.A.", "BANCO BNP PARIBAS BRASIL S.A.", "BANCO BOCOM BBM S.A.", "BANCO BRADESCARD S.A.", "BANCO BRADESCO BBI S.A.", "BANCO BRADESCO BERJ S.A.", "BANCO BRADESCO S.A.", "BANCO BS2 S.A.", "BANCO BTG PACTUAL S.A.", "BANCO C6 CONSIGNADO S.A.", "BANCO C6 S.A.", "BANCO CAIXA GERAL - BRASIL S.A.", "BANCO CAPITAL S.A.", "BANCO CARGILL S.A", "BANCO CEDULA S.A.", "BANCO CETELEM S.A.", "BANCO CIFRA S.A.", "BANCO CITIBANK S.A.", "BANCO CLASSICO S.A.", "BANCO CM CAPITAL MARKETS CCTVM", "BANCO COOPERATIOVO SICREDI S.A.", "BANCO COOPERATIVO DO BRASIL S.A.", "BANCO CREDIT AGRICOLE BRASIL S.A.", "BANCO CREDIT SUISSE(BRASIL) S", "BANCO CREFISA S.A.", "BANCO CSF S.A.", "BANCO DA AMAZONIA S.A.", "BANCO DA CHINA BRASIL S.A.", "BANCO DAYCOVAL S.A.", "BANCO DE LA NACION ARGENTINA", "BANCO DE LA PROV. DE BUENOS AL", "BANCO DE PERNAMBUCO S.A. - BANDE", "BANCO DIGIMAIS S.A.", "BANCO DIGIO S.A.", "BANCO DO BRASIL S.A.", "BANCO DO EST. DE SERGIPE S.A.", "BANCO DO EST. DO PARA S.A.", "BANCO DO NORDESTE DO BRASIL S.A.", "BANCO FATOR S.A.", "BANCO FIBRA S.A.", "BANCO FINAXIS S.A.", "BANCO GM S.A.", "BANCO GUANABARA S.A.", "BANCO HSBC S.A.", "BANCO INBURSA S.A.", "BANCO INDUSTRIAL DO BRASIL S.A.", "BANCO INDUSVAL S.A.", "BANCO INTER S.A.", "BANCO INVESTCRED UNIBANCO S.A.", "BANCO ITAU BBA S.A.", "BANCO ITAU HOLDING FINANCEIRA", "BANCO ITAUBANK S.A.", "BANCO J. P. MORGAN S.A.", "BANCO J. SAFRA S.A.", "BANCO JOHN DEERE S.A.", "BANCO KEB HANA DO BRASIL S.A.", "BANCO LUSO BRASILEIRO S.A.", "BANCO MAXIMA S.A.", "BANCO MECANTIL DO BRASIL S.A.", "BANCO MERCEDES-BENZ DO BRASIL", "BANCO MIZUHO DO BRASIL S.A.", "BANCO MODAL S.A.", "BANCO MORGAN STANLEY S.A.", "BANCO MUFG BRASIL S.A.", "BANCO NAC.DESENV.ECON. SOCIAL", "BANCO OLE CONSIGNADO S.A.", "BANCO ORIGINAL DO AGRONEGOCIO", "BANCO ORIGINAL S.A.", "BANCO OURINVEST S.A.", "BANCO PAN S.A.", "BANCO PAULISTA S.A.", "BANCO PINA. S.A.", "BANCO RADOBANK INTERNATIONAL B", "BANCO RANDON S.A.", "BANCO RENDIMENTO S.A.", "BANCO RIBEIRAO PRETO S.A.", "BANCO RODOBENS S.A.", "BANCO SAFRA S.A.", "BANCO SANTANDER (BRASIL) S.A.", "BANCO SC TULLET PREBON", "BANCO SEMEAR S.A.", "BANCO SISTEMA S.A.", "BANCO SMARTBANK S.A.", "BANCO SOCIETE GENERALE BRASIL", "BANCO SOFISA S.A.", "BANCO SUMITOMO MITSUI BRASILEI", "BANCO TOPAZIO S.A.", "BANCO TOYOTA DO BRASIL S.A.", "BANCO TRIANGULO S.A.", "BANCO TRICURY S.A.", "BANCO UNICO S.A.", "BANCO VOLKSWAGEN S.A.", "BANCO VOTORANTIM S.A.", "BANCO VR S.A.", "BANCO WESTERN UNION DO BRASIL", "BANCO WOORI BANK DO BRASIL S.A.", "BANCO XP S.A.", "BANCOSEGURO S.A.", "BANESTES S.A. BCO.EST.ESPIRITO", "BARI COMPANHIA HIPOTECARIA", "BBC LEASING S.A. - ARRENDAMENTO", "BCG LIQUIDEZ DISTR TITS VLRS M", "BCO BRADESCO FINANCIAMENTOS S.A.", "BCO.EST.R.GRANDE DO SUL S.A.", "BCO.ITAU BMG CONSIGNADO S.A.", "BCV - BANCO DE CRÉDITO E VAREJ", "BEXS BANCO DE CAMBIO S.A.", "BEXS CORRETORA DE CAMBIO S/A", "BI UBS BRASIL", "BNY MELLON BANCO S.A.", "BOLETOBANCARIO.COM TEC DE PAGT", "BPP INSTITUIÇÃO DE PAGAMENTOS", "BR PARTNERS BCO INVEST. S.A.", "BRB-BANCO DE BRASILIA S.A.", "BRK S.A. CRED. FINANC E INVEST", "BRL TRUST DIST. DE TIT. E VAL.", "BROKER BRASIL CORRET CAMBIO LT", "BS2 DIST. DE TIT. E VAL. MOB.", "CAIXA ECONOMICA FEDERAL", "CAMBIONET CORRETORA DE CÂMBIO", "CAROL DISTR. D. TIT. E VAL. IM", "CARTOS SOCIEDADE DE CREDITO DI", "CARUANA S.A. - SOC.CRED. FINAN", "CASA DO CREDITO S.A. SOC.D.CRED.", "CC UNIPRIME CENTRAL", "CC UNIPRIME NORTE DO PARANA", "CENTRAL COOP.EC.CR.MUTUO ESP.S", "CHINA CONSTRUCTION BANK (BRASIL", "CIELO S.A.", "CITIBANCK N.A.", "CODEPE - CORRETORA DE VALORES", "COMMERZBANK BRASIL S.A. - BANC", "CONFED.NAC.COOP.CENTRAIS ECON", "CONFIDENCE CORRETORA DE CÂMBIO", "COOP CRED RURAL DE IBIAM-SULCR", "COOP CRED RURAL PEQ AGRICULTOR", "COOP DE CRED RURAL DE ABELARDO", "COOP DE CRED.RURAL DA REG DA M", "COOP. DE CRED. RURAL D.PRIMAVE", "COOP.CRED.MUTUO DESP.TR.DE STA", "COOP.D.CRED.RURAL DE OUROSULC", "COOP.D.CRED.RURAL DE S. MIGUE", "COOPERATIVA CENTRAL SANTA CATAR", "COOPERATIVA DE CREDITO RURAL C", "COOPERFORTE-COOP DE ECON E CRE", "CORA SOCIEDADE DE CREDITO DIRE", "CREDIALIANCA COOP CRED RURAL", "CREDICOAMO CRED.RURAL COOP", "CREDISIS-CENTRAL DE COOP DE CR", "CREDIT SUISSE HG CORRETORA", "CREDITAS SOC. DE CREDITO DIRET", "CREFAZ SOC DE CRED AO MICROEMP", "DECYSEO CORRETORA DE CAMBIO LT", "DEUTSCHE BANK S.A.-BANCO ALEMA", "EASYNVEST TIT CORR VALORES S.A.", "FACTA FINANCEIRA S.A.", "FAIR CORRETORA DE CAMBIO S.A.", "FFA SOC DE CRED AO MIC E A EMP", "FIDUCIA SOC DE CRED AO MICRO E", "FRAM CAPITAL DIST DE TIT E VAL", "FRENT CORRETORA DE CAMBIO LTD", "GENIAL INVEST. COR DE VAL MOBI", "GERENCIANET PAGAMENTOS DO BRASIL", "GET MONEY CORRETORA DE CAMBIO", "GOLDMAN SACHS DO BRASIL. BCO MU", "GUIDE INVEST S.A. CORRETORA DE", "GUITTA CORRETORA DE CAMBIO", "HAITONG BANCO DE INVEST DO BRA", "HIPERCARD BANCO MULTIPLO S.A.", "HS FINANCEIRA S/A", "HUB PAGAMENTOS S.A.", "IB CORRET. DE CAMBIO. TIT.E VA", "ICAP DO BRASIL CORRETORA TITS", "ICBC DO BRASIL BCO MULTIPLO S.", "ING BANK N.V.", "INTESA SANPAOLO BRASIL S.A.", "ITAU UNIBANCO S.A.", "JP MORGAN CHASE BANK", "KDB DO BRASIL", "KIRTON BANK S.A. BCO. MULTIPLO", "LASTRO RDV DISTR D TIT E VAL M", "LECCA CREDITO FINANC.E INVESTI", "LEVYCAM - CORRET DE CAMBIO E V", "MAGLIANO S.A. COR.CAMB VLS MOBL", "MERCADOPAGO COM", "MONEY PLUS SOC DE CRED MICROEM", "MONEYCORP BANCO DE CAMBIO S.A.", "MS SOC DE CRED AO MICRO E EMP", "MSB BANK - BANCO DE CAMBIO", "NECTON INV SA CORRET DE VAL MO", "NEXT", "NOVA FUTURA CTVM LTDA", "NOVA PLATAFORMA DE COBRANÇA", "NOVO BANCO CONTINENTAL S.A.B - M", "NU PAGAMENTOS S.A.", "OLIVEIRA TRUST DIST TIT.VAL MO", "OM DIST DE TIT E VAL MOBILIARI", "OMNI BANCO S.A.", "ORAMA DIST DE TITULOS E VALORES", "OTIMO SOCIEDADE DE CREDITO DIR", "PAGSEGURO INTERNET S.A.", "PARANA BANCO S.A.", "PARATI-CREDITO FINANCIAMENTO E", "PARMETAL DISTRI. TIT. VAL. IMO", "PEFISA S.A. -CRED. FINANCIAMENT", "PI DIST. DE TIT. E VAL. MOBILI", "PLANNER CORRET. DE VALORES S.A.", "PLURAL S.A. BCO MULTIPLO", "POLOCRED SOC.CRED.MICROEMP.EMP", "PORTOPAR DIST. TIT. E VAL. MOB", "PORTOPCRED S.A. CRED.FINANC E IN", "QI SOCIEDADE DE CRÉDITO DIRETO", "RB CAPITAL INVESTIMENTOS DTVM", "REALIZE CRED. FINANC. E INVEST", "RENANSCENCA DIST.TIT.VAL.MOB.L.", "SAGITUR CORRETORA DE CAMBIO LT", "SCOTIABANK BRASIL S/A B.MULTIP", "SELECIONAR", "SENFF S.A. - CRED. FINANC. E IN", "SENSO CORR.CAMB.VAL.MOBILIARIO", "SERVICOOP-COOP DE CRED DOS SER", "SOC.DE CRED.AO MICROEMP.E EMP.", "SOCOPA SOCIEDADE CORRETORA PAU", "SOLIDUS S.A. C. CAMB. E VAL. MO", "SOROCRED CRED. FINAN. E INVESTI", "STATE STREET BRASIL S.A. - BCO", "STONE PAGAMENTOS S.A.", "SUPER PAGAMENTOS E ADMDE MEIOS", "TERRA INV. DIST. E TIT. E VAL", "TORO CORRETORA DE TIT E VALORES", "TRAVELEX BANCO DE CAMBIO S.A.", "TREVISO CORRETORA DE CAMBIO S/", "TRINUS CAPITAL DIST DE TIT E V", "UNICRED", "UNICRED CENTRAL RS-C.C.EC CRED", "UNS NRASIL CCTMV S.A.", "UP.P SOC DE EMPREST ENTRE PRESS", "VIP'S DE CORRETORA DE CAMBIO L", "VISION S.A. CORRETORA DE CAMBI", "VITREO DISTRIBUIDORA DE TIT E", "VORTX DIST. DE TIT. E VAL. MOB", "WARREN CORRET E VALORES IMOB", "XP INVEST.CORR.CAMP.VLS MOB.S.", "ZEMA CREDITO, FINANCIAMENTO IN" }));
        outputBank.setEnabled(false);
        getContentPane().add(outputBank);
        outputBank.setBounds(20, 580, 360, 30);

        outputSalary.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(outputSalary);
        outputSalary.setBounds(20, 510, 100, 30);

        outputCommission.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(outputCommission);
        outputCommission.setBounds(160, 510, 100, 30);

        outputFoodVoucher.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(outputFoodVoucher);
        outputFoodVoucher.setBounds(300, 510, 100, 30);

        outputMealTicket.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(outputMealTicket);
        outputMealTicket.setBounds(440, 510, 100, 30);

        buttonEdit.setText("EDITAR");
        buttonEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEditActionPerformed(evt);
            }
        });
        getContentPane().add(buttonEdit);
        buttonEdit.setBounds(20, 630, 100, 25);

        buttonLocale.setText("LOCALIZAR");
        buttonLocale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLocaleActionPerformed(evt);
            }
        });
        getContentPane().add(buttonLocale);
        buttonLocale.setBounds(180, 630, 100, 25);

        buttonTerminator.setText("DESLIGAMENTO");
        buttonTerminator.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTerminatorActionPerformed(evt);
            }
        });
        getContentPane().add(buttonTerminator);
        buttonTerminator.setBounds(340, 630, 130, 25);

        txtReasonDismissal.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtReasonDismissal.setText("Motivo da Demissão");
        getContentPane().add(txtReasonDismissal);
        txtReasonDismissal.setBounds(370, 410, 160, 20);

        outputReasonDismissal.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(outputReasonDismissal);
        outputReasonDismissal.setBounds(370, 440, 290, 30);

        txtStatus.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtStatus.setText("Status");
        getContentPane().add(txtStatus);
        txtStatus.setBounds(850, 340, 50, 20);

        outputStatus.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        outputStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ativo", "Inativo" }));
        getContentPane().add(outputStatus);
        outputStatus.setBounds(850, 370, 110, 30);

        jButton1.setText("COMISSÃO");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(540, 630, 110, 25);

        buttonPrinter.setText("IMPRIMIR");
        getContentPane().add(buttonPrinter);
        buttonPrinter.setBounds(730, 630, 90, 25);

        setSize(new java.awt.Dimension(1030, 702));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEditActionPerformed
        String[] id = this.getTitle().split(" ");
        NewEmployee newEmployee = new NewEmployee();
        newEmployee.txtNewEmployee.setText("ATUALIZAR FUNCIONÁRIO");
        newEmployee.setTitle("Atualizar Funcionário " + id[1]);  
        if(txtAdmissionDate.getText().equals("Data de Readmissão")){
            newEmployee.inputAdmissionDate.setText("Data de Readmissão");
        }
        this.dispose();
        newEmployee.setVisible(true);
    }//GEN-LAST:event_buttonEditActionPerformed

    private void outputPhotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_outputPhotoActionPerformed
        ImageScreen imageScreen = new ImageScreen();
        imageScreen.adress = imageAdress;
        imageScreen.setVisible(true);
    }//GEN-LAST:event_outputPhotoActionPerformed

    private void buttonLocaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLocaleActionPerformed
        LocaleEmployee localeEmployee = new LocaleEmployee();
        localeEmployee.setVisible(true);
    }//GEN-LAST:event_buttonLocaleActionPerformed

    private void buttonTerminatorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTerminatorActionPerformed
        EmployeeDismissal employeeDismissal = new EmployeeDismissal();
        if(!outputCPF.getText().equals("")){
            employeeDismissal.dismissalId = getId();
            String[] id = this.getTitle().split(" ");
            employeeDismissal.inputCodeEmployee.setText(id[1]);
            employeeDismissal.getEmployeeId();
        }
        employeeDismissal.setVisible(true);
    }//GEN-LAST:event_buttonTerminatorActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        CommissionScreen commissionScreen = new CommissionScreen();
        commissionScreen.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        if(x==0){
            x++;
            setProduct();
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
            java.util.logging.Logger.getLogger(EmployeeScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EmployeeScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EmployeeScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EmployeeScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EmployeeScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonEdit;
    private javax.swing.JButton buttonLocale;
    private javax.swing.JButton buttonPrinter;
    private javax.swing.JButton buttonTerminator;
    private javax.swing.JButton jButton1;
    private javax.swing.JTextField outputAccount;
    private javax.swing.JComboBox<String> outputAccountType;
    private javax.swing.JTextField outputAdmissionDate;
    private javax.swing.JTextField outputAgency;
    private javax.swing.JComboBox<String> outputBank;
    private javax.swing.JTextField outputBirthDay;
    private javax.swing.JTextField outputCEP;
    private javax.swing.JTextField outputCPF;
    private javax.swing.JTextField outputCellPhone;
    private javax.swing.JTextField outputCity;
    private javax.swing.JTextField outputCommission;
    private javax.swing.JTextField outputComplement;
    private javax.swing.JTextField outputDismissalDate;
    private javax.swing.JTextField outputEmail;
    private javax.swing.JTextField outputFatherName;
    private javax.swing.JTextField outputFoodVoucher;
    private javax.swing.JTextField outputFullName;
    private javax.swing.JTextField outputMealTicket;
    private javax.swing.JTextField outputMotherName;
    private javax.swing.JTextField outputNeighBorhood;
    private javax.swing.JTextField outputNumberHouse;
    private javax.swing.JTextField outputOccupation;
    private javax.swing.JTextField outputPIS;
    private javax.swing.JTextField outputPhone;
    public static javax.swing.JButton outputPhoto;
    private javax.swing.JTextField outputRG;
    private javax.swing.JTextField outputReasonDismissal;
    private javax.swing.JTextField outputSalary;
    private javax.swing.JComboBox<String> outputSex;
    private javax.swing.JComboBox<String> outputState;
    private javax.swing.JComboBox<String> outputStatus;
    private javax.swing.JTextField outputStreet;
    private javax.swing.JTextField outputTranportationVoucher;
    private javax.swing.JLabel txtAccount;
    private javax.swing.JLabel txtAccountType;
    private javax.swing.JLabel txtAdmissionDate;
    private javax.swing.JLabel txtAgency;
    private javax.swing.JLabel txtBank;
    private javax.swing.JLabel txtBirthDay;
    private javax.swing.JLabel txtCEP;
    private javax.swing.JLabel txtCPF;
    private javax.swing.JLabel txtCellPhone;
    private javax.swing.JLabel txtCity;
    private javax.swing.JLabel txtCommission;
    private javax.swing.JLabel txtComplement;
    private javax.swing.JLabel txtDismissalDate;
    private javax.swing.JLabel txtEmail;
    private javax.swing.JLabel txtEmployee;
    private javax.swing.JLabel txtFatherName;
    private javax.swing.JLabel txtFoodVoucher;
    private javax.swing.JLabel txtFullName;
    private javax.swing.JLabel txtMealTicket;
    private javax.swing.JLabel txtMotherName;
    private javax.swing.JLabel txtNeighBorhood;
    private javax.swing.JLabel txtNumberHouse;
    private javax.swing.JLabel txtOccupation;
    private javax.swing.JLabel txtPIS;
    private javax.swing.JLabel txtPhone;
    private javax.swing.JLabel txtRG;
    private javax.swing.JLabel txtReasonDismissal;
    private javax.swing.JLabel txtSalary;
    private javax.swing.JLabel txtSex;
    private javax.swing.JLabel txtState;
    private javax.swing.JLabel txtStatus;
    private javax.swing.JLabel txtStreet;
    private javax.swing.JLabel txtTranportationVoucher;
    // End of variables declaration//GEN-END:variables
}
