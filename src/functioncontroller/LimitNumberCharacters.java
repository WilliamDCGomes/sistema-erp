package functioncontroller;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author willi
 */
public class LimitNumberCharacters extends PlainDocument{
    private int limitSize = 13;
    @Override
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        UpperLetterAux upperLetter = new UpperLetterAux();
        if (str == null || str.equals(" ")){ 
            return;
        } 
        str=upperLetter.makeUp(str);
        String oldString = getText(0, getLength());  
        int newSize = oldString.length() + str.length(); 
        if (newSize <= limitSize){  
            super.insertString(offset, str , attr);  
        }  
    }
}
