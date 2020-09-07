package functionsuport;
import screens.files.editfiles.OSEditOS;

/**
 *
 * @author willi
 */
public class DisableInputsOSEditOS {
    public void disableInputs(){
        OSEditOS osEditOS = new OSEditOS();
        osEditOS.inputCodeDevice.setEnabled(true);
        osEditOS.inputNameDevice.setEnabled(true);
        osEditOS.inputDateOfOpening.setEnabled(true);
        osEditOS.inputBranchTavile.setEnabled(true);
        osEditOS.inputSituationDevice.setEnabled(true);
        osEditOS.inputObservations.setEnabled(true);
    }
}
