
package functioncontroller;
public class SetLocationSubMenus {
    public String processLocation(int InitialLocationButton,int var){
        int y = 190;
        InitialLocationButton-=var;
        return InitialLocationButton + "/" + y;
    }
}
