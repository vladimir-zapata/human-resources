package Controllers;

import Views.MenuView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuController implements ActionListener {

    private static MenuView menuView;

    public MenuController() {
        this.menuView = new MenuView();
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
            Controllers.PayrollController.show();
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
