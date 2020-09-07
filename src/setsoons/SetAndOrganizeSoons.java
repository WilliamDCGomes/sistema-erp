package setsoons;
import javax.swing.ImageIcon;
public class SetAndOrganizeSoons {
    public void SetSoons(AuxiliaryJFrame jFrame){
        ImageIcon soonOfTavile = new ImageIcon("C:/Users/Alunos/Desktop/Tavile System/src/images/soonTavile.png");
        soonOfTavile.setImage(soonOfTavile.getImage().getScaledInstance(jFrame.soonTavile.getWidth(), jFrame.soonTavile.getHeight(), 1));
        jFrame.soonTavile.setIcon(soonOfTavile);
        ImageIcon soonOfProxxi = new ImageIcon("C:/Users/Alunos/Desktop/Tavile System/src/images/soonProxxi.png");
        soonOfProxxi.setImage(soonOfProxxi.getImage().getScaledInstance(jFrame.soonProxxi.getWidth(), jFrame.soonProxxi.getHeight(), 1));
        jFrame.soonProxxi.setIcon(soonOfProxxi);
    }
    public void SetLocationSoons(AuxiliaryJFrame jFrame,int positionXTavile,int positionYTavile, int positionXProxxi, int positionYProxxi){
        jFrame.soonTavile.setLocation(positionXTavile, positionYTavile);
        jFrame.soonProxxi.setLocation(positionXProxxi, positionYProxxi);
    }
}