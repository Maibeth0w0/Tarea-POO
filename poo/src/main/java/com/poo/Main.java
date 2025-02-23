package com.poo;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        // Crear la instancia de CursosProfesores
        CursosProfesores cursosProfesores = new CursosProfesores();
        InscripcionesPersonas inscripcionesPersonas = new InscripcionesPersonas();
        CursosInscritos cursosInscritos = new CursosInscritos();



        // Crear algunos profesores
        Profesor profesor1 = new Profesor(12.0 , "juan jose", "perez aroza", "jose@mail.com","total");
        Profesor profesor2 = new Profesor(11.0 , "maria camila", "guevara ramirez", "maria@mail.com","total");

        Persona p1 = new Persona(1.0, "Luis", "Martínez", "luis@email.com");
        Persona p2 = new Persona(2.0, "Ana", "Gómez", "ana@email.com");

        Facultad facultad1 = new Facultad(9, "basicas e ingeneria", p1);
        Facultad facultad2 = new Facultad(9, "salud", p2);

        Programa programa1 = new Programa(16, "ingeneri de sistemas", 5.0 , "registro", facultad1 );
        Programa programa2 = new Programa(16, "enfermeria", 5.0 , "registro", facultad2 );
        // Crear algunos cursos
        Curso curso1 = new Curso(10, "matematicas" , programa2, Boolean.TRUE);
        Curso curso2 = new Curso(11, "Programación Avanzada",programa1, Boolean.TRUE);

        Inscripcion inscripcion1 = new Inscripcion(curso1, 2006, 5, new Estudiante());
        Inscripcion inscripcion2 = new Inscripcion(curso2, 2010, 10, new Estudiante());

        // Crear objetos CursoProfesor (relación curso-profesor)
        CursoProfesor asignacion1 = new CursoProfesor(profesor2,2012,3,curso1);
        CursoProfesor asignacion2 = new CursoProfesor(profesor1, 2020, 1, curso2);

        // Inscribir los cursos con los profesores
        cursosProfesores.inscribirCursoProfesores(asignacion1);
        cursosProfesores.inscribirCursoProfesores(asignacion2);
        cursosProfesores.guardarInformacion();

        // Inscribir las personas
        inscripcionesPersonas.inscribirPersona(profesor1);
        inscripcionesPersonas.inscribirPersona(profesor2);
        inscripcionesPersonas.guardarInformacion();

        // Inscribir los cursos
        cursosInscritos.inscribirCurso(inscripcion1);
        cursosInscritos.inscribirCurso(inscripcion2);
        cursosInscritos.guardarInformacion();

        // Mostrar la cantidad de asignaciones actuales
        System.out.println("Cantidad de cursos asignados: " + cursosProfesores.cantidadActual());

        // Imprimir la lista de profesores asignados
        List<String> listadoProfesores = cursosProfesores.imprimirListado();
        System.out.println("Profesores con cursos asignados:");
        for (String nombre : listadoProfesores) {
            System.out.println(nombre);
        }

        // Imprimir un curso-profesor en una posición específica
        System.out.println("Detalles de la primera asignación: " + cursosProfesores.imprimirPosicion(0));
    }
}
