package com.poo.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.poo.modelos.Curso;
import com.poo.modelos.Estudiante;
import com.poo.modelos.Inscripcion;
import com.poo.persistencia.CursosInscritos;

public class CursosInscritosPanel extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private JComboBox<Curso> comboCursos;
    private JComboBox<Estudiante> comboEstudiantes;
    private JTextField txtAnno, txtSemestre;
    private CursosInscritos cursosInscritos;

    public CursosInscritosPanel(CursosInscritos cursosInscritos) {
        this.cursosInscritos = cursosInscritos;
        setLayout(new BorderLayout());

        // Crear tabla
        tableModel = new DefaultTableModel(new String[]{"Curso", "Estudiante", "Año", "Semestre"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

          // Llenar los JTextField cuando se selecciona una fila
          table.getSelectionModel().addListSelectionListener(event -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                // Obtener valores de la tabla y llenar los campos de texto
                txtAnno.setText(table.getValueAt(selectedRow, 2).toString());
                txtSemestre.setText(table.getValueAt(selectedRow, 3).toString());
        
                //  Actualizar el combo de Cursos con el curso seleccionado en la tabla
                String nombreCurso = table.getValueAt(selectedRow, 0).toString();
                for (int i = 0; i < comboCursos.getItemCount(); i++) {
                    Curso curso = comboCursos.getItemAt(i);
                    if (curso.getNombre().equals(nombreCurso)) {
                        comboCursos.setSelectedIndex(i);
                        break;
                    }
                }
        
                //  Actualizar el combo de Estudiantes con el estudiante seleccionado en la tabla
                String nombreEstudiante = table.getValueAt(selectedRow, 1).toString();
                for (int i = 0; i < comboEstudiantes.getItemCount(); i++) {
                    Estudiante estudiante = comboEstudiantes.getItemAt(i);
                    String nombreCompleto = estudiante.getNombres() + " " + estudiante.getApellidos();
                    if (nombreCompleto.equals(nombreEstudiante)) {
                        comboEstudiantes.setSelectedIndex(i);
                        break;
                    }
                }
            }
        });

        // Crear formulario
        JPanel formPanel = new JPanel(new GridLayout(5, 2));
        formPanel.add(new JLabel("Curso:"));
        comboCursos = new JComboBox<>();
        formPanel.add(comboCursos);

        formPanel.add(new JLabel("Estudiante:"));
        comboEstudiantes = new JComboBox<>();
        formPanel.add(comboEstudiantes);

        formPanel.add(new JLabel("Año:"));
        txtAnno = new JTextField();
        formPanel.add(txtAnno);

        formPanel.add(new JLabel("Semestre:"));
        txtSemestre = new JTextField();
        formPanel.add(txtSemestre);

        // Botones
        JButton btnInscribir = new JButton("Inscribir");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnActualizar = new JButton("Actualizar");
        JButton btnVerificarArchivo = new JButton("Verificar Archivo Binario");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnInscribir);
        buttonPanel.add(btnEliminar);
        buttonPanel.add(btnActualizar);
        buttonPanel.add(btnVerificarArchivo);

        add(formPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);

        // Cargar datos en la tabla
        actualizarTabla();

        // Eventos
        btnInscribir.addActionListener((ActionEvent e) -> inscribirCurso());
        btnEliminar.addActionListener((ActionEvent e) -> eliminarInscripcion());
        btnActualizar.addActionListener((ActionEvent e) -> actualizarCurso());
        btnVerificarArchivo.addActionListener((ActionEvent e) -> verificarArchivoBinario());
    }

    private void actualizarTabla() {
        tableModel.setRowCount(0);
        List<Inscripcion> inscripciones = cursosInscritos.getListadoInscripciones();
        for (Inscripcion inscripcion : inscripciones) {
            String cursoNombre = (inscripcion.getCurso() != null) ? inscripcion.getCurso().getNombre() : "Curso no asignado";
            String estudianteNombre = (inscripcion.getEstudiante() != null) ? inscripcion.getEstudiante().getNombres() + " " + inscripcion.getEstudiante().getApellidos() : "Estudiante no asignado";
            
            tableModel.addRow(new Object[]{
                cursoNombre,
                estudianteNombre,
                inscripcion.getAnno(),
                inscripcion.getSemestre()
            });
        }
    }

    private void inscribirCurso() {
        Curso curso = (Curso) comboCursos.getSelectedItem();
        Estudiante estudiante = (Estudiante) comboEstudiantes.getSelectedItem();
        int anio = Integer.parseInt(txtAnno.getText());
        int semestre = Integer.parseInt(txtSemestre.getText());
        
        Inscripcion inscripcion = new Inscripcion(curso, anio, semestre, estudiante);
        cursosInscritos.inscribirCurso(inscripcion);
        cursosInscritos.guardarInformacion();
        actualizarTabla();
    }

    private void eliminarInscripcion() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            Curso curso = (Curso) tableModel.getValueAt(row, 0);
            cursosInscritos.eliminarInscripcion(new Inscripcion(curso, 0, 0, new Estudiante()));
            cursosInscritos.guardarInformacion();
            actualizarTabla();
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona un curso para eliminar.");
        }
    }

    private void actualizarCurso() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            Curso curso = (Curso) comboCursos.getSelectedItem();
            Estudiante estudiante = (Estudiante) comboEstudiantes.getSelectedItem();
            
            // Obtener valores desde los campos
            String anioTexto = txtAnno.getText().trim();
            String semestreTexto = txtSemestre.getText().trim();
    
            // DEPURACIÓN: Ver qué valores está obteniendo
            System.out.println("Valor en txtAnio: '" + anioTexto + "'");
            System.out.println("Valor en txtSemestre: '" + semestreTexto + "'");
    
            // Validar que los campos de año y semestre no estén vacíos
            if (anioTexto.isEmpty() || semestreTexto.isEmpty()) {
                JOptionPane.showMessageDialog(this, "⚠️ Año y semestre no pueden estar vacíos.");
                return;
            }
    
            // Convertir a número
            int anio = Integer.parseInt(anioTexto);
            int semestre = Integer.parseInt(semestreTexto);
    
            // Crear nueva inscripción actualizada
            Inscripcion inscripcionActualizada = new Inscripcion(curso, anio, semestre, estudiante);
            
            // Actualizar la inscripción en la lista y guardar en el archivo binario
            cursosInscritos.actualizarCurso(inscripcionActualizada);
            cursosInscritos.guardarInformacion();
            
            // Refrescar la tabla
            actualizarTabla();
        } else {
            JOptionPane.showMessageDialog(this, "⚠️ Selecciona un curso para actualizar.");
        }
    }
    
    private void verificarArchivoBinario() {
        cursosInscritos.cargarDatos();
        actualizarTabla();
        JOptionPane.showMessageDialog(this, "Datos cargados desde el archivo binario.");
    }
}
