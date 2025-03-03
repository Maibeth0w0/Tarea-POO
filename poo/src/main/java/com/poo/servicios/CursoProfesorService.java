package com.poo.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poo.modelos.CursoProfesor;
import com.poo.persistencia.CursosProfesores;

@Service

public class CursoProfesorService {
    @Autowired
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