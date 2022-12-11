package Controllers;

import static Controllers.LoginController.hide;
import Models.Employee;
import Views.EmployeesView;
import java.sql.Connection;
import humanresources.DBConnection;
import java.awt.Menu;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

import javax.swing.table.DefaultTableModel;

public class EmployeeController implements MouseListener {

    private static EmployeesView employeesView;
    private Menu menu;
    private Employee employee = new Employee();

    DBConnection conn = new DBConnection();
    Connection cn;
    Statement stmt = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public EmployeeController(Employee employee, EmployeesView employeesView) throws InstantiationException, IllegalAccessException {
        this.cn = conn.getConn();
        this.menu = menu;
        this.employeesView = employeesView;
        employeesView.tblEmployees.addMouseListener(this);
        getUsers();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        getUserById((String) employeesView.tblEmployees.getValueAt(employeesView.tblEmployees.getSelectedRow(), 0));
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

    public void getUsers() {
        String selectQuery = "SELECT * FROM empleados";

        DefaultTableModel tm = new DefaultTableModel();
        tm.addColumn("Id");
        tm.addColumn("Nombre");
        tm.addColumn("Apellido");
        tm.addColumn("Direccion");
        tm.addColumn("Telefono");
        tm.addColumn("Fecha de Ingreso");
        tm.addColumn("Cargo");
        tm.addColumn("Departamento");
        tm.addColumn("Salario");
        tm.addColumn("Cedula");
        employeesView.tblEmployees.setModel(tm);

        try {
            Statement stmt = cn.createStatement();

            ResultSet rs = stmt.executeQuery(selectQuery);
            String data[] = new String[10];
            while (rs.next()) {
                data[0] = rs.getString(1);
                data[1] = rs.getString(2);
                data[2] = rs.getString(3);
                data[3] = rs.getString(4);
                data[4] = rs.getString(5);
                data[5] = rs.getString(6);
                data[6] = rs.getString(7);
                data[7] = rs.getString(8);
                data[8] = rs.getString(9);
                data[9] = rs.getString(10);
                tm.addRow(data);
            }
            employeesView.tblEmployees.setModel(tm);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(employeesView, "Ha ocurrido un error inesperado!\nContacte a su supervisor.");
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                }
                stmt = null;
            }
        }
    }

    public int getUserById(String id) {
        String selectQuery = "SELECT * FROM empleados WHERE id = " + id;
        int userFound = 0;

        try {
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(selectQuery);

            rs.next();
            employeesView.txtID.setText(String.valueOf(rs.getInt("id")));
            employeesView.txtName.setText(rs.getString("nombre"));
            employeesView.txtLastName.setText(rs.getString("apellido"));
            employeesView.txtAddress.setText(rs.getString("direccion"));
            employeesView.txtPhone.setText(rs.getString("telefono"));
            employeesView.txtHireDate.setText(rs.getString("fecha_ingreso").substring(0, 10));
            employeesView.txtJobTitle.setText(rs.getString("cargo"));
            employeesView.txtDept.setText(rs.getString("departamento"));
            employeesView.txtSal.setText(String.valueOf(rs.getInt("salario")));
            employeesView.txtCedula.setText(rs.getString("cedula"));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(employeesView, "Ha ocurrido un error al obtener los datos del empleado!\nContacte a su supervisor.");
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }

                rs = null;
            }

            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                }

                ps = null;
            }
        }

        return userFound;
    }

    public static void show() {
        employeesView.setVisible(true);
    }

    public static void hide() {
        employeesView.dispose();
    }

    public static void logout() {
        employeesView.dispose();
        Controllers.LoginController.show();
    }
}
