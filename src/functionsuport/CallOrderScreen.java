package functionsuport;
import screens.files.editfiles.OrderEditOrder;

/**
 *
 * @author willi
 */
public class CallOrderScreen {
    public void callScreen(){
        OrderEditOrder orderEditOrder = new OrderEditOrder();
        orderEditOrder.txtOrderEditOrder.setText("Pedido");
        orderEditOrder.buttonOrderEditOrder.setText("EDITAR");
        orderEditOrder.setTitle("Pedido");
        orderEditOrder.setVisible(true);
    }
}
