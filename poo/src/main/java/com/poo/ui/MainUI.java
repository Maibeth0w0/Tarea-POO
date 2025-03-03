package com.poo.ui;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

import com.poo.persistencia.CursosInscritos;
import com.poo.persistencia.InscripcionesPersonas;

public class MainUI extends JFrame {
    
    public MainUI() {
        setTitle("Gestión de Inscripciones");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear el panel de pestañas
        JTabbedPane tabbedPane = new JTabbedPane();

        // Instancia de InscripcionesPersonas para pasar a la interfaz
        InscripcionesPersonas inscripciones = new InscripcionesPersonas();
        inscripciones.cargarDatos();

        CursosInscritos cursosInscritos = new CursosInscritos();
        cursosInscritos.cargarDatos();

        // Agregar pestañas
        tabbedPane.addTab("Cursos Inscritos", new CursosInscritosPanel(cursosInscritos));
        tabbedPane.addTab("Cursos Profesores", new CursosProfesoresPanel());
        tabbedPane.addTab("Inscripciones Personas", new InscripcionesPersonasPanel(inscripciones));

        // Agregar el tabbedPane a la ventana
        add(tabbedPane);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainUI ventana = new MainUI();
            ventana.setVisible(true);
        });
    }
}

