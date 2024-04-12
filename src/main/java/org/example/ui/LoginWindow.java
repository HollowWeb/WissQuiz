package org.example.ui;
import org.example.logics.JsonWriterReader;
import org.example.logics.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class LoginWindow extends JFrame {

    private JTextField userNameField;
    private JPasswordField passwordField;

    public LoginWindow() {
        super("Login");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2, 5, 5));


        // Initialize components
        userNameField = new JTextField();
        userNameField.setFont(new Font("Arial", Font.PLAIN, 15));
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.PLAIN, 15));
        JButton loginButton = new JButton("Login");
        JButton createAccountButton = new JButton("Create Account");


        // Add components
        add(new JLabel("Username:"));
        add(userNameField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(loginButton);
        add(createAccountButton);

        // Add action listeners
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    StringBuilder password = new StringBuilder();
                    for (Character c : passwordField.getPassword()) {
                        password.append(c);
                    }

                    Person person = login(userNameField.getText(), password.toString());
                    if (person != null) {
                        JOptionPane.showMessageDialog(LoginWindow.this, "Login Successful!");
                        setVisible(false);
                        dispose();

                        SwingUtilities.invokeLater(() -> new QuizSelectionWindow(person).setVisible(true));

                    } else {
                        JOptionPane.showMessageDialog(LoginWindow.this, "Invalid username or password!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new AccountCreationWindow(LoginWindow.this);
                setVisible(false);
            }
        });

        setVisible(true);
    }

    private Person login(String userName, String password) throws IOException {

        return JsonWriterReader.lookUpPerson(password, userName);
    }
}

