package screens;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import connectionbd.ConnectionModule;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
public class AllProducts extends javax.swing.JFrame {
    int x=0;
    int x1=0;
    Connection connection = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    public AllProducts() {
        initComponents();
        ConnectionModule connect = new ConnectionModule();
        connection = connect.getConnectionMySQL();
    }
    private void getAllProducts(){
        String sql ="select barCode as 'Código', nameProduct as 'Nome', price as 'Preço', manyProduct as 'Quantidade' from product";
        try {
            pst=connection.prepareStatement(sql);
            rs= pst.executeQuery();
            tableProducts.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void getProductsByName(){
        String sql ="select barCode as 'Código', nameProduct as 'Nome', price as 'Preço', manyProduct as 'Quantidade' from product where nameProduct like '" + inputProductName.getText() +"%'";
        try {
            pst=connection.prepareStatement(sql);
            rs = pst.executeQuery();
            tableProducts.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtAllProducts = new javax.swing.JLabel();
        tableAllProducts = new javax.swing.JScrollPane();
        tableProducts = new javax.swing.JTable();
        inputProductName = new javax.swing.JTextField();
        buttonShow = new javax.swing.JButton();
        buttonPrinter = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Todos os Produtos");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(null);

        txtAllProducts.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        txtAllProducts.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtAllProducts.setText("TODOS OS PRODUTOS");
        getContentPane().add(txtAllProducts);
        txtAllProducts.setBounds(200, 30, 270, 32);

        tableProducts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Código", "Nome", "Preço", "Quantidade"
            }
        ));
        tableAllProducts.setViewportView(tableProducts);

        getContentPane().add(tableAllProducts);
        tableAllProducts.setBounds(20, 140, 640, 320);

        inputProductName.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        inputProductName.setText("NOME DO PRODUTO");
        inputProductName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                inputProductNameFocusGained(evt);
            }
        });
        inputProductName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                inputProductNameKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                inputProductNameKeyTyped(evt);
            }
        });
        getContentPane().add(inputProductName);
        inputProductName.setBounds(20, 90, 240, 30);

        buttonShow.setText("MOSTRAR");
        buttonShow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonShowActionPerformed(evt);
            }
        });
        getContentPane().add(buttonShow);
        buttonShow.setBounds(300, 90, 90, 30);

        buttonPrinter.setText("IMPRIMIR");
        getContentPane().add(buttonPrinter);
        buttonPrinter.setBounds(420, 90, 90, 30);

        setSize(new java.awt.Dimension(683, 509));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonShowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonShowActionPerformed
        int set = tableProducts.getSelectedRow();
        ProductScreen productScreen = new ProductScreen();
        productScreen.setTitle("Produto: " + tableProducts.getModel().getValueAt(set,0).toString());
        productScreen.setVisible(true);
    }//GEN-LAST:event_buttonShowActionPerformed

    private void inputProductNameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputProductNameKeyTyped
        if(inputProductName.getText().equals("")){
            inputProductName.setText("NOME DO PRODUTO");
            getAllProducts();
            x=0;
        }
        else if(x==0){
            x++;
            inputProductName.setText("");
        }
    }//GEN-LAST:event_inputProductNameKeyTyped

    private void inputProductNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputProductNameFocusGained
        inputProductName.selectAll();
    }//GEN-LAST:event_inputProductNameFocusGained

    private void inputProductNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputProductNameKeyReleased
        if(!inputProductName.getText().equals("NOME DO PRODUTO")){
            getProductsByName();
        }
    }//GEN-LAST:event_inputProductNameKeyReleased

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        if(x1==0){
            x1++;
            inputProductName.setText("NOME DO PRODUTO");
            getAllProducts();
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
            java.util.logging.Logger.getLogger(AllProducts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AllProducts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AllProducts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AllProducts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AllProducts().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonPrinter;
    private javax.swing.JButton buttonShow;
    private javax.swing.JTextField inputProductName;
    private javax.swing.JScrollPane tableAllProducts;
    private javax.swing.JTable tableProducts;
    public static javax.swing.JLabel txtAllProducts;
    // End of variables declaration//GEN-END:variables
}
