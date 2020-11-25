package formattingmask;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author willi
 */
public class MaskDate extends PlainDocument{
    @Override
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        MaskDateAux maskDateAux = new MaskDateAux();
        if (str == null){ 
            return;
        } 
        if(str.length()>1){
            for(int i = str.length() - 1; i >= 0; i--){
                String auxStr = maskDateAux.getString( str.substring(i, i + 1) );
                super.insertString(offset, auxStr , attr);
            }
            return;
        }
        str=maskDateAux.getString(str);
        super.insertString(offset, str , attr);
    }
}
