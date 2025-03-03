package com.poo.servicios;

import com.poo.modelos.CursoProfesor;
import com.poo.persistencia.CursosProfesores;

import java.util.ArrayList;
import java.util.List;

public class CursoProfesorService {
    private List<CursoProfesor> listaAsignaciones;
    private CursosProfesores cursosProfesores;

    public CursoProfesorService() {
        this.listaAsignaciones = new ArrayList<>();
        this.cursosProfesores = new CursosProfesores();
    }

    public void agregarAsignacionProfesor(CursoProfesor asignacion) {
        listaAsignaciones.add(asignacion);
        cursosProfesores.inscribirCursoProfesores(asignacion);
        cursosProfesores.guardarInformacion();
    }

    public List<CursoProfesor> obtenerAsignacionesProfesores() {
        return listaAsignaciones;
    }
}