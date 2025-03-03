package com.poo.servicios;

import com.poo.modelos.Curso;

import java.util.ArrayList;
import java.util.List;

public class CursoService {
    private List<Curso> listaCursos;

    public CursoService() {
        this.listaCursos = new ArrayList<>();
    }

    public void agregarCurso(Curso curso) {
        listaCursos.add(curso);
    }

    public List<Curso> obtenerCursos() {
        return listaCursos;
    }
}