package Controllers;

import Views.*;
import java.awt.Menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuController implements ActionListener {

    private static MenuView menuView;
    private Menu menu;

    public MenuController(MenuView menuView, Menu menu) {
        this.menuView = menuView;
        this.menu = menu;
        this.menuView.btnEmployees.addActionListener(this);
        this.menuView.btnPayroll.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "Empleados") {
            hide();
            Controllers.EmployeeController.show();
            Controllers.EmployeeController.clean();

        }

        if (e.getActionCommand() == "Nómina") {
            hide();
            //Controllers.Payroll.show();
        }
    }

    public static void show() {
        menuView.setVisible(true);
    }

    public static void hide() {
        menuView.dispose();
    }

    public static void logout() {
        menuView.dispose();
        Controllers.LoginController.show();
    }
}
