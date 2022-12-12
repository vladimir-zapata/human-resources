package humanresources;
    
import Controllers.*;

import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.UIManager;

public class HumanResources {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception e) {
        }
        
        new MenuController();
        new EmployeeController();
        new PayrollController();
        new AboutController();

        LoginController loginCtrl = new LoginController();
        
        loginCtrl.show();        
    }
}
