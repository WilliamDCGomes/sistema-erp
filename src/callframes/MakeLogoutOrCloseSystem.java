package callframes;
import java.awt.Frame;
import static java.awt.Frame.getFrames;
import javax.swing.JOptionPane;
import screens.screenslogin.LoginScreen;

/**
 *
 * @author willi
 */
public class MakeLogoutOrCloseSystem {
    public void makingLogout(){
        int confirma = JOptionPane.showConfirmDialog(null, "DESEJA FAZER LOGOUT?","ATENÇÃO",JOptionPane.YES_NO_OPTION);
        if(confirma==JOptionPane.YES_OPTION){
            Frame[] frames = getFrames(); 
            for (int i = 0; i < frames.length; i++){ 
                frames[i].dispose(); 
            }
            LoginScreen loginScreen = new LoginScreen();
            loginScreen.setVisible(true);
        }
    }
    public void closeSystem(){
        int confirma = JOptionPane.showConfirmDialog(null, "DESEJA MESMO FECHAR O SISTEMA?","ATENÇÃO",JOptionPane.YES_NO_OPTION);
        if(confirma==JOptionPane.YES_OPTION){
            Frame[] frames = getFrames(); 
            for (int i = 0; i < frames.length; i++){ 
                frames[i].dispose(); 
            }
        }
    }
}
