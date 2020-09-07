package functioncontroller;
import java.awt.Dimension;
import java.awt.Toolkit;
public class ScreenUserSize {
    public int sizeOfScreen() {
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension dimension = tk.getScreenSize();
        return dimension.width;
    }
}
