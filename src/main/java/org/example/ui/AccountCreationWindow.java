package org.example.ui;
import org.example.logics.JsonWriterReader;
import org.example.logics.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountCreationWindow extends JFrame {

    private JTextField firstNameField, lastNameField, emailField, userNameField;
    private JPasswordField passwordField;

    public AccountCreationWindow(LoginWindow loginWindow) {
        super("Create Account");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 2, 5, 5));

        // Initialize components
        firstNameField = new JTextField();
        lastNameField = new JTextField();
        emailField = new JTextField();
        userNameField = new JTextField();
        passwordField = new JPasswordField();
        JButton createButton = new JButton("Create");

        // Add components
        add(new JLabel("First Name:"));
        add(firstNameField);
        add(new JLabel("Last Name:"));
        add(lastNameField);
        add(new JLabel("Email:"));
        add(emailField);
        add(new JLabel("Username:"));
        add(userNameField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(new JLabel());
        add(createButton);

        // Action listener for create button
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!JsonWriterReader.isUsernameTaken(userNameField.getText())) {
                    if (isNotMissing()) {
                        Person newUser = new Person(
                                firstNameField.getText(),
                                lastNameField.getText(),
                                emailField.getText(),
                                new String(passwordField.getPassword()),
                                userNameField.getText(),
                                null
                        );
                        JsonWriterReader.writeToJsonFile(newUser);
                        loginWindow.setVisible(true);
                        AccountCreationWindow.this.dispose();
                    }
                } else {
                    JOptionPane.showMessageDialog(AccountCreationWindow.this, "Username is already taken!");
                }
            }
        });

        setVisible(true);
    }

    private boolean isNotMissing(){
        if (firstNameField.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "First name is required");
            return false;
        }
        if (lastNameField.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Last name is required");
            return false;
        }
        if (emailField.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Email is required");
            return false;
        }
        if (userNameField.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Username is required");
            return false;
        }
        if (passwordField.getPassword().length == 0){
            JOptionPane.showMessageDialog(this, "Password is required");
            return false;
        }
        return true;
    }
}

