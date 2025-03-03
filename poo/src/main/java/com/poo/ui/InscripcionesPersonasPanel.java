package com.poo.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.poo.modelos.Persona;
import com.poo.persistencia.InscripcionesPersonas;

public class InscripcionesPersonasPanel extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField txtId, txtNombre, txtApellido, txtEmail;
    private InscripcionesPersonas inscripciones;

    public InscripcionesPersonasPanel(InscripcionesPersonas inscripciones) {
        this.inscripciones = inscripciones;
        setLayout(new BorderLayout());

        // Cargar datos antes de mostrar la tabla
        this.inscripciones.cargarDatos();
        
        // Crear tabla
        tableModel = new DefaultTableModel(new String[]{"ID", "Nombre", "Apellido", "Email"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Crear formulario
        JPanel formPanel = new JPanel(new GridLayout(5, 2));
        formPanel.add(new JLabel("ID:"));
        txtId = new JTextField();
        formPanel.add(txtId);

        formPanel.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        formPanel.add(txtNombre);

        formPanel.add(new JLabel("Apellido:"));
        txtApellido = new JTextField();
        formPanel.add(txtApellido);

        formPanel.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        formPanel.add(txtEmail);

        // Botones
        JButton btnInscribir = new JButton("Inscribir");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnActualizar = new JButton("Actualizar");
        JButton btnCargarBD = new JButton("Cargar Listado a BD");
        JButton btnVerificarArchivo = new JButton("Verificar Binario");//temporal
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnInscribir);
        buttonPanel.add(btnEliminar);
        buttonPanel.add(btnActualizar);
        buttonPanel.add(btnCargarBD);
        buttonPanel.add(btnVerificarArchivo);//temporal
        
        add(formPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);

        // Cargar datos en la tabla
        actualizarTabla();

        // Eventos
        btnInscribir.addActionListener((ActionEvent e) -> inscribirPersona());
        btnEliminar.addActionListener((ActionEvent e) -> eliminarPersona());
        btnActualizar.addActionListener((ActionEvent e) -> actualizarPersona());
        btnCargarBD.addActionListener((ActionEvent e) -> cargarListadoEnBD());
        btnVerificarArchivo.addActionListener((ActionEvent e) -> verificarArchivoBinario());//temporal
    }

    private void actualizarTabla() {
        tableModel.setRowCount(0);
        List<Persona> personas = inscripciones.getListadoPersonas();
        for (Persona p : personas) {
            tableModel.addRow(new Object[]{p.getId(), p.getNombres(), p.getApellidos(), p.getEmail()});
        }
    }

    private void inscribirPersona() {
        Double id = Double.parseDouble(txtId.getText());
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String email = txtEmail.getText();
        
        Persona persona = new Persona(id, nombre, apellido, email);
        inscripciones.inscribirPersona(persona);
        inscripciones.guardarInformacion();
        actualizarTabla();
    }

    private void eliminarPersona() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            Double id = (Double) tableModel.getValueAt(row, 0);
            inscripciones.eliminarPersonaPorId(id);
            inscripciones.guardarInformacion();
            actualizarTabla();
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona una persona para eliminar.");
        }
    }

    private void actualizarPersona() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            Double id = (Double) tableModel.getValueAt(row, 0);
            String nombre = txtNombre.getText();
            String apellido = txtApellido.getText();
            String email = txtEmail.getText();
            
            Persona personaActualizada = new Persona(id, nombre, apellido, email);
            inscripciones.actualizarPersona(personaActualizada);
            inscripciones.guardarInformacion();
            actualizarTabla();
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona una persona para actualizar.");
        }
    }

    private void cargarListadoEnBD() {
        JOptionPane.showMessageDialog(this, "Funcionalidad pendiente: Cargar listado en la base de datos");
    }
    private void verificarArchivoBinario() {
        inscripciones.cargarDatos();
        actualizarTabla();
        JOptionPane.showMessageDialog(this, "Datos cargados desde el archivo binario.");
    }
}
