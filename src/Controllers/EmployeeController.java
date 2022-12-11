package Controllers;

import static Controllers.LoginController.hide;
import Models.Employee;
import Views.EmployeesView;
import java.sql.Connection;
import humanresources.DBConnection;
import java.awt.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

import javax.swing.table.DefaultTableModel;

public class EmployeeController implements MouseListener, ActionListener {

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
        employeesView.btnAddEmployee.addActionListener(this);
        employeesView.btnUpdateEmployee.addActionListener(this);
        employeesView.btnDeleteEmployee.addActionListener(this);
        employeesView.btnClean.addActionListener(this);
        getUsers();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "Añadir") {
            addEmployee();
            getUsers();
        }

        if (e.getActionCommand() == "Actualizar") {
            updateEmployee();
            getUsers();
        }

        if (e.getActionCommand() == "Eliminar") {
            deleteEmployee();
            getUsers();
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
        getUserById((String) employeesView.tblEmployees.getValueAt(employeesView.tblEmployees.getSelectedRow(), 0));

        employeesView.btnAddEmployee.setEnabled(false);
        employeesView.btnUpdateEmployee.setEnabled(true);
        employeesView.btnDeleteEmployee.setEnabled(true);
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
            employeesView.txtHireDate.setText(rs.getString("fecha_ingreso"));
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

    public void addEmployee() {
        String selectQuery = "INSERT INTO empleados (nombre, apellido, direccion, telefono, fecha_ingreso, cargo, departamento, salario, cedula) VALUES (?,?,?,?,?,?,?,?,?)";

        try {
            ps = cn.prepareStatement(selectQuery);

            ps.setString(1, employeesView.txtName.getText());
            ps.setString(2, employeesView.txtLastName.getText());
            ps.setString(3, employeesView.txtAddress.getText());
            ps.setString(4, employeesView.txtPhone.getText());
            ps.setDate(5, java.sql.Date.valueOf(employeesView.txtHireDate.getText()));
            ps.setString(6, employeesView.txtJobTitle.getText());
            ps.setString(7, employeesView.txtDept.getText());
            ps.setInt(8, Integer.parseInt(employeesView.txtSal.getText()));
            ps.setString(9, employeesView.txtCedula.getText());
            ps.executeUpdate();

            JOptionPane.showMessageDialog(employeesView, "Añadido satisfactoriamente.");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(employeesView, "Ha ocurrido un error al añadir los datos del empleado!\nAsegurese de que todos los campos esten correctos.");
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
    }

    public void updateEmployee() {
        String selectQuery = "UPDATE empleados SET nombre = ?, apellido = ?, direccion = ?, telefono = ?, fecha_ingreso = ?, cargo = ?, departamento = ?, salario = ?, cedula = ? WHERE id = ?";

        try {
            ps = cn.prepareStatement(selectQuery);

            ps.setString(1, employeesView.txtName.getText());
            ps.setString(2, employeesView.txtLastName.getText());
            ps.setString(3, employeesView.txtAddress.getText());
            ps.setString(4, employeesView.txtPhone.getText());
            ps.setDate(5, java.sql.Date.valueOf(employeesView.txtHireDate.getText()));
            ps.setString(6, employeesView.txtJobTitle.getText());
            ps.setString(7, employeesView.txtDept.getText());
            ps.setInt(8, Integer.parseInt(employeesView.txtSal.getText()));
            ps.setString(9, employeesView.txtCedula.getText());
            ps.setInt(10, Integer.parseInt(employeesView.txtID.getText()));
            ps.executeUpdate();

            JOptionPane.showMessageDialog(employeesView, "Actualizado satisfactoriamente.");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(employeesView, "Ha ocurrido un error al actualizar los datos del empleado!\nAsegurese de que todos los campos esten correctos.");
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
    }
    
        public void deleteEmployee() {
        String selectQuery = "DELETE FROM empleados WHERE id = ?";

        try {
            ps = cn.prepareStatement(selectQuery);

            ps.setInt(1, Integer.parseInt(employeesView.txtID.getText()));
            ps.executeUpdate();

            JOptionPane.showMessageDialog(employeesView, "Eliminado satisfactoriamente.");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(employeesView, "Ha ocurrido un error al eliminar los datos del empleado!\nAsegurese de que todos los campos esten correctos.");
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
    }

    public static void show() {
        employeesView.setVisible(true);
    }

    public static void hide() {
        employeesView.dispose();
    }
    
        public static void clean() {
        employeesView.btnAddEmployee.setEnabled(true);
        employeesView.btnUpdateEmployee.setEnabled(false);
        employeesView.btnDeleteEmployee.setEnabled(false);

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
