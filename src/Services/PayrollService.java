package Services;

import Models.Payroll;
import Views.PayrollView;

import humanresources.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class PayrollService {
    DBConnection conn = new DBConnection();
    Connection cn = null;

    PayrollView payrollView;
    Payroll payroll = new Payroll();

    PreparedStatement ps = null;
    Statement stmt = null;
    ResultSet rs = null;

    public PayrollService(Payroll payroll, PayrollView payrollView) throws InstantiationException, IllegalAccessException {
        this.cn = conn.getConn();
        this.payrollView = payrollView;
    }

    public void getPayroll() {
        String selectQuery = "SELECT * FROM nominas";

        DefaultTableModel tm = new DefaultTableModel();
        tm.addColumn("Id");
        tm.addColumn("Nombre");
        tm.addColumn("Apellido");
        tm.addColumn("Cargo");
        tm.addColumn("Sueldo bruto");
        tm.addColumn("Horas extra");
        tm.addColumn("Precio Hora Extra");
        tm.addColumn("Pago hora extra");
        tm.addColumn("Bono transporte");
        tm.addColumn("Seguro medico");
        tm.addColumn("Adelanto sueldo");
        tm.addColumn("Sueldo neto");
        payrollView.tblPayroll.setModel(tm);

        try {
            stmt = cn.createStatement();

            rs = stmt.executeQuery(selectQuery);
            String data[] = new String[12];
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
                data[10] = rs.getString(11);
                data[11] = rs.getString(12);
                tm.addRow(data);
            }
            payrollView.tblPayroll.setModel(tm);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(payrollView, "Ha ocurrido un error inesperado!\nContacte a su supervisor.");
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

    public int getPayrollById(String id) {
        String selectQuery = "SELECT * FROM nominas WHERE id = " + id;
        int userFound = 0;

        try {
            stmt = cn.createStatement();

            rs = stmt.executeQuery(selectQuery);

            rs.next();

            payrollView.txtId.setText(String.valueOf(rs.getInt("id")));
            payrollView.txtName.setText(rs.getString("nombre"));
            payrollView.txtLastName.setText(rs.getString("apellido"));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(payrollView, "Ha ocurrido un error al obtener los datos del empleado!\nContacte a su supervisor.");
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

        return userFound;
    }

    public boolean getUserById(String id) {
        String selectQuery = "SELECT * FROM empleados WHERE id = " + id;

        try {
            stmt = cn.createStatement();

            rs = stmt.executeQuery(selectQuery);

            rs.next();

            payrollView.txtId.setText(String.valueOf(rs.getInt("id")));
            payrollView.txtName.setText(String.valueOf(rs.getString("nombre")));
            payrollView.txtLastName.setText(String.valueOf(rs.getString("apellido")));

            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(payrollView, "Ha ocurrido un error al obtener los datos del empleado!\nContacte a su supervisor.");
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

        return true;
    }
}
