package com.poo.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poo.persistencia.CursosInscritos;
import com.poo.repository.CursosInscritosRepository;

@Service
public class CursosInscritosService {

    @Autowired
    private CursosInscritosRepository cursosInscritosRepository;

    public List<CursosInscritos> findAll() {
        return cursosInscritosRepository.findAll();
    }

    public Optional<CursosInscritos> findById(Long id) {
        return cursosInscritosRepository.findById(id);
    }

    public CursosInscritos save(CursosInscritos cursosInscritos) {
        return cursosInscritosRepository.save(cursosInscritos);
    }

    public void deleteById(Long id) {
        cursosInscritosRepository.deleteById(id);
    }

    public CursosInscritos update(Long id, CursosInscritos cursosInscritos) {
        if (cursosInscritosRepository.existsById(id)) {
            return cursosInscritosRepository.save(cursosInscritos);
        } else {
            throw new RuntimeException("Curso no encontrado con id: " + id);
        }
    }

}