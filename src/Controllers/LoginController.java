package Controllers;

import Models.Login;
import Views.*;
import Controllers.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController implements ActionListener {

    private static LoginView loginView;
    private static Login login;

    public LoginController(LoginView loginView, Login login) {
        this.loginView = loginView;
        this.login = login;
        this.loginView.btnLogin.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        login.setUsername(loginView.txtUser.getText());
        login.setPassword(loginView.txtPassword.getText());
        login.logIn(login.getUsername(), login.getPassword());

        hide();
        Controllers.MenuController.show();

    }

    public static void show() {
        loginView.setVisible(true);
    }

    public static void hide() {
        loginView.dispose();
    }
}
