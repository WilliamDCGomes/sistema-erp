package functioncontroller;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FirstDayOfMonth {
    public String getDate(){
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, 1);
        return new SimpleDateFormat("dd/MM/yyyy").format(c.getTime());
    }
}
