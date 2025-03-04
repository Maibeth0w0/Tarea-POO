package com.poo.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poo.modelos.Inscripcion;
import com.poo.persistencia.CursosInscritos;
import com.poo.repository.CursosInscritosRepository;

@Service
public class CursosInscritosService {

    @Autowired
    private CursosInscritosRepository cursosInscritosRepository;

    public List<CursosInscritos> obtenerTodosLosCursosInscritos() {
        return cursosInscritosRepository.findAll();
    }

    public Optional<CursosInscritos> obtenerCursoInscritoPorId(Inscripcion inscripcion) {
        return cursosInscritosRepository.findById(inscripcion);
    }

    public CursosInscritos guardarCursoInscrito(CursosInscritos cursoInscrito) {
        return cursosInscritosRepository.save(cursoInscrito);
    }

    public void eliminarCursoInscrito(Inscripcion inscripcion) {
        cursosInscritosRepository.deleteById(inscripcion);
    }

    public CursosInscritos actualizarCursoInscrito(CursosInscritos cursoInscrito) {
        return cursosInscritosRepository.save(cursoInscrito);
    }
}