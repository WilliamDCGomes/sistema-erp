package functioncontroller;

import java.awt.Frame;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author willi
 */
public class GetImageAdress {
    public GetImageAdress(){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GetImageAdress.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(GetImageAdress.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(GetImageAdress.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(GetImageAdress.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public String getAdress(){
        JFileChooser files = new JFileChooser();
        File origin = new File("C:/Users/" + System.getProperty("user.name") + "/Desktop");
        files.setDialogTitle("SELECIONE UMA IMAGEM");
        files.setFileSelectionMode(JFileChooser.FILES_ONLY);
        files.setCurrentDirectory(origin);
        int opc = files.showOpenDialog(new Frame());
        if(opc==JFileChooser.APPROVE_OPTION){
            File file = new File("CAMINHO");
            file= files.getSelectedFile();
            String filename = file.getAbsolutePath();
            return filename;
        }
        return null;
    }
}
