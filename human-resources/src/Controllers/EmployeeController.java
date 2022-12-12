package Controllers;

import Models.Employee;
import Services.EmployeesService;
import Views.EmployeesView;
import java.awt.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

public class EmployeeController implements MouseListener, ActionListener {

    private static EmployeesView employeesView;
    private Menu menu;
    private EmployeesService service;

    public EmployeeController(Employee employee, EmployeesView employeesView) throws InstantiationException, IllegalAccessException {
        this.menu = menu;
        this.employeesView = employeesView;

        employeesView.tblEmployees.addMouseListener(this);
        employeesView.Add.addActionListener(this);
        employeesView.Actualizar.addActionListener(this);
        employeesView.Eliminar.addActionListener(this);
        employeesView.btnClean.addActionListener(this);

        this.service = new EmployeesService(employee, employeesView);
        service.getUsers();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "Añadir") {
            service.addEmployee();
            service.getUsers();
        }

        if (e.getActionCommand() == "Actualizar") {
            service.updateEmployee();
            service.getUsers();
        }

        if (e.getActionCommand() == "Eliminar") {
            int action = JOptionPane.showConfirmDialog(null, "Esta a punto de eliminar un registro!\nDesea continuar?");

            if (action == 0) {
                service.deleteEmployee();
                service.getUsers();
                clean();
            } else {
                JOptionPane.showMessageDialog(employeesView, "El registro no ha sido eliminado.");
            }
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
        service.getUserById((String) employeesView.tblEmployees.getValueAt(employeesView.tblEmployees.getSelectedRow(), 0));

        employeesView.Add.setEnabled(false);
        employeesView.Actualizar.setEnabled(true);
        employeesView.Eliminar.setEnabled(true);
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
        employeesView.setVisible(true);
    }

    public static void hide() {
        employeesView.dispose();
    }

    public static void clean() {
        employeesView.Add.setEnabled(true);
        employeesView.Actualizar.setEnabled(false);
        employeesView.Eliminar.setEnabled(false);
        employeesView.txtID.setText("");
        employeesView.txtName.setText("");
        employeesView.txtLastName.setText("");
        employeesView.txtAddress.setText("");
        employeesView.txtPhone.setText("");
        employeesView.txtHireDate.setText("");
        employeesView.txtJobTitle.setText("");
        employeesView.txtDept.setText("");
        employeesView.txtSal.setText("");
        employeesView.txtCedula.setText("");
    }

    public static void logout() {
        employeesView.dispose();
        Controllers.LoginController.show();
    }
}
