package com.poo.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

@Service

public class GestorDatos {
    @Autowired
    private List<Profesor> listaProfesores;
    private List<Persona> listaPersonas;
    private List<Facultad> listaFacultades;
    private List<Programa> listaProgramas;
    private List<Estudiante> listaEstudiantes;
    private List<Curso> listaCursos;
    private List<Inscripcion> listaInscripciones;
    private List<CursoProfesor> listaAsignaciones;
    
    private CursosProfesores cursosProfesores;
    private InscripcionesPersonas inscripcionesPersonas;
    private CursosInscritos cursosInscritos;

    public GestorDatos() {
        this.listaProfesores = new ArrayList<>();
        this.listaPersonas = new ArrayList<>();
        this.listaFacultades = new ArrayList<>();
        this.listaProgramas = new ArrayList<>();
        this.listaEstudiantes = new ArrayList<>();
        this.listaCursos = new ArrayList<>();
        this.listaInscripciones = new ArrayList<>();
        this.listaAsignaciones = new ArrayList<>();

        this.cursosProfesores = new CursosProfesores();
        this.inscripcionesPersonas = new InscripcionesPersonas();
        this.cursosInscritos = new CursosInscritos();
        
        cargarDatosDesdeArchivos();
    }

    private void cargarDatosDesdeArchivos() {
        inscripcionesPersonas.cargarDatos();
        cursosProfesores.cargarDatos();
        cursosInscritos.cargarDatos();
    }

    public void agregarProfesor(Profesor profesor) {
        listaProfesores.add(profesor);
        inscripcionesPersonas.inscribirPersona(profesor);
        inscripcionesPersonas.guardarInformacion();
    }
    
    public void agregarEstudiante(Estudiante estudiante) {
        listaEstudiantes.add(estudiante);
        inscripcionesPersonas.inscribirPersona(estudiante);
        inscripcionesPersonas.guardarInformacion();
    }
    
    public void agregarCurso(Curso curso) {
        listaCursos.add(curso);
    }
    
    public void agregarInscripcion(Inscripcion inscripcion) {
        listaInscripciones.add(inscripcion);
        cursosInscritos.inscribirCurso(inscripcion);
        cursosInscritos.guardarInformacion();
    }
    
    public void agregarAsignacionProfesor(CursoProfesor asignacion) {
        listaAsignaciones.add(asignacion);
        cursosProfesores.inscribirCursoProfesores(asignacion);
        cursosProfesores.guardarInformacion();
    }
    
    public List<Profesor> obtenerProfesores() {
        return listaProfesores;
    }
    
    public List<Estudiante> obtenerEstudiantes() {
        return listaEstudiantes;
    }
    
    public List<Curso> obtenerCursos() {
        return listaCursos;
    }
    
    public List<Inscripcion> obtenerInscripciones() {
        return listaInscripciones;
    }
    
    public List<CursoProfesor> obtenerAsignacionesProfesores() {
        return listaAsignaciones;
    }
}
