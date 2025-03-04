package com.poo.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poo.modelos.Persona;
import com.poo.persistencia.InscripcionesPersonas;
import com.poo.repository.InscripcionesPersonasRepository;

@Service
public class InscripcionesPersonasService {
    
    @Autowired
    private InscripcionesPersonasRepository inscripcionesPersonasRepository;

    public List<InscripcionesPersonas> obtenerTodasLasInscripcionesPersonas() {
        return inscripcionesPersonasRepository.findAll();
    }

    public Optional<InscripcionesPersonas> obtenerInscripcionPersonaPorId(Persona persona) {
        return inscripcionesPersonasRepository.findById(persona);
    }

    public InscripcionesPersonas guardarInscripcionPersona(InscripcionesPersonas inscripcionPersona) {
        return inscripcionesPersonasRepository.save(inscripcionPersona);
    }

    public void eliminarInscripcionPersona(Persona persona) {
        inscripcionesPersonasRepository.deleteById(persona);
    }

    public InscripcionesPersonas actualizarInscripcionPersona(InscripcionesPersonas inscripcionPersona) {
        return inscripcionesPersonasRepository.save(inscripcionPersona);
    }
}
