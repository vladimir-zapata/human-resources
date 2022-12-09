package Controllers;

import Models.Employee;
import Views.EmployeesView;
import java.awt.Menu;
import java.awt.event.ActionEvent;

public class EmployeeController {
    private static EmployeesView employeesView;
    private Menu menu;

    public EmployeeController(Employee employee, EmployeesView employeesView) {
        this.menu = menu;
        this.employeesView = employeesView;
    }
    
    

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "Empleados") {
            employeesView.setVisible(false);
            Controllers.EmployeeController.show();

        }

        if (e.getActionCommand() == "Nómina") {
            employeesView.setVisible(false);
            //Controllers.PayrollController.show();
        }
    }

    public static void show() {
        employeesView.setVisible(true);
    }

    public static void hide() {
        employeesView.dispose();
    }
    
    public static void logout() {
        System.exit(0);
    }
}
