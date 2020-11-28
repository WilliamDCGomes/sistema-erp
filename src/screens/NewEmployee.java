package screens;
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
import functioncontroller.GetImageAdress;
import functioncontroller.GetJustTheNumbers;
import functioncontroller.SearchCEP;
import functioncontroller.SearchCEPException;
import formattingmask.MaskUpperLetter;
import formattingmask.MaskUpperLetterAux;
import functioncontroller.ValidateCNPJ;
import functioncontroller.ValidateCPF;
import java.awt.Image;
import javax.swing.ImageIcon;

public class NewEmployee extends javax.swing.JFrame {
    Connection connection = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    PreparedStatement pst2 = null;
    ResultSet rs2 = null;
    PreparedStatement pst3 = null;
    ResultSet rs3 = null;
    PreparedStatement pst4 = null;
    ResultSet rs4 = null;
    String imageAdress = null;
    GetImageAdress getImageAdress = new GetImageAdress();
    int x = 0;
    boolean cpfValide = false;
    boolean cnpjValide = false;
    String safeCPF = null;
    public NewEmployee() {
        initComponents();
        ConnectionModule connect = new ConnectionModule();
        connection = connect.getConnectionMySQL();
        inputFullName.setDocument(new MaskUpperLetter());
        inputBirthDay.setDocument(new MaskDate());
        inputRG.setDocument(new MaskRG());
        inputCPF.setDocument(new MaskCPFAndCNPJ());
        inputPhone.setDocument(new MaskPhone());
        inputCellPhone.setDocument(new MaskPhone());
        inputEmail.setDocument(new MaskUpperLetter());
        inputFatherName.setDocument(new MaskUpperLetter());
        inputMotherName.setDocument(new MaskUpperLetter());
        inputCEP.setDocument(new MaskCepAndHouseNumber());
        inputStreet.setDocument(new MaskUpperLetter());
        inputNumberHouse.setDocument(new MaskCepAndHouseNumber());
        inputNeighBorhood.setDocument(new MaskUpperLetter());
        inputCity.setDocument(new MaskUpperLetter());
        inputComplement.setDocument(new MaskUpperLetter());
        inputAdmissionDate.setDocument(new MaskDate());
        inputOccupation.setDocument(new MaskUpperLetter());
        inputSalary.setDocument(new MaskCash());
        inputCommission.setDocument(new MaskJustNumbers());
        inputFoodVoucher.setDocument(new MaskCash());
        inputMealTicket.setDocument(new MaskCash());
        inputTranportationVoucher.setDocument(new MaskCash());
        inputPIS.setDocument(new MaskCPFAndCNPJ());
        inputAgency.setDocument(new MaskCPFAndCNPJ());
        inputAccount.setDocument(new MaskCPFAndCNPJ());
    }
    private void add(){
        String sql = "insert into employee(nameEmployee, birthday, rg, cpf, sexo, phone, cellPhone, email, fatherName, motherName, cep, street, houseNumber, neighborhood, city, state, complement, admissionDate, functionEmployee, salary, commission, foodVoucher, mealTicket, transportationVouchers, pisAndPasep, bank, agency, accountBank, bankType, photoAdress)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1,inputFullName.getText());
            pst.setString(2,inputBirthDay.getText());
            pst.setString(3,inputRG.getText());
            pst.setString(4,inputCPF.getText());
            pst.setString(5,inputSex.getSelectedItem().toString());
            pst.setString(6,inputPhone.getText());
            pst.setString(7,inputCellPhone.getText());
            pst.setString(8,inputEmail.getText());
            pst.setString(9,inputFatherName.getText());
            pst.setString(10,inputMotherName.getText());
            pst.setString(11,inputCEP.getText());
            pst.setString(12,inputStreet.getText());
            pst.setString(13,inputNumberHouse.getText());
            pst.setString(14,inputNeighBorhood.getText());
            pst.setString(15,inputCity.getText());
            pst.setString(16,inputState.getSelectedItem().toString());
            pst.setString(17,inputComplement.getText());
            pst.setString(18,inputAdmissionDate.getText());
            pst.setString(19,inputOccupation.getText());
            pst.setString(20,inputSalary.getText());
            pst.setString(21,inputCommission.getText());
            pst.setString(22,inputFoodVoucher.getText());
            pst.setString(23,inputMealTicket.getText());
            pst.setString(24,inputTranportationVoucher.getText());
            pst.setString(25,inputPIS.getText());
            pst.setString(26,inputBank.getSelectedItem().toString());
            pst.setString(27,inputAgency.getText());
            pst.setString(28,inputAccount.getText());
            pst.setString(29,inputAccountType.getSelectedItem().toString());
            pst.setString(30,imageAdress);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null,"FUNCIONÁRIO CADASTRADO COM SUCESSO");
            EmployeeScreen employeeScreen = new EmployeeScreen();
            this.dispose();
            employeeScreen.setTitle("Funcionário: " + getId());
            employeeScreen.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void update(){
        String sql = "update employee set nameEmployee=?, birthday=?, rg=?, cpf=?, sexo=?, phone=?, cellPhone=?, email=?, fatherName=?, motherName=?, cep=?, street=?, houseNumber=?, neighborhood=?, city=?, state=?, complement=?, admissionDate=?, functionEmployee=?, salary=?, commission=?, foodVoucher=?, mealTicket=?, transportationVouchers=?, pisAndPasep=?, bank=?, agency=?, accountBank=?, bankType=?, photoAdress=? where cpf=?";
        try {
            pst=connection.prepareStatement(sql);
            pst.setString(1,inputFullName.getText());
            pst.setString(2,inputBirthDay.getText());
            pst.setString(3,inputRG.getText());
            pst.setString(4,inputCPF.getText());
            pst.setString(5,inputSex.getSelectedItem().toString());
            pst.setString(6,inputPhone.getText());
            pst.setString(7,inputCellPhone.getText());
            pst.setString(8,inputEmail.getText());
            pst.setString(9,inputFatherName.getText());
            pst.setString(10,inputMotherName.getText());
            pst.setString(11,inputCEP.getText());
            pst.setString(12,inputStreet.getText());
            pst.setString(13,inputNumberHouse.getText());
            pst.setString(14,inputNeighBorhood.getText());
            pst.setString(15,inputCity.getText());
            pst.setString(16,inputState.getSelectedItem().toString());
            pst.setString(17,inputComplement.getText());
            pst.setString(18,inputAdmissionDate.getText());
            pst.setString(19,inputOccupation.getText());
            pst.setString(20,inputSalary.getText());
            pst.setString(21,inputCommission.getText());
            pst.setString(22,inputFoodVoucher.getText());
            pst.setString(23,inputMealTicket.getText());
            pst.setString(24,inputTranportationVoucher.getText());
            pst.setString(25,inputPIS.getText());
            pst.setString(26,inputBank.getSelectedItem().toString());
            pst.setString(27,inputAgency.getText());
            pst.setString(28,inputAccount.getText());
            pst.setString(29,inputAccountType.getSelectedItem().toString());
            pst.setString(30,imageAdress);
            pst.setString(31,safeCPF);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null,"FUNCIONÁRIO ATUALIZADO COM SUCESSO");
            EmployeeScreen employeeScreen = new EmployeeScreen();
            this.dispose();
            employeeScreen.setTitle("Funcionário: " + getId());
            employeeScreen.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }
    private void updateReadmited1(){
        String sql = "update employee set nameEmployee=?, birthday=?, rg=?, cpf=?, sexo=?, phone=?, cellPhone=?, email=?, fatherName=?, motherName=?, cep=?, street=?, houseNumber=?, neighborhood=?, city=?, state=?, complement=?, photoAdress=? where cpf=?";
        try {
            pst=connection.prepareStatement(sql);
            pst.setString(1,inputFullName.getText());
            pst.setString(2,inputBirthDay.getText());
            pst.setString(3,inputRG.getText());
            pst.setString(4,inputCPF.getText());
            pst.setString(5,inputSex.getSelectedItem().toString());
            pst.setString(6,inputPhone.getText());
            pst.setString(7,inputCellPhone.getText());
            pst.setString(8,inputEmail.getText());
            pst.setString(9,inputFatherName.getText());
            pst.setString(10,inputMotherName.getText());
            pst.setString(11,inputCEP.getText());
            pst.setString(12,inputStreet.getText());
            pst.setString(13,inputNumberHouse.getText());
            pst.setString(14,inputNeighBorhood.getText());
            pst.setString(15,inputCity.getText());
            pst.setString(16,inputState.getSelectedItem().toString());
            pst.setString(17,inputComplement.getText());
            pst.setString(18,imageAdress);
            pst.setString(19,safeCPF);
            pst.executeUpdate();
            updateReadmited2();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }
    private int getMaxId(){
        String sql ="select max(id) from readmissionEmployee where cpf=?";
        try {
            pst3=connection.prepareStatement(sql);
            pst3.setString(1, safeCPF);
            rs3= pst3.executeQuery();
            if(rs3.next()){
                return rs3.getInt(1);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return 0;
    }
    private void updateReadmited2(){
        String sql = "update readmissionEmployee set cpf=?, readmissionDate=?, functionEmployee=?, salary=?, commission=?, foodVoucher=?, mealTicket=?, transportationVouchers=?, pisAndPasep=?, bank=?, agency=?, accountBank=?, bankType=? where id=?";
        try {
            pst2=connection.prepareStatement(sql);
            pst2.setString(1,inputCPF.getText());
            pst2.setString(2,inputAdmissionDate.getText());
            pst2.setString(3,inputOccupation.getText());
            pst2.setString(4,inputSalary.getText());
            pst2.setString(5,inputCommission.getText());
            pst2.setString(6,inputFoodVoucher.getText());
            pst2.setString(7,inputMealTicket.getText());
            pst2.setString(8,inputTranportationVoucher.getText());
            pst2.setString(9,inputPIS.getText());
            pst2.setString(10,inputBank.getSelectedItem().toString());
            pst2.setString(11,inputAgency.getText());
            pst2.setString(12,inputAccount.getText());
            pst2.setString(13,inputAccountType.getSelectedItem().toString());
            pst2.setInt(14,getMaxId());
            pst2.executeUpdate();
            updateDismissal();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }
    private void updateDismissal(){
        String sql = "update dismissalEmployee set cpf=? where cpf=?";
        try {
            pst3=connection.prepareStatement(sql);
            pst3.setString(1,inputCPF.getText());
            pst3.setString(2,safeCPF);
            pst3.executeUpdate();
            updateReadmissal();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }
    private void updateReadmissal(){
        String sql = "update readmissionEmployee set cpf=? where cpf=?";
        try {
            pst4=connection.prepareStatement(sql);
            pst4.setString(1,inputCPF.getText());
            pst4.setString(2,safeCPF);
            pst4.executeUpdate();
            JOptionPane.showMessageDialog(null,"FUNCIONÁRIO ATUALIZADO COM SUCESSO");
            EmployeeScreen employeeScreen = new EmployeeScreen();
            this.dispose();
            employeeScreen.setTitle("Funcionário: " + getId());
            employeeScreen.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }
    private void delete(){
        int confirma = JOptionPane.showConfirmDialog(null, "TEM CERTEZA QUE DESEJA DELETAR ESSE FUNCIONÁRIO?","ATENÇÃO",JOptionPane.YES_NO_OPTION);
        if(confirma==JOptionPane.YES_OPTION){
            String sql = "delete from employee where cpf = ?";
            try {
                pst=connection.prepareStatement(sql);
                pst.setString(1, inputCPF.getText());
                int deleted = pst.executeUpdate();
                if(deleted>0){
                    JOptionPane.showMessageDialog(null,"FUNCIONÁRIO DELETADO COM SUCESSO");
                    deleteDismissal();
                    this.dispose();
                }
                else{
                    JOptionPane.showMessageDialog(null,"FUNCIONÁRIO NÃO CADASTRADO NO BANCO DE DADOS");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }
    private void deleteDismissal(){
        String sql = "delete from dismissalEmployee where cpf = ?";
        try {
            pst2=connection.prepareStatement(sql);
            pst2.setString(1, safeCPF);
            pst2.executeUpdate();
            deleteReadmissal();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void deleteReadmissal(){
        String sql = "delete from readmissionEmployee where cpf = ?";
        try {
            pst3=connection.prepareStatement(sql);
            pst3.setString(1, safeCPF);
            pst3.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
    private void setEmployee(){
        String[] id = this.getTitle().split(" ");
        String sql ="select nameEmployee, birthday, rg, cpf, sexo, phone, cellPhone, email, fatherName, motherName, cep, street, houseNumber, neighborhood, city, state, complement, admissionDate, functionEmployee, salary, commission, foodVoucher, mealTicket, transportationVouchers, pisAndPasep, bank, agency, accountBank, bankType, photoAdress, readmissionEmployee from employee where id=?";
        try {
            pst=connection.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt( id[2] ));
            rs= pst.executeQuery();
            if(rs.next()){
                inputFullName.setText(rs.getString(1));
                inputBirthDay.setText(rs.getString(2));
                inputRG.setText(rs.getString(3));
                inputCPF.setText(rs.getString(4));
                inputSex.setSelectedItem(rs.getString(5));
                inputPhone.setText(rs.getString(6));
                inputCellPhone.setText(rs.getString(7));
                inputEmail.setText(rs.getString(8));
                inputFatherName.setText(rs.getString(9));
                inputMotherName.setText(rs.getString(10));
                inputCEP.setText(rs.getString(11));
                inputStreet.setText(rs.getString(12));
                inputNumberHouse.setText(rs.getString(13));
                inputNeighBorhood.setText(rs.getString(14));
                inputCity.setText(rs.getString(15));
                inputState.setSelectedItem(rs.getString(16));
                inputComplement.setText(rs.getString(17));
                if(rs.getString(31).equals("Não")){
                    inputAdmissionDate.setText(rs.getString(18));
                    inputOccupation.setText(rs.getString(19));
                    inputSalary.setText(rs.getString(20));
                    inputCommission.setText(rs.getString(21));
                    inputFoodVoucher.setText(rs.getString(22));
                    inputMealTicket.setText(rs.getString(23));
                    inputTranportationVoucher.setText(rs.getString(24));
                    inputPIS.setText(rs.getString(25));
                    inputBank.setSelectedItem(rs.getString(26));
                    inputAgency.setText(rs.getString(27));
                    inputAccount.setText(rs.getString(28));
                    inputAccountType.setSelectedItem(rs.getString(29));
                }
                else if(rs.getString(31).equals("Sim")){
                    setReadmissal();
                }
                if(rs.getString(30)!=null){
                    inputPhoto.setText("");
                    imageAdress = rs.getString(30);
                    ImageIcon imagen = new ImageIcon(imageAdress);
                    inputPhoto.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(inputPhoto.getWidth(), inputPhoto.getHeight(), Image.SCALE_DEFAULT)));
                }
                cnpjValide = true;
                cpfValide = true;
                safeCPF = inputCPF.getText();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void setReadmissal(){
        txtAdmissionDate.setText("Data de Readmissão");
        String sql ="select readmissionDate, functionEmployee, salary, commission, foodVoucher, mealTicket, transportationVouchers, pisAndPasep, bank, agency, accountBank, bankType from readmissionEmployee where id=(select max(id) from readmissionEmployee where cpf = ?)";
        try {
            pst2=connection.prepareStatement(sql);
            pst2.setString(1, inputCPF.getText());
            rs2= pst2.executeQuery();
            if(rs2.next()){
                inputAdmissionDate.setText(rs2.getString(1));
                inputOccupation.setText(rs2.getString(2));
                inputSalary.setText(rs2.getString(3));
                inputCommission.setText(rs2.getString(4));
                inputFoodVoucher.setText(rs2.getString(5));
                inputMealTicket.setText(rs2.getString(6));
                inputTranportationVoucher.setText(rs2.getString(7));
                inputPIS.setText(rs2.getString(8));
                inputBank.setSelectedItem(rs2.getString(9));
                inputAgency.setText(rs2.getString(10));
                inputAccount.setText(rs2.getString(11));
                inputAccountType.setSelectedItem(rs2.getString(12));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private int getId(){
        String sql ="select id from employee where cpf=?";
        try {
            pst2=connection.prepareStatement(sql);
            pst2.setString(1, inputCPF.getText());
            rs2= pst2.executeQuery();
            if(rs2.next()){
                return rs2.getInt(1);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return 1;
    }
    private boolean checkIfExist(){
        String sql ="select id from employee where cpf=? or rg=?";
        try {
            pst2=connection.prepareStatement(sql);
            pst2.setString(1, inputCPF.getText());
            pst2.setString(2, inputRG.getText());
            rs2= pst2.executeQuery();
            if(rs2.next()){
                return true;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return false;
    }
    private String comp(){
        String[] id = this.getTitle().split(" ");
        String sql ="select rg, cpf from employee where id=?";
        try {
            pst2=connection.prepareStatement(sql);
            pst2.setInt(1, Integer.parseInt( id[2] ));
            rs2= pst2.executeQuery();
            if(rs2.next()){
                return rs2.getString(1) + ";" + rs2.getString(2);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }
    private boolean checkRG(){
        String sql ="select id from employee where rg=?";
        try {
            pst2=connection.prepareStatement(sql);
            pst2.setString(1, inputRG.getText());
            rs2= pst2.executeQuery();
            if(rs2.next()){
                return true;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return false;
    }
    private boolean checkCPF(){
        String sql ="select id from employee where cpf=?";
        try {
            pst2=connection.prepareStatement(sql);
            pst2.setString(1, inputCPF.getText());
            rs2= pst2.executeQuery();
            if(rs2.next()){
                return true;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return false;
    }
    private void setInformations(){
        MaskUpperLetterAux upperLetterAux = new MaskUpperLetterAux();
        SearchCEP searchCEP = new SearchCEP();
        try {
            searchCEP.buscar(inputCEP.getText());
            String neighBorhood = "";
            String street = "";
            String city = "";
            String neighBorhoodAux = searchCEP.getBairro();
            String streetAux = searchCEP.getLogradouro();
            String cityAux = searchCEP.getLocalidade();
            for(int i=0;i<neighBorhoodAux.length();i++){
                if(i==neighBorhoodAux.length()-1){
                    neighBorhood += upperLetterAux.makeUp(neighBorhoodAux.substring(i));
                    break;
                }
                neighBorhood += upperLetterAux.makeUp(neighBorhoodAux.substring(i, i + 1));
            }
            for(int i=0;i<streetAux.length();i++){
                if(i==streetAux.length()-1){
                    street += upperLetterAux.makeUp(streetAux.substring(i));
                    break;
                }
                street += upperLetterAux.makeUp(streetAux.substring(i, i + 1));
            }
            for(int i=0;i<cityAux.length();i++){
                if(i==cityAux.length()-1){
                    city += upperLetterAux.makeUp(cityAux.substring(i));
                    break;
                }
                city += upperLetterAux.makeUp(cityAux.substring(i, i + 1));
            }
            inputNeighBorhood.setText(neighBorhood);
            inputStreet.setText(street);
            inputCity.setText(city);
            inputState.setSelectedItem(searchCEP.getUf());
            inputNumberHouse.requestFocus();
        } catch (SearchCEPException ex) {
            JOptionPane.showMessageDialog(null, "NÃO FOI POSSÍVEL ENCONTRAR O CEP");
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtNewEmployee = new javax.swing.JLabel();
        txtFullName = new javax.swing.JLabel();
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
        inputPhoto = new javax.swing.JButton();
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
        inputEmail = new javax.swing.JTextField();
        inputCPF = new javax.swing.JTextField();
        inputCellPhone = new javax.swing.JTextField();
        inputSex = new javax.swing.JComboBox<>();
        inputCEP = new javax.swing.JTextField();
        inputPhone = new javax.swing.JTextField();
        inputMotherName = new javax.swing.JTextField();
        inputFullName = new javax.swing.JTextField();
        inputComplement = new javax.swing.JTextField();
        inputRG = new javax.swing.JTextField();
        inputFatherName = new javax.swing.JTextField();
        inputBirthDay = new javax.swing.JTextField();
        inputOccupation = new javax.swing.JTextField();
        inputStreet = new javax.swing.JTextField();
        inputNeighBorhood = new javax.swing.JTextField();
        inputAccountType = new javax.swing.JComboBox<>();
        inputAdmissionDate = new javax.swing.JTextField();
        inputPIS = new javax.swing.JTextField();
        inputNumberHouse = new javax.swing.JTextField();
        inputAgency = new javax.swing.JTextField();
        inputAccount = new javax.swing.JTextField();
        inputState = new javax.swing.JComboBox<>();
        inputTranportationVoucher = new javax.swing.JTextField();
        inputCity = new javax.swing.JTextField();
        inputBank = new javax.swing.JComboBox<>();
        inputSalary = new javax.swing.JTextField();
        inputCommission = new javax.swing.JTextField();
        inputFoodVoucher = new javax.swing.JTextField();
        inputMealTicket = new javax.swing.JTextField();
        buttonSave = new javax.swing.JButton();
        txtRequiredField2 = new javax.swing.JLabel();
        txtRequiredField3 = new javax.swing.JLabel();
        txtRequiredField4 = new javax.swing.JLabel();
        txtRequiredField5 = new javax.swing.JLabel();
        txtRequiredField6 = new javax.swing.JLabel();
        txtRequiredField7 = new javax.swing.JLabel();
        txtRequiredField8 = new javax.swing.JLabel();
        txtRequiredField9 = new javax.swing.JLabel();
        txtRequiredField10 = new javax.swing.JLabel();
        txtRequiredField11 = new javax.swing.JLabel();
        txtRequiredField12 = new javax.swing.JLabel();
        buttonCancel = new javax.swing.JButton();
        txtPercent = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Novo Funcionário");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(null);

        txtNewEmployee.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        txtNewEmployee.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtNewEmployee.setText("NOVO FUNCIONÁRIO");
        getContentPane().add(txtNewEmployee);
        txtNewEmployee.setBounds(230, 10, 590, 32);

        txtFullName.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtFullName.setText("Nome Completo");
        getContentPane().add(txtFullName);
        txtFullName.setBounds(20, 60, 120, 20);

        txtBirthDay.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtBirthDay.setText("Data de Nascimento");
        getContentPane().add(txtBirthDay);
        txtBirthDay.setBounds(380, 60, 150, 20);

        txtRG.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtRG.setText("RG");
        getContentPane().add(txtRG);
        txtRG.setBounds(560, 60, 40, 20);

        txtCPF.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtCPF.setText("CPF");
        getContentPane().add(txtCPF);
        txtCPF.setBounds(700, 60, 50, 20);

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
        txtNumberHouse.setBounds(570, 270, 60, 20);

        txtNeighBorhood.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtNeighBorhood.setText("Bairro");
        getContentPane().add(txtNeighBorhood);
        txtNeighBorhood.setBounds(680, 270, 50, 20);

        txtCity.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtCity.setText("Cidade");
        getContentPane().add(txtCity);
        txtCity.setBounds(20, 340, 60, 20);

        txtState.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtState.setText("Estado");
        getContentPane().add(txtState);
        txtState.setBounds(300, 340, 70, 20);

        txtComplement.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtComplement.setText("Complemento");
        getContentPane().add(txtComplement);
        txtComplement.setBounds(490, 340, 110, 20);

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
        txtOccupation.setBounds(190, 410, 70, 20);

        txtCommission.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtCommission.setText("Comissão");
        getContentPane().add(txtCommission);
        txtCommission.setBounds(690, 410, 80, 20);

        inputPhoto.setText("FOTO");
        inputPhoto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        inputPhoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputPhotoActionPerformed(evt);
            }
        });
        getContentPane().add(inputPhoto);
        inputPhoto.setBounds(850, 60, 150, 210);

        txtSalary.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtSalary.setText("Remuneração");
        getContentPane().add(txtSalary);
        txtSalary.setBounds(530, 410, 110, 20);

        txtFoodVoucher.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtFoodVoucher.setText("Vale Alimentação");
        getContentPane().add(txtFoodVoucher);
        txtFoodVoucher.setBounds(850, 410, 130, 20);

        txtTranportationVoucher.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtTranportationVoucher.setText("Vale Transporte");
        getContentPane().add(txtTranportationVoucher);
        txtTranportationVoucher.setBounds(170, 480, 130, 20);

        txtMealTicket.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtMealTicket.setText("Vale Refeição");
        getContentPane().add(txtMealTicket);
        txtMealTicket.setBounds(20, 480, 110, 20);

        txtPIS.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtPIS.setText("PIS/PASEP");
        getContentPane().add(txtPIS);
        txtPIS.setBounds(340, 480, 90, 20);

        txtBank.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtBank.setText("Banco");
        getContentPane().add(txtBank);
        txtBank.setBounds(610, 480, 60, 20);

        txtAgency.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtAgency.setText("Agência");
        getContentPane().add(txtAgency);
        txtAgency.setBounds(20, 550, 70, 20);

        txtAccount.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtAccount.setText("Conta");
        getContentPane().add(txtAccount);
        txtAccount.setBounds(180, 550, 50, 20);

        txtAccountType.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtAccountType.setText("Tipo de Conta");
        getContentPane().add(txtAccountType);
        txtAccountType.setBounds(480, 550, 110, 20);

        txtFatherName.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtFatherName.setText("Nome do Pai");
        getContentPane().add(txtFatherName);
        txtFatherName.setBounds(440, 200, 100, 20);

        txtMotherName.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtMotherName.setText("Nome da Mãe");
        getContentPane().add(txtMotherName);
        txtMotherName.setBounds(20, 200, 110, 20);

        inputEmail.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(inputEmail);
        inputEmail.setBounds(490, 160, 340, 30);

        inputCPF.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        inputCPF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                inputCPFFocusLost(evt);
            }
        });
        inputCPF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputCPFKeyPressed(evt);
            }
        });
        getContentPane().add(inputCPF);
        inputCPF.setBounds(700, 90, 130, 30);

        inputCellPhone.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(inputCellPhone);
        inputCellPhone.setBounds(330, 160, 130, 30);

        inputSex.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        inputSex.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Masculino", "Feminino" }));
        getContentPane().add(inputSex);
        inputSex.setBounds(20, 160, 130, 30);

        inputCEP.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        inputCEP.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                inputCEPFocusLost(evt);
            }
        });
        inputCEP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputCEPKeyPressed(evt);
            }
        });
        getContentPane().add(inputCEP);
        inputCEP.setBounds(20, 300, 100, 30);

        inputPhone.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(inputPhone);
        inputPhone.setBounds(170, 160, 130, 30);

        inputMotherName.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(inputMotherName);
        inputMotherName.setBounds(20, 230, 340, 30);

        inputFullName.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(inputFullName);
        inputFullName.setBounds(20, 90, 330, 30);

        inputComplement.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(inputComplement);
        inputComplement.setBounds(490, 370, 460, 30);

        inputRG.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(inputRG);
        inputRG.setBounds(560, 90, 110, 30);

        inputFatherName.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(inputFatherName);
        inputFatherName.setBounds(440, 230, 330, 30);

        inputBirthDay.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(inputBirthDay);
        inputBirthDay.setBounds(380, 90, 110, 30);

        inputOccupation.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(inputOccupation);
        inputOccupation.setBounds(190, 440, 290, 30);

        inputStreet.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(inputStreet);
        inputStreet.setBounds(150, 300, 390, 30);

        inputNeighBorhood.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(inputNeighBorhood);
        inputNeighBorhood.setBounds(680, 300, 320, 30);

        inputAccountType.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        inputAccountType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECIONAR", "CONTA CORRENTE", "POUPANÇA", "CONTA SALÁRIO" }));
        getContentPane().add(inputAccountType);
        inputAccountType.setBounds(480, 580, 270, 30);

        inputAdmissionDate.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(inputAdmissionDate);
        inputAdmissionDate.setBounds(20, 440, 110, 30);

        inputPIS.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(inputPIS);
        inputPIS.setBounds(340, 510, 220, 30);

        inputNumberHouse.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(inputNumberHouse);
        inputNumberHouse.setBounds(570, 300, 80, 30);

        inputAgency.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(inputAgency);
        inputAgency.setBounds(20, 580, 110, 30);

        inputAccount.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(inputAccount);
        inputAccount.setBounds(180, 580, 270, 30);

        inputState.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        inputState.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECIONAR", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
        getContentPane().add(inputState);
        inputState.setBounds(300, 370, 140, 30);

        inputTranportationVoucher.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(inputTranportationVoucher);
        inputTranportationVoucher.setBounds(170, 510, 100, 30);

        inputCity.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(inputCity);
        inputCity.setBounds(20, 370, 240, 30);

        inputBank.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        inputBank.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECIONAR", "ACESSO SOLUCOES DE PAGAMENTO S", "ADVANCED CORRET.CAMBIO LTDA", "AGK CORRETORA DE CAMBIO S.A.", "AL5 S.A.CRED. FINANC. E INVEST", "AMAZONIA CORRETORA DE CAMBIO L", "ATIVA INVESTIMENTOS S.A.", "AVISTA S.A. CREDITO.FINAN. E", "B.OF A.MERRILL LYNCH B.MULT.S.", "B&T ASSOCIADOS CORRETORA DE CA", "BANCO ABC-BRASIL S.A.", "BANCO ABN AMRO S.A.", "BANCO AGIBANK S.A.", "BANCO ALFA S.A.", "BANCO ALVORADA S.A.", "BANCO ANDBANK BRASIL S.A.", "BANCO ARBI S.A.", "BANCO B3 S.A.", "BANCO BARI INVESTIMENO E FINA", "BANCO BMG S.A.", "BANCO BNP PARIBAS BRASIL S.A.", "BANCO BOCOM BBM S.A.", "BANCO BRADESCARD S.A.", "BANCO BRADESCO BBI S.A.", "BANCO BRADESCO BERJ S.A.", "BANCO BRADESCO S.A.", "BANCO BS2 S.A.", "BANCO BTG PACTUAL S.A.", "BANCO C6 CONSIGNADO S.A.", "BANCO C6 S.A.", "BANCO CAIXA GERAL - BRASIL S.A.", "BANCO CAPITAL S.A.", "BANCO CARGILL S.A", "BANCO CEDULA S.A.", "BANCO CETELEM S.A.", "BANCO CIFRA S.A.", "BANCO CITIBANK S.A.", "BANCO CLASSICO S.A.", "BANCO CM CAPITAL MARKETS CCTVM", "BANCO COOPERATIOVO SICREDI S.A.", "BANCO COOPERATIVO DO BRASIL S.A.", "BANCO CREDIT AGRICOLE BRASIL S.A.", "BANCO CREDIT SUISSE(BRASIL) S", "BANCO CREFISA S.A.", "BANCO CSF S.A.", "BANCO DA AMAZONIA S.A.", "BANCO DA CHINA BRASIL S.A.", "BANCO DAYCOVAL S.A.", "BANCO DE LA NACION ARGENTINA", "BANCO DE LA PROV. DE BUENOS AL", "BANCO DE PERNAMBUCO S.A. - BANDE", "BANCO DIGIMAIS S.A.", "BANCO DIGIO S.A.", "BANCO DO BRASIL S.A.", "BANCO DO EST. DE SERGIPE S.A.", "BANCO DO EST. DO PARA S.A.", "BANCO DO NORDESTE DO BRASIL S.A.", "BANCO FATOR S.A.", "BANCO FIBRA S.A.", "BANCO FINAXIS S.A.", "BANCO GM S.A.", "BANCO GUANABARA S.A.", "BANCO HSBC S.A.", "BANCO INBURSA S.A.", "BANCO INDUSTRIAL DO BRASIL S.A.", "BANCO INDUSVAL S.A.", "BANCO INTER S.A.", "BANCO INVESTCRED UNIBANCO S.A.", "BANCO ITAU BBA S.A.", "BANCO ITAU HOLDING FINANCEIRA", "BANCO ITAUBANK S.A.", "BANCO J. P. MORGAN S.A.", "BANCO J. SAFRA S.A.", "BANCO JOHN DEERE S.A.", "BANCO KEB HANA DO BRASIL S.A.", "BANCO LUSO BRASILEIRO S.A.", "BANCO MAXIMA S.A.", "BANCO MECANTIL DO BRASIL S.A.", "BANCO MERCEDES-BENZ DO BRASIL", "BANCO MIZUHO DO BRASIL S.A.", "BANCO MODAL S.A.", "BANCO MORGAN STANLEY S.A.", "BANCO MUFG BRASIL S.A.", "BANCO NAC.DESENV.ECON. SOCIAL", "BANCO OLE CONSIGNADO S.A.", "BANCO ORIGINAL DO AGRONEGOCIO", "BANCO ORIGINAL S.A.", "BANCO OURINVEST S.A.", "BANCO PAN S.A.", "BANCO PAULISTA S.A.", "BANCO PINA. S.A.", "BANCO RADOBANK INTERNATIONAL B", "BANCO RANDON S.A.", "BANCO RENDIMENTO S.A.", "BANCO RIBEIRAO PRETO S.A.", "BANCO RODOBENS S.A.", "BANCO SAFRA S.A.", "BANCO SANTANDER (BRASIL) S.A.", "BANCO SC TULLET PREBON", "BANCO SEMEAR S.A.", "BANCO SISTEMA S.A.", "BANCO SMARTBANK S.A.", "BANCO SOCIETE GENERALE BRASIL", "BANCO SOFISA S.A.", "BANCO SUMITOMO MITSUI BRASILEI", "BANCO TOPAZIO S.A.", "BANCO TOYOTA DO BRASIL S.A.", "BANCO TRIANGULO S.A.", "BANCO TRICURY S.A.", "BANCO UNICO S.A.", "BANCO VOLKSWAGEN S.A.", "BANCO VOTORANTIM S.A.", "BANCO VR S.A.", "BANCO WESTERN UNION DO BRASIL", "BANCO WOORI BANK DO BRASIL S.A.", "BANCO XP S.A.", "BANCOSEGURO S.A.", "BANESTES S.A. BCO.EST.ESPIRITO", "BARI COMPANHIA HIPOTECARIA", "BBC LEASING S.A. - ARRENDAMENTO", "BCG LIQUIDEZ DISTR TITS VLRS M", "BCO BRADESCO FINANCIAMENTOS S.A.", "BCO.EST.R.GRANDE DO SUL S.A.", "BCO.ITAU BMG CONSIGNADO S.A.", "BCV - BANCO DE CRÉDITO E VAREJ", "BEXS BANCO DE CAMBIO S.A.", "BEXS CORRETORA DE CAMBIO S/A", "BI UBS BRASIL", "BNY MELLON BANCO S.A.", "BOLETOBANCARIO.COM TEC DE PAGT", "BPP INSTITUIÇÃO DE PAGAMENTOS", "BR PARTNERS BCO INVEST. S.A.", "BRB-BANCO DE BRASILIA S.A.", "BRK S.A. CRED. FINANC E INVEST", "BRL TRUST DIST. DE TIT. E VAL.", "BROKER BRASIL CORRET CAMBIO LT", "BS2 DIST. DE TIT. E VAL. MOB.", "CAIXA ECONOMICA FEDERAL", "CAMBIONET CORRETORA DE CÂMBIO", "CAROL DISTR. D. TIT. E VAL. IM", "CARTOS SOCIEDADE DE CREDITO DI", "CARUANA S.A. - SOC.CRED. FINAN", "CASA DO CREDITO S.A. SOC.D.CRED.", "CC UNIPRIME CENTRAL", "CC UNIPRIME NORTE DO PARANA", "CENTRAL COOP.EC.CR.MUTUO ESP.S", "CHINA CONSTRUCTION BANK (BRASIL", "CIELO S.A.", "CITIBANCK N.A.", "CODEPE - CORRETORA DE VALORES", "COMMERZBANK BRASIL S.A. - BANC", "CONFED.NAC.COOP.CENTRAIS ECON", "CONFIDENCE CORRETORA DE CÂMBIO", "COOP CRED RURAL DE IBIAM-SULCR", "COOP CRED RURAL PEQ AGRICULTOR", "COOP DE CRED RURAL DE ABELARDO", "COOP DE CRED.RURAL DA REG DA M", "COOP. DE CRED. RURAL D.PRIMAVE", "COOP.CRED.MUTUO DESP.TR.DE STA", "COOP.D.CRED.RURAL DE OUROSULC", "COOP.D.CRED.RURAL DE S. MIGUE", "COOPERATIVA CENTRAL SANTA CATAR", "COOPERATIVA DE CREDITO RURAL C", "COOPERFORTE-COOP DE ECON E CRE", "CORA SOCIEDADE DE CREDITO DIRE", "CREDIALIANCA COOP CRED RURAL", "CREDICOAMO CRED.RURAL COOP", "CREDISIS-CENTRAL DE COOP DE CR", "CREDIT SUISSE HG CORRETORA", "CREDITAS SOC. DE CREDITO DIRET", "CREFAZ SOC DE CRED AO MICROEMP", "DECYSEO CORRETORA DE CAMBIO LT", "DEUTSCHE BANK S.A.-BANCO ALEMA", "EASYNVEST TIT CORR VALORES S.A.", "FACTA FINANCEIRA S.A.", "FAIR CORRETORA DE CAMBIO S.A.", "FFA SOC DE CRED AO MIC E A EMP", "FIDUCIA SOC DE CRED AO MICRO E", "FRAM CAPITAL DIST DE TIT E VAL", "FRENT CORRETORA DE CAMBIO LTD", "GENIAL INVEST. COR DE VAL MOBI", "GERENCIANET PAGAMENTOS DO BRASIL", "GET MONEY CORRETORA DE CAMBIO", "GOLDMAN SACHS DO BRASIL. BCO MU", "GUIDE INVEST S.A. CORRETORA DE", "GUITTA CORRETORA DE CAMBIO", "HAITONG BANCO DE INVEST DO BRA", "HIPERCARD BANCO MULTIPLO S.A.", "HS FINANCEIRA S/A", "HUB PAGAMENTOS S.A.", "IB CORRET. DE CAMBIO. TIT.E VA", "ICAP DO BRASIL CORRETORA TITS", "ICBC DO BRASIL BCO MULTIPLO S.", "ING BANK N.V.", "INTESA SANPAOLO BRASIL S.A.", "ITAU UNIBANCO S.A.", "JP MORGAN CHASE BANK", "KDB DO BRASIL", "KIRTON BANK S.A. BCO. MULTIPLO", "LASTRO RDV DISTR D TIT E VAL M", "LECCA CREDITO FINANC.E INVESTI", "LEVYCAM - CORRET DE CAMBIO E V", "MAGLIANO S.A. COR.CAMB VLS MOBL", "MERCADOPAGO COM", "MONEY PLUS SOC DE CRED MICROEM", "MONEYCORP BANCO DE CAMBIO S.A.", "MS SOC DE CRED AO MICRO E EMP", "MSB BANK - BANCO DE CAMBIO", "NECTON INV SA CORRET DE VAL MO", "NEXT", "NOVA FUTURA CTVM LTDA", "NOVA PLATAFORMA DE COBRANÇA", "NOVO BANCO CONTINENTAL S.A.B - M", "NU PAGAMENTOS S.A.", "OLIVEIRA TRUST DIST TIT.VAL MO", "OM DIST DE TIT E VAL MOBILIARI", "OMNI BANCO S.A.", "ORAMA DIST DE TITULOS E VALORES", "OTIMO SOCIEDADE DE CREDITO DIR", "PAGSEGURO INTERNET S.A.", "PARANA BANCO S.A.", "PARATI-CREDITO FINANCIAMENTO E", "PARMETAL DISTRI. TIT. VAL. IMO", "PEFISA S.A. -CRED. FINANCIAMENT", "PI DIST. DE TIT. E VAL. MOBILI", "PLANNER CORRET. DE VALORES S.A.", "PLURAL S.A. BCO MULTIPLO", "POLOCRED SOC.CRED.MICROEMP.EMP", "PORTOPAR DIST. TIT. E VAL. MOB", "PORTOPCRED S.A. CRED.FINANC E IN", "QI SOCIEDADE DE CRÉDITO DIRETO", "RB CAPITAL INVESTIMENTOS DTVM", "REALIZE CRED. FINANC. E INVEST", "RENANSCENCA DIST.TIT.VAL.MOB.L.", "SAGITUR CORRETORA DE CAMBIO LT", "SCOTIABANK BRASIL S/A B.MULTIP", "SELECIONAR", "SENFF S.A. - CRED. FINANC. E IN", "SENSO CORR.CAMB.VAL.MOBILIARIO", "SERVICOOP-COOP DE CRED DOS SER", "SOC.DE CRED.AO MICROEMP.E EMP.", "SOCOPA SOCIEDADE CORRETORA PAU", "SOLIDUS S.A. C. CAMB. E VAL. MO", "SOROCRED CRED. FINAN. E INVESTI", "STATE STREET BRASIL S.A. - BCO", "STONE PAGAMENTOS S.A.", "SUPER PAGAMENTOS E ADMDE MEIOS", "TERRA INV. DIST. E TIT. E VAL", "TORO CORRETORA DE TIT E VALORES", "TRAVELEX BANCO DE CAMBIO S.A.", "TREVISO CORRETORA DE CAMBIO S/", "TRINUS CAPITAL DIST DE TIT E V", "UNICRED", "UNICRED CENTRAL RS-C.C.EC CRED", "UNS NRASIL CCTMV S.A.", "UP.P SOC DE EMPREST ENTRE PRESS", "VIP'S DE CORRETORA DE CAMBIO L", "VISION S.A. CORRETORA DE CAMBI", "VITREO DISTRIBUIDORA DE TIT E", "VORTX DIST. DE TIT. E VAL. MOB", "WARREN CORRET E VALORES IMOB", "XP INVEST.CORR.CAMP.VLS MOB.S.", "ZEMA CREDITO, FINANCIAMENTO IN" }));
        getContentPane().add(inputBank);
        inputBank.setBounds(610, 510, 360, 30);

        inputSalary.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(inputSalary);
        inputSalary.setBounds(530, 440, 100, 30);

        inputCommission.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(inputCommission);
        inputCommission.setBounds(690, 440, 100, 30);

        inputFoodVoucher.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(inputFoodVoucher);
        inputFoodVoucher.setBounds(850, 440, 100, 30);

        inputMealTicket.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(inputMealTicket);
        inputMealTicket.setBounds(20, 510, 100, 30);

        buttonSave.setText("SALVAR");
        buttonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSaveActionPerformed(evt);
            }
        });
        getContentPane().add(buttonSave);
        buttonSave.setBounds(20, 630, 100, 25);

        txtRequiredField2.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        txtRequiredField2.setForeground(new java.awt.Color(255, 0, 51));
        txtRequiredField2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtRequiredField2.setText("*");
        getContentPane().add(txtRequiredField2);
        txtRequiredField2.setBounds(250, 410, 20, 30);

        txtRequiredField3.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        txtRequiredField3.setForeground(new java.awt.Color(255, 0, 51));
        txtRequiredField3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtRequiredField3.setText("*");
        getContentPane().add(txtRequiredField3);
        txtRequiredField3.setBounds(140, 60, 20, 30);

        txtRequiredField4.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        txtRequiredField4.setForeground(new java.awt.Color(255, 0, 51));
        txtRequiredField4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtRequiredField4.setText("*");
        getContentPane().add(txtRequiredField4);
        txtRequiredField4.setBounds(590, 60, 20, 30);

        txtRequiredField5.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        txtRequiredField5.setForeground(new java.awt.Color(255, 0, 51));
        txtRequiredField5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtRequiredField5.setText("*");
        getContentPane().add(txtRequiredField5);
        txtRequiredField5.setBounds(530, 60, 20, 30);

        txtRequiredField6.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        txtRequiredField6.setForeground(new java.awt.Color(255, 0, 51));
        txtRequiredField6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtRequiredField6.setText("*");
        getContentPane().add(txtRequiredField6);
        txtRequiredField6.setBounds(740, 60, 20, 30);

        txtRequiredField7.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        txtRequiredField7.setForeground(new java.awt.Color(255, 0, 51));
        txtRequiredField7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtRequiredField7.setText("*");
        getContentPane().add(txtRequiredField7);
        txtRequiredField7.setBounds(60, 130, 20, 30);

        txtRequiredField8.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        txtRequiredField8.setForeground(new java.awt.Color(255, 0, 51));
        txtRequiredField8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtRequiredField8.setText("*");
        getContentPane().add(txtRequiredField8);
        txtRequiredField8.setBounds(60, 270, 20, 30);

        txtRequiredField9.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        txtRequiredField9.setForeground(new java.awt.Color(255, 0, 51));
        txtRequiredField9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtRequiredField9.setText("*");
        getContentPane().add(txtRequiredField9);
        txtRequiredField9.setBounds(190, 270, 20, 30);

        txtRequiredField10.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        txtRequiredField10.setForeground(new java.awt.Color(255, 0, 51));
        txtRequiredField10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtRequiredField10.setText("*");
        getContentPane().add(txtRequiredField10);
        txtRequiredField10.setBounds(730, 270, 20, 30);

        txtRequiredField11.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        txtRequiredField11.setForeground(new java.awt.Color(255, 0, 51));
        txtRequiredField11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtRequiredField11.setText("*");
        getContentPane().add(txtRequiredField11);
        txtRequiredField11.setBounds(80, 340, 20, 30);

        txtRequiredField12.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        txtRequiredField12.setForeground(new java.awt.Color(255, 0, 51));
        txtRequiredField12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtRequiredField12.setText("*");
        getContentPane().add(txtRequiredField12);
        txtRequiredField12.setBounds(360, 340, 20, 30);

        buttonCancel.setText("CANCELAR");
        buttonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelActionPerformed(evt);
            }
        });
        getContentPane().add(buttonCancel);
        buttonCancel.setBounds(150, 630, 110, 25);

        txtPercent.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        txtPercent.setText("%");
        getContentPane().add(txtPercent);
        txtPercent.setBounds(800, 440, 20, 30);

        setSize(new java.awt.Dimension(1029, 700));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSaveActionPerformed
        if(inputFullName.getText().equals("")||inputBirthDay.getText().equals("")||inputRG.getText().equals("")||inputCPF.getText().equals("")||inputSex.getSelectedItem().equals("Selecione")||inputCEP.getText().equals("")||inputStreet.getText().equals("")||inputNeighBorhood.getText().equals("")||inputCity.getText().equals("")||inputState.getSelectedItem().equals("SELECIONAR")||inputOccupation.getText().equals("")){
            JOptionPane.showMessageDialog(null, "POR FAVOR, PREENCHA TODOS OS CAMPOS OBRIGATÓRIOS");
        }
        else if(cpfValide==false&&cnpjValide==false){
            JOptionPane.showMessageDialog(null, "O CPF OU O CNPJ DIGITADO É INVÁLIDO!");
        }
        else if(txtNewEmployee.getText().equals("NOVO FUNCIONÁRIO")){
            if(checkIfExist()){
                JOptionPane.showMessageDialog(null, "O FUNCIONÁRIO EM QUESTÃO JÁ ESTÁ CADASTRADO NO SISTEMA");
            }
            else{
                add();
            }
        }
        else if(txtNewEmployee.getText().equals("ATUALIZAR FUNCIONÁRIO")){
            String[] var = comp().split(";");
            if(inputRG.getText().equals(var[0]) && inputCPF.getText().equals(var[1])){
                if(txtAdmissionDate.getText().equals("Data de Readmissão")){
                    updateReadmited1();
                }
                else{
                    update();
                }
            }
            else if(!inputRG.getText().equals(var[0]) && !checkRG() && !inputCPF.getText().equals(var[1]) && !checkCPF()){
                if(txtAdmissionDate.getText().equals("Data de Readmissão")){
                    updateReadmited1();
                }
                else{
                    update();
                }
            }
            else if(!inputRG.getText().equals(var[0]) && !checkRG()){
                if(txtAdmissionDate.getText().equals("Data de Readmissão")){
                    updateReadmited1();
                }
                else{
                    update();
                }
            }
            else if(!inputCPF.getText().equals(var[1]) && !checkCPF()){
                if(txtAdmissionDate.getText().equals("Data de Readmissão")){
                    updateReadmited1();
                }
                else{
                    update();
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "O NOVO RG OU CPF QUE VOCÊ DIGITOU JÁ ESTÁ CADASTRADO PARA ALGUM FUNCIONÁRIO!\nVERIFIQUE OS DADOS E TENTE NOVAMENTE");
            }
        }
    }//GEN-LAST:event_buttonSaveActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        if(x==0){
            x++;
            if(txtNewEmployee.getText().equals("ATUALIZAR FUNCIONÁRIO")){
                buttonCancel.setText("EXCLUIR");
                setEmployee();
            }
            inputFullName.requestFocus();
        }
    }//GEN-LAST:event_formWindowActivated

    private void inputCEPFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputCEPFocusLost
        if(!inputCEP.getText().equals("")){
            setInformations();
        }
    }//GEN-LAST:event_inputCEPFocusLost

    private void inputCEPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputCEPKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            setInformations();
        }
    }//GEN-LAST:event_inputCEPKeyPressed

    private void inputPhotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputPhotoActionPerformed
        if(inputPhoto.getText().equals("FOTO")){
            TakePictureOrChoose takePictureOrChoose = new TakePictureOrChoose();
            takePictureOrChoose.newEmployee = this;
            takePictureOrChoose.itsANewEmployee=true;
            takePictureOrChoose.setVisible(true);
        }
        else{
            TakePictureOrChoose takePictureOrChoose = new TakePictureOrChoose();
            takePictureOrChoose.newEmployee = this;
            takePictureOrChoose.buttonShowPicture.setVisible(true);
            takePictureOrChoose.adress=imageAdress;
            takePictureOrChoose.itsANewEmployee=true;
            takePictureOrChoose.setVisible(true);
        }
    }//GEN-LAST:event_inputPhotoActionPerformed

    private void buttonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelActionPerformed
        if(buttonCancel.getText().equals("EXCLUIR")){
            delete();
        }
        else if(buttonCancel.getText().equals("CANCELAR")){
            this.dispose();
        }
    }//GEN-LAST:event_buttonCancelActionPerformed

