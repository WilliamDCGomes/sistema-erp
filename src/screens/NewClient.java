/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screens;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import connectionbd.ConnectionModule;
import functioncontroller.GetImageAdress;
import functioncontroller.SearchCEP;
import functioncontroller.SearchCEPException;
import functioncontroller.UpperLetter;
import functioncontroller.UpperLetterAux;
/**
 *
 * @author Alunos
 */
public class NewClient extends javax.swing.JFrame {
    Connection connection = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    PreparedStatement pst2 = null;
    ResultSet rs2 = null;
    GetImageAdress getImageAdress = new GetImageAdress();
    String imageAdress = null;
    int x = 0;
    String clientID;
    /**
     * Creates new form NewClient
     */
    public NewClient() {
        initComponents();
        inputName.setDocument(new UpperLetter());
        inputEmail.setDocument(new UpperLetter());
        inputStreet.setDocument(new UpperLetter());
        inputNeighborhood.setDocument(new UpperLetter());
        inputCity.setDocument(new UpperLetter());
        inputObservation.setDocument(new UpperLetter());
        ConnectionModule connect = new ConnectionModule();
        connection = connect.getConnectionMySQL();
    }
    private void add(){
        String sql = "insert into clients(clientName, cpf, birthDay, phone, cellPhone, email, cep, street, houseNumber, neighborhood, city, state, observation, photoAdress)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1,inputName.getText());
            pst.setString(2,inputCPF.getText());
            pst.setString(3,inputBirthDay.getText());
            pst.setString(4,inputPhone.getText());
            pst.setString(5,inputCellphone.getText());
            pst.setString(6,inputEmail.getText());
            pst.setString(7,inputCEP.getText());
            pst.setString(8,inputStreet.getText());
            pst.setString(9,inputNumberHouse.getText());
            pst.setString(10,inputNeighborhood.getText());
            pst.setString(11,inputCity.getText());
            pst.setString(12,inputState.getSelectedItem().toString());
            pst.setString(13,inputObservation.getText());
            pst.setString(14,imageAdress);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null,"CLIENTE CADASTRADO COM SUCESSO");
            ClientScreen clientScreen = new ClientScreen();
            this.dispose();
            getId();
            clientScreen.setTitle("Cliente: " + clientID);
            clientScreen.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private boolean getId(){
        String sql ="select id,clientName from clients where cpf=?";
        try {
            pst2=connection.prepareStatement(sql);
            pst2.setString(1, inputCPF.getText());
            rs2= pst2.executeQuery();
            if(rs2.next()){
                clientID = Integer.toString(rs2.getInt(1));
                if(rs2.getString(2)!=null){
                    return true;
                }
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
            inputNeighborhood.setText(neighBorhood);
            inputStreet.setText(street);
            inputCity.setText(city);
            inputState.setSelectedItem(searchCEP.getUf());
            inputNumberHouse.requestFocus();
        } catch (SearchCEPException ex) {
            JOptionPane.showMessageDialog(null, "NÃO FOI POSSÍVEL ENCONTRAR O CEP");
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

        jLabel1 = new javax.swing.JLabel();
        txtBirthDay = new javax.swing.JLabel();
        txtName = new javax.swing.JLabel();
        txtCPF = new javax.swing.JLabel();
        txtPhone = new javax.swing.JLabel();
        txtCEP = new javax.swing.JLabel();
        txtStreet = new javax.swing.JLabel();
        txtNumberHouse = new javax.swing.JLabel();
        txtObservation = new javax.swing.JLabel();
        txtEmail = new javax.swing.JLabel();
        txtCellPhone = new javax.swing.JLabel();
        txtCadastreClient = new javax.swing.JLabel();
        inputName = new javax.swing.JTextField();
        inputCPF = new javax.swing.JFormattedTextField();
        inputBirthDay = new javax.swing.JFormattedTextField();
        inputPhone = new javax.swing.JFormattedTextField();
        inputCellphone = new javax.swing.JFormattedTextField();
        inputEmail = new javax.swing.JTextField();
        inputCEP = new javax.swing.JFormattedTextField();
        inputStreet = new javax.swing.JTextField();
        inputNumberHouse = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        inputObservation = new javax.swing.JTextArea();
        buttonPhoto = new javax.swing.JButton();
        buttonSave = new javax.swing.JButton();
        txtCity = new javax.swing.JLabel();
        inputCity = new javax.swing.JTextField();
        txtState = new javax.swing.JLabel();
        inputState = new javax.swing.JComboBox<>();
        txtNeighborhood = new javax.swing.JLabel();
        inputNeighborhood = new javax.swing.JTextField();
        txtRequiredField1 = new javax.swing.JLabel();
        txtRequiredField2 = new javax.swing.JLabel();
        txtRequiredField3 = new javax.swing.JLabel();
        txtRequiredField4 = new javax.swing.JLabel();
        txtRequiredField5 = new javax.swing.JLabel();
        txtRequiredField6 = new javax.swing.JLabel();
        txtRequiredField7 = new javax.swing.JLabel();
        txtRequiredField8 = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Cliente");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(null);

        txtBirthDay.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtBirthDay.setText("Data de Nascimento");
        getContentPane().add(txtBirthDay);
        txtBirthDay.setBounds(550, 80, 175, 24);

        txtName.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtName.setText("Nome");
        getContentPane().add(txtName);
        txtName.setBounds(40, 80, 52, 24);

        txtCPF.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtCPF.setText("CPF / CNPJ");
        getContentPane().add(txtCPF);
        txtCPF.setBounds(410, 80, 110, 24);

        txtPhone.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtPhone.setText("Telefone");
        getContentPane().add(txtPhone);
        txtPhone.setBounds(40, 150, 77, 30);

        txtCEP.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtCEP.setText("CEP");
        getContentPane().add(txtCEP);
        txtCEP.setBounds(40, 220, 37, 24);

        txtStreet.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtStreet.setText("Rua");
        getContentPane().add(txtStreet);
        txtStreet.setBounds(190, 220, 34, 24);

        txtNumberHouse.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtNumberHouse.setText("Nº");
        getContentPane().add(txtNumberHouse);
        txtNumberHouse.setBounds(620, 220, 20, 24);

        txtObservation.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtObservation.setText("Observação");
        getContentPane().add(txtObservation);
        txtObservation.setBounds(40, 360, 124, 24);

        txtEmail.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtEmail.setText("E-mail");
        getContentPane().add(txtEmail);
        txtEmail.setBounds(410, 150, 55, 30);

        txtCellPhone.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtCellPhone.setText("Celular");
        getContentPane().add(txtCellPhone);
        txtCellPhone.setBounds(220, 150, 62, 30);

        txtCadastreClient.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        txtCadastreClient.setText("Cadastro de Cliente");
        getContentPane().add(txtCadastreClient);
        txtCadastreClient.setBounds(340, 10, 225, 32);

        inputName.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(inputName);
        inputName.setBounds(40, 110, 360, 30);

        inputCPF.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(inputCPF);
        inputCPF.setBounds(410, 110, 130, 30);

        inputBirthDay.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(inputBirthDay);
        inputBirthDay.setBounds(550, 110, 159, 30);

        inputPhone.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(inputPhone);
        inputPhone.setBounds(40, 180, 160, 30);

        inputCellphone.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(inputCellphone);
        inputCellphone.setBounds(220, 180, 170, 30);

        inputEmail.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(inputEmail);
        inputEmail.setBounds(410, 180, 330, 30);

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
        inputCEP.setBounds(40, 250, 130, 30);

        inputStreet.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(inputStreet);
        inputStreet.setBounds(190, 250, 410, 30);

        inputNumberHouse.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(inputNumberHouse);
        inputNumberHouse.setBounds(620, 250, 98, 30);

        inputObservation.setColumns(20);
        inputObservation.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        inputObservation.setRows(5);
        jScrollPane1.setViewportView(inputObservation);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(40, 390, 740, 120);

        buttonPhoto.setText("FOTO");
        buttonPhoto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        buttonPhoto.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonPhoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPhotoActionPerformed(evt);
            }
        });
        getContentPane().add(buttonPhoto);
        buttonPhoto.setBounds(750, 80, 160, 200);

