package com.poo.servicios;

import com.poo.modelos.Estudiante;
import com.poo.persistencia.InscripcionesPersonas;

import java.util.ArrayList;
import java.util.List;

public class EstudianteService {
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