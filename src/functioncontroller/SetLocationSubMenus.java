
package functioncontroller;
import screens.mainmenu.ScreenUserSize;

/**
 *
 * @author willi
 */
public class SetLocationSubMenus {
    public Coordinate processLocation(int InitialLocationButton){
        ScreenUserSize screenUserSize = new ScreenUserSize();
        //Localizações do MainMenu
        int sizeScreen = screenUserSize.sizeOfScreen();
        int y = 193;
        int initialSizeScreen = 1366;
        int locationButton = (sizeScreen * InitialLocationButton)/initialSizeScreen;
        //Localizações do submenu
        int InitialLocationButtonO = 138;
        int x =locationButton - InitialLocationButtonO;
        Coordinate coordinate = new Coordinate(x,y);
        return coordinate;
    }
}
