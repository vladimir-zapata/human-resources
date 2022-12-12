package Services;

import Models.Employee;
import Views.EmployeesView;

import humanresources.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class EmployeesService {

    DBConnection conn = new DBConnection();
    Connection cn = null;

    EmployeesView employeesView;

    PreparedStatement ps = null;
    Statement stmt = null;
    ResultSet rs = null;

    public EmployeesService(Employee employee, EmployeesView employeeView) throws InstantiationException, IllegalAccessException {
        this.cn = conn.getConn();
        this.cn = cn;
        this.employeesView = employeeView;
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
            ps.setString(5, employeesView.txtHireDate.getText());
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
}
