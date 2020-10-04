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
import functioncontroller.UpperLetterAux;
import java.awt.Image;
import javax.swing.ImageIcon;
/**
 *
 * @author Lenovo
 */
public class ClientScreen extends javax.swing.JFrame {
    int x = 0;
    Connection connection = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    PreparedStatement pst2 = null;
    ResultSet rs2 = null;
    String imageAdress = null;
    GetImageAdress getImageAdress = new GetImageAdress();
    /**
     * Creates new form ClientScreen
     */
    public ClientScreen() {
        initComponents();
        ConnectionModule connect = new ConnectionModule();
        connection = connect.getConnectionMySQL();
    }
    private void updateClient(int id){
        String sql = "update clients set clientName=?, cpf=?, birthDay=?, phone=?, cellPhone=?, email=?, cep=?, street=?, houseNumber=?, neighborhood=?, city=?, state=?, observation=?,photoAdress=? where id=?";
        try {
            pst=connection.prepareStatement(sql);
            pst.setString(1,outputName.getText());
            pst.setString(2,outputCPF.getText());
            pst.setString(3,outputBirthDay.getText());
            pst.setString(4,outputPhone.getText());
            pst.setString(5,outputCellphone.getText());
            pst.setString(6,outputEmail.getText());
            pst.setString(7,outputCEP.getText());
            pst.setString(8,outputStreet.getText());
            pst.setString(9,outputNumberHouse.getText());
            pst.setString(10,outputNeighborhood.getText());
            pst.setString(11,outputCity.getText());
            pst.setString(12,outputState.getSelectedItem().toString());
            pst.setString(13,outputObservation.getText());
            pst.setString(14,imageAdress);
            pst.setInt(15,id);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null,"CLIENTE ATUALIZADO COM SUCESSO");
            this.dispose();
            txtClient.setText("CLIENTE");
            buttonEdit.setText("EDITAR");
            String[] idAux = this.getTitle().split(" ");
            int idAuxSiz = idAux.length;
            this.setTitle("Cliente " + idAux[idAuxSiz - 1]);
            this.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }
    private void setClient(int id){
        String sql ="select clientName, cpf, birthDay, phone, cellPhone, email, cep, street, houseNumber, neighborhood, city, state, observation, photoAdress from clients where id=?";
        try {
            pst=connection.prepareStatement(sql);
            pst.setInt(1, id);
            rs= pst.executeQuery();
            if(rs.next()){
                outputName.setText(rs.getString(1));
                outputCPF.setText(rs.getString(2));
                outputBirthDay.setText(rs.getString(3));
                outputPhone.setText(rs.getString(4));
                outputCellphone.setText(rs.getString(5));
                outputEmail.setText(rs.getString(6));
                outputCEP.setText(rs.getString(7));
                outputStreet.setText(rs.getString(8));
                outputNumberHouse.setText(rs.getString(9));
                outputNeighborhood.setText(rs.getString(10));
                outputCity.setText(rs.getString(11));
                outputState.setSelectedItem(rs.getString(12));
                outputObservation.setText(rs.getString(13));
                if(!rs.getString(14).equals(null)){
                    buttonPhoto.setText("");
                }
                imageAdress = rs.getString(14);
                ImageIcon imagen = new ImageIcon(imageAdress);
                buttonPhoto.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(buttonPhoto.getWidth(), buttonPhoto.getHeight(), Image.SCALE_DEFAULT)));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void setInformations(){
        UpperLetterAux upperLetterAux = new UpperLetterAux();
        SearchCEP searchCEP = new SearchCEP();
        try {
            searchCEP.buscar(outputCEP.getText());
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
            outputNeighborhood.setText(neighBorhood);
            outputStreet.setText(street);
            outputCity.setText(city);
            outputState.setSelectedItem(searchCEP.getUf());
            outputNumberHouse.requestFocus();
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
        txtClient = new javax.swing.JLabel();
        outputName = new javax.swing.JTextField();
        outputCPF = new javax.swing.JFormattedTextField();
        outputBirthDay = new javax.swing.JFormattedTextField();
        outputPhone = new javax.swing.JFormattedTextField();
        outputCellphone = new javax.swing.JFormattedTextField();
        outputEmail = new javax.swing.JTextField();
        outputCEP = new javax.swing.JFormattedTextField();
        outputStreet = new javax.swing.JTextField();
        outputNumberHouse = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        outputObservation = new javax.swing.JTextArea();
        buttonPhoto = new javax.swing.JButton();
        buttonEdit = new javax.swing.JButton();
        txtCity = new javax.swing.JLabel();
        outputCity = new javax.swing.JTextField();
        txtState = new javax.swing.JLabel();
        outputState = new javax.swing.JComboBox<>();
        buttonPrinter = new javax.swing.JButton();
        txtNeighborhood = new javax.swing.JLabel();
        outputNeighborhood = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cliente");
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
        txtBirthDay.setBounds(500, 60, 175, 24);

        txtName.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtName.setText("Nome");
        getContentPane().add(txtName);
        txtName.setBounds(20, 60, 52, 24);

        txtCPF.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtCPF.setText("CPF");
        getContentPane().add(txtCPF);
        txtCPF.setBounds(360, 60, 36, 24);

        txtPhone.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtPhone.setText("Telefone");
        getContentPane().add(txtPhone);
        txtPhone.setBounds(20, 130, 77, 24);

        txtCEP.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtCEP.setText("CEP");
        getContentPane().add(txtCEP);
        txtCEP.setBounds(20, 200, 37, 24);

        txtStreet.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtStreet.setText("Rua");
        getContentPane().add(txtStreet);
        txtStreet.setBounds(160, 200, 34, 24);

        txtNumberHouse.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtNumberHouse.setText("Nº");
        getContentPane().add(txtNumberHouse);
        txtNumberHouse.setBounds(570, 200, 20, 24);

        txtObservation.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtObservation.setText("Observação");
        getContentPane().add(txtObservation);
        txtObservation.setBounds(20, 345, 105, 24);

        txtEmail.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtEmail.setText("E-mail");
        getContentPane().add(txtEmail);
        txtEmail.setBounds(370, 130, 55, 24);

        txtCellPhone.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtCellPhone.setText("Celular");
        getContentPane().add(txtCellPhone);
        txtCellPhone.setBounds(200, 130, 62, 24);

        txtClient.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        txtClient.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtClient.setText("CLIENTE");
        getContentPane().add(txtClient);
        txtClient.setBounds(260, 10, 320, 32);

        outputName.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(outputName);
        outputName.setBounds(20, 90, 330, 30);

        outputCPF.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(outputCPF);
        outputCPF.setBounds(360, 90, 130, 30);

        outputBirthDay.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(outputBirthDay);
        outputBirthDay.setBounds(500, 90, 159, 30);

        outputPhone.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(outputPhone);
        outputPhone.setBounds(20, 160, 170, 30);

        outputCellphone.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(outputCellphone);
        outputCellphone.setBounds(200, 160, 160, 30);

        outputEmail.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(outputEmail);
        outputEmail.setBounds(370, 160, 310, 30);

        outputCEP.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        outputCEP.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                outputCEPFocusLost(evt);
            }
        });
        outputCEP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                outputCEPKeyPressed(evt);
            }
        });
        getContentPane().add(outputCEP);
        outputCEP.setBounds(20, 230, 120, 30);

        outputStreet.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(outputStreet);
        outputStreet.setBounds(160, 230, 390, 30);

        outputNumberHouse.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(outputNumberHouse);
        outputNumberHouse.setBounds(570, 230, 98, 30);

        outputObservation.setColumns(20);
        outputObservation.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        outputObservation.setRows(5);
        jScrollPane1.setViewportView(outputObservation);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(20, 377, 690, 130);

        buttonPhoto.setText("FOTO");
        buttonPhoto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        buttonPhoto.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonPhoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPhotoActionPerformed(evt);
            }
        });
        getContentPane().add(buttonPhoto);
        buttonPhoto.setBounds(690, 60, 150, 200);

        buttonEdit.setText("EDITAR");
        buttonEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEditActionPerformed(evt);
            }
        });
        getContentPane().add(buttonEdit);
        buttonEdit.setBounds(750, 430, 80, 30);

        txtCity.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtCity.setText("Cidade");
        getContentPane().add(txtCity);
        txtCity.setBounds(390, 270, 80, 24);

        outputCity.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(outputCity);
        outputCity.setBounds(390, 300, 170, 30);

        txtState.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtState.setText("Estado");
        getContentPane().add(txtState);
        txtState.setBounds(570, 270, 80, 24);

        outputState.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        outputState.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SEM ESTADO", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
        getContentPane().add(outputState);
        outputState.setBounds(570, 300, 140, 35);

        buttonPrinter.setText("IMPRIMIR");
        getContentPane().add(buttonPrinter);
        buttonPrinter.setBounds(740, 470, 90, 32);

        txtNeighborhood.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtNeighborhood.setText("Bairro");
        getContentPane().add(txtNeighborhood);
        txtNeighborhood.setBounds(20, 270, 60, 24);

        outputNeighborhood.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(outputNeighborhood);
        outputNeighborhood.setBounds(20, 300, 360, 30);

        setSize(new java.awt.Dimension(855, 550));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    private void buttonEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEditActionPerformed
        if(buttonEdit.getText().equals("EDITAR")){
            this.dispose();
            txtClient.setText("EDITAR CLIENTE");
            buttonEdit.setText("SALVAR");
            String[] idAux = this.getTitle().split(" ");
            int idAuxSize = idAux.length;
            this.setTitle("Editar Cliente " + idAux[idAuxSize - 1]);
            this.setVisible(true);
        }
        else if(buttonEdit.getText().equals("SALVAR")){
            String[] idAux = this.getTitle().split(" ");
            int idAuxSize = idAux.length - 1;
            updateClient(Integer.parseInt(idAux[idAuxSize]));
        }
    }//GEN-LAST:event_buttonEditActionPerformed

    private void buttonPhotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPhotoActionPerformed
        if(txtClient.getText().equals("CLIENTE")){
            ImageScreen imageScreen = new ImageScreen();
            imageScreen.adress = imageAdress;
            imageScreen.setVisible(true);
        }
        else if(txtClient.getText().equals("EDITAR CLIENTE")){
            imageAdress = getImageAdress.getAdress();
            if(!imageAdress.equals(null)){
                buttonPhoto.setText("");
            }
            ImageIcon imagen = new ImageIcon(imageAdress);
            buttonPhoto.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(buttonPhoto.getWidth(), buttonPhoto.getHeight(), Image.SCALE_DEFAULT)));
        }
    }//GEN-LAST:event_buttonPhotoActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        if(x==0){
            x++;
            String[] idAux = this.getTitle().split(" ");
            int idAuxSiz = idAux.length;
            setClient(Integer.parseInt(idAux[idAuxSiz - 1]));
        }
    }//GEN-LAST:event_formWindowActivated

    private void outputCEPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_outputCEPKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER&&txtClient.getText().equals("EDITAR CLIENTE")){
            setInformations();
        }
    }//GEN-LAST:event_outputCEPKeyPressed

    private void outputCEPFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_outputCEPFocusLost
        if(!outputCEP.getText().equals("")&&txtClient.getText().equals("EDITAR CLIENTE")){
            setInformations();
        }
    }//GEN-LAST:event_outputCEPFocusLost

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
            java.util.logging.Logger.getLogger(ClientScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClientScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClientScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClientScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonEdit;
    private javax.swing.JButton buttonPhoto;
    private javax.swing.JButton buttonPrinter;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JFormattedTextField outputBirthDay;
    private javax.swing.JFormattedTextField outputCEP;
    private javax.swing.JFormattedTextField outputCPF;
    private javax.swing.JFormattedTextField outputCellphone;
    private javax.swing.JTextField outputCity;
    private javax.swing.JTextField outputEmail;
    private javax.swing.JTextField outputName;
    private javax.swing.JTextField outputNeighborhood;
    private javax.swing.JTextField outputNumberHouse;
    private javax.swing.JTextArea outputObservation;
    private javax.swing.JFormattedTextField outputPhone;
    private javax.swing.JComboBox<String> outputState;
    private javax.swing.JTextField outputStreet;
    private javax.swing.JLabel txtBirthDay;
    private javax.swing.JLabel txtCEP;
    private javax.swing.JLabel txtCPF;
    private javax.swing.JLabel txtCellPhone;
    private javax.swing.JLabel txtCity;
    private javax.swing.JLabel txtClient;
    private javax.swing.JLabel txtEmail;
    private javax.swing.JLabel txtName;
    private javax.swing.JLabel txtNeighborhood;
    private javax.swing.JLabel txtNumberHouse;
    private javax.swing.JLabel txtObservation;
    private javax.swing.JLabel txtPhone;
    private javax.swing.JLabel txtState;
    private javax.swing.JLabel txtStreet;
    // End of variables declaration//GEN-END:variables
}
