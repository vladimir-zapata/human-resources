package Services;

import Models.Login;
import Views.LoginView;

import humanresources.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class LoginService {

    DBConnection conn = new DBConnection();
    Connection cn = null;
    
    LoginView loginView;
    Login login;
    
    PreparedStatement ps = null;
    ResultSet rs = null;

    public LoginService(Login login, LoginView loginView) throws InstantiationException, IllegalAccessException {
        this.cn = conn.getConn();
        this.login = login;
        this.loginView = loginView;
    }

    public int verifyUser() {
        String selectQuery = "SELECT * FROM usuarios WHERE usuario = ? AND contraseña = ?";
        int userFound = 0;

        try {
            ps = cn.prepareStatement(selectQuery);

            ps.setString(1, login.getUsername());
            ps.setString(2, login.getPassword());
            rs = ps.executeQuery();
            rs.next();

            userFound = rs.getRow();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(loginView, "Ha ocurrido un error inesperado!\nContacte a su supervisor.");
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
}
