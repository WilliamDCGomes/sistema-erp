package screens;

import java.awt.Image;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import connectionbd.ConnectionModule;

/**
 *
 * @author willi_bg
 */
public class TakePictureOrChoose extends javax.swing.JFrame {
    public NewClient newClient;
    public ClientScreen clientScreen;
    public NewProduct newProduct;
    public ProductScreen productScreen;
    public NewEmployee newEmployee;
    public EmployeeScreen employeeScreen;
    Connection connection = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    String adress;
    public boolean itsANewClient = false;
    public boolean itsANewProduct = false;
    public boolean itsANewEmployee = false;
    /**
     * Creates new form TakePictureOrChoose
     */
    public TakePictureOrChoose() {
        initComponents();
        ConnectionModule connect = new ConnectionModule();
        connection = connect.getConnectionMySQL();
        buttonShowPicture.setVisible(false);
    }
    public void timeSetClient(String begin){
        int delay = 100;   // tempo de espera antes da 1ª execução da tarefa.
        int interval = 1000;  // intervalo no qual a tarefa será executada.
        java.util.Timer timer = new java.util.Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                if(!begin.equals(adress)){
                    if(itsANewClient==true){
                        newClient.imageAdress = adress;
                        if(!newClient.imageAdress.equals("")){
                            newClient.buttonPhoto.setText("");
                        }
                        ImageIcon imagen = new ImageIcon(newClient.imageAdress);
                        newClient.buttonPhoto.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(newClient.buttonPhoto.getWidth(), newClient.buttonPhoto.getHeight(), Image.SCALE_DEFAULT)));
                        timer.cancel();
                    }
                    else{
                        clientScreen.imageAdress = adress;
                        if(!clientScreen.imageAdress.equals(null)){
                            clientScreen.buttonPhoto.setText("");
                        }
                        ImageIcon imagen = new ImageIcon(clientScreen.imageAdress);
                        clientScreen.buttonPhoto.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(clientScreen.buttonPhoto.getWidth(), clientScreen.buttonPhoto.getHeight(), Image.SCALE_DEFAULT)));
                        timer.cancel();
                    }
                }
                adress=getAdress();
            }
        }, delay, interval);
        this.dispose();
    }
    public void timeSetProduct(String begin){
        int delay = 100;   // tempo de espera antes da 1ª execução da tarefa.
        int interval = 1000;  // intervalo no qual a tarefa será executada.
        java.util.Timer timer = new java.util.Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                if(!begin.equals(adress)){
                    if(itsANewProduct==true){
                        newProduct.imageAdress = adress;
                        if(!newProduct.imageAdress.equals("")){
                            newProduct.inputPhoto.setText("");
                        }
                        ImageIcon imagen = new ImageIcon(newProduct.imageAdress);
                        newProduct.inputPhoto.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(newProduct.inputPhoto.getWidth(), newProduct.inputPhoto.getHeight(), Image.SCALE_DEFAULT)));
                        timer.cancel();
                    }
                    else{
                        productScreen.imageAdress = adress;
                        if(!productScreen.imageAdress.equals(null)){
                            productScreen.outputPhoto.setText("");
                        }
                        ImageIcon imagen = new ImageIcon(productScreen.imageAdress);
                        productScreen.outputPhoto.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(productScreen.outputPhoto.getWidth(), productScreen.outputPhoto.getHeight(), Image.SCALE_DEFAULT)));
                        timer.cancel();
                    }
                }
                adress=getAdress();
            }
        }, delay, interval);
        this.dispose();
    }
    public void timeSetEmployee(String begin){
        int delay = 100;   // tempo de espera antes da 1ª execução da tarefa.
        int interval = 1000;  // intervalo no qual a tarefa será executada.
        java.util.Timer timer = new java.util.Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                if(!begin.equals(adress)){
                    if(itsANewEmployee==true){
                        newEmployee.imageAdress = adress;
                        if(!newEmployee.imageAdress.equals("")){
                            newEmployee.inputPhoto.setText("");
                        }
                        ImageIcon imagen = new ImageIcon(newEmployee.imageAdress);
                        newEmployee.inputPhoto.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(newEmployee.inputPhoto.getWidth(), newEmployee.inputPhoto.getHeight(), Image.SCALE_DEFAULT)));
                        timer.cancel();
                    }
                    else{
                        employeeScreen.imageAdress = adress;
                        if(!employeeScreen.imageAdress.equals(null)){
                            employeeScreen.outputPhoto.setText("");
                        }
                        ImageIcon imagen = new ImageIcon(employeeScreen.imageAdress);
                        employeeScreen.outputPhoto.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(employeeScreen.outputPhoto.getWidth(), employeeScreen.outputPhoto.getHeight(), Image.SCALE_DEFAULT)));
                        timer.cancel();
                    }
                }
                adress=getAdress();
            }
        }, delay, interval);
        this.dispose();
    }
    public String getAdress(){
        String sql ="select adress from temporaryAdress where id = (select max(id) from temporaryAdress)";
        try {
            pst=connection.prepareStatement(sql);
            rs= pst.executeQuery();
            if(rs.next()){
                return rs.getString(1);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonTakePicture = new javax.swing.JButton();
        buttonChoosePicture = new javax.swing.JButton();
        buttonShowPicture = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Upload de Imagem");
        setResizable(false);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
                formWindowLostFocus(evt);
            }
        });
        getContentPane().setLayout(null);

        buttonTakePicture.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        buttonTakePicture.setText("TIRAR FOTO");
        buttonTakePicture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTakePictureActionPerformed(evt);
            }
        });
        getContentPane().add(buttonTakePicture);
        buttonTakePicture.setBounds(50, 40, 160, 29);

        buttonChoosePicture.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        buttonChoosePicture.setText("ESCOLHER FOTO");
        buttonChoosePicture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonChoosePictureActionPerformed(evt);
            }
        });
        getContentPane().add(buttonChoosePicture);
        buttonChoosePicture.setBounds(50, 80, 160, 29);

        buttonShowPicture.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        buttonShowPicture.setText("VER FOTO");
        buttonShowPicture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonShowPictureActionPerformed(evt);
            }
        });
        getContentPane().add(buttonShowPicture);
        buttonShowPicture.setBounds(50, 120, 160, 29);

        setSize(new java.awt.Dimension(285, 215));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonChoosePictureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonChoosePictureActionPerformed
        this.dispose();
        if(newClient!=null){
            if(itsANewClient==true){
                newClient.imageAdress = newClient.getImageAdress.getAdress();
                if(!newClient.imageAdress.equals("")){
                    newClient.buttonPhoto.setText("");
                }
                ImageIcon imagen = new ImageIcon(newClient.imageAdress);
                newClient.buttonPhoto.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(newClient.buttonPhoto.getWidth(), newClient.buttonPhoto.getHeight(), Image.SCALE_DEFAULT)));
            }
            else{
                clientScreen.imageAdress = clientScreen.getImageAdress.getAdress();
                if(!clientScreen.imageAdress.equals("")){
                    clientScreen.buttonPhoto.setText("");
                }
                ImageIcon imagen = new ImageIcon(clientScreen.imageAdress);
                clientScreen.buttonPhoto.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(clientScreen.buttonPhoto.getWidth(), clientScreen.buttonPhoto.getHeight(), Image.SCALE_DEFAULT)));
            }
        }
        else if(newProduct!=null){
            if(itsANewProduct==true){
                newProduct.imageAdress = newProduct.getImageAdress.getAdress();
                if(!newProduct.imageAdress.equals("")){
                    newProduct.inputPhoto.setText("");
                }
                ImageIcon imagen = new ImageIcon(newProduct.imageAdress);
                newProduct.inputPhoto.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(newProduct.inputPhoto.getWidth(), newProduct.inputPhoto.getHeight(), Image.SCALE_DEFAULT)));
            }
            else{
                productScreen.imageAdress = productScreen.getImageAdress.getAdress();
                if(!productScreen.imageAdress.equals("")){
                    productScreen.outputPhoto.setText("");
                }
                ImageIcon imagen = new ImageIcon(productScreen.imageAdress);
                productScreen.outputPhoto.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(productScreen.outputPhoto.getWidth(), productScreen.outputPhoto.getHeight(), Image.SCALE_DEFAULT)));
            }
        }
        else if(newEmployee!=null){
            if(itsANewEmployee==true){
                newEmployee.imageAdress = newEmployee.getImageAdress.getAdress();
                if(!newEmployee.imageAdress.equals("")){
                    newEmployee.inputPhoto.setText("");
                }
                ImageIcon imagen = new ImageIcon(newEmployee.imageAdress);
                newEmployee.inputPhoto.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(newEmployee.inputPhoto.getWidth(), newEmployee.inputPhoto.getHeight(), Image.SCALE_DEFAULT)));
            }
            else{
                employeeScreen.imageAdress = employeeScreen.getImageAdress.getAdress();
                if(!employeeScreen.imageAdress.equals("")){
                    employeeScreen.outputPhoto.setText("");
                }
                ImageIcon imagen = new ImageIcon(employeeScreen.imageAdress);
                employeeScreen.outputPhoto.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(employeeScreen.outputPhoto.getWidth(), employeeScreen.outputPhoto.getHeight(), Image.SCALE_DEFAULT)));
            }
        }
    }//GEN-LAST:event_buttonChoosePictureActionPerformed

    private void buttonTakePictureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTakePictureActionPerformed
        try{
            Runtime.getRuntime().exec("D:\\Programing\\Sistema ERP\\IniciaCamera.bat");
            adress=getAdress();
            if(newClient!=null){
                timeSetClient(getAdress());
            }
            else if(newProduct!=null){
                timeSetProduct(getAdress());
            }
            else if(newEmployee!=null){
                timeSetEmployee(getAdress());
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "ERRO: " + e);
            this.dispose();
        }
    }//GEN-LAST:event_buttonTakePictureActionPerformed

    private void buttonShowPictureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonShowPictureActionPerformed
        ImageScreen imageScreen = new ImageScreen();
        imageScreen.adress=adress;
        imageScreen.buttonPrinter.setText("EXCLUIR");
        if(newClient!=null){
            if(itsANewClient==true){
                imageScreen.newClient = newClient;
                imageScreen.itsANewClient=true;
            }
            else{
                imageScreen.clientScreen = clientScreen;
            }
        }
        else if(newProduct!=null){
            if(itsANewProduct==true){
                imageScreen.newProduct = newProduct;
                imageScreen.itsANewProduct=true;
            }
            else{
                imageScreen.productScreen = productScreen;
            }
        }
        else if(newEmployee!=null){
            if(itsANewEmployee==true){
                imageScreen.newEmployee = newEmployee;
                imageScreen.itsANewEmployee=true;
            }
            else{
                imageScreen.employeeScreen = employeeScreen;
            }
        }
        imageScreen.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_buttonShowPictureActionPerformed

    private void formWindowLostFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowLostFocus
        this.dispose();
    }//GEN-LAST:event_formWindowLostFocus

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
            java.util.logging.Logger.getLogger(TakePictureOrChoose.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TakePictureOrChoose.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TakePictureOrChoose.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TakePictureOrChoose.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TakePictureOrChoose().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonChoosePicture;
    public static javax.swing.JButton buttonShowPicture;
    private javax.swing.JButton buttonTakePicture;
    // End of variables declaration//GEN-END:variables
}
