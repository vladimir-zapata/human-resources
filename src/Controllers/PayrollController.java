package Controllers;

import Views.PayrollView;
import Models.Payroll;
import Services.PayrollService;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PayrollController implements ActionListener, MouseListener {

    private static PayrollView payrollView;
    private Payroll payroll;
    private PayrollService service;

    public PayrollController() throws InstantiationException, IllegalAccessException {
        this.payrollView = new PayrollView();
        this.payroll = new Payroll();

        payrollView.btnBuscar.addActionListener(this);
        payrollView.btnCalculatePayroll.addActionListener(this);
        payrollView.btnConfirm.addActionListener(this);
        payrollView.btnUpdate.addActionListener(this);
        payrollView.btnDelete.addActionListener(this);
        payrollView.btnClean.addActionListener(this);
        payrollView.tblPayroll.addMouseListener(this);

        this.service = new PayrollService(payroll, payrollView);

        service.getPayroll();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "Buscar") {
            boolean userFound = service.getUserById(payrollView.txtId.getText());

            if (userFound) {
                payrollView.btnBuscar.setEnabled(false);
                payrollView.txtWorkedHours.setEnabled(true);
                payrollView.txtExtraHours.setEnabled(true);
                payrollView.txtAvance.setEnabled(true);
                payrollView.btnCalculatePayroll.setEnabled(true);
                payrollView.btnConfirm.setEnabled(true);
                payrollView.btnDelete.setEnabled(true);
            }
        }

        if (e.getActionCommand() == "Calcular Nomina") {
            System.out.println("Calculando");
        }

        if (e.getActionCommand() == "Confirmar") {
            System.out.println("Confirm");
        }

        if (e.getActionCommand() == "Actualizar") {
            System.out.println("Update");
        }

        if (e.getActionCommand() == "Eliminar") {
            System.out.println("Delete");
        }

        if (e.getActionCommand() == "Limpiar") {
            clean();
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        service.getPayrollById((String) payrollView.tblPayroll.getValueAt(payrollView.tblPayroll.getSelectedRow(), 0));
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
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

    private void clean() {
        payrollView.btnBuscar.setEnabled(true);
        payrollView.txtWorkedHours.setEnabled(false);
        payrollView.txtExtraHours.setEnabled(false);
        payrollView.txtAvance.setEnabled(false);
        payrollView.btnCalculatePayroll.setEnabled(false);
        payrollView.btnConfirm.setEnabled(false);
        payrollView.btnDelete.setEnabled(false);

        payrollView.txtId.setText("");
        payrollView.txtWorkedHours.setText("");
        payrollView.txtExtraHours.setText("");
        payrollView.txtAvance.setText("");
        payrollView.txtName.setText("");
        payrollView.txtLastName.setText("");
        payrollView.txtInsurance.setText("");
        payrollView.txtAfp.setText("");
        payrollView.txtNetSal.setText("");
    }
}
