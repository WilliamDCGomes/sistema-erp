package formattingmask;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
/**
 *
 * @author willi
 */
public class MaskPhone extends PlainDocument {
    @Override
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        MaskPhoneAux maskPhoneAux = new MaskPhoneAux();
        if (str == null){ 
            return;
        } 
        if(str.length()>1){
            super.insertString(offset, str , attr);
            return;
        }
        str=maskPhoneAux.getString(str);
        super.insertString(offset, str , attr);
    }
}