        buttonSave.setText("SALVAR");
        buttonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSaveActionPerformed(evt);
            }
        });
        getContentPane().add(buttonSave);
        buttonSave.setBounds(830, 480, 80, 30);

        txtCity.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtCity.setText("Cidade");
        getContentPane().add(txtCity);
        txtCity.setBounds(410, 290, 70, 24);

        inputCity.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(inputCity);
        inputCity.setBounds(410, 320, 189, 30);

        txtState.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtState.setText("Estado");
        getContentPane().add(txtState);
        txtState.setBounds(610, 290, 80, 24);

        inputState.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        inputState.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECIONAR", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
        getContentPane().add(inputState);
        inputState.setBounds(610, 320, 140, 35);

        txtNeighborhood.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtNeighborhood.setText("Bairro");
        getContentPane().add(txtNeighborhood);
        txtNeighborhood.setBounds(40, 290, 60, 24);

        inputNeighborhood.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(inputNeighborhood);
        inputNeighborhood.setBounds(40, 320, 360, 30);

        txtRequiredField1.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        txtRequiredField1.setForeground(new java.awt.Color(255, 0, 51));
        txtRequiredField1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtRequiredField1.setText("*");
        getContentPane().add(txtRequiredField1);
        txtRequiredField1.setBounds(510, 80, 20, 30);

        txtRequiredField2.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        txtRequiredField2.setForeground(new java.awt.Color(255, 0, 51));
        txtRequiredField2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtRequiredField2.setText("*");
        getContentPane().add(txtRequiredField2);
        txtRequiredField2.setBounds(100, 80, 20, 30);

        txtRequiredField3.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        txtRequiredField3.setForeground(new java.awt.Color(255, 0, 51));
        txtRequiredField3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtRequiredField3.setText("*");
        getContentPane().add(txtRequiredField3);
        txtRequiredField3.setBounds(90, 290, 30, 30);

        txtRequiredField4.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        txtRequiredField4.setForeground(new java.awt.Color(255, 0, 51));
        txtRequiredField4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtRequiredField4.setText("*");
        getContentPane().add(txtRequiredField4);
        txtRequiredField4.setBounds(670, 290, 30, 30);

        txtRequiredField5.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        txtRequiredField5.setForeground(new java.awt.Color(255, 0, 51));
        txtRequiredField5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtRequiredField5.setText("*");
        getContentPane().add(txtRequiredField5);
        txtRequiredField5.setBounds(725, 80, 20, 30);

        txtRequiredField6.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        txtRequiredField6.setForeground(new java.awt.Color(255, 0, 51));
        txtRequiredField6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtRequiredField6.setText("*");
        getContentPane().add(txtRequiredField6);
        txtRequiredField6.setBounds(80, 216, 20, 40);

        txtRequiredField7.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        txtRequiredField7.setForeground(new java.awt.Color(255, 0, 51));
        txtRequiredField7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtRequiredField7.setText("*");
        getContentPane().add(txtRequiredField7);
        txtRequiredField7.setBounds(230, 220, 20, 30);

        txtRequiredField8.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        txtRequiredField8.setForeground(new java.awt.Color(255, 0, 51));
        txtRequiredField8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtRequiredField8.setText("*");
        getContentPane().add(txtRequiredField8);
        txtRequiredField8.setBounds(470, 290, 30, 30);

        setSize(new java.awt.Dimension(930, 560));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSaveActionPerformed
        if(inputName.getText().equals("")||inputCPF.getText().equals("")||inputBirthDay.getText().equals("")||inputCEP.getText().equals("")||inputStreet.getText().equals("")||inputNeighborhood.getText().equals("")||inputCity.getText().equals("")||inputState.getSelectedItem().equals("SELECIONAR")){
            JOptionPane.showMessageDialog(null, "POR FAVOR, PREENCHA TODOS OS CAMPOS OBRIGATÓRIOS");
        }
        else if(getId()){
            JOptionPane.showMessageDialog(null, "USUÁRIO JÁ CADASTRADO NO SISTEMA");
        }
        else{
            add();
        }
    }//GEN-LAST:event_buttonSaveActionPerformed

    private void buttonPhotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPhotoActionPerformed
        if(buttonPhoto.getText().equals("FOTO")){
            TakePictureOrChoose takePictureOrChoose = new TakePictureOrChoose();
            takePictureOrChoose.newClient = this;
            takePictureOrChoose.itsANewClient=true;
            takePictureOrChoose.setVisible(true);
        }
        else{
            TakePictureOrChoose takePictureOrChoose = new TakePictureOrChoose();
            takePictureOrChoose.newClient = this;
            takePictureOrChoose.buttonShowPicture.setVisible(true);
            takePictureOrChoose.adress=imageAdress;
            takePictureOrChoose.itsANewClient=true;
            takePictureOrChoose.setVisible(true);
        }
    }//GEN-LAST:event_buttonPhotoActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        if(x==0){
            x++;
            inputName.requestFocus();
        }
    }//GEN-LAST:event_formWindowActivated

    private void inputCEPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputCEPKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            setInformations();
        }
    }//GEN-LAST:event_inputCEPKeyPressed

    private void inputCEPFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputCEPFocusLost
        if(!inputCEP.getText().equals("")){
            setInformations();
        }
    }//GEN-LAST:event_inputCEPFocusLost

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
            java.util.logging.Logger.getLogger(NewClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewClient().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton buttonPhoto;
    private javax.swing.JButton buttonSave;
    private javax.swing.JFormattedTextField inputBirthDay;
    private javax.swing.JFormattedTextField inputCEP;
    private javax.swing.JFormattedTextField inputCPF;
    private javax.swing.JFormattedTextField inputCellphone;
    private javax.swing.JTextField inputCity;
    private javax.swing.JTextField inputEmail;
    private javax.swing.JTextField inputName;
    private javax.swing.JTextField inputNeighborhood;
    private javax.swing.JTextField inputNumberHouse;
    private javax.swing.JTextArea inputObservation;
    private javax.swing.JFormattedTextField inputPhone;
    private javax.swing.JComboBox<String> inputState;
    private javax.swing.JTextField inputStreet;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel txtBirthDay;
    private javax.swing.JLabel txtCEP;
    private javax.swing.JLabel txtCPF;
    private javax.swing.JLabel txtCadastreClient;
    private javax.swing.JLabel txtCellPhone;
    private javax.swing.JLabel txtCity;
    private javax.swing.JLabel txtEmail;
    private javax.swing.JLabel txtName;
    private javax.swing.JLabel txtNeighborhood;
    private javax.swing.JLabel txtNumberHouse;
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
    private javax.swing.JLabel txtStreet;
    // End of variables declaration//GEN-END:variables
}
