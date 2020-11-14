package screens;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import connectionbd.ConnectionModule;
import functioncontroller.SearchCEP;
import functioncontroller.SearchCEPException;
import functioncontroller.UpperLetter;
import functioncontroller.UpperLetterAux;


public class NewProvider extends javax.swing.JFrame {
    Connection connection = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    int x = 0;
    public NewProvider() {
        initComponents();
        ConnectionModule connect = new ConnectionModule();
        connection = connect.getConnectionMySQL();
        inputFantasyName.setDocument(new UpperLetter());
        inputCompanyName.setDocument(new UpperLetter());
        inputEmail.setDocument(new UpperLetter());
        inputAdress.setDocument(new UpperLetter());
        inputNeighborhood.setDocument(new UpperLetter());
        inputCity.setDocument(new UpperLetter());
        inputComplement.setDocument(new UpperLetter());
        inputObservation.setDocument(new UpperLetter());
    }
    private void add(){
        String sql = "insert into provider(codeProvider, cpf, fantasyName, companyName, phone, cellPhone, email, cep, street, houseNumber, neighborhood, city, state, complement, observation)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt( inputCode.getText() ));
            pst.setString(2,inputCPFAndCNPJ.getText());
            pst.setString(3,inputFantasyName.getText());
            pst.setString(4,inputCompanyName.getText());
            pst.setString(5,inputPhone.getText());
            pst.setString(6,inputCellPhone.getText());
            pst.setString(7,inputEmail.getText());
            pst.setString(8,inputCep.getText());
            pst.setString(9,inputAdress.getText());
            pst.setString(10,inputNumber.getText());
            pst.setString(11,inputNeighborhood.getText());
            pst.setString(12,inputCity.getText());
            pst.setString(13,inputState.getSelectedItem().toString());
            pst.setString(14,inputComplement.getText());
            pst.setString(15,inputObservation.getText());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null,"FORNECEDOR CADASTRADO COM SUCESSO");
            ProviderScreen providerScreen = new ProviderScreen();
            this.dispose();
            providerScreen.setTitle("Fornecedor: " + inputCode.getText());
            providerScreen.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private int getId(){
        String sql ="select max(codeProvider) from provider";
        try {
            pst=connection.prepareStatement(sql);
            rs= pst.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return 1;
    }
    private boolean hasThisProvider(){
        String sql ="select * from provider where cpf = ?";
        try {
            pst=connection.prepareStatement(sql);
            pst.setString(1, inputCPFAndCNPJ.getText());
            rs= pst.executeQuery();
            if(rs.next()){
                return true;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return false;
    }
    private void setInformations(){
        UpperLetterAux upperLetterAux = new UpperLetterAux();
        SearchCEP searchCEP = new SearchCEP();
        try {
            searchCEP.buscar(inputCep.getText());
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
            inputNeighborhood.setText(neighBorhood);
            inputAdress.setText(street);
            inputCity.setText(city);
            inputState.setSelectedItem(searchCEP.getUf());
            inputNumber.requestFocus();
        } catch (SearchCEPException ex) {
            JOptionPane.showMessageDialog(null, "NÃO FOI POSSÍVEL ENCONTRAR O CEP");
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtNewProvider = new javax.swing.JLabel();
        txtCPFAndCNPJ = new javax.swing.JLabel();
        inputCPFAndCNPJ = new javax.swing.JTextField();
        txtFantasyName = new javax.swing.JLabel();
        inputFantasyName = new javax.swing.JTextField();
        txtPhone = new javax.swing.JLabel();
        inputPhone = new javax.swing.JTextField();
        txtCep = new javax.swing.JLabel();
        inputCep = new javax.swing.JTextField();
        txtAdress = new javax.swing.JLabel();
        inputAdress = new javax.swing.JTextField();
        txtCity = new javax.swing.JLabel();
        inputCity = new javax.swing.JTextField();
        txtState = new javax.swing.JLabel();
        inputState = new javax.swing.JComboBox<>();
        txtNeighBorhood = new javax.swing.JLabel();
        inputNeighborhood = new javax.swing.JTextField();
        txtEmail = new javax.swing.JLabel();
        inputEmail = new javax.swing.JTextField();
        txtCompanyName = new javax.swing.JLabel();
        inputCompanyName = new javax.swing.JTextField();
        txtNumber = new javax.swing.JLabel();
        inputNumber = new javax.swing.JTextField();
        txtCellPhone = new javax.swing.JLabel();
        inputCellPhone = new javax.swing.JTextField();
        txtObservation = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        inputObservation = new javax.swing.JTextArea();
        buttonSave = new javax.swing.JButton();
        txtComplement = new javax.swing.JLabel();
        inputComplement = new javax.swing.JTextField();
        txtCode = new javax.swing.JLabel();
        inputCode = new javax.swing.JTextField();
        txtRequiredField1 = new javax.swing.JLabel();
        txtRequiredField2 = new javax.swing.JLabel();
        txtRequiredField3 = new javax.swing.JLabel();
        txtRequiredField4 = new javax.swing.JLabel();
        txtRequiredField5 = new javax.swing.JLabel();
        txtRequiredField6 = new javax.swing.JLabel();
        txtRequiredField7 = new javax.swing.JLabel();
        txtRequiredField8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Novo Fornecedor");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(null);

        txtNewProvider.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        txtNewProvider.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtNewProvider.setText("NOVO FORNECEDOR");
        getContentPane().add(txtNewProvider);
        txtNewProvider.setBounds(260, 20, 320, 32);

        txtCPFAndCNPJ.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtCPFAndCNPJ.setText("CPF / CNPJ");
        getContentPane().add(txtCPFAndCNPJ);
        txtCPFAndCNPJ.setBounds(20, 150, 100, 20);

        inputCPFAndCNPJ.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(inputCPFAndCNPJ);
        inputCPFAndCNPJ.setBounds(20, 180, 160, 30);

        txtFantasyName.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtFantasyName.setText("Nome Fantasia");
        getContentPane().add(txtFantasyName);
        txtFantasyName.setBounds(170, 70, 120, 20);

        inputFantasyName.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        inputFantasyName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputFantasyNameActionPerformed(evt);
            }
        });
        getContentPane().add(inputFantasyName);
        inputFantasyName.setBounds(170, 100, 360, 30);

        txtPhone.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtPhone.setText("Telefone");
        getContentPane().add(txtPhone);
        txtPhone.setBounds(210, 150, 64, 20);

        inputPhone.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(inputPhone);
        inputPhone.setBounds(210, 180, 150, 30);

        txtCep.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtCep.setText("CEP");
        getContentPane().add(txtCep);
        txtCep.setBounds(20, 240, 31, 20);

        inputCep.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        inputCep.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                inputCepFocusLost(evt);
            }
        });
        inputCep.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputCepKeyPressed(evt);
            }
        });
        getContentPane().add(inputCep);
        inputCep.setBounds(20, 270, 130, 30);

        txtAdress.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtAdress.setText("Endereço");
        getContentPane().add(txtAdress);
        txtAdress.setBounds(190, 240, 69, 20);

        inputAdress.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(inputAdress);
        inputAdress.setBounds(190, 270, 440, 30);

        txtCity.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtCity.setText("Cidade");
        getContentPane().add(txtCity);
        txtCity.setBounds(410, 320, 50, 20);

        inputCity.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(inputCity);
        inputCity.setBounds(410, 350, 230, 30);

        txtState.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtState.setText("Estado");
        getContentPane().add(txtState);
        txtState.setBounds(670, 320, 49, 20);

        inputState.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        inputState.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECIONAR", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
        getContentPane().add(inputState);
        inputState.setBounds(670, 350, 140, 30);

        txtNeighBorhood.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtNeighBorhood.setText("Bairro");
        getContentPane().add(txtNeighBorhood);
        txtNeighBorhood.setBounds(20, 320, 44, 20);

        inputNeighborhood.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(inputNeighborhood);
        inputNeighborhood.setBounds(20, 350, 360, 30);

        txtEmail.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtEmail.setText("Email");
        getContentPane().add(txtEmail);
        txtEmail.setBounds(590, 150, 38, 20);

        inputEmail.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(inputEmail);
        inputEmail.setBounds(590, 180, 290, 30);

        txtCompanyName.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtCompanyName.setText("Razão Social");
        getContentPane().add(txtCompanyName);
        txtCompanyName.setBounds(550, 70, 120, 20);

        inputCompanyName.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(inputCompanyName);
        inputCompanyName.setBounds(550, 100, 330, 30);

        txtNumber.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtNumber.setText("Número");
        getContentPane().add(txtNumber);
        txtNumber.setBounds(680, 240, 60, 20);

        inputNumber.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(inputNumber);
        inputNumber.setBounds(680, 270, 130, 30);

        txtCellPhone.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtCellPhone.setText("Celular");
        getContentPane().add(txtCellPhone);
        txtCellPhone.setBounds(390, 150, 80, 20);

        inputCellPhone.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(inputCellPhone);
        inputCellPhone.setBounds(390, 180, 170, 30);

        txtObservation.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtObservation.setText("Observação");
        getContentPane().add(txtObservation);
        txtObservation.setBounds(20, 480, 110, 20);

        inputObservation.setColumns(20);
        inputObservation.setRows(5);
        jScrollPane1.setViewportView(inputObservation);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(20, 510, 740, 140);

        buttonSave.setText("SALVAR");
        buttonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSaveActionPerformed(evt);
            }
        });
        getContentPane().add(buttonSave);
        buttonSave.setBounds(800, 620, 78, 25);

        txtComplement.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtComplement.setText("Complemento");
        getContentPane().add(txtComplement);
        txtComplement.setBounds(20, 400, 110, 20);

        inputComplement.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(inputComplement);
        inputComplement.setBounds(20, 430, 270, 30);

        txtCode.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtCode.setText("Código");
        getContentPane().add(txtCode);
        txtCode.setBounds(20, 70, 100, 20);

        inputCode.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(inputCode);
        inputCode.setBounds(20, 100, 130, 30);

        txtRequiredField1.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        txtRequiredField1.setForeground(new java.awt.Color(255, 0, 51));
        txtRequiredField1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtRequiredField1.setText("*");
        getContentPane().add(txtRequiredField1);
        txtRequiredField1.setBounds(80, 70, 20, 30);

        txtRequiredField2.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        txtRequiredField2.setForeground(new java.awt.Color(255, 0, 51));
        txtRequiredField2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtRequiredField2.setText("*");
        getContentPane().add(txtRequiredField2);
        txtRequiredField2.setBounds(730, 320, 20, 30);

        txtRequiredField3.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        txtRequiredField3.setForeground(new java.awt.Color(255, 0, 51));
        txtRequiredField3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtRequiredField3.setText("*");
        getContentPane().add(txtRequiredField3);
        txtRequiredField3.setBounds(290, 70, 20, 30);

        txtRequiredField4.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        txtRequiredField4.setForeground(new java.awt.Color(255, 0, 51));
        txtRequiredField4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtRequiredField4.setText("*");
        getContentPane().add(txtRequiredField4);
        txtRequiredField4.setBounds(110, 150, 20, 30);

        txtRequiredField5.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        txtRequiredField5.setForeground(new java.awt.Color(255, 0, 51));
        txtRequiredField5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtRequiredField5.setText("*");
        getContentPane().add(txtRequiredField5);
        txtRequiredField5.setBounds(60, 240, 20, 30);

        txtRequiredField6.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        txtRequiredField6.setForeground(new java.awt.Color(255, 0, 51));
        txtRequiredField6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtRequiredField6.setText("*");
        getContentPane().add(txtRequiredField6);
        txtRequiredField6.setBounds(270, 240, 20, 30);

        txtRequiredField7.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        txtRequiredField7.setForeground(new java.awt.Color(255, 0, 51));
        txtRequiredField7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtRequiredField7.setText("*");
        getContentPane().add(txtRequiredField7);
        txtRequiredField7.setBounds(80, 320, 20, 30);

        txtRequiredField8.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        txtRequiredField8.setForeground(new java.awt.Color(255, 0, 51));
        txtRequiredField8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtRequiredField8.setText("*");
        getContentPane().add(txtRequiredField8);
        txtRequiredField8.setBounds(470, 320, 20, 30);

        setSize(new java.awt.Dimension(916, 696));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSaveActionPerformed
        if(txtNewProvider.getText().equals("NOVO FORNECEDOR")){
            if(inputCode.getText().equals("")||inputFantasyName.getText().equals("")||inputCPFAndCNPJ.getText().equals("")||inputCep.getText().equals("")||inputAdress.getText().equals("")||inputNeighborhood.getText().equals("")||inputCity.getText().equals("")||inputState.getSelectedItem().equals("SELECIONAR")){
                JOptionPane.showMessageDialog(null, "POR FAVOR, PREENCHA TODOS OS CAMPOS OBRIGATÓRIOS");
            }
            else if(hasThisProvider()){
                    JOptionPane.showMessageDialog(null, "FORNECEDOR JÁ CADASTRADO NO SISTEMA");
            }
            else{
                add();
            }
        }
        else if(txtNewProvider.getText().equals("EDITAR FORNECEDOR")){
            JOptionPane.showMessageDialog(null, "FORNECEDOR ATUALIZADO COM SUCESSO");
        }
    }//GEN-LAST:event_buttonSaveActionPerformed

    private void inputFantasyNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputFantasyNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputFantasyNameActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        if(x==0){
            x++;
            int id = getId() + 1;
            inputCode.setText(Integer.toString(id));
        }
    }//GEN-LAST:event_formWindowActivated

    private void inputCepKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputCepKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            setInformations();
        }
    }//GEN-LAST:event_inputCepKeyPressed

    private void inputCepFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputCepFocusLost
        if(!inputCep.getText().equals("")){
            setInformations();
        }
    }//GEN-LAST:event_inputCepFocusLost

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
            java.util.logging.Logger.getLogger(NewProvider.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewProvider.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewProvider.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewProvider.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewProvider().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonSave;
    private javax.swing.JTextField inputAdress;
    private javax.swing.JTextField inputCPFAndCNPJ;
    private javax.swing.JTextField inputCellPhone;
    private javax.swing.JTextField inputCep;
    private javax.swing.JTextField inputCity;
    private javax.swing.JTextField inputCode;
    private javax.swing.JTextField inputCompanyName;
    private javax.swing.JTextField inputComplement;
    private javax.swing.JTextField inputEmail;
    private javax.swing.JTextField inputFantasyName;
    private javax.swing.JTextField inputNeighborhood;
    private javax.swing.JTextField inputNumber;
    private javax.swing.JTextArea inputObservation;
    private javax.swing.JTextField inputPhone;
    private javax.swing.JComboBox<String> inputState;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel txtAdress;
    private javax.swing.JLabel txtCPFAndCNPJ;
    private javax.swing.JLabel txtCellPhone;
    private javax.swing.JLabel txtCep;
    private javax.swing.JLabel txtCity;
    private javax.swing.JLabel txtCode;
    private javax.swing.JLabel txtCompanyName;
    private javax.swing.JLabel txtComplement;
    private javax.swing.JLabel txtEmail;
    private javax.swing.JLabel txtFantasyName;
    private javax.swing.JLabel txtNeighBorhood;
    public static javax.swing.JLabel txtNewProvider;
    private javax.swing.JLabel txtNumber;
    private javax.swing.JLabel txtObservation;
    private javax.swing.JLabel txtPhone;
    private javax.swing.JLabel txtRequiredField1;
    private javax.swing.JLabel txtRequiredField2;
    private javax.swing.JLabel txtRequiredField3;
    private javax.swing.JLabel txtRequiredField4;
    private javax.swing.JLabel txtRequiredField5;
    private javax.swing.JLabel txtRequiredField6;
    private javax.swing.JLabel txtRequiredField7;
    private javax.swing.JLabel txtRequiredField8;
    private javax.swing.JLabel txtState;
    // End of variables declaration//GEN-END:variables
}
