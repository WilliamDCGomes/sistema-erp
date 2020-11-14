package functioncontroller;

/**
 *
 * @author willi
 */
public class GetJustTheNumbers {
    public String getNumbers(String cpf){
        String numberCPF = "";
        for(int i=0; i<cpf.length(); i++){
            if(cpf.subSequence(i, i+1).equals("1")){
                numberCPF += "1";
            }
            else if(cpf.subSequence(i, i+1).equals("2")){
                numberCPF += "2";
            }
            else if(cpf.subSequence(i, i+1).equals("3")){
                numberCPF += "3";
            }
            else if(cpf.subSequence(i, i+1).equals("4")){
                numberCPF += "4";
            }
            else if(cpf.subSequence(i, i+1).equals("5")){
                numberCPF += "5";
            }
            else if(cpf.subSequence(i, i+1).equals("6")){
                numberCPF += "6";
            }
            else if(cpf.subSequence(i, i+1).equals("7")){
                numberCPF += "7";
            }
            else if(cpf.subSequence(i, i+1).equals("8")){
                numberCPF += "8";
            }
            else if(cpf.subSequence(i, i+1).equals("9")){
                numberCPF += "9";
            }
            else if(cpf.subSequence(i, i+1).equals("0")){
                numberCPF += "0";
            }
        }
        return numberCPF;
    }
}
