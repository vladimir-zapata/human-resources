package humanresources;

import Controllers.LoginController;
import Controllers.MenuController;
import Models.Login;
import Views.*;
import Controllers.*;
import Models.*;
import com.formdev.flatlaf.FlatDarkLaf;
import java.awt.Menu;
import javax.swing.UIManager;

public class HumanResources {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception e) {
        }

        Login login = new Login();
        LoginView loginView = new LoginView();

        Menu menu = new Menu();
        MenuView menuView = new MenuView();
        
        Employee employee = new Employee();
        EmployeesView employeesView = new EmployeesView();
        
        LoginController loginCtrl = new LoginController(loginView, login);
        MenuController menuCtrl = new MenuController(menuView, menu);
        EmployeeController employeeController = new EmployeeController(employee, employeesView);
        
        loginCtrl.show();
    }
}
