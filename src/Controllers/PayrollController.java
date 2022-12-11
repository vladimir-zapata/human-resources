package Controllers;

import Views.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PayrollController implements ActionListener {

    private static PayrollView payrollView;

    public PayrollController(PayrollView payrollView) {
        this.payrollView = payrollView;
    }

    /**
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void show() {
        payrollView.setVisible(true);
    }

    public static void hide() {
        payrollView.dispose();
    }

    public static void logout() {
        payrollView.dispose();
        Controllers.LoginController.show();
    }
}
