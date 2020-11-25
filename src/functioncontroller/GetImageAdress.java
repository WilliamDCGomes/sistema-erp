package functioncontroller;

import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import connectionbd.ConnectionModule;
import javax.swing.JOptionPane;

/**
 *
 * @author willi
 */
public class GetImageAdress {
    Connection connection = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    String fileAdress = null;
    public GetImageAdress(){
        ConnectionModule connect = new ConnectionModule();
        connection = connect.getConnectionMySQL();
    }
    public String getAdress(){
        int actualId = getid();
        try{
            Runtime.getRuntime().exec("D:\\Programing\\Sistema ERP\\IniciaSelecaoDeArquivo.bat");
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "ERRO: " + e);
        }
        isDiferent(actualId);
        while(fileAdress==null){
            System.out.println(fileAdress);
        }
        return fileAdress;
    }
    public void isDiferent(int id){
        int delay = 100;   // tempo de espera antes da 1ª execução da tarefa.
        int interval = 1000;  // intervalo no qual a tarefa será executada.
        java.util.Timer timer = new java.util.Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                int idNow = getid();
                if(id!=idNow){
                    fileAdress = getAdressId(idNow);
                    timer.cancel();
                }
            }
        }, delay, interval);
    }
    private int getid(){
        String sql ="select max(id) from temporaryAdressChoosed";
        try {
            pst=connection.prepareStatement(sql);
            rs= pst.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return 0;
    }
    private String getAdressId(int id){
        String sql ="select adress from temporaryAdressChoosed where id = ?";
        try {
            pst=connection.prepareStatement(sql);
            pst.setInt(1, id);
            rs= pst.executeQuery();
            if(rs.next()){
                return rs.getString(1);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }
}
