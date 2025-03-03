package com.poo.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poo.modelos.Curso;

@Service

public class CursoService {
    @Autowired
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