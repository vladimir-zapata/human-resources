package humanresources;

import humanresources.DBConnection;

import Controllers.LoginController;
import Controllers.MenuController;
import Models.Login;
import Views.*;
import Controllers.*;
import Models.*;
import com.formdev.flatlaf.FlatDarkLaf;
import java.awt.Menu;
import javax.swing.UIManager;

import java.sql.Connection;

public class HumanResources {
    Connection cn;

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
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
        
        AboutView aboutView = new AboutView();
        PayrollView payrollView = new PayrollView();
        
        LoginController loginCtrl = new LoginController(loginView, login);
        MenuController menuController = new MenuController(menuView, menu);
        EmployeeController employeeController = new EmployeeController(employee, employeesView);
        AboutController aboutController = new AboutController(aboutView);
        PayrollController payrollController = new PayrollController(payrollView);
        
        loginCtrl.show();        
    }
}
