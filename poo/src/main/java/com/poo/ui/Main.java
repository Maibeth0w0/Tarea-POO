package com.poo.ui;
import java.util.List;
import com.poo.modelos.*;
import com.poo.persistencia.*;
import com.poo.ui.MainUI;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.swing.SwingUtilities;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        // Crear instancias de los gestores de datos
        CursosProfesores cursosProfesores = new CursosProfesores();
        InscripcionesPersonas inscripcionesPersonas = new InscripcionesPersonas();
        CursosInscritos cursosInscritos = new CursosInscritos();

        // Cargar información desde los archivos binarios cifrados
        inscripcionesPersonas.cargarDatos();
        cursosProfesores.cargarDatos();
        cursosInscritos.cargarDatos();

        Profesor p1 = new Profesor("Luis", "Martínez", "luis@email.com","Catedratico", null);
        /* Profesor p2 = new Persona("Ana", "Gómez", "ana@email.com", Persona.TipoPersona.PROFESOR); */


        Facultad facultad1 = new Facultad("basicas e ingeneria", p1);
        /* Facultad facultad2 = new Facultad("salud", p2); */

        // Crear profesores
        /* Profesor profesor1 = new Profesor(12.0, "Juan Jose", "Perez Aroza", "jose@mail.com", "total");
        Profesor profesor2 = new Profesor(11.0, "Maria Camila", "Guevara Ramirez", "maria@mail.com", "total"); */

        // Crear estudiantes
        Estudiante estudiante1 = new Estudiante( "Juan Camilo", "Gonzalez Ramirez", "jg@mail.com", 1234, null, true, 35.5);
        /* Estudiante estudiante2 = new Estudiante(101.0, "Daniela", "Giraldo Pardo", "dg@mail.com", 1235, null, true, 40.5); */

        // Crear programas académicos
        Programa programa1 = new Programa("Ingeniería de Sistemas", 5.0, "registro", facultad1);
        /* Programa programa2 = new Programa(17, "Enfermería", 5.0, "registro", null); */
        
        // Asignar programas a estudiantes
        estudiante1.setPrograma(programa1);
        /* estudiante2.setPrograma(programa2); */
        
        // Crear cursos
        Curso curso1 = new Curso("Matemáticas", programa1, true);
        /* Curso curso2 = new Curso(11, "Programación Avanzada", programa1, true); */

        // Crear inscripciones de cursos
        Inscripcion inscripcion1 = new Inscripcion(curso1, 2006, 5, estudiante1);
        /* Inscripcion inscripcion2 = new Inscripcion(curso2, 2010, 10, estudiante2); */

        // Crear asignaciones de curso a profesor
        CursoProfesor asignacion1 = new CursoProfesor(p1,curso1,2025,1);
        /* CursoProfesor asignacion2 = new CursoProfesor(profesor1, 2020, 1, curso2); */

        // Inscribir cursos con profesores
        cursosProfesores.inscribirCursoProfesores(asignacion1);
        /* cursosProfesores.inscribirCursoProfesores(asignacion2); */
        cursosProfesores.guardarInformacion();

        // Inscribir personas
        inscripcionesPersonas.inscribirPersona(p1);
        /* inscripcionesPersonas.inscribirPersona(profesor2); */
        inscripcionesPersonas.inscribirPersona(estudiante1);
        /* inscripcionesPersonas.inscribirPersona(estudiante2); */
        inscripcionesPersonas.guardarInformacion();

        // Inscribir cursos
        cursosInscritos.inscribirCurso(inscripcion1);
        /* cursosInscritos.inscribirCurso(inscripcion2); */
        cursosInscritos.guardarInformacion();

        // Iniciar la interfaz gráfica
        MainUI.main(args);
    }
}
