//package com.poo;
package com.poo.ui;

import java.util.List;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.poo.modelos.Curso;
import com.poo.modelos.CursoProfesor;
import com.poo.modelos.Estudiante;
import com.poo.modelos.Facultad;
import com.poo.modelos.Inscripcion;
import com.poo.modelos.Persona;
import com.poo.modelos.Profesor;
import com.poo.modelos.Programa;
import com.poo.persistencia.CursosInscritos;
import com.poo.persistencia.CursosProfesores;
import com.poo.persistencia.InscripcionesPersonas;

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

        // Crear profesores
        Profesor profesor1 = new Profesor(12.0 , "Juan Jose", "Perez Aroza", "jose@mail.com", "total");
        Profesor profesor2 = new Profesor(11.0 , "Maria Camila", "Guevara Ramirez", "maria@mail.com", "total");

        // Crear personas
        Persona p1 = new Persona(1.0, "Luis", "Martínez", "luis@email.com");
        Persona p2 = new Persona(2.0, "Ana", "Gómez", "ana@email.com");
        

        // Crear facultades
        Facultad facultad1 = new Facultad(9, "Básicas e Ingeniería", p1);
        Facultad facultad2 = new Facultad(9, "Salud", p2);

        // Crear programas académicos
        Programa programa1 = new Programa(16, "Ingeniería de Sistemas", 5.0, "registro", facultad1);
        Programa programa2 = new Programa(16, "Enfermería", 5.0, "registro", facultad2);
        
        // crear estudiante
        Estudiante estudiante1 = new Estudiante(100.0, "juan camilo", "gonzales ramirez", "jg@mail.com", 1234, programa1, Boolean.TRUE, 35.5); 
        Estudiante estudiante2 = new Estudiante(101.0, "danaiela ", "giraldo pardo", "dg@mail.com", 1235, programa2, Boolean.TRUE, 40.5); 
        // Crear cursos
        Curso curso1 = new Curso(10, "Matemáticas", programa2, Boolean.TRUE);
        Curso curso2 = new Curso(11, "Programación Avanzada", programa1, Boolean.TRUE);

        // Crear inscripciones de cursos
        Inscripcion inscripcion1 = new Inscripcion(curso1, 2006, 5, estudiante1);
        Inscripcion inscripcion2 = new Inscripcion(curso2, 2010, 10, estudiante2);

        // Crear asignaciones de curso a profesor
        CursoProfesor asignacion1 = new CursoProfesor(profesor2, 2012, 3, curso1);
        CursoProfesor asignacion2 = new CursoProfesor(profesor1, 2020, 1, curso2);

        // Inscribir los cursos con los profesores
        cursosProfesores.inscribirCursoProfesores(asignacion1);
        cursosProfesores.inscribirCursoProfesores(asignacion2);
        cursosProfesores.guardarInformacion(); // 

        // Inscribir personas (profesores y estudiantes)
        inscripcionesPersonas.inscribirPersona(profesor1);
        inscripcionesPersonas.inscribirPersona(profesor2);
        inscripcionesPersonas.guardarInformacion(); // 

        // Inscribir cursos
        cursosInscritos.inscribirCurso(inscripcion1);
        cursosInscritos.inscribirCurso(inscripcion2);
        cursosInscritos.guardarInformacion(); // 

        // Mostrar la cantidad de cursos asignados a profesores
        System.out.println("Cantidad de cursos asignados: " + cursosProfesores.cantidadActual());

        // Imprimir la lista de profesores con cursos asignados
        List<String> listadoProfesores = cursosProfesores.imprimirListado();
        System.out.println("Profesores con cursos asignados:");
        for (String nombre : listadoProfesores) {
            System.out.println(nombre);
        }

        // Imprimir detalles de la primera asignación curso-profesor
        System.out.println("Detalles de la primera asignación: " + cursosProfesores.imprimirPosicion(0));


        MainUI.main(args);
    }
}

