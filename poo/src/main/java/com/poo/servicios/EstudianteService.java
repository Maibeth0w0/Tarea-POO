package com.poo.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poo.modelos.Estudiante;
import com.poo.persistencia.InscripcionesPersonas;

@Service

public class EstudianteService {
    @Autowired
    private List<Estudiante> listaEstudiantes;
    private InscripcionesPersonas inscripcionesPersonas;

    public EstudianteService() {
        this.listaEstudiantes = new ArrayList<>();
        this.inscripcionesPersonas = new InscripcionesPersonas();
    }

    public void agregarEstudiante(Estudiante estudiante) {
        listaEstudiantes.add(estudiante);
        inscripcionesPersonas.inscribirPersona(estudiante);
        inscripcionesPersonas.guardarInformacion();
    }

    public List<Estudiante> obtenerEstudiantes() {
        return listaEstudiantes;
    }
}