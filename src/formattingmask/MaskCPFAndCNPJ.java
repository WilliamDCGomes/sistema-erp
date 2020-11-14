package formattingmask;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author willi
 */
public class MaskCPFAndCNPJ extends PlainDocument{
    @Override
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        MaskCPFAndCNPJAux maskCPFAux = new MaskCPFAndCNPJAux();
        if (str == null){ 
            return;
        } 
        if(str.length()>1){
            super.insertString(offset, str , attr);
            return;
        }
        str=maskCPFAux.getString(str);
        super.insertString(offset, str , attr);
    }
}
