package Controllers;

import Models.Login;
import Views.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class LoginController implements ActionListener {

    private static LoginView loginView;
    private static Login login;

    public LoginController(LoginView loginView, Login login) {
        this.loginView = loginView;
        this.login = login;
        this.loginView.btnLogin.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        validateUser();
    }

    public static void show() {
        loginView.setVisible(true);
    }

    public static void hide() {
        loginView.dispose();
    }

    public static void logout() {
        System.exit(0);
    }

    public void validateUser() {
        login.setUsername(loginView.txtUser.getText());
        login.setPassword(loginView.txtPassword.getText());

        if (login.getUsername().length() > 20) {
            JOptionPane.showMessageDialog(loginView, "El nombre usuario es incorrecto. Asegurese de ingresar un usuario valido.");
        }

        if (login.getUsername().matches("^\\\\d+$")) {
            JOptionPane.showMessageDialog(loginView, "Usuario incorrecto! Ingrese un nombre de usuario valido.");
        }
    }
}