    private void inputCPFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputCPFFocusLost
        if(!inputCPF.getText().equals("")){
            ValidateCPF validateCPF = new ValidateCPF();
            ValidateCNPJ validateCNPJ = new ValidateCNPJ();
            GetJustTheNumbers getJustTheNumbers = new GetJustTheNumbers();
            if(inputCPF.getText().length()>10 && inputCPF.getText().length()<15 && !validateCPF.isValide( getJustTheNumbers.getNumbers( inputCPF.getText() ) )){
                JOptionPane.showMessageDialog(null, "O CPF DIGITADO É INVÁLIDO");
                cpfValide = false;
            }
            else if(inputCPF.getText().length()>10 && inputCPF.getText().length()<15 && validateCPF.isValide( getJustTheNumbers.getNumbers( inputCPF.getText() ) )){
                cpfValide = true;
            }
            else if(inputCPF.getText().length()>13 && inputCPF.getText().length()<19 && !validateCNPJ.isValide( getJustTheNumbers.getNumbers( inputCPF.getText() ) )){
                JOptionPane.showMessageDialog(null, "O CNPJ DIGITADO É INVÁLIDO");
                cnpjValide = false;
            }
            else if(inputCPF.getText().length()>14 && inputCPF.getText().length()<19 && validateCNPJ.isValide( getJustTheNumbers.getNumbers( inputCPF.getText() ) )){
                cnpjValide = true;
            }
        }
    }//GEN-LAST:event_inputCPFFocusLost

    private void inputCPFKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputCPFKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            ValidateCPF validateCPF = new ValidateCPF();
            ValidateCNPJ validateCNPJ = new ValidateCNPJ();
            GetJustTheNumbers getJustTheNumbers = new GetJustTheNumbers();
            if(inputCPF.getText().length()>10 && inputCPF.getText().length()<15 && !validateCPF.isValide( getJustTheNumbers.getNumbers( inputCPF.getText() ) )){
                JOptionPane.showMessageDialog(null, "O CPF DIGITADO É INVÁLIDO");
                cpfValide = false;
            }
            else if(inputCPF.getText().length()>10 && inputCPF.getText().length()<15 && validateCPF.isValide( getJustTheNumbers.getNumbers( inputCPF.getText() ) )){
                cpfValide = true;
            }
            else if(inputCPF.getText().length()>13 && inputCPF.getText().length()<19 && !validateCNPJ.isValide( getJustTheNumbers.getNumbers( inputCPF.getText() ) )){
                JOptionPane.showMessageDialog(null, "O CNPJ DIGITADO É INVÁLIDO");
                cnpjValide = false;
            }
            else if(inputCPF.getText().length()>14 && inputCPF.getText().length()<19 && validateCNPJ.isValide( getJustTheNumbers.getNumbers( inputCPF.getText() ) )){
                cnpjValide = true;
            }
        }
    }//GEN-LAST:event_inputCPFKeyPressed

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
            java.util.logging.Logger.getLogger(NewEmployee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewEmployee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewEmployee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewEmployee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewEmployee().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCancel;
    private javax.swing.JButton buttonSave;
    private javax.swing.JTextField inputAccount;
    private javax.swing.JComboBox<String> inputAccountType;
    public static javax.swing.JTextField inputAdmissionDate;
    private javax.swing.JTextField inputAgency;
    private javax.swing.JComboBox<String> inputBank;
    private javax.swing.JTextField inputBirthDay;
    private javax.swing.JTextField inputCEP;
    private javax.swing.JTextField inputCPF;
    private javax.swing.JTextField inputCellPhone;
    private javax.swing.JTextField inputCity;
    private javax.swing.JTextField inputCommission;
    private javax.swing.JTextField inputComplement;
    private javax.swing.JTextField inputEmail;
    private javax.swing.JTextField inputFatherName;
    private javax.swing.JTextField inputFoodVoucher;
    private javax.swing.JTextField inputFullName;
    private javax.swing.JTextField inputMealTicket;
    private javax.swing.JTextField inputMotherName;
    private javax.swing.JTextField inputNeighBorhood;
    private javax.swing.JTextField inputNumberHouse;
    private javax.swing.JTextField inputOccupation;
    private javax.swing.JTextField inputPIS;
    private javax.swing.JTextField inputPhone;
    public static javax.swing.JButton inputPhoto;
    private javax.swing.JTextField inputRG;
    private javax.swing.JTextField inputSalary;
    private javax.swing.JComboBox<String> inputSex;
    private javax.swing.JComboBox<String> inputState;
    private javax.swing.JTextField inputStreet;
    private javax.swing.JTextField inputTranportationVoucher;
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
    private javax.swing.JLabel txtEmail;
    private javax.swing.JLabel txtFatherName;
    private javax.swing.JLabel txtFoodVoucher;
    private javax.swing.JLabel txtFullName;
    private javax.swing.JLabel txtMealTicket;
    private javax.swing.JLabel txtMotherName;
    private javax.swing.JLabel txtNeighBorhood;
    public static javax.swing.JLabel txtNewEmployee;
    private javax.swing.JLabel txtNumberHouse;
    private javax.swing.JLabel txtOccupation;
    private javax.swing.JLabel txtPIS;
    private javax.swing.JLabel txtPercent;
    private javax.swing.JLabel txtPhone;
    private javax.swing.JLabel txtRG;
    private javax.swing.JLabel txtRequiredField10;
    private javax.swing.JLabel txtRequiredField11;
    private javax.swing.JLabel txtRequiredField12;
    private javax.swing.JLabel txtRequiredField2;
    private javax.swing.JLabel txtRequiredField3;
    private javax.swing.JLabel txtRequiredField4;
    private javax.swing.JLabel txtRequiredField5;
    private javax.swing.JLabel txtRequiredField6;
    private javax.swing.JLabel txtRequiredField7;
    private javax.swing.JLabel txtRequiredField8;
    private javax.swing.JLabel txtRequiredField9;
    private javax.swing.JLabel txtSalary;
    private javax.swing.JLabel txtSex;
    private javax.swing.JLabel txtState;
    private javax.swing.JLabel txtStreet;
    private javax.swing.JLabel txtTranportationVoucher;
    // End of variables declaration//GEN-END:variables
}
