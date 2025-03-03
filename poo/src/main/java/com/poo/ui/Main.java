//package com.poo;
package com.poo.ui;

import java.util.List;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.poo.modelos.*;
import com.poo.persistencia.*;

@SpringBootApplication

public class Main {
    public static void main(String[] args) {

        CursosProfesores cursosProfesores = new CursosProfesores();
        InscripcionesPersonas inscripcionesPersonas = new InscripcionesPersonas();
        CursosInscritos cursosInscritos = new CursosInscritos();

        inscripcionesPersonas.cargarDatos();
        cursosProfesores.cargarDatos();
        cursosInscritos.cargarDatos();

        Profesor profesor1 = new Profesor(12 , "Juan Jose", "Perez Aroza", "jose@mail.com", "total");
        Profesor profesor2 = new Profesor(11, "Maria Camila", "Guevara Ramirez", "maria@mail.com", "total");

        Persona p1 = new Persona(1, "Luis", "Martínez", "luis@email.com");
        Persona p2 = new Persona(2, "Ana", "Gómez", "ana@email.com");
        
        Facultad facultad1 = new Facultad(9, "Básicas e Ingeniería", p1);
        Facultad facultad2 = new Facultad(9, "Salud", p2);
        
        Programa programa1 = new Programa(16, "Ingeniería de Sistemas", 5.0, "registro", facultad1);
        Programa programa2 = new Programa(16, "Enfermería", 5.0, "registro", facultad2);
        
        
        Estudiante estudiante1 = new Estudiante(100, "juan camilo", "gonzales ramirez", "jg@mail.com", 1234, programa1, Boolean.TRUE, 35.5);
        Estudiante estudiante2 = new Estudiante(101, "danaiela ", "giraldo pardo", "dg@mail.com", 1235, programa2, Boolean.TRUE, 40.5);
        
        Curso curso1 = new Curso(10, "Matemáticas", programa2, Boolean.TRUE);
        Curso curso2 = new Curso(11, "Programación Avanzada", programa1, Boolean.TRUE);

        Inscripcion inscripcion1 = new Inscripcion(curso1, 2006, 5, estudiante1);
        Inscripcion inscripcion2 = new Inscripcion(curso2, 2010, 10, estudiante2);

        CursoProfesor asignacion1 = new CursoProfesor(profesor2, 2012, 3, curso1);
        CursoProfesor asignacion2 = new CursoProfesor(profesor1, 2020, 1, curso2);

        cursosProfesores.inscribirCursoProfesores(asignacion1);
        cursosProfesores.inscribirCursoProfesores(asignacion2);
        cursosProfesores.guardarInformacion();

        inscripcionesPersonas.inscribirPersona(profesor1);
        inscripcionesPersonas.inscribirPersona(profesor2);
        inscripcionesPersonas.guardarInformacion();

        cursosInscritos.inscribirCurso(inscripcion1);
        cursosInscritos.inscribirCurso(inscripcion2);
        cursosInscritos.guardarInformacion();

        System.out.println("Cantidad de cursos asignados: " + cursosProfesores.cantidadActual());

        List<String> listadoProfesores = cursosProfesores.imprimirListado();
        System.out.println("Profesores con cursos asignados:");
        for (String nombre : listadoProfesores) {
            System.out.println(nombre);
        }

        System.out.println("Detalles de la primera asignación: " + cursosProfesores.imprimirPosicion(0));

        MainUI.main(args);
    }
}