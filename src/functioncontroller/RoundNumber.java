package functioncontroller;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 *
 * @author willi
 */
public class RoundNumber {
    public String doRound(double value){
        DecimalFormat df = new DecimalFormat("0.00");
        df.setRoundingMode(RoundingMode.HALF_UP);
        return df.format(value);
    }
}
