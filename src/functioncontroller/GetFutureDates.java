package functioncontroller;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author willi
 */
public class GetFutureDates {
    public String getDate(int time, String lastDate){
        String[] auxDate = lastDate.split("/");
        lastDate = auxDate[2] + "/" + auxDate[1] + "/" + auxDate[0];
        Date date = new Date(lastDate);
        date.setDate(date.getDate() + time);
        String formate = "dd/MM/yyyy";
        SimpleDateFormat formatedDate = new SimpleDateFormat(formate); 
        return formatedDate.format(date);
    }
}
