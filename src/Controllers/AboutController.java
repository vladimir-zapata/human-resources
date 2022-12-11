package Controllers;

import Views.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AboutController implements ActionListener {

    private static AboutView aboutView;

    public AboutController(AboutView aboutView) {
        this.aboutView = aboutView;
    }

    /**
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void show() {
        aboutView.setVisible(true);
    }

    public static void hide() {
        aboutView.dispose();
    }

    public static void logout() {
        aboutView.dispose();
        Controllers.LoginController.show();
    }
}
