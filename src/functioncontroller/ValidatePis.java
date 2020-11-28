package functioncontroller;

public class ValidatePis {
    String weight="3298765432";
    int total=0;
    int rest=0;
    String numPIS="";
    String strRest="";

    public boolean ChecaPIS(String pis){
        total=0;
        rest=0;
        numPIS="";
        strRest="";
        numPIS=pis;
        if (numPIS=="" || numPIS==null){
                return false;
        }
        for(int i=0;i<=9;i++){
            int resultado = (Integer.parseInt( numPIS.substring(i,i+1)) )*(Integer.parseInt( weight.substring(i,i+1) ));
            total += resultado;
        }
        rest = (total % 11);
        if (rest != 0){
            rest=11-rest;
        }
        if (rest==10 || rest==11){
            strRest=Integer.toString(rest);
            rest = Integer.parseInt( strRest.substring(1,2) );
        }
        if (rest!=Integer.parseInt( (numPIS.substring(10,11)) )){
            return false;
        }
        return true;
    }
    public boolean ValidaPis(String pis){
        String aux = getJustNumbers(pis);
        if (ChecaPIS(aux)){
           return true;
        }
        return false;
    }
    public String getJustNumbers(String pis){
        String aux = "";
        for(int i = 0; i < pis.length(); i++){
            if(pis.subSequence(i, i + 1).equals(".") || pis.subSequence(i, i + 1).equals("-") || pis.subSequence(i, i + 1).equals("/") || pis.subSequence(i, i + 1).equals("X")){
                continue;
            }
            aux += pis.subSequence(i, i + 1);
        }
        if(!aux.equals("")){
            return aux;
        }
        return null;
    }
}
