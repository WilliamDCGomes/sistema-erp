package functionsuport;

import screens.files.editfiles.ItemStockEditItemStock;

/**
 *
 * @author willi
 */
public class CallItemScreen {
    public void callScreen(){
        ItemStockEditItemStock itemStockEditItemStock = new ItemStockEditItemStock();
        itemStockEditItemStock.txtItemEditItem.setText("Item no Estoque");
        itemStockEditItemStock.buttonEditItemAddItem.setText("EDITAR");
        itemStockEditItemStock.setTitle("Item no Estoque");
        itemStockEditItemStock.setVisible(true);
    }
}
