/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.User;

import Controller.ControllerLogin;
import Controller.LoginViewContract;
import View.Admin.DashboardAdmin;
import View.Component.AppButtonFactory;
import View.Component.AppContentPanel;
import View.Component.AppFrame;
import View.Component.AppTheme;
import View.Component.LabeledInput;

import javax.swing.*;
import java.awt.*;

public class Login extends AppFrame implements LoginViewContract {

    private final LabeledInput usernameInput;
    private final LabeledInput passwordInput;

    public Login() {
        super("Login", AppTheme.WINDOW_AUTH);

        ControllerLogin controller = new ControllerLogin(this);

        JPanel panel = createScreenPanel();

        JPanel form = new AppContentPanel(new GridBagLayout());
        form.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 14, 8, 14);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.weightx = 1.0;

        JLabel title = new JLabel("LOGIN");
        title.setFont(AppTheme.TITLE_FONT);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setForeground(AppTheme.PRIMARY);

        usernameInput = LabeledInput.text("Username", 16);
        passwordInput = LabeledInput.password("Password", 16);

        JButton btnLogin = AppButtonFactory.primary("LOGIN");
        JButton btnRegister = AppButtonFactory.success("REGISTER");

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 12, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.add(btnLogin);
        buttonPanel.add(btnRegister);

        gbc.gridy = 0;
        form.add(title, gbc);

        gbc.gridy = 1;
        form.add(usernameInput, gbc);

        gbc.gridy = 2;
        form.add(passwordInput, gbc);

        gbc.gridy = 3;
        gbc.insets = new Insets(14, 14, 8, 14);
        form.add(buttonPanel, gbc);

        panel.add(form, BorderLayout.CENTER);
        setScreenContent(panel);

        btnLogin.addActionListener(e ->
            controller.handleLogin(
                usernameInput.getText(),
                passwordInput.getPassword()
            )
        );

        btnRegister.addActionListener(e -> {
            dispose();
            new Register().setVisible(true);
        });
    }

    @Override
    public void showInfoMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    @Override
    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    @Override
    public void openAdminDashboard() {
        dispose();
        new DashboardAdmin().setVisible(true);
    }

    @Override
    public void openUserDashboard() {
        dispose();
        new DashboardUser().setVisible(true);
    }
}