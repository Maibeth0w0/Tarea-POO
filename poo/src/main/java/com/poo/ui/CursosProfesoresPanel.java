package com.poo.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
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
import com.poo.modelos.CursoProfesor;
import com.poo.modelos.Profesor;
import com.poo.persistencia.CursosProfesores;

public class CursosProfesoresPanel extends JPanel {
    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private JTextField txtAnno, txtSemestre;
    private JComboBox<String> cbCurso, cbProfesor;
    private JButton btnAgregar, btnEditar, btnEliminar, btnCargarBD;

    private CursosProfesores cursosProfesores;

    public CursosProfesoresPanel(CursosProfesores cursosProfesores) {
        this.cursosProfesores = cursosProfesores;
        setLayout(new BorderLayout());

        cursosProfesores = new CursosProfesores();
        cursosProfesores.cargarDatos();

        // Panel superior con formulario
        JPanel panelFormulario = new JPanel(new GridLayout(2, 4, 10, 10));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Asignar Curso a Profesor"));

        txtAnno = new JTextField();
        txtSemestre = new JTextField();
        cbCurso = new JComboBox<>(new String[]{"Curso 1", "Curso 2", "Curso 3"}); // TODO: Cargar desde BD
        cbProfesor = new JComboBox<>(new String[]{"Profesor 1", "Profesor 2", "Profesor 3"}); // TODO: Cargar desde BD

        panelFormulario.add(new JLabel("Año:"));
        panelFormulario.add(txtAnno);
        panelFormulario.add(new JLabel("Semestre:"));
        panelFormulario.add(txtSemestre);
        panelFormulario.add(new JLabel("Curso:"));
        panelFormulario.add(cbCurso);
        panelFormulario.add(new JLabel("Profesor:"));
        panelFormulario.add(cbProfesor);

        // Tabla para mostrar asignaciones
        modeloTabla = new DefaultTableModel(new String[]{"Año", "Semestre", "Curso", "Profesor"}, 0);
        tabla = new JTable(modeloTabla);
        JScrollPane scrollTabla = new JScrollPane(tabla);
        cargarTabla();

        // Panel inferior con botones
        JPanel panelBotones = new JPanel();
        btnAgregar = new JButton("Agregar");
        btnEditar = new JButton("Editar");
        btnEliminar = new JButton("Eliminar");
        btnCargarBD = new JButton("Cargar lista a BD");

        panelBotones.add(btnAgregar);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnCargarBD);

        // Listeners para botones
        btnAgregar.addActionListener(e -> agregarCursoProfesor());
        btnCargarBD.addActionListener(e -> cargarListaEnBD());

        // Agregar componentes al panel principal
        add(panelFormulario, BorderLayout.NORTH);
        add(scrollTabla, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void cargarTabla() {
        modeloTabla.setRowCount(0);
        List<CursoProfesor> lista = cursosProfesores.getListadoCursoProfesores();
        for (CursoProfesor cp : lista) {
            modeloTabla.addRow(new Object[]{
                    cp.getAnno(),
                    cp.getSemestre(),
                    cp.getCurso().getNombre(),
                    cp.getProfesor().getNombres()
            });
        }
    }

    private void agregarCursoProfesor() {
        String anno = txtAnno.getText().trim();
        String semestre = txtSemestre.getText().trim();
        String nombreCurso = (String) cbCurso.getSelectedItem();
        String nombreProfesor = (String) cbProfesor.getSelectedItem();
    
        if (anno.isEmpty() || semestre.isEmpty() || nombreCurso == null || nombreProfesor == null) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!anno.matches("\\d+") || !semestre.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "El año y el semestre deben ser números enteros", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int annoInt = Integer.parseInt(anno);
        int semestreInt = Integer.parseInt(semestre);

        Curso cursoObj = cursosProfesores.obtenerCursoPorNombre(nombreCurso);
        Profesor profesorObj = cursosProfesores.obtenerProfesorPorNombre(nombreProfesor);

        if (cursoObj == null || profesorObj == null) {
            JOptionPane.showMessageDialog(this, "Curso o profesor no existen", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        CursoProfesor nuevo = new CursoProfesor(profesorObj,cursoObj, annoInt, semestreInt);
        cursosProfesores.inscribirCursoProfesores(nuevo);
        cursosProfesores.guardarInformacion();

        cargarTabla();
        JOptionPane.showMessageDialog(this, "Asignación guardada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    private void cargarListaEnBD() {
        cursosProfesores.guardarInformacion();
        JOptionPane.showMessageDialog(this, "Lista cargada en la base de datos correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }
}
