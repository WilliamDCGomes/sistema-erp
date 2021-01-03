package screens;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import connectionbd.ConnectionModule;
import formattingmask.MaskCash;
import formattingmask.MaskJustNumbers;
import functioncontroller.GetImageAdress;
import formattingmask.MaskUpperLetter;
import functioncontroller.RoundNumber;
import java.awt.Image;
import javax.swing.ImageIcon;
public class NewProduct extends javax.swing.JFrame {
    int x = 0;
    Connection connection = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    PreparedStatement pst2 = null;
    ResultSet rs2 = null;
    String imageAdress = null;
    GetImageAdress getImageAdress = new GetImageAdress();
    String productID;
    /**
     * Creates new form NewProduct
     */
    public NewProduct() {
        initComponents();
        ConnectionModule connect = new ConnectionModule();
        connection = connect.getConnectionMySQL();
        inputName.setDocument(new MaskUpperLetter());
        inputLocalization.setDocument(new MaskUpperLetter());
        inputBrand.setDocument(new MaskUpperLetter());
        inputDescription.setDocument(new MaskUpperLetter());
        inputBarCode.setDocument(new MaskUpperLetter());
        inputQuantity.setDocument(new MaskJustNumbers());
        inputMinimumQuantity.setDocument(new MaskJustNumbers());
        inputExpense.setDocument(new MaskCash());
        inputPrice.setDocument(new MaskCash());
        inputProfit.setDocument(new MaskCash());
        inputProfitPercentage.setDocument(new MaskCash());
        inputProvider.setDocument(new MaskJustNumbers());
    }
    private void add(){
        String sql = "insert into product(barCode, nameProduct, manyProduct, manyMinimumProduct, expensive, price, profit, profitPercent, location, brand, provider, descrition, photoAdress)values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1,inputBarCode.getText());
            pst.setString(2,inputName.getText());
            pst.setInt(3,Integer.parseInt( inputQuantity.getText() ));
            pst.setInt(4,Integer.parseInt( inputMinimumQuantity.getText() ));
            pst.setString(5,inputExpense.getText().replace(',', '.'));
            pst.setString(6,inputPrice.getText().replace(',', '.'));
            pst.setString(7,inputProfit.getText().replace(',', '.'));
            pst.setString(8,inputProfitPercentage.getText().replace(',', '.'));
            pst.setString(9,inputLocalization.getText());
            pst.setString(10,inputBrand.getText());
            pst.setString(11,inputNameProvider.getText());
            pst.setString(12,inputDescription.getText());
            pst.setString(13,imageAdress);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null,"PRODUTO CADASTRADO COM SUCESSO");
            ProductScreen productScreen = new ProductScreen();
            this.dispose();
            productScreen.setTitle("Produto: " + inputBarCode.getText());
            productScreen.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void updateProduct(){
        String[] id = this.getTitle().split(" ");
        String sql = "update product set barCode=?, nameProduct=?, manyProduct=?, manyMinimumProduct=?, expensive=?, price=?, profit=?, profitPercent=?, location=?, brand=?, provider=?, descrition=?, photoAdress=? where barCode=?";
        try {
            pst=connection.prepareStatement(sql);
            pst.setString(1,inputBarCode.getText());
            pst.setString(2,inputName.getText());
            pst.setInt(3,Integer.parseInt( inputQuantity.getText() ));
            pst.setInt(4,Integer.parseInt( inputMinimumQuantity.getText() ));
            pst.setString(5,inputExpense.getText().replace(',', '.'));
            pst.setString(6,inputPrice.getText().replace(',', '.'));
            pst.setString(7,inputProfit.getText().replace(',', '.'));
            pst.setString(8,inputProfitPercentage.getText().replace(',', '.'));
            pst.setString(9,inputLocalization.getText());
            pst.setString(10,inputBrand.getText());
            pst.setString(11,inputNameProvider.getText());
            pst.setString(12,inputDescription.getText());
            pst.setString(13,imageAdress);
            pst.setString(14,id[2]);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null,"PRODUTO ATUALIZADO COM SUCESSO");
            ProductScreen productScreen = new ProductScreen();
            this.dispose();
            productScreen.setTitle("Produto: " + inputBarCode.getText());
            productScreen.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }
    private void delete(String id){
        int confirma = JOptionPane.showConfirmDialog(null, "TEM CERTEZA QUE DESEJA DELETAR ESSE PRODUTO?","ATENÇÃO",JOptionPane.YES_NO_OPTION);
        if(confirma==JOptionPane.YES_OPTION){
            String sql = "delete from product where barCode = ?";
            try {
                pst=connection.prepareStatement(sql);
                pst.setString(1, id);
                int deleted = pst.executeUpdate();
                if(deleted>0){
                    JOptionPane.showMessageDialog(null,"PRODUTO DELETADO COM SUCESSO");
                    this.dispose();
                }
                else{
                    JOptionPane.showMessageDialog(null,"PRODUTO NÃO CADASTRADO NO BANCO DE DADOS");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }
    private void setProduct(){
        String[] id = this.getTitle().split(" ");
        inputBarCode.setText(id[2]);
        String sql ="select nameProduct, manyProduct, manyMinimumProduct, expensive, price, profit, profitPercent, location, brand, provider, descrition, photoAdress from product where barCode=?";
        try {
            pst=connection.prepareStatement(sql);
            pst.setString(1, id[2]);
            rs= pst.executeQuery();
            if(rs.next()){
                inputName.setText(rs.getString(1));
                inputQuantity.setText(rs.getString(2));
                inputMinimumQuantity.setText(rs.getString(3));
                inputExpense.setText(rs.getString(4).replace('.', ','));
                inputPrice.setText(rs.getString(5).replace('.', ','));
                inputProfit.setText(rs.getString(6).replace('.', ','));
                inputProfitPercentage.setText(rs.getString(7).replace('.', ','));
                inputLocalization.setText(rs.getString(8));
                inputBrand.setText(rs.getString(9));
                inputProvider.setText(rs.getString(10));
                inputDescription.setText(rs.getString(11));
                if(!rs.getString(12).equals("")){
                    inputPhoto.setText("");
                    imageAdress = rs.getString(12);
                    ImageIcon imagen = new ImageIcon(imageAdress);
                    inputPhoto.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(inputPhoto.getWidth(), inputPhoto.getHeight(), Image.SCALE_DEFAULT)));
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private boolean getId(){
        String sql ="select id,nameProduct from product where barCode=?";
        try {
            pst2=connection.prepareStatement(sql);
            pst2.setString(1, inputBarCode.getText());
            rs2= pst2.executeQuery();
            if(rs2.next()){
                productID = Integer.toString(rs2.getInt(1));
                if(rs2.getString(2)!=null){
                    return true;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return false;
    }
    private void LocaleProvider(){
        String sql ="select fantasyName from provider where codeProvider=?";
        try {
            pst2=connection.prepareStatement(sql);
            pst2.setString(1, inputProvider.getText());
            rs2= pst2.executeQuery();
            if(rs2.next()){
                inputNameProvider.setText(rs2.getString(1));
            }
            else{
                JOptionPane.showMessageDialog(null, "FORNECEDOR NÃO ENCONTRADO NO BANCO DE DADOS!");
                inputProvider.setText("");
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

        txtName = new javax.swing.JLabel();
        txtDescription = new javax.swing.JLabel();
        txtExpense = new javax.swing.JLabel();
        txtProfit = new javax.swing.JLabel();
        txtQuantity = new javax.swing.JLabel();
        txtProvider = new javax.swing.JLabel();
        txtBarCode = new javax.swing.JLabel();
        txtLocalization = new javax.swing.JLabel();
        inputProvider = new javax.swing.JTextField();
        inputQuantity = new javax.swing.JTextField();
        inputBrand = new javax.swing.JTextField();
        inputProfitPercentage = new javax.swing.JTextField();
        inputExpense = new javax.swing.JTextField();
        txtProftPercentage = new javax.swing.JLabel();
        inputLocalization = new javax.swing.JTextField();
        txtPrice = new javax.swing.JLabel();
        inputProfit = new javax.swing.JTextField();
        inputName = new javax.swing.JTextField();
        inputPrice = new javax.swing.JTextField();
        inputBarCode = new javax.swing.JTextField();
        inputPhoto = new javax.swing.JButton();
        buttonCancel = new javax.swing.JButton();
        txtBrand = new javax.swing.JLabel();
        tableNewProduct = new javax.swing.JScrollPane();
        inputDescription = new javax.swing.JTextArea();
        txtNewProduct = new javax.swing.JLabel();
        buttonSave = new javax.swing.JButton();
        txtRequiredField2 = new javax.swing.JLabel();
        txtRequiredField3 = new javax.swing.JLabel();
        txtRequiredField4 = new javax.swing.JLabel();
        buttonAll = new javax.swing.JButton();
        buttonLocale = new javax.swing.JButton();
        txtMinimumQuantity = new javax.swing.JLabel();
        inputMinimumQuantity = new javax.swing.JTextField();
        txtPercent = new javax.swing.JLabel();
        txtNameProvider = new javax.swing.JLabel();
        inputNameProvider = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Novo Produto");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(null);

        txtName.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtName.setText("Nome");
        getContentPane().add(txtName);
        txtName.setBounds(180, 80, 60, 20);

        txtDescription.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtDescription.setText("Descrição");
        getContentPane().add(txtDescription);
        txtDescription.setBounds(20, 430, 80, 20);

        txtExpense.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtExpense.setText("Custo");
        getContentPane().add(txtExpense);
        txtExpense.setBounds(320, 150, 80, 20);

        txtProfit.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtProfit.setText("Lucro");
        getContentPane().add(txtProfit);
        txtProfit.setBounds(20, 220, 50, 20);

        txtQuantity.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtQuantity.setText("Quantidade");
        getContentPane().add(txtQuantity);
        txtQuantity.setBounds(20, 150, 110, 20);

        txtProvider.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtProvider.setText("Código do Fornecedor");
        getContentPane().add(txtProvider);
        txtProvider.setBounds(20, 290, 190, 20);

        txtBarCode.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtBarCode.setText("Código");
        getContentPane().add(txtBarCode);
        txtBarCode.setBounds(20, 80, 70, 20);

        txtLocalization.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtLocalization.setText("Localização");
        getContentPane().add(txtLocalization);
        txtLocalization.setBounds(400, 220, 110, 20);

        inputProvider.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        inputProvider.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                inputProviderFocusGained(evt);
            }
        });
        inputProvider.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                inputProviderKeyTyped(evt);
            }
        });
        getContentPane().add(inputProvider);
        inputProvider.setBounds(20, 320, 140, 30);

        inputQuantity.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        inputQuantity.setText("1");
        getContentPane().add(inputQuantity);
        inputQuantity.setBounds(20, 180, 80, 30);

        inputBrand.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(inputBrand);
        inputBrand.setBounds(20, 390, 240, 30);

        inputProfitPercentage.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        inputProfitPercentage.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                inputProfitPercentageFocusLost(evt);
            }
        });
        getContentPane().add(inputProfitPercentage);
        inputProfitPercentage.setBounds(170, 250, 90, 30);

        inputExpense.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        inputExpense.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                inputExpenseFocusLost(evt);
            }
        });
        getContentPane().add(inputExpense);
        inputExpense.setBounds(320, 180, 90, 30);

        txtProftPercentage.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtProftPercentage.setText("Porcentagem de Lucro");
        getContentPane().add(txtProftPercentage);
        txtProftPercentage.setBounds(170, 220, 180, 20);

        inputLocalization.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(inputLocalization);
        inputLocalization.setBounds(400, 250, 130, 30);

        txtPrice.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtPrice.setText("Preço de Venda");
        getContentPane().add(txtPrice);
        txtPrice.setBounds(440, 150, 130, 20);

        inputProfit.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(inputProfit);
        inputProfit.setBounds(20, 250, 90, 30);

        inputName.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(inputName);
        inputName.setBounds(180, 110, 330, 30);

        inputPrice.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        inputPrice.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                inputPriceFocusLost(evt);
            }
        });
        getContentPane().add(inputPrice);
        inputPrice.setBounds(440, 180, 90, 30);

        inputBarCode.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(inputBarCode);
        inputBarCode.setBounds(20, 110, 130, 30);

        inputPhoto.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        inputPhoto.setText("FOTO");
        inputPhoto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        inputPhoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputPhotoActionPerformed(evt);
            }
        });
        getContentPane().add(inputPhoto);
        inputPhoto.setBounds(590, 90, 150, 190);

        buttonCancel.setText("CANCELAR");
        buttonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelActionPerformed(evt);
            }
        });
        getContentPane().add(buttonCancel);
        buttonCancel.setBounds(640, 575, 100, 25);

        txtBrand.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtBrand.setText("Marca");
        getContentPane().add(txtBrand);
        txtBrand.setBounds(20, 360, 80, 20);

        inputDescription.setColumns(20);
        inputDescription.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        inputDescription.setRows(5);
        tableNewProduct.setViewportView(inputDescription);

        getContentPane().add(tableNewProduct);
        tableNewProduct.setBounds(20, 460, 520, 140);

        txtNewProduct.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtNewProduct.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtNewProduct.setText("NOVO PRODUTO");
        getContentPane().add(txtNewProduct);
        txtNewProduct.setBounds(280, 10, 220, 60);

        buttonSave.setText("SALVAR");
        buttonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSaveActionPerformed(evt);
            }
        });
        getContentPane().add(buttonSave);
        buttonSave.setBounds(550, 575, 80, 25);

        txtRequiredField2.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        txtRequiredField2.setForeground(new java.awt.Color(255, 0, 51));
        txtRequiredField2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtRequiredField2.setText("*");
        getContentPane().add(txtRequiredField2);
        txtRequiredField2.setBounds(80, 80, 20, 30);

        txtRequiredField3.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        txtRequiredField3.setForeground(new java.awt.Color(255, 0, 51));
        txtRequiredField3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtRequiredField3.setText("*");
        getContentPane().add(txtRequiredField3);
        txtRequiredField3.setBounds(230, 80, 20, 30);

        txtRequiredField4.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        txtRequiredField4.setForeground(new java.awt.Color(255, 0, 51));
        txtRequiredField4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtRequiredField4.setText("*");
        getContentPane().add(txtRequiredField4);
        txtRequiredField4.setBounds(560, 150, 20, 30);

        buttonAll.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        buttonAll.setText("TODOS");
        buttonAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAllActionPerformed(evt);
            }
        });
        getContentPane().add(buttonAll);
        buttonAll.setBounds(280, 320, 80, 30);

        buttonLocale.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        buttonLocale.setText("LOCALIZAR");
        buttonLocale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLocaleActionPerformed(evt);
            }
        });
        getContentPane().add(buttonLocale);
        buttonLocale.setBounds(170, 320, 100, 30);

        txtMinimumQuantity.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtMinimumQuantity.setText("Quantidade Mínima");
        getContentPane().add(txtMinimumQuantity);
        txtMinimumQuantity.setBounds(140, 150, 150, 20);

        inputMinimumQuantity.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        inputMinimumQuantity.setText("1");
        getContentPane().add(inputMinimumQuantity);
        inputMinimumQuantity.setBounds(140, 180, 80, 30);

        txtPercent.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        txtPercent.setText("%");
        getContentPane().add(txtPercent);
        txtPercent.setBounds(270, 250, 20, 30);

        txtNameProvider.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtNameProvider.setText("Nome do Fornecedor");
        getContentPane().add(txtNameProvider);
        txtNameProvider.setBounds(390, 290, 170, 20);

        inputNameProvider.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(inputNameProvider);
        inputNameProvider.setBounds(390, 320, 350, 30);

        setSize(new java.awt.Dimension(769, 642));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        if(x==0){
            x++;
            inputBarCode.requestFocus();
            inputProvider.setText("CÓDIGO DO FORNECEDOR");
            inputMinimumQuantity.setText("1");
            inputQuantity.setText("1");
            if(txtNewProduct.getText().equals("EDITAR PRODUTO")){
                buttonCancel.setText("EXCLUIR");
                setProduct();
            }
        }
    }//GEN-LAST:event_formWindowActivated

    private void buttonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelActionPerformed
        if(buttonCancel.getText().equals("EXCLUIR")){
            String[] id = this.getTitle().split(" ");
            delete(id[2]);
        }
        else if(buttonCancel.getText().equals("CANCELAR")){
            this.dispose();
        }
    }//GEN-LAST:event_buttonCancelActionPerformed

    private void buttonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSaveActionPerformed
        if(txtNewProduct.getText().equals("NOVO PRODUTO")){
            if(inputName.getText().equals("")||inputBarCode.getText().equals("")||inputPrice.getText().equals("")){
                JOptionPane.showMessageDialog(null, "POR FAVOR, PREENCHA TODOS OS CAMPOS OBRIGATÓRIOS");
            }
            else if(getId()){
                JOptionPane.showMessageDialog(null, "PRODUTO JÁ CADASTRADO NO SISTEMA");
            }
            else{
                add();
            }
        }
        else if(txtNewProduct.getText().equals("EDITAR PRODUTO")){
            String[] id = this.getTitle().split(" ");
            if(inputName.getText().equals("")||inputBarCode.getText().equals("")||inputPrice.getText().equals("")){
                JOptionPane.showMessageDialog(null, "POR FAVOR, PREENCHA TODOS OS CAMPOS OBRIGATÓRIOS");
            }
            else if(id[2].equals(inputBarCode.getText())){
                updateProduct();
            }
            else if(!getId()){
                updateProduct();
            }
            else{
                JOptionPane.showMessageDialog(null, "O NOVO CÓDIGO QUE VOCÊ DIGITOU JÁ EXISTE NO BANCO DE DADOS!\nPOR FAVOR, INSIRA OUTRO");
            }
        }
    }//GEN-LAST:event_buttonSaveActionPerformed

    private void inputPhotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputPhotoActionPerformed
        if(inputPhoto.getText().equals("FOTO")){
            TakePictureOrChoose takePictureOrChoose = new TakePictureOrChoose();
            takePictureOrChoose.newProduct = this;
            takePictureOrChoose.itsANewProduct=true;
            takePictureOrChoose.setVisible(true);
        }
        else{
            TakePictureOrChoose takePictureOrChoose = new TakePictureOrChoose();
            takePictureOrChoose.newProduct = this;
            takePictureOrChoose.buttonShowPicture.setVisible(true);
            takePictureOrChoose.adress=imageAdress;
            takePictureOrChoose.itsANewProduct=true;
            takePictureOrChoose.setVisible(true);
        }
    }//GEN-LAST:event_inputPhotoActionPerformed

    private void buttonAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAllActionPerformed
        AllProviders allProviders = new AllProviders();
        allProviders.setVisible(true);
    }//GEN-LAST:event_buttonAllActionPerformed

    private void buttonLocaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLocaleActionPerformed
        if(inputProvider.getText().equals("")){
            JOptionPane.showMessageDialog(null, "DIGITE O CÓDIGO DO FORNECEDOR");
        }
        else{
            LocaleProvider();
        }
    }//GEN-LAST:event_buttonLocaleActionPerformed

    private void inputProviderFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputProviderFocusGained
        inputProvider.selectAll();
    }//GEN-LAST:event_inputProviderFocusGained

    private void inputProviderKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputProviderKeyTyped
        
    }//GEN-LAST:event_inputProviderKeyTyped

    private void inputPriceFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputPriceFocusLost
        if(!inputExpense.getText().equals("")&&!inputPrice.getText().equals("")){
            RoundNumber roundNumber = new RoundNumber();
            String expensiveString = inputExpense.getText().replace(',', '.');
            String priceString = inputPrice.getText().replace(',', '.');
            double profit = Double.parseDouble(priceString) - Double.parseDouble(expensiveString);
            double percentProfit = (profit / Double.parseDouble(priceString)) * 100;
            inputProfit.setText(roundNumber.doRound(profit));
            inputProfitPercentage.setText(roundNumber.doRound(percentProfit));
        }
    }//GEN-LAST:event_inputPriceFocusLost

    private void inputExpenseFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputExpenseFocusLost
        if(!inputExpense.getText().equals("")&&!inputPrice.getText().equals("")){
            RoundNumber roundNumber = new RoundNumber();
            String expensiveString = inputExpense.getText().replace(',', '.');
            String priceString = inputPrice.getText().replace(',', '.');
            double profit = Double.parseDouble(priceString) - Double.parseDouble(expensiveString);
            double percentProfit = (100 * profit) / Double.parseDouble(expensiveString);
            inputProfit.setText(roundNumber.doRound(profit));
            inputProfitPercentage.setText(roundNumber.doRound(percentProfit));
        }
    }//GEN-LAST:event_inputExpenseFocusLost

    private void inputProfitPercentageFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputProfitPercentageFocusLost
       
    }//GEN-LAST:event_inputProfitPercentageFocusLost

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
            java.util.logging.Logger.getLogger(NewProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewProduct().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAll;
    private javax.swing.JButton buttonCancel;
    private javax.swing.JButton buttonLocale;
    private javax.swing.JButton buttonSave;
    private javax.swing.JTextField inputBarCode;
    private javax.swing.JTextField inputBrand;
    private javax.swing.JTextArea inputDescription;
    private javax.swing.JTextField inputExpense;
    private javax.swing.JTextField inputLocalization;
    private javax.swing.JTextField inputMinimumQuantity;
    private javax.swing.JTextField inputName;
    private javax.swing.JTextField inputNameProvider;
    public static javax.swing.JButton inputPhoto;
    private javax.swing.JTextField inputPrice;
    private javax.swing.JTextField inputProfit;
    private javax.swing.JTextField inputProfitPercentage;
    private javax.swing.JTextField inputProvider;
    private javax.swing.JTextField inputQuantity;
    private javax.swing.JScrollPane tableNewProduct;
    private javax.swing.JLabel txtBarCode;
    private javax.swing.JLabel txtBrand;
    private javax.swing.JLabel txtDescription;
    private javax.swing.JLabel txtExpense;
    private javax.swing.JLabel txtLocalization;
    private javax.swing.JLabel txtMinimumQuantity;
    private javax.swing.JLabel txtName;
    private javax.swing.JLabel txtNameProvider;
    public static javax.swing.JLabel txtNewProduct;
    private javax.swing.JLabel txtPercent;
    private javax.swing.JLabel txtPrice;
    private javax.swing.JLabel txtProfit;
    private javax.swing.JLabel txtProftPercentage;
    private javax.swing.JLabel txtProvider;
    private javax.swing.JLabel txtQuantity;
    private javax.swing.JLabel txtRequiredField2;
    private javax.swing.JLabel txtRequiredField3;
    private javax.swing.JLabel txtRequiredField4;
    // End of variables declaration//GEN-END:variables
}
