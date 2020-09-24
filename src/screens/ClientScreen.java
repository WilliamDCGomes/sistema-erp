/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screens;

/**
 *
 * @author Lenovo
 */
public class ClientScreen extends javax.swing.JFrame {

    /**
     * Creates new form ClientScreen
     */
    public ClientScreen() {
        initComponents();
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
        txtComplement = new javax.swing.JLabel();
        txtEmail = new javax.swing.JLabel();
        txtCellPhone = new javax.swing.JLabel();
        txtCadastreClient = new javax.swing.JLabel();
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
        outputComplement = new javax.swing.JTextArea();
        buttonPhoto = new javax.swing.JButton();
        buttonEdit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cliente");
        setResizable(false);
        getContentPane().setLayout(null);

        txtBirthDay.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtBirthDay.setText("Data de Nascimento");
        getContentPane().add(txtBirthDay);
        txtBirthDay.setBounds(510, 60, 175, 24);

        txtName.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtName.setText("Nome");
        getContentPane().add(txtName);
        txtName.setBounds(20, 60, 52, 24);

        txtCPF.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtCPF.setText("CPF");
        getContentPane().add(txtCPF);
        txtCPF.setBounds(270, 60, 36, 24);

        txtPhone.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtPhone.setText("Telefone");
        getContentPane().add(txtPhone);
        txtPhone.setBounds(20, 150, 77, 24);

        txtCEP.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtCEP.setText("CEP");
        getContentPane().add(txtCEP);
        txtCEP.setBounds(20, 230, 37, 24);

        txtStreet.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtStreet.setText("Rua");
        getContentPane().add(txtStreet);
        txtStreet.setBounds(270, 230, 34, 24);

        txtNumberHouse.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtNumberHouse.setText("Nº");
        getContentPane().add(txtNumberHouse);
        txtNumberHouse.setBounds(510, 230, 20, 24);

        txtComplement.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtComplement.setText("Complemento");
        getContentPane().add(txtComplement);
        txtComplement.setBounds(20, 320, 124, 24);

        txtEmail.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtEmail.setText("E-mail");
        getContentPane().add(txtEmail);
        txtEmail.setBounds(510, 150, 55, 24);

        txtCellPhone.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtCellPhone.setText("Celular");
        getContentPane().add(txtCellPhone);
        txtCellPhone.setBounds(270, 150, 62, 24);

        txtCadastreClient.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        txtCadastreClient.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtCadastreClient.setText("Cliente");
        getContentPane().add(txtCadastreClient);
        txtCadastreClient.setBounds(170, 10, 320, 32);

        outputName.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(outputName);
        outputName.setBounds(20, 90, 219, 30);

        outputCPF.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(outputCPF);
        outputCPF.setBounds(270, 90, 130, 30);

        outputBirthDay.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(outputBirthDay);
        outputBirthDay.setBounds(510, 90, 159, 30);

        outputPhone.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(outputPhone);
        outputPhone.setBounds(20, 180, 184, 30);

        outputCellphone.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(outputCellphone);
        outputCellphone.setBounds(270, 180, 140, 30);

        outputEmail.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(outputEmail);
        outputEmail.setBounds(510, 180, 185, 30);

        outputCEP.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(outputCEP);
        outputCEP.setBounds(20, 260, 194, 30);

        outputStreet.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(outputStreet);
        outputStreet.setBounds(270, 260, 189, 30);

        outputNumberHouse.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(outputNumberHouse);
        outputNumberHouse.setBounds(510, 260, 98, 30);

        outputComplement.setColumns(20);
        outputComplement.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        outputComplement.setRows(5);
        jScrollPane1.setViewportView(outputComplement);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(20, 350, 553, 150);

        buttonPhoto.setText("FOTO");
        getContentPane().add(buttonPhoto);
        buttonPhoto.setBounds(600, 430, 61, 30);

        buttonEdit.setText("EDITAR");
        buttonEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEditActionPerformed(evt);
            }
        });
        getContentPane().add(buttonEdit);
        buttonEdit.setBounds(600, 470, 80, 30);

        setSize(new java.awt.Dimension(732, 567));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEditActionPerformed
        if(buttonEdit.getText().equals("EDITAR")){
            this.dispose();
            txtCadastreClient.setText("Editar Cliente");
            buttonEdit.setText("SALVAR");
            this.setTitle("Editar Cliente");
            this.setVisible(true);
        }
        else if(buttonEdit.getText().equals("SALVAR")){
                this.dispose();
                txtCadastreClient.setText("Cliente");
                buttonEdit.setText("EDITAR");
                this.setTitle("Cliente");
                this.setVisible(true);
        }
    }//GEN-LAST:event_buttonEditActionPerformed

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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JFormattedTextField outputBirthDay;
    private javax.swing.JFormattedTextField outputCEP;
    private javax.swing.JFormattedTextField outputCPF;
    private javax.swing.JFormattedTextField outputCellphone;
    private javax.swing.JTextArea outputComplement;
    private javax.swing.JTextField outputEmail;
    private javax.swing.JTextField outputName;
    private javax.swing.JTextField outputNumberHouse;
    private javax.swing.JFormattedTextField outputPhone;
    private javax.swing.JTextField outputStreet;
    private javax.swing.JLabel txtBirthDay;
    private javax.swing.JLabel txtCEP;
    private javax.swing.JLabel txtCPF;
    private javax.swing.JLabel txtCadastreClient;
    private javax.swing.JLabel txtCellPhone;
    private javax.swing.JLabel txtComplement;
    private javax.swing.JLabel txtEmail;
    private javax.swing.JLabel txtName;
    private javax.swing.JLabel txtNumberHouse;
    private javax.swing.JLabel txtPhone;
    private javax.swing.JLabel txtStreet;
    // End of variables declaration//GEN-END:variables
}
