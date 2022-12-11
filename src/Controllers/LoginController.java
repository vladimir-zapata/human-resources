package Controllers;

import Models.Login;
import Views.*;
import java.sql.Connection;
import java.sql.ResultSet;
import humanresources.DBConnection;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class LoginController implements ActionListener {

    private static LoginView loginView;
    private static Login login;

    DBConnection conn = new DBConnection();
    Connection cn;

    public LoginController(LoginView loginView, Login login) throws InstantiationException, IllegalAccessException {
        this.cn = conn.getConn();
        this.loginView = loginView;
        this.login = login;
        this.loginView.btnLogin.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (validateUser() == false) {
            return;
        }

        if (verifyUser() == 1) {
            hide();
            Controllers.MenuController.show();
        } else {
            JOptionPane.showMessageDialog(loginView, "Usuario o contraseña incorrectos.");

        }

    }

    public static void show() {
        loginView.setVisible(true);
    }

    public static void hide() {
        loginView.setVisible(false);
    }

    public static void logout() {
        System.exit(0);
    }

    public boolean validateUser() {
        login.setUsername(loginView.txtUser.getText());
        login.setPassword(new String(loginView.passInput.getPassword()));

        if (login.getUsername().length() > 50 || login.getPassword().length() > 30) {
            JOptionPane.showMessageDialog(loginView, "El nombre de usuario o la contraseña es incorrecto.\nAsegurese de ingresar un usuario y contraseña validos.");
            return false;
        }

        if (!login.getUsername().matches("[a-zA-Z]*")) {
            JOptionPane.showMessageDialog(loginView, "Usuario incorrecto!\nIngrese un nombre de usuario valido.");
            return false;
        }

        if (login.getPassword().length() == 0 || login.getUsername().length() == 0) {
            JOptionPane.showMessageDialog(loginView, "Campos vacios!\nIngrese un usuario y contraseña validos.");
            return false;
        }

        return true;
    }

    public int verifyUser() {
        String selectQuery = "SELECT * FROM usuarios WHERE usuario = ? AND contraseña = ?";
        int userFound = 0;

        try {
            PreparedStatement ps = cn.prepareStatement(selectQuery);

            ps.setString(1, login.getUsername());
            ps.setString(2, login.getPassword());
            ResultSet rs = ps.executeQuery();
            rs.next();

            userFound = rs.getRow();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(loginView, e);
        }

        return userFound;
    }

}
