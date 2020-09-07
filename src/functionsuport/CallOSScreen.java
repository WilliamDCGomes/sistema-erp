package functionsuport;

import screens.files.editfiles.OSEditOS;

/**
 *
 * @author willi
 */
public class CallOSScreen {
    public void callScreen(){
        OSEditOS oSEditOS = new OSEditOS();
        oSEditOS.txtEquipamentEditEquipament.setText("Equipamento");
        oSEditOS.buttonCadastreEdit.setText("EDITAR");
        oSEditOS.setTitle("Equipamento");
        oSEditOS.setVisible(true);
    }
}
