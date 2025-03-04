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

        Persona p1 = new Persona(1.0, "Luis", "Martínez", "luis@email.com");
        Persona p2 = new Persona(2.0, "Ana", "Gómez", "ana@email.com");


        Facultad facultad1 = new Facultad(9, "basicas e ingeneria", p1);
        Facultad facultad2 = new Facultad(9, "salud", p2);

        // Crear profesores
        Profesor profesor1 = new Profesor(12.0, "Juan Jose", "Perez Aroza", "jose@mail.com", "total");
        Profesor profesor2 = new Profesor(11.0, "Maria Camila", "Guevara Ramirez", "maria@mail.com", "total");

        // Crear estudiantes
        Estudiante estudiante1 = new Estudiante(100.0, "Juan Camilo", "Gonzalez Ramirez", "jg@mail.com", 1234, null, true, 35.5);
        Estudiante estudiante2 = new Estudiante(101.0, "Daniela", "Giraldo Pardo", "dg@mail.com", 1235, null, true, 40.5);

        // Crear programas académicos
        Programa programa1 = new Programa(16, "Ingeniería de Sistemas", 5.0, "registro", null);
        Programa programa2 = new Programa(17, "Enfermería", 5.0, "registro", null);
        
        // Asignar programas a estudiantes
        estudiante1.setPrograma(programa1);
        estudiante2.setPrograma(programa2);
        
        // Crear cursos
        Curso curso1 = new Curso(10, "Matemáticas", programa2, true);
        Curso curso2 = new Curso(11, "Programación Avanzada", programa1, true);

        // Crear inscripciones de cursos
        Inscripcion inscripcion1 = new Inscripcion(curso1, 2006, 5, estudiante1);
        Inscripcion inscripcion2 = new Inscripcion(curso2, 2010, 10, estudiante2);

        // Crear asignaciones de curso a profesor
        CursoProfesor asignacion1 = new CursoProfesor(profesor2, 2012, 3, curso1);
        CursoProfesor asignacion2 = new CursoProfesor(profesor1, 2020, 1, curso2);

        // Inscribir cursos con profesores
        cursosProfesores.inscribirCursoProfesores(asignacion1);
        cursosProfesores.inscribirCursoProfesores(asignacion2);
        cursosProfesores.guardarInformacion();

        // Inscribir personas
        inscripcionesPersonas.inscribirPersona(profesor1);
        inscripcionesPersonas.inscribirPersona(profesor2);
        inscripcionesPersonas.inscribirPersona(estudiante1);
        inscripcionesPersonas.inscribirPersona(estudiante2);
        inscripcionesPersonas.guardarInformacion();

        // Inscribir cursos
        cursosInscritos.inscribirCurso(inscripcion1);
        cursosInscritos.inscribirCurso(inscripcion2);
        cursosInscritos.guardarInformacion();

        // Iniciar la interfaz gráfica
        MainUI.main(args);
    }
}
