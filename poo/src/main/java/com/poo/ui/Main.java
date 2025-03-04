package com.poo.ui;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.swing.SwingUtilities;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainUI ventana = new MainUI();
            ventana.setVisible(true);
        });
    }
}