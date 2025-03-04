package com.poo.servicios;

import com.poo.persistencia.InscripcionesPersonas;
import com.poo.repository.InscripcionesPersonasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class InscripcionesPersonasService {

    @Autowired
    private InscripcionesPersonasRepository repository;

    public List<InscripcionesPersonas> obtenerTodas() {
        return repository.findAll();
    }

    public Optional<InscripcionesPersonas> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    public InscripcionesPersonas guardar(InscripcionesPersonas inscripciones) {
        return repository.save(inscripciones);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}