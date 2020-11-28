package functioncontroller;
public class GetDateFormate {
    public String getDate(String date){
        String[] aux = date.split("/");
        return aux[2] + "/" + aux[1] + "/" + aux[0];
    }
}
