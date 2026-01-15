package com.bankease;

import javax.swing.*;
import com.bankease.ui.LoginUI;

public class App {

    public static void main(String[] args) {

        // Optional: prevents warnings on Mac/Linux
        System.setProperty("awt.useSystemAAFontSettings", "on");
        System.setProperty("swing.aatext", "true");

        SwingUtilities.invokeLater(() -> {
            new LoginUI();
        });
    }
}
