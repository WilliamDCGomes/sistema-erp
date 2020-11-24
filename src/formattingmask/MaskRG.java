package formattingmask;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author willi
 */
public class MaskRG extends PlainDocument{
    @Override
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        MaskRGAux maskRGAux = new MaskRGAux();
        if (str == null){ 
            return;
        } 
        if(str.length()>1){
            super.insertString(offset, str , attr);
            return;
        }
        str=maskRGAux.getString(str);
        super.insertString(offset, str , attr);
    }
}
